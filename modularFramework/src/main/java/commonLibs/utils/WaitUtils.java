package commonLibs.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WaitUtils {
     public static void WaitTillElementVisible(WebDriver driver, WebElement element, int timeoutInSeconds) {
    	 
    	 WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
    	 wait.until(ExpectedConditions.visibilityOf(element));
     }
     
public static void WaitTillElementClickable(WebDriver driver, WebElement element, int timeoutInSeconds) {
    	 
    	 WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
    	 wait.until(ExpectedConditions.elementToBeClickable(element));
     }
public static void WaitTillAlertIsPresent(WebDriver driver, int timeoutInSeconds) {
	 
	 WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
	 wait.until(ExpectedConditions.alertIsPresent());
}
}
