package com.demoExtentReports;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.io.FileHandler;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentAventReporter;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import freemarker.template.SimpleDate;

public class OrangeHrmTest {
	public ExtentHtmlReporter htmlReporter;
	public ExtentReports reports;
	public ExtentTest logger;
	public static WebDriver driver;
	
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
	@BeforeMethod
	public void setup()
	{
		System.setProperty("webdriver.chrome.driver", "./Drivers/chromedriver.exe");
		driver=new ChromeDriver();
		driver.get("https://opensource-demo.orangehrmlive.com/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		
	}
	@Test
   public void  verifyTitleTest()
   {
	   logger=reports.createTest("verifyTitleTest");
	   String actualTitle=driver.getTitle();
	   String ExpectedTitle="OrangeHRM";
	   Assert.assertEquals(actualTitle, ExpectedTitle);
       	    }
	@Test
	public void verifyLogoTest()
	{
		logger =reports.createTest("verifylogoTest");
		boolean status=driver.findElement(By.xpath("//*[@id=\"divLogo\"]/img")).isDisplayed();
		Assert.assertFalse(status);
	}
	@AfterMethod
	public void tearDown(ITestResult result) throws IOException
	{
		if(result.getStatus()==ITestResult.FAILURE)
		{
			logger.log(Status.FAIL, "TestCaseFailed"+result.getName());
			logger.log(Status.FAIL, "TestCaseFailed"+result.getThrowable());
			String screenshotpath=captureScreenshot(result.getName());
			logger.addScreenCaptureFromPath(screenshotpath);
		}
		else if(result.getStatus()==ITestResult.SUCCESS)
		{
		 logger.log(Status.PASS, "TestCasePassed"+result.getName());
		}
		else if(result.getStatus()==ITestResult.SKIP)
		{
		 logger.log(Status.SKIP, "TestCaseSkipped"+result.getName());
		}
		driver.quit();
	}
	public static String captureScreenshot(String screenshotName) throws IOException
	{
		String dateformat=new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		TakesScreenshot ts=(TakesScreenshot)driver;
		File srcFile=ts.getScreenshotAs(OutputType.FILE);
		String screenshotPath="./Screenshot"+screenshotName+dateformat+".png";
		File destFile=new File(screenshotPath);
		FileHandler.copy(srcFile, destFile);
		
		return screenshotPath;
	}
}
