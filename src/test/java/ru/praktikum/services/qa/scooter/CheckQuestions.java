package ru.praktikum.services.qa.scooter;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import ru.praktikum.services.qa.scooter.pom.MainPage;
import ru.praktikum.services.qa.scooter.rules.BaseTest;


@RunWith(Parameterized.class)
public class CheckQuestions {


    @Parameterized.Parameters(name = "{index} Сценарий проверки всех вопросов из раздела FAQ WebDriver: {0}")
    public static Object[][] data() {
        return new Object[][] {
                { },
                { }
        };
    }

    @Rule
    public BaseTest baseTest = new BaseTest();


    @Test
    public void clickAllQuestionsFAQ() {


        MainPage mainPage = new MainPage(baseTest.getWebDriver());

        mainPage.open()
                .checkVisibilityAllQuestionsListFAQ();
    }


}
