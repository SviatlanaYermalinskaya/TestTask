package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import pages.MainPage;
import pages.SearchCarPage;
import pages.CarPage;
import pages.CarRentalPage;
import pages.MainCarPage;

public class SeventhTest extends BaseTest{

	private MainPage mainPage;
	private MainCarPage mainCarPage;
	private SearchCarPage searchCarPage;
	private CarPage carPage;
	private CarRentalPage carRentalPage;
	public String location = "Москва Домодедово";
	
	// Москва Домодедово, бронируем первую машину без указания данных, проверяем, что появилось сообщение о незаполненых полях
	@Test 
	public void seventh() 
	{
		  mainPage = new MainPage(getWebDriver());
		  mainPage.open(MainPage.MAIN_URL);
		  mainCarPage = mainPage.chooseRentCar();
		  searchCarPage = mainCarPage.searchByPlace(location);
		  searchCarPage.showAvailableCars();
		  carPage = searchCarPage.chooseCar(0);
		  carRentalPage = carPage.rentCar();
		  carRentalPage.rentCar();
		  
		  Assert.assertTrue(carRentalPage.isErrorNoticePresent(), 
				  "Test failed: Error notice is not present!");	
	}
}
