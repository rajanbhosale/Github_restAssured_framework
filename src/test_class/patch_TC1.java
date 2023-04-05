package test_class;

import org.testng.Assert;
import org.testng.annotations.Test;

import common_methods.common_method_Patch_API;
import common_methods.common_method_utilities;
import io.restassured.path.json.JsonPath;
import request_repository.patch_request_repository;
import java.io.IOException;

public class patch_TC1 {
	@Test
	public static void orchestrator() throws IOException
	
	{
		String responseBody = "";
		int resstatusCode =0;
		String baseURI = patch_request_repository.baseuri();
		String request = patch_request_repository.patch_request_TC1();
		String response = patch_request_repository.resource();
		
		for (int i=0; i<7;i++)
		{
			resstatusCode = common_method_Patch_API.responsestatuscode_extractor(baseURI, response, request);
			
			if(resstatusCode == 200)
			{
				responseBody = common_method_Patch_API.responsebody_extractor(baseURI, response, request);
				response_body_validator(responseBody);
				break;
			}
			else
			{
				System.out.println("Correct Status code not found in iteration :" +i);
			}
		}
		common_method_utilities.evidence_FileCreator("Patch_TC1", request, responseBody);
			Assert.assertEquals(resstatusCode,200);		
	}
	public static void response_body_validator(String responseBody)
		{
		JsonPath abc = new JsonPath(responseBody);
		
		String responseName = abc.getString("name");
		String responseJob = abc.getString("job");
		String responseID = abc.getString("id");
		String responseCreatedAt = abc.getString("createdAt");
		
		//System.out.println("name:" + responseName + "\njob :" + responseJob + "\n id:" + responseID + "\n createdAt:" + responseCreatedAt);
		
		Assert.assertEquals(responseName,"rajan");
		Assert.assertEquals(responseJob, "QALead");
		Assert.assertNotEquals(responseID,"Assertion error, id parameter is null");
		
		}
	

}
