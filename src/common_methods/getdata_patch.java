package common_methods;

import java.io.FileInputStream;     
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DataFormatter;

public class getdata_patch  {
	
	public static ArrayList<String> getDataExcel(String testSheetName, String testCaseName) throws IOException
	{
		ArrayList<String> arrayData = new ArrayList <String>();
		//step 1 locate excel data file, by creating the object of file input stream
		FileInputStream fis = new FileInputStream("C:\\Users\\Rajan\\Documents\\testdata.xlsx");
		
		//step 2 create the object of XSSFWorkbook to open the excel file
		XSSFWorkbook workbook = new XSSFWorkbook(fis);
		
		//step 3 get/access the desired sheet
		
			//step 3.1 fetch the count of sheets available in the excel file
			int countOfSheet = workbook.getNumberOfSheets();
			
			//step 3.2 fetch the name of sheet and compare against desired sheet name
			for(int i=0; i<countOfSheet; i++)
			{
				String sheetname = workbook.getSheetName(i);
				//System.out.println(sheetname);
				if (sheetname.equalsIgnoreCase(testSheetName))
				{
					//System.out.println(testSheetName);
					
					//step 4 access the sheet and iterate through rows to fetch the column in which testcase column is mentioned. 
					XSSFSheet Sheet = workbook.getSheetAt(i);
					// step 4.1 create iterator for rows.
					Iterator <Row> Rows = Sheet.iterator();
					Row firstRow = Rows.next();
					// step 4.2 create iterator for cells.
					Iterator <Cell> Cells = firstRow.cellIterator();
					int j=0;
					int tc_column=0;
					// step 4.3 read the cell values of row no. 1 to compare against the test case name
					while(Cells.hasNext())
					{
						Cell CellValue = Cells.next();
						if (CellValue.getStringCellValue().equalsIgnoreCase("TCName"))
						{
							tc_column = j;
							//System.out.println(tc_column);
						}
						j++;
					}
					// step 5 fetch the data for designated testcase
					while(Rows.hasNext())
					{
						Row dataRow = Rows.next();
						if (dataRow.getCell(tc_column).getStringCellValue().equalsIgnoreCase(testCaseName))
						{
							Iterator <Cell> dataCellValue = dataRow.cellIterator();
							while(dataCellValue.hasNext())
								
							{
								Cell celldata = dataCellValue.next();
								try
								{
									String testdata = celldata.getStringCellValue();
									System.out.println(testdata);
									arrayData.add(testdata);
								}
								catch (IllegalStateException e)
								{
									int integerTestData = (int) celldata.getNumericCellValue();
									String Stringtestdata = Integer.toString(integerTestData);
									System.out.println(Stringtestdata);
									arrayData.add(Stringtestdata);
								}
																																	
							}
							
						}
						
					}
				
				}
			}				
			workbook.close();
			return arrayData;
	
	}

}
