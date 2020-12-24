 package com.visionit.orangehrm.testCases;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.visionit.orangehrm.page.AddUserPage;
import com.visionit.orangehrm.page.HomePage;
import com.visionit.orangehrm.page.LoginPage;
import com.visionit.orangehrm.testBase.TestBase;

public class AddUser_002 extends TestBase{
	
	
	@Test(dataProvider="getExcelData")
	public void AddNewUserTest(String user_role,String empName,String userName,String status,
			String password,String confirmpassword)
	{
		LoginPage login = new LoginPage(driver);
		//login.loginorangehrm(username, userpass);
		HomePage homepage=login.loginorangehrm(xlsx.getStringCellData("login", 0, 0),xlsx.getStringCellData("login", 0, 1) );
	     AddUserPage adduser = homepage.navigateToAddUserPage();
	     adduser.AddNewUser(user_role, empName, userName, status, password, confirmpassword);
	
	
	}
	@DataProvider
	public Object[][] getExcelData()
	{
		Object [][]data=xlsx.excelTestData("addUser");
		return data;
	}

}
