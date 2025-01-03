package com.swagLabs.pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage 
{
    @FindBy(xpath = "//input[@data-test='username']")
    private WebElement usernameField;

    @FindBy(xpath = "//input[@data-test='password']")
    private WebElement passwordField;

    @FindBy(xpath = "//input[@data-test='login-button']")
    private WebElement loginButton;

    @FindBy(xpath = "//div[@class='error-message-container']")
    private WebElement errorMessageContainer;

    @FindBy(xpath = "//div[@data-test='login-credentials']")
    private WebElement loginCredentialsContainer;

    @FindBy(xpath = "//div[@data-test='login-password']")
    private WebElement loginPasswordContainer;

    @FindBy(xpath = "//div[@class='login_logo']")
    private WebElement loginLogo;


    public LoginPage(WebDriver driver) 
    {
        PageFactory.initElements(driver, this);
    }
    
    
    public WebElement getUsernameField() { return usernameField; }

    public WebElement getPasswordField() { return passwordField; }

    public WebElement getLoginButton() { return loginButton; }

    public WebElement getErrorMessageContainer() { return errorMessageContainer; }

    public WebElement getLoginCredentialsContainer() { return loginCredentialsContainer; }

    public WebElement getLoginPasswordContainer() { return loginPasswordContainer; }

    public WebElement getLoginLogo() { return loginLogo; }
    
    
    
    
    
}
















