package TestRequest;

import java.util.Properties;

import EndPoints.Endpoint;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public class UserReqSpec {

	
static RequestSpecBuilder req = new RequestSpecBuilder();
    static Properties prop;
	
	public static RequestSpecification GetAllUser() {	
		//String BaseURL = prop.getProperty("TestURL");
		req.setBaseUri(Endpoint.BaseURL);
		RequestSpecification res = req.build();
		req.setBasePath(Endpoint.GET_AllUsers);
		req.setContentType(ContentType.JSON);
		  //req.setAccept(ContentType.JSON);
		
		return res;
	}
	
	public static RequestSpecification InvalidGetAllUser() {
		
		//String BaseURL = prop.getProperty("TestURL");
		req.setBaseUri(Endpoint.BaseURL);
		RequestSpecification res = req.build();
		req.setBasePath(Endpoint.NegativeGET_AllUsers);
		req.setContentType(ContentType.JSON);
		  //req.setAccept(ContentType.JSON);
		
		return res;
	}
	
       public static RequestSpecification PostValidData() {
		
		
		req.setBaseUri(Endpoint.BaseURL);
		RequestSpecification res = req.build();
		req.setBasePath(Endpoint.Post_CreateUser);
		req.setContentType(ContentType.JSON);
		return res;
	}
	
       public static RequestSpecification PostINValidData() {
   		
   		
   		req.setBaseUri(Endpoint.BaseURL);
   		RequestSpecification res = req.build();
   		req.setBasePath(Endpoint.Post_CreateUser);
   		req.setContentType(ContentType.JSON);
   		return res;
   	}
       public static RequestSpecification GetUserbyID() {
      		
      		
      		req.setBaseUri(Endpoint.BaseURL);
      		RequestSpecification res = req.build();
      		req.setBasePath(Endpoint.GET_userbyUSER_ID);
      		//req.addPathParams(:{userId});
      		req.setContentType(ContentType.JSON);
      		return res;
      	}
       public static RequestSpecification GET_userbyFN() {
     		
     		
     		req.setBaseUri(Endpoint.BaseURL);
     		RequestSpecification res = req.build();
     		req.setBasePath(Endpoint.GET_userbyFN);
     		req.setContentType(ContentType.JSON);
     		return res;
     	}
       public static RequestSpecification DeleteByUserID() {
    		
    		
    		req.setBaseUri(Endpoint.BaseURL);
    		RequestSpecification res = req.build();
    		req.setBasePath(Endpoint.Delete_UserByuserID);
    		req.setContentType(ContentType.JSON);
    		return res;
    	}
       
       public static RequestSpecification DeleteByName() {
   		
   		
   		req.setBaseUri(Endpoint.BaseURL);
   		RequestSpecification res = req.build();
   		req.setBasePath(Endpoint.Delete_UserByuserFN);
   		req.setContentType(ContentType.JSON);
   		return res;
   	}
       public static RequestSpecification Putvaliddata() {
      		
      		
      		req.setBaseUri(Endpoint.BaseURL);
      		RequestSpecification res = req.build();
      		req.setBasePath(Endpoint.PUT_Updateuser);
      		req.setContentType(ContentType.JSON);
      		return res;
      	}
       
       
}
