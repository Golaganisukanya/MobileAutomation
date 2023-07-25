package pages;

import core.driver.Attributes;
import core.driver.Mobile;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class CheckOutComplete extends HeaderPage {

    final static Logger log = Logger.getLogger(CheckOutComplete.class);

    @iOSXCUITFindBy(accessibility = "test-BACK HOME")
    @AndroidFindBy(accessibility = "test-BACK HOME")
    WebElement BACK_HOME;

    @iOSXCUITFindBy(accessibility = "THANK YOU FOR YOU ORDER")
    @AndroidFindBy(accessibility = "THANK YOU FOR YOU ORDER")
    WebElement THANK_YOU_TITLE;

    @iOSXCUITFindBy(accessibility = "Your order has been dispatched, and will arrive just as fast as the pony can get there!")
    @AndroidFindBy(accessibility = "Your order has been dispatched, and will arrive just as fast as the pony can get there!")
    WebElement THANK_YOU_DESCRIPTION;

    public CheckOutComplete() {
        log.info("Initializing in the CheckOutComplete page: ");
        PageFactory.initElements(new AppiumFieldDecorator(getDriver()), this);
        log.info("All element of the page is initialized and ready to use");
    }
    public void tapOn_BackHomeButton(){
        tapOn_Element(BACK_HOME);
    }

    public String getThankYouTitle(){
        return getElementTextByAttribute(THANK_YOU_TITLE, Attributes.NAME);
    }

    public String getThankYouDescription(){

        return getElementTextByAttribute(THANK_YOU_DESCRIPTION, Attributes.NAME);
    }
}
