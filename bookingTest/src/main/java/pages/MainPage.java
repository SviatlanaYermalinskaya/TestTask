package pages;

import java.util.List;
import java.util.Set;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class MainPage extends Page{
	public static final String MAIN_URL = "https://www.booking.com/";
	
	@FindBy(id = "ss")
    private WebElement searchPlace;
	
    @FindBy(xpath = ".//div[@class=\"xp__dates-inner xp__dates__checkin\"]")
    private WebElement datePicker;
    
    @FindBy(xpath = ".//td[@class=\"bui-calendar__date\"]")
    private List<WebElement> calendarDatesAfterToday;
    
    @FindBy(xpath = ".//button[@data-sb-id=\"main\"]")
    private WebElement searchButton;
    
    @FindBy(xpath = ".//span[@class=\"xpb__link__text\"]")
    private WebElement rentCar;

	public MainPage(WebDriver driver) 
	{
		super(driver);
	}
	
	public SearchPlacePage searchByPlace(String text)
	{
		type(searchPlace, text);
		searchPlace.submit();
		return new SearchPlacePage(driver);
	}
	
	public SearchPlacePage searchByPlaceDates(String text, int inner, int checkin)
	{
		type(searchPlace, text);
		datePicker.click();
		calendarDatesAfterToday.get(inner - 1).click();
		calendarDatesAfterToday.get(checkin - 2).click();
		searchButton.click();
		return new SearchPlacePage(driver);
	}
	
	public MainCarPage chooseRentCar()
	{
		rentCar.click();
		return new MainCarPage(driver);
	}
	

}
