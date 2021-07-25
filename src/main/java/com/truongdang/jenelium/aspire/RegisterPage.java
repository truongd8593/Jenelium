package com.truongdang.jenelium.aspire;

import com.truongdang.jenelium.core.BasePage;
import com.truongdang.jenelium.core.Constants;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;

/**
 * @author Truong Dang.
 * Created on Jul, 2021.
 */
public class RegisterPage extends BasePage {
    public RegisterPage(WebDriver driver) {
        super(driver);
    }

    @FindAll({
            @FindBy(xpath = "//input[@data-cy='register-person-name']"),
            @FindBy(xpath = "//input[@name='full_name']")
    })
    private WebElement nameInput;

    @FindAll({
            @FindBy(xpath = "//input[@data-cy='register-person-email']"),
            @FindBy(xpath = "//input[@name='email']")
    })
    private WebElement emailInput;

    @FindAll({
            @FindBy(xpath = "//input[@data-cy='register-person-phone']"),
            @FindBy(xpath = "//input[@name='phone']")
    })
    private WebElement phoneInput;

    @FindAll({
            @FindBy(xpath = "//input[@data-cy='register-person-heard-about']"),
            @FindBy(xpath = "//input[@type='search']")
    })
    private WebElement heardAboutInput;

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
