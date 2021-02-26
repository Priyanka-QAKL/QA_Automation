package testCase;

import static org.testng.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

public class Test1 extends BaseTest {

	@Test
	public void sample1() throws Throwable {

		WebDriver driver = driver();

		//EBAY
		driver.get("https://www.ebay.com/");

		driver.findElement(By.xpath(".//*[@placeholder='Search for anything']")).sendKeys("iphone11");

		driver.findElement(By.xpath(".//*[@value='Search']")).click();

		driver.findElement(By.xpath("(.//*[@class='expand-btn__cell'])[4]")).click();

		driver.findElement(By.xpath(".//*[@class='fake-menu-button__items']/a/span[text()='Price + Shipping: highest first']")).click();

		List<WebElement> links = driver.findElements(By.xpath(".//*[@class='s-item__wrapper clearfix']/div[2]/a"));

		List<WebElement> prices = driver.findElements(By.xpath(".//*[@class='s-item__wrapper clearfix']/div[2]/a/following::div/span[@class='s-item__price']"));

		List<WebElement> products = driver.findElements(By.xpath(".//*[@class='s-item__info clearfix']/a/h3"));

		//Validation with all links having Iphone 11
		for (WebElement iphonellink : links) {

			assertTrue(
					iphonellink.getAttribute("href").contains("iPhone-11")||iphonellink.getAttribute("href").contains("Iphone-11")||
					iphonellink.getAttribute("href").contains("iPHONE-11"),"Product not contains Iphone 11...");
		}


		//List for product data
		List<ProductDetail> details = new ArrayList<ProductDetail>();

		//Add data to the list from ebay
		for (int i = 0; i < products.size(); i++) {

			details.add(new ProductDetail(links.get(i).getAttribute("href"), products.get(i).getText(), Double.parseDouble(prices.get(i).getText().split("\\.")[0].replaceAll("\\$", "").replaceAll("\"", "").replace(",", "").toString()), "EBAY"));

		}


		//FLIPKART
		driver.navigate().refresh();

		driver.get("https://www.flipkart.com/");

		synchronized (driver) {
			driver.wait(3000);
		}

		driver.findElement(By.xpath(".//*[@class='_2KpZ6l _2doB4z']")).click();

		

		driver.findElement(By.xpath(".//*[@title='Search for products, brands and more']")).sendKeys("iphone 11");		

		synchronized (driver) {
			driver.wait(3000);
		}
		driver.findElement(By.xpath(".//*[@type='submit']")).click();	
		
		synchronized (driver) {
			driver.wait(3000);
		}

		List<WebElement> flipkartlinks = driver.findElements(By.cssSelector("._1fQZEK"));

		List<WebElement> flipkartprices = driver.findElements(By.xpath(".//*[@class='_3tbKJL']/div[1]/div[1]"));

		List<WebElement> flipkartproducts = driver.findElements(By.cssSelector("._4rR01T"));

		//Validation with all links having Iphone 11
		for (WebElement iphonellink : flipkartlinks) {

			assertTrue(iphonellink.getAttribute("href").contains("iphone-11"),"Product not contains Iphone 11...");

		}

		//Add data to the list from flipkart
		for (int i = 0; i < flipkartlinks.size(); i++) {

			details.add(new ProductDetail(flipkartlinks.get(i).getAttribute("href"), flipkartproducts.get(i).getText(), Double.parseDouble(flipkartprices.get(i).getText().replace("₹", "").replace(",", "")), "FLIPKART"));
		}

		//This is for sorting the list using prices
		Collections.sort(details,Comparator.comparing(ProductDetail::getpPrice).reversed());

		//Print data into console
		details.stream().forEach(p->{
			System.out.println(p.getPsite()+"-----"+p.getpName()+"-----"+p.getpPrice()+"-----"+p.getpLink());});

		//Close browser
		quit(driver);
	}

}
