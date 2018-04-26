/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.qait.automation.utils;

import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;

import org.yaml.snakeyaml.Yaml;

import static com.qait.automation.utils.ConfigPropertyReader.getProperty;

import org.testng.Reporter;

@SuppressWarnings("unchecked")
public class YamlReader {
	
	public static String tier, browser, appType, platform;
	public static String yamlFilePath = null;

	public static String setYamlFilePath() {
		
		tier = getProperty("Config.properties", "tier").trim();
        tier = System.getProperty("tier",tier);
        browser = getProperty("Config.properties", "browser").trim();
        appType = getProperty("Config.properties", "appType").trim();
        platform = getProperty("Config.properties", "platform").trim();
        System.out.println("YAML DATA "+browser + "\t"+ platform +"\t" +appType );
        
        if(platform.equalsIgnoreCase("mobile")/*&&appType.equalsIgnoreCase("")*/){
        	Reporter.log("Test running on "+browser.toUpperCase()+" and on "+appType.toUpperCase()+"app");
        	yamlFilePath = "/testdata/Mobile_TestData.yml";
        }
        else{
			Reporter.log("Test running on " + tier + " environment");
			if (tier.equalsIgnoreCase("local")) {
				yamlFilePath = "/testdata/Local_TestData.yml";
			} else if (tier.equalsIgnoreCase("stg")) {
				yamlFilePath = "/testdata/STG_TestData.yml";
			} else if (tier.equalsIgnoreCase("qaorange")) {
				yamlFilePath = "/testdata/QAOrange_TestData.yml";
			} else if (tier.equalsIgnoreCase("westcoast")) {
				yamlFilePath = "/testdata/WestCoast_TestData.yml";
			} else if (tier.equalsIgnoreCase("qared")) {
				yamlFilePath = "/testdata/QaRed_TestData.yml";
			} else if (tier.equalsIgnoreCase("qablue")) {
				yamlFilePath = "/testdata/QABlue_TestData.yml";
			} else if (tier.equalsIgnoreCase("prod")
					|| tier.equalsIgnoreCase("production")) {
				yamlFilePath = "/testdata/PROD_TestData.yml";}
				else if (tier.equalsIgnoreCase("stage")
						|| tier.equalsIgnoreCase("stagewhite")) {
					yamlFilePath = "/testdata/StageWhite_TestData.yml";
				
			} else {
				Reporter.log(
						"YOU HAVE PROVIDED WRONG TIER IN CONFIG!!! using dev test data",
						true);
			}
		}
		return yamlFilePath;
	}
	
//	public static String setYamlFilePath( Map<String, String> _getSessionConfig ) {
//        System.out.println("YAML DATA "+
//        				_getSessionConfig.get("browser") + "\t"+
//        				_getSessionConfig.get("platform") +"\t"+
//        				_getSessionConfig.get("appType") );
//        
//        if (_getSessionConfig.get("tier").equalsIgnoreCase("qared")){
//        	yamlFilePath = "/testdata/PROD_TestData.yml";
//        }
//        return yamlFilePath;
//	}
//	
//	
//	
//	
	

	public static String getYamlValue(String token) {
		try {
			return getValue(token);
		} catch (FileNotFoundException ex) {
			System.out.println(ex.getMessage());
			return null;
		}
	}

	public static String getData(String token) {
		return getYamlValue(token);
	}

	public static Map<String, Object> getYamlValues(String token) {
		Reader doc = new InputStreamReader(Object.class.getResourceAsStream(yamlFilePath));
		Yaml yaml = new Yaml();
		// TODO: check the type casting of object into the Map and create
		// instance in one place
		Map<String, Object> object = (Map<String, Object>) yaml.load(doc);
		return parseMap(object, token + ".");
	}

	private static String getValue(String token) throws FileNotFoundException {
        Reader doc = new InputStreamReader(Object.class.getResourceAsStream(yamlFilePath));
		Yaml yaml = new Yaml();
		Map<String, Object> object = (Map<String, Object>) yaml.load(doc);
		return getMapValue(object, token);
	}

	public static String getMapValue(Map<String, Object> object, String token) {
		// TODO: check for proper yaml token string based on presence of '.'
		String[] st = token.split("\\.");
		Object result = parseMap(object, token).get(st[st.length - 1]);
		return result == null ? null : result.toString();
	}

	private static Map<String, Object> parseMap(Map<String, Object> object,
			String token) {
		if (token.contains(".")) {
			String[] st = token.split("\\.");
			object = parseMap((Map<String, Object>) object.get(st[0]),
					token.replace(st[0] + ".", ""));
		}
		return object;
	}

	public static int generateUniqueRandomNumber(int minRange, int maxRange) {

		ArrayList<Integer> list = new ArrayList<Integer>();
		for (int i = minRange; i < maxRange; i++) {
			list.add(new Integer(i));
		}
		Collections.shuffle(list);
		for (int i = 0; i < 9; i++) {
			System.out.println(list.get(i));
			return list.get(i);
		}
		return 0;
	}

	public static int generateRandomNumber(int MinRange, int MaxRange) {
		int randomNumber = MinRange
				+ (int) (Math.random() * ((MaxRange - MinRange) + 1));
		return randomNumber;
	}
	
	// Gives a List of Strings 
	public static ArrayList<String> getYamlList(String token) {
		  String[] dataList = (getData(token)).split(" ");
		  ArrayList<String> list = new ArrayList<String>();
		  for(String element : dataList){
		   list.add(element);
		  }
		  return list;
		 }

}




