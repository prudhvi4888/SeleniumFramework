package SeleniumFramworkTest;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import PageObjects.LoginPage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class SubmitOrderTest {

	public static void main(String[] args) {
		String requiredItem = "ZARA COAT 3";
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		LoginPage page = new LoginPage(driver);
		
		driver.get("https://rahulshettyacademy.com/client");
		driver.manage().window().maximize();
		
		driver.findElement(By.id("userEmail")).sendKeys("prudhvi44.raj@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("Password1!@");
		driver.findElement(By.id("login")).click();

		
		List<WebElement> product = driver.findElements(By.cssSelector(".card-body"));
//i[class*='fa-shopping-cart']
		WebElement prod = product.stream()
				.filter(s -> s.findElement(By.cssSelector("b")).getText().contains(requiredItem)).findFirst()
				.orElse(null);

		prod.findElement(By.cssSelector("i[class*='fa-shopping-cart']")).click();

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("toast-container")));

		driver.findElement(By.cssSelector("button[routerlink*='cart']")).click();

		List<WebElement> productCart = driver.findElements(By.xpath("(//div[@class='cartSection'])"));
		Boolean result = productCart.stream().anyMatch(s -> s.getText().contains(requiredItem));
		System.out.println(result);
		Assert.assertTrue(result);

		driver.findElement(By.xpath("//button[text()='Checkout']")).click();
		driver.findElement(By.cssSelector("[placeholder='Select Country']")).sendKeys("India");
		wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".ta-results")));
		driver.findElement(By.xpath("(//button[@type='button'])[2]")).click();
		driver.findElement(By.cssSelector("a[class*='btnn action']")).click();
		String Confirmation = driver.findElement(By.cssSelector(".hero-primary")).getText();
		System.out.println(Confirmation);
		Assert.assertTrue(Confirmation.equalsIgnoreCase("Thankyou for the order."));
		driver.close();

	}

}
