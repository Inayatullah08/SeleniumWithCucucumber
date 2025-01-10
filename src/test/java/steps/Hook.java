package steps;

import Base.BaseUtil;
import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeStep;
import io.cucumber.java.Scenario;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

/**
 * Created by Karthik on 31/01/2019.
 */

public class Hook extends BaseUtil {

    private final BaseUtil base;

    public Hook(BaseUtil base) {
        this.base = base;
    }

    @Before
    public void InitializeTest(Scenario scenario) {
        // Initialize the scenario for reporting
        base.scenarioDef = base.features.createNode(scenario.getName());
        
        // Set up WebDriver
        WebDriverManager.chromedriver().setup();
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--headless", "--disable-gpu", "--window-size=1920,1200");
        base.Driver = new ChromeDriver(chromeOptions);
        
        System.out.println("Test initialized: " + scenario.getName());
    }

    @After
    public void TearDownTest(Scenario scenario) {
        if (scenario.isFailed()) {
            // Add screenshot logic here if needed
            System.out.println("Scenario failed: " + scenario.getName());
        }
        System.out.println("Closing the browser for scenario: " + scenario.getName());
        if (base.Driver != null) {
            base.Driver.quit();
        }
    }

    @BeforeStep
    public void BeforeEveryStep(Scenario scenario) {
        System.out.println("Executing before step in scenario: " + scenario.getName());
    }

    @AfterStep
    public void AfterEveryStep(Scenario scenario) {
        System.out.println("Executed after step in scenario: " + scenario.getName());
        // Add logic for post-step actions if required
    }
}
