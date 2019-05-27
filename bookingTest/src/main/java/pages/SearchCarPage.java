package pages;


import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SearchCarPage extends Page{
	
	@FindBy(id="ss_origin")
    private WebElement searchPlace;
	
    @FindBy(xpath = ".//a[@class=\"show-cars-link select \"]")
    private WebElement buttonShow;
    
    @FindBy(id="js-closeCookieBanner")
    private WebElement closeBanner;
    
	@FindBy(xpath = ".//span[text()=\"Бронировать \"]")
	private List<WebElement> cars;


	public SearchCarPage(WebDriver driver) 
	{
		super(driver);
	}
	
	public void showAvailableCars()
	{
		buttonShow.click();
	}

	public CarPage chooseCar(int number)
	{
		if (isElementPresent(closeBanner))
		{
			closeBanner.click();
		}	
		cars.get(number).click();
		return new CarPage(driver);
	}
	
	public int getAvailableCarsNumber()
	{
		return Integer.valueOf(buttonShow.getText().replaceAll("\\D", ""));
	}
}
