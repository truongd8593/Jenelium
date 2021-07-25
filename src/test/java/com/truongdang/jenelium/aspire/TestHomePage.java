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
public class TestHomePage {
    private WebDriver driver;

    private LoginPage page;

    private SoftAssertions softAssertion = new SoftAssertions();

    @Before
    public void setUp() throws Exception {
        driver = DriverFactory.createDriver(BrowserType.CHROME);
        page = new LoginPage(driver);
    }

    @Test
    public void testVisitHomePage() {
        page.visitAspireHomePage();
        softAssertion.assertThat(page.getPageTitle()).isEqualTo(Constants.LOGIN_PAGE_TITLE);
    }

    @After
    public void tearDown() {
        softAssertion.assertAll();
        driver.quit();
    }
}
