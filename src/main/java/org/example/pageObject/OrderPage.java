package org.example.pageObject;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class OrderPage {
    private final WebDriver driver;

    //Поле "Имя"
    private final By nameInput = By.xpath(".//input[@placeholder ='* Имя']");
    //Поле "Фамилия"
    private final By lastNameInput = By.xpath(".//input[@placeholder ='* Фамилия']");
    //Поле "Адрес"
    private final By adressInput = By.xpath(".//input[@placeholder ='* Адрес: куда привезти заказ']");
    //Поле "Станция метро"
    private final By metroInput = By.xpath(".//input[@placeholder ='* Станция метро']");
    private final String metroChoose = ".//div[text()='%s']";
    //Поле "Телефон"
    private final By phoneInput = By.xpath(".//input[@placeholder ='* Телефон: на него позвонит курьер']");
    //Кнопка "Далее"
    private final By nextButton = By.xpath("//button[text()='Далее']");

    public OrderPage(WebDriver driver){
        this.driver = driver;
    }

    public void setName(String firstName) {
        WebElement nameElement = driver.findElement(nameInput);
        nameElement.clear();
        nameElement.sendKeys(firstName);
    }

    public void setLastName(String lastName) {
        WebElement lastNameElement = driver.findElement(lastNameInput);
        lastNameElement.clear();
        lastNameElement.sendKeys(lastName);
    }

    public void setAdress(String adress) {
        WebElement adressElement = driver.findElement(adressInput);
        adressElement.clear();
        adressElement.sendKeys(adress);
    }

    public void setMetro(String metroName) {
        WebElement metroInputElement = driver.findElement(metroInput);
        metroInputElement.click();
        String metroNameChoose = String.format(metroChoose, metroName);
        WebElement stationElement = new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.elementToBeClickable(By.xpath(metroNameChoose)));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", stationElement);
        stationElement.click();
    }

    public void setPhoneNumber(String phoneNumber) {
        WebElement phoneNumberElement = driver.findElement(phoneInput);
        phoneNumberElement.clear();
        phoneNumberElement.sendKeys(phoneNumber);
    }

    public void nextButtonClick() {
        WebElement buttonElement = driver.findElement(nextButton);
        buttonElement.click();
    }

    public void fillOrderPage(String name, String lastName, String adress, String metroName, String phoneNumber) {
        setName(name);
        setLastName(lastName);
        setAdress(adress);
        setMetro(metroName);
        setPhoneNumber(phoneNumber);
        nextButtonClick();
    }
}
