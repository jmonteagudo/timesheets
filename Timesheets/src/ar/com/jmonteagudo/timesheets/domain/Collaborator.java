package ar.com.jmonteagudo.timesheets.domain;

import java.time.Duration;
import java.time.Instant;
import java.time.MonthDay;
import java.time.YearMonth;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.TreeSet;

import ar.com.jmonteagudo.timesheets.util.Configuration;
import ar.com.jmonteagudo.timesheets.util.Date;

public class Collaborator {
	private String name;
	private TreeSet<Activity> timeline;
	private String zone;
	private String dateFormat; 
	
	public Collaborator(String name) {
		this.name = name;
		this.timeline = new TreeSet<Activity>();
		this.zone = Configuration.properties.getProperty("Zone");
		this.dateFormat = Configuration.properties.getProperty("dateFormat");
	}
	
	public Boolean startANewActivity(Task currentTask) {
		Activity newActivity = new Activity(currentTask);
		
		if(timeline.size() > 0 && timeline.last().getFinalTime()==null) {
			timeline.last().setFinalTime(newActivity.getInitialTime());
		}
		
		return timeline.add(newActivity);
	}
	
	public Boolean addAnActivityManually(Activity newActivity) {
		// It is no able to add a future activity 
		if(newActivity.isInTheFuture()) {
			return false;
		}
		return timeline.add(newActivity);		
	}
	
	public void endActivity() {
		if(timeline.size()>0) {
			timeline.last().setFinalTime(Instant.now());
		}
	}
	
	public void endActivity(Activity currentActivity) {
		Activity wished = searchAnActivity(currentActivity);
		if(wished!=null) {
			wished.setFinalTime(Instant.now());
		}
	}
	
	private Activity searchAnActivity(Activity wished) {
		for(Activity current: timeline) {
			if(current.equals(wished)) {
				return current;
			}
		}
		return null;
	}
	
	public Activity getCurrentActivity() {
		if(timeline.size()>0) {
			return timeline.last();
		}
		else {
			return null;
		}

	}
	
	public Long getTimePerTask(Task wished) {
		Long totalTime = 0L;
		
		for(Activity current: timeline) {
			if(current.getRelatedTask().equals(wished)) {
				totalTime+=(Duration.between(current.getInitialTime(), current.getFinalTime())).toHours();				
			}
		}
		return totalTime;
	}
	
	public Long getTimePerTask(Task wished, ZonedDateTime day) {
		Long totalTime = 0L;
		
		for(Activity current: timeline) {
			if(current.getRelatedTask().equals(wished) && Date.sameDate(current.getInitialTime(), day)) {
				totalTime+=(Duration.between(current.getFinalTime(), current.getFinalTime())).toHours();				
			}
		}
		return totalTime;
	}
	
	public TreeSet<Activity> getACtivitiesPerMonth(YearMonth month){
		TreeSet<Activity> filter = new TreeSet<Activity>();

		for(Activity current: timeline) {
			if(month.getMonth().equals(current.getInitialTime().atZone(ZoneId.of(this.zone)).getMonth())) {
				filter.add(current);
			}
		}
		
		return filter;
	}
	
	public TreeSet<Activity> getACtivitiesPerDay(ZonedDateTime day){
		TreeSet<Activity> filter = new TreeSet<Activity>();

		for(Activity current: timeline) {
			if(Date.sameDate(current.getInitialTime(), day)) {
				filter.add(current);
			}
		}
		
		return filter;
	}
	
	public String toString() {
		String currentState = this.name + "\n\n";
		
		for(Activity current: timeline) {
			currentState += current.toString() + "\n";
		}
		
		return currentState;
	}

	public TreeSet<Activity> getTimeline() {
		return timeline;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
