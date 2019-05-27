package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import pages.MainPage;
import pages.SearchCarPage;
import pages.MainCarPage;

public class SixthTest extends BaseTest{

	private MainPage mainPage;
	private MainCarPage mainCarPage;
	private SearchCarPage searchCarPage;
	public String location = "Новогрудок";
	public int minCarsNumber = 5;

	
	// Выбираем Новогрудок и проверяем, что доступно не менее 5 машин
	@Test 
	public void sixth() 
	{
		  mainPage = new MainPage(getWebDriver());
		  mainPage.open(MainPage.MAIN_URL);
		  mainCarPage = mainPage.chooseRentCar();	  
		  searchCarPage = mainCarPage.searchByPlace(location);

		  Assert.assertTrue(searchCarPage.getAvailableCarsNumber() >= minCarsNumber, 
				  "Test failed: Quantity of available cars is less than required!");				  
	}
}
