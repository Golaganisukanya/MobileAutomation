package tests.home;

import flows.Flows;
import io.appium.java_client.MobileBy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomePage;
import tests.MobileBaseTest;

public class HomeTests extends MobileBaseTest {

    @Test(description = "The user should be able to login successfully, and see the home screen", testName = "Home screen", groups = {"home"}, priority = 1, enabled = true)
    public void test_HOME_TC_1() {
        pages.getLoginPage().enterText_UserNameTextField("standard_user");
        pages.getLoginPage().enterText_PasswordTextField("secret_sauce");
        pages.getLoginPage().tapOn_LoginButton();
//        pages.getHomePage().assertHomePage();

        Flows.performLogOut(pages.getHomePage().tapOn_MenuIcon()).is_LoginButtonDisplayed();
    }

    @Test (description = "The user should be able to login successfully, and see the home screen.", testName = "Home screen", groups = {"home"}, priority = 2, enabled = true)
    public void test_HOME_TC_2() {
        pages.getLoginPage().enterText_UserNameTextField("standard_user");
        pages.getLoginPage().enterText_PasswordTextField("secret_sauce");
        pages.getLoginPage().tapOn_LoginButton();
//        pages.getHomePage().assertHomePage();
        pages.getHomePage().tapOn_ListToggleIcon();
        pages.getHomePage().waitFor_LoadingListView();
        Assert.assertTrue(pages.getHomePage().is_ListView());

        Flows.performLogOut(pages.getHomePage().tapOn_MenuIcon()).is_LoginButtonDisplayed();
    }

    @Test (description = "User should be able to add first product to the cart.", testName = "Home screen add product", groups = {"home"}, priority = 2, enabled = false)
    public void test_HOME_TC_3() {
        pages.getLoginPage().enterText_UserNameTextField("standard_user");
        pages.getLoginPage().enterText_PasswordTextField("secret_sauce");
        pages.getLoginPage().tapOn_LoginButton();
//        pages.getLoginPage().is_LoginButtonDisplayed();
//        pages.getHomePage().assertHomePage();
        pages.getHomePage().tapOn_Item();

        // validate cart count is 1
        // validate the "REMOVE' label of the button

        pages.getHomePage().tapOn_MenuIcon();
        pages.getMenuPage().tapOn_LogoutOption();
    }


    // Rework - on this
    @Test (description = "The user should be able to add more than one product.", testName = "Home screen add product", groups = {"home"}, priority = 2, enabled = false)
    public void test_HOME_TC_4() {
        pages.getLoginPage().enterText_UserNameTextField("standard_user");
        pages.getLoginPage().enterText_PasswordTextField("secret_sauce");
        pages.getLoginPage().tapOn_LoginButton();
        pages.waitFor_ElementAndTap(By.xpath("(//XCUIElementTypeOther[@name=\"test-ADD TO CART\"])[1]"));
        pages.tapOn_Element(By.xpath("(//XCUIElementTypeOther[@name=\"test-ADD TO CART\"])[1]"));
        pages.swipeDownTo_Element(By.xpath("(//XCUIElementTypeOther[@name=\"test-ADD TO CART\"])[1]"), 3);
        pages.tapOn_Element(By.xpath("(//XCUIElementTypeOther[@name=\"test-ADD TO CART\"])[1]"));
//        pages.swipeDownTo_Element(By.xpath("(//XCUIElementTypeOther[@name=\"test-ADD TO CART\"])[1]"), 3);
//        pages.tapOn_Element(By.xpath("(//XCUIElementTypeOther[@name=\"test-ADD TO CART\"])[1]"));
        pages.getHomePage().tapOn_CartIcon();
    }

    @Test (description = "User should be able to add first product to the cart via list view.", testName = "Home screen add product", groups = {"home"}, priority = 2, enabled = false)
    public void test_HOME_TC_5() {
        pages.getLoginPage().perform_successfulLogin("standard_user", "secret_sauce");
        pages.getHomePage().tapOn_ListToggleIcon();
        pages.tapOn_Element(MobileBy.xpath("(//XCUIElementTypeStaticText[@name=\"test-Item title\"])[1]")); // this method should come from page class
        pages.getHomePage().tapOn_MenuIcon();
        pages.getMenuPage().tapOn_LogoutOption();
    }

