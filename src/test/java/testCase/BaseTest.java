package testCase;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class BaseTest {


	public WebDriver driver() throws Throwable {

		WebDriver driver=null;

		System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"/src/test/resources/driver/chromedriver.exe");
		driver = new ChromeDriver();

		Thread.sleep(2000);
		driver.manage().window().maximize();

		return driver;

	}
	
	public void quit(WebDriver driver) {
		if (driver != null) {
			//driver.close();
			driver.quit();
		}
	}
}
