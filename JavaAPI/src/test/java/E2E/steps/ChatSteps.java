package E2E.steps;

import E2E.runner.TestRunner;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

public class ChatSteps {

    private LoginSteps loginSteps = new LoginSteps();

    private int listCountBefore;
    private int listCountAfter;

    private int listCountBeforeImg;
    private int listCountAfterImg;

    @Given("the user is on their dashboard page")
    public void theUserIsOnTheirDashboardPage() {
        TestRunner.driver.get("http://127.0.0.1:5500/FrontEnd/loginpage/login.html");
        loginSteps.the_user_enters_correct_username();
        loginSteps.the_user_enters_correct_password();
        loginSteps.the_user_clicks_on_log_in_button_again();
    }

    @When("the user clicks the chat room button")
    public void theUserClicksTheChatRoomButton() {
        TestRunner.driver.findElement(By.xpath("/html/body/div/div/div[5]/div[1]/a/span")).click();
    }

    @Then("the users should be redirected to the global chat room page")
    public void theUsersShouldBeRedirectedToTheGlobalChatRoomPage() {
        TestRunner.explicitWait.until(ExpectedConditions.elementToBeClickable(TestRunner.chatPage.sendButton));
        String title = TestRunner.driver.getTitle();
        Assert.assertEquals(title, "ChatRoom");
    }

    @Given("the user is in the chatroom")
    public void theUserIsInTheChatroom() {
        theUserIsOnTheirDashboardPage();
        theUserClicksTheChatRoomButton();
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

//    @When("the user clicks on the chat text box")
//    public void theUserClicksOnTheChatTextBox() {
//        TestRunner.explicitWait.until(ExpectedConditions.elementToBeClickable(TestRunner.chatPage.chatBox));
//        TestRunner.chatPage.chatBox.click();
//    }

    @When("the user enters their chat message")
    public void theUserEntersTheirChatMessage() {
        listCountBefore = TestRunner.driver.findElements(By.xpath("//*[@id=\"chat\"]/div")).size();
        TestRunner.chatPage.chatBox.sendKeys("Selenium Testing");
    }

    @When("the user clicks the send chat message button")
    public void theUserClicksTheSendChatMessageButton() {

        TestRunner.chatPage.sendButton.click();
    }

    @Then("the message will appear in chat")
    public void theMessageWillAppearInChat() {
        // id = "chat"
        listCountAfter = TestRunner.driver.findElements(By.xpath("//*[@id=\"chat\"]/div")).size();
        Assert.assertEquals(listCountAfter, listCountBefore+1);
    }

    @When("the user clicks on the Choose File button in chatroom")
    public void theUserClicksOnTheChooseFileButtonInChatroom() {
        TestRunner.explicitWait.until(ExpectedConditions.elementToBeClickable(TestRunner.chatPage.chooseFileButton));
        listCountBeforeImg = TestRunner.driver.findElements(By.xpath("//*[@id=\"chat\"]/div")).size();
        TestRunner.chatPage.chooseFileButton.click();
    }

    @When("the user selects their chat image they want to send")
    public void theUserSelectsTheirChatImageTheyWantToSend() {
        TestRunner.chatPage.chooseFileButton.sendKeys("C:\\Users\\Jonat\\Desktop\\Swing-analogy-1.jpg");
    }

//    @When("the user clicks on the send chat message button")
//    public void theUserClicksOnTheSendChatMessageButton() {
//
//    }

    @Then("the image appears in the group chat")
    public void theImageAppearsInTheGroupChat() {
        listCountAfterImg = TestRunner.driver.findElements(By.xpath("//*[@id=\"chat\"]/div")).size();
        Assert.assertEquals(listCountAfterImg, listCountBeforeImg+1);
    }

    @When("the user selects their chat image that is too big")
    public void theUserSelectsTheirChatImageThatIsTooBig() {
        TestRunner.chatPage.chooseFileButton.sendKeys("C:\\Users\\Jonat\\Desktop\\bigimg.jpg");
    }

    @Then("the user clicks the alert button saying File is too big to send")
    public void theUserClicksTheAlertButtonSayingFileIsTooBigToSend() {
        String message = TestRunner.driver.switchTo().alert().getText();
        Assert.assertEquals(message, "File is too big to send");
    }

    @When("the user clicks on the refresh button")
    public void theUserClicksOnTheRefreshButton() {
        TestRunner.driver.navigate().refresh();
    }

    @Then("the user will see their previous messages")
    public void theUserWillSeeTheirPreviousMessages() {
        Assert.assertTrue(TestRunner.driver.findElements(By.xpath("//*[@id=\"chat\"]/div")).size() > 1);
    }

    @When("the user clicks on the send chat message button for image")
    public void theUserClicksOnTheSendChatMessageButtonForImage() {
        theUserClicksTheSendChatMessageButton();
    }
}
