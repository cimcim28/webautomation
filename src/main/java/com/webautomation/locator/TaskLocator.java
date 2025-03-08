package com.webautomation.locator;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.Alert; // Import kelas Alert dari Selenium

import io.github.bonigarcia.wdm.WebDriverManager;

public class TaskLocator {

    public static void main(String[] args) throws InterruptedException {
        
        // radioButtonExample();
        // sugessionClassExample();
        // dropdownExample();
        // checkboxExample();
        // switchWindowExample();
        // switchTabExample();
        // switchToAlertExample();
        // elementDisplayedExample();

    }

    public static void radioButtonExample() throws InterruptedException {
        WebDriverManager.chromedriver().setup(); // Automatically manages chromedriver
        WebDriver driver = new ChromeDriver();
        driver.get("https://rahulshettyacademy.com/AutomationPractice/");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));

        driver.findElement(By.name("radioButton")).click();
        Thread.sleep(2000);

        driver.quit();
    }

    public static void sugessionClassExample() throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.get("https://rahulshettyacademy.com/AutomationPractice/");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    
        // Input teks "ind"
        WebElement input = driver.findElement(By.id("autocomplete"));
        input.sendKeys("ind");
    
        // Tunggu suggestions muncul
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ui-menu-item")));
    
        // Ambil semua elemen suggestions
        List<WebElement> suggestions = driver.findElements(By.cssSelector(".ui-menu-item"));
    
        // Iterasi dan pilih "Indonesia"
        for (WebElement suggestion : suggestions) {
            String text = suggestion.getText();
            System.out.println("Negara: " + text);
            if (text.equals("Indonesia")) {
                suggestion.click();
                break;
            }
        }
    
        Thread.sleep(2000); // Hanya untuk demo, sebaiknya gunakan explicit wait
        driver.quit();
    }

    public static void dropdownExample() throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.get("https://rahulshettyacademy.com/AutomationPractice/");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));

        List<WebElement> options = driver.findElements(By.xpath("//select[@id='dropdown-class-example']//child::option"));

        for (WebElement option : options) {
            if (option.getText().equals("Option1")) {
                option.click();
                break;
            }
        }
        driver.quit();
    }

    public static void checkboxExample() throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.get("https://rahulshettyacademy.com/AutomationPractice/");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));

        List<WebElement> checkboxes = driver.findElements(
        By.xpath("//div[@id='checkbox-example']//input[@type='checkbox']"));

        for (WebElement checkbox : checkboxes) {
            checkbox.click();
        }
        driver.quit();
    }

    public static void switchWindowExample() throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.get("https://rahulshettyacademy.com/AutomationPractice/");

        // Buka tab baru
        driver.findElement(By.id("openwindow")).click();

        // Get the new window handle
        String mainWindowHandle = driver.getWindowHandle();
        System.out.println("New window handle: " + mainWindowHandle);

        // Tunggu hingga window baru muncul dan switch ke window baru
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.numberOfWindowsToBe(2)); // Pastikan ada 2 window

        // Loop untuk switch ke window baru
        for (String handle : driver.getWindowHandles()) {
            if (!handle.equals(mainWindowHandle)) {
                driver.switchTo().window(handle);
                break;
            }
        }

        // Tunggu elemen di window baru (ganti dengan elemen yang benar-benar ada)
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("domain")));
        
        // Ambil title window baru
        System.out.println("Title window baru: " + driver.getTitle());

        // Tutup window baru dan kembali ke window utama
        driver.close();
        driver.switchTo().window(mainWindowHandle);
        
        driver.quit();
    }

    public static void switchTabExample() throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.get("https://rahulshettyacademy.com/AutomationPractice/");

        // Buka tab baru
        driver.findElement(By.id("opentab")).click();

        // Get the new tab handle
        String mainTabHandle = driver.getWindowHandle();
        System.out.println("New tab handle: " + mainTabHandle);

        // Tunggu hingga tab baru muncul dan switch ke tab baru
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.numberOfWindowsToBe(2)); // Pastikan ada 2 tab

        // Loop untuk switch ke tab baru
        for (String handle : driver.getWindowHandles()) {
            if (!handle.equals(mainTabHandle)) {
                driver.switchTo().window(handle);
                break;
            }
        }
        Thread.sleep(5000);
        driver.quit();
    }

    public static void switchToAlertExample() throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.get("https://rahulshettyacademy.com/AutomationPractice/");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    
            // Masukkan teks dan klik tombol alert
        driver.findElement(By.id("name")).sendKeys("Cim");
        driver.findElement(By.id("alertbtn")).click();
    
            // Tangani alert pertama
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.alertIsPresent());
            
        Alert alert = driver.switchTo().alert();
        System.out.println("Alert Text: " + alert.getText());
        alert.accept();
    
            // Contoh tambahan: Klik tombol confirm untuk alert kedua
        driver.findElement(By.id("confirmbtn")).click();
        wait.until(ExpectedConditions.alertIsPresent());
            
        Alert confirmAlert = driver.switchTo().alert();
        System.out.println("Confirm Alert Text: " + confirmAlert.getText());
        confirmAlert.dismiss(); // Klik cancel/tidak setuju

        Thread.sleep(3000);
    
        driver.quit();
    }

    public static void elementDisplayedExample() throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.get("https://rahulshettyacademy.com/AutomationPractice/");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));

        driver.findElement(By.id("show-textbox")).click();

        // Cek apakah elemen di id="displayed-text" tampil
        WebElement element = driver.findElement(By.id("displayed-text"));
        boolean isDisplayed = element.isDisplayed();
        System.out.println("Element displayed: " + isDisplayed);

        Thread.sleep(3000);

        driver.findElement(By.id("displayed-text")).sendKeys("WKWKW");

        driver.quit();
    }

}
