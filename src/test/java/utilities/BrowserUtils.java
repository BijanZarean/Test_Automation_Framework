package utilities;

import java.time.Duration;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Random;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BrowserUtils {

	// Functions specific to browser UI

	Actions action;
	WebDriverWait wait;
	JavascriptExecutor js;
	Select letsSelect;

	// waits for an element to be visible
	public void waitUntilElementvisible(WebElement element) {
		wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOf(element));
	}

	// waits for an element to be gone
	public void waitUntilElementNotvisible(WebElement element) {
		wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(15));
		wait.until(ExpectedConditions.invisibilityOf(element));
	}

	// wit until element is able to be clicked
	public void waitUntilElementToBeClickable(WebElement element) {
		wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(10));
		wait.until(ExpectedConditions.elementToBeClickable(element));
	}

	// this method checks if an element exists in the dom (html)
	public boolean isElementPresent(WebElement element) {
		try {
			element.isDisplayed();
		} catch (NoSuchElementException e) {
			return false;
		}
		return true;
	}

	// avoiding StaleElementReferenceException
	public boolean retryingFindClick(WebElement element) {
		boolean result = false;
		int attempts = 0;
		while (attempts < 2) {
			try {
				element.click();
				result = true;
				break;
			} catch (StaleElementReferenceException e) {
			}
			attempts++;
		}
		return result;
	}

	// sendkeys via actions class to the field that is not intractable
	public void actionsSendKeys(WebElement element, String text) {
		action = new Actions(Driver.getDriver());
		action.sendKeys(element, text).build().perform();
	}

	// using actions class to click on an element
	public void actionsClick(WebElement element) {
		Actions act = new Actions(Driver.getDriver());
		act.moveToElement(element).click().perform();
	}

	// using javascript to enlarge an element so that it may be clickable in
	// headless mode
	public void makeVisibleJS(WebElement element) {
		js = (JavascriptExecutor) Driver.getDriver();
		js.executeScript("arguments[0].style.display='block';", element);
	}

	// select by visible text
	public void selectByVisibleText(WebElement selectElement, String tobeSelectedOptionText) {
		letsSelect = new Select(selectElement);
		letsSelect.selectByVisibleText(tobeSelectedOptionText);
	}

	// return the selected option from the dropdown
	public String getSelectedOption(WebElement selectElement) {
		letsSelect = new Select(selectElement);
		String option = letsSelect.getFirstSelectedOption().getText();
		return option;
	}

	// returns a random generated number
	public int randomNumber() {
		Random rand = new Random();
		int randomNum = rand.nextInt((999 - 100) + 1) + 100;
		return randomNum;
	}

	public void clearText(WebElement element) {
		String temp = element.getAttribute("value");
		for (int i = 0; i < temp.length(); i++) {
			element.sendKeys(Keys.BACK_SPACE);
		}
	}

	public void scrollToElement(WebElement element) {
		js = (JavascriptExecutor) Driver.getDriver();
		js.executeScript("arguments[0].scrollIntoView(true);", element);
	}

	public void scrollByUpOrDownPixel(int number) {
		js = (JavascriptExecutor) Driver.getDriver();
		js.executeScript("window.scrollBy(0, " + number + ")");
	}

	public void highlightElement(WebElement element) throws InterruptedException {
		js = (JavascriptExecutor) Driver.getDriver();
		for (int i = 0; i < 3; i++) {
			js.executeScript("arguments[0].style.border='4px solid red'", element);
			Thread.sleep(100);
		}

	}

	// dropDowns

	public List<WebElement> getDropDownOptions(WebElement selectElement) {
		Select choose = new Select(selectElement);
		List<WebElement> allOptions = choose.getOptions();
		return allOptions;
	}

	public void dropDownValueValidation(String expected, WebElement elementActual) {
		Select dropDown = new Select(elementActual);
		if (dropDown.getFirstSelectedOption().getText().equals(expected)) {
			System.out.println(
					"Default dropdown value is correct, PASS. Value: " + dropDown.getFirstSelectedOption().getText());
		} else {
			System.out.println("Default dropdown value is not correct, FAIL  Value: "
					+ dropDown.getFirstSelectedOption().getText());
		}
	}

	public void selectDropDownByString(String value, WebElement dropDown) {
		Select select = new Select(dropDown);
		select.selectByVisibleText(value);
	}

	public void dropDownSizeValidation(int expectedSize, WebElement dropDown) {
		Select select = new Select(dropDown);
		List<WebElement> allOptions = select.getOptions();

		if (allOptions.size() == expectedSize) {
			System.out.println("There are " + expectedSize + " departments, PASS");
		} else {
			System.out.println("There are not " + expectedSize + " departments, FAIL:");
		}
		System.out.println(allOptions.size());
	}

	public void printAllDropDownOpt(WebElement dropDown) {
		Select select = new Select(dropDown);
		List<WebElement> allOptions = select.getOptions();
		for (WebElement option : allOptions) {
			System.out.println(option.getText());
		}
	}

	// Title
	public String getTitle() {
		return Driver.getDriver().getTitle();
	}

	public void pageTitleValidation(String expected, WebDriver actualTitle) {
		if (actualTitle.getTitle().contains(expected)) {
			System.out.println("Current page title matches expected, PASS. Title: " + actualTitle.getTitle());
		} else {
			System.out.println("Current page title does not match expected, FAIL. Title: " + actualTitle.getTitle());
		}

	}

	public boolean pageTitleValidationAssert(String expected, WebDriver actualTitle) {
		if (actualTitle.getTitle().contains(expected)) {
			return true;
		} else {
			return false;
		}

	}

}
