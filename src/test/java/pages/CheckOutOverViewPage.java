package pages;

import core.driver.Mobile;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class CheckOutOverViewPage extends Mobile {
    final static Logger log = Logger.getLogger(LoginPage.class);

    @iOSXCUITFindBy(accessibility = "test-FINISH")
    @AndroidFindBy(accessibility = "test-FINISH")
    WebElement FINISH_BUTTON;

    public CheckOutOverViewPage() {
        PageFactory.initElements(new AppiumFieldDecorator(getDriver()), this);
    }
    public void tapOn_FinishButton(){
        tapOn_Element(FINISH_BUTTON);
    }
}
