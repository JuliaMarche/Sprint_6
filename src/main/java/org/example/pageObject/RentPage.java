package org.example.pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class RentPage {
    private final WebDriver driver;

    //Поле "Когда привезти самокат"
    private final By dateChoose = By.xpath(".//input[@placeholder ='* Когда привезти самокат']");
    //Поле "Срок ареды"
    private final By selectRentPeriod = By.xpath(".//div[@class='Dropdown-control']");
    private final String rentPeriodChoose = "//div[text()='%s']";
    //Поле "Цвет самоката"
    private final String colourChoose = "//label[contains(text(), '%s')]";
    //Поле "Комментарий для курьера"
    private final By commentInput = By.xpath(".//input[@placeholder ='Комментарий для курьера']");
    //Кнопка "Заказать"
    private final By orderButton = By.xpath(".//div[contains(@class, 'Order_Buttons')]/button[text()='Заказать']");
    //Кнопка "Да" в модальном окне
    private final By yesButton = By.xpath(".//button[text()='Да']");
    //Модальное окно об успешном заказе
    private final By confirmedOrder = By.xpath("//*[text()='Заказ оформлен']");

    public RentPage(WebDriver driver) {
        this.driver = driver;
    }

    public void setDate(String date) {
        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.elementToBeClickable(dateChoose));
        WebElement dateElement = driver.findElement(dateChoose);
        dateElement.click();
        dateElement.sendKeys(date);
        dateElement.sendKeys(Keys.ENTER);
    }

    public void setRentPeriod(String rentPeriod) {
        WebElement rentElement = driver.findElement(selectRentPeriod);
        rentElement.click();
        By rentOptionBy = By.xpath(String.format(rentPeriodChoose, rentPeriod));
        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.elementToBeClickable(rentOptionBy)).click();
    }

    public void setColour(String colour) {
        By colourOption = By.xpath(String.format(colourChoose, colour));
        WebElement colourElement = driver.findElement(colourOption);
        colourElement.click();
    }

    public void setComment(String comment) {
        WebElement commentElement = driver.findElement(commentInput);
        commentElement.clear();
        commentElement.sendKeys(comment);
    }

    public void orderButtonClick() {
        WebElement orderButtonElement = driver.findElement(orderButton);
        orderButtonElement.click();
    }

    public void yesButtonClick() {
        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.elementToBeClickable(yesButton)).click();
    }

    public String getConfirmationText() {
        return new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.visibilityOfElementLocated(confirmedOrder)).getText();
    }

    public void fillRentPage(String date, String rentPeriod, String colour, String comment){
        setDate(date);
        setRentPeriod(rentPeriod);
        setColour(colour);
        setComment(comment);
        orderButtonClick();
        yesButtonClick();
    }
}
