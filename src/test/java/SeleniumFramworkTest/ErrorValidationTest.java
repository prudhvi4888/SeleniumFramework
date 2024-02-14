package SeleniumFramworkTest;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import java.io.IOException;
import java.time.Duration;
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
import SeleniumFramworkDesign.TestComponents.Retry;
import io.github.bonigarcia.wdm.WebDriverManager;

public class ErrorValidationTest extends BaseTest {

	@Test(groups= {"NegativeScenario"}, retryAnalyzer=Retry.class )
	public void submitOrder() throws IOException, InterruptedException {

		String usename = "prudhvi44.raj@gmail.com";
		String password = "Pass!@";

		loginPage.loginApplication(usename, password);
		Assert.assertEquals("Incorrect email or password.",loginPage.validateErrormsg());

	}
	@Test
	public void productErrorValidation() throws InterruptedException {
		
		String name="test";
		String itemName = "ZARA COAT 3";
		String usename = "prudhvi.raj@gmail.com";
		String password = "Nani4321!@";
		

		productCatalogPage productPage= loginPage.loginApplication(usename, password);
		
		//List<WebElement> productList = productPage.getProductList();
		productPage.getProductsByName(itemName);
		productPage.addProductToCart(itemName);

		loginPage.waitForInvisibility(By.id("toast-container"));

		Boolean result= productPage.checkItemAddedToCart("ZARA COAT 33");
		Assert.assertFalse(result);
		
		
	}
	@Test
	public void sampleTest() {
		System.out.println("This is sample test");
	}
}