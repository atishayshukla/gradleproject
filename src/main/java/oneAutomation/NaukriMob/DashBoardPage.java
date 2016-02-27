package oneAutomation.NaukriMob;

import io.appium.java_client.MobileBy;
import oneAutomation.AbstractPageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Created by Ati on 22-02-2016.
 */
public class DashBoardPage extends AbstractPageObject {
    public DashBoardPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public By getUniqueElementOnPage() {
        return MobileBy.id("naukriApp.appModules.login:id/profileMeter");
    }

    By loginName = MobileBy.id("naukriApp.appModules.login:id/name");

    public MyProfilePage clickLoginName(){
        driver.findElement(loginName).click();
        return new MyProfilePage(driver);
    }
}
