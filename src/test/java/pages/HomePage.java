package pages;

import core.driver.Attributes;
import io.appium.java_client.MobileBy;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.apache.log4j.Logger;
import org.apache.poi.ss.formula.functions.T;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import java.util.List;

public class HomePage extends HeaderPage {
    final static Logger log = Logger.getLogger(HomePage.class);

    public enum FilterOptions {
        NAME_A_TO_Z,
        NAME_Z_TO_A,
        PRICE_LOW_TO_HIGH,
        PRICE_HIGH_TO_LOW,
        CANCEL
    }
//    @iOSXCUITFindBy(xpath = "//XCUIElementTypeOther[@name=\"headerContainer\"]/following-sibling::XCUIElementTypeOther[3]")
//    @AndroidFindBy(xpath = "//XCUIElementTypeOther[@name=\"test-Cart\"]/XCUIElementTypeOther")
//    WebElement SL_LOGO;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeOther[@name=\"test-Cart\"]/XCUIElementTypeOther")
    @AndroidFindBy(xpath = "//XCUIElementTypeOther[@name=\"test-Cart\"]/XCUIElementTypeOther")
    WebElement CART_ICON;

    @iOSXCUITFindBy(accessibility = "test-Toggle")
    @AndroidFindBy(accessibility = "test-Toggle")
    WebElement TOGGLE_LIST_ICON;

    @iOSXCUITFindBy(accessibility = "test-Modal Selector Button")
    @AndroidFindBy(accessibility = "test-Modal Selector Button")
    WebElement FILTER_ICON;

    @iOSXCUITFindBy(accessibility = "test-Image Container")
    @AndroidFindBy(accessibility = "test-Image Container")
    WebElement ITEMS;

    @iOSXCUITFindBy(accessibility = "test-Cart drop zone")
    @AndroidFindBy(accessibility = "test-Cart drop zone")
    WebElement PRODUCTS;

    @iOSXCUITFindBy( xpath = "(//XCUIElementTypeOther[@name=\"test-Drag Handle\"])[1]")
    @AndroidFindBy(accessibility = "test-Drag Handle")
    WebElement DRAG_HANDLE;


    @iOSXCUITFindBy(accessibility = "test-PRODUCTS")
    @AndroidFindBy(accessibility = "test-PRODUCTS")
    WebElement VIEW_PRODUCTS;

//    @iOSXCUITFindBy(accessibility = "headerContainer")
//    @AndroidFindBy(xpath = "//android.widget.ScrollView[@content-desc=\"test-Login\"]/android.view.ViewGroup/android.widget.ImageView[1]\n")
//    WebElement HEADER_CONTAINER;

    @iOSXCUITFindBy(xpath = "(//XCUIElementTypeOther[@name=\"test-ADD TO CART\"])[1]")
    @AndroidFindBy(xpath = "(//android.view.ViewGroup[@content-desc=\"test-ADD TO CART\"])")
    public WebElement ADD_TO_CART_BUTTON;

    @iOSXCUITFindBy(accessibility = "© 2023 Sauce Labs. All Rights Reserved.")
    @AndroidFindBy(xpath = "(//android.view.ViewGroup[@content-desc=\"test-ADD TO CART\"])")
    public WebElement RIGHTS_TEXT;
    @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name='Terms of Service | Privacy Policy']")
    @AndroidFindBy(xpath = "(//android.view.ViewGroup[@content-desc=\"test-ADD TO CART\"])")
    public WebElement TERMS_SERVICE;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name='Terms of Service | Privacy Policy']/following-sibling::XCUIElementTypeOther")
    @AndroidFindBy(xpath = "(//android.view.ViewGroup[@content-desc=\"test-ADD TO CART\"])")
    public WebElement ROBOT_LOGO;

    public HomePage(){
        PageFactory.initElements(new AppiumFieldDecorator(getDriver()), this);
    }

    public void tapOn_FilterIcon(){
        log.info("Tap on filter icon");
//        tapOn_Element(FILTER_ICON);
        waitFor_ElementAndTap(FILTER_ICON);
    }

    public void tapOn_FirstItem(){
        log.info("Select the item Sauce Labs Backpack");
        tapOn_Element(ADD_TO_CART_BUTTON);
    }

