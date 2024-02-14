package PageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import AbstractComponent.AbstractComponent;

public class productCatalogPage extends AbstractComponent {

	WebDriver driver;

	public productCatalogPage(WebDriver driver) {
		super(driver);
		this.driver = driver;

		// To initialize the WebElements listed below and use the driver
		PageFactory.initElements(driver, this);

	}
	// PageFactory method to declare WebElements and in run time the webelements are
	// constructed as expected and assigned to variables declared
//List<WebElement> product = driver.findElements(By.cssSelector(".card-body"));

	@FindBy(css = ".card-body")
	List<WebElement> products;

	@FindBy(css = "button[routerlink*='cart']")
	WebElement cartButton;

	@FindBy(xpath = "//div[@class='cartSection']")
	List<WebElement> cartSection;
	
	@FindBy(id= "toast-container")
	WebElement toastContainer;
	
	@FindBy(xpath = "//button[text()='Checkout']")
	WebElement checkoutButton;


	By addToCart = By.cssSelector("i[class*='fa-shopping-cart']");

	public List<WebElement> getProductList() {

		return products;
	}

	public WebElement getProductsByName(String itemName) {
		WebElement product = getProductList().stream()
				.filter(s -> s.findElement(By.cssSelector("b")).getText().contains(itemName)).findFirst().orElse(null);
		return product;

	}

	public void addProductToCart(String productName) throws InterruptedException {

		WebElement product = getProductsByName(productName);
		product.findElement(addToCart).click();
		waitForElementVisibility(toastContainer);
		Thread.sleep(2000);
		cartButton.click();
	}

	public boolean checkItemAddedToCart(String itemName) {
		Boolean check = cartSection.stream().anyMatch(s -> s.getText().contains(itemName));
		return check;
	}

	public CheckoutPage Checkout() {
		checkoutButton.click();
		return new CheckoutPage(driver);
		
		
		
	}


}
