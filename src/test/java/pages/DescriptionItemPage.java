package pages;

import core.driver.Attributes;
import core.driver.Mobile;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class DescriptionItemPage extends HeaderPage {
    final static Logger log = Logger.getLogger(DescriptionItemPage.class);

    @iOSXCUITFindBy(id = "test-BACK TO PRODUCTS")
    @AndroidFindBy(accessibility = "test-BACK TO PRODUCTS")
    WebElement BACK_TO_PRODUCT;

    @iOSXCUITFindBy(id = "test-ADD TO CART")
    @AndroidFindBy(accessibility = "test-ADD TO CART")
    WebElement ADD_TO_CART;

    @iOSXCUITFindBy(xpath = "tXCUIElementTypeOther[@name=\"test-Image Container\"]/XCUIElementTypeOther/XCUIElementTypeImage")
    @AndroidFindBy(xpath = "android.view.ViewGroup[@content-desc=\"test-Image Container\"]/android.widget.ImageView")
    WebElement PRODUCT_IMAGE;

    public DescriptionItemPage(){

        PageFactory.initElements(new AppiumFieldDecorator(getDriver()), this);
    }

    public boolean display_DescriptionItem(){
        return waitFor_ElementToBeDisplayed(PRODUCT_IMAGE);}

    public void tapOn_BackToProduct(){
        waitFor_ElementAndTap(BACK_TO_PRODUCT);}

    public void tapOn_AddToCartButton(){
        swipeDownTo_Element(ADD_TO_CART, 20);
        tapOn_Element(ADD_TO_CART);}

    public boolean is_TShirtTitleDisplay(String name){
//        this example is when you need to swipedown and grab the xpath
        swipeDownTo_Element(By.xpath("//XCUIElementTypeStaticText[@name='"+name+"']"), 30);
//        tapOn_Element(By.xpath("//XCUIElementTypeStaticText[@label='"+name+"']"));
        return is_ElementDisplayed(By.xpath("//XCUIElementTypeStaticText[@name='"+name+"']"));
    }
}
