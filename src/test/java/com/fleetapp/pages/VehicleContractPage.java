package com.fleetapp.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

import static com.fleetapp.utilities.BrowserUtils.*;

public class VehicleContractPage extends BasePage{

    @FindBy(xpath = "//a[@title='Create Vehicle Contract']")
    public WebElement createVehicleContract;

    @FindBy(xpath = "//label[contains(.,'Total of')]")
    public WebElement totalContractLabel;

    @FindBy(xpath = "//div[contains(@class,'page-size')]//button")
    public WebElement viewPerPageDropdown;

    public void selectViewPerPage(String dataSize) {
        String dataSizeXPath = String.format("//a[@data-size='%s']",dataSize);
        clickElement(viewPerPageDropdown);
        clickElement(dataSizeXPath);

        boolean correctSize = false;
        while (!correctSize) {
            List<WebElement> elements = getElements("//tbody/tr");
            if (dataSize.equals(elements.size()+"")) {
                correctSize = true;
            }
        }
    }

    public void createVehicleContract() {
        waitTilLoaderDisappears();
        clickElement(createVehicleContract);
    }

    public int getTotalContractCount() {
        String label = totalContractLabel.getText().trim();
        String result = "";
        for (int i = 0; i < label.length(); i++) {
            char eachChar = label.charAt(i);
            if (Character.isDigit(eachChar)) {
                result += eachChar;
            }
        }
        return Integer.parseInt(result);
    }

    public String getNumberOfContractsDisplayed() {
        return getElements("//tbody/tr").size() + "";
    }

}
