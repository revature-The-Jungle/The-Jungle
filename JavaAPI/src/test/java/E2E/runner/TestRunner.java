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

    @BeforeClass
    public static void setup() {
        File file = new File("src/test/resources/chromedriver.exe");
        System.setProperty("webdriver.chrome.driver", file.getAbsolutePath());
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        rlsPom = new RegLoginSearchPOM(driver);

    }


    @AfterClass
    public static void teardown() {
        driver.quit();
    }
}