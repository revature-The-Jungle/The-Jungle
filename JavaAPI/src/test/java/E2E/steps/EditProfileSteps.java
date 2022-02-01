package E2E.steps;

import E2E.runner.TestRunner;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.Test;

public class EditProfileSteps {

    @When("the user clicks on the edit profile button")
    public void the_user_clicks_on_the_edit_profile_button() {
        TestRunner.editProfile.updateProfileEditButton.click();

    }

    @When("the user modifies their about me section")
    public void the_user_modifies_their_about_me_section() {
        TestRunner.editProfile.updateProfileAboutMeInput.sendKeys("E2E Test for about me");
    }

    @When("the user clicks on the save changes button")
    public void the_user_clicks_on_the_save_changes_button() {
        TestRunner.editProfile.saveChangesModalButton.click();
    }

    @Then("there is a success message saying that the changes have been saved")
    public void there_is_a_success_message_saying_that_the_changes_have_been_saved() {
        TestRunner.explicitWait.until(ExpectedConditions.visibilityOf(TestRunner.editProfile.profileSuccessMessage);
    }

    @When("the user selects a date")
    public void the_user_selects_a_date() {
        TestRunner.editProfile.updateProfileUserBirthDateInput.sendKeys("0101", Keys.RIGHT, "2022");
    }
}
