package tests;

import io.appium.java_client.MobileBy;
import org.testng.annotations.Test;
import pages.MenuPage;
import pages.WebViewPage;

import java.util.Set;

public class WebViewTests extends MobileBaseTest {

    @Test
    public void test() {
//        pages.getLoginPage().perform_successfulLogin("standard_user", "secret_sauce");
//        pages.getHomePage().tapOn_MenuIcon();
//        pages.getMenuPage().tapOn_MenuOption(MenuPage.Options.WEBVIEW);
//
//        mobile.pauseExecutionForSeconds(5);
//        WebViewPage web = new WebViewPage();
//        web.enterText_URLTextField("https://www.facebook.com");
//        web.tapOn_GoToSiteButton();

        getDriver().get("https://www.facebook.com");

        mobile.pauseExecutionForSeconds(10);

        String currentView = getDriver().getContext();
        System.out.println("My current view is: " + currentView);

        Set<String> views = getDriver().getContextHandles();
        for(String v: views) {
            System.out.println("Available view: " + v);
//            if(v.startsWith("NATIVE"))
//                getDriver().context(v);
        }

        getDriver().findElement(MobileBy.id("m_login_email")).sendKeys("Praveen");
        getDriver().findElement(MobileBy.id("m_login_password")).sendKeys("Praveen");
        getDriver().findElement(MobileBy.name("login")).click();
        mobile.pauseExecutionForSeconds(5);

        getDriver().installApp("/Users/praveenashapure/IdeaProjects/appium-frameworks/MobileTestNGTAF/input/apps/sl.app");
        getDriver().launchApp();

        mobile.pauseExecutionForSeconds(10);

    }

}
