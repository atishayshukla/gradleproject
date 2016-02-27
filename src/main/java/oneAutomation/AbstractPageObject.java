package oneAutomation;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

/**
 * Created by Ati on 16-02-2016.
 */
public abstract class AbstractPageObject {
    protected WebDriver driver;
    protected AndroidDriver<MobileElement> mobileDriver;
    protected WebDriverWait wait;

    public AbstractPageObject(WebDriver driver){
        this(driver,20);
    }

    public AbstractPageObject(WebDriver driver, int timeOut){
        this.driver = driver;
        this.wait = new WebDriverWait(driver,timeOut);
        waitForPageToLoad();
    }

    public abstract By getUniqueElementOnPage();

    public void waitForPageToLoad(){
        try {
           wait.until(ExpectedConditions.visibilityOfElementLocated(getUniqueElementOnPage()));
        } catch (TimeoutException e){
            Assert.fail("Timed Out Loading " + this.getClass().getSimpleName() + "Page");
        }
    }
    public void clickWithTry(By element){
        int attempt = 0;
        int maxAttempts = 3;
        try {
            while (maxAttempts > attempt){
                driver.findElement(element).click();

                break;
            }
        } catch (StaleElementReferenceException s){
            System.out.println(s.getMessage());
        }
        attempt ++;
    }
}
