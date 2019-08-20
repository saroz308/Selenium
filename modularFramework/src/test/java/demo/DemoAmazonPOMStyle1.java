package demo;


import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import commonLibs.implementations.CommonDriver;
import commonLibs.implementations.ElementControl;
import commonLibs.implementations.SelectDropdown;
import designPattern.AmazonHomePageStyle1;

public class DemoAmazonPOMStyle1 {
    
	CommonDriver cmnDriver;
	AmazonHomePageStyle1 homepage;
	WebDriver driver;
	
	ElementControl elementControl;
    SelectDropdown dropdownControl;
	
	@BeforeClass
	public void setup() throws Exception{
		cmnDriver = new CommonDriver("chrome");
		
		cmnDriver.setPageloadTimeout(20);
		cmnDriver.elementDetectionTimeout(10);
		cmnDriver.navigateToFirstUrl("http://amazon.com");
		
		driver = cmnDriver.getDriver();
		
		homepage = new AmazonHomePageStyle1(driver);
		
		}
	@Test
	public void verifyProduct() throws Exception {
		homepage.searchProduct("Apple Watch", "Electronics");
		
		
		
	}
	
}
