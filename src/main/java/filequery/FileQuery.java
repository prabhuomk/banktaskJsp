package filequery;


import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import accountinfo.AccountInfo;
import customerinfo.CustomerInfo;
import proexception.ProException;
import java.io.BufferedWriter;
import java.io.File;
import java.io.BufferedReader;
import jacksonutility.JacksonUtility;
import maputility.MapUtility;

import java.util.HashMap;
import java.util.Map;

public class FileQuery {
	   	  
	      	  
	      	
	      	 
	      	
	      	 
	      	File file=new File("customerData.txt");
	      
	      	 public Map<Integer,CustomerInfo> readCustomer()throws ProException
	      	 {
	      	
	      	   if(file.exists())
	      		{	
	      		 
	      		   
	      		  try(FileReader data = new FileReader("customerData.txt");BufferedReader buffer = new BufferedReader(data);)
	               {
	      			   
	      			      
	      			      Map<Integer,CustomerInfo> customerMap=new HashMap<Integer,CustomerInfo>();
	      			      
	      			      JSONParser parser = new JSONParser();
	      			      
	      			      String line;
	            	   
	            	      while ((line=buffer.readLine() )!= null)
	            	       {
	            		       if(line.startsWith("#customerData#"))
	            		       {
	            			   
	            			       String value=line.replaceAll("#customerData#","");
	      	         	     
	            			       JSONObject customer=(JSONObject)parser.parse(value);
	            			       
	            			       
	            			      for(Object keys:customer.keySet())
	            			      {
	            			    	  customerMap.put(Integer.parseInt((String)keys),JacksonUtility.fromJsonObject(customer.get(keys).toString()));
	            			      }
	            			      
	            			      return customerMap;
	            		       }
	            	       
	            	        }
         
	             	     return customerMap;
	             	
	               }
	               catch(Exception ex)
	               {
                          throw new ProException(ex);
	            	   
	               }
	      		}
	      	   
	      	   return new HashMap<Integer,CustomerInfo>();
	      	 }
	      	  
	      	
	      	public Map<Integer,Map<Integer,AccountInfo>> readAccount()throws ProException
	      	 {
	      		if(file.exists())
	      		{
	      		
	      		   try(FileReader data = new FileReader("customerData.txt");BufferedReader buffer = new BufferedReader(data);)
	               {
	      			   
	      			       Map<Integer,Map<Integer,AccountInfo>> accountMap=new HashMap<Integer,Map<Integer,AccountInfo>>();
	      			
	      			       JSONParser parser = new JSONParser();
	      			   
	      			       String line;
	            	   
	            	      while ((line=buffer.readLine() )!= null)
	            	       {
	            		       if(line.startsWith("#accountData#"))
	            		       {
	            			   
	            			       String value=line.replaceAll("#accountData#","");
	      	         	     
	            			       JSONObject account=(JSONObject)parser.parse(value);
	            			       
	            			        for(Object keys:account.keySet())
		            			      {
	            			        	  JSONObject innerMap=(JSONObject)account.get(keys);
	            			        	  
	            			        	
	            			        	  
	            			        	  
	            			        	   for(Object innerKey:innerMap.keySet())
	            			        	   {
	            			        		  
	            			        		   
	            			        		   Map<Integer,AccountInfo> subMap=accountMap.get(Integer.parseInt((String)keys));
	            			        		   
	            			        		   
            			        			   
	            			        		   
	            			        		   if(subMap==null)
	            			        		   {
	            			        			   
	            			        			   subMap=new HashMap<Integer,AccountInfo>();
	            			        			   
			            			    	        
			            			    	        accountMap.put(Integer.parseInt((String)keys),subMap);
			            			    	       
	            			        			   
	            			        			  
	            			        		   }   
	            			        			   
	            			        			    subMap.put(Integer.parseInt((String)innerKey),JacksonUtility.fromJsons(innerMap.get(innerKey).toString()));
			            			    	       
		            			    	        
		            			    	        
		            			    	        
		            			    	       
	            			        	   }
		            			      }
	            			             
	            			      return accountMap;
	            		       }
	            	       
	            	        }
        
	             	     return accountMap;
	             	
	               }
	               catch(Exception ex)
	               {

	            	   throw new ProException(ex);
	               }
	      		}
		      	   
		      	   return new HashMap<Integer,Map<Integer,AccountInfo>>();
		      	 }
	      	 
	      	
	      	public Map<String,Integer> readID()throws ProException
	      	 {
	      		
	      		if(file.exists())
	      		{
	      		 try(FileReader data = new FileReader("customerData.txt");BufferedReader buffer = new BufferedReader(data);)
	               {
	      			     
	      			   String line;
	      			   
	      			    Map<String,Integer> idDetailMap=new HashMap<String,Integer>();
	      			      
	      			    JSONParser parser = new JSONParser();
	            	   
	            	      while ((line=buffer.readLine() )!= null)
	            	       {
	            		       if(line.startsWith("#idDetails#"))
	            		       {   
	            		    	   
	            			       String value=line.replaceAll("#idDetails#","");
	            			       
	            			       JSONObject idDetail=(JSONObject)parser.parse(value);
	            			       
	            			      
	            			      
	      	         	     
	            			       for(Object keys:idDetail.keySet())
		            			      {
	            			    	   
	            			    	  idDetailMap.put((String)keys,(int)((long)idDetail.get(keys)));
	            			    	  
	            			          }
	            			      return idDetailMap;
	            		       }
	            	       
	            	        }
        
	             	     return idDetailMap;
	             	
	               }
	               catch(Exception ex)
	               {

	            	   throw new ProException(ex);
	               }
	      		}
	      		
	      		return new HashMap<String,Integer>();
	      	 }
	      	
	      	 
	      	public void write(Map<Integer,CustomerInfo> customerMap,Map<Integer,Map<Integer,AccountInfo>> accountMap,Map<String,Integer> idDetail)throws ProException
	        {

	              	        	  
	              try(FileWriter writeData = new FileWriter("customerData.txt");BufferedWriter buffer = new BufferedWriter(writeData);)
	              {
	            	         
	 	            	     String[] data= {"#customerData#"+ JacksonUtility.toJsonCustomer(customerMap),"#accountData#"+JacksonUtility.toJsonAccount(accountMap),"#idDetails#"+JacksonUtility.toJsonId(idDetail)};
	 	            	    
	 	            	     for(int i=0;i<3;i++)
	 	            	     {
	 	                         buffer.write(data[i]);   
	 	                     
	 	                         buffer.newLine();
	 	            	     }	    
	 	            	     
	 	            	    
	               
	              }
	              catch(IOException e)
	              {

	                    throw new ProException(e);

	              }   

	        }
	      	
	      	
	      	
	      	
	      	public int addCustomer(CustomerInfo customerInfo)throws ProException
	      	{
	      		     Map<String,Integer> idDetail= readID();
	      		     
	      		     Map<Integer,CustomerInfo> customerMap=readCustomer();
	      		     
	      		     Map<Integer,Map<Integer,AccountInfo>> accountMap=readAccount();
	         		     
	      		     if(idDetail.get("customerId")!=null)
	      		     {
	      		    	 
	      		        int customerId=idDetail.get("customerId");
	      		        
	      		        customerInfo.setCustomerId(customerId+1);
	      		       
	      		        MapUtility.addCustomer(customerId+1, customerInfo,customerMap);
	                 
	                    idDetail.put("customerId", customerId+1);
	                    
	                    write(customerMap,accountMap,idDetail);
	                   
	                    return customerId;
	                 
	                   
	      		     }
	      		     else
	      		     {
	      		    	 customerInfo.setCustomerId(1);
	      		    	
	      		    	 MapUtility.addCustomer(1, customerInfo, customerMap);
		                
		                 idDetail.put("customerId",1);
		                 
		                 write(customerMap,accountMap,idDetail);
		               
		                 return 1;

	      		     }
	      		    
	      		  
	                
	          }
	      	
	     
	      	
