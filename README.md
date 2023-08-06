# Test Automation Framework
Complete end 2 end test automation framework for UI and API testing

This test automation framework is built as a Maven project utilizing Behavior Driven Development principles with Cucumber and JUnit in Java.
It also uses a Page Object Model design for framework reusability and maintainability.
The Cucumber Scenarios are written in Gherkin language which is plain English and could aid in helping to keep the team connected.

This framework is also structured to support conducting API testing using RestAssured Library as well as Database integration capabilities using JDBC.

## The Project Structure:

src/test/java
- api_tests: API tests written using TestNG test structure and RestAssured Library.
- pages: page objects are created within this folder utulizing Page Object Model design.
- runners: the Cucumber runner classes reside within this folder.