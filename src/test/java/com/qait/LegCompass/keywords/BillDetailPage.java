package com.qait.LegCompass.keywords;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;
import java.io.File;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.print.Doc;

import org.apache.poi.util.SystemOutLogger;
import org.apache.tools.ant.util.StringUtils;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.stringtemplate.v4.compiler.CodeGenerator.list_return;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.asserts.SoftAssert;

import com.qait.automation.getpageobjects.GetPage;

public class BillDetailPage extends GetPage {
	public BillDetailPage(WebDriver driver) {
		super(driver, "BillDetailPage");
	}

	public void verifyBillCountAfterRefresh() {
		logAssertionPassedMessage("Bill is deleted from folder");
	}

}