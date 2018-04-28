/*
 * To change this template, choose Tools | Templates

 * and open the template in the editor.
 */
package com.qait.automation;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.remote.CapabilityType;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.LocalFileDetector;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.Reporter;

public class WebDriverFactory {

	private static String browser, appType, platform;
	private static final DesiredCapabilities capabilities = new DesiredCapabilities();
	public static String filePath = System.getProperty("user.dir") + File.separator + "target" + File.separator
			+ "DataPointGraphicsDownloads";
	public static String logGeckoPath = System.getProperty("user.dir") + File.separator + "target" + File.separator
			+ "geckodriverlog" + "log.txt";

	public WebDriver getDriver(Map<String, String> seleniumconfig) {
		platform = seleniumconfig.get("platform");
		browser = seleniumconfig.get("browser");
		Reporter.log("Platform - " + platform + "\n" + "Browser - " + browser);

		if (platform.equalsIgnoreCase("mobile")) {
			appType = seleniumconfig.get("appType");
			if (appType.equals("web")) {
				return setMobileDriver(seleniumconfig);
			} else if (appType.equals("native") || appType.equals("hybrid")) {
				return setAndroidDriver(seleniumconfig);
			}
		} else {
			Reporter.log("Test running on " + browser + " browser");
			if (seleniumconfig.get("seleniumserver").equalsIgnoreCase("local")) {
				if (browser.equalsIgnoreCase("firefox")) {
					return getFirefoxDriver(seleniumconfig.get("driverpath"));
				} else if (browser.equalsIgnoreCase("phantomjs")) {
					// return getPhantomjsDriver();
				} else if (browser.equalsIgnoreCase("chrome")) {
					return getChromeDriver(seleniumconfig.get("driverpath"));
				} else if (browser.equalsIgnoreCase("Safari")) {
					return getSafariDriver();
				} else if ((browser.equalsIgnoreCase("ie")) || (browser.equalsIgnoreCase("internetexplorer"))
						|| (browser.equalsIgnoreCase("internet explorer"))) {
					return getInternetExplorerDriver(seleniumconfig.get("driverpath"));
				}
				else if ((browser.equalsIgnoreCase("edge")))
						 {
					return getEdgeDriver();
				}
			}
		}
		if (seleniumconfig.get("seleniumserver").equalsIgnoreCase("remote")) {
			return setRemoteDriver(seleniumconfig);
		}
		return new FirefoxDriver();
	}

