package objectrepository;

import org.openqa.selenium.By;

public class Locators {
	
	public static final By userName  =  By.id("user-name");
	public static final By password  =  By.name("password");
	public static final By loginButton  =  By.id("login-button");

	
	public static final By ADD_TO_CART_BUTTON = By.xpath("//button[@id='add-to-cart']");
	public static final By REMOVE_FROM_CART_BUTTON = By.xpath("//button[@id='remove']");
	
	
    public static final By PRODUCT_LIST = By.xpath("//div[@class='inventory_item']");
    public static final String PRODUCT_NAME = "(//div[@class='inventory_item_name '])";
    public static final By PRODUCT_PAGE_NAME = By.xpath("//div[@class='inventory_details_name large_size']");
    
        
    public static final By CART_ICON_COUNT = By.xpath("//a[@class='shopping_cart_link']/child::span");
    
    public static final By BACK_BUTTON = By.xpath("//button[@id='back-to-products']");
    

}