package com.guru9Project9.com;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.util.Date;

import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.guru99Project.Account;
import com.guru99Project.CustomerPage;
import com.guru99Project.HomePage;
import com.guru99Project.LoginPage;

import commonLibs.implementations.CommonDriver;
import commonLibs.implementations.ScreenshotControl;
import in.amazon.pages.Homepage;

public class BaseTest {

	CommonDriver cmnDriver;

	LoginPage loginPage;
	HomePage homePage;
	CustomerPage customerPage;
	Account accountPage;

	ScreenshotControl screenshotControl;

	ExtentHtmlReporter htmlReporter;
	ExtentReports extentReport;
	ExtentTest extentTest;

	String executionStartTime;
	String currentWorkingDirectory;
	String reportFilename;

	String browserType;
	String baseUrl;

	WebDriver driver;

	@BeforeSuite
	public void beforeSuite() {
		initializeExcutionStartTime();
		initializeCurrentWorkingDirectory();
		initializeExtentReport();

	}

	@BeforeClass
	public void setup() throws Exception {

		extentTest = extentReport.createTest("TC - Setting up the tests");

		invokeBrowser();
		initializeScreenshotVaraiable();

	}

	@AfterClass
	public void cleanup() throws Exception {
		extentTest = extentReport.createTest("TC - Clearing up the tests");
		extentTest.log(Status.INFO, "Closing the browser: ");

		cmnDriver.closeAllBrowsers();
	}

	@AfterMethod
	public void afterATestcase(ITestResult result) throws Exception {

		String testcaseName = result.getName();
		String filename = String.format("%s/screenshots/%s_%s.jpeg", currentWorkingDirectory, testcaseName,
				executionStartTime);

		if (result.getStatus() == ITestResult.FAILURE) {
			screenshotControl.captureAndSaveScreenshot(filename);

			extentTest.log(Status.FAIL, "Failed testcase: " + testcaseName);
			extentTest.addScreenCaptureFromPath(filename);
		} else if (result.getStatus() == ITestResult.SKIP)

		{
			extentTest.log(Status.SKIP, "Skipprd testcase: " + testcaseName);

		}
	}

	private void initializeExtentReport() {

		extentReport = new ExtentReports();
		extentReport.attachReporter(htmlReporter);
	}

	private void initializeCurrentWorkingDirectory() {
		currentWorkingDirectory = System.getProperty("user.dir");

		reportFilename = String.format("%s/reports/Guru99Report_%s.html", currentWorkingDirectory, executionStartTime);
		htmlReporter = new ExtentHtmlReporter(reportFilename);

	}

	private void initializeExcutionStartTime() {
		Date date = new Date();
		executionStartTime = String.valueOf(date.getTime());

	}

	private void initializeScreenshotVaraiable() {
		screenshotControl = new ScreenshotControl(driver);

	}

	private void invokeBrowser() throws Exception {
		browserType = "chrome";

		extentTest.log(Status.INFO, "Initializing Browser: " + browserType);

		cmnDriver = new CommonDriver("chrome");

		int pageloadtimeout = 20;
		extentTest.log(Status.INFO, "Pageload Timeout: " + pageloadtimeout);
		cmnDriver.setPageloadTimeout(pageloadtimeout);

		int implicitWait = 10;
		extentTest.log(Status.INFO, "Pageload Timeout: " + implicitWait);
		cmnDriver.elementDetectionTimeout(implicitWait);

		baseUrl = "http://demo.guru99.com/v4";
		cmnDriver.navigateToFirstUrl(baseUrl);

		driver = cmnDriver.getDriver();

		loginPage = new LoginPage(driver);
		homePage = new HomePage(driver);
		customerPage = new CustomerPage(driver);
		accountPage = new Account(driver);

	}

	@AfterSuite
	public void postCleanup() {
		extentReport.flush();
	}

}
