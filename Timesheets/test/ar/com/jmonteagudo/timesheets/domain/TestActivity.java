package ar.com.jmonteagudo.timesheets.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.time.Duration;
import java.time.Instant;

import org.junit.jupiter.api.Test;

class TestActivity {

	@Test
	void toBeAbleToCreateAnActivityWithOneParameter() {
		// Preparation
		final Task RELATED_TASK = new Task();
		final Instant INITIAL_TIME = Instant.now();	
		
		// Execution
		Activity newOne = new Activity(RELATED_TASK);
		
		// Validation
		assertNotNull(newOne);
		assertEquals(RELATED_TASK, newOne.getRelatedTask());
		assertEquals(INITIAL_TIME, newOne.getInitialTime());
		assertNull(newOne.getFinalTime());
	}

	@Test
	void toBeAbleToCreateAnActivityWithMoreParameters() {
		// Preparation
		final Task RELATED_TASK = new Task();
		final Instant INITIAL_TIME = Instant.now();	
		final Instant FINAL_TIME = Instant.now().plus(Duration.ofDays(1));	
		
		// Execution
		Activity newOne = new Activity(RELATED_TASK, INITIAL_TIME, FINAL_TIME);
		
		// Validation
		assertNotNull(newOne);
		assertEquals(RELATED_TASK, newOne.getRelatedTask());
		assertEquals(INITIAL_TIME, newOne.getInitialTime());
		assertEquals(FINAL_TIME, newOne.getFinalTime());
	}
	
	@Test
	void toCompareTwoEqualsActivities() {
		// Preparation
		final Task RELATED_TASK_1 = new Task();
		final Task RELATED_TASK_2 = new Task();
		final Instant INITIAL_TIME = Instant.now();	
		final Instant FINAL_TIME_1 = Instant.now().plus(Duration.ofDays(1));	
		final Instant FINAL_TIME_2 = Instant.now().plus(Duration.ofDays(2));	
		
		// Execution
		Activity firstOne = new Activity(RELATED_TASK_1, INITIAL_TIME, FINAL_TIME_1);
		Activity secondOne = new Activity(RELATED_TASK_2, INITIAL_TIME, FINAL_TIME_2);
		
		// Validation
		assertNotNull(firstOne);
		assertNotNull(secondOne);
		assertEquals(RELATED_TASK_1, firstOne.getRelatedTask());
		assertEquals(INITIAL_TIME, firstOne.getInitialTime());
		assertEquals(FINAL_TIME_1, firstOne.getFinalTime());
		assertEquals(firstOne, secondOne);
	}
	
	
	@Test
	void toCompareTwoDifferentsActivities() {
		// Preparation
		final Task RELATED_TASK = new Task();
		final Instant INITIAL_TIME_1 = Instant.now();	
		final Instant INITIAL_TIME_2 = Instant.now().plus(Duration.ofDays(1));	
		final Instant FINAL_TIME = Instant.now().plus(Duration.ofDays(2));	
		
		// Execution
		Activity firstOne = new Activity(RELATED_TASK, INITIAL_TIME_1, FINAL_TIME);
		Activity secondOne = new Activity(RELATED_TASK, INITIAL_TIME_2, FINAL_TIME);
		
		// Validation
		assertNotNull(firstOne);
		assertNotNull(secondOne);
		assertEquals(RELATED_TASK, firstOne.getRelatedTask());
		assertEquals(INITIAL_TIME_1, firstOne.getInitialTime());
		assertEquals(FINAL_TIME, firstOne.getFinalTime());
		assertNotEquals(firstOne, secondOne);
	}
	
	@Test
	void toEvaluateIfAnActivityIsInTheFuture() {
		// Preparation
		final Task RELATED_TASK = new Task();
		final Instant INITIAL_TIME = Instant.now().plus(Duration.ofDays(1));	
		final Instant FINAL_TIME = Instant.now().plus(Duration.ofDays(2));	
		
		// Execution
		Activity futureOne = new Activity(RELATED_TASK, INITIAL_TIME, FINAL_TIME);
		
		// Validation
		assertNotNull(futureOne);
		assertTrue(futureOne.isInTheFuture());
	}

}
