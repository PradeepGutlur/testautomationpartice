package com.amdocs.amazon.pomrepo;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {
	WebDriver driver;
	public HomePage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath ="//input[@type='text']")
	private WebElement searchBox;
	
	@FindBy(id="nav-search-submit-text")
	private WebElement searchOption;
	
	public WebElement getSearchBox() {
		return searchBox;
	}

	public WebElement getSearchOption() {
		return searchOption;
	}
	
	public void searchItem(String items,WebDriver driver)
	{
		searchBox.sendKeys("toys");
		searchOption.click();
	}
}
