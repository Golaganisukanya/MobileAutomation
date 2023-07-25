package tests;

import io.appium.java_client.MobileBy;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.HomePage;

public class IOSSauceLabsTests extends MobileBaseTest{
    @Test
    public void test_SauceLabIos(){

        mobile.pauseExecutionForSeconds(5);
        LoginPage login = new LoginPage();
        login.tapOn_StandardUser();
//        mobile.tapOn_Element(MobileBy.AccessibilityId("est-standard_user"));
        mobile.tapOn_Element(MobileBy.AccessibilityId("test-LOGIN"));

        HomePage products = new HomePage();
        mobile.tapOn_Element(MobileBy.AccessibilityId("test-Toggle"));
        mobile.tapOn_Element(MobileBy.AccessibilityId("test-Image Container"));
        mobile.quitApp();


    }
}
