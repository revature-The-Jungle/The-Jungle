package E2E.steps;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;
import E2E.runner.TestRunner;

public class JoinGroupSteps {
    // Join Group
    @When("the user clicks on the group they want to join")
    public void the_user_clicks_on_the_group_they_want_to_join() {
        TestRunner.userHomePage.groupToJoin.click();
    }

    @When("the user is redirected to the group page")
    public void the_user_is_redirected_to_the_group_page() {
        String title = TestRunner.driver.getTitle();
        Assert.assertEquals(title, "Group Page");
    }

    @When("the user is on the group page")
    public void the_user_is_on_the_group_page() {
        TestRunner.driver.get("http://127.0.0.1:500/individual-group-page.html");
    }

    @When("the user clicks on the join group button")
    public void the_user_clicks_on_the_join_group_button() {
        TestRunner.groupPage.submitJoinGroup.click();
    }

    @Then("a joined group success message will appear")
    public void a_joined_group_success_message_will_appear() {
        Assert.assertEquals(TestRunner.groupPage.groupJoined.getText(), "Group joined.");
    }
}
