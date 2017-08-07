package tests;

import SeleniumWebApi.infrastructure.SeleniumTestCaseBaseWebSession;
import org.junit.Assert;
import org.junit.Test;
import testdata.homepagedata.ConnectedHealthData;
import testdata.homepagedata.SemiconductorSolutionsData;


public class HomePageTests {

    private String actualUrl;
    private String actualTitle;

    @Test
    public void verifySemiconductorSolutionsMenuPageLoad() {

        verifyMenuPageLoad(SemiconductorSolutionsData.menuOurBusinessesByXpath, SemiconductorSolutionsData.subMenuSemiconductorSolutionsByXpath,
                SemiconductorSolutionsData.expectedUrl, SemiconductorSolutionsData.expectedTitle);
    }

    @Test
    public void verifyConnectedHealthMenuPageLoad() {

        verifyMenuPageLoad(ConnectedHealthData.menuOurBusinessesByXpath, ConnectedHealthData.subMenuConnectedHealthByXpath,
                ConnectedHealthData.expectedUrl, ConnectedHealthData.expectedTitle);
    }

    private void verifyMenuPageLoad(String menu, String submenu, String expectedUrl, String expectedTitle) {

        SeleniumTestCaseBaseWebSession web = new SeleniumTestCaseBaseWebSession();

        web.homePage().clickMenuOption(menu, submenu);

        actualUrl = web.homePage().returnPageUrl();
        Assert.assertEquals("Page is different than expected", expectedUrl, actualUrl);

        actualTitle = web.homePage().returnTitle();
        Assert.assertEquals("Title  is different than expected", expectedTitle, actualTitle);

        web.close();
    }
}
