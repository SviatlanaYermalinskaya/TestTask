package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CarPage extends Page{

	@FindBy(id = "addPolicyButton")
    private WebElement buttonRent;
	
	public CarPage(WebDriver driver) 
	{
		super(driver);
	}
	
	public CarRentalPage rentCar()
	{
		if (!isElementPresent(buttonRent))
		{
			switchToLastWindow();
		}
		buttonRent.click();	
		return new CarRentalPage(driver);
	}
	
}
