package com.gmartov.pages.support;

import com.gmartov.base.drivers.BaseDriver;
import com.gmartov.base.elements.WebDropdown;
import com.gmartov.base.elements.WebLink;
import com.gmartov.pages.PagesList;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;
import java.util.stream.Collectors;

public class SupportSelectDevicePage extends SupportMainNavigationPage {

    private final String xpathMenu = "//div[contains(@class, 'main-row') and position()=3]//ul[contains(@class, 'dialog__breadcrumbs__list')]/li/a";
    private String xpathTemplate = "//h2[.='%s']/../ul/li/a[./span/span[.='%s']]";

    public SupportSelectDevicePage(BaseDriver driver) {
        super(driver, PagesList.SUPPORT_DEVICE_GUIDES_SELECT_DEVICE.getTitle());
        List<String> menuElements = getMenuElements();
        if (menuElements.size() > 0) {
            new WebLink(driver, xpathMenu + "[" + 1 + "]").click();
        }
    }


    //Probably this method should be in helper class and each action implemented separately on page level.
    public SupportSelectDevicePage selectDevice(DeviceType deviceType, String deviceBrand, String deviceModel) {
        String xpathType = String.format(xpathTemplate, "Select your device type", deviceType.text);
        String xpathBrand = String.format(xpathTemplate, "Select the make of your device", deviceBrand);
        String xpathModel = String.format(xpathTemplate, "Select the model", deviceModel);
        new WebLink(getDriver(), xpathType).click();
        // wait not needed here really but just to be sure that I can dot it :)
        getDriver().waitExpected(ExpectedConditions.invisibilityOfElementLocated(By.xpath(xpathType)));
        new WebLink(getDriver(), xpathBrand).click();
        getDriver().waitExpected(ExpectedConditions.invisibilityOfElementLocated(By.xpath(xpathBrand)));
        new WebLink(getDriver(), xpathModel).click();
        return this;
    }


    public SupportSelectDevicePage searchDevice(String text, String... exactDeviceName) {
        new WebDropdown(getDriver(), "//h2[.='Or search for your device']/following::form", "/ul/li")
                .select(text, exactDeviceName);
        return this;
    }

    public List<String> getMenuElements() {
        return getDriver().getDriver().findElements(By.xpath(xpathMenu))
                .stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
    }

    public enum DeviceType {
        PHONE("Phone");

        private String text;

        DeviceType(String text) {
            this.text = text;
        }
    }
}
