package pages;

import java.time.Duration;
import java.util.List;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import objectrepository.Locators;
import utils.PropertyReader;
import utils.Reporter;

public class SelectProduct {
	WebDriver driver;
	WebDriverWait wait;
	ExtentTest test;
	
	String ProductName;
	String expectedProd;

	public SelectProduct(WebDriver driver, ExtentTest test) {
		this.driver = driver;
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		this.test = test;
	}

	public void validateProduct() {
		Properties prop = PropertyReader.readProperties();
		ProductName = prop.getProperty("Product");

		validate(ProductName);
	}

	public void validateProduct(String Product) {
		ProductName = Product;
		validate(ProductName);
	}

	public void validate(String ProductName) {
		List<WebElement> items = driver.findElements(Locators.PRODUCT_LIST);
//	        System.out.println("Total items found: " + items.size());

		for (int i = 1; i <= items.size(); i++) {
			
			String dynamicXPath = Locators.PRODUCT_NAME + "[" + i + "]";
			
			expectedProd = driver.findElement(By.xpath(dynamicXPath)).getText();

			if (ProductName.equals(expectedProd)) {
				driver.findElement(By.xpath(dynamicXPath)).click();

//				System.out.println("Clicked on the (" + expectedProd + ")");
				break;
			}
		}

		expectedProd = driver.findElement(Locators.PRODUCT_PAGE_NAME).getText();

		try {
			// explicit wait to verify the Button "Back to Products"
			wait.until(ExpectedConditions.visibilityOfElementLocated(Locators.BACK_BUTTON));
			Reporter.generateReport(driver, test, Status.PASS, "Product Page Validation Sucessful");
			Assert.assertEquals(ProductName, expectedProd, "Product matched on Product Page");
			
		} catch (TimeoutException te) {
			Reporter.generateReport(driver, test, Status.FAIL, "Product Page Validation Failed");
			Assert.assertEquals(ProductName, expectedProd);
		}
	}

}