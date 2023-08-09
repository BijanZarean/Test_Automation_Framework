package api_tests;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import java.io.File;
import org.hamcrest.Matchers;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import utilities.BrowserUtils;

public class ExampleAPITests {
	
	//Running the API tests using TestNG
	
	/*
	 * Assert class from TestNG provides us with Assertion methods to verify if a certain condition is true or not. 
	 * These methods are used below in the API tests to verify the API is performing accordingly.
	 */

	BrowserUtils utils = new BrowserUtils();

	Response response;
	static int iD;
	static String petName;

	@BeforeTest
	public void setup() {
		baseURI = "https://petstore.swagger.io/v2";
	}

//	@AfterMethod
//	public void cleanup() {
//		deleteThePet();
//	}

	@Test
	public void findPetsByStatus() {
		// The test is directly calling to the end point and validating just two items
		// status code and content type.

		String endpoint = "/pet/findByStatus";
		given().contentType("application/json").accept(ContentType.JSON).when().get(endpoint + "?status=sold").then()
				.statusCode(200).and().contentType("application/json");
	}

	@Test
	public void findPetsByStatus_providingQueryParam() {
		// The test is directly calling to the end point with query parameters as a
		// header
		// and getting the response into a Response object,
		// Then validating the status code and content type.

		String endpoint = "/pet/findByStatus";
		response = given().contentType("application/json").accept(ContentType.JSON).queryParam("status", "sold").when()
				.get(endpoint).thenReturn();

		response.prettyPrint();

		Assert.assertEquals(response.getStatusCode(), 200);
		Assert.assertEquals(response.getContentType(), "application/json");
	}

	@Test
	public void createAPet() {
		iD = 1000 + utils.randomNumber();
		petName = "Wolfy";
		String endpoint = "/pet";
		String requestBody = "{\n" + "  \"id\": " + iD + ",\n" + "  \"category\": {\n" + "    \"id\": 21,\n"
				+ "    \"name\": \"Dog\"\n" + "  },\n" + "  \"name\": \"" + petName + "\",\n" + "  \"photoUrls\": [\n"
				+ "    \"string\"\n" + "  ],\n" + "  \"tags\": [\n" + "    {\n" + "      \"id\": 21000,\n"
				+ "      \"name\": \"W21000\"\n" + "    }\n" + "  ],\n" + "  \"status\": \"Available\"\n" + "}";
		response = given().contentType("application/json").accept(ContentType.JSON).body(requestBody).when()
				.post(endpoint).thenReturn();

		response.prettyPrint();
		Assert.assertEquals(response.getStatusCode(), 200);
		Assert.assertEquals(response.getContentType(), "application/json");
		Assert.assertEquals(response.jsonPath().getInt("id"), iD);

		System.out.println(response.path("name").toString());
		System.out.println(response.path("category.name").toString());
		System.out.println(response.jsonPath().get("id").toString());
		System.out.println(response.jsonPath().get("status").toString());
		System.out.println(response.jsonPath().get("tags[0].name").toString());
	}

	@Test(dependsOnMethods = "createAPet")
	public void updateAPet() {
		petName = "Wolfy";
		String endpoint = "/pet";
		String requestBody = "{\n" + "  \"id\": " + iD + ",\n" + "  \"category\": {\n" + "    \"id\": 21,\n"
				+ "    \"name\": \"Dog\"\n" + "  },\n" + "  \"name\": \"Darko\",\n" + "  \"photoUrls\": [\n"
				+ "    \"string\"\n" + "  ],\n" + "  \"tags\": [\n" + "    {\n" + "      \"id\": 21000,\n"
				+ "      \"name\": \"W21000\"\n" + "    }\n" + "  ],\n" + "  \"status\": \"Adopted\"\n" + "}";
		response = given().contentType("application/json").accept(ContentType.JSON).body(requestBody).when()
				.put(endpoint).thenReturn();

		response.prettyPrint();
		Assert.assertEquals(response.getStatusCode(), 200);
		Assert.assertEquals(response.getContentType(), "application/json");
		Assert.assertEquals(response.jsonPath().getInt("id"), iD);
		Assert.assertEquals(response.jsonPath().getString("name"), "Darko");

		System.out.println(response.path("name").toString());
		System.out.println(response.path("category.name").toString());
		System.out.println(response.jsonPath().get("id").toString());
		System.out.println(response.jsonPath().get("status").toString());
		System.out.println(response.jsonPath().get("tags[0].name").toString());
	}

