package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utilities.Driver;

public class AmazonHomePage {
	
	//Pages class utilizing Page Object Model
	
	//Where we store page objects and useful functions specific to that page

	
	//Constructor: use PageFactory class to initialize the elements in this page with the Driver instance we have.
	
	/*
	 * Whenever we create an object of this class, any page objects and useful functions will be loaded with the 
	 * driver in the memory so we can use them.
	 */
	
	public AmazonHomePage() {
		PageFactory.initElements(Driver.getDriver(), this);
	}

	
	
	@FindBy(id = "searchDropdownBox")
	public WebElement departmentsDropdown;

	@FindBy(xpath = "//form[@name='site-search']//child::input[@type='text']")
	public WebElement searchField;
	//input[@id='twotabsearchtextbox']
}
