package com.firebase.basetest.test;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import com.firebase.test.utility.CommonUtilities;
import com.firebase.test.utility.Constants;
import com.firebase.test.utility.GenerateReports;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Base extends CommonUtilities {

	public static String mainWindowHandle;

	public static WebDriver driver = null;
	public static WebDriverWait wait = null;
	public static GenerateReports report = null;

	public static void setDriver(String browser) {
		switch (browser) {
		case "edge":
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
			report.logTestInfo("Initiating the edge driver");
			break;
		case "chrome":
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			report.logTestInfo("Initiating the chrome driver");
			break;
		}
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	@BeforeTest
	public static void setupBeforeTest() {
		report = GenerateReports.getInstance();
		report.startExtentReport();
		System.out.println("Executig the report iniatiation code!!!");
	}

	@Parameters({ "browsername" })
	@BeforeMethod
	public static void openWebDriver(String browsername, Method m) {
		// String browsername="chrome";
		System.out.println("Just entered openWeb");
		report.startSingleTestReport(m.getName());
		report.logTestInfo("browsername is " + browsername);
		report.logTestInfo("before method execution started");
		setDriver(browsername);
		CommonUtilities CU = new CommonUtilities();
		Properties applicationPropertiesFile = CU.loadFile("Data");
		report.logTestInfo(CU.getApplicationProperty("url", applicationPropertiesFile));
		driver.get(CU.getApplicationProperty("url", applicationPropertiesFile));
		report.logTestInfo("loginSalesForce: Just opend the url");

	}

	@AfterMethod
	public static void tearDown() {

		report.logTestInfo("after method execution is started");
		// captureWindowScreenshot();
		closeBrowser();

	}

	public static void clickElement(String elementId, String objName) {
		report.logTestInfo("Trying to click : " + objName);
		WebElement element = createWebElement(elementId);

		if (element.isDisplayed()) {
			element.click();
			report.logTestInfo("Pass : " + objName + "Clicked ");
		} else {
			report.logTestFailed("Failed!!  " + objName + "element not clicked");
		}

	}

	public static void closeunimportantPoPupWindows(String elementId, String objName) {
		// WebElement element = createWebElement(elementId);
		for (String winhandle : driver.getWindowHandles()) {
			driver.switchTo().window(winhandle);
			System.out.println("Window Switch");
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			// driver.findElement(By.xpath("(//button[span[contains(text(),'Close')]])[1]")).click();
			driver.findElement(By.xpath(elementId)).click();
			System.out.println("pop up window is closed");
		}
	}

	public static void enterText(String elementId, String text, String objName) {
		WebElement element = createWebElement(elementId);
		if (element.isDisplayed()) {

			element.clear();
			element.sendKeys(text);
			report.logTestInfo("Text entered in  " + objName + "feild ");

		} else {

			report.logTestFailed("Failed!!  " + objName + "element not dispalyed");
		}
	}

	public static void validateText(String elementId, String expectedtext) {
		WebElement element = createWebElement(elementId);
		String objName = expectedtext;
		String actualtext = element.getText();
		if (actualtext.equals(expectedtext))
			report.logTestInfo("pass :" + objName + "validation success");
		else
			report.logTestFailed("Failed!!  " + objName + "validation failed");

	}

	public static void waitUntilPageLoads() {
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
	}

	public static void moveToElement(String element, String objName) {
		waitUntilVisibilityof(element, objName);
		Actions action = new Actions(driver);
		WebElement webElement = createWebElement(element);
		action.moveToElement(webElement).build().perform();
		report.logTestInfo("Moved to " + objName);
	}

	public static void waitUntilVisibilityof(String elementId, String objName) {
		WebElement element = driver.findElement(By.id(elementId));

		wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		wait.until(ExpectedConditions.visibilityOf(element));
		report.logTestInfo(objName + "waited for 15 sec");
	}

	public static void waitUntilVisibilityof(By locator, String objName) {
		wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		wait.until(ExpectedConditions.visibilityOfElementLocated(locator));

	}

	public static void waitUntilVisibile(String elementId, String objName) {
		WebElement element = createWebElement(elementId);
		wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		wait.until(ExpectedConditions.visibilityOf(element));

	}

	public static void waitUntilAlertIsPresent() {

		wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		wait.until(ExpectedConditions.alertIsPresent());

	}

	public static WebElement createWebElement(String element) {
		WebElement webelement;
		if (element.contains("//") || element.contains("(//")) {
			webelement = driver.findElement(By.xpath(element));
			report.logTestInfo("found by xpath");

		} else {
			webelement = driver.findElement(By.id(element));
			report.logTestInfo("found by elementId");
		}
		return webelement;
	}

	public static String captureWindowScreenshot() {
		String path = "";
		try {

			File source = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			Calendar currentDate = Calendar.getInstance();
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MMM/dd HH:mm:ss");
			String dateN = formatter.format(currentDate.getTime()).replace("/", "_");
			String dateNow = dateN.replace(":", "_");
			String targetImageName = Constants.SCREENSHOT_PATH + "/" + dateNow + ".png";
			report.logTestInfo("targetImageName: " + targetImageName);

			File f = new File(Constants.SCREENSHOT_PATH + "/");

			path = f.getAbsolutePath() + "/" + source.getName();
			FileUtils.copyFile(source, new File(targetImageName));
			path = targetImageName;

		} catch (IOException e) {
			path = "Failed to capture screenshot: " + e.getMessage();
		}
		return path;
	}

	@AfterTest
	public static void tearDownAfterTest() {
		report.logTestInfo("after method execution is completed");
		report.endReport();

	}

	public static void goToUrl(String url) {
		driver.get(url);
		report.logTestInfo("Opened url:" + url);
	}

	public static void closeBrowser() {
		driver.close();
		report.logTestInfo("Browsser is closed");
	}

	public static void closeAllBrowser() {
		driver.quit();
		report.logTestInfo("All Browsser are closed");
	}

	public static void loginToSalesForce() {
		CommonUtilities CU = new CommonUtilities();
		Properties applicationProperties = CU.loadFile("Data");
		enterText("username", CU.getApplicationProperty("valid_username", applicationProperties), "UserName");
		waitUntilVisibilityof("username", "UserName");
		enterText("password", CU.getApplicationProperty("valid-pwd", applicationProperties), "Password");
		clickElement("Login", "Login");

	}

	public static String retrieveText(String elementId, String objName) {
		WebElement element = createWebElement(elementId);
		String str = element.getText();
		return str;
	}

	public static void switchToIframe(String elementId, String objName) {
		WebElement element = createWebElement(elementId);
		driver.switchTo().frame(element);
		report.logTestInfo("Driver is in iframe" + objName);
	}

}
