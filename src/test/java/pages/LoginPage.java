package pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import objectrepository.Locators;
import utils.Reporter;

public class LoginPage {
	WebDriver driver;
	WebDriverWait wait;
	ExtentTest test;
	
	public LoginPage (WebDriver driver, ExtentTest test) {
		this.driver = driver;
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		this.test = test;
	}
	
	
	public void validateLogin(String uname, String pwd) {
		driver.findElement(Locators.userName).sendKeys(uname);
		//Base.sleep(1000);
		driver.findElement(Locators.password).sendKeys(pwd);
		//Base.sleep(1000);
		driver.findElement(Locators.loginButton).click();
		try {
		// explicit wait to verify the text "Products"
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='Products']")));
		Reporter.generateReport(driver,  test,  Status.PASS, "Login Sucessful");
		
		}catch(TimeoutException te) {
			Reporter.generateReport(driver, test, Status.FAIL, "Login Failed");
		}
	}
	
}