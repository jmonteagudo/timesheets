package ar.com.jmonteagudo.timesheets.domain;

import java.time.*;

public class Activity implements Comparable<Activity>{
	private Task relatedTask;
	private Instant initialTime;
	private Instant finalTime;

	public Activity(Task relatedTask) {
		this.relatedTask = relatedTask;
		this.initialTime = Instant.now();
		this.finalTime = null;
	}
	
	public Activity(Task relatedTask, Instant initialTime, Instant finalTime) {
		this.relatedTask = relatedTask;
		this.initialTime = initialTime;
		setFinalTime(finalTime);
	}
	
	public Boolean isInTheFuture() {
		return (this.initialTime.compareTo(Instant.now())>0);
	}

	public Task getRelatedTask() {
		return relatedTask;
	}

	public void setRelatedTask(Task relatedTask) {
		this.relatedTask = relatedTask;
	}

	public Instant getInitialTime() {
		return initialTime;
	}

	public void setInitialTime(Instant initialTime) {
		if(initialTime.compareTo(this.finalTime)<0) {
			this.initialTime = initialTime;	
		}
	}

	public Instant getFinalTime() {
		return finalTime;
	}

	public void setFinalTime(Instant finalTime) {
		if(finalTime.compareTo(this.initialTime)>=0) {
			this.finalTime = finalTime;
		}
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((initialTime == null) ? 0 : initialTime.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Activity other = (Activity) obj;
		if (initialTime == null) {
			if (other.initialTime != null)
				return false;
		} else if (!initialTime.equals(other.initialTime))
			return false;
		return true;
	}
	
	public String toString() {
		String currentState = "";
		
		currentState += this.relatedTask.toString() + "[" + this.initialTime + " / " + this.finalTime + "]";
		
		return currentState;
	}

	@Override
	public int compareTo(Activity o) {
		return this.initialTime.compareTo(o.getInitialTime());
	}	
}
