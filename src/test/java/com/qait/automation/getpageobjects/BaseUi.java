/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.qait.automation.getpageobjects;

import static com.qait.automation.getpageobjects.ObjectFileReader.getPageTitleFromFile;
import static com.qait.automation.utils.ConfigPropertyReader.getProperty;
import static org.testng.Assert.assertEquals;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidKeyCode;

import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.Alert;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchWindowException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;

//import com.gargoylesoftware.htmlunit.javascript.host.URL;
import com.qait.automation.utils.DataBaseConnecter;
import com.qait.automation.utils.RestAPITester;
import com.qait.automation.utils.SeleniumWait;
import com.qait.automation.utils.YamlReader;

/**
 *
 * @author QAIT
 */
public class BaseUi {

	WebDriver driver;
	// AndroidDriver androidDriver;
	// AppiumDriver appiumDriver;
	protected SeleniumWait wait;
	protected DataBaseConnecter dbConnector;
	protected RestAPITester apiTester;
	private String pageName;

	protected BaseUi(WebDriver driver, String pageName) {
		PageFactory.initElements(driver, this);
		this.driver = driver;
		this.pageName = pageName;
		this.wait = new SeleniumWait(driver, Integer.parseInt(getProperty("timeout")));
		this.apiTester = new RestAPITester();
		this.dbConnector = new DataBaseConnecter();
	}

	protected String getPageTitle() {
		return driver.getTitle();
	}

	protected String logMessage(String message) {
		Reporter.log(message, true);
		return message;
	}

	protected String logInfoMessage(String message) {
		logMessage("  [info] " + message);
		return message;
	}

	protected String logErrorMessage(String message) {
		logMessage(" [error] " + message);
		return message;
	}

	protected String logAssertionPassedMessage(String message) {
		Reporter.log("[PASSED] " + message, true);
		return message;
	}

	protected String logAssertionFailedMessage(String message) {
		Reporter.log("[FAILED] " + message, true);
		return message;
	}

	protected String getCurrentURL() {
		return driver.getCurrentUrl();
	}

	public void launchSpecificUrl(String url) {
		driver.get(url);
	}

	protected void verifyPageTitleExact() {
		String pageTitle = getPageTitleFromFile(pageName);
		verifyPageTitleExact(pageTitle.trim());
	}

	protected void verifyPageUrlContains(String url) {
		Assert.assertTrue(getCurrentURL().contains(url), "[ASSERT FAILED]: Navigated to " + getCurrentURL());
		logAssertionPassedMessage("Navigated To Page Containing " + url);
	}

	public void navigateBack() {
		driver.navigate().back();
		logInfoMessage("Navigated back");
	}

	protected void verifyPageTitleExact(String expectedPagetitle) {
		if (((expectedPagetitle == "") || (expectedPagetitle == null) || (expectedPagetitle.isEmpty()))
				&& (getProperty("browser").equalsIgnoreCase("chrome"))) {
			expectedPagetitle = getCurrentURL();
		}
		try {
			wait.waitForPageTitleToBeExact(expectedPagetitle);
			logAssertionPassedMessage("PageTitle for " + pageName + " is exactly: '" + expectedPagetitle + "'");
		} catch (TimeoutException ex) {
			Assert.fail("TEST FAILED: PageTitle for " + pageName + " is not exactly: '" + expectedPagetitle
					+ "'!!!\n instead it is :- " + driver.getTitle());
		}
	}

	public void verifyPageTitle(String pageTitle) {
		hardWait(6);
		assertEquals(getPageTitle().trim(), pageTitle);
		logAssertionPassedMessage("Page title verified");
	}

	/**
	 * Verifies if the arraylist is sorted or not <by Shweta Singh>
	 */
	public boolean isSorted(ArrayList<String> list) {
		boolean sorted = true;
		for (int i = 1; i < list.size(); i++) {
			if (list.get(i - 1).compareTo(list.get(i)) > 0)
				sorted = false;
		}

		return sorted;
	}

	public boolean isSortedDescending(ArrayList<String> list) {
		boolean sorted = true;
		for (int i = 1; i < list.size(); i++) {
			if (list.get(i - 1).compareTo(list.get(i)) < 0)
				sorted = false;
		}

		return sorted;
	}

