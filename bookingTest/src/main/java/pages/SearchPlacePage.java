package pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

public class SearchPlacePage extends Page{
	
	@FindBy(xpath = "(//td[@class=\"c2-day c2-day-s-weekend\"])[1]")
	private WebElement dataFirstWeekend;
	
	@FindBy(xpath = ".//td[contains(@class,\"today\")]")
	private WebElement dataToday;
	
	@FindBy(xpath = ".//button[@data-sb-id=\"main\"]")
    private WebElement searchButton;
	
	@FindBy(xpath = "//*[@id=\"filter_out_of_stock\"]/div[2]/a/div/span")
    private WebElement availableOption;
	
	@FindBy(xpath = "//*[@id=\"filter_hoteltype\"]/div[2]/a[3]/div/span")
    private WebElement hostelOption;
	
	@FindBy(xpath = "//*[@id=\"filter_distance\"]/div[2]/a[1]/div/span[1]")
    private WebElement distance1kmOption;
	
	@FindBy(xpath = ".//a[contains(text(),\"Меньше 1 км\")]")
    private WebElement distance1kmFilter;
	
	@FindBy(xpath = ".//div[@role=\"heading\"]")
    private WebElement findResult;
	
	@FindBy(xpath = ".//a[@data-category=\"price\"]")
    private WebElement filterPrice;
	
	@FindBy(xpath = ".//div[contains(@class,\"sr_item  sr_item_new\")]")
	private List<WebElement> hotels;
	
	@FindBy(xpath = ".//button[@id=\"sortbar_dropdown_button\"]")
	private WebElement dots;
	
	@FindBy(xpath = ".//li[@class=\" sort_category   sort_bayesian_review_score \"]")
	private WebElement rating;
	
	@FindBy(id="sr_notice_minimum_number_of_reviews")
    private WebElement ratingNotice;
	
	@FindBy(xpath = ".//div[@class=\"bui-review-score__badge\"]")
    private List<WebElement> hotelRating;
	
	@FindBy(xpath = ".//select[@name=\"group_adults\"]")
	private WebElement adults;
	
	@FindBy(xpath = "//*[@id=\"filter_price\"]/div[2]/a[1]/div")
	private WebElement price0_110;
	
	@FindBy(id = "sr_notice_no_dorms")
    private WebElement noticeNoDorms;
	
	@FindBy(xpath = ".//div[@class=\"bui-price-display__value prco-inline-block-maker-helper \"]")
    private List<WebElement> hotelPrices;
	
	@FindBy(xpath = ".//div[@class=\"bui-u-inline\"]")
    private List<WebElement> searchPages;
	
	@FindBy(xpath = ".//li[@class=\"bui-pagination__item bui-pagination__next-arrow bui-pagination__item--disabled\"]")
    private WebElement pagination;
	
	@FindBy(xpath = "(.//a[@aria-checked=\"true\"]//span[contains(text(),\"только доступные варианты\")])[1]")
	private WebElement checkedAvailables;
	
	public SearchPlacePage(WebDriver driver) 
	{
		super(driver);
	}
	
	public void chooseFirstWeekendArrivalDate()
	{
		dataFirstWeekend.click();
	}
	
	public void chooseTodayArrivalDate()
	{
		dataToday.click();
	}
	
	public void clickSearchButton()
	{
		wait.until(ExpectedConditions.elementToBeClickable(searchButton));
		//isElementClickable(searchButton);
		searchButton.click();	
	}
	
	public void chooseOnlyAvailable()
	{
		availableOption.click();
		isElementPresent(checkedAvailables);
	}

	public void chooseCenter()
	{
		distance1kmOption.click();
		wait.until(ExpectedConditions.elementToBeClickable(distance1kmFilter));	
		//isElementClickable(distance1kmFilter);
	}
	
	public void chooseHostel()
	{
		hostelOption.click();
		
	}
	
	public void filterByPrice()
	{
		filterPrice.click();
		
	}
	
	public int numberOfSearchResults()
	{
		wait.until(ExpectedConditions.visibilityOf(findResult));
		//isElementPresent(findResult);
		String[] parts = findResult.getText().split("вариант");
	    return Integer.valueOf(parts[0].replaceAll("\\D", ""));
	}
	
	public int getFirstHotelPrice()
	{
		return getPrice(hotels.get(0));
	}
	
	public void filterByRating()
	{
		dots.click();
		rating.click();
		//isElementPresent(ratingNotice);
		wait.until(ExpectedConditions.visibilityOf(ratingNotice));
	}
	
	public void openFirstHotel()
	{
		hotels.get(0).click();
	}

	public double getFirstHotelRating()
	{
		return Double.valueOf(hotelRating.get(0).getText().replace(',', '.'));
	}
	
	public boolean setAdults(int personsNumber)
	{
		Select adult_dropdown = new Select(adults);
		if ((personsNumber == 1)||(personsNumber == 21))
		{
			adult_dropdown.selectByVisibleText(String.valueOf(personsNumber) + " взрослый");
			return true;
		}
		else if ((personsNumber < 31)&&(personsNumber > 1))
		{
			adult_dropdown.selectByVisibleText(String.valueOf(personsNumber) + " взрослых");
			return true;
		}		
		return false;
	}
	
	public boolean isCheapPriceCheckboxPresent()
	{
		return isElementPresent(price0_110);
	}
	
	public void selectCheapPrice()
	{
		price0_110.click();
		isElementPresent(noticeNoDorms);
	}
	
	public boolean isPriceInRange(int left, int right)
	{
		for (WebElement element: hotels)
		{
			int price = getPrice(element);
			if ((price < left)||(price > right))
			{
				return false;
			}
		}		
		return true;
	}
	
	public boolean isResultNumberAndHotelsEqual()
	{
		int resultNumber = numberOfSearchResults();
		int  hotelsOnPage = hotels.size();
		
		//System.out.println("Number: " + resultNumber + ", hotels on the page: " + hotelsOnPage);
		
		if(resultNumber < hotelsOnPage)
		{
			return false;
		}
		else if (resultNumber == hotelsOnPage)
		{
			return true;
		}
		else
		{		
			int allHotelsNumber;
			int searchPagesNumber = Integer.valueOf(searchPages.get(searchPages.size()-1).getText());
			searchPages.get(searchPages.size()-1).click();
			isElementPresent(pagination);
			resultNumber = numberOfSearchResults();
			hotelsOnPage = hotels.size();
			allHotelsNumber = (searchPagesNumber - 1)*16+hotelsOnPage;
			//System.out.println("Number: " + resultNumber + ", hotels on the page: " + hotelsOnPage);
		    //System.out.println("All available hotels number: " + allHotelsNumber);
			if (resultNumber == allHotelsNumber)
			{
				return true;
			}
			return false;
		}
	}
}
