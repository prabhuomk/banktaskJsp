package implementskey;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;
import proexception.ProException;

public class ContainKey {
	
	public  static void  main(String[] args)
	{
		Properties data =new Properties();
		
		data.setProperty("key","databaselayer.DataBaseLayer");
		
		try
	       {
	       
	       
	       FileWriter writeData = new FileWriter("className.properties");

	       data.store(writeData,"contains value ");
	       System.out.println("success");
	        
	       }
	       catch(IOException e)
	       {
	         
	         System.out.println(e);
	       
	       }
		
		
		
	}

}
