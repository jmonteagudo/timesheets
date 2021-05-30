package ar.com.jmonteagudo.timesheets.util;

import static org.junit.jupiter.api.Assertions.*;

import java.time.Duration;
import java.time.Instant;
import java.time.ZonedDateTime;

import org.junit.jupiter.api.Test;

class TestDate {

	@Test
	void toCompareTheSameDayAtTwoInstanteOfTime() {
		Instant first = Instant.now();
		Instant second = Instant.now().plus(Duration.ofHours(2));
		
		assertTrue(Date.sameDate(first, second));
	}

	@Test
	void toCompareDifferentsDaysAtTwoInstanteOfTime() {
		Instant first = Instant.now();
		Instant second = Instant.now().plus(Duration.ofDays(2));
		
		assertFalse(Date.sameDate(first, second));
	}
	
	@Test
	void toCompareTheSameDayAtAnInstantAgainstAZonedDateTime() {
		Instant first = Instant.now();
		ZonedDateTime second = ZonedDateTime.now();
		
		
		assertTrue(Date.sameDate(first, second));
	}

	@Test
	void toCompareDifferentsDaysAtAnInstantAgainstAZonedDateTime() {
		Instant first = Instant.now().plus(Duration.ofDays(2));
		ZonedDateTime second = ZonedDateTime.now();
		
		assertFalse(Date.sameDate(first, second));
	}
}
