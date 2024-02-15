package SeleniumFramworkTest;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import PageObjects.CheckoutPage;
import PageObjects.LoginPage;
import PageObjects.OrderPage;
import PageObjects.productCatalogPage;
import SeleniumFramworkDesign.TestComponents.BaseTest;
import io.github.bonigarcia.wdm.WebDriverManager;

public class StandAloneTest extends BaseTest {

	// String usename = "prudhvi44.raj@gmail.com";
	// String password = "Password1!@";
	// String itemName = "ZARA COAT 3";
	OrderPage orderPage;

	@Test(dataProvider = "getData", groups = { "Purchase" })
	public void submitOrder(HashMap<String, String>data)
			throws IOException, InterruptedException {

		String country = "India";

		productCatalogPage productPage = loginPage.loginApplication(data.get("email"), data.get("password"));

		// List<WebElement> productList = productPage.getProductList();
		productPage.getProductsByName(data.get("product"));
		productPage.addProductToCart(data.get("product"));

		loginPage.waitForInvisibility(By.id("toast-container"));

		Boolean result = productPage.checkItemAddedToCart(data.get("product"));
		Assert.assertTrue(result);

		CheckoutPage checkoutPage = productPage.Checkout();
		OrderPage orderPage = checkoutPage.inputCounty(country);
	
		String Confirmation = orderPage.checkOrderConfirmation();
		Assert.assertTrue(Confirmation.equalsIgnoreCase("Thankyou for the order."), "failed to place the order");

	}

	@Test(dataProvider = "getData", dependsOnMethods = { "submitOrder" })
	public void orderHistoryTest(HashMap<String, String> data) {
		loginPage.loginApplication(data.get("email"), data.get("password"));
		orderPage = loginPage.goToOrdersPage();
		Boolean match = orderPage.checkOrderinList(data.get("product"));
		Assert.assertTrue(match);

	}

	@DataProvider
	public Object[][] getData() throws IOException {

		List<HashMap<String, String>> data = getJsonDataToMap(
				System.getProperty("user.dir") + "//src//test//java//Data//PurchaseOrder.json");

		return new Object[][] { { data.get(0) }, { data.get(1) } };
		
		
		// Object[][] data = { { "prudhvi44.raj@gmail.com", "Password1!@", "ZARA COAT 3"
				// },
				// { "shetty@gmail.com", "Iamking@000", "ADIDAS ORIGINAL" } };

	}
}