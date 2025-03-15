package runners;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
    features = "src/test/resources/features",
    glue = {"stepdefenitions","hook"},
    plugin = {
                "pretty",                                   // Pretty console output
                "html:target/cucumber-report.html",          // HTML report
                "json:target/cucumber-report.json",          // JSON report
                "junit:target/cucumber-report.xml"           // JUnit XML report
    }
)
public class TestRunner {
    
}


/*
 * Materi malam ini
 * 1. Hook
 * 2. Run automation pakai testng
 */