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

import java.time.Duration;

import static java.time.temporal.ChronoUnit.SECONDS;
import static ru.praktikumServices.QaScooter.constants.Color.COLOR_BLACK;
import static ru.praktikumServices.QaScooter.constants.Color.COLOR_GREY;
import static ru.praktikumServices.QaScooter.constants.Driver.CHROME_WEBDRIVER;
import static ru.praktikumServices.QaScooter.constants.Result.RESULT_ORDER_SCOOTER_POSITIVE;


@RunWith(Parameterized.class)
public class CheckQuestions {
    private WebDriver webDriver;

    @Parameterized.Parameter
    public String externalWebDriver;

    @Parameterized.Parameters(name = "{index} Сценарий проверки всех вопросов из раздела FAQ WebDriver: {0}")
    public static Object[][] data() {
        return new Object[][] {
                { CHROME_WEBDRIVER},
                { CHROME_WEBDRIVER}
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
    public void clickAllQuestionsFAQ() {
        MainPage mainPage = new MainPage(webDriver);

        mainPage.open()
                .checkVisibilityAllQuestionsListFAQ();
    }

    @After
    public void tearDown() {
        webDriver.quit();
    }

}
