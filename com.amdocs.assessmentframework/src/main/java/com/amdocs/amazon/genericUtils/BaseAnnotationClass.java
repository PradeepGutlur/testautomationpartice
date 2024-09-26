package com.amdocs.amazon.genericUtils;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseAnnotationClass {

	/* create Objects */
	public FileUtility flib = new FileUtility();
	public WebDriverUtility wlib = new WebDriverUtility();

	public WebDriver driver = null;
	public static WebDriver sdriver = null;

	@BeforeSuite
	public void configBS() {
		System.out.println("=========connect to database=======");
	}

	@Parameters("BROWSER")
	@BeforeClass
	public void configBC(String BROWSER) throws Throwable {
		System.out.println("======Launch the Browser=======");	
		/* launch the browser */
		
		if (BROWSER.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		} 
		else if (BROWSER.equalsIgnoreCase("firefox")) {
			
			
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		}
		wlib.maximizeThePage(driver);
		wlib.waitUntilPageLoad(driver);
		String URl = flib.readDatafromPropertyfile("url");
		driver.get(URl);
		
		sdriver = driver;

	}

	@BeforeMethod
	public void configBM() throws IOException {

		System.out.println("==========Login============");

	}

	@AfterMethod
	public void configAM() {

		System.out.println("=============Logout=============");
		/* logout */

	}

	@AfterClass
	public void configAC() {

		System.out.println("========Close the Browser========");
		/* close the browser */
		driver.quit();

	}

	@AfterSuite
	public void configAS() {
		System.out.println("=========close the database=======");
	}

}
