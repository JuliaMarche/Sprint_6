package org.example.pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class MainPage {
    private final WebDriver driver;
    public static final String BASE_URL = "https://qa-scooter.praktikum-services.ru/";


    //Кнопка для принятия куки
    private final By acceptCookieButton = By.id("rcc-confirm-button");
    //Кнопка "Заказать" вверху страницы
    private final By orderButtonHeader = By.className("Button_Button__ra12g");
    //Кнопка "Заказать" снизу страницы
    private final By orderButtonMiddle = By.xpath(".//button[@class='Button_Button__ra12g Button_Middle__1CSJM']");
    //Раздел "Вопросы о важном"
    private final String faqQuestions = "accordion__heading-";
    private final String faqAnswers = "accordion__panel-";

    public MainPage(WebDriver driver){
        this.driver = driver;
    }

    public void openPage (){
        driver.get(BASE_URL);
    }

    public void coockieButtonClick(){
        driver.findElement(acceptCookieButton).click();
    }

    public void orderButtonHeaderClick () {
        driver.findElement(orderButtonHeader).click();
    }

    public void scrollToButtonMiddle () {
        WebElement element = driver.findElement(orderButtonMiddle);
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", element);
    }

    public void orderButtonMiddleClick () {
        driver.findElement(orderButtonMiddle).click();
    }

    public void scrollToFAQ(int index) {
        WebElement element = driver.findElement(By.id(faqQuestions + index));
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", element);
    }

    public void clickFAQQuestion(int index) {
        WebElement questionElement = driver.findElement(By.id(faqQuestions + index));
        scrollToFAQ(index);
        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.elementToBeClickable(questionElement));
        questionElement.click();
    }

    public String getFAQAnswer(int index) {
        WebElement answerElement = new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(By.id(faqAnswers + index)));
        return answerElement.getText();
    }
}
