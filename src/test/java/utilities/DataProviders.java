package utilities;
import java.io.IOException;

import org.testng.annotations.DataProvider;

public class DataProviders {
	
	//DataProvider 1
	@DataProvider (name="LoginData")
	public String [][] getData() throws IOException
	{
		String path=".\\testData\\OpenCartLoginData.xlsx";//taking xl file from testData
		ExcelUtils xlutil=new ExcelUtils (path);//creating an object for XLUtility
		
		int totalrows=xlutil.getRowCount("Sheet1"); 
		int totalcols=xlutil.getCellCount("Sheet1", 1);
		
		String logindata[][]=new String[totalrows] [totalcols]; //created for two dimension array which can store
		
		for(int i=1;i<=totalrows; i++) //1 //read the data from xl storing in two dimensional array
		{
			for(int j=0;j<totalcols;j++) //0 i is rows j is columns
			{
				logindata[i-1][j]= xlutil.getCellData("Sheet1", i, j); //1,0
			}
//			logindata[i-1][totalcols] = xlutil.getCellCount("Sheet1", i);
		} 
		return logindata;//returning two dimension array
	}
	
	//DataProvider 2
	
	//DataProvider 3
	
	//DataProvider 4
}