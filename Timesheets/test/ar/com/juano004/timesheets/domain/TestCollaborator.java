package ar.com.juano004.timesheets.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.time.Duration;
import java.time.Instant;

import org.junit.jupiter.api.Test;

class TestCollaborator {

	@Test
	void toBeAbleToCreateACollaborator() {
		// Preparation
		final String NAME = "Juan";
		
		// Execution
		Collaborator newOne = new Collaborator(NAME);
		
		// Validation
		assertNotNull(newOne);
		assertEquals(NAME, newOne.getName());
		assertNotNull(newOne.getTimeline());
	}

	@Test
	void toStartANewActivity() {
		// Preparation
		final Integer ID = 178342;
		final String DESCRIPTION = "Automatización Interfaz 54000";
		final Task CURRENT_TASK = new Task(ID, DESCRIPTION);
		final String NAME = "Juan";
		
		// Execution
		Collaborator newOne = new Collaborator(NAME);
		newOne.startANewActivity(CURRENT_TASK);
		
		// Validation
		assertNotNull(newOne);
		assertNotNull(newOne.getCurrentActivity());
		assertEquals(CURRENT_TASK, newOne.getCurrentActivity().getRelatedTask());
	}
	
	@Test
	void toStartAnActivityManually() {
		// Preparation
		final Task RELATED_TASK = new Task();
		final Instant INITIAL_TIME = Instant.now();	
		final Instant FINAL_TIME = Instant.now().plus(Duration.ofDays(1));
		final Activity NEW_ACTIVITY = new Activity(RELATED_TASK, INITIAL_TIME, FINAL_TIME);
		
		// Execution
		Collaborator current = new Collaborator("Juan");
		current.addAnActivityManually(NEW_ACTIVITY);
		
		// Validation
		assertNotNull(current);
		assertEquals(NEW_ACTIVITY, current.getCurrentActivity());
		assertEquals(RELATED_TASK, current.getCurrentActivity().getRelatedTask());
	}
	
	@Test
	void toNotCreateAFutureActivity() {
		// Preparation
		final Task RELATED_TASK = new Task();
		final Instant INITIAL_TIME = Instant.now().plus(Duration.ofDays(1));	
		final Instant FINAL_TIME = Instant.now().plus(Duration.ofDays(2));
		final Activity NEW_ACTIVITY = new Activity(RELATED_TASK, INITIAL_TIME, FINAL_TIME);
		
		// Execution
		Collaborator current = new Collaborator("Juan");
		current.addAnActivityManually(NEW_ACTIVITY);
		
		// Validation
		assertNotNull(current);
		assertNull(current.getCurrentActivity());
	}
	
	@Test
	void toEndAnActivity() {
		// Preparation
		final Task RELATED_TASK = new Task();	
		
		// Execution
		Collaborator current = new Collaborator("Juan");
		current.startANewActivity(RELATED_TASK);
		current.endActivity();
		
		// Validation
		assertNotNull(current);
		assertNotNull(current.getCurrentActivity().getFinalTime());
	}
}
