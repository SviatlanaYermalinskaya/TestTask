package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MainCarPage extends Page{
	
	@FindBy(id="ss_origin")
    private WebElement searchPlace;
	
    @FindBy(xpath = ".//button[@data-sb-id=\"main\"]")
    private WebElement buttonSearch;
    
    @FindBy(xpath = "(.//li[contains(@class,\"c-autocomplete\")])[1]")
    private WebElement firstAutocompleteItem;
    
    @FindBy(xpath = "(.//ul[@aria-label=\"Список рекомендуемых направлений\"])[1]")
    private WebElement autocompleteList;
    

	public MainCarPage(WebDriver driver) 
	{
		super(driver);
	}
	
	public SearchCarPage searchByPlace(String text)
	{
		// осталось активным предыдущее окно, переключаем вручную с закрытием первого
		switchToRequiredWindow("Недорогая аренда автомобилей");

		type(searchPlace, text);
		isElementPresent(autocompleteList);
		firstAutocompleteItem.click();
		buttonSearch.click();

		return new SearchCarPage(driver);
	}


	
}
