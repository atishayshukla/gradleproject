package oneAutomation.NaukriWeb;

import oneAutomation.AbstractPageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

/**
 * Created by Ati on 21-02-2016.
 */
public class ProfilePage extends AbstractPageObject {
    public ProfilePage(WebDriver driver) {
        super(driver);
    }

    @Override
    public By getUniqueElementOnPage() {
        return By.xpath(".//h1[contains(text(),'Snapshot')]");
    }

    By experienceDropDown = By.id("experienceYear");
    By saveButton = By.cssSelector("[value='Save Changes']");

    public void selectExperience(String year){
        wait.until(ExpectedConditions.visibilityOfElementLocated(experienceDropDown));
        Select select = new Select(driver.findElement(experienceDropDown));
        select.selectByValue(year);
    }

    public HomePage setSaveButton(){
        driver.findElement(saveButton).click();
        return new HomePage(driver);
    }

    public String getExperienceSelectedValue(){
        wait.until(ExpectedConditions.visibilityOfElementLocated(experienceDropDown));
        Select select = new Select(driver.findElement(experienceDropDown));
        return select.getFirstSelectedOption().getText();
    }
}
