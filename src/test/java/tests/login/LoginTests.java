package tests.login;

import flows.Flows;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LoginPage;
import pages.MenuPage;
import tests.MobileBaseTest;

public class LoginTests extends MobileBaseTest {

    @Test (description = "Validate login screen", testName = "Login screen validation", groups = {"login"}, priority = 1, enabled = true)
    public void test_LOGIN_TC_1() {
        pages.getLoginPage().perform_successfulLogin("standard_user", "secret_sauce");
        pages.getHomePage().tapOn_MenuIcon();
        pages.getMenuPage().tapOn_LogoutOption();
        pages.getLoginPage().is_LoginButtonDisplayed();


        LoginPage login =  new LoginPage();
        HomePage homePage = Flows.performSuccessfulLogin(new LoginPage(), "standard_user","secret_sauce");
        Assert.assertTrue(homePage.is_FilterIconDisplayed(), "FAIL: Filter icon not found");

        Assert.assertTrue(Flows.performLogOut(homePage.tapOn_MenuIcon()).is_LoginButtonDisplayed());
    }

    @Test (description = "User should able to login successfully with valid username and password.", testName = "Successful login", groups = {"login"}, priority = 2, enabled = true)
    public void test_LOGIN_TC_2() {
        LoginPage login =  new LoginPage();
//        login.assertLoginPage();
        HomePage homePage = login.perform_successfulLogin("standard_user", "secret_sauce");
        Assert.assertTrue(homePage.is_FilterIconDisplayed(), "FAIL: Filter icon not found");

        MenuPage menu = homePage.tapOn_MenuIcon();
        menu.tapOn_LogoutOption();
        Assert.assertTrue(login.is_LoginButtonDisplayed(), "FAIL: Unable to find login button");
    }

    @Test (description = "User should able to login via provided accepted user details.", testName = "Successful login", groups = {"login"}, priority = 3, enabled = true)
    public void test_LOGIN_TC_3(){
        LoginPage login =  new LoginPage();
        login.assertLoginPage();
        login.tapOn_StandardUser();
        HomePage homePage = login.tapOn_LoginButton();
        Assert.assertTrue(homePage.is_FilterIconDisplayed(), "FAIL: Filter icon not found");

        MenuPage menu = homePage.tapOn_MenuIcon();
        menu.tapOn_LogoutOption();
        Assert.assertTrue(login.is_LoginButtonDisplayed(), "FAIL: Unable to find login button");
    }

    @Test (description = "User should able to login via overwrite the entered username and password.", testName = "Successful login", groups = {"login"}, priority = 4, enabled = true)
    public void test_LOGIN_TC_4(){
        LoginPage login =  new LoginPage();
        login.assertLoginPage();
        login.enterText_UserNameTextField("test");
        login.enterText_PasswordTextField("password");
        login.tapOn_SwagLabLogo();
        login.tapOn_StandardUser();
        HomePage homePage = login.tapOn_LoginButton();
        Assert.assertTrue(homePage.is_FilterIconDisplayed(), "FAIL: Filter icon not found");

        MenuPage menu = homePage.tapOn_MenuIcon();
        menu.tapOn_LogoutOption();
        Assert.assertTrue(login.is_LoginButtonDisplayed(), "FAIL: Unable to find login button");
    }

    @Test (description = "User should not able to login with locked user login details.", testName = "Locked user login", groups = {"login"}, priority = 5, enabled = true)
    public void test_LOGIN_TC_5(){
        LoginPage login =  new LoginPage();
        login.assertLoginPage();
        login.tapOn_LockedOutUser();
        login.tapOn_LoginButton();
        Assert.assertTrue(login.get_ErrorMsgLockedOut().equals("Sorry, this user has been locked out."), "FAIL: unable to find the error message on login page.");
    }

    @Test (description = "User should not be able to login with locked user via overwrite the entered username and password.", testName = "Successful login", groups = {"login"}, priority = 6, enabled = true)
    public void test_LOGIN_TC_6(){
        LoginPage login =  new LoginPage();
        login.assertLoginPage();
        login.enterText_UserNameTextField("standard_user");
        login.enterText_PasswordTextField("secret_sauce");
        login.tapOn_SwagLabLogo();
        login.tapOn_LockedOutUser();
        login.tapOn_LoginButton();
        Assert.assertTrue(login.get_ErrorMsgLockedOut().equals("Sorry, this user has been locked out."), "FAIL: unable to find the error message on login page.");

    }

