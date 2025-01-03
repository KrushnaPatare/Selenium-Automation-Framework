package com.swagLabs.pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CheckoutPage 
{
    @FindBy(xpath = "//*[@id='first-name']")
    private WebElement firstNameField;

    @FindBy(xpath = "//*[@id='last-name']")
    private WebElement lastNameField;

    @FindBy(xpath = "//*[@id='postal-code']")
    private WebElement postalCodeField;

    @FindBy(xpath = "//*[@id='cancel']")
    private WebElement cancelButton;

    @FindBy(xpath = "//input[@name='continue']")
    private WebElement continueButton;


    public CheckoutPage(WebDriver driver) 
    {
        PageFactory.initElements(driver, this);
    }
    
    
    public WebElement getFirstNameField() {return firstNameField;}

    public WebElement getLastNameField() {return lastNameField;}

    public WebElement getPostalCodeField() {return postalCodeField;}

    public WebElement getCancelButton() {return cancelButton;}

    public WebElement getContinueButton() {return continueButton;}

   

}









