package components;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;


public class BaseTest {

    public static WebDriver driver; // Ubah menjadi static

    public static WebDriver initializeDriver() throws IOException { // Ubah method jadi static
        Properties properties = new Properties();
        FileInputStream fileInputStream = new FileInputStream("src/main/resources/GlobalData.properties");
        properties.load(fileInputStream);

        String browserName = properties.getProperty("browser");

        if (browserName.equalsIgnoreCase("chrome")) {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
        } else if (browserName.equalsIgnoreCase("edge")) {
            WebDriverManager.edgedriver().setup();
            driver = new EdgeDriver();
        } else {
            throw new IllegalArgumentException("Unsupported browser: " + browserName);
        }

        driver.manage().window().maximize();
        return driver;
    }
}
