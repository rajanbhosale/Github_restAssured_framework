package common_methods;

import io.restassured.RestAssured; 
   

import static io.restassured.RestAssured.given;

public class common_method_Patch_API {

	public static int responsestatuscode_extractor(String baseuri, String resource,String requestBody)
	{
		RestAssured.baseURI =baseuri;
		
		int responsestatuscode = given().header("Content-Type","application/json").body(requestBody)
				.when().patch(resource).then().extract().statusCode();
		return responsestatuscode;
		
	}
	
	public static String responsebody_extractor(String baseuri, String resource, String requestBody)
	{
		RestAssured.baseURI = baseuri;
		
		String responsebody= given().header("Content-Type","application/json").body(requestBody)
				.when().patch(resource).then().extract().response().asString();
		return responsebody;
	}
}