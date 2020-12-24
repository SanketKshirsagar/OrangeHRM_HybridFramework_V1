package com.visionit.orangehrm.page;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
	WebDriver driver;
	@FindBy(name="txtUsername")
	WebElement uname;
	@FindBy(name="txtPassword")
	WebElement upass;
	@FindBy(id="btnLogin")
	WebElement Loginbtn;
	public LoginPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
public HomePage loginorangehrm(String username,String userpass)
{
	
	uname.sendKeys(username);

	upass.sendKeys(userpass);
	
	Loginbtn.click();
	return new HomePage(driver);
	
	
}
}
