package com.truongdang.jenelium.core;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.time.Duration;
import java.util.Set;

/**
 * @author Truong Dang.
 * Created on Jul, 2021.
 */
public class BasePage {
    WebDriver driver;
    WebDriverWait wait;
    Actions act;

    protected By spin = By.xpath("//div[@id='system-loader']");

    protected By popup = By.xpath("//*[@id='toast-container']/descendant::span");

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(this.driver, 300);
        this.act = new Actions(driver);
    }

    public BasePage waitForLoading() {
        wait.until(ExpectedConditions.invisibilityOfElementLocated(spin));
        return this;
    }

    public BasePage waitForPopUp() {
        wait.until(ExpectedConditions.invisibilityOfElementLocated(popup));
        return this;
    }

    public BasePage waitUntilPageContainsElement(String xpath, int seconds) {
        wait.withTimeout(Duration.ofSeconds(seconds)).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
        return this;
    }

    public BasePage clickOnElement(String xpath) {
        WebElement elm = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath)));
        act.moveToElement(elm).build().perform();
        elm.click();
        return this;
    }

    public BasePage clickOnElement(WebElement elm) {
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(elm));
        act.moveToElement(element).build().perform();
        element.click();
        return this;
    }

    public BasePage clickOnElement(By locator) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator)).click();
        return this;
    }

    public BasePage switchToPopUp() {

        //String parentWindowHandler = driver.getWindowHandle(); // Store your parent window
        String subWindowHandler = null;
        Set<String> handles = driver.getWindowHandles(); // get all window handles
        java.util.Iterator<String> iterator = handles.iterator();
        while (iterator.hasNext()){
            subWindowHandler = iterator.next();
        }
        driver.switchTo().window(subWindowHandler); // switch to popup window
        //driver.switchTo().window(parentWindowHandler);  // switch back to parent window
        return this;
    }

    public BasePage clickOnEnter(String xpath) {
        WebElement elm = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath)));
        act.moveToElement(elm).build().perform();
        elm.sendKeys(Keys.ENTER);
        return this;
    }

    public BasePage sendKeysToElement(String xpath, String keys) {
        WebElement elm = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
        act.moveToElement(elm).build().perform();
        elm.sendKeys(keys);
        return this;
    }
    public BasePage sendKeysToElementToEdit(String xpath, String keys) {
        WebElement elm = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
        act.moveToElement(elm).build().perform();
        elm.clear();
        elm.sendKeys(keys);
        return this;
    }

    public String getText(String xpath) {
        WebElement elm = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
        act.moveToElement(elm).build().perform();
        return elm.getText();
    }

    public Boolean verifyElement(String xpath) {
        Boolean result = false;
        try {
            WebElement elm = driver.findElement(By.xpath(xpath));
            act.moveToElement(elm).build().perform();
            waitSomeSeconds(1);
            result = true;
        } catch (Exception e) {
            result = false;
        }
        return result;
    }

    public Boolean verifyWebTitle(String expectedTitle){
        Boolean result = false;
        try{
            String actualTitle = driver.getTitle();
            if (actualTitle.equalsIgnoreCase(expectedTitle))
                result = true;
        } catch (Exception e) {
            result = false;
        }
        return result;
    }

    /**
     * This function will take screenshot
     * @param webdriver
     * @param fileWithPath
     * @throws Exception
     */
    public BasePage captureScreenshot(WebDriver webdriver,String fileWithPath) throws Exception{

        //Convert web driver object to TakeScreenshot
        TakesScreenshot scrShot =((TakesScreenshot)webdriver);

        //Call getScreenshotAs method to create image file
        File SrcFile=scrShot.getScreenshotAs(OutputType.FILE);

        //Move image file to new destination
        File DestFile=new File(fileWithPath);

        //Copy file at destination
        FileUtils.copyFile(SrcFile, DestFile);

        return this;

    }

    public BasePage navigateToUrl(String url) {
        driver.get(url);
        return this;
    }

    public BasePage maximizeBrowserWindow() {
        driver.manage().window().maximize();
        return this;
    }

    public BasePage waitSomeSeconds(int numberOfSeconds) {
        try {
            Thread.sleep(numberOfSeconds * 1000);
        } catch(Exception e){
            System.out.println("Warning: Some Other exception");
        }
        return this;
    }
}