	/**
	 * Verification of the page title with the title text provided in the page
	 * object repository
	 */
	protected void verifyPageTitleContains() {
		String expectedPagetitle = getPageTitleFromFile(pageName).trim();
		verifyPageTitleContains(expectedPagetitle);
	}

	/**
	 * this method will get page title of current window and match it partially
	 * with the param provided
	 *
	 * @param expectedPagetitle
	 *            partial page title text
	 */
	protected void verifyPageTitleContains(String expectedPagetitle) {

		if (((expectedPagetitle == "") || (expectedPagetitle == null) || (expectedPagetitle.isEmpty()))
				&& (getProperty("browser").equalsIgnoreCase("chrome"))) {
			expectedPagetitle = getCurrentURL();
		}
		try {
			wait.waitForPageTitleToContain(expectedPagetitle);
		} catch (TimeoutException exp) {
			String actualPageTitle = driver.getTitle().trim();
			logMessage("TEST FAILED: As actual Page Title: '" + actualPageTitle
					+ "' does not contain expected Page Title : '" + expectedPagetitle + "'.");
		}
		String actualPageTitle = getPageTitle().trim();
		logAssertionPassedMessage("PageTitle for " + actualPageTitle + " contains: '" + expectedPagetitle + "'");
	}

	protected WebElement getElementByIndex(List<WebElement> elementlist, int index) {
		return elementlist.get(index);
	}

	protected WebElement getElementByExactText(List<WebElement> elementlist, String elementtext) {
		WebElement element = null;
		for (WebElement elem : elementlist) {
			if (elem.getText().equalsIgnoreCase(elementtext.trim())) {
				element = elem;
			}
		}
		// FIXME: handle if no element with the text is found in list No element
		// exception
		if (element == null) {
		}
		return element;
	}

	protected WebElement getElementByContainsText(List<WebElement> elementlist, String elementtext) {
		WebElement element = null;
		for (WebElement elem : elementlist) {
			if (elem.getText().contains(elementtext.trim())) {
				element = elem;
			}
		}
		// FIXME: handle if no element with the text is found in list
		if (element == null) {
		}
		return element;
	}

	protected void switchToFrame(WebElement element) {
		// switchToDefaultContent();
		/*
		 * try { wait.waitForElementToBeVisible(element);
		 * driver.switchTo().frame(element);
		 * logMessage("[INFO']: Switched to frame : "+element.getAttribute("id")
		 * ); } catch(StaleElementReferenceException stl) {
		 * wait.waitForElementToBeVisible(element);
		 * driver.switchTo().frame(element);
		 * logMessage("[INFO']: Switched to frame : "+element.getAttribute("id")
		 * ); }
		 */
		wait.waitForElementToBeVisible(element);
		driver.switchTo().frame(element);
		// logMessage("[INFO']: Switched to frame :
		// "+element.getAttribute("id"));
	}

	public void switchToFrame(int i) {
		driver.switchTo().frame(i);
	}

	public void switchToFrame(String id) {
		driver.switchTo().frame(id);
	}

	public void switchToDefaultContent() {
		driver.switchTo().defaultContent();
	}

	public String switchToWindow() {
		String parentWindowHandler = driver.getWindowHandle();
		String subWindowHandler = null;
		Set<String> handles = driver.getWindowHandles(); // get all window
		// handles
		Iterator<String> iterator = handles.iterator();
		while (iterator.hasNext()) {
			subWindowHandler = iterator.next();
		}
		driver.switchTo().window(subWindowHandler);
		return parentWindowHandler;
	}

	public void closeWindow() {
		driver.close();
		hardWait(2);
		logInfoMessage("Window closed");
	}

	public void switchToDefaultWindow(String parentWindowHandler) {
		try {
			driver.switchTo().window(parentWindowHandler);
			logInfoMessage("Switched back to default window");
		} catch (NoSuchWindowException nw) {
			logInfoMessage("Not able to switch to default window");
		}
	}

	protected void executeJavascript(String script) {
		((JavascriptExecutor) driver).executeScript(script);
	}

	protected void executeJavascript(String script, WebElement element) {
		((JavascriptExecutor) driver).executeScript(script, element);
	}

	protected String executesJavascript(String script, WebElement element) {
		String returnValue = (String) ((JavascriptExecutor) driver).executeScript(script, element);
		return returnValue;

	}

