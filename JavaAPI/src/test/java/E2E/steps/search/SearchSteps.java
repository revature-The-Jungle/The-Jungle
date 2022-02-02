package E2E.steps.search;

import E2E.runner.TestRunner;
import E2E.steps.chat.ChatSteps;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class SearchSteps {

    ChatSteps chatSteps = new ChatSteps();

    //  SEARCH BY USERNAME

    @Given("the user is on the homepage")
    public void the_user_is_on_the_homepage() {
        chatSteps.theUserIsOnTheirDashboardPage();
    }
    @When("user inputs username into the search input field")
    public void user_inputs_username_into_the_search_input_field() throws InterruptedException {
        Thread.sleep(1000);
        TestRunner.rlsPom.usernameSearch.sendKeys("userna");
    }

    @When("the user clicks the search button")
    public void the_user_clicks_the_search_button() throws InterruptedException {
        Thread.sleep(500);
        TestRunner.rlsPom.submitButton.click();
    }
    @Then("results will be displayed")
    public void results_will_be_displayed() throws InterruptedException {
        Thread.sleep(1000);
        Assert.assertNotNull(TestRunner.rlsPom.getSearchResults);
    }

    // SYSTEM SEARCH FOR NO RESULTS

    @Given("the user is on homepage")
    public void the_user_is_on_homepage() {
        chatSteps.theUserIsOnTheirDashboardPage();
    }
    @When("user inputs a username that doesn't exist into the search input field")
    public void user_inputs_a_username_that_doesn_t_exist_into_the_search_input_field() {
        TestRunner.rlsPom.usernameSearch.sendKeys("xxxxxxxxxxxxx");
    }
//    @When("the user clicks the search button")
//    public void the_user_clicks_the_search_button() throws InterruptedException {
//        Thread.sleep(500);
//        TestRunner.rlsPom.submitButton.click();
//    }

    @Then("no results will be displayed")
    public void no_results_will_be_displayed() throws InterruptedException {
        TestRunner.explicitWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"searchList\"]/li")));
        Assert.assertEquals(TestRunner.rlsPom.getSearchResults.getText(), "No Results");
    }
}
