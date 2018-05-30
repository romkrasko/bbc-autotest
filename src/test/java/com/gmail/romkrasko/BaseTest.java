package com.gmail.romkrasko;
import com.gmail.romkrasko.BrowserFactory;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;
import static com.gmail.romkrasko.BrowserFactory.*;

public class BaseTest {

    private BrowserFactory singleton = BrowserFactory.getInstance();

    @BeforeTest
    public void setUp() {
        driver = singleton.setDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("http://www.bbc.com/");
    }

    @Test
    public void tests() {
        WebElement searchField = driver.findElement(By.cssSelector("#orb-search-q"));
        searchField.sendKeys("lol");
        WebElement headerNews = driver.findElement(By.cssSelector("#orb-nav-links .orb-nav-newsdotcom a"));
        headerNews.click();
        WebElement searchString = driver.findElement(By.cssSelector("#orb-search-q"));
        boolean inputEnabled = driver.findElement(By.cssSelector("#orb-search-q")).isEnabled();
        boolean inputDisplayed = driver.findElement(By.cssSelector("#orb-search-q")).isDisplayed();

        if (inputEnabled == true) {
            System.out.println("verification success for isEnabled() method");
        }

        if (inputDisplayed == true) {
            System.out.println("verification success for isDisplayed() method");
        }

        WaitUntilElementIsDisplay(searchString);
        WaitUntilElementIsEnable(searchString);
    }

    @AfterTest
    public void closeBrowser() {
        if (driver != null) {
            driver.quit();
        }
    }

    public void WaitUntilElementIsDisplay(WebElement element) {
        (new WebDriverWait(driver, 10)).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
                try {
                    return element.isDisplayed();
                }
                catch (NoSuchElementException e) {
                    return false;
                }
            }
        });
    }

    public void WaitUntilElementIsEnable(WebElement element) {
        (new WebDriverWait(driver, 10)).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
                try {
                    return element.isEnabled();
                }
                catch (NoSuchElementException e) {
                    return false;
                }
            }
        });
    }

}