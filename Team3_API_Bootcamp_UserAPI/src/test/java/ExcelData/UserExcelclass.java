package ExcelData;

/*import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import EndPoints.Endpoint;
import Utilities.UserExcelReader;


public class UserExcelclass {
	
	static UserExcelReader ER = new UserExcelReader();  
	//public static String userpassword;
	//public static  String mailId;
	//public static LinkedHashMap<String, String> ExcelDataMap;
	
	public static String FirstName1;
	public static String LastName1;
	public static String ContactNumber1;
	public static String Email1;
	public static String PlotNumber1;
	public static String Street1;
	public static String State1;
	public static String Country1;
	public static String ZipCode1;
	
	
	/*public static HashMap<String, Object> UserData(String Sheetname, Integer rownumber) throws InvalidFormatException, IOException
	{
		
		 HashMap<String,Object>map = new HashMap<String,Object>(); 
		 
		List<Map<String, String>>data = ER.getData(Endpoint.Excelpath, Sheetname);
		userpassword =  (String) map.put("password",data.get(rownumber).get("password") );
		mailId= (String) map.put("userLoginEmail", data.get(rownumber).get("userLoginEmail") );
		
		return map;
	}
		
	public static HashMap<String, Object> Userinfo(String Sheetname, Integer rownumber) throws InvalidFormatException, IOException{
		HashMap<String,Object>map = new HashMap<String,Object>();
	    List<Map<String, String>> formDataList = UserExcelReader.getData(Endpoint.Excelpath2, Sheetname);
		for (Map<String, String> formData : formDataList) {
             
             FirstName1 = formData.get("FirstName");
             LastName1 = formData.get("LastName");
             ContactNumber1 = formData.get("ContactNumber");
             Email1 = formData.get("Email");
             PlotNumber1 = formData.get("PlotNumber");
             Street1 = formData.get("Street"); 
             State1 = formData.get("State");
             Country1 = formData.get("Country");
             ZipCode1 = formData.get("ZipCode");
             
             String UserinfoJson = "{" +
                     "\"FirstName\": \"" + FirstName1 + "\"," +
                     "\"LastName\": \"" + LastName1 + "\"," +
                     "\"ContactNumber\": \"" + ContactNumber1 + "\"," +
                     "\"Email\": \"" + Email1 + "\"," +
                     "\"PlotNumber\": \"" + PlotNumber1 + "\"," +
                     "\"Street\": \"" + Street1 + "\"," +
                     "\"State\": \"" + State1 + "\"" +
                     "\"Country\": \"" + Country1 + "\"" +
                     "\"ZipCode\": \"" + ZipCode1 + "\"" +
                     "}";
			
           	
	}
		 return UserinfoJson;
	}
}*/
	
	
             


