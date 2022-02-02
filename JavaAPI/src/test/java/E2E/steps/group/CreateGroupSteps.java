package E2E.steps.group;

import E2E.runner.TestRunner;
import E2E.steps.chat.ChatSteps;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;


public class CreateGroupSteps {

    ChatSteps chatSteps = new ChatSteps();

    // Create Group
    @Given("the user is on the group page")
    public void the_user_is_on_the_group_page() {
        chatSteps.theUserIsOnTheirDashboardPage();
        TestRunner.driver.findElement(By.xpath("/html/body/div/div/div/div[6]/div[2]/a/img")).click();
    }

    @When("the user enters the group name")
    public void the_user_enters_the_group_name() {
        TestRunner.userHomePage.groupName.sendKeys("Killswitch Engage19 Fan Club");
    }

    @When("the user enters the group description")
    public void the_user_enters_the_group_description() {
        TestRunner.userHomePage.groupAbout.sendKeys("This group is for all Killswitch Engage Fans!");
    }

    @When("the user clicks the create button")
    public void the_user_clicks_the_create_button() {
        TestRunner.userHomePage.submitCreateGroup.click();
    }

    @Then("a created group success message will appear")
    public void a_created_group_success_message_will_appear() {
        TestRunner.explicitWait.until(ExpectedConditions.textToBePresentInElement(TestRunner.userHomePage.messageGroupCreated, "Group created successfully!"));
        Assert.assertEquals(TestRunner.userHomePage.messageGroupCreated.getText(), "Group created successfully!");
    }


    // Blank form
    @Then("both must enter group information messages will appear")
    public void both_must_enter_group_information_messages_will_appear() {
        Assert.assertEquals(TestRunner.userHomePage.groupNameNull.getText(), "Please enter a group name");
        Assert.assertEquals(TestRunner.userHomePage.groupAboutNull.getText(), "Please enter a valid description");
    }

    // Blank group name
    @Then("a must enter group name message will appear")
    public void a_must_enter_group_name_message_will_appear() {
        Assert.assertEquals(TestRunner.userHomePage.groupNameNull.getText(), "Please enter a group name");
    }

    // Blank group description
    @Then("a must enter group description message will appear")
    public void a_must_enter_group_description_message_will_appear() {
        Assert.assertEquals(TestRunner.userHomePage.groupAboutNull.getText(), "Please enter a valid description");
    }

    // Group name too short
    @When("the user enters the group name that is too short")
    public void the_user_enters_the_group_name_that_is_too_short() {
        TestRunner.userHomePage.groupName.sendKeys("a");
    }

    @Then("a group name too short message will appear")
    public void a_group_name_too_short_message_will_appear() {
        Assert.assertEquals(TestRunner.userHomePage.groupNameThreeChar.getText(), "Group name must be at least three characters long!");
    }

    // Group name too long
    @When("the user enters a group name that is too long")
    public void the_user_enters_a_group_name_that_is_too_long() {
        TestRunner.userHomePage.groupName.sendKeys("Killswitch Engage Fan Club!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
    }

    @Then("a group name too long message will appear")
    public void a_group_name_too_long_message_will_appear() {
        Assert.assertEquals(TestRunner.userHomePage.groupNameFortyChar.getText(), "Group Name cannot exceed 40 characters!");
    }

    // Group description too long
    @When("the user enters a group description that is too long")
    public void the_user_enters_a_group_description_that_is_too_long() {
        TestRunner.userHomePage.groupAbout.sendKeys("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
    }

    @Then("a group description too long message will appear")
    public void a_group_description_too_long_message_will_appear() {
        Assert.assertEquals(TestRunner.userHomePage.groupAbout500Char.getText(), "Group description cannot exceed 500 characters!");
    }

    // Duplicate group name
    @When("the user enters a group name that is already taken")
    public void the_user_enters_a_group_name_that_is_already_taken() {
        TestRunner.userHomePage.groupName.sendKeys("Metallica Fans");
    }

    @Then("a group name already taken message will appear")
    public void a_group_name_already_taken_message_will_appear() {
        TestRunner.explicitWait.until(ExpectedConditions.textToBePresentInElement(TestRunner.userHomePage.duplicateGroupNameMessage, "The group name you entered is already taken! Please try another group name."));
        Assert.assertEquals(TestRunner.userHomePage.duplicateGroupNameMessage.getText(), "The group name you entered is already taken! Please try another group name.");
}
}