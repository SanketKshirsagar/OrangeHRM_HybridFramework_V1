package com.visionit.orangehrm.testBase;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.visionit.orangehrm.utilities.ConfigDataProvider;
import com.visionit.orangehrm.utilities.Helper;
import com.visionit.orangehrm.utilities.XLSXDataProvider;

public class TestBase {
	public static WebDriver driver=null;
	public static ConfigDataProvider configdata;
	public static XLSXDataProvider xlsx;
	public ExtentHtmlReporter htmlReporter;
	public ExtentReports reports;
	public ExtentTest test;
	
	@BeforeSuite
	public void setupSuite()
	{
		configdata=new ConfigDataProvider();
		xlsx=new XLSXDataProvider();
	}
	@BeforeTest
	public void setupExtent()
	{
		File fs	= new File("./Reports/orange_hrm.html");
		htmlReporter =new ExtentHtmlReporter(fs);
		htmlReporter.config().setDocumentTitle("Automation Report");
		htmlReporter.config().setReportName("Funtional Report");
		htmlReporter.config().setTheme(Theme.DARK);
		reports =new ExtentReports();
		reports.attachReporter(htmlReporter);
		reports.setSystemInfo("Hostname","LocalHost");
		reports.setSystemInfo("OS", "Windows");
		reports.setSystemInfo("Tester Name", "Sanket");
		reports.setSystemInfo("Browser", "Chrome");
		
	}
	@AfterTest
	public void endReport()
	{
		reports.flush();
	}
	@Parameters("Browser")
	@BeforeMethod
	public void suit(@Optional("Chrome")String browsername)
	{
		if(browsername.equals("Chrome"))
		{
			System.setProperty("webdriver.chrome.driver", "./Drivers/chromedriver.exe");
			driver=new ChromeDriver();
		
		}
		else if(browsername.equals("Firefox")) {
			System.setProperty("webdriver.gecko.driver","./Drivers/geckodriver.exe");
			 driver=new FirefoxDriver();
			}
		else if(browsername.equals("IE"))
		{
			System.setProperty("webdriver.ie.driver","./Drivers/IEDriverServer.exe");
			driver= new InternetExplorerDriver();
	
		}
		
		
       // driver.manage().timeouts().pageLoadTimeout(5, TimeUnit.SECONDS);
		driver.get(configdata.getUrl());
		
		driver.manage().window().maximize();
		//driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		}
	@AfterMethod
	public void tearDown(ITestResult result) throws IOException
	{
		if(result.getStatus()==ITestResult.FAILURE)
		{
			test.log(Status.FAIL, "TestCaseFailed"+result.getName());
			test.log(Status.FAIL, "TestCaseFailed"+result.getThrowable());
			String screenshotpath=Helper.captureScreenshot(result.getName(),driver);
			test.addScreenCaptureFromPath(screenshotpath);
		}
		else if(result.getStatus()==ITestResult.SUCCESS)
		{
			test.log(Status.PASS, "TestCasePassed"+result.getName());
		}
		else if(result.getStatus()==ITestResult.SKIP)
		{
			test.log(Status.SKIP, "TestCaseSkipped"+result.getName());
		}
		driver.quit();
	}

}
