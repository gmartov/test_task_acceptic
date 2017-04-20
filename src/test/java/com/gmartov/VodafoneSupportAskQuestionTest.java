package com.gmartov;

import com.gmartov.base.BaseTest;
import com.gmartov.base.Browser;
import com.gmartov.base.Log;
import com.gmartov.pages.support.SupportMainNavigationPage;
import org.testng.annotations.Test;

public class VodafoneSupportAskQuestionTest extends BaseTest {

    @Test
    public void askQuestion() {
        navigateTo(SupportMainNavigationPage.class, Browser.CHROME)
                .search("wifi", "what is wi fi calling")
                .expandAnswer();

        Log.info("Page URL is " + getDriver().getDriver().getCurrentUrl());
    }


}
