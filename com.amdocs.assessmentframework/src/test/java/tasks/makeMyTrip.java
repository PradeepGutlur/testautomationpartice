package tasks;

import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class makeMyTrip {

	static WebDriver driver = null;

	@Test
	public void bookTicket() throws InterruptedException {
		String fromName = "Bengaluru";
		String toName = "Delhi";
		String depart = "August";
		String departDate = "10";
		String retur = "November";
		String returnDate = "12";
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get("https://www.makemytrip.com/");

		driver.findElement(By.className("langCardClose")).click();
		driver.findElement(By.xpath("//li[@data-cy='roundTrip']")).click();
		driver.findElement(By.xpath("//label[@for='fromCity']")).click();
		// Thread.sleep(1000);

		driver.findElement(By.xpath("//input[@placeholder='From']")).sendKeys(fromName);
		driver.findElement(
				By.xpath("//p[contains(text(),'" + fromName + "') and @class='font14 appendBottom5 blackText']"))
				.click();

		driver.findElement(By.xpath("//label[@for='toCity']")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//input[@placeholder='To']")).sendKeys(toName);
		driver.findElement(
				By.xpath("//p[contains(text(),'" + toName + "') and @class='font14 appendBottom5 blackText']")).click();
		calender(depart, departDate);
		calender(retur, returnDate);
		driver.findElement(By.xpath("//a[text()='Search']")).click();

		try {

			driver.findElement(By.xpath("//span[@class='bgProperties icon20 overlayCrossIcon']")).click();

		} catch (Exception e) {

		}

		List<WebElement> actual = driver.findElements(By.xpath("//div[@class='paneView']/p"));
		for (WebElement act : actual) {
			System.out.println(act.getText());
		}
		JavascriptExecutor j = (JavascriptExecutor) driver;
		j.executeScript("window.scrollTo(0,document.body.scrollHeight)");
		Thread.sleep(2000);
		j.executeScript("window.scrollTo(0,document.body.scrollHeight)");

		driver.findElement(By.xpath("((//div[@class='paneView'])[1]/descendant::button[text()='VIEW ALL'])[last()]"))
				.click();
		Thread.sleep(2000);

		j.executeScript("window.scrollTo(0,document.body.scrollHeight)");
		driver.findElement(By.xpath(
				"((//div[@class='paneView'])[1]/descendant::div[@class='listingCard ']/descendant::span[@class='customRadioBtn'])[last()]"))
				.click();

//		for (int i = 0; i < 6; i++) {
//			j.executeScript("window.scrollTo(0,-400)");
//		}
//		Thread.sleep(4000);
//		driver.findElement(By.xpath("(//button[text()='VIEW ALL'])[3]"))
//				.click();
//		Thread.sleep(2000);

		//j.executeScript("window.scrollTo(0,document.body.scrollHeight)");
//		WebElement returns = driver.findElement(By.xpath(
//				"((//div[@class='paneView'])[2]/descendant::div[@class='listingCard ']/descendant::(span[@class='customRadioBtn'])[125])"));
//		Thread.sleep(2000);
//		returns.click();
		
		Thread.sleep(1000);
		driver.findElement(By.xpath("//button[text()='Book Now']"))
		.click();
		driver.findElement(By.xpath("//button[text()='Continue']")).click();
		Set<String> wins = driver.getWindowHandles();
		Iterator<String> tabs = wins.iterator();
		while (tabs.hasNext()) {
			String tab = tabs.next();
			String currntWin = driver.switchTo().window(tab).getTitle();
			if (currntWin.contains("reviewDetails"))
				break;

		}
		
		driver.findElement(By.xpath("//span[text()='Login or']")).click();
		driver.findElement(By.id("username")).sendKeys("9035113448");
		
		driver.findElement(By.xpath("//span[.='Continue']")).click();
		Thread.sleep(2000);
		
		driver.findElement(By.xpath("//input[@placeholder='Enter OTP here']")).sendKeys("9035");
		
		driver.findElement(By.xpath("//button[@data-cy='verifyAndCreate']")).click();
	}

	/**
	 * 
	 * This method is used to handle calender
	 * 
	 * @param
	 */

	static public void calender(String month, String Date) {
		for (;;) {
			try {
				driver.findElement(By.xpath("//div[text()='" + month
						+ "']/ancestor::div[@class='DayPicker-Month']/descendant::p[text()='" + Date + "']")).click();
				break;
			} catch (Exception e) {
				driver.findElement(By.xpath("//span[@aria-label='Next Month']")).click();
			}
		}
	}

}
