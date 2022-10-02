package com.firebasetest.tests;
import com.aventstack.extentreports.reporter.configuration.Theme;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class TestExtent {
	public static ExtentHtmlReporter htmlReporter;
	public static ExtentReports extent;
	public static ExtentTest logger;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		htmlReporter=new ExtentHtmlReporter("./extentReport.html");
		extent=new ExtentReports();
		
		htmlReporter.config().setDocumentTitle("Test Execution Report");
		htmlReporter.config().setReportName("fire base regresion tests");
		htmlReporter.config().setTheme(Theme.STANDARD);
		
		extent.attachReporter(htmlReporter);
		extent.setSystemInfo("Host Name", "Salesforce");
		extent.setSystemInfo("Environment", "Automation Testing");
		extent.setSystemInfo("User Name", "Amrutha");
		
		logger=extent.createTest("test1");
		
		logger.log(Status.INFO,"info message");
		logger.log(Status.PASS,MarkupHelper.createLabel("test1"+"is pass Test", ExtentColor.GREEN));
		logger.log(Status.FAIL,MarkupHelper.createLabel("test1"+"is fail Test", ExtentColor.RED));
		extent.flush();
		
		System.out.println("Completed");

	}

}
