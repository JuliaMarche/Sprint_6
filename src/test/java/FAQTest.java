import org.example.pageObject.MainPage;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FAQTest {

    private WebDriver driver;
    private MainPage mainPage;

    @BeforeEach
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--no-sandbox", "--disable-dev-shm-usage");
        driver = new ChromeDriver(options);
        //driver = new FirefoxDriver();
        mainPage = new MainPage(driver);
        mainPage.openPage();
        mainPage.coockieButtonClick();
    }

    @AfterEach
    public void tearDown() {
            driver.quit();
    }

    @ParameterizedTest
    @CsvSource(delimiter = '|', value = {
            "0|Сутки — 400 рублей. Оплата курьеру — наличными или картой.",
            "1|Пока что у нас так: один заказ — один самокат. Если хотите покататься с друзьями, можете просто сделать несколько заказов — один за другим.",
            "2|Допустим, вы оформляете заказ на 8 мая. Мы привозим самокат 8 мая в течение дня. Отсчёт времени аренды начинается с момента, когда вы оплатите заказ курьеру. Если мы привезли самокат 8 мая в 20:30, суточная аренда закончится 9 мая в 20:30.",
            "3|Только начиная с завтрашнего дня. Но скоро станем расторопнее.",
            "4|Пока что нет! Но если что-то срочное — всегда можно позвонить в поддержку по красивому номеру 1010.",
            "5|Самокат приезжает к вам с полной зарядкой. Этого хватает на восемь суток — даже если будете кататься без передышек и во сне. Зарядка не понадобится.",
            "6|Да, пока самокат не привезли. Штрафа не будет, объяснительной записки тоже не попросим. Все же свои.",
            "7|Да, обязательно. Всем самокатов! И Москве, и Московской области."
    })
    public void checkFAQAnswer(int index, String expectedAnswer) {
        mainPage.clickFAQQuestion(index);
        String actualAnswer = mainPage.getFAQAnswer(index);
        assertEquals(expectedAnswer, actualAnswer, "Ответ на вопрос " + index + " не совпадает с ожидаемым");
    }
}
