package com.gmartov.pages;

import com.gmartov.base.BasePage;
import com.gmartov.pages.support.SupportMainNavigationPage;
import com.gmartov.pages.support.SupportSelectDevicePage;

public enum PagesList {

    SUPPORT_MAIN_NAVIGATION("Vodafone Support Centre", "http://support.vodafone.co.uk/", SupportMainNavigationPage.class),
    SUPPORT_DEVICE_GUIDES_SELECT_DEVICE("Device Guides", "http://support.vodafone.co.uk/Device-Guides/", SupportSelectDevicePage.class);

    private String title;
    private String link;
    private Class<? extends BasePage> aClass;

    PagesList(String title, String link, Class<? extends BasePage> aClass) {
        this.title = title;
        this.link = link;
        this.aClass = aClass;
    }

    public String getTitle() {
        return title;
    }

    public String getLink() {
        return link;
    }

    public Class<? extends BasePage> getaClass() {
        return aClass;
    }
}
