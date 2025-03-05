package com.testRunner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = {"src/test/features"},
        glue = {"com.stepDefinition", "com.utility"},
        plugin = {"html:./src/test/reports/localReports/localCucumber.html", "json:./src/test/reports/localReports/Cucumber2.json"},
        monochrome = true,
        tags = "@AddProduct"
)
public class TestRunner {

}
