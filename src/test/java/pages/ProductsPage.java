package pages;

import core.driver.Mobile;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class ProductsPage extends Mobile {
    final static Logger log = Logger.getLogger(ProductsPage.class);

    @iOSXCUITFindBy(accessibility = "test-Toggle")
    @AndroidFindBy(accessibility = "test-Toggle")
    WebElement TOGGLE_LIST_ICON;

    @iOSXCUITFindBy(accessibility = "test-Modal Selector Button")
    @AndroidFindBy(accessibility = "test-Modal Selector Button")
    WebElement FILTER_ICON;



    public ProductsPage(){
        PageFactory.initElements(new AppiumFieldDecorator(getDriver()), this);
    }

    public void tapOn_ListToggleIcon(){
        log.info("Select list toggle Items");
        tapOn_Element(TOGGLE_LIST_ICON);
    }

    public void tapOn_FilterIcon(){
        log.info("Tap on filter icon");
        tapOn_Element(FILTER_ICON);
    }
}
