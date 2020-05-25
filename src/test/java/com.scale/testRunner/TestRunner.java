package com.scale.testRunner;

import com.scale.context.*;
import org.json.simple.parser.ParseException;
import org.junit.AfterClass;
import org.junit.runner.RunWith;
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

import java.io.IOException;

@RunWith(Cucumber.class)
@CucumberOptions(
        strict = false,
        features = {"src/test/resources/FeatureFiles"},
        glue = {"com.scale.steps", "com.scale.context"},
//        tags = {"@Test"},
        monochrome = true,
        plugin = {"pretty", "html:target/cucumber-html-reports", "json:target/cucumber-html-reports/cucumber.json"}
)

// dryRun = false


public class TestRunner {
}
 
