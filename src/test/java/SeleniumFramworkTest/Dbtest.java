package SeleniumFramworkTest;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class Dbtest {

	@Test
	public void dbConnection() throws SQLException, InterruptedException {
		String host = "localhost";
		WebDriver driver;
		String port = "3306";
		// jdbc:mysql://localhost:3306/QADb
		Connection conn = DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/QADb", "root",
				"Nani1234!@");
		Statement st = conn.createStatement();
		ResultSet rs = st.executeQuery("select * from accountInfo where accname='Positivebalance';");
		while (rs.next()) {
			driver = new ChromeDriver();

			driver.get("https://www.salesforce.com/");
			driver.manage().window().maximize();
			Thread.sleep(3000);
			
			
			
			WebElement Login = driver.findElement(By.cssSelector("[aria-controls='login-unauth-content']"));
			
			JavascriptExecutor js = (JavascriptExecutor)driver;
		       
	        // Call the JavascriptExecutor methods
	        js.executeScript("arguments[0].click();", Login);
			driver.findElement(By.linkText("Salesforce")).click();
			driver.findElement(By.id("username")).sendKeys(rs.getString("username"));
			driver.findElement(By.id("password")).sendKeys(rs.getString("pass"));

		}
	}
}