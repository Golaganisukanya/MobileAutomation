package pages;

import core.driver.Attributes;
import core.driver.Mobile;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.util.HashMap;
import java.util.Map;

public class LoginPage extends Mobile {

    public enum USERNAMES {
        STANDARD_USER,
        LOCKED_OUT_USER,
        PROBLEM_USER
    }

    final static Logger log = Logger.getLogger(LoginPage.class);

    @iOSXCUITFindBy(xpath = "(//XCUIElementTypeOther[@name=\"test-Login\"]//XCUIElementTypeImage)[1]")
    @AndroidFindBy(xpath = "//android.widget.ScrollView[@content-desc=\"test-Login\"]/android.view.ViewGroup/android.widget.ImageView[1]")
    WebElement SWAGLABS_LOGO;

    @iOSXCUITFindBy(accessibility = "test-Username")
    @AndroidFindBy(accessibility = "test-Username")
    WebElement USERNAME;

    @iOSXCUITFindBy(accessibility = "test-Password")
    @AndroidFindBy(accessibility = "test-Password")
    public WebElement PASSWORD;

    @iOSXCUITFindBy(accessibility = "test-LOGIN")
    @AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc=\"test-LOGIN\"]/android.widget.TextView")
    WebElement LOGIN;

    //XCUIElementTypeOther[@name="Username Password LOGIN"]/XCUIElementTypeOther[6]/XCUIElementTypeImage
    @iOSXCUITFindBy(xpath = "//XCUIElementTypeOther[@name=\"Username Password LOGIN\"]/XCUIElementTypeOther[6]/XCUIElementTypeImage")
    @AndroidFindBy(xpath = "//android.widget.ScrollView[@content-desc=\"test-Login\"]/android.view.ViewGroup/android.widget.ImageView")
    WebElement SWAGLABS_BOT_LOGO;

    @iOSXCUITFindBy(accessibility = "test-standard_user")
    @AndroidFindBy(accessibility = "test-standard_user")
    public WebElement STANDARD_USER;

    @iOSXCUITFindBy(accessibility = "test-locked_out_user")
    @AndroidFindBy(id = "test-locked_out_user")
    WebElement LOCKED_OUT_USER;

    @iOSXCUITFindBy(accessibility = "test-Error message")
    @AndroidFindBy(xpath = "test-Error message")
    WebElement LOCKED_OUT_ERR_MSG;

    @iOSXCUITFindBy(accessibility = "test-problem_user")
    @AndroidFindBy(xpath = "test-problem_user")
    WebElement PROBLEM_USER;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name=\"The currently accepted usernames for this application are (tap to autofill):\"]")
    @AndroidFindBy(xpath = "//android.widget.ScrollView[@content-desc=\"test-Login\"]/android.view.ViewGroup/android.view.ViewGroup[4]/android.widget.TextView")
    WebElement USERNAME_MSG_TEXT;

    @iOSXCUITFindBy(accessibility = "Username and password do not match any user in this service.")
    @AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc=\"test-Error message\"]/android.widget.TextView")
    WebElement USERNAME_PASSWORD_ERR;

    @iOSXCUITFindBy(accessibility = "test-Error message")
    @AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc=\"test-Error message\"]/android.widget.TextView")
    WebElement USERNAME_ERR;

    @iOSXCUITFindBy(accessibility = "test-Error message")
    @AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc=\"test-Error message\"]/android.widget.TextView")
    WebElement PASSWORD_ERR;

    @iOSXCUITFindBy(accessibility = "And the password for all users is:")
    @AndroidFindBy(xpath = "//XCUIElementTypeStaticText[@name=\"And the password for all users is:\"]")
    WebElement PASSWORD_MSG_TEXT;

    @iOSXCUITFindBy(accessibility = "secret_sauce")
    @AndroidFindBy(xpath = "secret_sauce")
    WebElement SECRET_SAUCE;

    public LoginPage() {
        log.info("Initializing in the login page: ");
        PageFactory.initElements(new AppiumFieldDecorator(getDriver()), this);
        log.info("All element of the page is initialized and ready to use");
    }

    public void enterText_UserNameTextField(String username) {
        log.info("Enter username in the text field: " + username);
        waitFor_ElementClearAndType(USERNAME, username);
    }

    public void enterText_PasswordTextField(String password) {
        log.info("Enter password in the text field: " + password);
        waitFor_ElementClearAndType(PASSWORD, password);
    }

    public void tapOn_SwagLabLogo() {
        SWAGLABS_LOGO.click();;
        pauseExecutionForSeconds(1);
    }

    public void tapOn_StandardUser(){ tapOn_Element(STANDARD_USER);}
    public void tapOn_LockedOutUser(){ tapOn_Element(LOCKED_OUT_USER);}
    public void tapOn_ProblemUser(){ tapOn_Element(PROBLEM_USER);}

    public HomePage perform_successfulLogin(String username, String password) {
        enterText_UserNameTextField(username);
        enterText_PasswordTextField(password);
        tapOn_LoginButton();
        return new HomePage();
    }

    public HomePage tapOn_LoginButton() {
        tapOn_Element(LOGIN);
        return new HomePage();
    }


    public boolean is_SwagLabsLogoDisplayed() {
        return waitFor_ElementToBeDisplayed(SWAGLABS_LOGO);
    }

    public boolean is_SwagLabsBotLogoDisplayed() {
        return waitFor_ElementToBeDisplayed(SWAGLABS_BOT_LOGO);
    }
    public boolean is_UserNameDisplayed() {
        return waitFor_ElementToBeDisplayed(USERNAME);
    }
    public boolean is_PasswordDisplayed() {
        return waitFor_ElementToBeDisplayed(PASSWORD);
    }

    public boolean is_LoginButtonDisplayed() {
        return waitFor_ElementToBeDisplayed(LOGIN);
    }


   public boolean is_PasswordMsgTextDisplayed(){return waitFor_ElementToBeDisplayed(PASSWORD_MSG_TEXT);}

    public boolean is_ErrorMsgDisplayed() {
        return waitFor_ElementToBeDisplayed(USERNAME_ERR);
    }
    public boolean is_UserPassErrorMsgDisplayed() {
        return waitFor_ElementToBeDisplayed(USERNAME_PASSWORD_ERR);
    }

    public boolean is_UsernameTextMsgDisplayed() {return waitFor_ElementToBeDisplayed(USERNAME_MSG_TEXT);}


    public boolean is_TapOnSecretSauce(){ return waitFor_ElementToBeDisplayed(SECRET_SAUCE);}
    public boolean is_LockeOutTextErrMSg(){ return waitFor_ElementToBeDisplayed(LOCKED_OUT_ERR_MSG);}
    // public String get_ErrorMsg(){return USERNAME_ERR.getAttribute("name");}
