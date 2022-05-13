package stepDefinations;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.apache.http.impl.conn.tsccm.WaitingThread;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import resources.APIResources;
import resources.TestDataBuild;
import resources.Utils;

public class StepDefination extends Utils{
	RequestSpecification res;
	ResponseSpecification resspec;
	Response response;
	TestDataBuild data = new TestDataBuild();
	public static String placeId;
	
	@Given("Add place payload with {string} {string} {string}")
	public void add_place_payload_with(String name, String language, String address) throws IOException {
		
		//Response Specification
		
		res = given().spec(requestSpecification())
		.body(data.addPlacePayload(name,language,address));
	}

	@When("User call {string} with {string} http request")
	public void user_call_with_http_request(String resource, String method) {
		
		APIResources resourceAPI = APIResources.valueOf(resource);
		System.out.println(resourceAPI.getResources());
		
		resspec = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
		if(method.equalsIgnoreCase("POST")) 
			response = res.when().post(resourceAPI.getResources());
		else if(method.equalsIgnoreCase("GET"))
			response = res.when().get(resourceAPI.getResources());
		
	}
//	.then().spec(resspec).extract().response();
	@Then("The API call got success with status code {int}")
	public void the_API_call_got_success_with_status_code(Integer int1) {
		
	  assertEquals(response.getStatusCode(), 200);
	}

	@Then("{string} in response body is {string}")
	public void in_response_body_is(String keyValue, String expectedValue) {

	    assertEquals(getJsonPath(response, keyValue),expectedValue);
	}
	
	@Then("verify place_Id created maps to {string} using {string}")
	public void verify_place_Id_created_maps_to_using(String expectedName, String resource) throws IOException {
		placeId= getJsonPath(response, "place_id");
		res = given().spec(requestSpecification()).queryParam("place_id", placeId);
		user_call_with_http_request(resource,"GET");
		String actualName= getJsonPath(response, "name");
		System.out.println(actualName);
		assertEquals(actualName, expectedName);
	}
	
	@Given("DeletePlace Payload")
	public void deleteplace_Payload() throws IOException {
	  res = given().spec(requestSpecification()).body(data.deletePlacePayload(placeId));
	  System.out.println("In delete api");
	}


}
