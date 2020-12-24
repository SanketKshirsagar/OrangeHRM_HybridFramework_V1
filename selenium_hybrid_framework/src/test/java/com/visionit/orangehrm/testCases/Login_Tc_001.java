package com.visionit.orangehrm.testCases;



import java.util.concurrent.TimeUnit;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.visionit.orangehrm.page.HomePage;
import com.visionit.orangehrm.page.LoginPage;
import com.visionit.orangehrm.testBase.TestBase;

public class Login_Tc_001 extends TestBase {
@Test
public void loginorangehrmTest()
{
	LoginPage login=new LoginPage(driver);

	//login.loginorangehrm(configdata.getUsername(),configdata.getUserpassword());
	test=reports.createTest("loginorangehrmTest");
	
	test.info("Navigated on the login page");
	login.loginorangehrm(xlsx.getStringCellData("login", 0, 0),xlsx.getStringCellData("login", 0, 1) );
    test.info("Enter valid user name and password and click on login btn");
	String actualTitle =driver.getTitle();
     String expectedTitle="";
     if(expectedTitle.equals(driver.getTitle()))
     {
    	 Assert.assertTrue(true);
    	 test.pass("login page title is verified");
     }
     else {
    	 test.fail("login page title is not macthed");
    	 Assert.assertTrue(false);
    	 
     }
}

}
