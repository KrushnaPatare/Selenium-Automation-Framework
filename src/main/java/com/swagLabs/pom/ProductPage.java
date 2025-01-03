package com.swagLabs.pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProductPage 
{
	@FindBy(xpath = "//button[@name='back-to-products']")
    private WebElement backToProductsButton;
	
    @FindBy(xpath = "//div[@class='inventory_details_name']")
    private WebElement productTitle;

    @FindBy(xpath = "//div[@class='inventory_details_desc']")
    private WebElement productDescription;
    
    @FindBy(xpath = "//div[@class='inventory_details_price']")
    private WebElement productPrice;
    
    @FindBy(xpath = "//button[contains(@class, 'btn_inventory')]")
    private WebElement addToCartButton;

    @FindBy(xpath = "//button[text()='Remove']")
    private WebElement removeButton;
    
    
    public ProductPage(WebDriver driver) 
    {
        PageFactory.initElements(driver, this);
    }
    
    
    public WebElement getBackToProductsButton() {return backToProductsButton;}

    public WebElement getProductTitle() {return productTitle;}

    public WebElement getProductDescription() {return productDescription;}

    public WebElement getProductPrice() {return productPrice;}

    public WebElement getAddToCartButton() {return addToCartButton;}

    public WebElement getRemoveButton() {return removeButton;}

  
}







