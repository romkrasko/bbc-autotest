package com.gmail.romkrasko;
import com.gmail.romkrasko.BrowserFactory;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
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
        boolean inputEnabled = driver.findElement(By.cssSelector("#orb-nav-links .orb-nav-newsdotcom a")).isEnabled();
        boolean inputDisplayed = driver.findElement(By.cssSelector("#orb-nav-links .orb-nav-newsdotcom a")).isDisplayed();

        if (inputEnabled==true) {
            System.out.println("verification success for isEnabled() method");
        }

        if (inputDisplayed==true) {
            System.out.println("verification success for isDisplayed() method");
        }
    }

    @AfterTest
    public void closeBrowser() {
        if (driver != null) {
            driver.quit();
        }
    }

}