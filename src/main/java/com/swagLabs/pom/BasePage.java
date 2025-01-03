package com.swagLabs.pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class BasePage 
{
    @FindBy(xpath = "//div[@class='shopping_cart_container']")
    private WebElement shoppingCartIcon;
    
    @FindBy(xpath = "//*[@id='react-burger-menu-btn']")
    private WebElement burgerMenuButton;

    @FindBy(xpath = "//*[@id='react-burger-cross-btn']")
    private WebElement closeMenuButton;

    @FindBy(xpath = "//*[@id='inventory_sidebar_link']")
    private WebElement allItemsLink;

    @FindBy(xpath = "//*[@id='about_sidebar_link']")
    private WebElement aboutLink;

    @FindBy(xpath = "//*[@id='logout_sidebar_link']")
    private WebElement logoutLink;

    @FindBy(xpath = "//*[@id='reset_sidebar_link']")
    private WebElement resetAppStateLink;
    
    @FindBy(xpath = "//a[text()='Twitter']")
    private WebElement TwitterPageButton;
    
    @FindBy(xpath = "//a[text()='Facebook']")
    private WebElement FacebookPageButton;
    
    @FindBy(xpath = "//a[text()='LinkedIn']")
    private WebElement LinkedInPageButton;
    
    public BasePage(WebDriver driver) 
    {
        PageFactory.initElements(driver, this);
    }
    
    public WebElement getShoppingCartIcon() {return shoppingCartIcon;}

    public WebElement getBurgerMenuButton() {return burgerMenuButton;}

    public WebElement getCloseMenuButton() {return closeMenuButton;}

    public WebElement getAllItemsLink() {return allItemsLink;}

    public WebElement getAboutLink() {return aboutLink;}

    public WebElement getLogoutLink() {return logoutLink;}

    public WebElement getResetAppStateLink() {return resetAppStateLink;}

    public WebElement getTwitterPageButton() {return TwitterPageButton;}

    public WebElement getFacebookPageButton() {return FacebookPageButton;}

    public WebElement getLinkedInPageButton() {return LinkedInPageButton;}

}
