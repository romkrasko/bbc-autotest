package com.gmail.romkrasko;

import org.openqa.selenium.*;
import java.util.concurrent.TimeUnit;
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
        WaitUntilElementIsEnable();
        WaitUntilElementIsDisplay();
    }

    @AfterTest
    public void closeBrowser() {
        if (driver != null) {
            driver.quit();
        }
    }

    public void WaitUntilElementIsEnable() {
        long startTime = System.currentTimeMillis();
        while (true) {
            if (System.currentTimeMillis() - startTime > 10000) {
                throw new TimeoutException();
            }
            try {
                System.out.println(driver.findElement(By.cssSelector("#orb-search-q")).isEnabled());
                break;
            } catch (NoSuchElementException e) {
            }
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                System.out.println("lol((");
            }
        }
    }

    public void WaitUntilElementIsDisplay() {
        long startTime = System.currentTimeMillis();
        while (true) {
            if (System.currentTimeMillis() - startTime > 10000) {
                throw new TimeoutException();
            }
            try {
                System.out.println(driver.findElement(By.cssSelector("#orb-search-q")).isDisplayed());
                break;
            } catch (NoSuchElementException e) {
            }
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                System.out.println("lol((");
            }
        }
    }


}