    @Test (description = "The user should be able to add multiple products to cart.", testName = "Home screen add product", groups = {"home"}, priority = 2, enabled = false)
    public void test_HOME_TC_6() {
        pages.getLoginPage().perform_successfulLogin("standard_user", "secret_sauce");
        pages.getHomePage().tapOn_ListToggleIcon();
//        pages.getHomePage().assertHomePage();
        pages.tapOn_Element(By.xpath("(//XCUIElementTypeOther[@name=\"test-ADD TO CART\"])[1]"));
        pages.swipeDownTo_Element(By.xpath("(//XCUIElementTypeOther[@name=\"test-ADD TO CART\"])[1]"), 3);
        pages.tapOn_Element(By.xpath("(//XCUIElementTypeOther[@name=\"test-ADD TO CART\"])[3]"));
        pages.getHomePage().tapOn_MenuIcon();
        pages.getMenuPage().tapOn_LogoutOption();
    }

      // Pleas assert the menu options
    @Test (description = "The user should be to access Menu options.", testName = "Home screen menu", groups = {"home"}, priority = 2, enabled = false)
    public void test_HOME_TC_7() {
        pages.getLoginPage().perform_successfulLogin("standard_user", "secret_sauce");
//        pages.getHomePage().assertHomePage();
        pages.getHomePage().tapOn_MenuIcon();
        pages.getMenuPage().tapOn_CloseIcon();

    }

    @Test (description = "The user should be able to tap 'Shopping Cart' icon.", testName = "Home screen cart icon", groups = {"home"}, priority = 2, enabled = false)
    public void test_HOME_TC_8() {
        pages.getLoginPage().perform_successfulLogin("standard_user", "secret_sauce");
        pages.getHomePage().tapOn_CartIcon();

    }
    @Test (description = "The user should be able to scroll down.", testName = "Home screen scroll.", groups = {"home"}, priority = 2, enabled = false)
    public void test_HOME_TC_9() {
        pages.getLoginPage().perform_successfulLogin("standard_user", "secret_sauce");
        pages.getHomePage().scrollTo_Bottom();

    }
    @Test (description = "The user should be able to scroll down in list view.", testName = "Home screen scroll", groups = {"home"}, priority = 2, enabled = false)
    public void test_HOME_TC_10() {
        pages.getLoginPage().perform_successfulLogin("standard_user", "secret_sauce");
        pages.getHomePage().tapOn_ListToggleIcon();
        pages.getHomePage().scrollTo_Bottom();

    }

    @Test (description = "The user should be able to tap 'ADD TO CART' dragging one item.", testName = "Home screen drag n drop", groups = {"home"}, priority = 2, enabled = true)
    public void test_HOME_TC_11() {
        pages.getLoginPage().perform_successfulLogin("standard_user", "secret_sauce");

        pages.getHomePage().addItemToCartViaDragAndDropAction(2);

        Flows.performLogOut(pages.getHomePage().tapOn_MenuIcon()).is_LoginButtonDisplayed();
    }

