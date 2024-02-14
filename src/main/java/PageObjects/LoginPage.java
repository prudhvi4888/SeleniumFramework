package PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import AbstractComponent.AbstractComponent;

public class LoginPage extends AbstractComponent{

	public WebDriver driver;

	public LoginPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		
		//To initialize the WebElements listed below and use the driver 
		PageFactory.initElements(driver, this);

	}
	// PageFactory method to declare WebElements and in run time the webelements are
	// constructed as expected and assigned to variables declared

	@FindBy(id = "userEmail")
	WebElement emailid;

	@FindBy(id = "userPassword")
	WebElement password;

	@FindBy(id = "login")
	WebElement loginButton;
	
	@FindBy(css= "[class*='toast-error'")
	WebElement errorMessage;
	
//	.ng-tns-c4-10.ng-star-inserted.ng-trigger.ng-trigger-flyInOut.ngx-toastr.toast-error
	
	
	public productCatalogPage loginApplication(String email, String passWord) {
		
		emailid.sendKeys(email);
		password.sendKeys(passWord);
		loginButton.click();
		productCatalogPage productPage = new productCatalogPage(driver);
		return productPage;
	}
	
	public void goToLoginPage() {
		
		driver.get("https://rahulshettyacademy.com/client");

	}
	
	public String validateErrormsg() {
		waitForElementVisibility(errorMessage);
		return errorMessage.getText();
	}

}
