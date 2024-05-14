package ru.praktikumServices.QaScooter;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import ru.praktikumServices.QaScooter.pom.MainPage;
import ru.praktikumServices.QaScooter.pom.OrderPage;

import java.time.Duration;

import static java.time.temporal.ChronoUnit.SECONDS;
import static ru.praktikumServices.QaScooter.constants.Color.COLOR_BLACK;
import static ru.praktikumServices.QaScooter.constants.Color.COLOR_GREY;
import static ru.praktikumServices.QaScooter.constants.Driver.CHROME_WEBDRIVER;
import static ru.praktikumServices.QaScooter.constants.Driver.FIREFOX_WEBDRIVER;
import static ru.praktikumServices.QaScooter.constants.Result.RESULT_ORDER_SCOOTER_POSITIVE;


@RunWith(Parameterized.class)
public class OrderScooterWithTopButton {
    private WebDriver webDriver;

    @Parameterized.Parameter
    public String name;
    @Parameterized.Parameter(1)
    public String surname;
    @Parameterized.Parameter(2)
    public String address;
    @Parameterized.Parameter(3)
    public String metroStation;
    @Parameterized.Parameter(4)
    public String phone;
    @Parameterized.Parameter(5)
    public String dateOrder;
    @Parameterized.Parameter(6)
    public int countDays;
    @Parameterized.Parameter(7)
    public String color;
    @Parameterized.Parameter(8)
    public String comment;

    @Parameterized.Parameter(9)
    public String result;

    @Parameterized.Parameter(10)
    public String externalWebDriver;

    @Parameterized.Parameters(name = "{index} Сценарий заказа самоката с параметрами:\nИмя: {0},\nФамилия: {1},\nАдрес: {2},\nСтанция метро: {3},\nТелефон: {4},\nДата доставки: {5},\nКоличество дней: {6},\nЦвет самоката: {7},\nКомментарий: {8},\nОжидаемый результат: {9},\nБраузер: {10}")
    public static Object[][] data() {
        return new Object[][] {
                { "Григорий", "Распутин", "Москва, Шоссе космонавтов, д. 18, кв. 13", "Бабушкинская", "+79998887766",  "16.05.2024", 4, COLOR_GREY, "Комментарий от Распутина", RESULT_ORDER_SCOOTER_POSITIVE, CHROME_WEBDRIVER},
                { "Иван", "Федоров", "Москва, Академика Янгеля, д. 2, кв. 320", "Пушкинская", "+79098080706",  "22.05.2024", 6, COLOR_BLACK, "Комментарий от Федорова", RESULT_ORDER_SCOOTER_POSITIVE, CHROME_WEBDRIVER}
        };
    }

    @Before
    public void setup() {
        if ("firefox".equals(externalWebDriver)) {
            webDriver = new FirefoxDriver();
        } else if ("chrome".equals(externalWebDriver)) {
            webDriver = new ChromeDriver();
        }
        webDriver.manage().timeouts().implicitlyWait(Duration.of(3, SECONDS));
    }

    @Test
    public void orderScooterWithTopButtonPositive() {
        MainPage mainPage = new MainPage(webDriver);
        OrderPage orderPage = new OrderPage(webDriver);

        mainPage.open()
                .clickTopButtonOrder();

        orderPage.fillFirstPartForm(name, surname, address, metroStation, phone)
                .clickButtonNext()
                .fillSecondPartForm(dateOrder, countDays, color, comment)
                .clickButtonTakeOrder()
                .clickButtonConfirmOrder()
                .checkSuccessfulCreateOrder(result);

    }


    @After
    public void tearDown() {
        webDriver.quit();
    }

}
