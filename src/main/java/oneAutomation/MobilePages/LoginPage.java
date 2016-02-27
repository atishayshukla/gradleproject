package oneAutomation.MobilePages;

import io.appium.java_client.MobileBy;
import oneAutomation.AbstractPageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Created by Ati on 16-02-2016.
 */
public class LoginPage extends AbstractPageObject {
    public LoginPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public By getUniqueElementOnPage() {
        return MobileBy.id("com.sd2labs.infinity:id/signin_textView");
    }
    // Locators

    private By emailInputField = MobileBy.id("com.sd2labs.infinity:id/email_editText");
    private By passwordInputField = MobileBy.id("com.sd2labs.infinity:id/password_editText");
    private By loginButton = MobileBy.id("com.sd2labs.infinity:id/signin_imageButton");

    // Defining methods

    public void setEmailInputField(String email){
        driver.findElement(emailInputField).click();
        driver.findElement(emailInputField).clear();
        driver.findElement(emailInputField).sendKeys(email);
    }

    public void setPasswordInputField(String password){
        driver.findElement(passwordInputField).click();
        driver.findElement(passwordInputField).clear();
        driver.findElement(passwordInputField).sendKeys(password);
    }

    public void clickLoginButton(){
        driver.findElement(loginButton).click();
    }
}
