package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import pages.MainPage;
import pages.SearchPlacePage;

public class SecondTest extends BaseTest{

	private MainPage mainPage;
	private SearchPlacePage searchPlacePage;
	
	private int maxHotelPrice = 1000;
	private int diffArrivalAndTodayDate = 3;
	private int periodOfStay = 14;
	
	// проверяем, что цена на самый дешевый доступный хостел не превышает 1000 руб. за 14 дней на двоих в центре Москвы 
	//заезд через 3 дня от текущей даты 
	@Test 
	public void first() 
	{
		  mainPage = new MainPage(getWebDriver());
		  mainPage.open(MainPage.MAIN_URL);
		  
		  searchPlacePage = mainPage.searchByPlaceDates("Москва", diffArrivalAndTodayDate, diffArrivalAndTodayDate + periodOfStay);
		 
		  searchPlacePage.filterByPrice(); 		 	  
		  searchPlacePage.chooseHostel();
		  searchPlacePage.chooseOnlyAvailable();
		  searchPlacePage.chooseCenter();
		 	  
		  Assert.assertTrue(searchPlacePage.getFirstHotelPrice() <= maxHotelPrice, 
				  "Test failed: Hostel price is more than necessary!");	  
				 
	}
}
