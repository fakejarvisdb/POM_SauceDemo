package tests;

import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import pages.AddToCartPage;
import pages.LoginPage;
import pages.SelectProduct;
import utils.Base;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.BeforeClass;
import org.openqa.selenium.By;
import org.testng.annotations.AfterClass;

public class PlaceOrderTest extends Base {

	ExtentSparkReporter spark;
	ExtentReports extReports;
	ExtentTest test;

	@BeforeClass
	public void setUpReports() {
		spark = new ExtentSparkReporter("reports\\ExtentReport.html");
		extReports = new ExtentReports();
		extReports.attachReporter(spark);
	}

	@AfterClass
	public void writeReports() {
		extReports.flush();
	}

	@Test
	public void testPlaceOrder() {
		// create extent report
		test = extReports.createTest("place order");

		// 1. validate the login
		LoginPage loginpage = new LoginPage(driver, test);
		loginpage.validateLogin("standard_user", "secret_sauce");
		sleep(2000);
		
		// 2. click on the product and validate
		SelectProduct selectProduct = new SelectProduct(driver, test);
		selectProduct.validateProduct();
		sleep(2000);	
		
		// 3. Click add to cart button and validate
		AddToCartPage cartPage = new AddToCartPage(driver, test);
		cartPage.addToCart();
		cartPage.verifyCartUpdated();
		sleep(2000);
		

	}

	@BeforeMethod
	public void beforeMethod() {
		// to launch browser
		launchBrowser();
	}

	@AfterMethod
	public void afterMethod() {
		sleep(7000);
		driver.quit();
	}

	@BeforeClass
	public void beforeClass() {
	}

	@AfterClass
	public void afterClass() {
	}

	@DataProvider
	public Object[][] dp() {
		return new Object[][] { new Object[] { 1, "a" }, new Object[] { 2, "b" }, };
	}

}
