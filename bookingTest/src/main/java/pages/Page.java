package pages;

import java.util.Set;

import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class Page {
	public static final int LINK_PRESENSE_TIMEOUT = 15;
	protected WebDriver driver;
	protected WebDriverWait wait;
	
	public Page(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
		wait = new WebDriverWait(driver, LINK_PRESENSE_TIMEOUT);
	}
	
	protected void type(WebElement element, String text)
	{
		element.clear();
		element.sendKeys(text);
	}
	
	public void open(String url)
	{
		driver.get(url);
		driver.manage().window().maximize();
	}
	
	protected boolean isElementPresent(WebElement element)
	{
		try
		{
			return wait.until(ExpectedConditions.visibilityOf(element))
					.isDisplayed();
		}
		catch (TimeoutException e)
		{
			return false;
		}
	}
	
	protected boolean isElementClickable(WebElement element)
	{
		try
		{
			return wait.until(ExpectedConditions.elementToBeClickable(element))
					.isDisplayed();
		}
		catch (TimeoutException e)
		{
			return false;
		}
	}
	
	protected int getPrice(WebElement element)
	{
		String[] parts = element.getText().split("BYN");
		return Integer.valueOf(parts[1].replaceAll("\\D", ""));
	}

	protected void switchToRequiredWindow(String title)
	{
		String currentWindow;
		Set<String> handles = driver.getWindowHandles();
		for (String handle: handles)
		{		
			if (driver.getTitle().contains(title))
			{
				return;
			}	
			currentWindow = driver.getWindowHandle();
			if (!currentWindow.equals(handle))
			{
				driver.close();
				driver.switchTo().window(handle);
			}		
		}
	}
	
	protected void switchToLastWindow()
	{
		String currentWindow;
		Set<String> handles = driver.getWindowHandles();
		for (String handle: handles)
		{		
			currentWindow = driver.getWindowHandle();
			if (!currentWindow.equals(handle))
			{
				driver.switchTo().window(handle);
			}				
		}
	}
}
