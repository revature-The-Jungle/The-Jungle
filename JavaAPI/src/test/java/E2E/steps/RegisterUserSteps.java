package E2E.steps;

import E2E.runner.TestRunner;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;


public class RegisterUserSteps {


    // CORRECT INFORMATION TEST (with small validation correction)
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
        TestRunner.rlsPom.signUpLastName.sendKeys("Last-Name");
    }

    @When("the user enters Date of Birth into the new account form")
    public void the_user_enters_date_of_birth_into_the_new_account_form() {
        TestRunner.rlsPom.signUpBirthdate.sendKeys("10101990");
    }

    @When("the user enters email into the new account form")
    public void the_user_enters_email_into_the_new_account_form() {
        TestRunner.rlsPom.signUpEmail.sendKeys("E2E@test.com");
    }

    @When("the user enters an username with a space into the new account form")
    public void the_user_enters_an_username_with_a_space_into_the_new_account_form() {
        TestRunner.rlsPom.signUpUsername.sendKeys("Bad username");
    }


    @When("the user enters a password into the new account form")
    public void the_user_enters_a_password_into_the_new_account_form() {
        TestRunner.rlsPom.signUpPassword.sendKeys("e2epassword");
    }


    @When("an error message populates")
    public void an_error_message_populates() {
        TestRunner.explicitWait.until(ExpectedConditions.textToBePresentInElement(TestRunner.rlsPom.usernameErrorMessage,
                "Cannot contain spaces or `^*()+=[]{}\"<>~|;:"));
        String message = TestRunner.rlsPom.usernameErrorMessage.getText();
        Assert.assertEquals(message, "Cannot contain spaces or `^*()+=[]{}\"<>~|;:");
    }


    @When("the user replaces a correct username into the new account form")
    public void the_user_replaces_a_correct_username_into_the_new_account_form() {
        TestRunner.rlsPom.signUpUsername.clear();
        TestRunner.rlsPom.signUpUsername.sendKeys("goodusername");
        TestRunner.rlsPom.signUpPassword.click();
    }



    @When("the user clicks on the submit button in the new account form")
    public void the_user_clicks_on_the_submit_button_in_the_new_account_form() {
        TestRunner.explicitWait.until(ExpectedConditions.elementSelectionStateToBe(TestRunner.rlsPom.signUpSubmitButton,
                false));
        TestRunner.rlsPom.signUpSubmitButton.click();
    }

    @Then("the user will be redirected to their profile page")
    public void the_user_will_be_redirected_to_their_profile_page() {
        TestRunner.explicitWait.until(ExpectedConditions.titleIs("Home"));
        String title = TestRunner.driver.getTitle();
        Assert.assertEquals(title, "Home");
    }



    // DUPLICATE EMAIL BAD TEST
    @Given("the user is now on the sign up page with form")
    public void the_user_is_now_on_the_sign_up_page_with_form() {
        TestRunner.explicitWait.until(ExpectedConditions.titleIs("Home"));
        TestRunner.driver.get("file:///C:/Users/kckar/Desktop/The-Jungle/FrontEnd/registrationpage/sign-up.html");
    }

    @When("the user enters bad test First name into the new account form")
    public void the_user_enters_bad_test_first_name_into_the_new_account_form() {
        TestRunner.rlsPom.signUpFirstName.sendKeys("E2ETestBad");
    }

    @When("the user enters bad test Last name into the new account form")
    public void the_user_enters_bad_test_last_name_into_the_new_account_form() {
        TestRunner.rlsPom.signUpLastName.sendKeys("E2ETestBad");
    }

    @When("the user enters bad test Date of Birth into the new account form")
    public void the_user_enters_bad_test_date_of_birth_into_the_new_account_form() {
        TestRunner.rlsPom.signUpBirthdate.sendKeys("10101990");
    }

    @When("the user enters a duplicate email into the new account form")
    public void the_user_enters_a_duplicate_email_into_the_new_account_form() {
        TestRunner.rlsPom.signUpEmail.sendKeys("E2E@test.com");
    }

    @When("the user enters bad test username into the new account form")
    public void the_user_enters_bad_test_username_into_the_new_account_form() {
        TestRunner.rlsPom.signUpUsername.sendKeys("E2ETestavawovainaon");
    }

    @When("the user enters bad test password into the new account form")
    public void the_user_enters_bad_test_password_into_the_new_account_form() {
        TestRunner.rlsPom.signUpPassword.sendKeys("Test");
        TestRunner.rlsPom.signUpUsername.click();
    }

    @When("the user clicks on the bad test submit button in the new account form")
    public void the_user_clicks_on_the_bad_test_submit_button_in_the_new_account_form() {
        TestRunner.explicitWait.until(ExpectedConditions.elementSelectionStateToBe
                (TestRunner.rlsPom.signUpSubmitButton, false));
        TestRunner.rlsPom.signUpSubmitButton.click();
    }

    @Then("a message for duplicate email error populates")
    public void a_message_for_duplicate_email_error_populates() {
        TestRunner.explicitWait.until(ExpectedConditions.textToBePresentInElement
                (TestRunner.rlsPom.duplicateEmailMessage, "Email is already in use"));
        String message = TestRunner.rlsPom.duplicateEmailMessage.getText();
        Assert.assertEquals(message, "Email is already in use");
    }




    // BLANK INPUTS ERROR REGISTER TEST

}
