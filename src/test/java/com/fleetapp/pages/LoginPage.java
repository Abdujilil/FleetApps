package com.fleetapp.pages;

import com.fleetapp.utilities.ConfigurationReader;
import com.fleetapp.utilities.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends BasePage{

    public LoginPage() {

        PageFactory.initElements(Driver.get(),this);

    }

    @FindBy(id = "prependedInput")
    public WebElement usernameInput;

    @FindBy(id = "prependedInput2")
    public WebElement passwordInput;

    @FindBy(id = "_submit")
    public WebElement loginButton;

    @FindBy(xpath = "//div[contains(@class,'alert-error')]")
    public WebElement errorMessage;

    public String getErrorMessage() {
        return errorMessage.getText().trim();
    }

    public void login(String username, String password) {
        usernameInput.sendKeys(username);
        passwordInput.sendKeys(password);
        loginButton.click();
    }

    public void loginAsStoreManager() {
        String username = ConfigurationReader.get("storemanager_username");
        String password = ConfigurationReader.get("storemanager_password");
        login(username,password);
    }

    public void loginAsDriver() {
        String username = ConfigurationReader.get("driver_username");
        String password = ConfigurationReader.get("driver_password");
        login(username,password);
    }

    public void loginAsSalesManager() {
        String username = ConfigurationReader.get("salesmanager_username");
        String password = ConfigurationReader.get("salesmanager_password");
        login(username,password);
    }

}
