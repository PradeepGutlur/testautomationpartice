package com.amdocs.amazon.searchTest;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import com.amdocs.amazon.genericUtils.BaseAnnotationClass;
import com.amdocs.amazon.pomrepo.HomePage;

public class RedirectedToTheDesiredPageTest extends BaseAnnotationClass {
@Test
public void PracticalTest() throws InterruptedException
{
	HomePage hp=new HomePage(driver);
	String itemNAme = "toys";
	hp.searchItem(itemNAme, driver);
	String Url = driver.getCurrentUrl();
	Assert.assertTrue(Url.contains(itemNAme));
	Reporter.log("Redirected to Desired Page");
	
	}
}
