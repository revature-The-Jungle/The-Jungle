package E2E.steps;

import E2E.runner.TestRunner;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class RegisterUserSteps {


    @Given("the user is on the sign up page")
    public void the_user_is_on_the_sign_up_page() {
        TestRunner.driver.get("file:///C:/Users/kckar/Desktop/The-Jungle/FrontEnd/registrationpage/sign-up.html");
    }

    @When("the user enters First name into the new account form")
    public void the_user_enters_first_name_into_the_new_account_form() {
        TestRunner.rlsPom.signUpFirstName.sendKeys("E2ETest");
    }

    @When("the user enters Last name into the new account form")
    public void the_user_enters_last_name_into_the_new_account_form() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @When("the user enters Date of Birth into the new account form")
    public void the_user_enters_date_of_birth_into_the_new_account_form() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @When("the user enters email into the new account form")
    public void the_user_enters_email_into_the_new_account_form() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @When("the user enters an username with a space into the new account form")
    public void the_user_enters_an_username_with_a_space_into_the_new_account_form() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @When("an error message populates")
    public void an_error_message_populates() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @When("the user enters a correct username into the new account form")
    public void the_user_enters_a_correct_username_into_the_new_account_form() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @When("the user enters a password into the new account form")
    public void the_user_enters_a_password_into_the_new_account_form() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }


    @When("the user clicks on the submit button in the new account form")
    public void the_user_clicks_on_the_submit_button_in_the_new_account_form() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @Then("the user will be redirected to their profile page")
    public void the_user_will_be_redirected_to_their_profile_page() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }


}
