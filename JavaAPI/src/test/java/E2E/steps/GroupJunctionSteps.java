package E2E.steps;

import E2E.runner.TestRunner;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class GroupJunctionSteps {

    @Then("the user should see the list of users in the group")
    public void theUserShouldSeeTheListOfUsersInTheGroup() {
       Assert.assertTrue(TestRunner.groupJunctionPOM.userInGroupList.isDisplayed());
    }

    @Then("user can see who created the group")
    public void userCanSeeWhoCreatedTheGroup() {
        Assert.assertEquals(TestRunner.groupJunctionPOM.creator.getText(),"test create post,test");
    }

    @When("the user clicks the leave button")
    public void theUserClicksTheLeaveButton() {
        TestRunner.groupJunctionPOM.leaveButton.click();
    }

    @Then("the user will be redirected to the group homepage")
    public void theUserWillBeRedirectedToTheGroupHomepage() {
        TestRunner.explicitWait.until(ExpectedConditions.titleIs("Group Page"));
        String title = TestRunner.driver.getTitle();
        Assert.assertEquals(title,"Group Page");
    }

    @Then("the leave group button should not be visible")
    public void theLeaveGroupButtonShouldNotBeVisible() {
    }

    @Given("User is on the group page")
    public void userIsOnTheGroupPage() {
        TestRunner.driver.get("http://127.0.0.1:5500/FrontEnd/grouppage/group-page.html");
    }

    @When("the user selects the group")
    public void theUserSelectsTheGroup() {
        TestRunner.driver.findElement(By.cssSelector("#groupLink-7")).click();

    }

    @When("User is redirected to the group individual page")
    public void userIsRedirectedToTheGroupIndividualPage() {
        TestRunner.explicitWait.until(ExpectedConditions.titleIs("Individual Group Page"));
    }
}
