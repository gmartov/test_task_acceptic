package com.gmartov.base.elements;

import com.gmartov.base.drivers.BaseDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BaseElement {

    protected final BaseDriver driver;
    protected final By locator;

    public BaseElement(BaseDriver driver, By locator) {
        this.driver = driver;
        this.locator = locator;
    }


    protected WebElement getVisibleWebElement() {
        return new WebDriverWait(driver.getDriver(), BaseDriver.DEFAULT_TIMEOUT_S)
                .until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    protected WebElement getClickableElement() {
        return new WebDriverWait(driver.getDriver(), BaseDriver.DEFAULT_TIMEOUT_S)
                .until(ExpectedConditions.elementToBeClickable(locator));
    }


    public String getText() {
        return getVisibleWebElement().getText();
    }

}
