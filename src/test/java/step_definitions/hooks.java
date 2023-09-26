package step_definitions;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import utilities.DataReader;
import utilities.Driver;

public class hooks {

	//Hook Cucumber Java:
	@Before
	public void setup() {
		System.out.println("This is before hook");
		Driver.getDriver().manage().window().maximize();
		Driver.getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
	}
	
	@After
	public void teardown(Scenario scenario) {
		System.out.println("This is after hook");
		if (scenario.isFailed()) {
			final byte[] screenshot = ((TakesScreenshot) 
					Driver.getDriver()).getScreenshotAs(OutputType.BYTES);
			scenario.attach(screenshot, "image/png", "screenshot");
		}
		Driver.quitDriver();
	}
	
}
