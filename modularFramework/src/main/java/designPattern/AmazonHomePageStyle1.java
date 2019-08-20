package designPattern;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import commonLibs.implementations.ElementControl;
import commonLibs.implementations.SelectDropdown;

public class AmazonHomePageStyle1 {

	private WebElement searchBox;
	
	private WebElement selectDropdown;
	
	private WebElement searchButton;
   
	private WebElement result;
	
     SelectDropdown dropdownControl;
	
     ElementControl elementControl;
	
	public  AmazonHomePageStyle1 (WebDriver driver) {

        searchBox = driver.findElement(By.id("twotabsearchtextbox"));
        selectDropdown = driver.findElement(By.id("searchDropdownBox"));
		searchButton = driver.findElement(By.xpath("//input[@value='Go']"));
		result = driver.findElement(By.id("s-result-count"));
		dropdownControl = new SelectDropdown(); 
		elementControl = new ElementControl();
	
	}
	
	public void searchProduct(String product, String category) throws Exception{
		elementControl.setText(searchBox, product);
		dropdownControl.selectViaVisibleText(selectDropdown, category );
		elementControl.clickElement(searchButton);
		System.out.println(elementControl.getText(result));
		
	}
	}


	
		
		
	




