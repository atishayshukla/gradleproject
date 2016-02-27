package oneAutomation.WebTests;

import oneAutomation.BaseTest;
import oneAutomation.WebPages.DropDownPage;
import oneAutomation.WebPages.IndexPage;
import oneAutomation.WebPages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by Ati on 20-02-2016.
 */
public class CountTotalNoOfBoxTest extends BaseTest {
    @Test
    public void validateTotalNoOfBoxes(){
        LoginPage loginPage = new LoginPage(driver);
        IndexPage indexPage = loginPage.doLogin("atishay", "atishay");
        Assert.assertEquals(indexPage.getCountOfBoxesUnderInteractionSection(), 5);
    }
    @Test
    public void validateTotalBoxesInWidget(){
        LoginPage loginPage = new LoginPage(driver);
        IndexPage indexPage = loginPage.doLogin("atishay","atishay");
        Assert.assertEquals(indexPage.getCountOfBoxesUnderWidgetSection(), 7);
    }
    @Test
    public void clickOnDropDown(){
        LoginPage loginPage = new LoginPage(driver);
        IndexPage indexPage = loginPage.doLogin("atishay","atishay");
        DropDownPage dropDownPage = indexPage.clickDropDown();
        dropDownPage.setSelectCountry("India");
        dropDownPage.setEnterCountry("India");
    }
}
