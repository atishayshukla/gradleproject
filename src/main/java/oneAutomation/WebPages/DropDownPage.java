package oneAutomation.WebPages;

import oneAutomation.AbstractPageObject;
import org.omg.CORBA.portable.ValueOutputStream;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

/**
 * Created by Ati on 20-02-2016.
 */
public class DropDownPage extends AbstractPageObject {
    public DropDownPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public By getUniqueElementOnPage() {
        return By.cssSelector(".active");
    }
    By selectFrame = By.cssSelector("[id$='tab-1'] .demo-frame");
    By selectFrame2 = By.cssSelector("[id$='tab-2'] .demo-frame");
    By selectCountry = By.cssSelector("select");
    By enterCountry = By.cssSelector(".responsive-tabs>li:nth-child(2)>a");
    By inputCountry = By.cssSelector(".custom-combobox input");
    By countryAutocompleteList = By.cssSelector("ui-id-1 > li");

    public void setSelectCountry(String country){
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(selectFrame));
        WebElement countryList = driver.findElement(selectCountry);
        Select select = new Select(countryList);
        select.selectByValue(country);
    }
    public void setEnterCountry(String country){
        driver.switchTo().defaultContent();
        driver.findElement(enterCountry).click();
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(selectFrame2));
        driver.findElement(inputCountry).clear();
        driver.findElement(inputCountry).sendKeys(country);
        selectFromList(country).click();
    }
    public WebElement selectFromList(String country){
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(selectFrame2));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("ui-id-1")));
        List<WebElement> countryList = driver.findElements(countryAutocompleteList);
        for (WebElement con: countryList){
            if (con.getText().equals(country)){
                return con;
            }
            break;
        }
        return null;
    }
}
