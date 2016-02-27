package oneAutomation.WebPages;

import oneAutomation.AbstractPageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

/**
 * Created by Ati on 20-02-2016.
 */
public class LoginPage extends AbstractPageObject {
    public LoginPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public By getUniqueElementOnPage() {
        return By.xpath("//*[contains(text(),'Already')]");
    }

    By signInButton = By.cssSelector("a[href$='in']");
    By userNameInput = By.xpath("(.//input[@type='text' and @name='username'])[2]");
    By passwordInput = By.xpath("(.//input[@type='password' and @name='password'])[2]");
    By submitButton = By.xpath("(//*[@value='Submit'])[2]");

    public void clickSignInButton(){
        driver.findElement(signInButton).click();
    }

    public IndexPage doLogin(String username, String password){
        clickSignInButton();
        wait.until(ExpectedConditions.visibilityOfElementLocated(userNameInput));
        driver.findElement(userNameInput).click();
        driver.findElement(userNameInput).sendKeys(username);
        driver.findElement(passwordInput).click();
        driver.findElement(passwordInput).sendKeys(password);
        driver.findElement(submitButton).click();
        return new IndexPage(driver);
    }
}