    @Test (description = "Empty username field validation", testName = "Login required fields", groups = {"login"}, priority = 7, enabled = true)
    public void test_LOGIN_TC_7() {
        LoginPage login = new LoginPage();
        login.assertLoginPage();
        login.tapOn_LoginButton();
        Assert.assertTrue(login.get_ErrorMsg().equals("Username is required"), "FAIL: unable to find the error message on login page.");
    }

    @Test (description = "Empty password field validation.", testName = "Login required fields", groups = {"login"}, priority = 8, enabled = true)
    public void test_LOGIN_TC_8() {
        LoginPage login = new LoginPage();
        login.assertLoginPage();
        login.enterText_UserNameTextField("standard_user");
        login.tapOn_LoginButton();
        Assert.assertTrue(login.get_ErrorMsgPassword().equals("Password is required"), "FAIL: unable to find the error password message on login page.");
    }

    @Test (description = "Empty password with filled user name field.", testName = "Login required fields", groups = {"login"}, priority = 9, enabled = true)
    public void test_LOGIN_TC_9() {
        LoginPage login = new LoginPage();
        login.assertLoginPage();
        login.enterText_PasswordTextField("secret_sauce");
        login.tapOn_LoginButton();
        Assert.assertTrue(login.get_ErrorMsg().equals("Username is required"),"FAIL: unable to find the error username message on login page.");
        Assert.assertTrue(login.is_ErrorMsgDisplayed(), "Fail: unable to find the error username message on login page.");
    }

    @Test(description = "Empty username wth filled password field.", testName = "Invalid user details", groups = {"login"}, priority = 10, enabled = true)
    public void test_LOGIN_TC_10 () {
        LoginPage login = new LoginPage();
        login.assertLoginPage();
        login.enterText_PasswordTextField("secret_sauce");
        login.tapOn_LoginButton();
        Assert.assertTrue(login.get_ErrorMsg().equals("Username is required."),"FAIL: unable to find the error username message on login page.");

    }

    @Test(description = "Username and passwords are incorrect.", testName = "Invalid user details", groups = {"login"}, priority = 10, enabled = true)
    public void test_LOGIN_TC_11 () {
        LoginPage login = new LoginPage();
        login.assertLoginPage();
        login.enterText_UserNameTextField("secret");
        login.enterText_PasswordTextField("secret");
        login.tapOn_LoginButton();
        Assert.assertTrue(login.get_ErrorMsgUserPass().equals("Username and password do not match any user in this service."),"FAIL: unable to find the error username message on login page.");
    }

    @Test(description = "Invalid details correction for success login.", testName = "Invalid to success login", groups = {"login"}, priority = 10, enabled = true)
    public void test_LOGIN_TC_12 () {
        LoginPage login = new LoginPage();
        login.assertLoginPage();
        login.enterText_UserNameTextField("test");
        login.tapOn_LoginButton();
        Assert.assertTrue(login.get_ErrorMsg().equals("Password is required"),"FAIL: unable to find the error username message on login page.");
        login.enterText_PasswordTextField("password");
        login.tapOn_LoginButton();
        Assert.assertTrue(login.get_ErrorMsgUserPass().equals("Username and password do not match any user in this service."),"FAIL: unable to find the error username message on login page.");
        login.enterText_UserNameTextField("standard_user");
        login.tapOn_LoginButton();
        Assert.assertTrue(login.get_ErrorMsgUserPass().equals("Username and password do not match any user in this service."),"FAIL: unable to find the error username message on login page.");
        login.enterText_PasswordTextField("secret_sauce");
        HomePage homePage = login.tapOn_LoginButton();
        Assert.assertTrue(homePage.is_FilterIconDisplayed(), "FAIL: Filter icon not found");
        MenuPage menu = homePage.tapOn_MenuIcon();
        menu.tapOn_LogoutOption();
        Assert.assertTrue(login.is_LoginButtonDisplayed(), "FAIL: Unable to find login button");
    }

    @AfterMethod
    public void afterMethod() {
        getDriver().closeApp();
        getDriver().launchApp();
    }

}
