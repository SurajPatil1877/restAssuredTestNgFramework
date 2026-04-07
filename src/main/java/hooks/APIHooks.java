package hooks;

import io.cucumber.java.*;

public class APIHooks {
    @Before
    public void beforeAnyAPITest(Scenario scenario) {
        System.out.println("Current Scenario: ".concat(scenario.getName()));
        System.out.println();
    }

    @Before("@SimpleGetAPI")
    public void beforeGetAPI(Scenario scenario) {
        System.out.println("Before GET API: ".concat(scenario.getName()));
        System.out.println();
    }

    @BeforeStep
    public void beforeEachStepForAnyAPITest(Scenario scenario) {
        System.out.println("Current Scenario: ".concat(scenario.getName()));
    }

    @After
    public void afterAnyAPITest(Scenario scenario) {
        System.out.println("Current Scenario: ".concat(scenario.getName()));
    }

    @AfterStep
    public void afterEachStepForAnyAPITest(Scenario scenario) {
        System.out.println("Current Scenario: ".concat(scenario.getName()));
    }
}
