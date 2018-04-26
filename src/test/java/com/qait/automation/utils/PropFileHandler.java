package com.qait.automation.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

public class PropFileHandler {

	public PropFileHandler() {
		// TODO Auto-generated constructor stub
	}
	
	
	static String filePath = "./src/test/resources/testdata/Data.properties";
	
	 static Properties properties = new Properties();

	    /**
	     * This method is used to read the value of the given property from the
	     * properties file.
	     *
	     * @param property : the property whose value is to be fetched.
	     * @return the value of the given property.
	     * @throws IOException 
	     */
	    public static String readProperty(String property) {
	        InputStream inPropFile = null;
	        try {
	            inPropFile = new FileInputStream(new File(filePath));
	            properties.load(inPropFile);
	        } catch (IOException e) {
	        	System.out.println("There was Exception reading the Test data");
	        }
	        String value = properties.getProperty(property);
	        return value;
	    }

	    
	    public static String readPropertyFromFile(String property) 
		{
			InputStream inPropFile = null;
			try {
				
				inPropFile = new FileInputStream(filePath);
				properties.load(inPropFile);
			} catch (IOException e) {
			}
			String value=properties.getProperty(property);	
			
			return value;
		}
	    
	    public static String readHartfordProperty(String property) {
	        InputStream inPropFile = null;
	        try {
	            inPropFile = new FileInputStream("data/hartford.properties");
	            properties.load(inPropFile);

	        } catch (IOException e) {
	        }
	        String value = properties.getProperty(property);

	        return value;
	    }
	    /**
	     * This method is used to store a new property in the properties file.
	     *
	     * @param property : name for the new property.
	     * @param value : value for the new property.
	     * @throws IOException
	     */
	    public static void writeProperty(String property, String value) {
	        // Write properties file.
	        //OutputStream outPropFile = null;

	        try {
	           // InputStream inPropFile = new FileInputStream(System.getProperty("src/test/resources/testdata/Data.properties"));
	            InputStream inPropFile = new FileInputStream(filePath);
	            properties.load(inPropFile);
	            inPropFile.close();
	            OutputStream outPropFile = new FileOutputStream(System.getProperty("user.dir")+"/src/test/resources/testdata/Data.properties");
	            //System.getProperty("user.dir")+"\\AnswerKeys\\"+top+".properties"
	            
	            properties.setProperty(property, value);
	            properties.store(outPropFile, null);
	            outPropFile.close();
	        } catch (IOException e) {
	        }
	    }
	    
	    public static void writeToFile(String property, String value)  {
			try {
				InputStream inPropFile = new FileInputStream(filePath);
				properties.load(inPropFile);
				inPropFile.close();
				OutputStream outPropFile = new FileOutputStream(filePath);
				properties.setProperty(property, value);
				properties.store(outPropFile, null);
				outPropFile.close();
			} catch (IOException e) {
				System.out.println("Unable to write value in property file");
				e.printStackTrace();
			}
			}

	    public static void clearProperty() {
	        try {
	            InputStream inPropFile = new FileInputStream(System.getProperty("user.dir")+"/src/test/resources/testdata/Data.properties");
	            properties.load(inPropFile);
	            inPropFile.close();
	            OutputStream outPropFile = new FileOutputStream(System.getProperty("user.dir")+"/src/test/resources/testdata/Data.properties");
	            properties.clear();
	            outPropFile.close();
	        } catch (IOException e) {
	        }
	    }
	    public static void clearProperty(String property) {
	        try {
	            InputStream inPropFile = new FileInputStream(System.getProperty("user.dir")+"/src/test/resources/testdata/Data.properties");
	            properties.load(inPropFile);
	            inPropFile.close();
	            OutputStream outPropFile = new FileOutputStream(System.getProperty("user.dir")+"/src/test/resources/testdata/Data.properties");
	            //properties.clear();
	            properties.remove(property);
	            properties.store(outPropFile, null);
	            outPropFile.close();
	        } catch (IOException e) {
	        }
	    }
	    public static void writeDataInReportsData(String property, String str){
	    	 try {
		            InputStream inPropFile = new FileInputStream(System.getProperty("user.dir")+"/ReportsData/Data.properties");
		            properties.load(inPropFile);
		            inPropFile.close();
		            OutputStream outPropFile = new FileOutputStream(System.getProperty("user.dir")+"/ReportsData/Data.properties");
		            //System.getProperty("user.dir")+"\\AnswerKeys\\"+top+".properties"
		            
		            properties.setProperty(property, str);
		            properties.store(outPropFile, null);
		            outPropFile.close();
		        } catch (IOException e) {
		        }
	    }
	    
	    public static String readReportsData(String property) {
	        InputStream inPropFile = null;
	        try {
	            inPropFile = new FileInputStream(System.getProperty("user.dir")+"\\ReportsData\\Data.properties");
	                        
	            properties.load(inPropFile);

	        } catch (IOException e) {
	        	System.out.println("There was Exception reading the Test data");
	        }
	        String value = properties.getProperty(property);
	        return value;
	    }
	    
	    public static void clearPropertyFromReports() {
	        try {
	            InputStream inPropFile = new FileInputStream(System.getProperty("user.dir")+"\\ReportsData\\Data.properties");
	            properties.load(inPropFile);
	            inPropFile.close();
	            OutputStream outPropFile = new FileOutputStream(System.getProperty("user.dir")+"\\ReportsData\\Data.properties");
	            properties.clear();
	            outPropFile.close();
	        } catch (IOException e) {
	        }
	    }


}