    @Test (description = "The user should be able to tap 'ADD TO CART' dragging multiple items.", testName = "Home screen drag n drop", groups = {"home"}, priority = 2, enabled = false)
    public void test_HOME_TC_12() {
        pages.getLoginPage().perform_successfulLogin("standard_user", "secret_sauce");

    }
    @Test (description = "The user should be able to tap 'Shopping Cart' icon.", testName = "Home screen cart icon", groups = {"home"}, priority = 2, enabled = false)
    public void test_HOME_TC_13() {
        pages.getLoginPage().perform_successfulLogin("standard_user", "secret_sauce");
        pages.getHomePage().tapOn_ListToggleIcon();
        WebElement source = getDriver().findElement(By.xpath("(//XCUIElementTypeOther[@name=\"test-Drag Handle\"]/XCUIElementTypeStaticText)[1]"));
        WebElement target = getDriver().findElement(By.id("test-Cart drop zone"));
        pages.getHomePage().perform_DragAndDropAction(source, target);
    }
    @Test (description = "The user should be able to tap on  '+'  button dragging multiple items.", testName = "Home screen drag n drop", groups = {"home"}, priority = 2, enabled = false)
    public void test_HOME_TC_14() {
        pages.getLoginPage().perform_successfulLogin("standard_user", "secret_sauce");

    }
    @Test (description = "Filter icon options on home screen.", testName = "Home screen filter icon.", groups = {"home"}, priority = 2, enabled = true)
    public void test_HOME_TC_15() {
        pages.getLoginPage().perform_successfulLogin("standard_user", "secret_sauce");
        pages.getHomePage().tapOn_FilterIcon();
        pages.getHomePage().tapOn_FilterOption(HomePage.FilterOptions.NAME_A_TO_Z);
        pages.getHomePage().tapOn_FilterIcon();
        pages.getHomePage().tapOn_FilterOption(HomePage.FilterOptions.NAME_Z_TO_A);
        pages.getHomePage().tapOn_FilterIcon();
        pages.getHomePage().tapOn_FilterOption(HomePage.FilterOptions.PRICE_LOW_TO_HIGH);
        pages.getHomePage().tapOn_FilterIcon();
        pages.getHomePage().tapOn_FilterOption(HomePage.FilterOptions.PRICE_HIGH_TO_LOW);
        pages.getHomePage().tapOn_FilterIcon();
        pages.getHomePage().tapOn_FilterOption(HomePage.FilterOptions.CANCEL);

    }

    @Test (description = "The user should be able to sort items by Name (A to Z) .", testName = "Home screen filter icon", groups = {"home"}, priority = 2, enabled = false)
    public void test_HOME_TC_16() {
        pages.getLoginPage().perform_successfulLogin("standard_user", "secret_sauce");
        pages.getHomePage().tapOn_FilterIcon();
        pages.getHomePage().tapOn_FilterOption(HomePage.FilterOptions.NAME_A_TO_Z);
    }
    @Test (description = "The user should be able to sort items by Name (Z to A) .", testName = "Home screen filter icon", groups = {"home"}, priority = 2, enabled = false)
    public void test_HOME_TC_17() {
        pages.getLoginPage().perform_successfulLogin("standard_user", "secret_sauce");
        pages.getHomePage().tapOn_FilterIcon();
        pages.getHomePage().tapOn_FilterOption(HomePage.FilterOptions.NAME_Z_TO_A);
    }
    @Test (description = "The user should be able to sort items by Price (low to high) .", testName = "Home screen filter icon", groups = {"home"}, priority = 2, enabled = false)
    public void test_HOME_TC_18() {
        pages.getLoginPage().perform_successfulLogin("standard_user", "secret_sauce");
        pages.getHomePage().tapOn_FilterIcon();
        pages.getHomePage().tapOn_FilterOption(HomePage.FilterOptions.PRICE_LOW_TO_HIGH);
    }
    @Test (description = "The user should be able to sort items by Price (high to low) .", testName = "Home screen filter icon", groups = {"home"}, priority = 2, enabled = false)
    public void test_HOME_TC_19() {
        pages.getLoginPage().perform_successfulLogin("standard_user", "secret_sauce");
        pages.getHomePage().tapOn_FilterIcon();
        pages.getHomePage().tapOn_FilterOption(HomePage.FilterOptions.PRICE_HIGH_TO_LOW);
    }
    @Test (description = "The user should be able to tap on cancel button.", testName = "Home screen filter icon", groups = {"home"}, priority = 2, enabled = false)
    public void test_HOME_TC_20() {
        pages.getLoginPage().perform_successfulLogin("standard_user", "secret_sauce");
        pages.getHomePage().tapOn_FilterIcon();
        pages.getHomePage().tapOn_FilterOption(HomePage.FilterOptions.CANCEL);
    }
}

