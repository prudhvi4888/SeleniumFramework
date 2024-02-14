package AbstractComponent;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import PageObjects.OrderPage;

public class AbstractComponent {
	WebDriver driver;
	WebDriverWait wait;
	
	public AbstractComponent(WebDriver driver) {
		this.driver = driver;
		
	}
	
	@FindBy(css="[routerlink*='myorders']")
	WebElement ordersButton;
	
	public OrderPage goToOrdersPage() {
		ordersButton.click();
		OrderPage orderPage= new OrderPage(driver);
		return orderPage;
		
		
	}

	public void waitForInvisibility(By findBy) {
		wait =new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.invisibilityOfElementLocated(findBy));
	}
	
	public void waitForElementPresence(By findBy) {
		wait =new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.presenceOfElementLocated(findBy));
	}
	
	public void waitForElementVisibility(WebElement ele) {
		wait =new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOf(ele));
	}

}