package pages;

import core.driver.Mobile;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class MenuPage extends Mobile {

    final static Logger log = Logger.getLogger(MenuPage.class);

    public enum Options {
        ALL_ITEMS,
        WEBVIEW,
        QR_CODE_SCANNER,
        GEO_LOCATION,
        DRAWING,
        ABOUT,
        LOGOUT,
        RESET_APP_STATE
    }

    @iOSXCUITFindBy(accessibility = "test-Close")
    @AndroidFindBy(accessibility = "test-Close")
    WebElement CLOSE_ICON;

    @iOSXCUITFindBy(accessibility = "test-LOGOUT")
    @AndroidFindBy(accessibility = "test-LOGOUT")
    WebElement LOGOUT_OPTION;

    public MenuPage(){
        PageFactory.initElements(new AppiumFieldDecorator(getDriver()), this);
    }

    public void tapOn_CloseIcon(){
        waitFor_ElementAndTap(CLOSE_ICON);
    }

    public LoginPage tapOn_LogoutOption(){
        waitFor_ElementAndTap(LOGOUT_OPTION);
        return new LoginPage();
    }

    public void tapOn_MenuOption(Options menuOption) {
        if(is_IOSDriver())
            tapOn_Element(By.xpath("//XCUIElementTypeOther[@label='" + menuOption.name().replace("_", " ") + "']"));
    }


}
