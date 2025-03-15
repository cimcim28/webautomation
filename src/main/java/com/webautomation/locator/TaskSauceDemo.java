package com.webautomation.locator;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TaskSauceDemo {

    public static void main(String[] args) throws InterruptedException {
        WebDriverManager.chromedriver().setup(); // Automatically manages chromedriver
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.saucedemo.com/v1/index.html");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.manage().window().maximize();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(1000));

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[data-test='username']")));
        driver.findElement(By.cssSelector("[data-test='username']")).sendKeys("standard_user");

        driver.findElement(By.cssSelector("[data-test='password']")).sendKeys("secret_sauce");

        driver.findElement(By.id("login-button")).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("inventory_item")));

        List<WebElement> inventoryItems = driver.findElements(By.className("inventory_item"));

        // Filter dan klik item "Sauce Labs Bike Light"
        inventoryItems.stream()
            .filter(item -> {
                // Ambil nama item dari elemen inventory_item_name
                String itemName = item.findElement(By.className("inventory_item_name")).getText();
                return itemName.equals("Sauce Labs Bike Light");
            })
            .findFirst()
            .ifPresent(item -> {
                WebElement addToCartButton = item.findElement(By.xpath(".//button[text()='ADD TO CART']"));
                addToCartButton.click();
            });       

        driver.findElement(By.cssSelector("a.shopping_cart_link")).click();
        
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("a.btn_action")));

        driver.findElement(By.cssSelector("a.btn_action")).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[data-test='firstName']")));
        driver.findElement(By.cssSelector("[data-test='firstName']")).sendKeys("Jhon");
        driver.findElement(By.cssSelector("[data-test='lastName']")).sendKeys("Doe");
        driver.findElement(By.cssSelector("[data-test='postalCode']")).sendKeys("12345");

        driver.findElement(By.cssSelector("input.btn_primary.cart_button[value='CONTINUE']")).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("a.btn_action.cart_button[href='./checkout-complete.html']")));
        driver.findElement(By.cssSelector("a.btn_action.cart_button[href='./checkout-complete.html']")).click();

        String success = driver.findElement(By.cssSelector("h2.complete-header")).getText();
        System.out.println("user success: " + success);
        driver.quit();

    }

}
