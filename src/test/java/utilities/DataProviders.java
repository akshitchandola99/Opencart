package utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class DataProviders {
	
	@DataProvider(name="LoginData") //declaring that this method will be used to provide data
	public String[][] getData() throws IOException
	{
		String path = ".//testdata/LoginData.xlsx"; //Reading excel data from testdata
		
		ExcelUtility xlutil = new ExcelUtility(path);
		
		int totalRows = xlutil.getRowCount("Sheet");	
		int totalCols = xlutil.getCellCount("Sheet",1);
		
		String loginData[][] = new String[totalRows][totalCols]; //Creating a 2-D array to store data from sheet
		
		// Read the data from Excel and store it in a two-dimensional array
		for (int i = 1; i <= totalRows; i++)  // i is for rows, starting from 1 (assuming 0 is header)
		{
		    for (int j = 0; j < totalCols; j++)  // j is for columns
		    {
		        loginData[i - 1][j] = xlutil.getCellData("Sheet", i, j);  // Reading data from Excel
		    }
		}

		return loginData; // Returning the 2D array

	}

}
