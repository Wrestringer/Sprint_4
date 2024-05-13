package ru.praktikumServices.QaScooter;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import ru.praktikumServices.QaScooter.pom.MainPage;

import java.time.Duration;

import static java.time.temporal.ChronoUnit.SECONDS;

public class CheckQuestions {
    private WebDriver webDriver;

    @Before
    public void setup() {
        webDriver = new ChromeDriver();
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
