package PageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import AbstractComponent.AbstractComponent;

public class OrderPage extends AbstractComponent{

	WebDriver driver;

	public OrderPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		
		//To initialize the WebElements listed below and use the driver 
		PageFactory.initElements(driver, this);

	}
	// PageFactory method to declare WebElements and in run time the webelements are
	// constructed as expected and assigned to variables declared

	@FindBy(css = ".hero-primary")
	WebElement orderConfirmation;
	
	@FindBy(xpath="//tr/td[2]")
	List<WebElement> ordersList;
	

	public String checkOrderConfirmation() {
		
		String Confirmation= orderConfirmation.getText();
		return Confirmation;
	}
	
	public List<WebElement> getOrdersList() {

		return ordersList;
	}
	public Boolean checkOrderinList(String productName) {
		
		Boolean match = getOrdersList().stream().anyMatch(s->s.getText().equalsIgnoreCase(productName));
		return match;
				
	}

}
