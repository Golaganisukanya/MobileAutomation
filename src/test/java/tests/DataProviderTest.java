package tests;

import core.readers.ExcelReader;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.HeaderPage;
import pages.HomePage;
import pages.LoginPage;
import pages.MenuPage;

public class DataProviderTest extends MobileBaseTest{

    @Test(dataProvider = "data")
    public void testDataProvider(String username, String password){
        LoginPage loginPage = new LoginPage();
        loginPage.enterText_UserNameTextField(username);
        loginPage.enterText_PasswordTextField(password);
        loginPage.tapOn_LoginButton();

        MenuPage menuPage = new MenuPage();
        menuPage.tapOn_MenuOption(MenuPage.Options.LOGOUT);
    }
    @DataProvider(name = "data")
    public Object[][]testData(){
                ExcelReader.initWorkBook();
        return ExcelReader.getDataFromSheetName("test1");
    }

}