	protected String executeJavaScript(String script) {
		String returnValue = (String) ((JavascriptExecutor) driver).executeScript(script);
		return returnValue;
	}

	protected void hover(WebElement element) {
		Actions hoverOver = new Actions(driver);
		hoverOver.moveToElement(element).build().perform();
	}

	public void acceptAlert() {
		try {
			switchToAlert().accept();
			logInfoMessage("Javascript Alert handled");
			driver.switchTo().defaultContent();
		} catch (Exception e) {
			logInfoMessage("No Javascript Alert was found");
		}
	}

	public void acceptConsecutiveAlerts() {
		try {
			switchToAlert().accept();
			logInfoMessage("Javascript Alert handled");
			// driver.switchTo().defaultContent();
			hardWait(5);
			logInfoMessage(getAlertText());
			switchToAlert().accept();
			logInfoMessage("Another Javascript Alert handled");
		} catch (Exception e) {
			logInfoMessage("No Javascript Alert was found");
		}
	}

	protected void cancelAlert() {
		try {
			switchToAlert().dismiss();
			logInfoMessage("Javascript Alert cancelled");
			driver.switchTo().defaultContent();
		} catch (Exception e) {
			logInfoMessage("No Javascript Alert was found");
		}
	}

	public String getAlertText() {
		Alert alert = driver.switchTo().alert();
		return alert.getText();
	}

	private Alert switchToAlert() {
		WebDriverWait wait = new WebDriverWait(driver, 1);
		return wait.until(ExpectedConditions.alertIsPresent());
	}

	protected void selectProvidedTextFromDropDown(WebElement el, String text) {
		wait.waitForElementToBeVisible(el);
		scrollDown(el);
		Select sel = new Select(el);
		sel.selectByVisibleText(text);
	}

	protected void performDoubleClick(WebElement e) {
		Actions actions = new Actions(driver);
		actions.doubleClick(e).build().perform();
	}

	protected void openElementInNewTab(WebElement el) {
		String selectLinkOpeninNewTab = Keys.chord(Keys.CONTROL, Keys.RETURN);
		el.sendKeys(selectLinkOpeninNewTab);
	}

	protected void openElementInNewTabUsing_CtrlAndClick(WebElement el) {
		Actions action = new Actions(driver);
		action.moveToElement(el).sendKeys(Keys.CONTROL).click().build().perform();
	}

	protected void moveToElementAndClick(WebElement el) {
		Actions actions = new Actions(driver);
		actions.moveToElement(el).click().build().perform();
	}

	protected void moveToElement(WebElement el) {
		Actions actions = new Actions(driver);
		actions.moveToElement(el).build().perform();
	}

	protected void clickAtCoordinates(WebElement el, int xCoord, int yCoord) {
		Actions actions = new Actions(driver);
		actions.moveToElement(el, xCoord, yCoord).click().build().perform();
	}

	protected void sendKeyboardKeysUsingAction(WebElement element, Keys enter) {
		Actions actions = new Actions(driver);
		actions.moveToElement(element).click().sendKeys(enter).build().perform();
	}

	protected void sendKeyboardKeysUsingAction(WebElement element, Keys enter1, Keys enter2) {
		Actions actions = new Actions(driver);
		actions.moveToElement(element).click().sendKeys(enter1).sendKeys(enter2).build().perform();
	}

	protected void pasteUsingActions(WebElement el) {
		Actions action = new Actions(driver);
		action.keyDown(Keys.CONTROL).sendKeys("v").keyUp(Keys.CONTROL);
		action.perform();
	}

	protected void selectUsingActions(WebElement el) {
		Actions action = new Actions(driver);
		action.keyDown(Keys.CONTROL).sendKeys("a").keyUp(Keys.CONTROL);
		action.perform();
	}

	protected void deleteTextUsingKeyboardKeys(WebElement el) {
		Actions action = new Actions(driver);
		action.moveToElement(el).click().sendKeys(Keys.chord(Keys.CONTROL, "a")).sendKeys(Keys.DELETE).perform();
	}

	protected void selectUsingActions() {
		Actions action = new Actions(driver);
		action.keyDown(Keys.CONTROL).sendKeys("a").keyUp(Keys.CONTROL);
		action.perform();
	}

