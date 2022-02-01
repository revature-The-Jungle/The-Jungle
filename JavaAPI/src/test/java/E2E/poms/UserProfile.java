package E2E.poms;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class UserProfile {

    private WebDriver driver;

    public UserProfile(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "updateProfileEditProfileBtn")
    public WebElement updateProfileEditButton;
    @FindBy(id="userAboutMeInput")
    public WebElement updateProfileAboutMeInput;
    @FindBy(id="userBirthdateInput")
    public WebElement updateProfileUserBirthDateInput;
    @FindBy(id="updateProfileModalBtn")
    public WebElement saveChangesModalButton;
    @FindBy(id="modalProfileSuccessMessage")
    public WebElement profileSuccessMessage;
}
