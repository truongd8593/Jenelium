package com.truongdang.jenelium.aspire;

import com.truongdang.jenelium.core.BrowserType;
import com.truongdang.jenelium.core.Constants;
import com.truongdang.jenelium.core.DriverFactory;
import org.assertj.core.api.SoftAssertions;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

/**
 * @author Truong Dang.
 * Created on Jul, 2021.
 */
public class TestRegisterPage {
    private WebDriver driver;

    private LoginPage homePage;
    private RegisterPage registerPage;

    private SoftAssertions softAssertion = new SoftAssertions();

    @Before
    public void setUp() throws Exception {
        driver = DriverFactory.createDriver(BrowserType.CHROME);
        homePage = new LoginPage(driver);
        registerPage = new RegisterPage(driver);
    }

    @Test
    public void testRegisterPage() throws Exception {
        homePage.visitAspireHomePage();
        softAssertion.assertThat(homePage.getPageTitle()).isEqualTo(Constants.LOGIN_PAGE_TITLE);

        homePage
                .waitUntilPageContainsHyperText(Constants.REGISTER, 10)
                .waitForPageLoading()
                .clickOnHyperText(Constants.REGISTER)
                .waitForPageLoading();

        softAssertion.assertThat(registerPage.getPageTitle()).isEqualTo(Constants.REGISTER_PAGE_TITLE);
        registerPage.waitForPageLoading();
        registerPage
                .inputName("Truong Dang")
                .inputPhone("01234567")
                .inputEmail("user@gmail.com")
                .inputHeardAbout("Facebook")
                .acceptTermAndConditions()
                .captureScreenshot(driver, "ScreenShot.png");
    }

    @After
    public void tearDown() {
        softAssertion.assertAll();
        driver.quit();
    }
}
