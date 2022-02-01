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

    @FindBy(id= "groupLink-9000")
    public WebElement groupLink;

    @FindBy(id = "groupLink-32")
    public WebElement myGroupLink;

    @FindBy(xpath = "/html/body/div/div/div[1]/div[3]/div[2]/a/img")
    public WebElement groupLogoLink;
}
