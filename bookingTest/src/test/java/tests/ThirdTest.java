package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import pages.MainPage;
import pages.SearchPlacePage;

public class ThirdTest extends BaseTest{

	private MainPage mainPage;
	private SearchPlacePage searchPlacePage;
	private double minRating = 9;
	
	@Test // найти в Mоскве номер на сегодня, отсортировать по оценке + количество отзывов и проверить, 
          //что рейтинг первого отеля больше 9
	public void third() 
	{
		mainPage = new MainPage(getWebDriver());
		mainPage.open(MainPage.MAIN_URL);	  
		searchPlacePage = mainPage.searchByPlace("Москва"); 
		searchPlacePage.chooseTodayArrivalDate();
	    searchPlacePage.clickSearchButton();
	    searchPlacePage.filterByRating();
	    
	    Assert.assertTrue(searchPlacePage.getFirstHotelRating() >= minRating, "Test failed: Hotel rating is less than necessary!");
	}
}
