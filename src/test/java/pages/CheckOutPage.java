package pages;

import core.driver.Mobile;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class CheckOutPage extends Mobile {
    final static Logger log = Logger.getLogger(CheckOutPage.class);

    @iOSXCUITFindBy(accessibility = "test-First Name")
    @AndroidFindBy(accessibility = "test-First Name")
    WebElement First_Name;

    @iOSXCUITFindBy(accessibility = "test-Last Name")
    @AndroidFindBy(accessibility = "test-Last Name")
    WebElement Last_Name;

    @iOSXCUITFindBy(accessibility = "test-Zip/Postal Code")
    @AndroidFindBy(accessibility = "test-Zip/Postal Code")
    WebElement Zip_Code;

    @iOSXCUITFindBy(accessibility = "test-CANCEL")
    @AndroidFindBy(accessibility = "test-CANCEL")
    WebElement CANCEL_BUTTON;

    @iOSXCUITFindBy(accessibility = "test-CONTINUE")
    @AndroidFindBy(accessibility = "test-CONTINUE")
    WebElement CONTINUE_BUTTON;


    public CheckOutPage(){
        PageFactory.initElements(new AppiumFieldDecorator(getDriver()), this);
    }

    public void enterText_FirstName(String firstname){
        waitFor_ElementAndType(First_Name, firstname);
    }
    public void enterText_LastName(String lastname){
        typeOn_Element(Last_Name, lastname);
    }
    public void enterNumber_ZipCode(String zipcode) {
        typeOn_Element(Zip_Code, zipcode);
    }
    public void tapOn_ContinueButton(){
        tapOn_Element(CONTINUE_BUTTON);
    }
    public void tapOn_CancelButton(){tapOn_Element(CANCEL_BUTTON);}

    public void type_CheckOutInformation(String firstname, String lastname, String zipcode){
        log.info("Enter the information:" + firstname + lastname + zipcode);
        enterText_FirstName(firstname);
        enterText_LastName(lastname);
        enterNumber_ZipCode(zipcode);
        tapOn_ContinueButton();
    }
}
