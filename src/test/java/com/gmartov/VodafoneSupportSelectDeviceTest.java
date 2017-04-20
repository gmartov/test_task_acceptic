package com.gmartov;

import com.gmartov.base.BaseTest;
import com.gmartov.base.Browser;
import com.gmartov.pages.support.SupportMainNavigationPage;
import com.gmartov.pages.support.SupportSelectDevicePage;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

public class VodafoneSupportSelectDeviceTest extends BaseTest {

    private SupportMainNavigationPage mainNavigationPage;

    @BeforeClass
    public void init() {
        mainNavigationPage = navigateTo(SupportMainNavigationPage.class, Browser.CHROME);
    }

    @Test
    public void searchDeviceByMenu() {
        String phoneBrand = "Samsung";
        String phoneModel = "Galaxy Y";
        List<String> menuElements = mainNavigationPage
                .selectDeviceGuides()
                .selectDevice(SupportSelectDevicePage.DeviceType.PHONE, phoneBrand, phoneModel)
                .getMenuElements();

        Assert.assertEquals(menuElements.get(1), phoneBrand);
        Assert.assertEquals(menuElements.get(2), phoneModel);
    }

    @Test
    public void searchByText() {
        String phoneModel = "Galaxy A";
        String phoneModelExact = phoneModel + "ce";
        List<String> menuElements = mainNavigationPage
                .selectDeviceGuides()
                .searchDevice(phoneModel, phoneModelExact)
                .getMenuElements();

        Assert.assertEquals(menuElements.get(2), phoneModelExact);
    }

}
