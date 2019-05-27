package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;

public class BaseTest {
	
	//protected static WebDriver driver; 
	protected WebDriver driver; // чтобы запускался только один драйвер поменять поменять функционал на закомментированное
	
	protected WebDriver getWebDriver()
	{
		driver = new ChromeDriver();
		/*
		if (driver == null)
		{
			driver = new ChromeDriver();
		}
		*/
		return driver;
	}
	
	@AfterMethod
	public void closeWebDriver()
	{
		driver.quit();
		/*
		if (driver != null)
		{
			driver.quit();
	        driver = null;
		}
		*/
	}

}
