package E2E.steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class SearchSteps {

    //  SEARCH BY USERNAME

    @Given("the user is on the homepage")
    public void the_user_is_on_the_homepage() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }
    @When("user inputs username into the search input field")
    public void user_inputs_username_into_the_search_input_field() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }
    @When("the user clicks the search button or icon")
    public void the_user_clicks_the_search_button_icon() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }
    @Then("results will be displayed")
    public void results_will_be_displayed() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    // SYSTEM SEARCH FOR NO RESULTS

    @Given("the user is on homepage")
    public void the_user_is_on_homepage() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }
    @When("user inputs a username that doesn't exist into the search input field")
    public void user_inputs_a_username_that_doesn_t_exist_into_the_search_input_field() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

//    @When("the user clicks the search button or icon")
//    public void the_user_clicks_the_search_button_icon() {
//        // Write code here that turns the phrase above into concrete actions
//        throw new io.cucumber.java.PendingException();
//    }

    @Then("{string} or {string} will be displayed")
    public void or_will_be_displayed(String string, String string2) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }
}
