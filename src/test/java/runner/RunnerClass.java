package runner;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

import static io.cucumber.junit.CucumberOptions.*;

@RunWith(Cucumber.class)
@CucumberOptions(features="src/test/java/features/", plugin = {"pretty", "json:target/cucumber-reportsF/JsonReport.json",
		 "junit:target/cucumber-reportsF/XMLReport.xml",
		 "html:target/cucumber-reportsF/HTMLReport.html" }, glue= {"stepDefinitions"},
tags= "@DataTable", monochrome=true )
public class RunnerClass {

}
