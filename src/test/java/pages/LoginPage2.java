package pages;

import core.driver.Mobile;
import io.appium.java_client.MobileBy;
import org.apache.log4j.Logger;

public class LoginPage2 extends Mobile {
    final static Logger log = Logger.getLogger(LoginPage2.class);
    public LoginPage2() {
        log.info("No element on screen is initialized");
    }
    public void enterText_UserNameTextField(String username){
        log.info("Enter username in username text field as:" + username);
//        getDriver().findElement(MobileBy.AccessibilityId("")).sendKeys(username);
        waitFor_ElementAndType(MobileBy.AccessibilityId("test-Username"), username);

    }
}
