package com.swagLabs.pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CheckoutOverviewPage 
{
    @FindBy(xpath = "//div[@data-test='checkout-summary-container']")
    private WebElement checkoutSummaryContainer;

    @FindBy(xpath = "//div[@data-test='cart-list']")
    private WebElement cartList;

    @FindBy(xpath = "//button[@data-test='finish']")
    private WebElement finishButton;

    @FindBy(xpath = "//button[@data-test='cancel']")
    private WebElement cancelButton;

    
    public CheckoutOverviewPage(WebDriver driver) 
    {
        PageFactory.initElements(driver, this);
    }

    
    public WebElement getCheckoutSummaryContainer() 
    {
        return checkoutSummaryContainer;
    }
    

    public WebElement getCartList() 
    {
        return cartList;
    }


    public WebElement getFinishButton() 
    {
        return finishButton;
    }

    public WebElement getCancelButton() 
    {
        return cancelButton;
    }
}












	
