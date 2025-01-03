package com.swagLabs.pom;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class InventoryPage 
{
    @FindBy(xpath = "//div[@class='inventory_item_name ']")
    private List<WebElement> productNames;
    
    @FindBy(xpath = "product_sort_container")
    private WebElement sortDropdown;
    
    @FindBy(xpath = "//button[text()='Add to cart']")
    private List<WebElement> addToCartButton;
    
    @FindBy(xpath = "//button[text()='Remove']")
    private WebElement removeButton;

    
    public InventoryPage(WebDriver driver) 
    {
        PageFactory.initElements(driver, this);
    }
    


    public WebElement getSortDropdown() {return sortDropdown;}

    public List<WebElement> getAddToCartButton() {return addToCartButton;}
    
    public WebElement getRemoveButton() {return removeButton;}
    
    public List<WebElement> getProductNames() {return productNames;}

        
}









