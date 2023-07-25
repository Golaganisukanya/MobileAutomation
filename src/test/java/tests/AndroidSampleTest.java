package tests;

import io.appium.java_client.MobileBy;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.annotations.Test;
import pages.LoginPage;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;

public class AndroidSampleTest extends MobileBaseTest{

    @Test
    public void testSwagLabLoginAndroid() {
        LoginPage login = new LoginPage();
        login.perform_successfulLogin("standard_user","secret_sauce");
        mobile.takeScreenShot("loginpage");

        mobile.tapOn_Element(MobileBy.AccessibilityId("test-Toggle"));
        mobile.takeScreenShot("toggle");

        mobile.tapOn_Element(MobileBy.AccessibilityId("test-Item title"));
        mobile.takeScreenShot("listview");

        mobile.tapOn_Element(MobileBy.AccessibilityId("test-MenuPage"));
        mobile.takeScreenShot("product_description");

        mobile.tapOn_Element(MobileBy.AccessibilityId("test-LOGOUT"));
        mobile.takeScreenShot("logout");

        mobile.waitFor_ElementClickable(MobileBy.AccessibilityId("test-Username"));

        mobile.quitApp();


//        wait.until(ExpectedConditions.elementToBeClickable(MobileBy.AccessibilityId("test-Username"))).sendKeys("standard_user");
//        appium.findElement(MobileBy.AccessibilityId("test-Password")).sendKeys("secret_sauce");
//        appium.findElement(MobileBy.AccessibilityId("test-LOGIN")).click();
//
//        takeScreenShotAndroid("loginPage");
//        takeScreenShotAndroid();
//
//        wait.until(ExpectedConditions.elementToBeClickable(MobileBy.xpath(
//                "//android.view.ViewGroup[@content-desc=\"test-Toggle\"]/android.widget.ImageView"))).click();
//        takeScreenShotAndroid("toggle");
//        wait.until(ExpectedConditions.elementToBeClickable(MobileBy.xpath("(//android.view.ViewGroup[@content-desc=\"test-ADD TO CART\"])[1]/android.widget.TextView"))).click();
//        takeScreenShotAndroid("listview");
//
//        wait.until(ExpectedConditions.elementToBeClickable(MobileBy.xpath("//android.view.ViewGroup[@content-desc=\"test-MenuPage\"]/android.view.ViewGroup/android.widget.ImageView"))).click();
//        takeScreenShotAndroid("productDescription");
//
//        wait.until(ExpectedConditions.elementToBeClickable(MobileBy.AccessibilityId("test-LOGOUT"))).click();
//        takeScreenShotAndroid("logOut");
//
//        wait.until(ExpectedConditions.elementToBeClickable(MobileBy.AccessibilityId("test-Username")));
//
//        appium.quit();
    }

    public void takeScreenShotAndroid(String fileName) {
        String currentTimeStamp = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss").format(new java.util.Date());
        String SCREENSHOTS_PATH = System.getProperty("user.dir") + File.separator + "output" + File.separator + "screenshots" + File.separator + fileName +currentTimeStamp + ".png";
        File file = ((TakesScreenshot)getDriver()).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(file, new File(SCREENSHOTS_PATH));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void takeScreenShotAndroid() {
        String currentTimeStamp = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss").format(new java.util.Date());
        String SCREENSHOTS_PATH = System.getProperty("user.dir") + File.separator + "output" + File.separator + "screenshots" + File.separator +currentTimeStamp + ".png";
        File file = ((TakesScreenshot)getDriver()).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(file, new File(SCREENSHOTS_PATH));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
