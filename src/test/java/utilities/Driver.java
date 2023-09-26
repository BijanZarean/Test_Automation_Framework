package utilities;

import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;


public class Driver {
	
/* Driver class is reusable class for webDriver and it checks the webDriver on the system.
 * If there isn't any driver on the system, it downloads the driver and sets up the path and environment  
 * For this purpose, I've used WebDriver manager
 * And if I want to run my script on different browser, 
 * all I have to do is change the browser name in the env.properties file.
 */

	public static WebDriver driver;
	static ChromeOptions chromeOptions;

	public static WebDriver getDriver() {
		String browser = System.getProperty("browser");
		if (browser == null) {
			//Reading from the env.properties file and assigns browser whatever browser_type is:
			browser = DataReader.getProperty("browser_type");
		}
		if (driver == null || ((RemoteWebDriver) driver).getSessionId() == null) {
			switch (browser) {
			case "chrome-headless":
				chromeOptions = new ChromeOptions();
				chromeOptions.setPageLoadStrategy(PageLoadStrategy.NORMAL);
				chromeOptions.addArguments("--headless");
				driver = new ChromeDriver(chromeOptions);
				break;
			case "chrome":
				driver = new ChromeDriver();
				break;
			case "firefox":
				driver = new FirefoxDriver();
				break;
			case "firefox-headless":
				FirefoxOptions firefoxOptions = new FirefoxOptions();
				firefoxOptions.addArguments("--headless");
				driver = new FirefoxDriver(firefoxOptions);
				break;
			case "safari":
				driver = new SafariDriver();
				break;
			case "edge":
				driver = new EdgeDriver();
				break;
			default:
				ChromeOptions Options = new ChromeOptions();
				Options.addArguments("--headless");
				driver = new ChromeDriver(Options);
				break;
			}
		}
		return driver;
	}

	public static void quitDriver() {
		if (driver != null) {
			driver.quit();
			driver = null;
		}
	}
}
