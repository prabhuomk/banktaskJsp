package utility;

import java.util.Map;
import java.util.List;
import java.util.Properties;
import proexception.ProException;


public class Utility{


// Validator for Object

public static void validatorForObject(Object inputObject)throws ProException
{

               if(inputObject==null)
               {
                   throw new ProException("Null is not expected");

               }

}

//validator for string in terms of null and empty

public static void validatorForStrings(String inputString) throws ProException

{

    if(inputString == null|| inputString.isEmpty()==true)
    {
    
       throw new ProException("null is not excepted");
    
    }
   

}




// Validator for Index range in List,string

public static void indexRangeValidator(int lengthOrSize,int indexValue)throws ProException
{

            if(indexValue>=lengthOrSize|| indexValue<0)
            {
            
                  throw new ProException("Index Value is greater than size of the List");
                  
            }
    
      

}

//start and end index validator in List ,StringBuilder

public static void RangeValidatorBetweenIndex(int startIndex, int endIndex)throws ProException
{
       
       if(startIndex>endIndex || startIndex<0 || endIndex<0)
       {
       
            throw new ProException("Start number and end Number are not clear");
            
       }


}







// checker for Stringbuilder length vs Specified length mentioned


public static void lengthChecker(int stringLength,int specifiedLength)throws ProException
{

       if(stringLength<specifiedLength)
       {
       
             throw new ProException("String length should be greater or equal to the specified length mentioned");
             
       }

}


















}
