package tests;

import io.appium.java_client.MobileBy;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.*;

public class IOSSampleTest extends MobileBaseTest {

    @Test(enabled = true)
    public void selectAllPrices() {
        extentReport.getTest().info("This is my first report");
        extentReport.captureScreenShotInReport();
        LoginPage login = new LoginPage();
        login.tapOn_StandardUser();
        login.tapOn_LoginButton();
        HomePage homePage = new HomePage();
        homePage.tapOn_ListToggleIcon();
        homePage.tapOn_FilterIcon();
        homePage.tapOn_FilterOption(HomePage.FilterOptions.NAME_A_TO_Z);
        homePage.getAllTitleItem();
        homePage.getDescriptionOfTheItems();
        homePage.getAllPrices();

    }

    @Test(enabled = false)
    public void selectFilterOptions() {
        extentReport.getTest().info("This is my first report");
        extentReport.captureScreenShotInReport();

        LoginPage login = new LoginPage();
        login.tapOn_StandardUser();
        login.tapOn_LoginButton();

        HomePage homePage = new HomePage();
        homePage.tapOn_ListToggleIcon();
        homePage.tapOn_FilterIcon();
        homePage.tapOn_FilterOption(HomePage.FilterOptions.NAME_A_TO_Z);

        homePage.tapOn_FilterIcon();
        homePage.tapOn_FilterOption(HomePage.FilterOptions.NAME_Z_TO_A);

        homePage.tapOn_FilterIcon();
        homePage.tapOn_FilterOption(HomePage.FilterOptions.PRICE_LOW_TO_HIGH);

        homePage.tapOn_FilterIcon();
        homePage.tapOn_FilterOption(HomePage.FilterOptions.PRICE_HIGH_TO_LOW);

        homePage.tapOn_FilterIcon();
        homePage.tapOn_FilterOption(HomePage.FilterOptions.CANCEL);
    }

        @Test(enabled = false)
    public void shopThreeItems() {
        extentReport.getTest().info("This is my first report");
        extentReport.captureScreenShotInReport();

        LoginPage login = new LoginPage();
        login.tapOn_StandardUser();
        login.tapOn_LoginButton();

        HomePage homePage = new HomePage();
        homePage.tapOn_FirstItem();
//        homePage.tapOn_SecondItem();
//        homePage.tapOn_ThirdItem();
        homePage.tapOn_CartIcon();

        CartPage cartPage = new CartPage();
        cartPage.removeFirstItem_FromCart();
        cartPage.tapOn_DeleteButton();
        cartPage.removeFirstItem_FromCart();
        cartPage.tapOn_DeleteButton();
        cartPage.tapOn_CheckOut();

        CheckOutPage checkOutPage = new CheckOutPage();
        checkOutPage.type_CheckOutInformation("Loy", "Val", "123");
        checkOutPage.pauseExecutionForSeconds(3);
        CheckOutOverViewPage checkOutOverViewPage = new CheckOutOverViewPage();
//        checkOutOverViewPage.pauseExecutionForSeconds(3);
//        checkOutOverViewPage.scrollTo_Top();
        checkOutOverViewPage.tapOn_FinishButton();

        CheckOutComplete checkOutComplete = new CheckOutComplete();
        checkOutComplete.tapOn_BackHomeButton();

        mobile.quitApp();

    }

