package com.truongdang.jenelium.aspire;

import com.truongdang.jenelium.core.BasePage;
import com.truongdang.jenelium.core.Constants;
import org.openqa.selenium.WebDriver;

/**
 * @author Truong Dang.
 * Created on Jul, 2021.
 */
public class LoginPage extends BasePage {
    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public LoginPage visitAspireHomePage() {
        this
                .navigateToUrl(Constants.ASPIRE_HOME_PAGE)
                .waitForPageLoading()
                .waitUntilPageContainsText(Constants.LOGIN_TEXT, 3);
        return this;
    }

    public LoginPage clickOnRegister() {
        this.clickOnHyperText(Constants.REGISTER).waitForPageLoading();
        return this;
    }
}
