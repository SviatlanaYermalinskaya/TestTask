package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import pages.MainPage;
import pages.SearchPlacePage;

public class ForthTest extends BaseTest{

	private MainPage mainPage;
	private SearchPlacePage searchPlacePage;
	
	private int diffArrivalAndTodayDate = 10;
	private int periodOfStay = 1;
	
	// Выбрвть Париж через 10 дней на одни сутки 1 взрослый, проверить есть ли в фильтре Ваш бюджет чекбокс со стоимостью от 0 до 110 BYN
	// Выбрать этот фильтр и проверить, что стоимость проживания в найденных местах меньше 110
	@Test 
	public void forth() 
	{
		  mainPage = new MainPage(getWebDriver());
		  mainPage.open(MainPage.MAIN_URL);	  
		  searchPlacePage = mainPage.searchByPlaceDates("Париж", diffArrivalAndTodayDate, diffArrivalAndTodayDate + periodOfStay);
		  searchPlacePage.setAdults(1);
		  searchPlacePage.clickSearchButton();
		  
		  Assert.assertTrue(searchPlacePage.isCheapPriceCheckboxPresent(), 
				  "Test failed: Price rang checkbox is not found in required time.");
		  
		  searchPlacePage.selectCheapPrice();
		  
		  Assert.assertTrue(searchPlacePage.isPriceInRange(0, 110), 
				  "Test failed: Search result contains hotel with price out of range!");		  
	}
}
