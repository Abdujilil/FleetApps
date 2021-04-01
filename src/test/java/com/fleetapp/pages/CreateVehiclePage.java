package com.fleetapp.pages;

import com.fleetapp.utilities.Driver;
import com.fleetapp.utilities.GlobalDataUtils;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static com.fleetapp.utilities.BrowserUtils.*;
import static com.fleetapp.utilities.DataUtils.*;

public class CreateVehiclePage extends BasePage{

    public GlobalDataUtils globalData = new GlobalDataUtils();

    @FindBy(name = "custom_entity_type[LicensePlate]")
    public WebElement licensePlate;

    @FindBy(xpath = "//div[contains(@id,'Tags')]//input")
    public List<WebElement> vehicleTypeCheckBoxes;

    @FindBy(xpath = "//label[contains(@for,'Tags')]")
    public List<WebElement> vehicleTypes;

    @FindBy(name = "custom_entity_type[Driver]")
    public WebElement driver;

    @FindBy(xpath = "//div[contains(@class,'pull-right')]/button[contains(.,'Save and Close')]")
    public WebElement saveAndClose;

    @FindBy(id = "oro-dropdown-mask")
    public WebElement dropdownMask;

    @FindBy(xpath = "//a[@title='Cancel']")
    public WebElement cancel;

    @FindBy(xpath = "(//span[contains(.,'This value is not')])[last()]")
    public WebElement errorMessage;

    public void createAVehicle(String fullOrPartial) {
        if (fullOrPartial.equalsIgnoreCase("partial")) {
            fillPartialNewVehicleInfo();
        } else if (fullOrPartial.equalsIgnoreCase("full")) {
            fillAllNewVehicleInfo();
        }
        submit();
    }

    public void fillPartialNewVehicleInfo() {
        generatePartialNewVehicleInfo();
        licensePlate.sendKeys(globalData.getLicensePlate());
        int vehicleTypeIndex = generateRandomNumber(0, vehicleTypeCheckBoxes.size() - 1);
        WebElement vehicleType = vehicleTypeCheckBoxes.get(vehicleTypeIndex);
        globalData.setVehicleType(vehicleTypes.get(vehicleTypeIndex).getText().trim());
        vehicleType.click();
        enterInfo("Driver",globalData.getDriver());
        enterInfo("Model Year",globalData.getModelYear());
        enterInfo("Color",globalData.getColor());
    }

    public void fillAllNewVehicleInfo() {
        generateFullNewVehicleInfo();
        fillPartialNewVehicleInfo();
        enterInfo("Location",globalData.getLocation());
        enterInfo("Chassis Number",globalData.getChassisNumber());
        enterInfo("Last Odometer",globalData.getLastOdometer());
        enterInfo("Immatriculation Date",globalData.getImmatriculationDate());
        enterInfo("First Contract Date",globalData.getFirstContractDate());
        enterInfo("Catalog Value",globalData.getCatalogValue());
        enterInfo("Seats Number",globalData.getSeatsNumber());
        enterInfo("Doors Number",globalData.getDoorsNumber());
        selectType("Transmission");
        selectType("FuelType");
        enterInfo("C02 Emissions",globalData.getC02Emissions());
        enterInfo("Horsepower",globalData.getHorsepower());
        enterInfo("Horsepower Taxation",globalData.getHorsepowerTaxation());
        enterInfo("Power",globalData.getPower());
    }

    public void selectType(String dropDown) {
        String dropdownXpath = String.format("//div[contains(@id,'%s')]/a",dropDown);
        String optionsXpath = "//li[contains(@class,'highlighted')]/..//li";
        Actions actions = new Actions(Driver.get());
        actions.moveToElement(getElement(dropdownXpath)).click().perform();
        List<WebElement> options = getElements(optionsXpath);
        WebElement option = options.get(generateRandomNumber(0, options.size() - 1));
        if (dropDown.equalsIgnoreCase("transmission")) {
            globalData.setTransmission(option.getText().trim());
        } else if (dropDown.equalsIgnoreCase("fuelType")) {
            globalData.setFuelType(option.getText().trim());
        }
        option.click();
    }

    public void generatePartialNewVehicleInfo() {
        String licensePlate = "CA" + generateRandomNumber(5);
        String driver = generateRandomFullName();
        String color = generateRandomColor();
        String modelYear = LocalDate.now().getYear() + "";
        globalData.setLicensePlate(licensePlate);
        globalData.setDriver(driver);
        globalData.setModelYear(modelYear);
        globalData.setColor(color);
    }

    public void generateFullNewVehicleInfo() {
        generatePartialNewVehicleInfo();
        String location = generateRandomCity();
        String chassisNumber = generateRandomNumber(7);
        String lastOdometer = generateRandomNumber(3);
        String immatriculationDate = LocalDate.now().minusWeeks(3).format(DateTimeFormatter.ofPattern("MMM d, yyyy"));
        String firstContractDate = LocalDate.now().minusWeeks(2).format(DateTimeFormatter.ofPattern("MMM d, yyyy"));
        String catalogValue = generateRandomNumber(5);
        String seatNumber = generateRandomNumber(4,7) + "";
        String c02Emission = generateRandomNumber(1,5) + "";
        String horsePower = generateRandomNumber(100,500) + "";
        String horsePowerTax = Math.round(Integer.parseInt(horsePower) * 2.5) + "";
        String power = Math.round(Integer.parseInt(horsePower)/1.369) + "";
        globalData.setLocation(location);
        globalData.setChassisNumber(chassisNumber);
        globalData.setLastOdometer(lastOdometer);
        globalData.setImmatriculationDate(immatriculationDate);
        globalData.setFirstContractDate(firstContractDate);
        globalData.setCatalogValue(catalogValue);
        globalData.setSeatsNumber(seatNumber);
        globalData.setDoorsNumber(4+"");
        globalData.setC02Emissions(c02Emission);
        globalData.setHorsepower(horsePower);
        globalData.setHorsepowerTaxation(horsePowerTax);
        globalData.setPower(power);
    }

    public void cancel() {
        clickElement(cancel);
    }

    public void enterInfo(String infoType, String data) {
        String infoTypeXpath = String.format("//label[contains(.,'%s')]/../following-sibling::div/input",infoType);
        getElement(infoTypeXpath).sendKeys(data);
        if (infoType.equals("Immatriculation Date") || infoType.equals("First Contract Date")) {
            clickElement(dropdownMask);
        }
    }

    public void submit() {
        scrollTo(saveAndClose);
        saveAndClose.click();
    }
}