	@Test(dependsOnMethods = "createAPet")
	public void deleteThePet() {
		String endpoint = "/pet/" + iD;

		response = given().accept(ContentType.JSON).contentType("application/json").delete(endpoint).thenReturn();

		response.prettyPrint();
		Assert.assertEquals(response.getStatusCode(), 200);
		Assert.assertEquals(response.getContentType(), "application/json");
		Assert.assertEquals(response.jsonPath().getString("message"), iD + "");
	}

	@Test
	public void createAPetWithJSONFile() {

		String endpoint = "/pet";
		File requestBody = new File("./src/test/resources/test_files/CreateAPet.json");

		response = given().contentType("application/json").accept(ContentType.JSON).body(requestBody).when()
				.post(endpoint).thenReturn();

		iD = response.jsonPath().getInt("id");

		response.prettyPrint();
		Assert.assertEquals(response.getStatusCode(), 200);
		Assert.assertEquals(response.getContentType(), "application/json");
		Assert.assertEquals(response.jsonPath().getInt("id"), iD);

		System.out.println(response.path("name").toString());
		System.out.println(response.path("category.name").toString());
		System.out.println(response.jsonPath().get("id").toString());
		System.out.println(response.jsonPath().get("status").toString());
		System.out.println(response.jsonPath().get("tags[0].name").toString());
	}

	@Test(dependsOnMethods = "createAPet")
	public void getAPetByID() {
		String endpoint = "/pet/" + iD;
		response = given().contentType("application/json").accept(ContentType.JSON).when().get(endpoint).thenReturn();

		response.prettyPrint();

		Assert.assertEquals(response.getStatusCode(), 200);
		Assert.assertEquals(response.contentType(), "application/json");
		Assert.assertEquals(response.jsonPath().getInt("id"), iD);
		Assert.assertEquals(response.jsonPath().getInt("category.id"), 21);
		Assert.assertEquals(response.jsonPath().getString("category.name"), "Dog");
		Assert.assertEquals(response.jsonPath().getString("name"), petName);
		Assert.assertEquals(response.jsonPath().getString("status"), "Available");
	}

	@Test(dependsOnMethods = "createAPet")
	public void getAPetByID_ChainValidation() {
		String endpoint = "/pet/" + iD;
		response = given().contentType("application/json").accept(ContentType.JSON).when().get(endpoint).thenReturn();

		response.then().assertThat().statusCode(200).and().assertThat().contentType("application/json").and()
				.assertThat().body("id", Matchers.equalTo(iD)).and().assertThat()
				.body("category.id", Matchers.equalTo(21)).and().assertThat()
				.body("category.name", Matchers.equalTo("Dog")).and().assertThat()
				.body("name", Matchers.equalTo(petName)).and().assertThat()
				.body("tags[0].name", Matchers.equalTo("W21000")).and().assertThat()
				.body("status", Matchers.equalTo("Available"));
	}

	@Test
	public void petNotFound() {
		String endpoint = "/pet/" + 4611111;
		response = given().accept(ContentType.JSON).contentType("application/json").get(endpoint).thenReturn();

		response.prettyPrint();

		Assert.assertEquals(response.getStatusCode(), 404);
		Assert.assertEquals(response.contentType(), "application/json");
		Assert.assertEquals(response.jsonPath().getString("message"), "Pet not found");
	}

}
