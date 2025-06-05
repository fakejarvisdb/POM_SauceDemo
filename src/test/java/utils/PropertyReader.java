package utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class PropertyReader {
	
	public static Properties readProperties() {
		
		FileInputStream fis;
		Properties prop = null;
		String fileName= "src\\test\\resources\\config\\config.properties";
		try {
			fis = new FileInputStream(fileName);
			prop = new Properties();
			prop.load(fis);
		
		} catch (FileNotFoundException e) {
			System.out.println("The file path or file name is not correct, please check");
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return prop;	
	}
}