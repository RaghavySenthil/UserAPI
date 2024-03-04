package StepDefinitions;

import static org.testng.Assert.assertEquals;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Optional;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;



import EndPoints.Endpoint;
import TestRequest.UserReqSpec;
import Utilities.ConfigReader;

import Utilities.UserExcelReader;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.authentication.BasicAuthScheme;
import io.restassured.matcher.ResponseAwareMatcher;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBodyExtractionOptions;

public class UserAPI {
	
	public static String user_id;
	public static String user_first_name;
	Response GetAllUserResponse;
	Response InvalidendAllUserResponse;
	Response ValidDataResponse;
	Response GetUserbyIDResponse;
	Response GetUserbyFNResponse;
	Response DeleteUserbyIDResponse;
	Response DeleteUserbyFNResponse;
	Response PutValidDataResponse;
	ConfigReader ConfigReaderobj;
	Properties prop;
	//int user_id;
//ConfigReader for reading UserID and password from config file	
	public  UserAPI() throws IOException {
		ConfigReaderobj = new ConfigReader();
		        prop = ConfigReaderobj.init_prop();
		}
	
	public void authentication() throws IOException
	{
	BasicAuthScheme authScheme = new BasicAuthScheme();
	        authScheme.setUserName(ConfigReader.prop.getProperty("username"));
	        authScheme.setPassword(ConfigReader.prop.getProperty("Password"));
	RestAssured.authentication = authScheme;
	}
	@Before
	public void BeforeHook() throws IOException {
	authentication();
	}
//Get All the user-Valid end point	
	@Given("User creates GET Request for the API endpoint to get all Users")
	public void user_creates_get_request_for_the_api_endpoint_to_get_all_users() {
		GetAllUserResponse = RestAssured
				.given()
	    		.spec(UserReqSpec.GetAllUser())
		    	.when()
		    	.get();
	    
	   
	}

	@When("User sends GET request")
	public void user_sends_get_request() {
		GetAllUserResponse.then().log().all().extract().response();
	    
	}

	@Then("Response status code should be {int} OK and response body contains all the users")
	public void response_status_code_should_be_ok_and_response_body_contains_all_the_users(Integer int1) {
		GetAllUserResponse.then().log().all().assertThat().statusCode(int1).extract().response().asString();
		int statuscode=GetAllUserResponse.getStatusCode();
		System.out.println("Statuscode:" +statuscode);
	   
	}
	//Get All the user-InValid end point
	@Given("User sets valid URL and invalid endpoint")
	public void user_sets_valid_url_and_invalid_endpoint() {
		InvalidendAllUserResponse = RestAssured
				.given()
	    		.spec(UserReqSpec.InvalidGetAllUser())
		    	.when()
		    	.get();
	}
	
	@When("User sends GET Invalid request")
	public void user_sends_get_invalid_request() {
		InvalidendAllUserResponse.then().log().all().extract().response();
	    
	}

