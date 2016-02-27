package oneAutomation.WebAndMobEndToEnd;

import oneAutomation.BaseTest;
import oneAutomation.NaukriMob.DashBoardPage;
import oneAutomation.NaukriMob.MyProfilePage;
import oneAutomation.NaukriWeb.HomePage;
import oneAutomation.NaukriWeb.LandingPage;
import oneAutomation.NaukriWeb.ProfilePage;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Properties;

/**
 * Created by Ati on 21-02-2016.
 */
public class LanguageUpdateTest extends BaseTest {
    @Test(groups = {"webFirst"})
    public void verifyOnMobThatExperienceIsUpdated(){
        LandingPage landingPage = new LandingPage(driver);
        landingPage.clickLoginButton();
        HomePage homePage = landingPage.doLogin("atishay_shukla2@yahoo.co.in", "9670094920");
        ProfilePage profilePage = homePage.hoverEditProfileAndClickEditProfile();
        profilePage.selectExperience("06");
        Assert.assertEquals(profilePage.getExperienceSelectedValue(),"6");
        homePage = profilePage.setSaveButton();
        Assert.assertTrue(homePage.verifyNotifyBlockWithText("updated"));
        homePage.clickLogOutLink();
    }
    @Test(groups = {"mobSecond"})
    public void verifyThatMobShowsUpdatedInfo(){
        DashBoardPage dashBoardPage = new DashBoardPage(driver);
        MyProfilePage profilePage = dashBoardPage.clickLoginName();
        Assert.assertTrue(profilePage.getBasicExp().contains("6"));
    }
}
