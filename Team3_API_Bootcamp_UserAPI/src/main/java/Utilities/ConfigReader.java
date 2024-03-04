package Utilities;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;




public class ConfigReader {

	public static Properties prop;
	public Properties init_prop() throws IOException {
    	
    	 System.out.println("executing LoadProperties.....");
    	 try {
    		
    		/* System.out.println(System.getProperty("user.dir") +
    		 "//src//test//resources//config.properties");*/
    		
    		 prop = new Properties();
    		
    		 FileInputStream ip = new FileInputStream("C:\\Users\\senth\\eclipse-workspace\\Team3_API_Bootcamp_UserAPI\\src\\main\\resources\\UserConfigfile\\UserConfig.properties");
    		 prop.load(ip);
    		
    	 }
         catch(NullPointerException nullPointer) {
  			System.out.println(nullPointer.getCause());
  			System.out.println(nullPointer.getMessage());
  			nullPointer.printStackTrace();
  		}
		
    	
    	 return prop;
    	
    }
}
	
	/*	public static Properties prop =new Properties();
		public static FileReader fr;
	    private Properties properties;
		private static Base_URL configReader;
		//public static webSocket WS = new WebSocket(url, protocols);
		
		
		public static void setup() throws IOException {
			try {
				
			FileReader fr=new FileReader("C:\\Users\\senth\\eclipse-workspace\\Team3_API_Bootcamp_UserAPI\\src\\main\\resources\\Configfile\\config.properties");
	           prop.load(fr);
	           
			}
	          catch(NullPointerException nullPointer) {
	   			System.out.println(nullPointer.getCause());
	   			System.out.println(nullPointer.getMessage());
	   			nullPointer.printStackTrace();
	   		}
			
		}*/






