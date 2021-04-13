package com.fleetapp.pages;

import com.fleetapp.utilities.GlobalDataUtils;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.fleetapp.utilities.BrowserUtils.*;
import static com.fleetapp.utilities.DataUtils.*;

public class CreateVehicleContractPage extends BasePage{

    public GlobalDataUtils globalDataUtils = new GlobalDataUtils();

    @FindBy(xpath = "//div[contains(@class,'pull-right')]/button[contains(.,'Save and Close')]")
    public WebElement saveAndClose;

    @FindBy(id = "oro-dropdown-mask")
    public WebElement dropdownMask;

    public void createNewContract() {
        fillUpContractForm();
        clickElement(saveAndClose);
    }

    public void fillUpContractForm() {
        generateRandomContractInfo();
        enterInfo("Responsible", globalDataUtils.getResponsible());
        enterInfo("Odometer Details", globalDataUtils.getOdometerDetails()+"");
        enterInfo("Contract Start Date", globalDataUtils.getContractStartDate());
        enterInfo("Driver", globalDataUtils.getDriver());
    }

    public void enterInfo(String infoType, String data) {
        String infoTypeXpath = String.format("//label[contains(.,'%s')]/../following-sibling::div/input",infoType);
        getElement(infoTypeXpath).sendKeys(data);
        if (infoType.equals("Contract Start Date")) {
            clickElement(dropdownMask);
        }
    }

    public void generateRandomContractInfo() {
        String responsible = generateRandomFullName();
        String odometerDetails = generateRandomNumber(3) + 0.1;
        String contractStartDate = LocalDate.now().plusDays(1).format(DateTimeFormatter.ofPattern("MMM d, yyyy"));
        String driver = generateRandomFullName();
        globalDataUtils.setResponsible(responsible);
        globalDataUtils.setOdometerDetails(odometerDetails);
        globalDataUtils.setContractStartDate(contractStartDate);
        globalDataUtils.setDriver(driver);
    }

}
