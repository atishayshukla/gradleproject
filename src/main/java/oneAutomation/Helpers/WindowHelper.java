package oneAutomation.Helpers;

import org.openqa.selenium.WebDriver;

import java.util.Iterator;
import java.util.Set;

/**
 * Created by Ati on 21-02-2016.
 */
public class WindowHelper {
    /*public static void switchToDesiredWindowWithTitleAndCloseRest(WebDriver driver){
        String parentWindowHandle = driver.getWindowHandle();
        Set<String> getAllWindowHandles = driver.getWindowHandles();
        for (String s: getAllWindowHandles){
            if (!s.equals(parentWindowHandle)){
                driver.switchTo().window(s).close();
            }
        }
        driver.switchTo().window(parentWindowHandle);
    }*/

    public static int windowNumber(WebDriver driver){
        Set<String> windows = driver.getWindowHandles();
        System.out.println(windows.size());
        return windows.size();
    }

    public static void switchToWindowWithTitleAndCloseOthers(WebDriver driver, String title){
        String currentWindowHandle = "";
        String windowToKeep = "";
        String currentTitle = "";
        Set<String> allWindowHandles = driver.getWindowHandles();
        Iterator<String> itr = allWindowHandles.iterator();
        while (itr.hasNext()){
            currentWindowHandle = itr.next();
            currentTitle = driver.switchTo().window(currentWindowHandle).getTitle();
            if (!currentTitle.contains(title)){
                driver.close();
                TimeHelper.pause(1);
            }
        }
        driver.switchTo().window(currentWindowHandle);
    }

    public static String getWindowTitle(WebDriver driver){
        String window = driver.getWindowHandle();
        String title = driver.switchTo().window(window).getTitle();
        return title;
    }

    public static void closeAllWindowsExceptParent(WebDriver driver){
        String parentWindow = driver.getWindowHandle();
        Set<String> allWindowHandles = driver.getWindowHandles();

        for (String s: allWindowHandles){
            if (!s.equals(parentWindow)){
                driver.switchTo().window(s);
                driver.close();
            }
        }
        driver.switchTo().window(parentWindow);
    }
}