	@Then("Response status code should be {int} Not Found and response body contains error message")
	public void response_status_code_should_be_not_found_and_response_body_contains_error_message(Integer int1) {
		InvalidendAllUserResponse.then().log().all().assertThat().statusCode(int1).extract().response().asString();
		int statuscode=InvalidendAllUserResponse.getStatusCode();
		System.out.println("Statuscode:" +statuscode);
	}
	//Post-Create new User    
	@Given("User creates POST Request {string} and {int} for the API endpoint with valid credentials")
	public void user_creates_post_request_and_for_the_api_endpoint_with_valid_credentials(String string, Integer int1) throws InvalidFormatException, IOException {
	
		// Retrieve data from Excel file
		
		 List<Map<String, String>> getUserData= (UserExcelReader.getData(Endpoint.Excelpath2, "ValidData"));
		
		// Iterate over each row of data
		 for (Map<String, String> row : getUserData){
			String firstname = row.get("FirstName");
			String lastname = row.get("LastName");
			String phonenumber = row.get("ContactNumber");
			String emailid = row.get("Email");
			String plotNumber = row.get("PlotNumber");
			String street = row.get("Street");
			String state = row.get("State");
			String country = row.get("Country");
			String zipCode = row.get("ZipCode");
			
			  // Construct JSON body for the request
			String userInfoJson = "{" +
					"\"user_first_name\": \"" + firstname + "\"," +
					"\"user_last_name\": \"" + lastname + "\"," +
					"\"user_contact_number\": \"" + phonenumber + "\"," +
					"\"user_email_id\": \"" + emailid + "\"," +
					"\"userAddress\": {" +
					"\"plotNumber\": \"" + plotNumber + "\"," +
					"\"street\": \"" + street + "\"," +
					"\"state\": \"" + state + "\"," +
					"\"country\": \"" + country + "\"," +
					"\"zipCode\": " + zipCode +
					"}" +
					"}";
			ValidDataResponse = RestAssured
					.given()
		    		.spec(UserReqSpec.PostValidData()).body(userInfoJson)
		    		.when()
			    	.post();
			ValidDataResponse.then().assertThat().body(JsonSchemaValidator.matchesJsonSchema(new File("./src/test/resources/PostSchema.json")));
			assertEquals(firstname,ValidDataResponse.jsonPath().getString("user_first_name") ); 
			assertEquals(lastname,ValidDataResponse.jsonPath().getString("user_last_name") );
			assertEquals(phonenumber,ValidDataResponse.jsonPath().getString("user_contact_number") );
			assertEquals(emailid,ValidDataResponse.jsonPath().getString("user_email_id") );
			assertEquals(plotNumber,ValidDataResponse.jsonPath().getString("userAddress.plotNumber") );
			assertEquals(street,ValidDataResponse.jsonPath().getString("userAddress.street") );
			assertEquals(state,ValidDataResponse.jsonPath().getString("userAddress.state") );
			assertEquals(country,ValidDataResponse.jsonPath().getString("userAddress.country") );
			assertEquals(zipCode,ValidDataResponse.jsonPath().getString("userAddress.zipCode") );
		
			
			//System.out.println(userInfoJson);
		
		
		
		}
		
	   
	}

	private ResponseAwareMatcher<Response> is(Object message) {
		// TODO Auto-generated method stub
		return null;
	}

	@When("User sends HTTPS Request and  request Body with mandatory fields")
	public void user_sends_https_request_and_request_body_with_mandatory_fields() {
		          ValidDataResponse.then().log().all();
	}
//Getting and the userID and User name
	@Then("New user must be created with the Response code: {int}")
	public void new_user_must_be_created_with_the_response_code(Integer int1) {
		 user_id = ValidDataResponse.jsonPath().getString("user_id");
		 user_first_name = ValidDataResponse.jsonPath().getString("user_first_name");
        // Print the user ID
       System.out.println("User ID: " + user_id);
       System.out.println("First Name: " +  user_first_name);
       //Assert.assertEquals(ValidDataResponse.header("Content-Type"), "application/json");
       ValidDataResponse.then().assertThat().body(JsonSchemaValidator.matchesJsonSchema(getClass()
    		   .getClassLoader()
    		   .getResourceAsStream("PostSchema.json")));
		//ValidDataResponse.then().assertThat().statusCode(201);
		
		
	}
	
//Get the User by UserID using the PathParams	
	@Given("User sets GET request for user API with the valid given base URL and valid endpoint")
	public void user_sets_get_request_for_user_api_with_the_valid_given_base_url_and_valid_endpoint() {
		
		System.out.println(user_id);
		GetUserbyIDResponse = RestAssured
			 
				.given()
	    		.spec(UserReqSpec.GetUserbyID())
	    		.pathParam("userId",user_id)
		    	.when()
		    	 .get(); 	
		}
	@When("User sends GET request with valid user ID")
	public void user_sends_get_request_with_valid_user_id() {
		GetUserbyIDResponse.then().log().all().extract().response();
	}

	@Then("The response status code should be {int} OK and the response body should contain the user that we searched for")
	public void the_response_status_code_should_be_ok_and_the_response_body_should_contain_the_user_that_we_searched_for(Integer int1) {
		GetUserbyIDResponse.then().statusCode(200);
		
	}
	//Get the User by UserFirstname using the PathParams	

	@Given("User sets GET request using correct URL endpoint")
	public void user_sets_get_request_using_correct_url_endpoint() {
		
		GetUserbyFNResponse = RestAssured
			 
				.given()
	    		.spec(UserReqSpec.GET_userbyFN())
	    		.pathParam("userFirstName",user_first_name)
		    	.when()
		    	 .get(); 
	}

	@When("Get request is made using valid user FirstName")
	public void get_request_is_made_using_valid_user_first_name() {
		GetUserbyFNResponse.then().log().all().extract().response();
	}

