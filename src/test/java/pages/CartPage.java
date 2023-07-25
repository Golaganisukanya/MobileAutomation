package pages;

import core.driver.Mobile;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class CartPage extends Mobile {
    final static Logger log = Logger.getLogger(CartPage.class);

    @iOSXCUITFindBy(accessibility = "test-CHECKOUT")
    @AndroidFindBy(accessibility = "test-CHECKOUT")
    WebElement CHECKOUT_BUTTON;

    @iOSXCUITFindBy(accessibility = "test-REMOVE" )
    @AndroidFindBy(accessibility = "test-REMOVE")
    WebElement REMOVE_BUTTON;
//    //XCUIElementTypeOther[@name='test-REMOVE']

    @iOSXCUITFindBy(accessibility = "test-CONTINUE SHOPPING")
    @AndroidFindBy(accessibility = "test-CONTINUE SHOPPING")
    WebElement CONTINUE_SHOPPING;

    @iOSXCUITFindBy(accessibility = "test-Delete")
    @AndroidFindBy(accessibility = "test-Delete")
    WebElement DELETE_BUTTON;
//    //XCUIElementTypeOther[@name='test-Delete']

    @iOSXCUITFindBy(accessibility = "test-Item")
    @AndroidFindBy(accessibility = "//XCUIElementTypeOther[@name='test-Item']")
    WebElement DESCRIPTION_ITEM;

    public CartPage(){
        PageFactory.initElements(new AppiumFieldDecorator(getDriver()), this);
    }
//create checkout method

    public void tapOn_CheckOut(){tapOn_Element(CHECKOUT_BUTTON);
//        swipeUpTo_Element(CHECKOUT_BUTTON, 10);
    }
    public void tapOn_RemoveButton(){tapOn_Element(REMOVE_BUTTON);}
    public void tapOn_DeleteButton(){tapOn_Element(DELETE_BUTTON);}

    public void removeFirstItem_FromCart(){performSwipeLeftJS(DESCRIPTION_ITEM);}
}
