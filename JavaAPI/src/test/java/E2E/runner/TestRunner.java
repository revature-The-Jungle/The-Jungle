package E2E.runner;

import E2E.poms.UserProfile;
import E2E.poms.group.GroupJunctionPOM;
import E2E.poms.loginRegisterSearch.RegLoginSearchPOM;
import E2E.poms.chat.ChatPage;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import E2E.poms.homepage.UserHomePage;
import E2E.poms.group.GroupPage;
import java.io.File;
import java.time.Duration;

@RunWith(Cucumber.class)
@CucumberOptions(features = "classpath:features/group/GroupJunction.feature", glue = "E2E.steps", plugin = {"pretty",
        "html:src/test/java/resources/reports/html-reports.html"})
public class TestRunner {
    public static WebDriver driver;
    public static WebDriverWait explicitWait;

    // POMs
    public static ChatPage chatPage;
    public static RegLoginSearchPOM rlsPom;
    public static UserHomePage userHomePage;
    public static GroupJunctionPOM groupJunctionPOM;
    public static GroupPage groupPage;
    public static UserProfile userProfile;

    @BeforeClass
    public static void setup() {
        File file = new File("src/test/resources/chromedriver.exe");
        System.setProperty("webdriver.chrome.driver", file.getAbsolutePath());
        driver = new ChromeDriver();
        driver.manage().window().maximize();

        // POMs
        chatPage = new ChatPage(driver);
        rlsPom = new RegLoginSearchPOM(driver);
        userProfile = new UserProfile(driver);
        userHomePage = new UserHomePage(driver);
        groupPage = new GroupPage(driver);
        groupJunctionPOM = new GroupJunctionPOM(driver);

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(10));
        System.out.println("Set up complete!");
        driver.manage().window().maximize();

    }

    @AfterClass
    public static void teardown() {
        driver.quit();
        System.out.println("teardown complete!");

    }
}