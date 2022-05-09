package com.pages;

import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.factory.DriverFactory;

public class AccountPage {
	
	private WebDriver driver;
	
	@FindBy(css = "div#center_column div div ul li")
	private List<WebElement> accountSection;
	
	public AccountPage(WebDriver driver)
	{
		this.driver = DriverFactory.getDriver();
		PageFactory.initElements(driver,this);
	}
	
	public int getCountOfAccountSectn()
	{
		return accountSection.size();
	}
	
	public String getAccountTitlePage()
	{
		return driver.getTitle();
	}

	
	public List<String> getAccountSectnList()
	{
		List<String> list = new ArrayList<>();
		for(WebElement e : accountSection)
		{
			String text = e.getText();
			System.out.println(text);
			list.add(text);
		}
		return list;
	}

}
