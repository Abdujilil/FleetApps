package com.fleetapp.pages;

import com.fleetapp.utilities.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

import static com.fleetapp.utilities.BrowserUtils.*;

public abstract class BasePage {

    public BasePage() {
        PageFactory.initElements(Driver.get(),this);
    }

    @FindBy(xpath = "//span[contains(@class,'title-level-1')]")
    public List<WebElement> menuOptions;

    @FindBy(xpath = "//li/a[@class='dropdown-toggle']")
    public WebElement userFullName;

    @FindBy(xpath = "//div[@id='main-menu']")
    public WebElement mainMenu;

    @FindBy(xpath = "//li[contains(@class,'align-menu-right')]//li[contains(@class,'dropdown-menu-single-item')]")
    public List<WebElement> fleetOptions;

    @FindBy(xpath = "//div[@class='loader-mask shown']")
    public WebElement loaderMask;

    @FindBy(xpath = "//div[@class='message']")
    public WebElement errorMessage;

    public void hoverOverModule(String module) {
        String moduleXpath = "//span[normalize-space()='" + module + "' and contains(@class,'title title-level-1')]";
        waitTilLoaderDisappears();
        hoverOver(moduleXpath);
    }

    public void navigateToModule(String module, String option) {
        String optionXpath = String.format("//span[contains(.,'%s')]/..",option);
        hoverOverModule(module);
        clickElement(optionXpath);
    }

    public void waitTilLoaderDisappears() {
        boolean present = true;
        String loaderXpath = "//div[@class='loader-mask shown']";
        waitUntilVisible(mainMenu);
        waitUntilVisible(loaderXpath);
        turnOffImplicitWait();
        while (present) {
            try {
                Driver.get().findElement(By.xpath(loaderXpath));
            } catch (NoSuchElementException e) {
                present = false;
            }
        }
        turnOnImplicitWait();
    }


}

