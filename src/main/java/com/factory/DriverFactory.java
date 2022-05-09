package com.factory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverFactory { // acting as base class
	
	public WebDriver driver;
	
	//threadLocal is used as part of parallel execution so that each thread doesnt inteupts another thread operations
	public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<>();
	
	public WebDriver init_driver(String browser)
	{
		System.out.println("Browser is: "+browser);
		if(browser.equals("chrome"))
		{
			WebDriverManager.chromedriver().setup();
			tlDriver.set(new ChromeDriver());   //here we are setting up the driver of browser inside a threadlocal driver used by indivdual threads
		}
		else if(browser.equals("firefox"))
		{
			WebDriverManager.firefoxdriver().setup();
			tlDriver.set(new FirefoxDriver());
		}
		else
		{
			System.out.println("Enter a valid browser: "+browser);
		}
		
		getDriver().manage().deleteAllCookies();
		getDriver().manage().window().maximize();
		return getDriver();
	}
	
	public static synchronized WebDriver getDriver()   //here we are returning the webdriver coz threadlocal is initialized with webdriver
	{												   //all the threads calling this webdriver must in synch so used synchronized
		return tlDriver.get();
	}

}
