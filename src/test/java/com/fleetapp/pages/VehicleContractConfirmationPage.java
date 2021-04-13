package com.fleetapp.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static com.fleetapp.utilities.BrowserUtils.*;

public class VehicleContractConfirmationPage extends BasePage{

    @FindBy(xpath = "//a[@title='Delete Vehicle Contract']")
    public WebElement delete;

    @FindBy(xpath = "//a[contains(@class,'ok')]")
    public WebElement deleteConfirm;

    @FindBy(xpath = "//h1[@class='user-name']")
    public WebElement responsibleHeader;

    public String getVehicleInfo(String infoType) {
        String xpath = String.format("//label[contains(.,'%s')]/..//div[@class='control-label']",infoType);
        return getElement(xpath).getText().trim();
    }

    public void deleteContract() {
        clickElement(delete);
        clickElement(deleteConfirm);
        waitUntilTitleIs("All - Vehicle Contract - Entities - System - Car - Entities - System");
    }

}
