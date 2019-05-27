package tests;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pages.MainPage;
import pages.SearchPlacePage;

public class FifthTest extends BaseTest{

	private MainPage mainPage;
	private SearchPlacePage searchPlacePage;

	  @DataProvider
	  public Object[][] dp() 
	  {
	    return new Object[][] {
	      new Object[] { "Новогрудок" },
	      new Object[] { "Москва" },
	    };
	  }
	// проверяем, что отображаемое число найденных отелей соостветсвует найденному количеству отелей
	// Новогрудок и Москва сегодня на завтра
	@Test(dataProvider = "dp")
	public void fifth(String city) 
	{
		  mainPage = new MainPage(getWebDriver());
		  mainPage.open(MainPage.MAIN_URL);	  
		  searchPlacePage = mainPage.searchByPlace(city);
		  searchPlacePage.chooseTodayArrivalDate();
		  searchPlacePage.clickSearchButton();
		  searchPlacePage.chooseOnlyAvailable();

		  Assert.assertTrue(searchPlacePage.isResultNumberAndHotelsEqual(), 
				  "Test failed: Search result number does not equal to quantity of hotels!");	  

	}
}
