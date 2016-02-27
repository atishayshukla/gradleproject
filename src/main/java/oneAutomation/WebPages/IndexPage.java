package oneAutomation.WebPages;

import oneAutomation.AbstractPageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

/**
 * Created by Ati on 20-02-2016.
 */
public class IndexPage extends AbstractPageObject {
    public IndexPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public By getUniqueElementOnPage() {
        return By.cssSelector(".row");
    }

    By interactionSection = By.cssSelector(".row:nth-child(2)>div:nth-child(1)");
    By widgetSection = By.cssSelector(".row:nth-child(2)>div:nth-child(2)");
    By dropDown = By.xpath("(.//a[@href='dropdown.php'])[2]");

    public int getCountOfBoxesUnderInteractionSection(){
        return driver.findElement(interactionSection).findElements(By.tagName("li")).size();
    }
    public int getCountOfBoxesUnderWidgetSection(){
        return driver.findElement(widgetSection).findElements(By.tagName("li")).size();
    }
    public DropDownPage clickDropDown(){
        wait.until(ExpectedConditions.visibilityOfElementLocated(dropDown));
        clickWithTry(dropDown);
        return new DropDownPage(driver);
    }

}
