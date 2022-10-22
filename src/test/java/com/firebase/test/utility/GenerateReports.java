package com.firebase.test.utility;

import java.io.IOException;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.firebase.basetest.test.Base;

public class GenerateReports {
	ExtentHtmlReporter htmlReporter;
	public static ExtentReports extent;
	public static ExtentTest logger;
	private static GenerateReports ob;

	private GenerateReports() {

	}

	public static GenerateReports getInstance() {
		if (ob == null) {
			ob = new GenerateReports();
		}
		return ob;
	}

	public void startExtentReport() {
		htmlReporter = new ExtentHtmlReporter(Constants.GENERATE_REPORT_PATH);
		extent = new ExtentReports();

		extent.attachReporter(htmlReporter);
		extent.setSystemInfo("Host Name", "Salesforce");
		extent.setSystemInfo("Environment", "Automation Testing");
		extent.setSystemInfo("User Name", "Amrutha");

		htmlReporter.config().setDocumentTitle("Test Execution Report");
		htmlReporter.config().setReportName("fire base regresion tests");
		htmlReporter.config().setTheme(Theme.STANDARD);

	}

	public void startSingleTestReport(String testname) {
		logger = extent.createTest(testname);
	}

	public void logTestInfo(String message) {
		logger.log(Status.INFO, message);
	}

	public void logTestPassed(String testcaseName) {
		logger.log(Status.PASS, MarkupHelper.createLabel(testcaseName + " is pass Test", ExtentColor.GREEN));
	}

	public void logTestScreenShot(String testcaseName, String screenShotPath) {

		logger.log(Status.PASS, MarkupHelper.createLabel(testcaseName + "See the Screenshot below", ExtentColor.GREEN));
		try {

			logger.log(Status.PASS, (Markup) logger.addScreenCaptureFromPath(screenShotPath));
		} catch (IOException e) {

			e.printStackTrace();
		}

	}

	public void logTestAssertionFailed(String testcaseName, String screenShotPath) {

		logger.log(Status.FAIL, MarkupHelper
				.createLabel(testcaseName + "Failed the assertion. See the Screenshot below", ExtentColor.RED));
		try {

			logger.log(Status.FAIL, (Markup) logger.addScreenCaptureFromPath(screenShotPath));
		} catch (IOException e) {

			e.printStackTrace();
		}

	}

	public void logTestFailed(String testcaseName) {
		logger.log(Status.FAIL, MarkupHelper.createLabel(testcaseName + "is fail Test", ExtentColor.RED));
	}

	public void logTestSkipped(String testcaseName) {
		logger.log(Status.SKIP, MarkupHelper.createLabel(testcaseName + "is fail Test", ExtentColor.BLUE));
	}

	public void endReport() {
		extent.flush();
	}
}
