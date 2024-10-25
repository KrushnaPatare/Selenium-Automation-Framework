package com.swagLabs.pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SwagLabsProductpage {
	
	@FindBy (xpath = "(//div[@class='inventory_item_img'])[1]") private WebElement productImage;
	@FindBy (xpath = "//div[text()='Sauce Labs Backpack']") private WebElement productTitle;
	@FindBy (xpath = "//div[@class='inventory_details_desc_container']") private WebElement productDescription;
	@FindBy (xpath = "//button[@name='back-to-products']") private WebElement backToProductsButton;
	@FindBy (xpath = "(//button)[4]") private WebElement addToCartButton;
	@FindBy (xpath = "(//button)[4]") private WebElement removeButton;
	@FindBy (tagName = "span") private WebElement shoppingCartBadge;
	@FindBy (xpath = "//a[@class='shopping_cart_link']") private WebElement shoppingCart;
	
	//span[@class='shopping_cart_badge']
	
	
	public SwagLabsProductpage(WebDriver driver) {
		
		PageFactory.initElements(driver, this);
	}
	
	public void clickProductImage() {
		
		productImage.click();
	}
	
	public void clickProductBanner() {
		
		productTitle.click();
	}
	
	public String getProductDescription() {
		
		String description = productDescription.getText();
		return description;
	}
	
	public void clickOnBackToProducts() {
		
		backToProductsButton.click();
	}
	
	public String getCartNotification() {
		
		String count = shoppingCartBadge.getText();
		return count;
	}
	
	public String clickOnAddToCartButton() {


		addToCartButton.click();
		String text = addToCartButton.getText();
		return text;
	}
	
	public String clickOnRemoveButton() {

		removeButton.click();
		String text = removeButton.getText();
		return text;
	}
	
	public void clickOnCartButton() {

		shoppingCart.click();
	}
	
	

}
