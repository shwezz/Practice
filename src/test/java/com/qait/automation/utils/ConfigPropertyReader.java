package com.qait.automation.utils;

import org.testng.Reporter;

import java.io.File;
import java.util.Properties;

/**
 * This is the utility class for data read write
 *
 * @author QAIT
 *
 */
public class ConfigPropertyReader {

    private static String LOCAL_PROPERTIES_FILENAME = "config-local.properties";

    protected static Properties localProps = null;
    protected static Properties props = null;


    /**
     * construtor of this class
     */
    public ConfigPropertyReader() {
    }

    /**
     *
     * This method will get the properties pulled from a file located relative to the base dir
     *
     * @param propFile complete or relative (to base dir) file location of the properties file
     * @param Property property name for which value has to be fetched
     * @return String value of the property
     */
    public static String getProperty(String propFile, String property) {
        loadProperties(propFile);

        if (localProps != null)
        {
            String value = localProps.getProperty(property);
            if (value != null)
            {
                return value;
            }
        }

        return props.getProperty(property);
    }
    
    public static String getProperty(String property){
        return getProperty(getDefaultConfigFile(), property);
    }

    public static String getDefaultConfigFile(){
        String result = System.getProperty("config");
        if( result == null ){
            result = "Config.properties";
        }
        return result;
    }

    public static void loadProperties(String propFile)
    {
        if ( props != null )
        {
            return;
        }

        try {
            props = ResourceLoader.loadProperties(propFile);

        } catch (Exception ex) {
            ex.printStackTrace();
            return;
        }

        // Allow a local properties file to override.
        File localPropsFile = new File(LOCAL_PROPERTIES_FILENAME);

        if ( localPropsFile.exists())
        {
            try
            {
                localProps = ResourceLoader.loadProperties(localPropsFile.getAbsolutePath());
            } catch (Exception ex)
            {
                ex.printStackTrace();
                return;
            }
        }


    }

}
