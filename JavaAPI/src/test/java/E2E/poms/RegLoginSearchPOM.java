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

    //  ------------------- LOGIN --------------------------------

    //  ------------------- LOGOUT --------------------------------

    //  ------------------- SEARCH --------------------------------

    @FindBy(id="")
    public WebElement login;

    @FindBy(id="")
    public WebElement profilePage;

//    @FindBy(id="")
//    public WebElement profilePage;


}