	      	public int addAccount(AccountInfo accountInfo)throws ProException
	      	{
	      		
	      		Map<String,Integer> idDetail= readID();
     		     
     		     Map<Integer,CustomerInfo> customerMap=readCustomer();
     		     
     		     Map<Integer,Map<Integer,AccountInfo>> accountMap=readAccount();
     		     
	      		 if(idDetail.get("accountId")!=null)
     		     {
     		    	 
     		        int accountId=idDetail.get("accountId");
     		        
     		        accountInfo.setAccountId(accountId+1);
     		        
     		        MapUtility.addAccount(accountId+1,accountInfo,accountMap);
                
                    idDetail.put("accountId",accountId+1);
                   
                    write(customerMap,accountMap,idDetail);
                    
              
                   
                   return accountId;
                
                  
     		     }
     		     else
     		     {
     		    	   accountInfo.setAccountId(1);
     		    	
     		    	   MapUtility.addAccount(1,accountInfo,accountMap);
                    
                       idDetail.put("accountId",1);
	                 
	                    write(customerMap,accountMap,idDetail);
	                    
	                   
	                  
	                    return 1;

     		     }
     		        
	                	
	      	}
	      	
	      public void changeCustomerStatus(int customerId,int status)throws ProException
	  	  {
	    	  Map<String,Integer> idDetail= readID();
  		     
  		      Map<Integer,CustomerInfo> customerMap=readCustomer();
  		     
  		      Map<Integer,Map<Integer,AccountInfo>> accountMap=readAccount();
  		      
  		      MapUtility.changeCustomerStatus(customerId,status,customerMap);
	    	  
	    	  write(customerMap,accountMap,idDetail);
	  		  
	  	  }
	  	  
	  	  public void changeAccountStatus(int customerId,int accountId,int status)throws ProException
	  		{
	  		    Map<String,Integer> idDetail= readID();
 		     
		        Map<Integer,CustomerInfo> customerMap=readCustomer();
		     
		        Map<Integer,Map<Integer,AccountInfo>> accountMap=readAccount();
		      
		        MapUtility.changeAccountStatus(customerId,accountId,status,accountMap);
	  			
	  			write(customerMap,accountMap,idDetail);
	  			
	  		}
	  	  
	  	   public void deposit(int customerId,int accountId,int amount)throws ProException
	  		{
	  			
	  		    Map<String,Integer> idDetail= readID();
  		     
 		        Map<Integer,CustomerInfo> customerMap=readCustomer();
 		     
 		        Map<Integer,Map<Integer,AccountInfo>> accountMap=readAccount();
 		      
 		        MapUtility.deposit(customerId, accountId, amount,customerMap,accountMap);
	  			
	  			write(customerMap,accountMap,idDetail);
	  				
	  		}
	  		
	  		public void withdrawal(int customerId,int accountId,int amount)throws ProException
	  		{
	  			  Map<String,Integer> idDetail= readID();
	  		     
	  		      Map<Integer,CustomerInfo> customerMap=readCustomer();
	  		     
	  		      Map<Integer,Map<Integer,AccountInfo>> accountMap=readAccount();
	  		      
	  		      MapUtility.withdrawal(customerId, accountId, amount,customerMap,accountMap);
	  			
	  			  write(customerMap,accountMap,idDetail);
	  			
	  			
	  			
	  		}
	      	
	      
	          
}
