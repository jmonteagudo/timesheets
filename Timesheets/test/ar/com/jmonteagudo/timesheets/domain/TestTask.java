package ar.com.jmonteagudo.timesheets.domain;

import org.junit.jupiter.api.Test;

import static org.junit.Assert.*;

class TestTask {
	
	@Test
	void toBeAbleToCreateATaskWithoutParameters() {
		// Preparation
		final State expectedState = State.PROPOSAL;
		
		// Execution
		Task newOne = new Task();
		
		// Validation
		assertNotNull(newOne);
		assertEquals(expectedState, newOne.getCurrentState());
	}

	@Test
	void toBeAbleToCreateATaskWithParameters() {
		// Preparation
		final Integer ID = 178342;
		final String DESCRIPTION = "Automatización Interfaz 54000";
		final State expectedState = State.PROPOSAL;
		
		// Execution
		Task newOne = new Task(ID, DESCRIPTION);
		
		// Validation
		assertNotNull(newOne);
		assertEquals(ID, newOne.getId());
		assertEquals(DESCRIPTION, newOne.getDescription());
		assertEquals(expectedState, newOne.getCurrentState());
	}
	
	@Test
	void toCompareTwoEqualsTasks() {
		// Preparation
		final Integer ID = 178342;
		final String DESCRIPTION = "Automatización Interfaz 54000";
		
		// Execution
		Task newOne = new Task(ID, DESCRIPTION);
		Task anotherOne = new Task(ID, DESCRIPTION);
		
		// Validation
		assertEquals(newOne, anotherOne);
	}
	
	
	@Test
	void toCompareTwoDifferentsTasks() {
		// Preparation
		final Integer ID_1 = 178342;
		final Integer ID_2 = 178343;
		final String DESCRIPTION = "Automatización Interfaz 54000";
		
		// Execution
		Task newOne = new Task(ID_1, DESCRIPTION);
		Task anotherOne = new Task(ID_2, DESCRIPTION);
		
		// Validation
		assertNotEquals(newOne, anotherOne);
	}

}
