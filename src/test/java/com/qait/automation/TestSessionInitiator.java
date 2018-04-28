package com.qait.automation;

import static com.qait.automation.utils.ConfigPropertyReader.getProperty;

import com.qait.LegCompass.keywords.HomePageActions;

import com.qait.automation.utils.TakeScreenshot;
import com.qait.automation.utils.YamlReader;

import static com.qait.automation.utils.YamlReader.getYamlValue;
import static com.qait.automation.utils.YamlReader.setYamlFilePath;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Reporter;

import java.util.*;

import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.Logs;
import org.openqa.selenium.logging.LogEntry;

public class TestSessionInitiator {

	protected String testName = null;
	protected WebDriver driver;
	private final WebDriverFactory wdfactory;
	String browser;
	String seleniumserver;
	String seleniumserverhost;
	String appbaseurl;
	String applicationpath;
	String chromedriverpath;
	String datafileloc = "";
	static int timeout;
	Map<String, Object> chromeOptions = null;
	DesiredCapabilities capabilities;

	/**
	 * Initiating the page objects
	 * 
	 */

	public TakeScreenshot takescreenshot;
	public HomePageActions home;

	public WebDriver getDriver() {
		return this.driver;
	}

	private void _initPage() {

		home = new HomePageActions(driver);

	}

	/**
	 * Page object Initiation done
	 * 
	 */
	public TestSessionInitiator(String testname) {
		this.testName = testname;
		wdfactory = new WebDriverFactory();
		testInitiator(testname);
	}

	private void testInitiator(String testname) {
		setYamlFilePath();
		_configureBrowser();
		_initPage();
		takescreenshot = new TakeScreenshot(testname, this.driver);
	}

	private void _configureBrowser() {
		driver = wdfactory.getDriver(_getSessionConfig());
		// System.out.println(getProperty("browser")+"\t"+
		// getProperty("platform"));
		if (!getProperty("platform").equalsIgnoreCase("mobile")) {
			driver.manage().window().maximize();

		}
		driver.manage().timeouts().implicitlyWait(Integer.parseInt(getProperty("timeout")), TimeUnit.SECONDS);
		
	}

	private Map<String, String> _getSessionConfig() {
		String[] configKeys = { "tier", "browser", "seleniumserver", "seleniumserverhost", "timeout", "driverpath",
				"mobileDevice", "appiumServer", "appType", "platform" };
		Map<String, String> config = new HashMap<String, String>();
		for (String string : configKeys) {
			config.put(string, getProperty(string));
		}
		return config;
	}

	public void launchApplication() {
		launchApplication(getYamlValue("base_url"));
	}

	// ___________________________________________________________________________

	/**
	 * 
	 * Test Session Initiator for launching CHROMEDRIVER from a MOBILE Test
	 * Class
	 */
	public TestSessionInitiator(String simpleName, Map<String, String> _getSessionConfig) {
		wdfactory = new WebDriverFactory();
		testInitiator(simpleName, _getSessionConfig);
	}

	private void testInitiator(String simpleName, Map<String, String> _getSessionConfig) {
		setYamlFilePath();
		_configureBrowser(simpleName, _getSessionConfig);
		_initPage();
		takescreenshot = new TakeScreenshot(simpleName, this.driver);
	}

	private Map<String, String> _configureBrowser(String simpleName, Map<String, String> _getSessionConfig) {
		driver = wdfactory.getDriver(_getSessionConfig);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Integer.parseInt(getProperty("timeout")), TimeUnit.SECONDS);
		return _getSessionConfig;

	}

	// ___________________________________________________________________________

	protected String setBaseUrl() {
		String str = "";
		str = YamlReader.getData("base_url");
		str = System.getProperty("baseurl", str);// base url from command
													// line/CI
		return str;
	}

	// Launch Politico Front end (core)
	public void launchPoliticoFrontEnd() {
		// launchApplication("https://" + setBaseUrl());
		launchApplication(setBaseUrl());

	}

	// Launch Politico Pro
	public void launchPoliticoProUrl() {
		launchApplication("https://pro." + setBaseUrl());
	}

	// Launch CMS url
	public void launchPoliticoCMS() {
		if (setBaseUrl().contains("west")) {
			launchApplication("https://cms-west.ops.politico.com/cms");
		} else {
			launchApplication("https://" + setBaseUrl() + "cms");
		}
	}

	// get CMS URL
	public String getPoliticoCMSUrl() {
		if (setBaseUrl().contains("west")) {
			return "https://cms-west.ops.politico.com/cms";
		} else {
			return "https://" + setBaseUrl() + "cms";
		}
	}

	public void launchApplication(String loginUrl) {
		String launchMessage = String.format(
				"\n***** STARTING TEST: %s *****\n\nLaunching browser /%s/ against starting URL: %s",
				testName.toUpperCase(), _getSessionConfig().get("browser"), loginUrl);

		Reporter.log(launchMessage, true);
		driver.manage().deleteAllCookies();
		driver.get(loginUrl);
	}

	public void openUrl(String url) {
		driver.get(url);
		Reporter.log("Launched URL: " + url);

	}

	public void closeCurrentWindowopenUrl(String url) {
		driver.get(url);
	}

	public void closeBrowserSession() {
		Reporter.log("\n", true);
		driver.quit();
	}

	public void stepStartMessage(String testStepName) {
		Reporter.log(" ", true);
		Reporter.log("***** STARTING TEST STEP: " + testStepName.toUpperCase() + " *****", true);
		Reporter.log(" ", true);
	}

	public void closeTestSession() {
		closeBrowserSession();
	}
	/*
	 * public void closeBrowserSession() throws IOException { driver.quit();
	 * driver.close(); String os = System.getProperty("os.name"); if
	 * (os.contains("Windows")) {
	 * Runtime.getRuntime().exec("taskkill /F /IM chrome.exe");
	 * Runtime.getRuntime().exec("taskkill /F /IM iexplorer.exe");
	 * Runtime.getRuntime().exec("taskkill /F /IM firefox.exe");
	 * Runtime.getRuntime().exec("taskkill /F /IM safari.exe");
	 * Runtime.getRuntime().exec("taskkill /F /IM opera.exe"); } else { //
	 * Assuming a non Windows OS will be some version of Unix, Linux, or Mac
	 * Runtime.getRuntime()
	 * .exec("kill `ps -ef | grep -i firefox | grep -v grep | awk '{print $2}'`"
	 * ); Runtime.getRuntime()
	 * .exec("kill `ps -ef | grep -i chrome | grep -v grep | awk '{print $2}'`"
	 * ); Runtime.getRuntime()
	 * .exec("kill `ps -ef | grep -i safari | grep -v grep | awk '{print $2}'`"
	 * ); } }
	 */

	public void getCookies() {
		Set<Cookie> allcookies = driver.manage().getCookies();
		System.out.println(allcookies);

	}

	public void deleteCookies(String cookieName) {
		driver.manage().deleteCookieNamed(cookieName);
		Reporter.log("");
	}

	public void checkLogs() {
		Logs logs = driver.manage().logs();
		LogEntries logEntries = logs.get(LogType.BROWSER);

	}

}