//    public void tapOn_SecondItem(){
//        log.info("Select the item Sauce Labs Bike Light");
//        swipeDownTo_Element(ADD_TO_CART_BUTTON, 10);
//        tapOn_Element(ADD_TO_CART_BUTTON);
//    }
//    public void tapOn_ThirdItem(){
//        log.info("Select the item Sauce Labs Bolt T-shirt");
//        tapOn_Element(ADD_TO_CART_BUTTON);
//
//    }
    public void tapOn_ListToggleIcon(){
        log.info("Select List Toggle Items");
//        tapOn_Element(TOGGLE_LIST_ICON);
        waitFor_ElementAndTap(TOGGLE_LIST_ICON);
    }
    public void tapOn_Item(){
        log.info("Select the item Sauce Labs Backpack");
        tapOn_Element(ADD_TO_CART_BUTTON);
    }

//    public void tapOn_TopLogo() {
//        SL_LOGO.click();;
//        pauseExecutionForSeconds(1);
//    }

    public void tapOn_FilterOption(FilterOptions filterOptions){
        String option = filterOptions.name().replace("_", " ").toLowerCase();
        if(option.contains("a to z"))
            option = "Name (A to Z)";
        else if(option.contains("z to a"))
            option = "Name (Z to A)";
        else if(option.contains("low to high"))
            option = "Price (low to high)";
        else if(option.contains("high to low"))
            option = "Price (high to low)";
        else if(option.contains("cancel"))
            option = "Cancel";
        log.info("Tap on the icon Sort items by...");
        if(is_IOSDriver())
            tapOn_Element(By.xpath("//XCUIElementTypeOther[@label='" + option + "']"));
        pauseExecutionForSeconds(3);
    }

//    public static void main (String[] args){
//        String option = FilterOptions.Name_A_to_Z.name().replace("_", " ").toLowerCase();
//        if(option.contains("a to z"))
//            option = "Name (A to Z)";
//        else if(option.contains("z to a"))
//            option = "Name (Z to A)";
//        else if(option.contains("low to high"))
//            option = "Price (low to high)";
//        else if(option.contains("high to low"))
//            option = "Price (high to low)";
//
//            System.out.println(option);
//    }

    public void getAllPrices(){
        List<WebElement> prices = getDriver().findElements(MobileBy.xpath("//XCUIElementTypeStaticText[@name='test-Price']"));
        for(WebElement price: prices){
            log.info("Get the prices :" + price.getAttribute("value"));
        }
    }
    public void getAllTitleItem(){
    List<WebElement> titles = getDriver().findElements(MobileBy.xpath("//XCUIElementTypeStaticText[@name='test-Item title']"));
    for(WebElement title: titles){
//        log.info("Get the shirts title: " + title.getAttribute("value"));
        log.info(("List of shirts title: " + getElementTextByAttribute(title, Attributes.VALUE)));
        }
    }
    public void getDescriptionOfTheItems(){
        List<WebElement> descriptions = getDriver().findElements(MobileBy.xpath("//XCUIElementTypeStaticText[@name='test-Item description']"));
        for(WebElement description: descriptions){
            log.info("List the Shirt's descriptions :" + getElementTextByAttribute(description, Attributes.LABEL));
        }
    }
    public void tapOn_ItemDisplayName(String name) {
        swipeDownTo_Element(By.xpath("//XCUIElementTypeStaticText[@label='"+name+"']"), 30);
        tapOn_Element(By.xpath("//XCUIElementTypeStaticText[@label='"+name+"']"));
    }

