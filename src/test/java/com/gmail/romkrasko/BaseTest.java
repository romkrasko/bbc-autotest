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
        WebElement searchField1 = driver.findElement(By.cssSelector("#orb-search-q"));
        WaitUntilElementIsEnable(searchField1);
        WaitUntilElementIsEnable(searchField);
        WaitUntilElementIsDisplay(searchField1);
        WaitUntilElementIsDisplay(searchField);
    }

    @AfterTest
    public void closeBrowser() {
        if (driver != null) {
            driver.quit();
        }
    }

    public void WaitUntilElementIsEnable(WebElement element) {
        long startTime = System.currentTimeMillis();
        while (true) {
            if (System.currentTimeMillis() - startTime > 10000) {
                System.out.println("Time out(");
                break;
            }
            try {
                System.out.println(element.isEnabled());
                break;

            }catch (NoSuchElementException e) {}
            catch (StaleElementReferenceException e){
            }
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                System.out.println("lol((");
            }
        }
    }

    public void WaitUntilElementIsDisplay(WebElement element) {
        long startTime = System.currentTimeMillis();
        while (true) {
            if (System.currentTimeMillis() - startTime > 10000) {
                System.out.println("Time out(");
                break;
            }
            try {
                System.out.println(element.isDisplayed());
                break;

            }catch (NoSuchElementException e) {}
            catch (StaleElementReferenceException e){
            }
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                System.out.println("lol((");
            }
        }
    }
}