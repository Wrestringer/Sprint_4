package ru.praktikum.services.qa.scooter.rules;

import org.junit.rules.ExternalResource;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.time.Duration;

import static java.time.temporal.ChronoUnit.SECONDS;


public class BaseTest extends ExternalResource {

    protected WebDriver webDriver;

    public BaseTest () {
        webDriver = new ChromeDriver();
    }

    public BaseTest (String externalWebDriver) {
        if ("firefox".equals(externalWebDriver)) {
            this.webDriver = new FirefoxDriver();
        }
        else if ("chrome".equals(externalWebDriver)) {
            this.webDriver = new ChromeDriver();
        }
        else {
            this.webDriver = new ChromeDriver();
        }
    }


    protected void before() {
        webDriver.manage().timeouts().implicitlyWait(Duration.of(3, SECONDS));
    }

    protected void after() {
        webDriver.quit();
    }

    public WebDriver getWebDriver() {
        return webDriver;
    }

}
