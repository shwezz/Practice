package com.qait.automation.utils;

/**
 *
 */


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Properties;

/**
 * @author Administrator
 *
 */
public class ResourceLoader {

	 private ResourceLoader() {
	    }

	 /**
     *
     * @param resourceName
     * @return
     * @throws IOException
     */
    public static URL getResourceUrl(String resourceName) throws IOException {
        ClassLoader classLoader = ResourceLoader.class.getClassLoader();

        URL resourceUrl = null;

        if (classLoader != null) {
        	resourceUrl = classLoader.getResource(resourceName);
        }

        if (resourceUrl == null) {
            classLoader = ClassLoader.getSystemClassLoader();
            if (classLoader != null) {
            	resourceUrl = classLoader.getResource(resourceName);
            }
        }

        return resourceUrl;
    }//end loadResource

	    /**
	     *
	     * @param resourceName
	     * @return
	     * @throws IOException
	     */
	    public static InputStream loadResource(String resourceName) throws IOException {
	        ClassLoader classLoader = ResourceLoader.class.getClassLoader();

	        InputStream inputStream = null;

	        if (classLoader != null) {
	            inputStream = classLoader.getResourceAsStream(resourceName);
	        }

	        if (inputStream == null) {
	            classLoader = ClassLoader.getSystemClassLoader();
	            if (classLoader != null) {
	                inputStream = classLoader.getResourceAsStream(resourceName);
	            }
	        }

	        if (inputStream == null) {
	            File file = new File(resourceName);
	            if (file.exists()) {
	                inputStream = new FileInputStream(file);
	            }
	        }

	        return inputStream;
	    }//end loadResource

	    /**
	     *
	     * @param resourceName
	     * @return
	     * @throws IOException
	     */
	    public static Properties loadProperties(String resourceName) throws IOException {
			Properties result = null;
	    	File f = new File(resourceName);
	    	if(f.exists()){
				result = _loadProperties(new FileInputStream(f));
			} else {
				result = _loadProperties(loadResource(resourceName));
			}

	    	return result;
	    }


	public static Properties _loadProperties(InputStream inputStream) throws IOException {
		Properties properties = null;

		try {
			properties = new Properties();
			properties.load(inputStream);
		} finally {
			if (inputStream != null) {
				inputStream.close();
			}
		}
		return properties;
	}

}
