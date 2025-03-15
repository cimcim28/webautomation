package com.webautomation.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;


public class OrderPage {
    WebDriver driver;   

    public OrderPage(WebDriver driver){
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

     @FindBy(css = "[placeholder = 'Select Country']")
    WebElement selectCountry;

    @FindBy(xpath = "//span[@class='ng-star-inserted']")
    List<WebElement> listCountry;

    @FindBy(css = ".action__submit")
    WebElement placeOrderBtn;

    By elementSelectCountry = By.cssSelector("[placeholder = 'Select Country']");
    By elementListCountry = By.xpath("//span[@class='ng-star-inserted']");

    public void selectCountry(String destination){
        visibilityOfElementLocated(elementSelectCountry);
        Actions action =  new Actions(driver);
        action.sendKeys(selectCountry,destination).build().perform();
        
        visibilityOfElementLocated(elementListCountry);
        WebElement cont = listCountry.stream().filter(cont2 -> 
        cont2.getText().equalsIgnoreCase(destination)).findFirst().orElse(null);
        cont.click();
    }

    public void submitOrder(){
        placeOrderBtn.click();
    }

}
