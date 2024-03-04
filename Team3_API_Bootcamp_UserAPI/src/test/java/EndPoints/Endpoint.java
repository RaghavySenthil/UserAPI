package EndPoints;
import Utilities.ConfigReader;

public class Endpoint extends ConfigReader{
	
	public static String BaseURL = ("https://userapi-8877aadaae71.herokuapp.com/uap");
	/*public static String username = prop.getProperty("username");
	public static String Password = prop.getProperty("Password");*/
	public static String Post_CreateUser = "/createusers";
	public static String GET_AllUsers = "/users";
	public static String NegativeGET_AllUsers = "/users1";
	public static String GET_userbyUSER_ID = "/user/{userId}";
	public static String GET_userbyFN = "users/username/{userFirstName}";
	public static String PUT_Updateuser = "updateuser/{userId}";
	public static String Delete_UserByuserFN = "/deleteuser/username/{userfirstname}";
	public static String Delete_UserByuserID = "/deleteuser/{userId}";
	//public static String Excelpath3 ="./src/main/resources/Test_Data/Put-ValidData.xlsx";	
	public static String Excelpath2 = "./src/main/resources/Test_Data/ValidData.xlsx";
	}
