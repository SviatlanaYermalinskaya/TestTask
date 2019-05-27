package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CarRentalPage extends Page{

	@FindBy(id="btn-submit-dd")
    private WebElement buttonRent;
	
	@FindBy(id="errorTop")
    private WebElement errorNotice;
	
	public CarRentalPage(WebDriver driver) 
	{
		super(driver);
	}
	
	public void rentCar()
	{
		buttonRent.click();	
	}
	
	public boolean isErrorNoticePresent()
	{
		return isElementPresent(errorNotice);
	}
	
}
