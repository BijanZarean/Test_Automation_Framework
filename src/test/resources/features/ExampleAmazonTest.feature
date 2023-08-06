@TestExamples @amazon
Feature: Amazon departments and search test examples


@amazonDepartmentsTest
Scenario: As a User, I am able to select different departments and search
Given I am on the amazon homepage
And The departments dropdown is "All Departments"
When I select the department "Home & Kitchen"
And I search "Coffee mug"
Then I should be on "Coffee mug" search result page
And The departments dropdown is "Home & Kitchen"

 @amazonSearchTest
 Scenario Outline: As a user, I am able to search for different products and recieve appropriate results
 Given I am on the amazon homepage
 When I search "<product>" and click the search button
 Then I should be on the "<product>" search result page
    
    
   Examples:
   | product           |
   | coffee mug        |
   | pretty coffee mug |
   | cool coffee mug   |
   | cute coffee mug   |