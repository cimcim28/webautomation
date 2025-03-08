package com.webautomation.locator;

import java.time.Duration;
import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class HandleWindow {

    public static void main(String[] args) throws InterruptedException {
        /*
         * Get id tab/window.
         * move ke tab lain menggunakan id tab tersebut
         * tab1 = 10
         * tab2 = 20
         * tab3 = 40
         * 
         * current_tab = 10
         * 
         * call tab2.id
         * call tab3.id
         */

        WebDriverManager.chromedriver().setup(); // Automatically manages chromedriver
        WebDriver driver = new ChromeDriver();
        driver.get("https://rahulshettyacademy.com/loginpagePractise/#");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        
        driver.findElement(By.className("blinkingText")).click();
        Set<String> windows = driver.getWindowHandles();

        /*
         * Bakal ada dua value [parent, child]
         * [7301AABDA9242CA1CF26BFC1C9D6BF0E, DF220471DCB65795E3EF30531088DA00]
         */
        System.out.println("Ini adalah windows" + windows);

        Iterator<String> iterator = windows.iterator();
        String parentId = iterator.next();
        String childId = iterator.next();

        Thread.sleep(5000);

        driver.switchTo().window(childId);

        Thread.sleep(3000);

        driver.switchTo().window(parentId);

        Thread.sleep(3000);

        driver.switchTo().window(parentId);

        Thread.sleep(3000);

        driver.switchTo().window(parentId);

        Thread.sleep(3000);

        driver.switchTo().window(parentId);

        driver.close();

    }

}
