package com.fleetapp.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static com.fleetapp.utilities.BrowserUtils.*;

public class NewVehicleConfirmationPage extends BasePage{

    @FindBy(xpath = "//h5[@class='user-fieldset']")
    public WebElement generalInformationTag;

    @FindBy(xpath = "//a[@title='Delete Car']")
    public WebElement delete;

    @FindBy(xpath = "//a[contains(@class,'ok')]")
    public WebElement deleteConfirm;

    public String getVehicleInfo(String infoType) {
        String xpath = String.format("//label[contains(.,'%s')]/..//div[@class='control-label']",infoType);
        return getElement(xpath).getText().trim();
    }

    public void deleteCar() {
        clickElement(delete);
        clickElement(deleteConfirm);
        waitUntilTitleIs("All - Car - Entities - System - Car - Entities - System");
    }

}
