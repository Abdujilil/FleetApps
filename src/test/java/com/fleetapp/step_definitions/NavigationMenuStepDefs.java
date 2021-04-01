package com.fleetapp.step_definitions;

import com.fleetapp.pages.DashBoardPage;
import com.fleetapp.pages.LoginPage;
import com.fleetapp.utilities.ConfigurationReader;
import com.fleetapp.utilities.Driver;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.List;

import static com.fleetapp.utilities.BrowserUtils.*;
import static org.junit.Assert.*;

public class NavigationMenuStepDefs {

    @Given("Logged in as store manager")
    public void logged_in_as_store_manager() {
        new LoginPage().loginAsStoreManager();
    }

    @Given("the user logs in as {string}")
    public void the_user_logs_in_as(String userType) {
        Driver.get().get(ConfigurationReader.get("url"));
        LoginPage loginPage = new LoginPage();
        if (userType.equalsIgnoreCase("driver")) {
            loginPage.loginAsDriver();
        } else if (userType.equalsIgnoreCase("sales manager")) {
            loginPage.loginAsSalesManager();
        } else if (userType.equalsIgnoreCase("store manager")) {
            loginPage.loginAsStoreManager();
        }
    }

    @When("the user hovers over {string} module")
    public void the_user_hovers_over_module(String module) {
        new DashBoardPage().hoverOverModule(module);
    }

    @Then("the user should see following options")
    public void the_user_should_see_following_options(List<String> expectedOptions) {
        DashBoardPage dashBoardPage = new DashBoardPage();
        List<String> actualOptions = getTextOfElements(dashBoardPage.fleetOptions);
        assertEquals(expectedOptions,actualOptions);
    }

    @When("the user navigates to {string} {string}")
    public void the_user_navigates_to(String tab, String module) {
        new DashBoardPage().navigateToModule(tab,module);
    }

    @Then("the title of the page should contain {string}")
    public void the_title_of_the_page_should_contain(String expectedTitle) {
        waitUntilTitleContains(expectedTitle);
        String actualTitle = getCurrentPageTitle();
        assertTrue(actualTitle.contains(expectedTitle));
    }

    @Then("the user should see an error message")
    public void the_user_should_see_an_error_message() {
        DashBoardPage dashBoardPage = new DashBoardPage();
        dashBoardPage.waitTilLoaderDisappears();
        assertTrue(dashBoardPage.errorMessage.isDisplayed());
    }

}
