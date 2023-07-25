package tests;

import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import core.driver.Mobile;
import core.readers.ReadCapabilityFromJSON;
import core.report.ExtentReport;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import pages.PageManager;

import java.io.File;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;

public class MobileBaseTest {

    public PageManager pages = null;

    static {
        PropertyConfigurator.configure(System.getProperty("user.dir") + File.separator + "config" +File.separator+ "log4j.properties");
    }

    final static Logger log = Logger.getLogger(MobileBaseTest.class);

    public Mobile mobile = null;
    public ExtentReport extentReport = null;

    @Parameters({"runOn", "deviceConfig", "appiumServerURL"})
    @BeforeMethod
    public void beforeClass(Method method, String runOn, String deviceConfig, String appiumServerURL) throws MalformedURLException {
        extentReport = new ExtentReport();
        extentReport.setTestName(method.getName());
        log.info("Run On: " + runOn);
        log.info("Device config: " + deviceConfig);
        log.info("Appium server url: " + appiumServerURL);

        mobile = new Mobile();
        mobile.initMobileDriver(deviceConfig, appiumServerURL);
        pages = new PageManager();
    }

    public AppiumDriver getDriver() {
        return mobile.getDriver();
    }

    public WebDriverWait getWebDriverWait() {
        return mobile.getWebDriverWait();
    }

    @AfterMethod
    public void afterMethod(ITestResult result) {
        if(result.getStatus()==ITestResult.FAILURE) {
            extentReport.getTest().addScreenCaptureFromPath(mobile.takeScreenShot());
            extentReport.getTest().log(Status.FAIL, MarkupHelper.createLabel(result.getName() + " FAIL", ExtentColor.RED));
            extentReport.getTest().fail(result.getThrowable());
        }
        else if(result.getStatus()==ITestResult.SUCCESS) {
            extentReport.getTest().log(Status.PASS, MarkupHelper.createLabel(result.getName() + " PASS", ExtentColor.TEAL));
        }
        else if(result.getStatus()==ITestResult.SKIP) {
            extentReport.getTest().log(Status.SKIP, MarkupHelper.createLabel(result.getName() + " SKIP", ExtentColor.ORANGE));
        }
    }

    @AfterTest
    public void afterTest() {
        extentReport.getReport().flush();
    }
}
