package runner;
//46
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import io.cucumber.testng.FeatureWrapper;
import io.cucumber.testng.PickleWrapper;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

@CucumberOptions(
        features = "src/test/resources/features",
        glue = {"stepDefs"},
        plugin = "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm"
)
public class CucumberTests extends AbstractTestNGCucumberTests {
    @DataProvider(parallel = true)
    public Object[][] parallelRunProvider() {
        return super.scenarios();
    }

    @Test(description = "Runs Cucumber Scenarios in parallel", dataProvider = "parallelRunProvider")
    public void runScenario(PickleWrapper pickleWrapper, FeatureWrapper featureWrapper) {
        super.runScenario(pickleWrapper, featureWrapper);
    }
}
