package com.truongdang.jenelium.aspire;

import com.truongdang.jenelium.core.BasePage;
import com.truongdang.jenelium.core.Constants;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * @author Truong Dang.
 * Created on Jul, 2021.
 */
public class RegisterPage extends BasePage {
    public RegisterPage(WebDriver driver) {
        super(driver);
    }

    private By nameInput = By.xpath("//input[@data-cy='register-person-name']");
    private By emailInput = By.xpath("//input[@data-cy='register-person-email']");
    private By phoneInput = By.xpath("//input[@data-cy='register-person-phone']");
    private By heardAboutInput = By.xpath("//input[@data-cy='register-person-heard-about']");

    public RegisterPage inputName(String text) {
        this.sendKeysToElement(nameInput, text);
        return this;
    }

    public RegisterPage inputEmail(String text) {
        this.sendKeysToElement(emailInput, text);
        return this;
    }

    public RegisterPage inputPhone(String text) {
        this.sendKeysToElement(phoneInput, text);
        return this;
    }

    public RegisterPage inputHeardAbout(String text) {
        this.sendKeysToElementByJs(heardAboutInput, text);
        return this;
    }

    public RegisterPage acceptTermAndConditions() {
        this.clickOnCheckboxBeforeText(Constants.TERM_AND_CONDITIONS_TEXT);
        return this;
    }
}
