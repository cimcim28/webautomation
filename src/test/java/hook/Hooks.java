package hook;

import java.io.IOException;
import java.io.InputStream;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.github.bonigarcia.wdm.WebDriverManager;

public class Hooks {
    public static WebDriver driver;
    
    // setup driver
    @Before
    public void setupAutomation() throws IOException{
        Properties properties = new Properties();
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("GlobalData.properties");

        if (inputStream == null) {
            throw new IOException("Property file 'GlobalData.properties' not found in the classpath");
        }

        properties.load(inputStream);
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
        driver.get("https://rahulshettyacademy.com/client");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        System.out.println("ini adaldah driver1" + driver);
    }

    // close driver
    @After
    public void tearDownAutomation(){
        if (driver != null) {
            driver.close();
        }
    }

    public static WebDriver initializeDriver(){
        System.out.println("ini adaldah driver" + driver);
        return driver;
    }
}