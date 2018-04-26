package com.qait.politico.LegCompass.tests;

import com.qait.automation.TestSessionInitiator;
import com.qait.automation.utils.YamlReader;

import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import java.lang.reflect.Method;

public abstract class BaseProTest {
	protected TestSessionInitiator test;

	protected abstract String getLaunchUrl();

	@BeforeClass(groups = { "smoke", "mobile" })

	protected void Start_Test_Session() {
		test = new TestSessionInitiator(this.getClass().getSimpleName());
		launchApplication(getLaunchUrl());
	}

	protected void launchApplication(String launchUrl) {
		test.launchApplication(launchUrl);
	}

	@BeforeMethod(groups = { "smoke", "mobile" })

	protected void handleTestMethodName(Method method) {
		test.stepStartMessage(method.getName());
	}

	@AfterMethod(groups = { "smoke", "mobile" })

	protected void takeScreenshotonFailure(ITestResult result) {
		test.takescreenshot.takeScreenShotOnException(result);
	}

	@AfterClass(groups = { "smoke", "mobile" })
	protected void stop_test_session() {
//		 test.closeTestSession();
	}

	protected void AssertTrue(boolean b, String logPassMessage, String logFailmessage) {
		Assert.assertTrue(b, "[ASSERT FAILED]: " + logFailmessage);
		Reporter.log("[ASSERT PASSED]: " + logPassMessage);
	}

	/**
	 * Returns a configuration value by searching in these locations (first one
	 * wins): - System property - YAML configuration file for tier
	 */
	protected String getConfigValue(String name) {
		String systemValue = System.getProperty(name);
		if (systemValue != null && systemValue.trim().length() > 0) {
			return systemValue;
		}
		System.out.println("#########");
		System.out.println(YamlReader.getYamlValue(name));
		return YamlReader.getYamlValue(name);
	}

	/**
	 * Returns first non-empty configuration value for any of the names (first
	 * one wins). For each name, we search System properties first, and then the
	 * YAML configuration file for the tier.
	 */
	protected String getFirstConfigValueFound(String... names) {
		for (String name : names) {
			String value = getConfigValue(name);
			if (value != null) {
				return value;
			}
		}
		return null;
	}

}
