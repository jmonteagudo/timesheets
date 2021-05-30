package ar.com.jmonteagudo.timesheets.util;

import java.util.Properties;

public class Configuration {
	
	public final static Properties properties;
	
	static{
		properties = new Properties();
		properties.setProperty("zone", "America/Argentina/Buenos_Aires");
		properties.setProperty("fileName", "C:\\tmp\\timesheets.xls");
		properties.setProperty("dateFormat", "yyyymmdd");
	}

}
