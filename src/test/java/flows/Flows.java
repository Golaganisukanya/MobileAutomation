package flows;

import pages.HomePage;
import pages.LoginPage;
import pages.MenuPage;

public class Flows {

    public static HomePage performSuccessfulLogin(LoginPage loginPage, String username, String password) {
        loginPage.enterText_UserNameTextField(username);
        loginPage.enterText_PasswordTextField(password);
        return loginPage.tapOn_LoginButton();
    }

    public static LoginPage performLogOut(MenuPage menuPage) {
        menuPage.tapOn_LogoutOption();
        return new LoginPage();
    }

}
