package com.qait.automation.getpageobjects;

import static com.qait.automation.getpageobjects.ObjectFileReader.getELementFromFile;
import io.appium.java_client.android.AndroidDriver;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import junit.framework.Assert;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.qait.automation.utils.LayoutValidation;

import static org.testng.Assert.fail;
import static org.testng.Assert.assertTrue;
import static org.testng.Assert.assertEquals;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.StringContains.containsString;

public class GetPage extends BaseUi {

	protected WebDriver webdriver;
	// protected AndroidDriver androidDriver;
	String pageName;
	LayoutValidation layouttest;

	public GetPage(WebDriver driver, String pageName) {
		super(driver, pageName);
		this.webdriver = driver;
		this.pageName = pageName;
		layouttest = new LayoutValidation(driver, pageName);

		// if
		// (ConfigPropertyReader.getProperty("appType").equals("native")||ConfigPropertyReader.getProperty("appType").equals("hybrid")){
		// androidDriver = (AndroidDriver)(driver);
		// System.out.println("casted driver to android driver");
		// }
		// else if
		// (ConfigPropertyReader.getProperty("appType").equals("hybrid")){
		// System.out.println("In else if ");
		// }
	}

	public boolean testPageLayout(List<String> tagsToBeTested) {
		return layouttest.checklayout(tagsToBeTested);
	}

	public void testPageLayout(List<String> browserSizes, List<String> tagsToBeTested) {
		layouttest.checklayout(browserSizes, tagsToBeTested);
	}

	public void testPageLayout(String[] browserSizes, String[] tagToBeTested) {
		testPageLayout(Arrays.asList(browserSizes), Arrays.asList(tagToBeTested));
	}

	public boolean testPageLayout(String... tagToBeTested) {
		return testPageLayout(Arrays.asList(tagToBeTested));
	}

	// TODO: put this in right place, create dedicated class for frame and
	// window handlers
	protected void switchToNestedFrames(String frameNames) {
		switchToDefaultContent();
		String[] frameIdentifiers = frameNames.split(":");
		for (String frameId : frameIdentifiers) {
			wait.waitForFrameToBeAvailableAndSwitchToIt(getLocator(frameId.trim()));
		}
	}

	protected WebElement element(String elementToken) {
		return element(elementToken, "");
	}

	// Mobile
	protected WebElement mobileElement(String elementToken) {
		return mobileElement(elementToken, "");
	}

	protected WebElement element(String elementToken, String replacement1, String replacement2)
			throws NoSuchElementException {
		WebElement elem = null;
		try {
			elem = wait.waitForElementToBeVisible(
					webdriver.findElement(getLocator(elementToken, replacement1, replacement2)));
		} catch (NoSuchElementException excp) {
			fail("FAILED: Element " + elementToken + " not found on the " + this.pageName + " !!!");
		}
		return elem;
	}

	protected WebElement element(String elementToken, String replacement) throws NoSuchElementException {
		WebElement elem = null;
		try {
			elem = wait.waitForElementToBeVisible(webdriver.findElement(getLocator(elementToken, replacement)));
		} catch (NoSuchElementException excp) {
			fail("FAILED: Element " + elementToken + " not found on the " + this.pageName + " !!!");
		}
		return elem;
	}

	// Mobile
	protected WebElement mobileElement(String elementToken, String replacement) throws NoSuchElementException {
		AndroidDriver androidDriver = ((AndroidDriver) webdriver);
		// AppiumDriver appiumDriver = ((AppiumDriver)webdriver);
		WebElement elem = null;
		try {
			// elem =
			// wait.waitForElementToBeVisible(appiumDriver.findElement(getLocator(elementToken,
			// replacement)));
			elem = wait.waitForElementToBeVisible(androidDriver.findElement(getLocator(elementToken, replacement)));
		} catch (NoSuchElementException excp) {
			fail("FAILED: Element " + elementToken + " not found on the " + this.pageName + " !!!");
		}
		return elem;
	}

	protected List<WebElement> elements(String elementToken, String replacement) {
		return wait.waitForElementsToBeVisible(webdriver.findElements(getLocator(elementToken, replacement)));
	}