	protected void deleteUsingAction() {
		Actions action = new Actions(driver);
		action.keyDown(Keys.CONTROL).sendKeys("d").keyUp(Keys.CONTROL);
		action.perform();
	}

	protected void PerformUsingAction(String key) {
		Actions action = new Actions(driver);
		action.keyDown(Keys.CONTROL).sendKeys(key).keyUp(Keys.CONTROL);
		action.perform();
	}

	protected void openNewTabInBrowser() {

		((JavascriptExecutor) driver).executeScript("window.open();");
		logInfoMessage("New Tab is opened");
		// action.keyDown(Keys.CONTROL).keyDown(Keys.SHIFT).moveToElement(element).click().keyUp(Keys.SHIFT).keyUp(Keys.CONTROL).build().perform();
	}

	protected void open(WebElement el) {
		Actions action = new Actions(driver);
		action.moveToElement(el).sendKeys(Keys.chord(Keys.CONTROL, "t"));
	}

	protected void sendKeysUsingAction(WebElement el, String text) {
		Actions actions = new Actions(driver);
		actions.moveToElement(el).click().sendKeys(text).build().perform();
	}

	protected void selectCurrentLineUsingActions() {
		Actions actions = new Actions(driver);
		actions.keyDown(Keys.SHIFT).sendKeys(Keys.HOME).keyUp(Keys.SHIFT).build().perform();
	}

	protected void OpenDeveloperTools() {
		Actions actions = new Actions(driver);
		actions.keyDown(Keys.CONTROL).keyDown(Keys.SHIFT).sendKeys("i").build().perform();

	}

	protected void OpenMobileView() {
		Actions actions = new Actions(driver);
		actions.keyDown(Keys.CONTROL).keyDown(Keys.SHIFT).sendKeys("m").build().perform();
	}

	protected void backspaceUsingAction(WebElement el) {
		Actions actions = new Actions(driver);
		actions.moveToElement(el).click().sendKeys(Keys.BACK_SPACE).build().perform();
	}

	protected void sendControlKeysUsingAction(WebElement el, String key) {
		Actions action = new Actions(driver);
		if (key.equalsIgnoreCase("a")) {
			action.keyDown(Keys.CONTROL).sendKeys(String.valueOf('\u0041')).perform();
			action.keyUp(Keys.CONTROL);
		} else if (key.equalsIgnoreCase("c")) {
			/*
			 * action.keyDown(Keys.CONTROL).sendKeys(String.valueOf('\u0043')).
			 * perform(); action.keyUp(Keys.CONTROL);
			 */
			action.keyDown(Keys.CONTROL).sendKeys("c").keyUp(Keys.CONTROL);
			action.perform();
		}
	}

	protected void selectPartOfProvidedTextFromDropdown(List<WebElement> dropdown, String text) {
		for (WebElement e : dropdown) {
			if (e.getText().contains(text)) {
				e.click();
				break;
			}
		}
	}

	protected void scrollDown(WebElement element) {
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
	}

