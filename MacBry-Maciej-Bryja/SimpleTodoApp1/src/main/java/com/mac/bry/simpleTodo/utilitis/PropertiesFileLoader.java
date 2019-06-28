package com.mac.bry.simpleTodo.utilitis;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesFileLoader {

	public static Properties loadPropertiesFile(String propertiesFileName) {
		Properties properties = new Properties();
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		InputStream stream = classLoader.getResourceAsStream(propertiesFileName);
		try {
			properties.load(stream);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return properties;
	}
}
