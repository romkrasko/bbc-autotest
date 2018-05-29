package com.gmail.romkrasko;

import com.gmail.romkrasko.DriverCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import java.util.concurrent.TimeUnit;
import static com.gmail.romkrasko.DriverCapabilities.chromeCapabilities;

public class BrowserFactory implements DriverCapabilities {

        private static volatile BrowserFactory instance;
        public static WebDriver driver;

        public static BrowserFactory getInstance() {
            BrowserFactory localInstance = instance;
            if (localInstance == null) {
                synchronized (BrowserFactory.class) {
                    localInstance = instance;
                    if (localInstance == null) {
                        instance = localInstance = new BrowserFactory();
                    }
                }
            }
            return localInstance;
        }

    private static ThreadLocal<WebDriver> driverThread = new ThreadLocal<>();

    public synchronized WebDriver setDriver() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\romkr\\work\\chromedriver\\chromedriver.exe");


        driverThread.set(new ChromeDriver(chromeCapabilities()));
        driver = driverThread.get();

        return driver;
    }
}
