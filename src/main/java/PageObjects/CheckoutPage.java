package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import AbstractComponent.AbstractComponent;

public class CheckoutPage extends AbstractComponent{

	WebDriver driver;
	public CheckoutPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	
	
	@FindBy(css = "[placeholder='Select Country']")
	WebElement countryBox;
	
	@FindBy(css = "a[class*='btnn action']")
	WebElement placeOrderButton;
	
	@FindBy(xpath= "(//button[@type='button'])[2]")
	WebElement land;
	


	public OrderPage inputCounty(String countryName) {

		countryBox.sendKeys(countryName);
		JavascriptExecutor js=(JavascriptExecutor)driver;
		js.executeScript("arguments[0].scrollIntoView();",placeOrderButton );
		waitForElementPresence(By.cssSelector(".ta-results"));
		land.click();
		placeOrderButton.click();
		OrderPage orderPage = new OrderPage(driver);
		return orderPage;
		
		
	}

}
