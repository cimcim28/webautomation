package com.webautomation.locator;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Alert {
        public static void main(String[] args) throws InterruptedException {

            WebDriverManager.chromedriver().setup(); // Automatically manages chromedriver
            WebDriver driver = new ChromeDriver();
            driver.get("https://rahulshettyacademy.com/AutomationPractice/");

            driver.findElement(By.id("name")).sendKeys("Albert");

            driver.findElement(By.id("alertbtn")).click();

            System.out.println(driver.switchTo().alert().getText());

            Thread.sleep(2000);

            driver.switchTo().alert();

    }
}
