package E2E.steps.search;

import E2E.runner.TestRunner;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

public class SearchSteps {

    //  SEARCH BY USERNAME

    @Given("the user is on the homepage")
    public void the_user_is_on_the_homepage() {
        TestRunner.driver.get("http://127.0.0.1:5500/FrontEnd/profilepage/profile-page.html");
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
        TestRunner.driver.get("file:///Users/adamjanusewski/Desktop/The-Jungle/FrontEnd/profilepage/profile-page.html");
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
        Thread.sleep(500);
        Assert.assertEquals(TestRunner.rlsPom.getSearchResults.getText(), "No Results");
    }
}
