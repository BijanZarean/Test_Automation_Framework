# Test Automation Framework
Complete end 2 end test automation framework for UI and API testing

This test automation framework is built as a Maven project utilizing Behavior Driven Development principles with Cucumber and JUnit in Java.
It also uses a Page Object Model design for framework reusability and maintainability.
The Cucumber Scenarios are written in Gherkin language which is plain English and could aid in helping to keep the team connected.

This framework is also structured to support conducting API testing using RestAssured Library as well as Database integration capabilities using JDBC.

## The Project Structure:

src/test/java
- api_tests: API tests written using TestNG test structure and RestAssured Library.
- pages: Page objects are created within this folder utilizing Page Object Model design.
- runners: The Cucumber runner classes reside within this folder.
- step_definitions: The test steps implementation for the scenario steps in the Cucumber feature files.
- utilities: Any support and utility classes are within this folder.

src/test/resources
- features: Cucumber feature files are created and kept in this folder. They define scenarios for features.
- test_data: Test data and properties files are kept within this folder.
- test_files: Json files and any other files are stored in this folder.

- pom.xml: Project, build, and run configurations as well as content, library, and dependencies management.
- TestNG_APITests: TestNG test suite management and executions control.

## Framework Tools

Tools Used for UI Automation Testing
- Maven: Project, build, and run configurations as well as content, library, and dependencies management.
- Cucumber: Is used to define feature scenarios in gherkin language and to create test suites and execution flow through tagging.
- Selenium: Used to automate the browser for UI testing of the web application by implementing the step definitions and managing the page objects.
- WebDriverManager: Used to manage the browser driver binary and auto downloading and setting up the browser drivers.
- Junit: Used to execute the Cucumber scenarios with Cucumber options.
- JDBC: Used to establish a connection with databases for testing activities.
- MySQL driver: Used to allow connection to MySQL database that the application uses.

Tools Used for API Automation Testing
- TestNG: Used for test execution flow and assertions, manage API test suites, and reporting.
- RestAssured: Used to define API tests.
- JDBC: Used to establish a connection with databases for testing activities.
- MySQL driver: Used to allow connection to MySQL database that the application uses.

Other tools used for end 2 end testing
- Git: Version control and source code management.
- GitHub: Version control platform and remote source code management.
- Jenkins: CI/CD tool used for running the test suites.
- Eclipse: Integrated development environment used for project development.

## Creating Tests

### Creating UI Tests: [Cucumber Documents](https://cucumber.io/docs/cucumber/api/?lang=java#running-cucumber)

1. Create a feature file in the 'features' folder under 'src/test/resources' with the extension '.feature'.
2. Define scenario in the feature file with the Cucumber key words 'Given', 'When', and 'Then' structure.
3. Generate Step Definition snippets using 'dry runner' class.
4. Create a steps class in the 'step_definitions' folder under 'src/test/java' using the step definition snippets.
5. Create a page class in the 'pages' folder under 'src/test/java' and define the page objects / elements.
6. Implement the step definitions based on the Cucumber scenario behaviors.

### Creating API Tests:  [RestAssured Docs](https://rest-assured.io/)

1. Create a class in 'api_tests' folder under src/test/java.
2. Create a TestNG test and define the API test flow using RestAssured library.

## Running Tests

### Running UI Tests

1. Create a runners class under 'runners' folder within 'src/test/java'.
2. Define the RunWith Cucumber options with the proper necessary options.
3. Pass the scenario tag to run the test locally using Cucumber tags and execute as Junit test.

For running the tests remotely through a CI/CD tool such as Jenkins
4. Create a build in 'pom.xml' and point to the runner class in build configuration. (refer to pom.xml in this project for an example)
5. On Jenkins job under 'build workflow', select 'top level maven target' and pass the following command:
'''bash
clean test -Dcucumber.filter.tags="@smoketest"
'''

### Running API Tests

1. Run the API test class as a TestNG test.

For running the tests remotely through a CI/CD tool such as Jenkins
2. Create a 'TestNG.xml' file and define a TestNG suite that contains the API test classes that you want to execute.
3. Create a profile in pom.xml file that includes the TestNG xml as a test suite.
4. On jenkins under 'build workflow', select 'top level maven target' and pass the following command:
'''bash
clean test -P<profileId>

Profile example:
'''
	<profiles>
		<profile>
			<id>api_tests</id>
			<build>
				<plugins>
					<plugin>
						<groupId>org.apache.maven.plugins</groupId>
						<artifactId>maven-surefire-plugin</artifactId>
						<version>3.0.0-M5</version>
						<configuration>
							<suiteXmlFiles>
								<suiteXmlFile>TestNG_APITests.xml</suiteXmlFile>
							</suiteXmlFiles>
						</configuration>
					</plugin>
				</plugins>
			</build>
		</profile>
	</profiles>
'''