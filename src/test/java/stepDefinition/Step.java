package stepDefinition;

import static io.restassured.RestAssured.given;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.*;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import pojo.Location;
import pojo.Serailize;
import resources.APIResource;
import resources.TestData;
import resources.Utils;

public class Step extends Utils {
	
	RequestSpecification request;
	ResponseSpecification res;
	Response response;
	TestData td = new TestData();	
	JsonPath js;
	static String placeId;
	

		@Given("User has API endpoint {string} {string} {string} {string}")
		public void user_has_api_endpoint(String name, String language, String address, String website) throws IOException {
			request =given().spec(requestSpec())
			.body(td.getJsonData(name, language,address, website));

		}


	
			@When("user sends {string} http {string} request")
			public void user_sends_http_request(String resource, String httpMethod) {
				
				APIResource apiResource = APIResource.valueOf(resource);
				System.out.println(apiResource.getResource());
				
				if(httpMethod.equalsIgnoreCase("POST")) {
					response = request.when().post(apiResource.getResource());
				} else if(httpMethod.equalsIgnoreCase("GET")) {
					response = request.when().get(apiResource.getResource());
				}
			}

		
		@Then("validate that status code in response body")
		public void validate_that_status_code_in_response_body() {
				assertEquals(response.getStatusCode(), 200);
		}

		@Then("{string} contains in it response {string}")
		public void contains_in_it_response(String j, String y) {

			assertEquals(getJsonPath(response,j),y);

		}
		
		@Then("verify place_id created above maps to {string} using {string}")
		public void verify_place_id_created_above_maps_to_using(String expectedName, String resource) throws IOException {
				placeId = getJsonPath(response, "place_id");				
				request =given().spec(requestSpec()).queryParam("place_id", placeId);
				user_sends_http_request(resource, "GET");
				String actualName = getJsonPath(response, "name");
				assertEquals(expectedName,actualName);			
				

			}

			@Given("user has endpoint")
			public void user_has_endpoint() throws IOException {
				request =given().spec(requestSpec()).body(td.getDeleteData(placeId));

			}



			











}
