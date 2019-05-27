package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import pages.MainPage;
import pages.SearchPlacePage;

public class FirstTest extends BaseTest{

	private MainPage mainPage;
	private SearchPlacePage searchPlacePage;
	
	private int minMinskHotelNumber = 3;
	
	// проверяем, что найдется как минимум 3 доступных номера на ближайший выходной на двоих в Минске 
	@Test 
	public void first() 
	{
		  mainPage = new MainPage(getWebDriver());
		  mainPage.open(MainPage.MAIN_URL);	  
		  searchPlacePage = mainPage.searchByPlace("Минск");
		  searchPlacePage.chooseFirstWeekendArrivalDate();
		  searchPlacePage.clickSearchButton();
		  searchPlacePage.chooseOnlyAvailable();
		  Assert.assertTrue(searchPlacePage.numberOfSearchResults() >= minMinskHotelNumber, 
				  "Test failed: Number of hotels is less than necessary!");	  
	}
}
