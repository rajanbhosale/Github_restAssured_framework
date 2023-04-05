package common_methods;

import io.restassured.RestAssured;       

import static io.restassured.RestAssured.given;

public class common_method_Get_API {

	public static int responsestatuscode_extractor(String baseuri, String resource)
	{
		RestAssured.baseURI =baseuri;
		
		int responsestatuscode = given().when().get(resource).then().extract().statusCode();
		return responsestatuscode;
		
	}
	
	public static String responsebody_extractor(String baseuri, String resource)
	{
		RestAssured.baseURI = baseuri;
		
		String responsebody= given().when().get(resource).then().extract().response().asString();
		return responsebody;
	}
}