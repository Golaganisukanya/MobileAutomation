package pages;

import core.driver.Mobile;

public class PageManager extends Mobile {

    public LoginPage loginPage = null;
    public HomePage homePage = null;
    public CartPage cartPage = null;
    public MenuPage menuPage = null;
    public ProductsPage productPage = null;
    public DescriptionItemPage describePage = null;
    public CheckOutPage checkOutPage = null;

    public PageManager() {

    }

    public LoginPage getLoginPage() {
        if(loginPage == null)
            loginPage = new LoginPage();
        return loginPage;
    }

    public HomePage getHomePage() {
        if(homePage == null)
            homePage = new HomePage();
        return homePage;
    }

    public MenuPage getMenuPage() {
        if(menuPage == null)
            menuPage = new MenuPage();
        return menuPage;
    }


}
