package com.fleetapp.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static com.fleetapp.utilities.BrowserUtils.*;

public class VehiclesPage extends BasePage{

    @FindBy(xpath = "//a[@title='Create Car']")
    public WebElement createCar;

    public void createCar() {
        waitTilLoaderDisappears();
        clickElement(createCar);
    }

}
