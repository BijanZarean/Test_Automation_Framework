package utilities;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class DataReader {
private static Properties property;
	
	static {
		try {
			//Loads the "env.properties" file path into File object
			File file = new File("./src/test/resources/test_data/env.properties");
			//Passing File object into FileInputStream object called "input"
			//FileInputStream parses all the data in the file into Key and Value format (EX: browser=chrome)
			FileInputStream input = new FileInputStream(file);
			//property object from Java.Utils Properties class
			//Allows us to read from the FileInputStream
			property = new Properties();
			//Loading the properties file
			property.load(input);
			input.close();
		} catch (Exception e) {
			e.printStackTrace();
			
		}
	}
	
	//This function will read the key of the specified value when called
	public static String getProperty(String key) {
		return property.getProperty(key);
	}
}
