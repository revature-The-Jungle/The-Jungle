package E2E.steps;

import E2E.runner.TestRunner;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.io.File;

public class EditProfileSteps {

    @Given("the user is on the home page for temporary profile")
    public void the_user_is_on_the_home_page_for_temporary_profile() {
        TestRunner.driver.get("C:\\Users\\chris\\project3_repo\\The-Jungle\\FrontEnd\\profilepage\\profile-page.html");
        TestRunner.explicitWait.until(ExpectedConditions.titleIs("Home"));
    }

    @When("the user clicks on the edit profile button")
    public void the_user_clicks_on_the_edit_profile_button() {
        TestRunner.userProfile.updateProfileEditButton.click();

    }

    @When("the user modifies their about me section")
    public void the_user_modifies_their_about_me_section() {
        TestRunner.userProfile.updateProfileAboutMeInput.sendKeys("E2E Test for about me");
    }

    @When("the user clicks on the save changes button")
    public void the_user_clicks_on_the_save_changes_button() {
        TestRunner.userProfile.saveChangesModalButton.click();
    }

    @Then("there is a success message saying that the changes have been saved")
    public void there_is_a_success_message_saying_that_the_changes_have_been_saved() {
        TestRunner.explicitWait.until(ExpectedConditions.visibilityOf(TestRunner.userProfile.profileSuccessMessage));
    }

    @When("the user selects a date")
    public void the_user_selects_a_date() {
        TestRunner.userProfile.updateProfileUserBirthDateInput.sendKeys("0101", Keys.RIGHT, "2022");
    }
}
