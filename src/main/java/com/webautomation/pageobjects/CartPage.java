package com.webautomation.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;




public class CartPage {
     WebDriver driver;   

    public CartPage(WebDriver driver){
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = ".totalRow button" )
    WebElement btnCheckout;

    public void goToCheckoutPage(){
        visibilityOfElementLocated(rowButton);
        btnCheckout.click();
    }



}
