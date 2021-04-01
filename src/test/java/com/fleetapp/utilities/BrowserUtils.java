package com.fleetapp.utilities;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class BrowserUtils {

    private static String parentWindow;

    public static void sleep(double seconds){
        try {
            Thread.sleep((long) (seconds * 1000));
        }catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static WebElement getElement(String xpath, String value){
        WebDriverWait wait = new WebDriverWait(Driver.get(),5);
        return wait.until(ExpectedConditions
                .presenceOfElementLocated(By.xpath(String.format(xpath,value))));
    }

    public static WebElement getElement(String xpath){
        WebDriverWait wait = new WebDriverWait(Driver.get(),5);
        return wait.until(ExpectedConditions
                .visibilityOfElementLocated(By.xpath(xpath)));
    }

    public static List<WebElement> getElements(String xpath){
        WebDriverWait wait = new WebDriverWait(Driver.get(),5);
        return wait.until(ExpectedConditions
                .visibilityOfAllElementsLocatedBy(By.xpath(xpath)));
    }

    public static void clickElement(WebElement element){
        waitUntilClickable(element).click();
    }

    public static void clickAndSend(String xpath,String value) {
        WebElement element = getElement(xpath);
        clickElement(element);
        element.sendKeys(value);
    }

    public static void clickElement(String xpath){
        waitUntilClickable(getElement(xpath)).click();
    }

    public static void clickElement(String xpath, String value){
        waitUntilClickable(getElement(xpath,value)).click();
    }

    public static void scrollTo(String xpath,String value){
        ((JavascriptExecutor) Driver.get()).executeScript("arguments[0].scrollIntoView(true);",
                getElement(xpath,value));
    }

    public static void scrollTo(String xpath){
        ((JavascriptExecutor) Driver.get()).executeScript("arguments[0].scrollIntoView(true);",
                getElement(xpath));
    }

    public static void scrollTo(WebElement element){
        ((JavascriptExecutor) Driver.get()).executeScript("arguments[0].scrollIntoView(true);",
                element);
    }

    public static void scrollAndClick(String xpath,String value){
        scrollTo(xpath,value);
        clickElement(xpath,value);
    }

    public static void switchToParentWindow(){
        Driver.get().switchTo().window(parentWindow);
    }

    public static boolean elementDisplayed(String xpath){
        WebDriverWait wait = new WebDriverWait(Driver.get(),5);
        return wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).isDisplayed();
    }

    public static boolean elementDisplayed(String xpath,String value){
        WebDriverWait wait = new WebDriverWait(Driver.get(),5);
        return wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(String.format(xpath,value)))).isDisplayed();
    }

    public static boolean elementSelected(String xpath){
        WebDriverWait wait = new WebDriverWait(Driver.get(),5);
        return wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).isSelected();
    }

    public static boolean elementSelected(String xpath,String value){
        WebDriverWait wait = new WebDriverWait(Driver.get(),5);
        return wait.until(ExpectedConditions.elementToBeSelected(By.xpath(String.format(xpath,value))));
    }

    public static List<String> getTextOfElements(List<WebElement> elements) {
        List<String> texts = new ArrayList<>();
        for (WebElement eachElement : elements) {
            texts.add(eachElement.getText().trim());
        }
        return texts;
    }

    public static WebElement waitUntilClickable(WebElement element) {
        WebDriverWait wait = new WebDriverWait(Driver.get(),10);
        return wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public static void waitForClickable(WebElement element, int timeToWaitInSec) {
        WebDriverWait wait = new WebDriverWait(Driver.get(), timeToWaitInSec);
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public static boolean waitUntilTextToBe(WebElement element, String text) {
        WebDriverWait wait = new WebDriverWait(Driver.get(),10);
        return wait.until(ExpectedConditions.textToBePresentInElement(element,text));
    }

    public static void waitUntilUrlToBe(String url) {
        WebDriverWait wait = new WebDriverWait(Driver.get(), 5);
        wait.until(ExpectedConditions.urlToBe(url));
    }

    public static void waitUntilTitleContains(String title) {
        WebDriverWait wait = new WebDriverWait(Driver.get(), 10);
        wait.until(ExpectedConditions.titleContains(title));
    }

    public static void waitUntilTitleIs(String title) {
        WebDriverWait wait = new WebDriverWait(Driver.get(), 10);
        wait.until(ExpectedConditions.titleIs(title));
    }

    public static void waitUntilInVisible(String xpath) {
        WebDriverWait wait = new WebDriverWait(Driver.get(),10);
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(xpath)));
    }

    public static void waitUntilVisible(WebElement element) {
        WebDriverWait wait = new WebDriverWait(Driver.get(),15);
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public static void waitUntilVisible(String xpath) {
        WebDriverWait wait = new WebDriverWait(Driver.get(),15);
        wait.until(ExpectedConditions.visibilityOf(getElement(xpath)));
    }

    public static void hoverOver(String xpath) {
        Actions act = new Actions(Driver.get());
        act.moveToElement(Driver.get().findElement(By.xpath(xpath))).perform();
    }

    public static void hoverOver(WebElement element) {
        Actions act = new Actions(Driver.get());
        act.moveToElement(element).perform();
    }

    public static void turnOnImplicitWait() {
        Driver.get().manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

    public static void turnOffImplicitWait() {
        Driver.get().manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
    }

    /**
     * waits for backgrounds processes on the browser to complete
     *
     * @param timeOutInSeconds
     */
    public static void waitForPageToLoad(long timeOutInSeconds) {
        ExpectedCondition<Boolean> expectation = new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver driver) {
                return ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
            }
        };
        try {
            WebDriverWait wait = new WebDriverWait(Driver.get(), timeOutInSeconds);
            wait.until(expectation);
        } catch (Throwable error) {
            error.printStackTrace();
        }
    }

    public static String getCurrentPageTitle() {
        return Driver.get().getTitle();
    }


}