package pages;

import core.driver.Mobile;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class HeaderPage extends Mobile {

    final static Logger log = Logger.getLogger(HeaderPage.class);

    @iOSXCUITFindBy(accessibility = "test-Cart")
    @AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc=\"test-Cart\"]/android.view.ViewGroup/android.widget.ImageView")
    WebElement CART_ICON;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeOther[@name=\"headerContainer\"]/following-sibling::XCUIElementTypeOther[3]")
    @AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc=\"test-Cart\"]/android.view.ViewGroup/android.widget.ImageView")
    WebElement SWAG_LAB_LOGO;

    @iOSXCUITFindBy(accessibility = "test-Menu")
    @AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc=\"test-Menu\"]/android.view.ViewGroup/android.widget.ImageView")
    WebElement MENU;

    public void HeaderPage(){
    log.info("Initializing in the login page: ");
        PageFactory.initElements(new AppiumFieldDecorator(getDriver()), this);
    }

    public MenuPage tapOn_MenuIcon(){
        tapOn_Element(MENU);
        return new MenuPage();
    }

    public void tapOn_CartIcon(){ tapOn_Element(CART_ICON);}

    public boolean is_SwagLabLogoDisplayed() {
        return waitFor_ElementToBeDisplayed(SWAG_LAB_LOGO);
    }
}
