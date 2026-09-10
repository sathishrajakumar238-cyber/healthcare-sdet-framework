package com.healthcare.framework;

import com.healthcare.framework.factory.DriverFactory;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;

public class DriverFactoryTest {

    @Test
    public void driverOpensAndClosesBrowser() {
        WebDriver driver = DriverFactory.getDriver();
        driver.get("https://google.com");
        System.out.println("Page title: " + driver.getTitle());
        DriverFactory.quitDriver();
    }
}