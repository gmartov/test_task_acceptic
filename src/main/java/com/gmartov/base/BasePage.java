package com.gmartov.base;

import com.gmartov.base.drivers.BaseDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class BasePage {

    private BaseDriver driver;

    public BasePage(BaseDriver driver, String title) {
        setDriver(driver);
        waitForDisplayed(title);
    }

    public void waitForDisplayed(String title) {
        getDriver().waitExpected(ExpectedConditions.titleIs(title));
    }

    public BaseDriver getDriver() {
        return driver;
    }

    public void setDriver(BaseDriver driver) {
        this.driver = driver;
    }
}
