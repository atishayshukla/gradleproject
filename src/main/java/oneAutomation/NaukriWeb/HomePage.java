package oneAutomation.NaukriWeb;

import oneAutomation.AbstractPageObject;
import oneAutomation.Helpers.WindowHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

/**
 * Created by Ati on 21-02-2016.
 */
public class HomePage extends AbstractPageObject {
    public HomePage(WebDriver driver) {
        super(driver);
    }

    @Override
    public By getUniqueElementOnPage() {
        return By.cssSelector(".rghtSec>li:nth-child(2)>a>div:nth-child(2)");
    }

    By editProfileLink = By.cssSelector(".rghtSec>li:nth-child(2)>a");
    By editProfileSubLink = By.cssSelector("[title='Edit Profile']");
    By notifyBlock = By.cssSelector("#confirmMessage");
    By logOutLink = By.cssSelector("[title = 'Log Out']");

    public ProfilePage hoverEditProfileAndClickEditProfile(){
        Actions actions = new Actions(driver);
        actions.moveToElement(driver.findElement(editProfileLink)).build().perform();
        driver.findElement(editProfileSubLink).click();
        WindowHelper.switchToWindowWithTitleAndCloseOthers(driver,"Update Profile");
        return new ProfilePage(driver);
    }

    public boolean verifyNotifyBlockWithText(String text){
        return driver.findElement(notifyBlock).getText().toLowerCase().contains(text);
    }

    public void clickLogOutLink(){
        Actions actions = new Actions(driver);
        actions.moveToElement(driver.findElement(editProfileLink)).build().perform();
        driver.findElement(logOutLink).click();
    }
}