	private WebDriver setRemoteDriver(Map<String, String> selConfig) {
		DesiredCapabilities cap = null;
		browser = selConfig.get("browser");
		if (browser.equalsIgnoreCase("firefox")) {
			cap = DesiredCapabilities.firefox(); 
		} else if (browser.equalsIgnoreCase("chrome")) {
			cap = DesiredCapabilities.chrome();
			//new
			ChromeOptions dc=new ChromeOptions();
			dc.addArguments("disable-infobars");
			cap.setCapability(ChromeOptions.CAPABILITY, dc);
		} else if (browser.equalsIgnoreCase("Safari")) {
			cap = DesiredCapabilities.safari();
		} else if ((browser.equalsIgnoreCase("ie")) || (browser.equalsIgnoreCase("internetexplorer"))
				|| (browser.equalsIgnoreCase("internet explorer"))) {
			cap = DesiredCapabilities.internetExplorer();
		}
		String seleniuhubaddress = selConfig.get("seleniumserverhost");
		URL selserverhost = null;
		try {
			selserverhost = new URL(seleniuhubaddress);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		cap.setJavascriptEnabled(true);
		return new RemoteWebDriver(selserverhost, cap);

	}

	private static WebDriver getChromeDriver(String driverpath) {
		// System.setProperty("webdriver.chrome.driver", driverpath);
		System.setProperty("webdriver.chrome.driver", "src/test/resources/drivers/chromedriver.exe");
		// //options.addExtensions(new File("extension_2_1_2.crx"));
		// DesiredCapabilities cap = DesiredCapabilities.chrome();
		// cap.setCapability(ChromeOptions.CAPABILITY, options);
		// cap.setVersion("54.0.2840.99 m"); // Adding version
		//
		System.setProperty("webdriver.chrome.args", "--disable-logging");
		System.setProperty("webdriver.chrome.silentOutput", "true");
		return new ChromeDriver(getChromeOptions());
	}
	
	private static WebDriver getInternetExplorerDriver(String driverpath) {
		// System.setProperty("webdriver.ie.driver", driverpath);
		 System.setProperty("webdriver.ie.driver",
		 "src/test/resources/drivers/IEDriverServer.exe");
		// capabilities.setCapability("ignoreZoomSetting", true);
		// capabilities.setCapability("ignoreZoomLevel", true);
		// InternetExplorerOptions options = new InternetExplorerOptions();
		DesiredCapabilities dc = DesiredCapabilities.internetExplorer();
		dc.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
		// InternetExplorerOptions options = new InternetExplorerOptions();
		return new InternetExplorerDriver(dc);
	}

	private static WebDriver getSafariDriver() {
		return new SafariDriver();
	}
	private static WebDriver getEdgeDriver()
	{
		System.setProperty("webdriver.edge.driver", "src/test/resources/drivers/MicrosoftWebDriver.exe");
		EdgeOptions options = new EdgeOptions();
		options.setPageLoadStrategy("eager");
		return new EdgeDriver(options);
		
	}

	private static WebDriver getFirefoxDriver(String driverpath) {
		// System.setProperty("webdriver.gecko.driver", driverpath);
		System.setProperty("webdriver.gecko.driver", "src/test/resources/drivers/geckodriver.exe");
		System.setProperty(FirefoxDriver.SystemProperty.DRIVER_USE_MARIONETTE, "true");
		System.setProperty(FirefoxDriver.SystemProperty.BROWSER_LOGFILE, logGeckoPath);
		// DesiredCapabilities capabilities = DesiredCapabilities.firefox();
		// capabilities.setCapability("marionette", true);
		FirefoxOptions options = new FirefoxOptions();
		options.setProfile(getFirefoxProfile());
		return new FirefoxDriver(options);
		// return new FirefoxDriver(capabilities);
	}

	private static ChromeOptions getChromeOptions() {
		ChromeOptions options = new ChromeOptions();
		options.addArguments("test-type");
		options.addArguments("start-maximized");
		options.addArguments("--disable-notifications");
		options.addExtensions(new File("extension_2_1_2.crx"));
		Map<String, Object> prefs = new HashMap<String, Object>();
		prefs.put("profile.default_content_settings.popups", 0);
		prefs.put("profile.default_content_setting_values.notifications", 1);
		prefs.put("download.default_directory", filePath);
		options.setExperimentalOption("prefs", prefs);
		return options;
	}
	
	private static FirefoxProfile getFirefoxProfile() {
		FirefoxProfile profile = new FirefoxProfile();
		profile.setPreference("browser.download.folderList", 2);
		profile.setPreference("browser.download.dir", filePath);
		profile.setPreference("browser.helperApps.neverAsk.saveToDisk", "application/octet-stream");
		profile.setPreference("pdfjs.disabled", true);
		return profile;
	}

	private WebDriver setMobileDriver(Map<String, String> selConfig) {
		DesiredCapabilities cap = new DesiredCapabilities();
		String[] appiumDeviceConfig = selConfig.get("mobileDevice").split(":");
		cap.setCapability("deviceName", appiumDeviceConfig[0]);
		cap.setCapability("device", appiumDeviceConfig[1]);
		cap.setCapability("platformName", appiumDeviceConfig[1]);
		cap.setCapability("androidPackage", "com.android.chrome");
		cap.setCapability(CapabilityType.VERSION, "5.0.1");
		cap.setCapability(CapabilityType.PLATFORM, "Windows");
		cap.setCapability(MobileCapabilityType.BROWSER_NAME, "chrome");
		cap.setCapability("browser", "chrome");
		capabilities.setCapability("chrome.switches", Arrays.asList("--incognito"));
		cap.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, "400");

		String appiumServerHostUrl = selConfig.get("appiumServer");
		URL appiumServerHost = null;
		try {
			appiumServerHost = new URL(appiumServerHostUrl);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		cap.setJavascriptEnabled(true);
		System.out.println(appiumServerHostUrl);

		AndroidDriver driverAndroid = new AndroidDriver(appiumServerHost, cap);

		Set<String> contextNames = driverAndroid.getContextHandles();
		for (String contextName : contextNames) {
			System.out.println(contextName);
			if (contextName.contains("WEBVIEW") || contextName.contains("CHROMIUM")) {
				driverAndroid.context(contextName);
			}
		}

		WebDriver driver = driverAndroid;
		System.out.println("driver is :" + driver);

		return driver;

		// return new RemoteWebDriver(appiumServerHost, cap);

	}

	private WebDriver setMobileDriver_IOS(Map<String, String> selConfig) {
		DesiredCapabilities cap = new DesiredCapabilities();
		String[] appiumDeviceConfig = selConfig.get("mobileDevice").split(":");

		cap.setCapability("deviceName", appiumDeviceConfig[0]);
		cap.setCapability("device", appiumDeviceConfig[1]);
		cap.setCapability("platformName", appiumDeviceConfig[1]);
		cap.setCapability("app", appiumDeviceConfig[2]);
		cap.setCapability(CapabilityType.VERSION, "5.0.2");
		cap.setCapability(CapabilityType.PLATFORM, "Windows");
		capabilities.setCapability("chrome.switches", Arrays.asList("--incognito"));

		String appiumServerHostUrl = selConfig.get("appiumServer");
		URL appiumServerHost = null;
		try {
			appiumServerHost = new URL(appiumServerHostUrl);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		cap.setJavascriptEnabled(true);
		System.out.println(appiumServerHostUrl);

		/*
		 * RemoteWebDriver driver1= new RemoteWebDriver(appiumServerHost, cap);;
		 * driver1.setFileDetector(new LocalFileDetector()); return driver1;
		 */

		return new RemoteWebDriver(appiumServerHost, cap);

	}

	private WebDriver setAndroidDriver(Map<String, String> selConfig) {

		File app = new File("src\\test\\resources\\App\\ProApp.apk");
		DesiredCapabilities cap = new DesiredCapabilities();
		String[] appiumDeviceConfig = selConfig.get("mobileDevice").split(":");
		cap.setCapability(MobileCapabilityType.BROWSER_NAME, "");
		cap.setCapability(MobileCapabilityType.DEVICE_NAME, appiumDeviceConfig[0]);
		cap.setCapability(MobileCapabilityType.APP, app.getAbsolutePath());
		cap.setCapability("fullReset", false);
		cap.setCapability("noReset", true);
		// cap.setCapability("fullReset",true);
		cap.setCapability("--session-override", true);
		cap.setCapability("device", appiumDeviceConfig[1]);
		cap.setCapability("platformName", appiumDeviceConfig[1]);
		cap.setCapability("app", appiumDeviceConfig[2]);
		cap.setCapability("newCommandTimeout", 1000000);
		cap.setCapability(CapabilityType.VERSION, "5.0.2");
		cap.setCapability(CapabilityType.PLATFORM, "Windows");
		String appiumServerHostUrl = selConfig.get("appiumServer");
		URL appiumServerHost = null;
		try {
			appiumServerHost = new URL(appiumServerHostUrl);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		cap.setJavascriptEnabled(true);
		System.out.println(appiumServerHostUrl);
		AndroidDriver driver = new AndroidDriver(appiumServerHost, cap);
		return driver;
	}

}
