package com.swagLabs.pom;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SwagLabsCartPage {
	
	@FindBy (xpath = "//button[@id='continue-shopping']") private WebElement continueShoppingButton;
	@FindBy (xpath = "//button[@id='checkout']") private WebElement checkoutButton;
	@FindBy (xpath = "(//button)[3]") private WebElement removeButton;
	@FindBy (xpath = "//div[@class='cart_item_label']") private WebElement productDescription;
	@FindBy (xpath = "//div[@class='inventory_item_name']") private List<WebElement> productNames;

	public SwagLabsCartPage(WebDriver driver) 
	{
		PageFactory.initElements(driver, this);
	}
	
	public String showProductDetails() 
	{
		String details = productDescription.getText();
		return details;
	}
	
	public void clickOnRemove() 
	{	
		removeButton.click();
	}
	
	public void clickOncontinueShopping() 
	{	
		continueShoppingButton.click();
	}
	
	public void clickOnCheckoutButton() 
	{
		checkoutButton.click();
	}
	
	public String[] nameOfAddedElementsInCart() 
	{
		String [] itemNames = new String [productNames.size()];
		for(int i=0;i<productNames.size();i++) 
		{
			itemNames[i] = productNames.get(i).getText();
		}
		return itemNames;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
   
}
