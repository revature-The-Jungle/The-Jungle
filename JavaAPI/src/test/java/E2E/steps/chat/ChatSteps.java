package E2E.steps;

import E2E.runner.TestRunner;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
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

    @When("the user enters their chat message")
    public void theUserEntersTheirChatMessage() {
        listCountBefore = TestRunner.driver.findElements(By.xpath("//*[@id=\"chat\"]/div")).size();
        TestRunner.chatPage.chatBox.sendKeys("Selenium Testing");
    }

    @When("the user clicks the send chat message button")
    public void theUserClicksTheSendChatMessageButton() {
        TestRunner.explicitWait.until(ExpectedConditions.elementToBeClickable(TestRunner.chatPage.sendButton));
        TestRunner.chatPage.chatBox.sendKeys("\n");
    }

    @Then("the message will appear in chat")
    public void theMessageWillAppearInChat() {
        WebElement chatMessage = TestRunner.driver.findElement(By.xpath("//*[@id=\"chat\"]/div[" + (listCountBefore + 1) + "]"));
        Actions actions = new Actions(TestRunner.driver);
        actions.moveToElement(chatMessage);

        listCountAfter = TestRunner.driver.findElements(By.xpath("//*[@id=\"chat\"]/div")).size();
        Assert.assertEquals(listCountAfter, listCountBefore + 1);
    }

    @When("the user clicks on the Choose File button in chatroom")
    public void theUserClicksOnTheChooseFileButtonInChatroom() {
        TestRunner.explicitWait.until(ExpectedConditions.elementToBeClickable(TestRunner.chatPage.chooseFileButton));
        listCountBeforeImg = TestRunner.driver.findElements(By.xpath("//*[@id=\"chat\"]/div")).size();
    }

    /**
     * Change the path to your img
     */
    @When("the user selects their chat image they want to send")
    public void theUserSelectsTheirChatImageTheyWantToSend() {
        TestRunner.chatPage.chooseFileButton.sendKeys("C:\\Users\\Jonat\\Desktop\\smallimg.jpg");
    }

    @Then("the image appears in the group chat")
    public void theImageAppearsInTheGroupChat() {
        WebElement chatMessage = TestRunner.driver.findElement(By.xpath("//*[@id=\"chat\"]/div[" + (listCountBeforeImg) + "]"));
        Actions actions = new Actions(TestRunner.driver);
        actions.moveToElement(chatMessage);
        TestRunner.explicitWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"chat\"]/div[" + (listCountBeforeImg) + "]")));
        listCountAfterImg = TestRunner.driver.findElements(By.xpath("//*[@id=\"chat\"]/div")).size();
        Assert.assertEquals(listCountAfterImg, listCountBeforeImg);
    }


    /**
     * Change the path to your img
     */
    @When("the user selects their chat image that is too big")
    public void theUserSelectsTheirChatImageThatIsTooBig() {
        TestRunner.chatPage.chooseFileButton.sendKeys("C:\\Users\\Jonat\\Desktop\\bigimg.jpg");
    }

    @Then("the user clicks the alert button saying File is too big to send")
    public void theUserClicksTheAlertButtonSayingFileIsTooBigToSend() {
        Alert result = TestRunner.explicitWait.until(ExpectedConditions.alertIsPresent());
        Assert.assertEquals("File is too big to send", result.getText());
        result.accept();
    }

    @When("the user clicks on the refresh button")
    public void theUserClicksOnTheRefreshButton() {
        TestRunner.driver.navigate().refresh();
    }

    @Then("the user will see their previous messages")
    public void theUserWillSeeTheirPreviousMessages() {
        TestRunner.explicitWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"chat\"]/div")));
        Assert.assertTrue(TestRunner.driver.findElements(By.xpath("//*[@id=\"chat\"]/div")).size() > 0);
    }

}
