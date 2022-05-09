package com.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.factory.DriverFactory;

public class loginPage {
	
	private WebDriver driver;
	
	@FindBy(id = "email")
	private WebElement emailId;
	
	@FindBy(id = "passwd")
	private WebElement password;
	
	@FindBy(id = "SubmitLogin")
	private WebElement SignInButton;
	
	@FindBy(linkText = "Forgot your password?")
	private WebElement forgotPwdLink;
	
	public loginPage(WebDriver driver) //constructor
	{
		this.driver = DriverFactory.getDriver();
		PageFactory.initElements(driver, this);
	}
	
	public String getTitle()
	{
		return driver.getTitle();
	}
	
	public boolean isForgetPasswdLinkExists()
	{
		return forgotPwdLink.isDisplayed();
	}
	
	public void enterUserName(String username)
	{
		emailId.sendKeys(username);
	}
	
	public void enterPasswrd(String pwd)
	{
		password.sendKeys(pwd);
	}
	
	
	public void clickLoginBtn()
	{
		SignInButton.click();
	}
	
	//common Method for login
	
	public AccountPage getCredentials(String username, String pwd)
	{
		emailId.sendKeys(username);
		password.sendKeys(pwd);
		SignInButton.click();		
		return new AccountPage(driver);
	}

}
