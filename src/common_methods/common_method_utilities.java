package common_methods;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class common_method_utilities {
	
	public static void evidence_FileCreator(String filename, String request, String response) throws IOException
	{
		File newTextFile = new File ("D:\\restassured evidence\\" + filename +".txt");
		if(newTextFile.createNewFile())
		{
			FileWriter dataWriter= new FileWriter(newTextFile);
			dataWriter.write("Request body is :" + request + "\n\n");
			dataWriter.write("Response body is:" + response );
			dataWriter.close();
			System.out.println("Request and response body saved in :" + newTextFile.getName());
		}
		else
		{
			System.out.println(newTextFile.getName() + " file already exists. Please take backup of the same.");
		}
	}
	
}
