package test_class;

import org.testng.Assert; 
import org.testng.annotations.Test;

import common_methods.common_method_Put_API;
import common_methods.common_method_utilities;
import io.restassured.path.json.JsonPath;
import request_repository.put_request_repository;
import java.io.IOException;

public class put_TC1 {
	@Test
	public static void orchestrator() throws IOException
	
	{
		String responseBody = "";
		int resstatusCode =0;
		String baseURI = put_request_repository.baseuri();
		String request = put_request_repository.put_request_TC1();
		String response = put_request_repository.resource();
		
		for (int i=0; i<8;i++)
		{
			resstatusCode = common_method_Put_API.responsestatuscode_extractor(baseURI, response, request);
			
			if(resstatusCode == 200)
			{
				responseBody = common_method_Put_API.responsebody_extractor(baseURI, response, request);
				response_body_validator(responseBody);
				break;
			}
			else
			{
				System.out.println("Correct Status code not found in iteration :" +i);
			}
		}
		common_method_utilities.evidence_FileCreator("Put_TC1", request, responseBody);
			Assert.assertEquals(resstatusCode,200);		
	}
	public static void response_body_validator(String responseBody)
		{
		JsonPath xyz = new JsonPath(responseBody);
		
		String responseName = xyz.getString("name");
		String responseJob = xyz.getString("job");
		String responseID = xyz.getString("id");
		String responseCreatedAt = xyz.getString("createdAt");
		
		//System.out.println("name:" + responseName + "\njob :" + responseJob + "\n id:" + responseID + "\n createdAt:" + responseCreatedAt);
		
		Assert.assertEquals(responseName,"Prathik");
		Assert.assertEquals(responseJob, "QA");
		Assert.assertNotEquals(responseID,"Assertion error, id parameter is null");
		
		}
	

}
