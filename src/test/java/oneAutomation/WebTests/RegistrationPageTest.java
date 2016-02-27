package oneAutomation.WebTests;

import oneAutomation.BaseTest;
import oneAutomation.WebPages.RegistrationPage;
import org.testng.annotations.Test;

/**
 * Created by Ati on 20-02-2016.
 */
public class RegistrationPageTest extends BaseTest {
    @Test
    public void validateRegistrationIsSuccessful(){
        RegistrationPage registrationPage = new RegistrationPage(driver);
        registrationPage.setNameInput("Atishay");
        registrationPage.setPhoneInput("124-2576605");
        registrationPage.setEmailInput("atishayshukla@gmail.com");
        registrationPage.setCountryDrop("India");
        registrationPage.setCityInput("Gurgaon");
        registrationPage.setUserNameInput("atishay");
        registrationPage.setPasswordInput("atishay");
        registrationPage.clickSubmitButton();
    }
}
