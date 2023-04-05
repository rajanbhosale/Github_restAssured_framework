package test_class;

import org.testng.Assert;
import org.testng.annotations.Test;

import common_methods.common_method_Get_API;
import common_methods.common_method_utilities;
import io.restassured.path.json.JsonPath;
import request_repository.get_request_repository;
import java.io.IOException;

public class get_TC1 {
	@Test
	public static void orchestrator() throws IOException
	
	{
		String responseBody = "";
		int resstatusCode =0;
		String baseURI = get_request_repository.baseuri();
		String response = get_request_repository.resource();
		
		for (int i=0; i<3;i++)
		{
			resstatusCode = common_method_Get_API.responsestatuscode_extractor(baseURI, response);
			
			if(resstatusCode == 200)
			{
				responseBody = common_method_Get_API.responsebody_extractor(baseURI, response);
				response_body_validator(responseBody);
				break;
			}
			else
			{
				System.out.println("Correct Status code not found in iteration :" +i);
			}
		}
		common_method_utilities.evidence_FileCreator("Get_TC1", null, responseBody);
			Assert.assertEquals(resstatusCode,200);		
	}
	public static void response_body_validator(String responseBody)
		{
		JsonPath raj = new JsonPath(responseBody);
		
			int dataarraylength = raj.getInt("data.size()");
			System.out.println("Data array length : " + dataarraylength);
		
			int id[] = {7,8,9,10,11,12};
				String email[] = {"michael.lawson@reqres.in","lindsay.ferguson@reqres.in","tobias.funke@reqres.in","byron.fields@reqres.in","george.edwards@reqres.in","rachel.howell@reqres.in"};
				String firstname[] = {"Michael","Lindsay","Tobias","Byron","George","Rachel"};
				String lastname[] = {"Lawson","Ferguson","Funke","Fields","Edwards","Howell"};
				String avatar[] = {"https://reqres.in/img/faces/7-image.jpg","https://reqres.in/img/faces/8-image.jpg","https://reqres.in/img/faces/9-image.jpg","https://reqres.in/img/faces/10-image.jpg","https://reqres.in/img/faces/11-image.jpg","https://reqres.in/img/faces/12-image.jpg"};

				System.out.println("Data json object elements :");

			for(int i = 0 ; i<dataarraylength ; i++)
				{
					int responseid = raj.getInt("data["+i+"].id");
					String responseemail = raj.getString("data["+i+"].email");
					String responsefirstname = raj.getString("data["+i+"].first_name");
					String responselastname = raj.getString("data["+i+"].last_name");
					String responseavatar = raj.getString("data["+i+"].avatar");
	
					System.out.println(responseid);
					System.out.println(responseemail);
					System.out.println(responsefirstname);
					System.out.println(responselastname);
					System.out.println(responseavatar);
	
					Assert.assertEquals(responseid, id[i]);
					Assert.assertEquals(responseemail, email[i]);
					Assert.assertEquals(responsefirstname, firstname[i]);
					Assert.assertEquals(responselastname, lastname[i]);
					Assert.assertEquals(responseavatar,avatar[i]);
				}
	
		}
}
