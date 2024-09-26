package tasks;

import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import io.github.bonigarcia.wdm.WebDriverManager;

public class D2_Task1Test {

	@Test
	public void practicalTest() {

		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("https://www.amazon.in/");
		driver.findElement(By.id("twotabsearchtextbox")).sendKeys("arts and crafts");
		List<WebElement> autoSuggestion = driver
				.findElements(By.xpath("(//div[@class='s-suggestion s-suggestion-ellipsis-direction'])"));
		int count = 1;
		for (WebElement autoSug : autoSuggestion) {

			if (count == 4) {
				autoSug.click();
				break;
			}
			count++;
		}
		// driver.findElement(By.xpath("//span[text()=' materials']")).click();
		SoftAssert s = new SoftAssert();
		WebElement price = driver.findElement(
				By.xpath("//div[@class='a-section a-spacing-base']/descendant::span[@class='a-price-whole']"));
		String actualTitle = driver
				.findElement(By.xpath(
						"//div[@class='a-section a-spacing-base']/descendant::span[contains(@class,'a-size-base')]"))
				.getText();

		String actPrice = price.getText();
		int actPriceInt = Integer.parseInt(actPrice);
		s.assertTrue(actPriceInt > 500, "Price is low");
		price.click();
		Set<String> wins = driver.getWindowHandles();
		// Set<String> wins = driver.getWindowHandles();
		Iterator<String> tabs = wins.iterator();
		while (tabs.hasNext()) {
			String tab = tabs.next();
			String currntWin = driver.switchTo().window(tab).getTitle();
			if (currntWin.contains(actualTitle))
				break;

		}
		driver.findElement(By.id("add-to-cart-button")).click();
		driver.findElement(By.xpath("//div[@id='sw-atc-buy-box']/descendant::a[contains(text(),'Go to Cart')]"))
				.click();
		WebElement prodName = driver.findElement(By.className("a-truncate-cut"));
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOf(prodName));
		String productName = prodName.getText();
		s.assertTrue(actualTitle.contains(productName), "Assert Failes ProductNAme");
		System.out.println(productName);
		s.assertAll();

		driver.quit();
	}

}