    @Test(enabled = false)
    public void testSwagLabLogin() {
//        call to Report
        extentReport.getTest().info("This is my first report");
//        extentReport.getTest().addScreenCaptureFromPath(mobile.takeScreenShot());
//        after created a method in side of ExtendReport just call it (line 16)
        extentReport.captureScreenShotInReport();

        LoginPage login = new LoginPage();
        login.scrollTo_Bottom();

        Assert.assertTrue(login.is_TapOnSecretSauce(), "FAIL: LoginPage display secret_sauce");
        login.scrollTo_Top();

        Assert.assertTrue(login.is_LoginButtonDisplayed(), "FAIL: LoginPage button not found");
        Assert.assertEquals(login.get_LoginButtonName(), "LOGIN", "FAIL: LoginPage button text not found");

        login.perform_successfulLogin("standard_user", "secret_sauce");


        HomePage homePage = new HomePage();
        homePage.tapOn_FirstItem();
//        homePage.tapOn_SecondItem();
//        homePage.tapOn_ThirdItem();

//        HeaderPage headerPage = new HeaderPage();
        homePage.tapOn_CartIcon();

        CartPage cart = new CartPage();
        cart.tapOn_CheckOut();
//
        CheckOutPage checkOutPage = new CheckOutPage();
        checkOutPage.type_CheckOutInformation("Loy", "Val", "123");


//        CheckOutOverViewPage checkOutOverView = new CheckOutOverViewPage();
//        checkOutOverView.scrollUp();
//        checkOutOverView.tapOn_FinishButton();




//        mobile.tapOn_Element(MobileBy.AccessibilityId("est-standard_user"));
//        mobile.tapOn_Element(MobileBy.AccessibilityId("test-LOGIN"));
//
//        HomePage homePage = new HomePage();
//        mobile.tapOn_Element(MobileBy.AccessibilityId("test-Toggle"));
//        mobile.tapOn_Element(MobileBy.AccessibilityId("test-Image Container"));

//        mobile.getAllAttributeNames(login.PASSWORD);

//        login.perform_successfulLogin("standard_user","secret_sauce");
//        mobile.takeScreenShot("loginpage");

//        mobile.tapOn_Element(MobileBy.AccessibilityId("test-Toggle"));
//        mobile.takeScreenShot("toggle");
//        mobile.tapOn_Element(MobileBy.xpath("(//XCUIElementTypeStaticText[@name=\"test-Item title\"])[1]"));
//        mobile.takeScreenShot("listview");
//
//        mobile.tapOn_Element(MobileBy.xpath("//XCUIElementTypeOther[@name=\"test-MenuPage\"]/XCUIElementTypeOther"));
//        mobile.takeScreenShot("productdescription");
//        mobile.tapOn_Element(MobileBy.AccessibilityId("test-LOGOUT"));
//        mobile.takeScreenShot("logout");
//        mobile.waitFor_ElementClickable(MobileBy.AccessibilityId("test-Username"));

        mobile.quitApp();

    }
    @Test (enabled = false)
    public void testMultipleOrders() {
        extentReport.getTest().info("This message will print in the report ");
        extentReport.captureScreenShotInReport();

        LoginPage login = new LoginPage();
        Assert.assertTrue(login.is_LoginButtonDisplayed(), "FAIL: LoginPage button not found");
        Assert.assertEquals(login.get_LoginButtonName(), "LOGIN", "FAIL: LoginPage button text not found");

        login.enterText_UserNameTextField("standard_user");
        login.enterText_PasswordTextField("secret_sauce");
        login.tapOn_LoginButton();

        mobile.waitFor_ElementAndTap(By.xpath("(//XCUIElementTypeOther[@name=\"test-ADD TO CART\"])[1]"));
        mobile.tapOn_Element(By.xpath("(//XCUIElementTypeOther[@name=\"test-ADD TO CART\"])[1]"));
        mobile.swipeDownTo_Element(By.xpath("(//XCUIElementTypeOther[@name=\"test-ADD TO CART\"])[1]"), 10);
        mobile.tapOn_Element(By.xpath("(//XCUIElementTypeOther[@name=\"test-ADD TO CART\"])[1]"));
        mobile.swipeDownTo_Element(By.xpath("(//XCUIElementTypeOther[@name=\"test-ADD TO CART\"])[1]"), 10);
        mobile.tapOn_Element(By.xpath("(//XCUIElementTypeOther[@name=\"test-ADD TO CART\"])[1]"));

        mobile.tapOn_Element(By.id("test-CartPage"));

        Assert.assertTrue(mobile.swipeDownTo_Element(By.id("Sauce Labs Backpack"), 10), "FAIL: Unable to find t-shirt text");
        Assert.assertTrue(mobile.swipeDownTo_Element(By.id("Sauce Labs Bike Light"), 10), "FAIL: Unable to find t-shirt text");
        Assert.assertTrue(mobile.swipeDownTo_Element(By.id("Sauce Labs Bolt T-Shirt"), 10), "FAIL: Unable to find t-shirt text");
        Assert.assertTrue(mobile.swipeDownTo_Element(By.id("Sauce Labs Fleece Jacket"), 10), "FAIL: Unable to find t-shirt text");

        mobile.swipeDownTo_Element(By.id("test-CHECKOUT"), 10);
        mobile.tapOn_Element(By.id("test-CHECKOUT"));

    }


    @Test (enabled = false)
    public void testLogin() {
        extentReport.getTest().info("This message will print in the report ");
        extentReport.captureScreenShotInReport();

        LoginPage loginPage = new LoginPage();
        loginPage.enterText_UserNameTextField("blabla");
        loginPage.enterText_PasswordTextField("blabla");
        loginPage.tapOn_LoginButton();

        log.info("Error msg: " + loginPage.get_ErrorMsg());

        Assert.assertEquals(loginPage.get_ErrorMsg(), "Username and password do not match any user in this service.","FAIL: error message miss matched");

        mobile.quitApp();
        }
    @Test (enabled = false)
    public void commandTest() {
        mobile.tapOn_Element(mobile.waitFor_ElementClickable(MobileBy.xpath("//*[@label='standard_user']")));
        mobile.tapOn_Element(mobile.waitFor_ElementClickable(MobileBy.AccessibilityId("test-LOGIN")));
        mobile.waitFor_ElementAndTap(By.xpath("(//XCUIElementTypeOther[@name=\"test-ADD TO CART\"])[1]"));
        mobile.waitFor_ElementAndTap(By.xpath("(//XCUIElementTypeOther[@name=\"test-ADD TO CART\"])[1]"));
        mobile.tapOn_Element(By.id("test-CartPage"));
        mobile.performSwipeLeftJS(getDriver().findElement(MobileBy.AccessibilityId("test-Item")));
        mobile.pauseExecutionForSeconds(3);
//        mobile.performSwipeRightJS(getDriver().findElement(MobileBy.AccessibilityId("test-Item")));
    }

}
