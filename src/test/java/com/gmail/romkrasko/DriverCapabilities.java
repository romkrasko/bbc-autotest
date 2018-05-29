package com.gmail.romkrasko;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.Platform;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

public interface DriverCapabilities {

    static Capabilities chromeCapabilities() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("start-maximized");
        DesiredCapabilities capability = DesiredCapabilities.chrome();
        capability.setBrowserName("chrome");
        capability.setPlatform(Platform.ANY);
        capability.setCapability(ChromeOptions.CAPABILITY, options);
        return capability;
    }
}