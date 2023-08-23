package step_definitions;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.ui.Select;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.AmazonHomePage;
import utilities.BrowserUtils;
import utilities.Driver;

public class AmazonTestSteps {
	
	BrowserUtils utils = new BrowserUtils();
	AmazonHomePage homepage = new AmazonHomePage();

	@Given("I am on the amazon homepage")
	public void i_am_on_the_amazon_homepage() throws InterruptedException {
		Driver.getDriver().get("https://amazon.com");
		Thread.sleep(3000);
		String homepageTitle = Driver.getDriver().getTitle();
		Assert.assertEquals("Amazon.com. Spend less. Smile more.", homepageTitle);
	}

	@Given("The departments dropdown is {string}")
	public void the_departments_dropdown_is(String defaultOption) {
		Assert.assertEquals(utils.getSelectedOption(homepage.departmentsDropdown), defaultOption);
	}

	@When("I select the department {string}")
	public void i_select_the_department(String optionToSelect) {
		utils.selectByVisibleText(homepage.departmentsDropdown, optionToSelect);
	}

	@When("I search {string}")
	public void i_search(String searchItem) {
		homepage.searchField.sendKeys(searchItem + Keys.ENTER);
	}

	@Then("I should be on {string} search result page")
	public void i_should_be_on_search_result_page(String searchedItem) {
		String searchPageTitle = Driver.getDriver().getTitle();
		Assert.assertTrue(searchPageTitle.contains(searchedItem));
	}

	// amazon search scenario outline
	@When("I search {string} and click the search button")
	public void i_search_and_click_the_search_button(String product) {
		utils.waitUntilElementvisible(homepage.searchField);
		homepage.searchField.sendKeys(product + Keys.ENTER);
	}

	@Then("I should be on the {string} search result page")
	public void i_should_be_on_the_search_result_page(String product) {
		Assert.assertTrue(utils.pageTitleValidationAssert(product, Driver.getDriver()));
	}

}
