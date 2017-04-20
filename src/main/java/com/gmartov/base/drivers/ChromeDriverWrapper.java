package com.gmartov.base.drivers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

public class ChromeDriverWrapper extends BaseDriver {

    public ChromeDriverWrapper() {
        init();
    }

    private static void setChromeDriver() {
        String os = System.getProperty("os.name").toLowerCase().substring(0, 3);
        String chromeBinary = "src/main/resources/drivers/chrome/chromedriver"
                + (os.equals("win") ? ".exe" : "");
        System.setProperty("webdriver.chrome.driver", chromeBinary);
    }

    protected WebDriver initDriver() {
        DesiredCapabilities capabilities = DesiredCapabilities.chrome();
        setChromeDriver();
        return new ChromeDriver(capabilities);
    }
}
