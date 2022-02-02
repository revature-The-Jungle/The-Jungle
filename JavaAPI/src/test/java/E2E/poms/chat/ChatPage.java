package E2E.poms.chat;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ChatPage {
    private WebDriver driver;

    public ChatPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    /**
     * @TODO get this element from profile page
     */
    @FindBy(id = "toChat")
    public WebElement globalChat;

    @FindBy(id = "send")
    public WebElement sendButton;

    @FindBy(id = "message")
    public WebElement chatBox;

    @FindBy(id = "imgInput")
    public WebElement chooseFileButton;

    @FindBy(xpath = "/html/body/div/div/div[1]/div[1]/a/img")
    public WebElement logo;
}
