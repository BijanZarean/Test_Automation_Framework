package runners;

import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

//Using JUnit to run our cucumber scenarios / tests, locally or remotely.

//@RunWith tag is from JUnit and by passing Cucumber.class, we can run our cucumber scenario tests using JUnit.

@RunWith(Cucumber.class)
@CucumberOptions(
		/*
		 * Specifying the plug in keyword with generating two types of "pretty" test reports, 
		 * cucumber html and json reports in the test_results folder
		 */
		plugin ={"pretty", 
				"html:test_results/cucumber_report.html",
				"json:test_results/cucumber_report.json"},
		//Telling the runner class where the cucumber feature files reside in the project framework.
		features="./src/test/resources/features",
		//Telling the runner class where the step definition classes reside in the project framework.
		glue="step_definitions",
		//Telling the runner class which cucumber scenario tests to run in the feature files.
		tags="@TestExamples"
		,publish=true
		)

public class TestRunner {

}
