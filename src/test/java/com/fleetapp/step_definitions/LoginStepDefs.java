package com.fleetapp.step_definitions;

import com.fleetapp.pages.LoginPage;
import com.fleetapp.utilities.ConfigurationReader;
import com.fleetapp.utilities.Driver;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static com.fleetapp.utilities.BrowserUtils.*;
import static org.junit.Assert.*;

public class LoginStepDefs {

    @Given("the user is on the login page")
    public void the_user_is_on_the_login_page() {
        String url = ConfigurationReader.get("url");
        Driver.get().get(url);
    }

    @When("the user enters the driver information")
    public void the_user_enters_the_driver_information() {
        LoginPage loginPage = new LoginPage();
        loginPage.loginAsDriver();
    }

    @When("the user enters the sales manager information")
    public void the_user_enters_the_sales_manager_information() {
        LoginPage loginPage = new LoginPage();
        loginPage.loginAsSalesManager();
    }

    @When("the user enters the store manager information")
    public void theUserEntersTheStoreManagerInformation() {
        LoginPage loginPage = new LoginPage();
        loginPage.loginAsStoreManager();
    }

    @When("the user logs in using {string} and {string}")
    public void the_user_logs_in_using_and(String username, String password) {
        LoginPage loginPage = new LoginPage();
        loginPage.login(username,password);
    }

    @Then("the title should contains {string}")
    public void the_title_should_contains(String expectedTitle) {
        waitUntilTitleContains(expectedTitle);
        assertTrue(Driver.get().getTitle().contains(expectedTitle));
    }

    @Then("the user should be able to login")
    public void the_user_should_be_able_to_login() {
        waitUntilTitleIs("Dashboard");
        String actualTitle = Driver.get().getTitle();
        assertEquals("Dashboard",actualTitle);
    }

    @Then("the user should not be able to login")
    public void the_user_should_not_be_able_to_login() {
        LoginPage loginPage = new LoginPage();
        waitUntilVisible(loginPage.errorMessage);
        String actualMessage = loginPage.getErrorMessage();
        String expectedMessage = "Invalid user name or password.";
        assertEquals(expectedMessage,actualMessage);
    }
}
