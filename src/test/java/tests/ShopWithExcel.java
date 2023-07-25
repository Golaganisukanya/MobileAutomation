package tests;

import core.readers.ExcelReader;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.DescriptionItemPage;
import pages.HomePage;
import pages.LoginPage;

public class ShopWithExcel extends MobileBaseTest{

    @Test(dataProvider = "data")

        public void shoppingExcel(String username, String pwd,
                                  String shirtTitle, String shirtDescription,
                                  String one, String two){

        LoginPage loginPage = new LoginPage();
        loginPage.perform_successfulLogin(username, pwd);

//            HomePage homePage = new HomePage();
//            homePage.tapOn_ListToggleIcon();
//            homePage.tapOn_FilterIcon();
//            homePage.tapOn_FilterOption(HomePage.FilterOptions.PRICE_LOW_TO_HIGH);
//            homePage.tapOn_ShirtByDisplayName(shirtTitle);
//
//            DescriptionItemPage descriptionItemPage = new DescriptionItemPage();
//            Assert.assertTrue(descriptionItemPage.is_TShirtTitleDisplay(shirtTitle), "FAIL: T shirt title not match.");
//            Assert.assertTrue(descriptionItemPage.is_TShirtTitleDisplay(shirtDescription), "FAIL: T shirt title not match.");
//            descriptionItemPage.tapOn_BackToProduct();
//            homePage.scrollTo_Top();
//
//            homePage.tapOn_ShirtByDisplayName(shirtTitle);
        HomePage home = new HomePage();
        home.tapOn_ListToggleIcon();
        home.tapOn_FilterIcon();
        home.tapOn_FilterOption(HomePage.FilterOptions.PRICE_LOW_TO_HIGH);
        home.tapOn_ItemDisplayName("Sauce Labs Fleece Jacket");

        DescriptionItemPage desc = new DescriptionItemPage();
        desc.tapOn_BackToProduct();
        home.scrollTo_Top();

        home.tapOn_ItemDisplayName("Sauce Labs Bike Light");

        desc.tapOn_AddToCartButton();


    }
    @DataProvider(name = "data")
    public Object[][]testData(){
        ExcelReader.initWorkBook();
        return ExcelReader.getDataFromSheetName("test2");
    }
}
