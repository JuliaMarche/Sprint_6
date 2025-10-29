import org.example.pageobject.MainPage;
import org.example.pageobject.OrderPage;
import org.example.pageobject.RentPage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class BaseTest {
    protected WebDriver driver;
    protected MainPage mainPage;
    protected OrderPage orderPage;
    protected RentPage rentPage;

    @BeforeEach
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--no-sandbox", "--disable-dev-shm-usage");
        driver = new ChromeDriver(options);
        //driver = new FirefoxDriver();
        mainPage = new MainPage(driver);
        orderPage = new OrderPage(driver);
        rentPage = new RentPage(driver);
        mainPage.openPage();
        mainPage.cookieButtonClick();
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }
}
