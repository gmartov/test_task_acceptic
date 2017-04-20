package com.gmartov.base.elements;

import com.gmartov.base.Log;
import com.gmartov.base.drivers.BaseDriver;
import com.gmartov.utils.Sleeper;
import org.openqa.selenium.By;
import org.testng.TestNGException;

public class WebDropdown {

    private BaseDriver driver;
    private String xpathBase;
    private String xpathToMenu;

    public WebDropdown(BaseDriver driver, String xpathBase, String xpathToMenu) {
        this.driver = driver;
        this.xpathBase = xpathBase;
        this.xpathToMenu = xpathToMenu;
    }

    public void select(String text, String... exactDeviceName) {
        String exactText = exactDeviceName.length > 0 ? exactDeviceName[0] : text;
        new WebTextBox(driver, xpathBase + "//input").setText(text);
        Sleeper.sleep(1);
        int listSize = driver.getDriver().findElements(By.xpath(xpathBase + xpathToMenu)).size();
        if (listSize > 1 && exactDeviceName.length == 0) {
            Log.info("WARNING: found more that one element! " + text);
        }
        for (int i = 0; i < listSize; i++) {
            WebLink link = new WebLink(driver, xpathBase + xpathToMenu + "[" + (i + 1) + "]");
            String itemText = link.getText();
            if (itemText.toLowerCase().contains(exactText.toLowerCase())) {
                link.click();
                return;
            }
        }
        throw new TestNGException("Item is not found " + text + " " + exactText);
    }
}
