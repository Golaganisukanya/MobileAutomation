package pages;

import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class WebViewPage extends HeaderPage {

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name=\"WEBVIEW SELECTION\"]")
    @AndroidFindBy(accessibility = "test-Toggle")
    WebElement WEBVIEW_LABEL_TEXT;

    @iOSXCUITFindBy(accessibility = "test-GO TO SITE")
    @AndroidFindBy(accessibility = "test-Toggle")
    WebElement GO_TO_SITE_BUTTON;

    @iOSXCUITFindBy(accessibility = "test-enter a https url here...")
    @AndroidFindBy(accessibility = "test-Toggle")
    WebElement URL_TEXT_FIELD;

    @iOSXCUITFindBy(xpath = "(//XCUIElementTypeOther[@name=\"Vertical scroll bar, 1 page Horizontal scroll bar, 1 page\"])[3]/XCUIElementTypeOther[2]/XCUIElementTypeOther/XCUIElementTypeImage")
    @AndroidFindBy(accessibility = "test-Toggle")
    WebElement LOADING_ICON;

    public WebViewPage() {
        PageFactory.initElements(new AppiumFieldDecorator(getDriver()), this);
    }

    public WebViewPage enterText_URLTextField(String URL) {
        waitFor_ElementAndType(URL_TEXT_FIELD, URL);
        return this;
    }

    public WebViewPage tapOn_GoToSiteButton() {
        waitFor_ElementAndTap(GO_TO_SITE_BUTTON);
        return this;
    }

}
