package oneAutomation.NaukriWeb;

import oneAutomation.AbstractPageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Created by Ati on 21-02-2016.
 */
public class LandingPage extends AbstractPageObject{
    public LandingPage(WebDriver driver){
        super(driver);
    }

    @Override
    public By getUniqueElementOnPage() {
        return By.id("login_Layer");
    }

    By loginLink = By.id("login_Layer");
    By emailIdInput = By.id("eLogin");
    By passLoginInput = By.id("pLogin");
    By loginButton = By.cssSelector("[value='Login']");

    public void clickLoginButton(){
        driver.findElement(loginLink).click();
    }

    public HomePage doLogin(String email, String password){
        driver.findElement(emailIdInput).click();
        driver.findElement(emailIdInput).clear();
        driver.findElement(emailIdInput).sendKeys(email);
        driver.findElement(passLoginInput).click();
        driver.findElement(passLoginInput).clear();
        driver.findElement(passLoginInput).sendKeys(password);
        driver.findElement(loginButton).click();
        return new HomePage(driver);
    }

}
