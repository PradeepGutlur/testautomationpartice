package tasks;

import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import io.github.bonigarcia.wdm.WebDriverManager;

public class D2_Task2Test {
	@Test
	public void practicalTest() {

		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("https://www.amazon.in/");
		driver.findElement(By.id("twotabsearchtextbox")).sendKeys("toys");
		driver.findElement(By.id("nav-search-submit-text")).click();
		driver.findElement(By.id("a-autoid-0-announce")).click();
		driver.findElement(By.xpath("//a[text()='Price: Low to High']")).click();
		SoftAssert s = new SoftAssert();
		WebElement prodPrice = driver.findElement(
				By.xpath("//div[@class='a-section a-spacing-base']/descendant::span[@class='a-price-whole']"));
		String actualTitle = driver
				.findElement(By.xpath(
						"//div[@class='a-section a-spacing-base']/descendant::span[contains(@class,'a-size-base')]"))
				.getText();
		String actPrice = prodPrice.getText();
		int actPriceInt = Integer.parseInt(actPrice);
		s.assertTrue(actPriceInt < 100, "Price is greater than 100");
		System.out.println("Price is " + actPrice);
		prodPrice.click();
		Set<String> wins = driver.getWindowHandles();
		Iterator<String> tabs = wins.iterator();
		while (tabs.hasNext()) {
			String tab = tabs.next();
			String currntWin = driver.switchTo().window(tab).getTitle();
			if (currntWin.contains(actualTitle))
				break;

		}
		WebElement ele = driver.findElement(By.xpath("//select[@id='quantity']"));
		Select dropDown = new Select(ele);
		WebElement firstopt = dropDown.getFirstSelectedOption();
		String quantity = firstopt.getText();
		int actQuan = Integer.parseInt(quantity);
		s.assertEquals(actQuan, 8);
		System.out.println(actQuan);
		driver.findElement(By.id("add-to-cart-button")).click();
		driver.findElement(By.xpath("//div[@id='sw-atc-buy-box']/descendant::a[contains(text(),'Go to Cart')]"))
				.click();
		WebElement prodName = driver.findElement(By.className("a-truncate-cut"));
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOf(prodName));
		String productNAme = prodName.getText();
		s.assertTrue(actualTitle.contains(productNAme),"Product Name is not matching");
		System.out.println("Product Name :"+productNAme);
		driver.quit();
	}
}
