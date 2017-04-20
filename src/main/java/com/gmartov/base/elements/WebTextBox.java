package com.gmartov.base.elements;

import com.gmartov.base.drivers.BaseDriver;
import org.openqa.selenium.By;

public class WebTextBox extends BaseElement {
    public WebTextBox(BaseDriver driver, String xpath) {
        super(driver, By.xpath(xpath));
    }

    public void setText(String t) {
        getVisibleWebElement().sendKeys(t);
    }
}
