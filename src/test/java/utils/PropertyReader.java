package utils;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class PropertyReader {

	private Properties properties;
	public PropertyReader(String propertyFilePath) {
		FileReader reader;
		try {
			reader = new FileReader(propertyFilePath);
			Properties property=new Properties();
			property.load(reader);
		} catch (FileNotFoundException e) {
		System.out.println(e.getMessage());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
		
		
		
	}
	
	public Properties getProperties() {
		return properties;
		
	}
	
}
