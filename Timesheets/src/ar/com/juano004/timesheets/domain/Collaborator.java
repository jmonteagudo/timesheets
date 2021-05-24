package ar.com.juano004.timesheets.domain;

import java.time.Duration;
import java.time.Instant;
import java.time.YearMonth;
import java.time.ZoneId;
import java.util.TreeSet;

public class Collaborator {
	private String name;
	private TreeSet<Activity> timeline;
	private String zone;
	
	public Collaborator(String name) {
		this.name = name;
		this.timeline = new TreeSet<Activity>();
		this.zone = "Argentina/Buenos Aires";
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
	
	public Long getHoursPerTask(Task wished) {
		Long totalTime = 0L;
		
		for(Activity current: timeline) {
			if(current.getRelatedTask().equals(wished)) {
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
