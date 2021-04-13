package com.fleetapp.step_definitions;

import com.fleetapp.pages.CreateVehicleContractPage;
import com.fleetapp.pages.DashBoardPage;
import com.fleetapp.pages.VehicleContractConfirmationPage;
import com.fleetapp.pages.VehicleContractPage;
import com.fleetapp.utilities.DBUtils;
import com.fleetapp.utilities.GlobalDataUtils;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.Map;

import static com.fleetapp.utilities.BrowserUtils.*;
import static com.fleetapp.utilities.DataUtils.*;
import static org.junit.Assert.*;

public class VehicleContractStepDefs {

    private GlobalDataUtils globalDataUtils;

    @When("the user creates a vehicle contract")
    public void the_user_creates_a_vehicle_contract() {
        new DashBoardPage().navigateToModule("Fleet","Vehicle Contracts");
        new VehicleContractPage().createVehicleContract();
        CreateVehicleContractPage createVehicleContractPage = new CreateVehicleContractPage();
        createVehicleContractPage.createNewContract();
        globalDataUtils = createVehicleContractPage.globalDataUtils;
    }

    @Then("the new vehicle contract info should match expected on confirmation page")
    public void the_new_vehicle_contract_info_should_match_expected_on_confirmation_page() {
        VehicleContractConfirmationPage confirmationPage = new VehicleContractConfirmationPage();
        waitUntilTitleContains(globalDataUtils.getResponsible());
        String actualResponsible = confirmationPage.getVehicleInfo("Responsible");
        String actualOdometer = confirmationPage.getVehicleInfo("Odometer Details");
        String actualStartDate = confirmationPage.getVehicleInfo("Contract Start Date");
        String actualDriver = confirmationPage.getVehicleInfo("Driver");

        assertEquals(actualResponsible,globalDataUtils.getResponsible());
        assertEquals(actualOdometer,globalDataUtils.getOdometerDetails());
        assertEquals(actualStartDate,globalDataUtils.getContractStartDate());
        assertEquals(actualDriver,globalDataUtils.getDriver());

        confirmationPage.deleteContract();
    }

    @When("the user retrieves contract info from database")
    public void the_user_retrieves_contract_info_from_database() {
        VehicleContractConfirmationPage confirmationPage = new VehicleContractConfirmationPage();
        String headerText = globalDataUtils.getResponsible() + " " + globalDataUtils.getDriver();
        waitUntilTextToBe(confirmationPage.responsibleHeader,headerText);
        String query = "select * from TableName where HeaderName = '"
                + globalDataUtils.getResponsible() + "'";
        Map<String, Object> rowMap = DBUtils.getRowMap(query);
        globalDataUtils.setRowMap(rowMap);
    }

    @Then("the new vehicle contract info on UI should match database")
    public void the_new_vehicle_contract_info_on_ui_should_match_database() {
        VehicleContractConfirmationPage confirmationPage = new VehicleContractConfirmationPage();
        String actualResponsible = confirmationPage.getVehicleInfo("Responsible");
        String odometer = confirmationPage.getVehicleInfo("Odometer Details");
        double actualOdometer = Double.parseDouble(odometer);
        String actualStartDate = confirmationPage.getVehicleInfo("Contract Start Date");
        String formattedDate = changeDateFormat(actualStartDate,"MMM d, yyyy","yyyy-MM-dd");
        String actualDriver = confirmationPage.getVehicleInfo("Driver");
        Map<String,Object> expectedInfo = globalDataUtils.getRowMap();

        assertEquals(expectedInfo.get("Responsible"),actualResponsible);
        assertEquals(expectedInfo.get("OdometerDetails"),actualOdometer);
        assertEquals(expectedInfo.get("ContractStartDate"),formattedDate);
        assertEquals(expectedInfo.get("Driver"),actualDriver);

        confirmationPage.deleteContract();
    }

    @Then("the total number of contracts on UI match database")
    public void the_total_number_of_contracts_on_ui_match_database() {
        waitUntilTitleContains("All - Vehicle Contract");
        int actualTotal = new VehicleContractPage().getTotalContractCount();

        String query = "select * from TableName";
        DBUtils.runQuery(query);
        int expectedTotal = DBUtils.getRowCount();

        assertEquals(expectedTotal,actualTotal);
    }

    @When("the user change view per page to {string}")
    public void the_user_change_view_per_page_to(String dataSize) {
        waitUntilTitleContains("All - Vehicle Contract");
        VehicleContractPage vehicleContractPage = new VehicleContractPage();
        vehicleContractPage.selectViewPerPage(dataSize);
        waitUntilTextToBe(vehicleContractPage.viewPerPageDropdown, dataSize);
    }

    @Then("the number of contracts displayed should be {string}")
    public void the_number_of_contracts_displayed_should_be(String expectedDataSize) {
        String actualDataSize = new VehicleContractPage().getNumberOfContractsDisplayed();

        assertEquals(expectedDataSize,actualDataSize);
    }

}
