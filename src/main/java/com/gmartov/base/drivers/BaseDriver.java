package com.gmartov.base.drivers;

import com.gmartov.base.Log;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import java.util.concurrent.TimeUnit;

public abstract class BaseDriver {

    public final static int IMPLICITLY_WAIT_MS = 100;
    public final static int PAGE_LOAD_TIMEOUT_S = 30;
    public final static int DEFAULT_TIMEOUT_S = 10;

    private WebDriver driver;

    public void quit() {
        Log.trace("Trying to quit driver ...");
        if (getDriver() != null) {
            try {
                getDriver().close();
                getDriver().quit();
                setDriver(null); // :)
            } catch (Exception ignored) {
            }
        }
    }

    void init() {
        setDriver(initDriver());

        getDriver().manage().timeouts().implicitlyWait(IMPLICITLY_WAIT_MS, TimeUnit.MILLISECONDS);
        getDriver().manage().timeouts().pageLoadTimeout(PAGE_LOAD_TIMEOUT_S, TimeUnit.SECONDS);
    }

    protected abstract WebDriver initDriver();

    public WebDriver getDriver() {
        return this.driver;
    }

    public void setDriver(WebDriver driver) {
        this.driver = driver;
    }


    public void waitExpected(ExpectedCondition<Boolean> condition, int... timeOutSec) {
        wait(timeOutSec).until(condition);
    }

    private Wait<WebDriver> wait(int... timeOutSec) {
        return new FluentWait<>(getDriver())
                .withTimeout(timeOutSec.length > 0 ? timeOutSec[0] : DEFAULT_TIMEOUT_S, TimeUnit.SECONDS)
                .pollingEvery(100, TimeUnit.MILLISECONDS)
                .ignoring(NoSuchElementException.class, InvalidElementStateException.class)
                .ignoring(AssertionError.class, StaleElementReferenceException.class);
    }

    public void switchToFrame(String frameName) {
        getDriver().switchTo().frame(frameName);
    }

    public void switchToFrame(WebElement el) {
        getDriver().switchTo().frame(el);
    }

    public void switchToDefaultContent() {
        getDriver().switchTo().defaultContent();
    }

}
