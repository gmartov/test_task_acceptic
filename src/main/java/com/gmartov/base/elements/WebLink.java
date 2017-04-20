package com.gmartov.base.elements;

import com.gmartov.base.drivers.BaseDriver;
import org.openqa.selenium.By;

public class WebLink extends BaseElement {

    public WebLink(BaseDriver driver, String xpath) {
        super(driver, By.xpath(xpath));
    }

    public void click() {
        getClickableElement().click();
    }
}
