package ru.praktikumServices.QaScooter.pom;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

import static org.junit.Assert.*;


public class MainPage {
    private static final String URL = "https://qa-scooter.praktikum-services.ru/";

    private final WebDriver webDriver;

    private final By topButtonOrder = By.xpath(".//button[@class = 'Button_Button__ra12g']");
    private final By middleButtonOrder = By.xpath(".//button[@class = 'Button_Button__ra12g Button_UltraBig__UU3Lp']");
    private final By openQuestionsListFAQ = By.xpath(".//div[@class = 'accordion__heading']");
    private final By hiddenAnswersListFAQ = By.xpath(".//div[@data-accordion-component='AccordionItemPanel']");

    public MainPage(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public MainPage open() {
        webDriver.get(URL);
        return this;
    }

    public MainPage clickTopButtonOrder() {
        webDriver.findElement(topButtonOrder).click();
        return this;
    }

    public MainPage clickMiddleButtonOrder() {
        webDriver.findElement(middleButtonOrder).click();
        return this;
    }


    public MainPage checkVisibilityAllQuestionsListFAQ() {

        List<WebElement> elementsQuestions = webDriver.findElements(openQuestionsListFAQ);
        List<WebElement> elementsAnwers = webDriver.findElements(hiddenAnswersListFAQ);
        int i = 0;
        for (WebElement e : elementsQuestions) {
            ((JavascriptExecutor)webDriver).executeScript("arguments[0].scrollIntoView();", e);
            e.click();
            checkHiddenElement(elementsAnwers.get(i));
            i++;
        }
        return this;
    }

    public void checkHiddenElement(WebElement e) {
        assertNull("Элемент" + e + " скрыт или не виден", e.getAttribute("hidden"));
    }

}
