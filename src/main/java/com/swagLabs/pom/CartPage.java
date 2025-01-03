package com.swagLabs.pom;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CartPage 
{	
	@FindBy(xpath = "//div[@class='cart_list']")
    private WebElement cartItemDetails;
	
	@FindBy(xpath = "//button[@id='continue-shopping']")
    private WebElement continueShoppingButton; 
	
    @FindBy(xpath = "//button[text()='Remove']")
    private List<WebElement> removeButton;
    
    @FindBy(xpath = "//button[text()='Checkout']")
    private WebElement checkoutButton;


    public CartPage(WebDriver driver) 
    {
        PageFactory.initElements(driver, this);
    }
    
   
    public WebElement getCartItemDetails() {return cartItemDetails;}

    public List<WebElement> getRemoveButton() {return removeButton;}
    
    public WebElement getContinueShoppingButton() {return continueShoppingButton;}

    public WebElement getCheckoutButton() {return checkoutButton;}

	

}