//    public String  get_ErrorMsg(){return getElementTextByAttribute(USERNAME_ERR. Attributes.Name);}

    public String get_ErrorMsg() {
        String text = null;
        if(getDriver() instanceof IOSDriver)
            text = getElementTextByAttribute(USERNAME_ERR, Attributes.LABEL);
        if(getDriver() instanceof AndroidDriver) {
            log.error("I have to figure out the attribute for android here");
        }
        return text;
    }
    public String get_ErrorMsgPassword() {
        String text = null;
        if(getDriver() instanceof IOSDriver)
            text = getElementTextByAttribute(PASSWORD_ERR, Attributes.LABEL);
        if(getDriver() instanceof AndroidDriver) {
            log.error("I have to figure out the attribute for android here");
        }
        return text;
    }
    public String get_ErrorMsgLockedOut() {
        String text = null;
        if(getDriver() instanceof IOSDriver)
            text = getElementTextByAttribute(LOCKED_OUT_ERR_MSG, Attributes.LABEL);
        if(getDriver() instanceof AndroidDriver) {
            log.error("I have to figure out the attribute for android here");
        }
        return text;
    }

    public String get_ErrorMsgUserPass() {
        String text = null;
        if(getDriver() instanceof IOSDriver)
            text = getElementTextByAttribute(USERNAME_PASSWORD_ERR, Attributes.LABEL);
        if(getDriver() instanceof AndroidDriver) {
            log.error("I have to figure out the attribute for android here");
        }
        return text;
    }

//    when you want to see all the attributes Login's button
    public String get_LoginButtonName(){
        return getElementTextByAttribute(LOGIN, Attributes.LABEL);
    }
//    public String get_TextFrom_UserName(){return getElementTextByAttribute(STANDARD_USER, Attributes.NAME);}

    public String getTextFrom_UserName(USERNAMES username) {
        String text = null;
        if (is_IOSDriver()) {
            text = getElementTextByAttribute(By.xpath("//XCUIElementTypeOther[@label='"+username.name().toLowerCase()+"']"), Attributes.LABEL);
        }else
            text = getElementTextByAttribute(By.xpath("//android.widget.TextView[@text='"+username.name().toLowerCase()+"']"), Attributes.TEXT);
        log.info("Get username text as: " + text);
        return text;
    }

    public void assertLoginPage() {
        Assert.assertTrue(is_SwagLabsLogoDisplayed(), "FAIL: Unable to find swag lab logo on login page");
        Assert.assertTrue(is_UserNameDisplayed(), "FAIL: Unable to find username text field on login page");
        Assert.assertTrue(is_PasswordDisplayed(), "FAIL: Unable to find password text field on login page");
        Assert.assertTrue(is_LoginButtonDisplayed(), "FAIL: Unable to find button on login page");
        Assert.assertTrue(is_SwagLabsBotLogoDisplayed(), "FAIL: Unable to find swag labs bot logo on login page");
        Assert.assertTrue(is_UsernameTextMsgDisplayed(), "FAIL: unable to find text message on login page. ");
        Assert.assertTrue(getTextFrom_UserName(USERNAMES.STANDARD_USER).equals("standard_user"), "FAIL: Unable to find username text message on login page");
        Assert.assertTrue(getTextFrom_UserName(USERNAMES.LOCKED_OUT_USER).equals("locked_out_user"), "FAIL: Unable to find locked out user on login page");
        Assert.assertTrue(getTextFrom_UserName(USERNAMES.PROBLEM_USER).equals("problem_user"), "FAIL: Unable to find problem user on login page");
        scrollTo_Bottom();
        Assert.assertTrue(is_PasswordMsgTextDisplayed(), "FAIL: unable to find the password for all users on login page");
        Assert.assertTrue(is_TapOnSecretSauce(), "FAIL: unable to find secret sauce text on login page");
        scrollTo_Top();
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

}
