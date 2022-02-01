package E2E.steps;

import E2E.runner.TestRunner;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

public class ChatSteps {

    private int count;

    @Given("the user is on their dashboard page")
    public void theUserIsOnTheirDashboardPage() {
        TestRunner.driver.get("http://127.0.0.1:5500/FrontEnd/loginpage/login.html");
        /**
         * @TODO get steps from Login
         */
    }

    @When("the user clicks the chat room button")
    public void theUserClicksTheChatRoomButton() {
        /**
         * @TODO fix ChatPom and use the Chat Button in profile page
         */
    }

    @Then("the users should be redirected to the global chat room page")
    public void theUsersShouldBeRedirectedToTheGlobalChatRoomPage() {
        TestRunner.explicitWait.until(ExpectedConditions.elementToBeClickable(TestRunner.chatPage.sendButton));
        String title = TestRunner.driver.getTitle();
        Assert.assertEquals(title, "ChatRoom");
    }

    @Given("the user is in the chatroom")
    public void theUserIsInTheChatroom() {
    }

    @When("the user clicks on the logo")
    public void theUserClicksOnTheLogo() {
        TestRunner.explicitWait.until(ExpectedConditions.elementToBeClickable(TestRunner.chatPage.logo));
        TestRunner.chatPage.logo.click();
    }

    @Then("they are redirected to their dashboard")
    public void theyAreRedirectedToTheirDashboard() {
        /**
         * @TODO get profile page to use explicit wait for logo to be clickable
         */
        String title = TestRunner.driver.getTitle();
        Assert.assertEquals(title, "Home");
    }

    @When("the user clicks on the chat text box")
    public void theUserClicksOnTheChatTextBox() {
        TestRunner.explicitWait.until(ExpectedConditions.elementToBeClickable(TestRunner.chatPage.chatBox));
        TestRunner.chatPage.chatBox.click();
    }

    @When("the user enters their chat message")
    public void theUserEntersTheirChatMessage() {
        count = TestRunner.driver.findElements(By.xpath("//*[@id=\"chat\"]/div")).size();
        TestRunner.chatPage.chatBox.sendKeys("Selenium Testing");
    }

    @When("the user clicks the send chat message button")
    public void theUserClicksTheSendChatMessageButton() {
        TestRunner.chatPage.sendButton.click();
    }

    @Then("the message will appear in chat")
    public void theMessageWillAppearInChat() {
        // id = "chat"
        count = TestRunner.driver.findElements(By.xpath("//*[@id=\"chat\"]/div")).size();
        Assert.assertEquals(count, count-1);
    }

    @When("the user clicks on the Choose File button in chatroom")
    public void theUserClicksOnTheChooseFileButtonInChatroom() {

    }

    @When("the user selects their chat image they want to send")
    public void theUserSelectsTheirChatImageTheyWantToSend() {

    }

    @When("the user clicks on the send chat message button")
    public void theUserClicksOnTheSendChatMessageButton() {

    }

    @Then("the image appears in the group chat")
    public void theImageAppearsInTheGroupChat() {

    }

    @When("the user selects their chat image that is too big")
    public void theUserSelectsTheirChatImageThatIsTooBig() {

    }

    @Then("the user clicks the alert button saying File is too big to send")
    public void theUserClicksTheAlertButtonSayingFileIsTooBigToSend() {

    }

    @When("the user clicks on the refresh button")
    public void theUserClicksOnTheRefreshButton() {

    }

    @Then("the user will see their previous messages")
    public void theUserWillSeeTheirPreviousMessages() {

    }
}