	protected void scrollToPageLength() {
		((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight)");
	}

	protected void scrollToTop() {
		((JavascriptExecutor) driver).executeScript("window.scrollTo(0, -document.body.scrollHeight)");
		logInfoMessage("Scrolled to top");
	}

	protected void hoverClick(WebElement element) {
		Actions hoverClick = new Actions(driver);
		hoverClick.moveToElement(element).click().build().perform();
	}

	protected void click(WebElement element) {
		try {
			wait.waitForElementToBeVisible(element);
			scrollDown(element);
			element.click();
		} catch (StaleElementReferenceException ex1) {
			wait.waitForElementToBeVisible(element);
			scrollDown(element);
			element.click();
			logInfoMessage("Clicked Element " + element + " after catching Stale Element Exception");
		} catch (Exception ex2) {
			logInfoMessage("Element " + element + " could not be clicked! " + ex2.getMessage());
		}
	}

	public void hardWait(int seconds) {
		try {
			Thread.sleep(seconds * 1000);
		} catch (InterruptedException ex) {
			ex.printStackTrace();
		}
	}

	public void switchToNextTab() {
		Actions action = new Actions(driver);
		action.keyDown(Keys.CONTROL).keyDown(Keys.TAB).build().perform();
	}

	// public void waitForAlertToAppear() {
	// WebDriverWait wait = new WebDriverWait(driver, 5);
	// wait.until(ExpectedConditions.alertIsPresent());
	// }

	public void switchToPreviousTab() {
		Actions action = new Actions(driver);
		action.keyDown(Keys.CONTROL).keyDown(Keys.SHIFT).sendKeys(Keys.TAB).build().perform();
	}

	public void clickUsingJS(WebElement element) {
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		try {
			executor.executeScript("arguments[0].click();", element);

		} catch (StaleElementReferenceException sre) {
			System.out.println("Stale Exeception handled.");
			wait.hardWait(1);

		} // end of catch
	}

	public void CloseOtherWindows() {
		Set<String> windows = driver.getWindowHandles();
		System.out.println("Windows size before: " + windows.size());
		if (windows.size() > 1) {
			driver.close();
		}
		driver.switchTo().window("0");

	}

	public void changeWindow(int i) {
		Set<String> windows = driver.getWindowHandles();
		System.out.println("Windows size before: " + windows.size());
		if (i > 0) {
			for (int j = 0; j < 9; j++) {
				// System.out.println("Windows size before: " + windows.size());
				if (windows.size() >= 2) {
					try {
						Thread.sleep(5000);
					} catch (Exception ex) {
						ex.printStackTrace();
					}
					break;
				}
				windows = driver.getWindowHandles();
			}
		}
		// System.out.println("Windows size after: " + windows.size());
		String wins[] = windows.toArray(new String[windows.size()]);
		driver.switchTo().window(wins[i]);
		hardWait(2);
		logInfoMessage("After switching windows, new window title: " + driver.switchTo().window(wins[i]).getTitle());
	}

	public void waitForWindowToChaneUntilTitle(int i, String title) {
		Set<String> windows = driver.getWindowHandles();
		System.out.println("Windows size before: " + windows.size());

		if (i > 0) {
			for (int j = 0; j < 9; j++) {
				// System.out.println("Windows size before: " + windows.size());
				if (windows.size() >= 2) {
					try {
						Thread.sleep(5000);
					} catch (Exception ex) {
						ex.printStackTrace();
					}
					break;
				}
				windows = driver.getWindowHandles();
			}
		}
		// System.out.println("Windows size after: " + windows.size());
		String wins[] = windows.toArray(new String[windows.size()]);
		driver.switchTo().window(wins[i]);
		WebDriverWait wait = new WebDriverWait(driver, 5);
		wait.until(ExpectedConditions.titleContains(title));
		windows = driver.getWindowHandles();
		System.out.println("Windows size later: " + windows.size());
		logInfoMessage("After switching windows, new window title: " + driver.switchTo().window(wins[i]).getTitle());
	}

	public void verifyNewWindowIsLaunched(int i) {
		Set<String> windows = driver.getWindowHandles();
		System.out.println("Windows size before: " + windows.size());
		if (i > 0) {
			System.out.println("Windows size before: " + windows.size());
			windows = driver.getWindowHandles();
		}
		System.out.println("Windows size after: " + windows.size());
		String wins[] = windows.toArray(new String[windows.size()]);
		driver.switchTo().window(wins[i]);
		logInfoMessage("After switching windows, new window title: " + driver.switchTo().window(wins[i]).getTitle());
	}

	public void dragNDrop(WebElement From, WebElement To) {
		Actions act = new Actions(driver);
		act.dragAndDrop(From, To).perform();

	}

	public void dragNDropBy(WebElement From, int x_offset, int y_offset) {
		Actions act = new Actions(driver);
		act.dragAndDropBy(From, x_offset, y_offset).perform();

	}

	public void waitElementTextToChangeInto(WebElement ele, String text) {
		for (int i = 1; i <= 30; i++) {
			try {
				if (ele.getText().equalsIgnoreCase(text))
					break;
				else
					wait.hardWait(1);
				continue;
			} catch (StaleElementReferenceException stle) {
				System.out.println("in the catch :" + i + "th time");
				wait.hardWait(5);
				System.out.println("after waiting for " + i + "th time text is " + ele.getText());
				wait.hardWait(2);
				if (ele.getText().equalsIgnoreCase(text))
					break;
				else
					wait.hardWait(1);
				continue;
			}
		}
	}

	public void dragNDropByoffset(WebElement From, WebElement To) {
		Actions act = new Actions(driver);
		act.moveToElement(From, 0, 0).clickAndHold().perform();
		// act.moveByOffset(10, 10);
		wait.hardWait(2);
		act.moveToElement(To).perform();
		wait.hardWait(2);
		// act.moveByOffset(element("span3_questionTemplateAssignment").getLocation().getX(),element("span3_questionTemplateAssignment").getLocation().getY()).perform();
		int off_x = (To.getLocation().getX()) - (From.getLocation().getX());
		int off_y = (To.getLocation().getY()) - (From.getLocation().getY());
		act.moveByOffset(off_x, off_y);
		wait.hardWait(2);

		act.moveToElement(To, 0, 0).release().perform();
	}

	public void dragNDropByCoordinates(WebElement From, int x_offset, int y_offset) {
		Actions act = new Actions(driver);
		act.moveToElement(From, 0, 0).clickAndHold().perform();
		wait.hardWait(2);
		// act.moveToElement(To).perform();
		wait.hardWait(2);
		act.moveByOffset(x_offset, y_offset).perform();

		// act.moveByOffset(x_offset,y_offset);
		wait.hardWait(2);

		act.moveToElement(From, x_offset, y_offset).release().perform();

		// Action rotateText = builder.clickAndHold(From)
		/*
		 * act.clickAndHold(From).moveByOffset(x_offset, y_offset)
		 * .release(From) .build(); act.perform();
		 */
	}

	public void dragNDropMove(WebElement From, WebElement To) {
		Actions builder = new Actions(driver);
		Actions action = new Actions(driver);
		action.dragAndDrop(From, To).perform();
		/*
		 * builder.clickAndHold(From) .moveToElement(To) .release(To)
		 * .build().perform();
		 */

	}

	public static boolean isTimeStampValid(String inputString, String dateformat) {
		SimpleDateFormat format = new java.text.SimpleDateFormat(dateformat);
		try {
			format.parse(inputString);
			return true;
		} catch (ParseException e) {
			return false;
		}
	}

	public void refreshWindow() {
		driver.navigate().refresh();
	}

	public void resizeWindow(Dimension d) {
		driver.manage().window().setSize(d);
	}

	public void fullScreenwindow() {
		driver.manage().window().maximize();
	}

	public String getpagesource() {
		return driver.getPageSource();
	}

	public void ExtractLogs() {
		LogEntries logEntries = driver.manage().logs().get(LogType.BROWSER);
		for (LogEntry entry : logEntries) {
			System.out.println(new Date(entry.getTimestamp()) + " " + entry.getLevel() + " " + entry.getMessage());
		}
	}

	// Methods for MOBILE APP
	protected void clickElementOnApp(WebElement el) {
		TouchAction touchAction = new TouchAction((AndroidDriver) (driver));
		touchAction.tap(el).perform();
	}

	protected void sendKeysUsingTouchAction(WebElement el) {
		// TouchAction touchAction = new TouchAction((AndroidDriver)(driver));
		// touchAction.moveTo(el).
	}

	protected void tap(WebElement el) {
		TouchAction touchAction = new TouchAction((AndroidDriver) (driver));
		touchAction.tap(el).perform();
	}

	protected void sendKeyCodeOfMobileKeypad(int keyCode) {
		((AndroidDriver) driver).pressKeyCode(keyCode);
	}

	// public void getColorOfAnElement(WebElement element){
	// String color = executeJavaScript("return
	// window.getComputedStyle("+element+").getPropertyValue('background-color');");
	//
	// }

	protected void doubleTap(WebElement el) {
		TouchAction touchAction = new TouchAction((AndroidDriver) (driver));
		touchAction.tap(el).tap(el).perform();
	}

	public String verifyLink(WebElement link, String linkName) {
		URL url;
		String message = "";
		try {
			url = new URL(link.getAttribute("href"));
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.connect();
			int code = connection.getResponseCode();
			if (code == 401)
				Reporter.log("[401]: " + linkName);
			else
				Assert.assertTrue(code < 400 || code == 403, "Broken link: " + linkName);
			message = "Info :: " + linkName;
		} catch (ConnectException ex) {
			Reporter.log("Error Reference is: " + linkName);
			Assert.fail("Link is broken");
			message = "Error :: " + linkName;
		} catch (Exception e) {
			message = "Error :: " + linkName;
		}
		return message;
	}

}
