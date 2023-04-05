package request_repository;

import java.io.IOException;
import java.util.ArrayList;

import common_methods.getdata_patch;


public class patch_request_repository 
{
	public static String baseuri()
		{
			String baseuri = "https://reqres.in/";
			return baseuri;
		}
	
	public static String resource()
		{
			String resource = "api/users/2";
			return resource;
		}
	
	public static String patch_request_TC1() throws IOException
		{
		ArrayList<String> data =getdata_patch.getDataExcel("patch_data", "tc3");
		System.out.println(data);
		String Name = data.get(2);
		String Job = data.get(3);
		String requestbody="{\r\n"
				+ "    \"name\": \""+Name+"\",\r\n"
				+ "    \"job\": \""+Job+"\"\r\n"
				+ "}";
		return requestbody;
		
		}
 
	
	
}
