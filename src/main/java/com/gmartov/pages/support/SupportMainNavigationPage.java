package com.gmartov.pages.support;

import com.gmartov.base.BasePage;
import com.gmartov.base.drivers.BaseDriver;
import com.gmartov.base.elements.WebDropdown;
import com.gmartov.base.elements.WebLink;
import com.gmartov.pages.PagesList;
import org.openqa.selenium.By;

public class SupportMainNavigationPage extends BasePage {

    private final By locIFrame = By.xpath("//iframe[contains(@id, 'nanoRep_frame')]");
    private String xpathTemplateIcon = "//div[@class='main-row'and position() = 2]//div[@class='main-row__frame']//li[contains(@class,'icons-list__item')]/a[@href='%s']";

    public SupportMainNavigationPage(BaseDriver driver) {
        super(driver, PagesList.SUPPORT_MAIN_NAVIGATION.getTitle());
    }

    public SupportMainNavigationPage(BaseDriver driver, String title) {
        super(driver, title);
    }

    public SupportSelectDevicePage selectDeviceGuides() {
        new WebLink(getDriver(), String.format(xpathTemplateIcon, "/Device-Guides/")).click();
        return new SupportSelectDevicePage(getDriver());
    }

    public SupportMainNavigationPage search(String text, String... exactQuestion) {
        getDriver().switchToFrame(getDriver().getDriver().findElement(locIFrame));
        new WebDropdown(getDriver(), "//div[@id='searchTextboxContainer']", "//div[@class = 'nr-autocomplete-drop']/ul/li")
                .select(text, exactQuestion);
        getDriver().switchToDefaultContent();
        return this;
    }

    public void expandAnswer() {
        getDriver().switchToFrame(getDriver().getDriver().findElement(locIFrame));
        new WebLink(getDriver(), "//div[@id='widgetBottomSection']//div[@class='nr-answering-answer-top-menu']//div[@class='nr-answering-answer-buttons-align']//a[@aria-label='Go to article page']").click();
        getDriver().switchToDefaultContent();
    }

}
