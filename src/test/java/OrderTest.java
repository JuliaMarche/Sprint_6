import org.example.pageObject.MainPage;
import org.example.pageObject.OrderPage;
import org.example.pageObject.RentPage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class OrderTest {

    private WebDriver driver;
    private MainPage mainPage;
    private OrderPage orderPage;
    private RentPage rentPage;

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
        mainPage.coockieButtonClick();
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }

    @ParameterizedTest
    @CsvSource({
            "header, Иван, Иванов, 'Москва, Кутузовский проспект 32', Лубянка, +79048889933, 01.12.2025, сутки, чёрный жемчуг, Комментарий один",
            "middle, Ольга, Иванова, Светлановский 67 , Спортивная, 89112223344, 19.10.2025, двое суток, серая безысходность, Комментарий 2"
    })

    void orderTest(String buttonPosition,
                   String name,
                   String lastName,
                   String address,
                   String metro,
                   String phone,
                   String date,
                   String rentPeriod,
                   String colour,
                   String comment) {

        if (buttonPosition.equals("header")) {
            mainPage.orderButtonHeaderClick();
        } else if (buttonPosition.equals("middle")) {
            mainPage.scrollToButtonMiddle();
            mainPage.orderButtonMiddleClick();
        }

        orderPage.fillOrderPage(name, lastName, address, metro, phone);
        rentPage.fillRentPage(date, rentPeriod, colour, comment);

        String confirmationText = rentPage.getConfirmationText();
        assertTrue(confirmationText.contains("Заказ оформлен"), "Заказ не оформлен");
    }
}
