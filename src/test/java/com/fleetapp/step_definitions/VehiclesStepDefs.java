package com.fleetapp.step_definitions;

import com.fleetapp.pages.CreateVehiclePage;
import com.fleetapp.pages.NewVehicleConfirmationPage;
import com.fleetapp.pages.VehiclesPage;
import com.fleetapp.utilities.GlobalDataUtils;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static com.fleetapp.utilities.BrowserUtils.*;
import static org.junit.Assert.*;

public class VehiclesStepDefs {

    private GlobalDataUtils globalData;

    @When("the user creates a vehicle with {string} information")
    public void the_user_creates_a_vehicle_with_information(String fullOrPartial) {
        new VehiclesPage().createCar();
        CreateVehiclePage createVehiclePage = new CreateVehiclePage();
        createVehiclePage.waitTilLoaderDisappears();
        createVehiclePage.createAVehicle(fullOrPartial);
        globalData = createVehiclePage.globalData;
    }

    @Then("the new vehicle information shown on confirmation page should be correct")
    public void the_new_vehicle_information_shown_on_confirmation_page_should_be_correct() {
        NewVehicleConfirmationPage confirmationPage = new NewVehicleConfirmationPage();
        waitUntilVisible(confirmationPage.generalInformationTag);
        String actualLicensePlate = confirmationPage.getVehicleInfo("License Plate");
        String actualVehicleType = confirmationPage.getVehicleInfo("Tags");
        String actualDriver = confirmationPage.getVehicleInfo("Driver");
        String actualModelYear = confirmationPage.getVehicleInfo("Model Year");
        String actualColor = confirmationPage.getVehicleInfo("Color");

        assertEquals(globalData.getLicensePlate(), actualLicensePlate);
        assertEquals(globalData.getVehicleType(), actualVehicleType);
        assertEquals(globalData.getDriver(), actualDriver);
        assertEquals(globalData.getModelYear(), actualModelYear);
        assertEquals(globalData.getColor(), actualColor);

        confirmationPage.deleteCar();
    }

    @Then("the new vehicle information shown on confirmation page should be correct part one")
    public void the_new_vehicle_information_shown_on_confirmation_page_should_be_correct_part_one() {
        NewVehicleConfirmationPage confirmationPage = new NewVehicleConfirmationPage();
        waitUntilVisible(confirmationPage.generalInformationTag);
        String actualLicensePlate = confirmationPage.getVehicleInfo("License Plate");
        String actualVehicleType = confirmationPage.getVehicleInfo("Tags");
        String actualDriver = confirmationPage.getVehicleInfo("Driver");
        String actualLocation = confirmationPage.getVehicleInfo("Location");

        assertEquals(globalData.getLicensePlate(), actualLicensePlate);
        assertEquals(globalData.getVehicleType(), actualVehicleType);
        assertEquals(globalData.getDriver(), actualDriver);
        assertEquals(globalData.getLocation(), actualLocation);

        confirmationPage.deleteCar();
    }

    @Then("the new vehicle information shown on confirmation page should be correct part two")
    public void the_new_vehicle_information_shown_on_confirmation_page_should_be_correct_part_two() {
        NewVehicleConfirmationPage confirmationPage = new NewVehicleConfirmationPage();
        waitUntilVisible(confirmationPage.generalInformationTag);
        String actualChassisNumber = confirmationPage.getVehicleInfo("Chassis Number");
        String actualModelYear = confirmationPage.getVehicleInfo("Model Year");
        String actualLastOdometer = confirmationPage.getVehicleInfo("Last Odometer");
        String actualImmatriculationDate = confirmationPage.getVehicleInfo("Immatriculation Date");


        assertEquals(globalData.getChassisNumber(), actualChassisNumber);
        assertEquals(globalData.getModelYear(), actualModelYear);
        assertEquals(globalData.getLastOdometer(), actualLastOdometer);
        assertEquals(globalData.getImmatriculationDate(), actualImmatriculationDate);

        confirmationPage.deleteCar();
    }

