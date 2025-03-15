package com.webautomation.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.webautomation.abstractcomponents.AbstractComponent;

public class LandingPage extends AbstractComponent {
    WebDriver driver;

    public LandingPage(WebDriver driver){
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "userEmail")
    WebElement userEmail;

    @FindBy(id = "userPassword")
    WebElement userPassword;

    @FindBy(className = "login-btn")
    WebElement loginBtn;

    By cartButton = By.id("userEmail");

    public void loginApplication(String email, String password){
        visibilityOfElementLocated(cartButton);
        userEmail.sendKeys(email);
        userPassword.sendKeys(password);
        loginBtn.click();
    }

}
