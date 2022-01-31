package E2E.poms;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RegLoginSearchPOM {

    private WebDriver webDriver;

    public RegLoginSearchPOM(WebDriver webDriver){
        this.webDriver = webDriver;

        PageFactory.initElements(webDriver, this);
    }

    @FindBy(id="signup-firstname")
    public WebElement signUpFirstName;

    @FindBy(id="signup-lastname")
    public WebElement signUpLastName;

    @FindBy(id="signup-email")
    public WebElement signUpEmail;

    @FindBy(id="signup-bdate")
    public WebElement signUpBirthdate;

    @FindBy(id="signup-username")
    public WebElement signUpUsername;

}
