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
public class RegistrationPage extends AbstractPageObject {
    public RegistrationPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public By getUniqueElementOnPage() {
        return By.xpath("//*[contains(text(),'Already')]");
    }
    // Locators for Registration page

    By nameInput = By.name("name");
    By phoneInput = By.name("phone");
    By emailInput = By.name("email");
    By countryDrop = By.name("country");
    By cityInput = By.name("city");
    By userNameInput = By.xpath("(.//input[@type='text' and @name='username'])[2]");
    By passwordInput = By.xpath("(.//input[@type='password' and @name='password'])[2]");
    By submitButton = By.xpath("(//*[@value='Submit'])[2]");

    public void setNameInput(String name){
        driver.findElement(nameInput).click();
        driver.findElement(nameInput).clear();
        driver.findElement(nameInput).sendKeys(name);
    }

    public void setPhoneInput(String phone){
        driver.findElement(phoneInput).click();
        driver.findElement(phoneInput).clear();
        driver.findElement(phoneInput).sendKeys(phone);
    }

    public void setEmailInput(String email){
        driver.findElement(emailInput).click();
        driver.findElement(emailInput).clear();
        driver.findElement(emailInput).sendKeys(email);
    }

    public void setCountryDrop(String country){
        WebElement countryDropValue = driver.findElement(countryDrop);
        Select select = new Select(countryDropValue);
        select.selectByValue(country);
    }

    public void setCityInput(String city){
        driver.findElement(cityInput).click();
        driver.findElement(cityInput).clear();
        driver.findElement(cityInput).sendKeys(city);
    }

    public void setUserNameInput(String userName){
        wait.until(ExpectedConditions.visibilityOfElementLocated(userNameInput));
        driver.findElement(userNameInput).click();
        driver.findElement(userNameInput).clear();
        driver.findElement(userNameInput).sendKeys(userName);
    }

    public void setPasswordInput(String password){
        driver.findElement(passwordInput).click();
        driver.findElement(passwordInput).clear();
        driver.findElement(passwordInput).sendKeys(password);
    }

    public void clickSubmitButton(){
        driver.findElement(submitButton).click();
    }
}
