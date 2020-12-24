package com.visionit.orangehrm.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.visionit.orangehrm.utilities.Helper;

public class AddUserPage {
	@FindBy(id="btnAdd")
	@CacheLookup
	WebElement btnAdd;
	@FindBy(id="systemUser_userType")
	@CacheLookup
	WebElement System_userType;
	@FindBy(id="systemUser_employeeName_empName")
	@CacheLookup
	WebElement System_user_Empname;
	@FindBy(id="systemUser_userName")
	@CacheLookup
	WebElement System_userName;
	@FindBy(id="systemUser_status")
	@CacheLookup
	WebElement System_userStatus;
	@FindBy(id="systemUser_password")
	@CacheLookup
	WebElement System_userPassword;
	@FindBy(id="systemUser_confirmPassword")
	@CacheLookup
	WebElement System_userConfirm;
	@FindBy(id="btnSave")
	@CacheLookup
	WebElement btnSave;
 WebDriver driver;
 public AddUserPage(WebDriver driver)
 {
	 this.driver=driver;
	 PageFactory.initElements(driver, this);
 }
 public void AddNewUser(String selectrole,String employeeName,String UserName,String Status,String password,String confirmPassword)
 {
	 try {
		 btnAdd.click();
		 Helper.selectDropDownValue( System_userType,selectrole);
		 System_user_Empname.sendKeys(employeeName);
		 System_user_Empname.sendKeys(UserName);
		 Helper.selectDropDownValue(System_userStatus, Status);
		 System_userPassword.sendKeys(password);
		 System_userConfirm.sendKeys(confirmPassword);
		 btnSave.click();
	 }
	 catch(Exception e)
	 {
		 
	 }
 }
 
}
