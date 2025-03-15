package automation;

import java.time.Duration;
import java.util.HashMap;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.webautomation.pageobjects.CartPage;
import com.webautomation.pageobjects.ConfirmationPage;
import com.webautomation.pageobjects.LandingPage;
import com.webautomation.pageobjects.OrderPage;
import com.webautomation.pageobjects.ProductListPage;

import io.github.bonigarcia.wdm.WebDriverManager;

public class StandAloneTesNGImplTest {
    /*
     * Annotasi
     * dataprovider
     * Running testng
     */

    public WebDriver driver;

    @BeforeMethod
    public void setUp(){
        // Setup Driver
        WebDriverManager.chromedriver().setup();
        this.driver = new ChromeDriver();  // Perbaikan: Gunakan variabel instance
        driver.get("https://rahulshettyacademy.com/client");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.manage().window().maximize();
    }

    @Test(dataProvider = "dataTestMapping")
    public void createOrder(HashMap<String, String> input) throws InterruptedException{
        LandingPage landingPage = new LandingPage(driver);
        landingPage.loginApplication(input.get("userEmail"), input.get("password"));

        String productName = "ZARA COAT 3";
        ProductListPage productListPage = new ProductListPage(driver);
        productListPage.addToCart(productName);

        driver.findElement(By.cssSelector("[routerlink*='cart']")).click();
       
        CartPage cartPage = new CartPage(driver);
        Assert.assertTrue(cartPage.verifyCheckoutProduct(productName));
        cartPage.goToCheckoutPage();
       
        String destination = "Indonesia";

        OrderPage orderPage = new OrderPage(driver);
        orderPage.selectCountry(destination);
        orderPage.submitOrder();
        
        ConfirmationPage confirmationPage = new ConfirmationPage(driver);
        String confirmationText = confirmationPage.getConfirmationPage();

        Assert.assertEquals(confirmationText, "THANKYOU FOR THE ORDER.");
    }

    @AfterMethod
    public void tearDown(){
        if (driver != null) {
            driver.quit(); // Perbaikan: Menggunakan quit() agar Chrome benar-benar tertutup
        }
    }

    @DataProvider
    public Object[][] dataTest(){
        return new Object[][]{
            {"simanjuntakalbert57@gmail.com", "XBf@rWNvByn!#K8", "ZARA COAT 3" },
        };
    }

    @DataProvider
    public Object[][] dataTestMapping(){
        HashMap<String, String> map = new HashMap<>();
        map.put("userEmail", "simanjuntakalbert57@gmail.com");
        map.put("password", "XBf@rWNvByn!#K8");
        map.put("productName", "ZARA COAT 3");

        return new Object[][] {{map}};
    }
}
