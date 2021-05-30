package ar.com.jmonteagudo.timesheets.util;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class Date {
	
	public static Boolean sameDate(Instant firstDate, Instant secondDate) {
		return firstDate.atZone(ZoneId.of(Configuration.properties.getProperty("zone"))).format(DateTimeFormatter.ofPattern(Configuration.properties.getProperty("dateFormat"))).equals(secondDate.atZone(ZoneId.of(Configuration.properties.getProperty("zone"))).format(DateTimeFormatter.ofPattern(Configuration.properties.getProperty("dateFormat"))));
	}
	
	public static Boolean sameDate(Instant firstDate, ZonedDateTime secondDate) {
		return firstDate.atZone(ZoneId.of(Configuration.properties.getProperty("zone"))).format(DateTimeFormatter.ofPattern(Configuration.properties.getProperty("dateFormat"))).equals(secondDate.format(DateTimeFormatter.ofPattern(Configuration.properties.getProperty("dateFormat"))));
	}

}