    @Then("the new vehicle information shown on confirmation page should be correct part three")
    public void the_new_vehicle_information_shown_on_confirmation_page_should_be_correct_part_three() {
        NewVehicleConfirmationPage confirmationPage = new NewVehicleConfirmationPage();
        waitUntilVisible(confirmationPage.generalInformationTag);
        String actualFirstContractDate = confirmationPage.getVehicleInfo("First Contract Date");
        String actualCatalogValue = confirmationPage.getVehicleInfo("Catalog Value");
        String actualSeatsNumber = confirmationPage.getVehicleInfo("Seats Number");
        String actualDoorsNumber = confirmationPage.getVehicleInfo("Doors Number");
        String expectedCatalogValue = globalData.getCatalogValue();
        expectedCatalogValue = "$" + expectedCatalogValue.substring(0,2) + "," + expectedCatalogValue.substring(2) + ".00";

        assertEquals(globalData.getFirstContractDate(), actualFirstContractDate);
        assertEquals(expectedCatalogValue, actualCatalogValue);
        assertEquals(globalData.getSeatsNumber(), actualSeatsNumber);
        assertEquals(globalData.getDoorsNumber(), actualDoorsNumber);

        confirmationPage.deleteCar();
    }

    @Then("the new vehicle information shown on confirmation page should be correct part four")
    public void the_new_vehicle_information_shown_on_confirmation_page_should_be_correct_part_four() {
        NewVehicleConfirmationPage confirmationPage = new NewVehicleConfirmationPage();
        waitUntilVisible(confirmationPage.generalInformationTag);
        String actualColor = confirmationPage.getVehicleInfo("Color");
        String actualTransmission = confirmationPage.getVehicleInfo("Transmission");
        String actualFuelType = confirmationPage.getVehicleInfo("Fuel Type");
        String actualCO2Emissions = confirmationPage.getVehicleInfo("CO2 Emissions");

        assertEquals(globalData.getColor(), actualColor);
        assertEquals(globalData.getTransmission(), actualTransmission);
        assertEquals(globalData.getFuelType(), actualFuelType);
        assertEquals(globalData.getC02Emissions(), actualCO2Emissions);

        confirmationPage.deleteCar();
    }

    @Then("the new vehicle information shown on confirmation page should be correct part five")
    public void the_new_vehicle_information_shown_on_confirmation_page_should_be_correct_part_five() {
        NewVehicleConfirmationPage confirmationPage = new NewVehicleConfirmationPage();
        waitUntilVisible(confirmationPage.generalInformationTag);
        String actualHorsepower = confirmationPage.getVehicleInfo("Horsepower");
        String actualHorsepowerTaxation = confirmationPage.getVehicleInfo("Horsepower Taxation");
        String actualPower = confirmationPage.getVehicleInfo("Power");

        assertEquals(globalData.getHorsepower(), actualHorsepower);
        assertEquals(globalData.getHorsepowerTaxation(), actualHorsepowerTaxation);
        assertEquals(globalData.getPower(), actualPower);

        confirmationPage.deleteCar();
    }

    @When("the user creates a vehicle with {string} {string}")
    public void the_user_creates_a_vehicle_with(String infoType, String invalidInfo) {
        new VehiclesPage().createCar();
        CreateVehiclePage createVehiclePage = new CreateVehiclePage();
        createVehiclePage.enterInfo(infoType,invalidInfo);
        createVehiclePage.submit();
    }

    @Then("the user should not be able to create a vehicle")
    public void the_user_should_not_be_able_to_create_a_vehicle() {
        CreateVehiclePage createVehiclePage = new CreateVehiclePage();
        assertTrue(createVehiclePage.errorMessage.isDisplayed());
    }

    @When("the user tries create a vehicle with partial information")
    public void the_user_tries_create_a_vehicle_with_partial_information() {
        new VehiclesPage().createCar();
        new CreateVehiclePage().fillPartialNewVehicleInfo();
    }

    @When("the user cancel the process")
    public void the_user_cancel_the_process() {
        new CreateVehiclePage().cancel();
    }

    @Then("the user should be on vehicles page")
    public void the_user_should_be_on_vehicles_page() {
        String expectedTitle = "All - Car - Entities - System - Car - Entities - System";
        waitUntilTitleIs(expectedTitle);
        String actualTitle = getCurrentPageTitle();
        assertEquals(expectedTitle,actualTitle);
    }

}
