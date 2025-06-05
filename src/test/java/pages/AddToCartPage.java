package pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import objectrepository.Locators;
import utils.Reporter;

public class AddToCartPage {

    WebDriver driver;
	WebDriverWait wait;
	ExtentTest test;
	
    String productName;

    public AddToCartPage (WebDriver driver, ExtentTest test) {
		this.driver = driver;
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		this.test = test;
	}

    public void addToCart() {
        // Click on Add to Cart button
        WebElement addToCartButton = driver.findElement(Locators.ADD_TO_CART_BUTTON);
        addToCartButton.click();
    }

    public void verifyCartUpdated() {
        try {
        	// explicit wait to verify the Button "REMOVE"
        	wait.until(ExpectedConditions.visibilityOfElementLocated(Locators.REMOVE_FROM_CART_BUTTON));
        				
        	//check the count with the cart icon
        	String count = driver.findElement(Locators.CART_ICON_COUNT).getText();
            
            Assert.assertEquals(count, "1", "Cart count is not 1");
            Reporter.generateReport(driver, test, Status.PASS, "Cart count is validated: (1)");
            
        } catch (Exception e) {
        	e.printStackTrace();
            Reporter.generateReport(driver, test, Status.FAIL, "Cart count is Invalidated");
            Assert.fail("Add to cart failed");
        }
    }
}