	@Then("The response status code should be {int} OK and the response body should contain the user details that we searched for")
	public void the_response_status_code_should_be_ok_and_the_response_body_should_contain_the_user_details_that_we_searched_for(Integer int1) {
		GetUserbyFNResponse.then().statusCode(200);
	}

	//Delete the User by UserID using the PathParams	
	
	@Given("User creates DELETE Request for user API with valid endpoint")
	public void user_creates_delete_request_for_user_api_with_valid_endpoint() {
		DeleteUserbyIDResponse= RestAssured
		 			.given()
   		           .spec(UserReqSpec.DeleteByUserID())
   		           .pathParam("userId",user_id)
	    	       .when()
	    	        .delete(); 
	    
	}

	@When("User sends DELETE request with valid user ID")
	public void user_sends_delete_request_with_valid_user_id() {
		DeleteUserbyIDResponse.then().log().all().extract().response();
	}

	@Then("The response status code should be {int} OK  and the response body should contain the message as User is deleted successfully!")
	public void the_response_status_code_should_be_ok_and_the_response_body_should_contain_the_message_as_user_is_deleted_successfully(Integer int1) {
		DeleteUserbyIDResponse.then().statusCode(200);
	}
	
	//Delete the User by User name using the PathParams	
	@Given("User creates DELETE Request for user API with valid endpoint name")
	public void user_creates_delete_request_for_user_api_with_valid_endpoint_name() {
		DeleteUserbyFNResponse= RestAssured
	 			.given()
		        .spec(UserReqSpec.DeleteByName())
		       .pathParam("userfirstname",user_first_name)
    	       .when()
    	        .delete(); 
	}

	@When("User sends DELETE request with valid user Name")
	public void user_sends_delete_request_with_valid_user_name() {
		DeleteUserbyFNResponse.then().log().all().extract().response();
	}

	@Then("The response status code should be {int} OK  and the response body should contain the message as User is deleted usersuccessfully!")
	public void the_response_status_code_should_be_ok_and_the_response_body_should_contain_the_message_as_user_is_deleted_usersuccessfully(Integer int1) {
		DeleteUserbyFNResponse.then().statusCode(200);
	}
	
	//Put-update the User by UserID using the PathParams	
	@Given("User creates PUT Request using correct URL endpoint")
	public void user_creates_put_request_using_correct_url_endpoint() throws InvalidFormatException, IOException {
List<Map<String, String>> getUserData= (UserExcelReader.getData(Endpoint.Excelpath2, "Put-ValidData"));
		
		// Iterate over each row of data
		 for (Map<String, String> row : getUserData){
			String firstname = row.get("FirstName");
			String lastname = row.get("LastName");
			String phonenumber = row.get("ContactNumber");
			String emailid = row.get("Email");
			String plotNumber = row.get("PlotNumber");
			String street = row.get("Street");
			String state = row.get("State");
			String country = row.get("Country");
			String zipCode = row.get("ZipCode");
			
			  // Construct JSON body for the request
			String PutuserInfoJson = "{" +
					"\"user_first_name\": \"" + firstname + "\"," +
					"\"user_last_name\": \"" + lastname + "\"," +
					"\"user_contact_number\": \"" + phonenumber + "\"," +
					"\"user_email_id\": \"" + emailid + "\"," +
					"\"userAddress\": {" +
					"\"plotNumber\": \"" + plotNumber + "\"," +
					"\"street\": \"" + street + "\"," +
					"\"state\": \"" + state + "\"," +
					"\"country\": \"" + country + "\"," +
					"\"zipCode\": " + zipCode +
					"}" +
					"}";
			//System.out.println(userInfoJson);
		PutValidDataResponse = RestAssured
				.given()
				.spec(UserReqSpec.Putvaliddata()).body(PutuserInfoJson)
				.pathParam("userId",user_id)
	    		.when()
		    	.put();
		 }
	}

	@When("User sends https request with body containing only mandatory fields-First Name,Updated Last Name,Contact Name,Email Id")
	public void user_sends_https_request_with_body_containing_only_mandatory_fields_first_name_updated_last_name_contact_name_email_id() {
		PutValidDataResponse.then().log().all();
	}

	@Then("User gets {int}:OK status code and response body with updated Last name")
	public void user_gets_ok_status_code_and_response_body_with_updated_last_name(Integer int1) {
		PutValidDataResponse.then().statusCode(200);
	}
	
	
		
	}


