package E2E.runner;

import E2E.poms.RegLoginSearchPOM;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.io.File;

@RunWith(Cucumber.class)
@CucumberOptions(features="classpath:features", glue="E2E.steps", plugin = {"pretty", "html:src/test/java/resources/reports/html-reports.html"})
public class TestRunner {

    public static WebDriver driver;
    public static WebDriverWait explicitWait;
    public static RegLoginSearchPOM rlsPom;

    //    public static WebDriver webDriver;
//    public static POM pom;
//    public static userSignUpLoginPOM signUpPOM;
//    public static WebDriverWait explicitWait;

    @BeforeClass
    public static void setup() {
        File file = new File("src/test/resources/chromedriver.exe");
        System.setProperty("webdriver.chrome.driver", file.getAbsolutePath());
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        rlsPom = new RegLoginSearchPOM(driver);

    }


//    @BeforeClass
//    public static void setup(){
//        File file = new File("src/main/resources/msedgedriver.exe");
//        System.setProperty("webdriver.edge.driver", file.getAbsolutePath());
//        webDriver = new EdgeDriver();
//        pom = new POM(webDriver);
//        signUpPOM = new userSignUpLoginPOM(webDriver);
//        System.out.println("setup complete!");
//        //SETUP IMPLICIT WAIT
//        webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
//        explicitWait = new WebDriverWait(webDriver,Duration.ofSeconds(10));
//    }
//
//    @AfterClass
//    public static void teardown(){
//        //end the driver
//        webDriver.quit();
//        System.out.println("teardown complete!");
//    }


    @AfterClass
    public static void teardown() {
        driver.quit();
    }
}