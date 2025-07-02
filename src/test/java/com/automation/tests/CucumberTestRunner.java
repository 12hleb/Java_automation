package com.automation.tests;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.DataProvider;

/**
 * Cucumber Test Runner for TestNG integration
 * Configures Cucumber options and test execution
 */
@CucumberOptions(
    features = "src/test/resources/features",
    glue = {
        "com.automation.stepdefinitions",
        "com.automation.hooks"
    },
    plugin = {
        "pretty",
        "html:target/cucumber-reports/cucumber-pretty.html",
        "json:target/cucumber-reports/CucumberTestReport.json",
        "junit:target/cucumber-reports/CucumberTestReport.xml"
    },
    monochrome = true,
    dryRun = false,
    publish = false
)
public class CucumberTestRunner extends AbstractTestNGCucumberTests {

    /**
     * Override to enable parallel execution
     * @return DataProvider for parallel test execution
     */
    @Override
    @DataProvider(parallel = true)
    public Object[][] scenarios() {
        return super.scenarios();
    }
}