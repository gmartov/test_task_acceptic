package com.gmartov.base;


import com.gmartov.base.drivers.BaseDriver;
import com.gmartov.base.drivers.ChromeDriverWrapper;
import com.gmartov.pages.PagesList;
import org.testng.ITestResult;
import org.testng.TestNGException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;

public abstract class BaseTest {

    private final Browser defaultBrowser = Browser.CHROME;
    private BaseDriver driver;

    public void startNewSession(Browser browser) {
        if (driver != null) {
            driver.quit();
        }
        initDriver(browser);
    }

    public BaseDriver getDriver() {
        return driver;
    }

    private void initDriver(Browser browser) {
        if (browser == null) {
            // TODO Should be in properties
            browser = Browser.CHROME;
        }

        switch (browser) {
            case CHROME:
            default:
                driver = new ChromeDriverWrapper();
        }
    }

    @AfterClass(alwaysRun = true)
    public void closeBrowser() {
        getDriver().quit();
        Log.trace(" Browser was closed");
    }

    @AfterMethod(alwaysRun = true)
    public void after(ITestResult result) {
        if (!result.isSuccess() && null != getDriver()) {
            //takeScreenShot;
            Log.trace("Test fail");
        }
    }


    public <T> T navigateTo(Class<T> page, Browser... browser) {
        if (getDriver() == null) {
            startNewSession(browser.length == 0 ? defaultBrowser : browser[0]);
        }

        PagesList pageItem = Arrays.stream(PagesList.values())
                .filter(p -> p.getaClass().equals(page)).findFirst()
                .orElseThrow(() -> new TestNGException("Page not in the list"));

        if (getDriver().getDriver().getCurrentUrl().endsWith(pageItem.getLink())) {
            Log.info("Look like navigation is not required");
        } else {
            getDriver().getDriver().navigate().to(pageItem.getLink());
        }

        try {
            return page.getConstructor(BaseDriver.class).newInstance(driver);
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            throw new TestNGException("Error during create page instance" + page.toString() + e);
        } catch (Exception e1) {
            throw new TestNGException("Other error create page instance " + e1);
        }

    }


}
