package ru.praktikumServices.QaScooter.pom;

import org.openqa.selenium.*;

import java.util.List;

import static org.junit.Assert.assertTrue;

public class OrderPage {
    private final WebDriver webDriver;

    private final By inputName = By.xpath( ".//input [@placeholder='* Имя']");
    private final By inputSurname = By.xpath( ".//input [@placeholder='* Фамилия']");
    private final By inputAddress = By.xpath( ".//input [@placeholder='* Адрес: куда привезти заказ']");
    private final By inputMetroStation = By.xpath( ".//input [@class = 'select-search__input']");
    private final By liMetroStationChoice = By.xpath(".//li [@class = 'select-search__row']");
    private final By inputPhone = By.xpath( ".//input [@placeholder='* Телефон: на него позвонит курьер']");

    private final By buttonNext = By.xpath(".//button [@class = 'Button_Button__ra12g Button_Middle__1CSJM']");

    private final By inputDateOrder = By.xpath( ".//input [@placeholder='* Когда привезти самокат']");
    private final By divChooseRentalPeriod = By.xpath( ".//div [@class = 'Dropdown-control']");
    private final By divChoiceRentalPeriod = By.xpath( ".//div [@class = 'Dropdown-option']");//множественный блок

    private final By inputColorGrey = By.xpath( ".//input [@id = 'grey']");
    private final By inputColorBlack = By.xpath( ".//input [@id = 'black']");

    private final By inputComment = By.xpath( ".//input [@placeholder = 'Комментарий для курьера']");

    private final By buttonTakeOrder = By.xpath( ".//button [@class = 'Button_Button__ra12g Button_Middle__1CSJM']");

    private final By buttonConfirmOrder = By.xpath( ".//button [text() = 'Да']");

    private final By divMessageOrder = By.xpath(".//div [@class = 'Order_ModalHeader__3FDaJ']");


    public OrderPage(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public void fillInput(By elementInput, String value) {
        WebElement element = webDriver.findElement(elementInput);
        ((JavascriptExecutor)webDriver).executeScript("arguments[0].scrollIntoView();", element);
        webDriver.findElement(elementInput).sendKeys(value);
    }

    public OrderPage fillFirstPartForm(String name, String surname, String address, String metroStation, String phone) {

        fillInput(inputName, name);
        fillInput(inputSurname, surname);
        fillInput(inputAddress, address);
        fillInput(inputMetroStation, metroStation);
        webDriver.findElement(liMetroStationChoice).click();
        fillInput(inputPhone, phone);
        return this;
    }

    public OrderPage clickButtonNext() {

        webDriver.findElement(buttonNext).click();

        return this;
    }

    public OrderPage fillSecondPartForm(String dateOrder, int countDays, String color, String comment) {

        fillInput(inputDateOrder, dateOrder);
        webDriver.findElement(inputDateOrder).sendKeys(Keys.RETURN);

        webDriver.findElement(divChooseRentalPeriod).click();
        List<WebElement> elementsRentalPeriod = webDriver.findElements(divChoiceRentalPeriod);
        elementsRentalPeriod.get(countDays - 1).click();

        if ("black".equals(color)) {
            webDriver.findElement(inputColorBlack).click();
        }
        else if ("grey".equals(color)){
            webDriver.findElement(inputColorGrey).click();
        }

        fillInput(inputComment, comment);

        return this;
    }

    public OrderPage clickButtonTakeOrder() {

        webDriver.findElement(buttonTakeOrder).click();

        return this;
    }

    public OrderPage clickButtonConfirmOrder() {

        webDriver.findElement(buttonConfirmOrder).click();

        return this;
    }


    public OrderPage checkSuccessfulCreateOrder() {
        assertTrue("Сообщения об успешном заказе нет", webDriver.findElement(divMessageOrder).getText().contains("Заказ оформлен"));

        return this;
    }

}
