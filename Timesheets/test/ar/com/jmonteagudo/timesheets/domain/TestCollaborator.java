package ar.com.jmonteagudo.timesheets.domain;

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
	
	@Test
	void toGetTheTimePerTask() {
		// Preparation
		final Integer OLD_ID = 178342;
		final String OLD_DESCRIPTION = "Automatización Interfaz 54000";
		final Task OLD_TASK = new Task(OLD_ID, OLD_DESCRIPTION);
		final Long OLD_TASK_EXPECTED_TIME = 6L;
		final Integer CURRENT_ID = 185432;
		final String CURRENT_DESCRIPTION = "Cambios en el backoffice";
		final Task CURRENT_TASK = new Task(CURRENT_ID, CURRENT_DESCRIPTION);
		final Long CURRENT_TASK_EXPECTED_TIME = 3L;
		final Integer NEXT_ID = 185412;
		final String NEXT_DESCRIPTION = "Testing";
		final Task NEXT_TASK = new Task(NEXT_ID, NEXT_DESCRIPTION);
		final Long NEXT_TASK_EXPECTED_TIME = 2L;
		final String NAME = "Juan";
		
		// Execution
		Collaborator newOne = new Collaborator(NAME);
		newOne.addAnActivityManually(new Activity(OLD_TASK, Instant.now().plus(Duration.ofHours(-6)), Instant.now()));
		newOne.addAnActivityManually(new Activity(CURRENT_TASK, Instant.now(), Instant.now().plus(Duration.ofHours(3))));
		newOne.addAnActivityManually(new Activity(NEXT_TASK,  Instant.now().plus(Duration.ofHours(3)), Instant.now().plus(Duration.ofHours(5))));
		
		// Validation
		assertEquals(OLD_TASK_EXPECTED_TIME, newOne.getTimePerTask(OLD_TASK));
		assertEquals(CURRENT_TASK_EXPECTED_TIME, newOne.getTimePerTask(CURRENT_TASK));
		assertEquals(NEXT_TASK_EXPECTED_TIME, newOne.getTimePerTask(NEXT_TASK));
	}
}
