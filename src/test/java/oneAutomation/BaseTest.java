package oneAutomation;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.remote.MobilePlatform;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import oneAutomation.Helpers.TimeHelper;
import oneAutomation.Helpers.WindowHelper;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.LocalFileDetector;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

/**
 * Created by Ati on 16-02-2016.
 */
public class BaseTest {
    // Get system properties // http://way2automation.com/way2auto_jquery/index.php
    protected static final String BASE_URL = System.getProperty("BASE_URL", "http://www.naukri.com");
    protected static final String BROWSER = System.getProperty("BROWSER", "firefox");
    protected static final boolean REMOTE = Boolean.valueOf(System.getProperty("REMOTE", "false"));
    protected static final String SELENIUM_HOST = System.getProperty("SELENIUM_HOST", "localhost");
    protected static final int SELENIUM_PORT = Integer.valueOf(System.getProperty("SELENIUM_PORT", "4723"));

    protected static RemoteWebDriver driver;
    protected static AppiumDriverLocalService service;
    protected static String service_url;

    protected static final String APPIUM_NODE = System.getProperty("APPIUM_NODE", "C:\\tools\\Appium\\node.exe");
    protected static final String APPIUM_JS_PATH = System.getProperty("APPIUM_JS_PATH", "C:\\tools\\Appium\\node_modules\\appium\\bin\\appium.js");

    // Methods annotated with @BeforeClass are run once after all test methods in test class have run
    // This method creates a WebDriver session for the test methods in each test class
    @BeforeClass(groups = {"webFirst"})
    public void setupWebDriver() throws MalformedURLException {
        if (REMOTE) {
            setupRemoteWebDriver();
            driver.setFileDetector(new LocalFileDetector());
        } else {
            setupLocalWebDriver();
        }
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

    @BeforeClass(groups = {"mobSecond"})
    public void setupAppiumDriver() throws MalformedURLException {
        setUpAppium(); // For this Remote will always be true
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

    // Methods annotated with @BeforeMethod are run before each test method in test class
    @BeforeMethod(groups = {"webFirst"})
    public void loadLandingPage() {
        if (!BROWSER.equals("android")){
            driver.get(BASE_URL);
            TimeHelper.pause(0.5);
        }

        // We check if the window contains desired title to close all the popups
        WindowHelper.closeAllWindowsExceptParent(driver);
    }

    // Methods annotated with @AfterMethod are run after each test method in test class
    @AfterMethod(groups = {"webFirst"})
    public void deleteAllCookies() {
        if (!BROWSER.equals("android")){
            driver.manage().deleteAllCookies();
        }
    }

    // Methods annotated with @AfterClass are run once after all test methods in test class have run
    @AfterClass(groups = {"webFirst"})
    public void closeBrowser() {
        driver.quit();
    }

    @AfterClass(groups = {"mobSecond"})
    public void closeAppium() {
        tearDownAppium();
    }

    // Sets up a Remote WebDriver session, requires Selenium Server to be running on SELENIUM_HOST : SELENIUM_PORT
    private void setupRemoteWebDriver() throws MalformedURLException {
        DesiredCapabilities capabilities;
        switch (BROWSER) {
            case "firefox":
                capabilities = DesiredCapabilities.firefox();
                break;
            case "internetExplorer":
                capabilities = DesiredCapabilities.internetExplorer();
                capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
                break;
            case "chrome":
                capabilities = DesiredCapabilities.chrome();
                break;
            case "android":
                File appDir = new File(System.getProperty("user.dir")+"//"+"apk");
                File app = new File(appDir,"diginity.apk");
                capabilities = DesiredCapabilities.android();
                capabilities.setCapability(MobileCapabilityType.BROWSER_NAME, "");
                capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "Android Emulator");
                capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, MobilePlatform.ANDROID);
                capabilities.setCapability(MobileCapabilityType.APP_PACKAGE,"naukriApp.appModules.login");
                capabilities.setCapability(MobileCapabilityType.APP_ACTIVITY,"com.naukri.fragments.MNJDashboardActivity");
                capabilities.setCapability("--full-reset","false");
                capabilities.setCapability("--no-reset","false");
                //capabilities.setCapability(MobileCapabilityType.APP, app.getAbsolutePath());
                break;
            default:
                throw new RuntimeException("Browser type unsupported:" + BROWSER);
        }

        driver = new RemoteWebDriver(
                new URL("http://" + SELENIUM_HOST + ":" + SELENIUM_PORT + "/wd/hub"),
                capabilities);
        //driver = launchAppium(capabilities);

    }

    // Sets up local WebDriver session, does not require Selenium Server
    private void setupLocalWebDriver() {
        DesiredCapabilities capabilities;
        switch (BROWSER) {
            case "firefox":
                driver = new FirefoxDriver();
                break;
            case "internetExplorer":
                capabilities = DesiredCapabilities.internetExplorer();
                capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
                driver = new InternetExplorerDriver(capabilities);
                break;
            case "chrome":
                String chromeDriverPath = "lib/chromedriver";
                if (System.getProperty("os.name").contains("Windows")) {
                    chromeDriverPath = "C:\\tools\\chromedriver.exe";
                }
                System.setProperty("webdriver.chrome.driver", chromeDriverPath);
                driver = new ChromeDriver();
                break;
            default:
                throw new RuntimeException("Browser type unsupported" + BROWSER);
        }
    }

    // Takes a screenshot
    public static void takeScreenshot(String testName) {
        File scrFile = driver.getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(scrFile, new File("build/screenshots/" + testName + "_" + BROWSER + ".png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*public static RemoteWebDriver launchAppium(DesiredCapabilities capabilities){
        AndroidDriver driver;
        AppiumDriverLocalService service = null;
        try {
            service = new AppiumDriverLocalService().buildDefaultService();
        } catch (IOException e) {
            e.printStackTrace();
        }
        service.start();
        if (service == null || !service.isRunning()){
            throw new RuntimeException("Appium Server not started");
        } else {
            driver = new AndroidDriver<>(service.getUrl(),capabilities);
        }
        return driver;
    }*/

    public static void setUpAppium() throws MalformedURLException {
        // Start Appium programmatically
        service = AppiumDriverLocalService.buildService(new AppiumServiceBuilder().usingPort(4723)
                .usingDriverExecutable(new File(APPIUM_NODE))
                .withAppiumJS(new File(APPIUM_JS_PATH)));
        service.start();
        TimeHelper.pause(5);
        service_url = service.getUrl().toString();
        File appDir = new File(System.getProperty("user.dir")+"//"+"apk");
        File app = new File(appDir,"diginity.apk");
        DesiredCapabilities capabilities;
        capabilities = DesiredCapabilities.android();
        capabilities.setCapability(MobileCapabilityType.BROWSER_NAME, "");
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "Android Emulator");
        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, MobilePlatform.ANDROID);
        capabilities.setCapability(MobileCapabilityType.APP_PACKAGE,"naukriApp.appModules.login");
        capabilities.setCapability(MobileCapabilityType.APP_ACTIVITY,"com.naukri.fragments.MNJDashboardActivity");
        capabilities.setCapability("--full-reset","false");
        capabilities.setCapability("--no-reset","false");
        driver = new AndroidDriver<>(new URL(service_url),capabilities);
    }

    public static void tearDownAppium(){
        if (!service.isRunning()){
            service.stop();
        }
    }
}
