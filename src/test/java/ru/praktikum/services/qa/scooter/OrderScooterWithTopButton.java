package ru.praktikum.services.qa.scooter;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import ru.praktikum.services.qa.scooter.pom.MainPage;
import ru.praktikum.services.qa.scooter.pom.OrderPage;
import ru.praktikum.services.qa.scooter.rules.BaseTest;

import static ru.praktikum.services.qa.scooter.constants.Color.COLOR_BLACK;
import static ru.praktikum.services.qa.scooter.constants.Color.COLOR_GREY;
import static ru.praktikum.services.qa.scooter.constants.Result.RESULT_ORDER_SCOOTER_POSITIVE;


@RunWith(Parameterized.class)
public class OrderScooterWithTopButton {

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


    @Parameterized.Parameters(name = "{index} Сценарий заказа самоката с параметрами:\nИмя: {0},\nФамилия: {1},\nАдрес: {2},\nСтанция метро: {3},\nТелефон: {4},\nДата доставки: {5},\nКоличество дней: {6},\nЦвет самоката: {7},\nКомментарий: {8},\nОжидаемый результат: {9}")
    public static Object[][] data() {
        return new Object[][] {
                { "Григорий", "Распутин", "Москва, Шоссе космонавтов, д. 18, кв. 13", "Бабушкинская", "+79998887766",  "16.05.2024", 4, COLOR_GREY, "Комментарий от Распутина", RESULT_ORDER_SCOOTER_POSITIVE},
                { "Иван", "Федоров", "Москва, Академика Янгеля, д. 2, кв. 320", "Пушкинская", "+79098080706",  "22.05.2024", 6, COLOR_BLACK, "Комментарий от Федорова", RESULT_ORDER_SCOOTER_POSITIVE}
        };
    }


    @Rule
    public BaseTest baseTest = new BaseTest();


    @Test
    public void orderScooterWithTopButtonPositive() {
        MainPage mainPage = new MainPage(baseTest.getWebDriver());
        OrderPage orderPage = new OrderPage(baseTest.getWebDriver());

        mainPage.open()
                .clickTopButtonOrder();

        orderPage.fillFirstPartForm(name, surname, address, metroStation, phone)
                .clickButtonNext()
                .fillSecondPartForm(dateOrder, countDays, color, comment)
                .clickButtonTakeOrder()
                .clickButtonConfirmOrder()
                .checkSuccessfulCreateOrder(result);

    }


}
