package E2E.steps;

import E2E.runner.TestRunner;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class LoginSteps {

    @Given("the user is on the log-in page")
    public void the_user_is_on_the_log_in_page() {
        TestRunner.driver.get("http://127.0.0.1:5500/login.html");
    }
    @When("the user enters correct username")
    public void the_user_enters_correct_username() {
//        TestRunner.rlsPom
    }
//    .sendKeys("test");
    @When("the  user enters correct password")
    public void the_user_enters_correct_password() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }
    @When("the  user clicks on log-in buttun")
    public void the_user_clicks_on_log_in_buttun() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }
    @Then("the user will be redirected to the homepage")
    public void the_user_will_be_redirected_to_the_homepage() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    //  LOGOUT

    @Given("user in  on the home-page")
    public void user_in_on_the_home_page() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }
    @When("user clicks on the log-out button")
    public void user_clicks_on_the_log_out_button() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }
    @Then("user will be redirected to the landing page")
    public void user_will_be_redirected_to_the_landing_page() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    // SYSTEM STEPS

    @Given("user is on the log-in page")
    public void user_is_on_the_log_in_page() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }
    @When("the user enters wrong username")
    public void the_user_enters_wrong_username() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }
    @When("the  user enters wrong password")
    public void the_user_enters_wrong_password() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }
    @When("the user clicks on the log-in buttun")
    public void the_user_clicks_on_the_log_in_buttun() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }
    @Then("error message will be displayed")
    public void error_message_will_be_displayed() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

}
