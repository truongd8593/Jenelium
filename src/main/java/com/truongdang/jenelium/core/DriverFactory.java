package com.truongdang.jenelium.core;

/**
 * @author Truong Dang.
 * Created on Jul, 2021.
 */

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;

import java.util.ArrayList;
import java.util.List;

public class DriverFactory {

    public static WebDriver createDriver(BrowserType browserType) throws Exception {
        List<String> defaultCapabilities = getDefaultCapabilities();

        switch (browserType) {
            case CHROME:
                WebDriverManager.chromedriver().setup();
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments(defaultCapabilities);
                return new ChromeDriver(chromeOptions);
            case IE:
                WebDriverManager.iedriver().setup();
                return new InternetExplorerDriver();
            case FIREFOX:
                WebDriverManager.firefoxdriver().setup();
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                firefoxOptions.addArguments(defaultCapabilities);
                return new FirefoxDriver();
            default:
                System.out.println("Please specify browser type !!!");
                return null;
        }
    }

    private static List<String> getDefaultCapabilities() {
        List<String> capability = new ArrayList<>();
        capability.add("--headless");
        capability.add("--disable-gpu");
        capability.add("--start-maximized");
        capability.add("--window-size=1920,1080");
        return capability;
    }

}
