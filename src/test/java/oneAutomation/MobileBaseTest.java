package oneAutomation;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

/**
 * Created by Ati on 16-02-2016.
 * This will launch the Appium driver specific to mobile devices
 */
public class MobileBaseTest {
    private AndroidDriver<AndroidElement> driver;
    private static AppiumDriverLocalService service;

    @BeforeClass
    public void setupAndroidDriver(){
        service = AppiumDriverLocalService.buildDefaultService();
        service.start();
    }
    @BeforeMethod
    public void setupMobile(){

    }
 }
