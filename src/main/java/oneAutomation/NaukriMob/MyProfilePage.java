package oneAutomation.NaukriMob;

import io.appium.java_client.MobileBy;
import oneAutomation.AbstractPageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Created by Ati on 22-02-2016.
 */
public class MyProfilePage extends AbstractPageObject {

    public MyProfilePage(WebDriver driver) {
        super(driver);
    }

    @Override
    public By getUniqueElementOnPage() {
        return MobileBy.xpath("//android.widget.TextView[@text='My Profile']");
    }

    By basicExp = MobileBy.id("naukriApp.appModules.login:id/basicExperience");

    public String getBasicExp(){
        return driver.findElement(basicExp).getText();
    }
}
