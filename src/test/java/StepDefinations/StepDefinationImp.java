package StepDefinations;

import java.io.IOException;

import org.openqa.selenium.By;
import org.testng.Assert;

import PageObjects.CheckoutPage;
import PageObjects.LoginPage;
import PageObjects.OrderPage;
import PageObjects.productCatalogPage;
import SeleniumFramworkDesign.TestComponents.BaseTest;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepDefinationImp extends BaseTest {

	public LoginPage loginpage;
	public productCatalogPage productPage;
	CheckoutPage checkoutPage;
	OrderPage orderPage;
	String country = "India";

	@Given("I Landed on ecommerce page")
	public void I_landed_on_ecommerce_page() throws IOException {

		loginPage = launchApplication();
	}

	@Given("^I want to login with (.+) and (.+)$")
	public void I_want_to_login_username_password(String username, String password) {

		productPage = loginPage.loginApplication(username, password);
	}

	@When("^I add product (.+) to the cart$")
	public void I_add_product_to_cart(String productName) throws InterruptedException {
		productPage.getProductsByName(productName);
		productPage.addProductToCart(productName);

		loginPage.waitForInvisibility(By.id("toast-container"));

	}

	@And("^Checkout(.+) and submit order$")
	public void Checkout_product_and_submit_order(String productName) {

		Boolean result = productPage.checkItemAddedToCart(productName);
		Assert.assertTrue(result);
	}

	@Then("{string} message is displayed on ConfirmationPage")
	public void message_displayed_confirmationPage(String string) {
		checkoutPage = productPage.Checkout();
		orderPage = checkoutPage.inputCounty(country);
		String Confirmation = orderPage.checkOrderConfirmation();
		Assert.assertTrue(Confirmation.equalsIgnoreCase(string), "failed to place the order");
		driver.close();
	}
	
	 @Then("{string} message is displayed")
	 public void message_displayed_loginPage(String string) {
		 Assert.assertEquals("Incorrect email or password.",loginPage.validateErrormsg());
		 driver.close();
		 
	 }

}
