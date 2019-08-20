package in.amazon.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import commonLibs.implementations.ElementControl;
import commonLibs.implementations.SelectDropdown;

public class Homepage {

	@CacheLookup
	@FindBy(id = "twotabsearchtextbox")
	private WebElement searchBox;

	@FindBy(id = "searchDropdownBox")
	private WebElement selectCategory;

	@FindBy(xpath = "//input[@value='Go']")
	private WebElement searchButton;

	@FindBy(xpath = "//span[@data-component-type='s-result-info-bar']")
	private WebElement resultText;

	private SelectDropdown dropdownControl;

	private ElementControl elementControl;

	public Homepage(WebDriver driver) {
		PageFactory.initElements(driver, this);

		dropdownControl = new SelectDropdown();
		elementControl = new ElementControl();

	}

	public void searchProduct(String product, String category) throws Exception {
		elementControl.setText(searchBox, product);
		dropdownControl.selectViaVisibleText(selectCategory, category);
		elementControl.clickElement(searchButton);

		String result = elementControl.getText(resultText);
		System.out.println(result);

	}
}