	protected List<WebElement> elements(String elementToken, String replacement1, String replacement2) {
		return webdriver.findElements(getLocator(elementToken, replacement1, replacement2));
	}

	protected List<WebElement> elements(String elementToken) {
		return elements(elementToken, "");
	}

	/**
	 * @param xpath
	 * @return TO be used for avoiding check for visibility/implicit wait
	 *         condition
	 */
	protected List<WebElement> getListofWebElementsUsingStaticXpath(String xpath) {
		return driver.findElements(By.xpath(xpath));
	}

	protected WebElement getWebElementUsingStaticXpath(String xpath) {
		return driver.findElement(By.xpath(xpath));
	}

	protected WebElement getWebelementusingStaticCss(String css) throws NoSuchElementException {
		return driver.findElement(By.cssSelector(css));
	}

	protected String getPageSource() {
		return driver.getPageSource();
	}

	protected List<WebElement> getListofWebelementusingStaticCss(String css) throws NoSuchElementException {
		return driver.findElements(By.cssSelector(css));
	}

	protected void _waitForElementToDisappear(String elementToken, String replacement) {
		int i = 0;
		int initTimeout = wait.getTimeout();
		wait.resetImplicitTimeout(2);
		int count;
		while (i <= 20) {
			if (replacement.isEmpty())
				count = elements(elementToken).size();
			else
				count = elements(elementToken, replacement).size();
			if (count == 0)
				break;
			i += 2;
		}
		wait.resetImplicitTimeout(initTimeout);
	}

	protected void waitForElementToDisappear(String elementToken) {
		_waitForElementToDisappear(elementToken, "");
	}

	protected void waitForElementToDisappear(String elementToken, String replacement) {
		_waitForElementToDisappear(elementToken, replacement);
	}

	protected boolean isElementPresentOnMobile(String elementName, String elementTextReplace) {
		wait.waitForElementToBeVisible(element(elementName, elementTextReplace));
		boolean result = mobileElement(elementName, elementTextReplace).isDisplayed();
		assertTrue(result,
				"TEST FAILED: element '" + elementName + "with text " + elementTextReplace + "' is not displayed.");
		logAssertionPassedMessage("element " + elementName + " with text " + elementTextReplace + " is displayed.");
		return result;
	}

	protected boolean isElementDisplayed(String elementName, String elementTextReplace) {
		wait.waitForElementToBeVisible(element(elementName, elementTextReplace));
		boolean result = element(elementName, elementTextReplace).isDisplayed();
		assertTrue(result,
				"TEST FAILED: element '" + elementName + "with text '" + elementTextReplace + "' is not displayed.");
		logAssertionPassedMessage("element " + elementName + " with text '" + elementTextReplace + "' is displayed.");
		return result;
	}

	protected boolean isElementDisplayed(String elementName, String elementTextReplace, String elementTextReplace2) {
		wait.waitForElementToBeVisible(element(elementName, elementTextReplace, elementTextReplace2));
		boolean result = element(elementName, elementTextReplace, elementTextReplace2).isDisplayed();
		assertTrue(result, "TEST FAILED: element '" + elementName + "with text '" + elementTextReplace + "' and '"
				+ elementTextReplace2 + "' is not displayed.");
		logAssertionPassedMessage("element " + elementName + " with text '" + elementTextReplace + "' and '"
				+ elementTextReplace2 + "' is displayed.");
		return result;
	}

	protected void verifyElementText(String elementName, String expectedText) {
		wait.waitForElementToBeVisible(element(elementName));
		assertEquals(element(elementName).getText().trim(), expectedText,
				"TEST FAILED: element '" + elementName + "' Text is not as expected: ");
		logAssertionPassedMessage("element " + elementName + " is visible and Text is " + expectedText);
	}

	protected void verifyElementTextContains(String elementName, String expectedText) {
		wait.waitForElementToBeVisible(element(elementName));
		assertThat("TEST FAILED: element '" + elementName + "' Text is not as expected: ",
				element(elementName).getText().trim(), containsString(expectedText));
		logAssertionPassedMessage("element " + elementName + " is visible and Text is " + expectedText);
	}

