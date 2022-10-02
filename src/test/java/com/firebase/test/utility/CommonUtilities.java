package com.firebase.test.utility;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
//import java.util.*;
//import org.bouncycastle.util.Properties;
import java.util.Properties;
import com.firebase.test.utility.*;

public class CommonUtilities {

	public FileInputStream stream=null;
	public Properties loadFile(String filename){
		Properties propertyFile=new Properties();
		String PropertyFilePath=null;
		switch(filename) {
		case"Data":
			PropertyFilePath=Constants.APPLICATION_PROPERTIES_PATH;
			System.out.println("PropertyFilePath is set to be :"+PropertyFilePath);
			break;
		}
		
			try {
				stream=new FileInputStream(PropertyFilePath);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				propertyFile.load(stream);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		
		return propertyFile;
		
	}
public String getApplicationProperty(String Key,Properties propertyFile) {
	String value=propertyFile.getProperty(Key);
	System.out.println("Property we got from the fileis :: "+value);
		try {
			stream.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return value;
		

}
 public HashMap getAllPropertiesAsSet(Properties propertyFile) {
	 return new HashMap(propertyFile);
 }
 }
	


