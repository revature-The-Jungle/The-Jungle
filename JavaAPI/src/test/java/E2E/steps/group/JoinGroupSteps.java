package E2E.steps.group;

import E2E.steps.chat.ChatSteps;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import E2E.runner.TestRunner;
import org.testng.annotations.Test;

public class JoinGroupSteps {

    CreateGroupSteps cgs = new CreateGroupSteps();

    // Join Group
    @When("the user clicks on the group they want to join")
    public void the_user_clicks_on_the_group_they_want_to_join() {
        TestRunner.userHomePage.groupToJoin.click();
    }

    @When("the user is redirected to the group page")
    public void the_user_is_redirected_to_the_group_page() {
        String title = TestRunner.driver.getTitle();
        Assert.assertEquals(title, "Individual Group Page");
    }

    @When("the user is on the individual group page")
    public void the_user_is_on_the_individual_group_page() {
        cgs.the_user_is_on_the_group_page();
        TestRunner.explicitWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"groups-div\"]/div[1]/div")));
        TestRunner.driver.findElement(By.xpath("//*[@id=\"groups-div\"]/div[1]/div")).click();
//        TestRunner.driver.get("File:///Users/dlanderos/Desktop/Project 3/The-Jungle/FrontEnd/grouppage/individualgrouppage/individual-group-page.html");
    }

    @When("the user clicks on the join group button")
    public void the_user_clicks_on_the_join_group_button() {
        TestRunner.groupPage.submitJoinGroup.click();
    }

    @Then("a joined group success message will appear")
    public void a_joined_group_success_message_will_appear() {
        TestRunner.explicitWait.until(ExpectedConditions.textToBePresentInElement(TestRunner.groupPage.groupJoined, "Group joined."));
        Assert.assertEquals(TestRunner.groupPage.groupJoined.getText(), "Group joined.");
    }
}