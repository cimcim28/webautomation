package com.webautomation.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProductListPage {

    WebDriver driver;
    WebElement product;

    public ProductListPage(WebDriver driver){
        // super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }


    @FindBy(css = ".mb-3")
    List<WebElement> listProducts;

    By cartButton = By.xpath("//div[@class='card-body']//child::button//child::i[@class='fa fa-shopping-cart']");
    By titleProduct = By.cssSelector("b");
    By listElement = By.cssSelector(".mb-3");

    public List<WebElement> getProductList(){
        return listProducts;
    }

    public WebElement getProductByName(String productName){
        WebElement product = getProductList().stream().filter(prod -> 
        prod.findElement(titleProduct).getText().equals(productName)).findFirst().orElse(null);

        return product;
    }
    
    public void addToCart(String productName) throws InterruptedException{
        visibilityOfElementLocated(listElement);
        product = getProductByName(productName);
        product.findElement(cartButton).click();
        // visibilityOfElementLocated(toaster);
        Thread.sleep(2000);
    }

}