	protected boolean isElementDisplayed(String elementName) {
		wait.waitForElementToBeVisible(element(elementName));
		boolean result = element(elementName).isDisplayed();
		assertTrue(result, "TEST FAILED: element '" + elementName + "' is not displayed.");
		logAssertionPassedMessage("element " + elementName + " is displayed.");
		return result;
	}

	protected boolean isElementDisplayed(WebElement elementName) {
		wait.waitForElementToBeVisible(elementName);
		boolean result = elementName.isDisplayed();
		assertTrue(result, "TEST FAILED: element is not displayed.");
		logAssertionPassedMessage("element is displayed.");
		return result;
	}

	protected boolean isElementPresentOnMobile(String elementName) {
		wait.waitForElementToBeVisible(element(elementName));
		boolean result = mobileElement(elementName).isDisplayed();
		assertTrue(result, "TEST FAILED: element '" + elementName + "' is not displayed.");
		logAssertionPassedMessage("element " + elementName + " is displayed.");
		return result;
	}

	protected boolean isElementEnabled(String elementName, boolean expected) {
		wait.waitForElementToBeVisible(element(elementName));
		boolean result = expected && element(elementName).isEnabled();
		assertTrue(result, "TEST FAILED: element '" + elementName + "' is  ENABLED :- " + !expected);
		logAssertionPassedMessage("element " + elementName + " is enabled : " + expected);
		return result;
	}

	protected boolean isElementEnabled(WebElement element, boolean expected) {
		wait.waitForElementToBeVisible(element);
		boolean result = expected && element.isEnabled();
		assertTrue(result);
		return result;
	}

	public WebElement getElementusingTag(String tagName) {
		return driver.findElement(By.tagName(tagName));
	}

	private By getBy(String locatorType, String locatorValue) {
		switch (Locators.valueOf(locatorType)) {
		case id:
			return By.id(locatorValue);
		case xpath:
			return By.xpath(locatorValue);
		case css:
			return By.cssSelector(locatorValue);
		case name:
			return By.name(locatorValue);
		case classname:
			return By.className(locatorValue);
		case linktext:
			return By.linkText(locatorValue);
		default:
			return By.id(locatorValue);
		}
	}

	protected boolean isStringMatching(String actual, String expected) {
		if (actual.equals(expected)) {
			logAssertionPassedMessage("String /" + actual + "/ equal to expected /" + expected + "/");
			return true;
		} else {
			logErrorMessage("String don't match as expected i.e. " + expected);
			return false;
		}
	}

	protected boolean isElementNotDisplayed(String elementName) {
		boolean result = false;
		try {
			driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
			hardWait(1);
			element(elementName).isDisplayed();
			result = false;
			logAssertionPassedMessage("element " + elementName + " is displayed");

		} catch (TimeoutException ae) {
			Assert.assertTrue(true);
			logAssertionPassedMessage("element " + elementName + " is not displayed");
			result = true;

		} // end of catch
		catch (NoSuchElementException nsex) {
			Assert.assertTrue(true);
			logAssertionPassedMessage("element " + elementName + " is not displayed");
			result = true;

		} // end of catch
		catch (NullPointerException npt) {
			Assert.assertTrue(true);
			logAssertionPassedMessage("element " + elementName + " is not displayed");
			result = true;

		} // end of catch
		catch (AssertionError ae) {
			Assert.assertTrue(true);
			logAssertionPassedMessage("element " + elementName + " is not displayed");
			result = true;

		} // end of catch
		hardWait(1);
		return result;
	}

	protected boolean isElementNotDisplayed(WebElement elementName) {
		boolean result = false;
		try {
			driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
			hardWait(1);
			elementName.isDisplayed();
			result = false;
			logAssertionPassedMessage("element is displayed");

		} catch (TimeoutException ae) {
			Assert.assertTrue(true);
			logAssertionPassedMessage("element is not displayed");
			result = true;

		} // end of catch
		catch (NoSuchElementException nsex) {
			Assert.assertTrue(true);
			logAssertionPassedMessage("element  is not displayed");
			result = true;

		} // end of catch
		catch (NullPointerException npt) {
			Assert.assertTrue(true);
			logAssertionPassedMessage("element is not displayed");
			result = true;

		} // end of catch
		catch (AssertionError ae) {
			Assert.assertTrue(true);
			logAssertionPassedMessage("element is not displayed");
			result = true;

		} // end of catch
		hardWait(1);
		return result;
	}

