package oneAutomation.MobileTests;

import oneAutomation.BaseTest;
import oneAutomation.MobilePages.LoginPage;
import org.testng.annotations.Test;

/**
 * Created by Ati on 16-02-2016.
 */
public class LoginPageTest extends BaseTest{
    @Test
    public void verifyLoginPageOpenTest(){
        LoginPage loginPage = new LoginPage(driver);
        loginPage.setEmailInputField("atishay");
    }
}
