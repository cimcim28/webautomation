package com.webautomation.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.webautomation.abstractcomponents.AbstractComponent;

public class CartPage extends AbstractComponent{
    WebDriver driver;   

    public CartPage(WebDriver driver){
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = ".totalRow button" )
    WebElement btnCheckout;

    @FindBy(css = ".cartSection h3")
    List<WebElement> listProducts;

    By rowButton = By.cssSelector(".totalRow button");

    public void goToCheckoutPage(){
        visibilityOfElementLocated(rowButton);
        btnCheckout.click();
    }

    public Boolean verifyCheckoutProduct(String productName){
        visibilityOfElementLocated(rowButton);
        Boolean match = listProducts.stream().anyMatch(cartProduct -> cartProduct.getText().equalsIgnoreCase(productName));

        return match;
    }
}