//    public boolean is_HeaderContainerDisplayed() {return waitFor_ElementToBeDisplayed(HEADER_CONTAINER);}

    public boolean is_IconCartDisplayed() {return waitFor_ElementToBeDisplayed(CART_ICON);}

    public boolean is_FilterIconDisplayed() {
        return waitFor_ElementToBeDisplayed(FILTER_ICON);
    }
    public boolean is_ToggleListIconDisplayed() {
        return waitFor_ElementToBeDisplayed(TOGGLE_LIST_ICON);
    }
    public boolean is_ItemsDisplayed() {
        return waitFor_ElementToBeDisplayed(ITEMS);
    }

    public boolean is_ViewProductsDisplayed() {return waitFor_ElementToBeDisplayed(VIEW_PRODUCTS);}
    public boolean is_ProductsDisplayed() {return waitFor_ElementToBeDisplayed(PRODUCTS);}
    public boolean is_RightsTextDisplayed() {return waitFor_ElementToBeDisplayed(RIGHTS_TEXT);}
    public boolean is_TermServiceDisplayed() {return waitFor_ElementToBeDisplayed(TERMS_SERVICE);}

    public boolean is_RobotLogoDisplayed() {return waitFor_ElementToBeDisplayed(ROBOT_LOGO);}

    public void assertHomePage() {
        Assert.assertTrue(is_SwagLabLogoDisplayed(), "FAIL: Unable to find the header container on home page");
        Assert.assertTrue(is_IconCartDisplayed(), "FAIL: Unable to find icon cart on home page");
        Assert.assertTrue(is_ProductsDisplayed(), "FAIL: Unable to displayed products on home page");
        Assert.assertTrue(is_FilterIconDisplayed(), "FAIL: Unable to find filter icon on home page");
        Assert.assertTrue(is_ToggleListIconDisplayed(), "FAIL: Unable to find toggle list icon on home page");
        Assert.assertTrue(is_ViewProductsDisplayed(), "FAIL: Unable to view products list icon on home page");
//        Assert.assertTrue(is_ItemsDisplayed(), "FAIL: unable to find items on home page. ");
        scrollTo_Bottom();
        Assert.assertTrue(is_TermServiceDisplayed(), "FAIL: unable to find the text of terms service on home page");
        Assert.assertTrue(is_RightsTextDisplayed(), "FAIL: unable to find all rights reserved text on home page");
        Assert.assertTrue(is_RobotLogoDisplayed(), "FAIL: unable to find all rights reserved text on home page");
        scrollTo_Top();
    }

    public boolean waitFor_LoadingListView() {
        waitFor_ElementToBeDisplayed(By.xpath("//XCUIElementTypeOther[@label='+']"));
        if(getDriver().findElements(By.xpath("//XCUIElementTypeOther[@label='+']")).size() > 0)
            return true;
        else
            return false;
    }

    public boolean waitFor_LoadingGridView() {
        waitFor_ElementToBeDisplayed(By.xpath("//XCUIElementTypeOther[@label='ADD TO CART']"));
        if(getDriver().findElements(By.xpath("//XCUIElementTypeOther[@label='ADD TO CART']")).size() > 0)
            return true;
        else
            return false;
    }

    public boolean is_ListView() {
        if(getDriver().findElements(By.xpath("//XCUIElementTypeOther[@label='+']")).size() > 0)
            return true;
        else
            return false;
    }

    public boolean is_GridView() {
        if(getDriver().findElements(By.xpath("//XCUIElementTypeOther[@label='ADD TO CART']")).size() > 0)
            return true;
        else
            return false;
    }

    public void addItemToCartViaDragAndDropAction(int count) {
        for(int i=1;i<=count; i++) {
            WebElement source = getDriver().findElement(By.xpath("(//XCUIElementTypeOther[@name='test-Drag Handle']/XCUIElementTypeStaticText)[1]"));
            WebElement target = getDriver().findElement(By.id("test-Cart drop zone"));
            perform_DragAndDropAction(source, target);
        }
    }
}
