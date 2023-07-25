package core.driver;

import core.readers.ReadCapabilityFromJSON;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.ElementOption;
import org.apache.commons.collections.map.HashedMap;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class Mobile {

    final static Logger log = Logger.getLogger(Mobile.class);

    private static AppiumDriver appium = null;
    private static WebDriverWait wait = null;

    public void initMobileDriver(String deviceConfig, String appiumServerURL) {
        DesiredCapabilities capabilities = ReadCapabilityFromJSON.getDeviceCapabilities(deviceConfig);
        log.info("Capabilities successfully initialized");
        try {
            if (ReadCapabilityFromJSON.is_IOSDriver()) {
                appium = new IOSDriver(new URL(appiumServerURL), capabilities);
                log.info("IOS driver initialized");
            }
            else if (ReadCapabilityFromJSON.is_AndroidDriver()) {
                appium = new AndroidDriver(new URL(appiumServerURL), capabilities);
                log.info("Android driver initialized");
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
            log.error(e.getMessage());
        }
        appium.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
        wait = new WebDriverWait(appium, 300);
        log.info("WebDriverWait 'wait' initialized with wait object");
    }

    public AppiumDriver getDriver() {
        if(appium == null)
            log.error("appium driver is null, please call 'initMobileDriver' method to initialize it");
        return appium;
    }

    public WebDriverWait getWebDriverWait() {
        if(getDriver() != null) {
            if (wait == null) {
                wait = new WebDriverWait(getDriver(), 300);
                log.info("WebDriverWait 'wait' was null initialized again");
            }
            else {
                log.info("Returned 'wait' object");
            }
        }
        else {
            log.error("AppiumDriver object 'appium' is null");
        }
        return wait;
    }

    public WebElement waitFor_ElementClickable(WebElement e) {
        log.info("Waiting for element: " + e.toString());
        return getWebDriverWait().until(ExpectedConditions.elementToBeClickable(e));
    }

    public WebElement waitFor_ElementClickable(By by) {
        log.info("Waiting for element by: " + by.toString());
        return getWebDriverWait().until(ExpectedConditions.elementToBeClickable(getDriver().findElement(by)));
    }

    public void tapOn_Element(WebElement e) {
        log.info("Tap on element: " + e.toString());
        e.click();
    }

    public void tapOn_Element(By by) {
        log.info("Tap on element by: " + by.toString());
        getDriver().findElement(by).click();
    }

    public void waitFor_ElementAndTap(WebElement e) {
        log.info("Tap on element: " + e.toString());
        waitFor_ElementClickable(e).click();
    }

    public void waitFor_ElementAndTap(By by) {
        log.info("Tap on element by: " + by.toString());
        waitFor_ElementClickable(by).click();
    }

    public void typeOn_Element(WebElement e, String text) {
        log.info("Type in element: " + e.toString() + " text: " + text);
        e.sendKeys(text);
    }

    public void typeOn_Element(By by, String text) {
        log.info("Type in element: " + by.toString() + " text: " + text);
        getDriver().findElement(by).sendKeys(text);
    }

    public void waitFor_ElementClearAndType(WebElement e, String text) {
        log.info("Type in element: " + e.toString() + " text: " + text);
        e.clear();
        waitFor_ElementClickable(e).sendKeys(text);
    }

    public void waitFor_ElementAndType(WebElement e, String text) {
        log.info("Type in element: " + e.toString() + " text: " + text);
        waitFor_ElementClickable(e).sendKeys(text);
    }

    public void waitFor_ElementAndType(By by, String text) {
        log.info("Type in element: " + by.toString() + " text: " + text);
        waitFor_ElementClickable(by).sendKeys(text);
    }

    public void quitApp() {
        log.info("Quiting the running app");
        getDriver().quit();
    }

    public boolean is_ElementDisplayed(WebElement e) {
        log.info("Validating isElementDisplayed on screen: " + e.toString());
        return e.isDisplayed();
    }

    public boolean is_ElementDisplayed(By by) {
        log.info("Validating isElementDisplayed on screen: " + by.toString());
        return getDriver().findElement(by).isDisplayed();
    }

    public boolean waitFor_ElementToBeDisplayed(WebElement e) {
        log.info("Waiting and validating isElementDisplayed on screen: " + e.toString());
        return waitFor_ElementClickable(e).isDisplayed();
    }

    public boolean waitFor_ElementToBeDisplayed(By by) {
        log.info("Waiting and validating isElementDisplayed on screen: " + by.toString());
        return waitFor_ElementClickable(by).isDisplayed();
    }
//public String getElementByTextAttribute(WebElement e);{
//log.ingo("Get element text by attribute 'name' : + e.toString());
// return e.getAttribute("name");
// }
    public String getElementTextByAttribute(WebElement e, Attributes attribute) {
        String a = attribute.name().toLowerCase();
        if(a.contains("_"))
            a = a.replace("_","-");
        log.info("Get element text by attribute '"+a+"': " + e.toString());
        String value = null;
        try {
            value = e.getAttribute(a);
        }
        catch(WebDriverException ee) {
            log.info("Attribute: "+ a + " error: NOT FOUND");
        }
        return value;
    }

    public String waitAnd_GetElementByAttribute(WebElement e, Attributes attribute) {
        String a = attribute.name().toLowerCase();
        if(a.contains("_"))
            a = a.replace("_","-");
        log.info("Wait and get element text by attribute '\"+a+\"': " + e.toString());
        String value = null;
        try {
            value = waitFor_ElementClickable(e).getAttribute(a);
        }
        catch(WebDriverException ee) {
            log.info("Attribute: "+ a + " error: NOT FOUND");
        }
        return value;
    }

    public String getElementTextByAttribute(By by, Attributes attribute) {
        String a = attribute.name().toLowerCase();
        log.info("Get element text by attribute '\"+a+\"': " + by.toString());
        return getDriver().findElement(by).getAttribute(a);
    }

    public String waitAnd_GetElementTextByAttribute(By by, Attributes attribute) {
        String a = attribute.name().toLowerCase();
        log.info("Wait and get element text by attribute '\"+a+\"': " + by.toString());
        return waitFor_ElementClickable(by).getAttribute(a);
    }

    public void takeScreenShot(String fileName) {
        String currentTimeStamp = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss").format(new Date());
        String SCREENSHOTS_PATH = System.getProperty("user.dir") + File.separator + "output" + File.separator + "screenshots" + File.separator + fileName +currentTimeStamp + ".png";
        File file = ((TakesScreenshot)getDriver()).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(file, new File(SCREENSHOTS_PATH));
        } catch (IOException e) {
            e.printStackTrace();
        }
        log.info("Screenshot successfully captured in this location: " + SCREENSHOTS_PATH);
    }
//this method is for return REPORT_SCREENSHOT_PATH
    public String takeScreenShot() {
        String currentTimeStamp = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss").format(new Date());
        String SCREENSHOTS_PATH = System.getProperty("user.dir") + File.separator + "output" + File.separator + "screenshots" + File.separator +currentTimeStamp + ".png";
        String REPORT_SCREENSHOT_PATH = "./screenshots/" + currentTimeStamp + ".png"; File file = ((TakesScreenshot)getDriver()).getScreenshotAs(OutputType.FILE);
        try { FileUtils.copyFile(file, new File(SCREENSHOTS_PATH));
        } catch (IOException e) {
            e.printStackTrace();
        } http://log.infolog.info("Screenshot successfully captured in this location: " + SCREENSHOTS_PATH);
    return REPORT_SCREENSHOT_PATH;
    }

    public List<String> getAllAttributeNames(WebElement e) {
        List<String> attr = new ArrayList<>();
        String[] names = Arrays.toString(Attributes.values()).replaceAll("^.|.$", "").split(", ");
        for(String name: names){
            if(name.contains("_"))
                name = name.replace("_","-");
            name = name.toLowerCase();
            try {
                log.info("Attribute: "+ name + " value: " + e.getAttribute(name));
                attr.add(name);
            }
            catch(WebDriverException ee) {
                log.info("Attribute: "+ name + " error: NOT FOUND");
            }
        }
        log.info("All Attributes: "+ attr);
        return attr;
    }

    public List<String> getAllAttributeNames(By by) {
        WebElement e = getDriver().findElement(by);
        List<String> attr = new ArrayList<>();
        String[] names = Arrays.toString(Attributes.values()).replaceAll("^.|.$", "").split(", ");
        for(String name: names){
            if(name.contains("_"))
                name = name.replace("_","-");
            name = name.toLowerCase();
            try {
                log.info("Attribute: "+ name + " value: " + e.getAttribute(name));
                attr.add(name);
            }
            catch(WebDriverException ee) {
                log.info("Attribute: "+ name + " error: NOT FOUND");
            }
        }
        log.info("All Attributes: "+ attr);
        return attr;
    }

    public boolean is_AttributeExists(WebElement e, String attribute) {
        String value = null;
        try {
            value = waitFor_ElementClickable(e).getAttribute(attribute);
            log.info("Attribute: "+ attribute + " value is: " + value);
        }
        catch(WebDriverException ee) {
            log.info("Attribute: "+ attribute + " error: NOT FOUND");
        }
        if(value!=null)
            return true;
        else
            return false;
    }

    public boolean is_AttributeExists(By by, String attribute) {
        String value = null;
        try {
            value = waitFor_ElementClickable(by).getAttribute(attribute);
            log.info("Attribute: " + attribute + " value is: " + value);
        } catch (WebDriverException ee) {
            log.info("Attribute: " + attribute + " error: NOT FOUND");
        }
        if (value != null)
            return true;
        else
            return false;
    }

    public void pauseExecutionForSeconds(int seconds) {
        for(int a=seconds;a>=0;a--) {
            try { Thread.sleep(1000); }
            catch (InterruptedException e) {
                e.printStackTrace();}
            log.info("Test execution resume in: " + a + " Seconds");
         }
    }
    public boolean swipeDownTo_Element(WebElement e, int timeoutSeconds) {
        boolean flag = false;
        WebDriverWait w = new WebDriverWait(getDriver(), 3);
        for(int i=0;i<=timeoutSeconds;i++) {
            try {
                if (w.until(ExpectedConditions.visibilityOf(e)).isDisplayed()) {
                    flag = true;
                    log.info("Element is visible: " + e.toString());
                    break;
                }
            }
            catch(TimeoutException ee) {
                Map<String, Object> args = new HashMap<>();
                args.put("direction", "up");
                getDriver().executeScript("mobile: swipe",args);
                log.info("Element is not visible scrolling down");
                pauseExecutionForSeconds(1);
            }
        }
        return flag;
    }

    public boolean swipeDownTo_Element(By by, int timeoutSeconds) {
        boolean flag = false;
        WebDriverWait w = new WebDriverWait(getDriver(), 3);
        for(int i=0;i<=timeoutSeconds;i++) {
            try {
                if (w.until(ExpectedConditions.visibilityOf(getDriver().findElement(by))).isDisplayed()) {
                    flag = true;
                    log.info("Element is visible: " + by.toString());
                    break;
                }
            }
            catch(TimeoutException ee) {
                Map<String, Object> args = new HashMap<>();
                args.put("direction", "up");
                getDriver().executeScript("mobile: swipe",args);
                log.info("Element is not visible scrolling down");
                pauseExecutionForSeconds(1);
            }
        }
        return flag;
    }

    public boolean swipeUpTo_Element(WebElement e, int timeoutSeconds) {
        boolean flag = false;
        WebDriverWait w = new WebDriverWait(getDriver(), 3);
        for(int i=0;i<=timeoutSeconds;i++) {
            try {
                if (w.until(ExpectedConditions.visibilityOf(e)).isDisplayed()) {
                    flag = true;
                    log.info("Element is visible: " + e.toString());
                    break;
                }
            }
            catch(TimeoutException ee) {
                Map<String, Object> args = new HashMap<>();
                args.put("direction", "down");
                getDriver().executeScript("mobile: swipe",args);
                log.info("Element is not visible scrolling up");
                pauseExecutionForSeconds(1);
            }
        }
        return flag;
    }

    public boolean swipeUpTo_Element(By by, int timeoutSeconds) {
        boolean flag = false;
        WebDriverWait w = new WebDriverWait(getDriver(), 3);
        for(int i=0;i<=timeoutSeconds;i++) {
            try {
                if (w.until(ExpectedConditions.visibilityOf(getDriver().findElement(by))).isDisplayed()) {
                    flag = true;
                    log.info("Element is visible: " + by.toString());
                    break;
                }
            }
            catch(TimeoutException ee) {
                Map<String, Object> args = new HashMap<>();
                args.put("direction", "down");
                getDriver().executeScript("mobile: swipe",args);
                log.info("Element is not visible scrolling up");
                pauseExecutionForSeconds(1);
            }
        }
        return flag;
    }

    public void scrollTo_Top() {
        Map<String, Object> args = new HashMap<>();
        args.put("direction", "up");
        getDriver().executeScript("mobile: scroll",args);
        log.info("Scroll to top of the screen");
    }

    public void scrollTo_Bottom() {
        Map<String, Object> args = new HashMap<>();
        args.put("direction", "down");
        getDriver().executeScript("mobile: scroll",args);
        log.info("Scroll to bottom of the screen");
    }

    public void swipeDown(int numberOfTimes) {
        for(int i=1;i<=numberOfTimes;i++) {
            Map<String, Object> args = new HashMap<>();
            args.put("direction", "up");
            getDriver().executeScript("mobile: swipe", args);
            log.info("Swipe to down");
            pauseExecutionForSeconds(1);
        }
    }

    public void swipeUp(int numberOfTimes) {
        for(int i=1;i<=numberOfTimes;i++) {
            Map<String, Object> args = new HashMap<>();
            args.put("direction", "down");
            getDriver().executeScript("mobile: swipe", args);
            log.info("Swipe to up");
            pauseExecutionForSeconds(1);
        }
    }
    public void performSwipeLeftJS(WebElement element) {
//swipe left on element
        Map<String, Object> args = new HashMap<>();
        args.put("element",((MobileElement)element).getId());
        args.put("direction", "left");
        appium.executeScript("mobile:swipe", args);
    }

    public void performSwipeRightJS(WebElement element) {
//swipe right on element
        Map<String, Object> args = new HashMap<>();
        args.put("element",((MobileElement)element).getId());
        args.put("direction", "right");
        appium.executeScript("mobile:swipe", args);
    }

    public void performSwipeLeftJS() {
//swipe left
        Map<String, Object> args = new HashMap<>();
        args.put("direction", "left");
        appium.executeScript("mobile:swipe", args);
    }

    public void performSwipeRightJS() {
//swipe right
        Map<String, Object> args = new HashMap<>();
        args.put("direction", "right");
        appium.executeScript("mobile:swipe", args);
    }
//perfom for iOS
    public boolean is_IOSDriver() {
        if(getDriver() instanceof IOSDriver)
            return true;
        else
            return false;
    }

    public boolean is_AndroidDriver() {
        if(getDriver() instanceof AndroidDriver)
            return true;
        else
            return false;
    }
//    Method where you can drag to the shopping card

    public void perform_DragAndDropAction(WebElement source, WebElement target) {
        TouchAction action = new TouchAction(getDriver());
        action.longPress(new ElementOption().withElement(source))
                .waitAction(WaitOptions.waitOptions(Duration.ofSeconds(3)))
                .moveTo(new ElementOption().withElement(target))
                .release()
                .perform();
    }
}
