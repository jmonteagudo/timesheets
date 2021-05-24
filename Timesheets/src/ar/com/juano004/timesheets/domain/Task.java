package ar.com.juano004.timesheets.domain;

public class Task {
	
	private Integer id;
	private String description;
	private State currentState;
	
	public Task() {
		this.currentState = State.PROPOSAL;
	}
	
	public Task(Integer id, String description) {
		this.currentState = State.PROPOSAL;
		this.id = id;
		this.description = description;
	}
	
	public Task(Integer id, String description, State initialState) {
		this.currentState = initialState;
		this.id = id;
		this.description = description;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public State getCurrentState() {
		return currentState;
	}

	public void setCurrentState(State currentState) {
		this.currentState = currentState;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		Task other = (Task) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	public String toString() {
		return this.id + " - " + this.description;
	}
	

}
