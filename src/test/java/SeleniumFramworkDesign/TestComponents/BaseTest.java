package SeleniumFramworkDesign.TestComponents;

import org.testng.annotations.AfterMethod;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import PageObjects.LoginPage;
import io.github.bonigarcia.wdm.WebDriverManager;


public class BaseTest {

	public WebDriver driver;
	public LoginPage loginPage;

	public WebDriver initializeDriver() throws IOException {

		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(
				System.getProperty("user.dir") + "//src//main//java//resources//Global.properties");
		prop.load(fis);

		// String browserName = prop.getProperty("browser");
		String browserName = System.getProperty("browser") != null ? System.getProperty("browser")
				: prop.getProperty("browser");
		if (browserName.contains("chrome")) {
			
			//For headless mode execution
			ChromeOptions options = new ChromeOptions();
			WebDriverManager.chromedriver().setup();
			if(browserName.contains("headless")) {
			
			options.addArguments("headless");
			}
			
			driver = new ChromeDriver(options);
			//To set Full Screen
			driver.manage().window().setSize(new Dimension(1440,900));

		} else if (browserName.equalsIgnoreCase("chrome")) {

		}

		else if (browserName.equalsIgnoreCase("Edge")) {

			System.setProperty("webdriver.edge.driver", "path to edge driver");
			driver = new EdgeDriver();

		} else if (browserName.equalsIgnoreCase("Firefox")) {
			System.setProperty("webdriver.gecko.driver", "//Users//prudhvirajgollapalli//Downloads//geckodriver");
			driver = new FirefoxDriver();

		}

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		return driver;
	}

	public List<HashMap<String, String>> getJsonDataToMap(String filePath) throws IOException {

		// reading Json to String
		String jsonContent = FileUtils.readFileToString(new File(filePath), StandardCharsets.UTF_8);

		// converting string to Hashmap need dependency called Jackson Databind

		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String, String>> data = mapper.readValue(jsonContent,
				new TypeReference<List<HashMap<String, String>>>() {
				});

		return data;

	}

	public String getScreenShot(String testCaseName, WebDriver driver) throws IOException {

		File source = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		File destination = new File(System.getProperty("user.dir") + "//reports" + testCaseName + ".png");
		FileUtils.copyFile(source, destination);
		return System.getProperty("user.dir") + "//reports" + testCaseName + ".png";

	}

	@BeforeMethod(alwaysRun = true)
	public LoginPage launchApplication() throws IOException {
		driver=initializeDriver();
		loginPage = new LoginPage(driver);
		loginPage.goToLoginPage();
		return loginPage;

	}

	@AfterMethod(alwaysRun = true)
	public void tearDown() {
		driver.close();
	}
}