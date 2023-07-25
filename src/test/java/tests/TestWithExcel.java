package tests;

import core.readers.ExcelReader;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.*;

public class TestWithExcel extends MobileBaseTest{
    @Test(dataProvider = "data")
    public void testExcel(String username, String pwd,
                              String shirtTitle, String shirtDescription,
                              String fname, String lname, String zcode,
                              String thankyouTitle, String thankyouDescription){

        LoginPage loginPage = new LoginPage();
        loginPage.perform_successfulLogin(username, pwd);

        HomePage homePage = new HomePage();
        homePage.tapOn_ItemDisplayName(shirtTitle);


        DescriptionItemPage descriptionItemPage = new DescriptionItemPage();
        Assert.assertTrue(descriptionItemPage.is_TShirtTitleDisplay(shirtTitle), "FAIL: T shirt title not match.");
//        Assert.assertTrue(descriptionItemPage.is_TShirtTitleDisplay(shirtDescription), "FAIL: T shirt title not match.");

        descriptionItemPage.tapOn_AddToCartButton();
        descriptionItemPage.tapOn_CartIcon();

        CartPage cartPage = new CartPage();
        cartPage.tapOn_CheckOut();

        CheckOutPage checkOutPage = new CheckOutPage();
        checkOutPage.type_CheckOutInformation(fname, lname, zcode);

        CheckOutOverViewPage checkOutOverViewPage = new CheckOutOverViewPage();
        checkOutOverViewPage.tapOn_FinishButton();

        CheckOutComplete checkOutComplete = new CheckOutComplete();
        Assert.assertEquals(checkOutComplete.getThankYouTitle(), thankyouTitle, "FAIL: Tshirt title not match.");
        Assert.assertEquals(checkOutComplete.getThankYouDescription(), thankyouDescription, "FAIL: Tshirt title not match.");

        checkOutComplete.tapOn_BackHomeButton();
        homePage.tapOn_MenuIcon();

        MenuPage menuPage = new MenuPage();
        menuPage.tapOn_LogoutOption();


    }
    @DataProvider(name = "data")
    public Object[][]testData(){
        ExcelReader.initWorkBook();
        return ExcelReader.getDataFromSheetName("test1");
    }
}