	protected boolean isElementNotDisplayed(List<WebElement> elements) {
		boolean result = false;
		try {
			driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
			hardWait(1);
			elements.get(0).isDisplayed();
			result = false;
			logAssertionPassedMessage("element is displayed");

		} catch (TimeoutException ae) {
			Assert.assertTrue(true);
			logAssertionPassedMessage("element is not displayed");
			result = true;

		} // end of catch
		catch (NoSuchElementException nsex) {
			Assert.assertTrue(true);
			logAssertionPassedMessage("element is not displayed");
			result = true;

		} // end of catch
		catch (NullPointerException npt) {
			Assert.assertTrue(true);
			logAssertionPassedMessage("element is not displayed");
			result = true;

		} // end of catch
		catch (AssertionError ae) {
			Assert.assertTrue(true);
			logAssertionPassedMessage("element is not displayed");
			result = true;

		} // end of catch
		hardWait(1);
		return result;
	}

	protected boolean isElementNotDisplayed(String elementName, String replacement) {
		boolean result = false;
		try {
			driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
			hardWait(1);
			element(elementName, replacement).isDisplayed();
			result = false;
			logAssertionFailedMessage("element " + elementName + " is displayed.");

		} catch (TimeoutException ae) {
			Assert.assertTrue(true);
			logAssertionPassedMessage("element " + elementName + " is not displayed.");
			result = true;

		} // end of catch
		catch (NoSuchElementException nsex) {
			Assert.assertTrue(true);
			logAssertionPassedMessage("element " + elementName + " is not displayed.");
			result = true;

		} // end of catch
		catch (NullPointerException npt) {
			Assert.assertTrue(true);
			logAssertionPassedMessage("element " + elementName + " is not displayed.");
			result = true;

		} // end of catch
		catch (AssertionError ae) {
			Assert.assertTrue(true);
			logAssertionPassedMessage("element " + elementName + " is not displayed.");
			result = true;

		} // end of catch
		hardWait(1);
		return result;
	}

	protected boolean isElementNotDisplayed(String elementName, String replacement1, String replacement2) {
		boolean result = false;
		try {
			driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
			hardWait(1);
			element(elementName, replacement1, replacement2).isDisplayed();
			result = false;
			logAssertionFailedMessage("element " + elementName + " is displayed.");

		} catch (TimeoutException ae) {
			Assert.assertTrue(true);
			logAssertionPassedMessage("element " + elementName + " is not displayed.");
			result = true;

		} // end of catch
		catch (NoSuchElementException nsex) {
			Assert.assertTrue(true);
			logAssertionPassedMessage("element " + elementName + " is not displayed.");
			result = true;

		} // end of catch
		catch (NullPointerException npt) {
			Assert.assertTrue(true);
			logAssertionPassedMessage("element " + elementName + " is not displayed.");
			result = true;

		} // end of catch
		catch (AssertionError ae) {
			Assert.assertTrue(true);
			logAssertionPassedMessage("element " + elementName + " is not displayed.");
			result = true;

		} // end of catch
		hardWait(1);
		return result;
	}

	protected By getLocator(String elementToken) {
		return getLocator(elementToken, "");
	}

	protected By getLocator(String elementToken, String replacement) {
		String[] locator = getELementFromFile(this.pageName, elementToken);
		locator[2] = locator[2].replaceAll("\\$\\{.+\\}", replacement);
		return getBy(locator[1].trim(), locator[2].trim());
	}

	protected By getLocator(String elementToken, String replacement1, String replacement2) {
		String[] locator = getELementFromFile(this.pageName, elementToken);
		locator[2] = StringUtils.replace(locator[2], "$", replacement1);
		locator[2] = StringUtils.replace(locator[2], "%", replacement2);
		return getBy(locator[1].trim(), locator[2].trim());
	}

	public boolean isCheckboxChecked(WebElement element) {
		boolean isChecked;
		isChecked = element.getAttribute("checked").equals("checked");
		return isChecked;
	}

}
