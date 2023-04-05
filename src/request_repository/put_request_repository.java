package request_repository;

import java.io.IOException; 
import java.util.ArrayList;


import common_methods.getdata_put;

public class put_request_repository 
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
	
	public static String put_request_TC1() throws IOException
		{
		ArrayList<String> data =getdata_put.getDataExcel("put_data", "tc2");
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
