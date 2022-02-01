package E2E.poms;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class GroupPage {
    private WebDriver driver;

    public GroupPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "submitJoinGroup")
    public WebElement submitJoinGroup;

    @FindBy(id = "groupJoined")
    public WebElement groupJoined;

    @FindBy(id = "postInput")
    public WebElement getGroupPostInput;

    @FindBy(id = "sendGroupPostButton")
    public WebElement getSendGroupPostButton;

    @FindBy(id = "postInfo")
    public WebElement getPostInfoNotification;

    @FindBy(id = "deletePost9005")
    public WebElement getDeleteGroupPostButton;
}
