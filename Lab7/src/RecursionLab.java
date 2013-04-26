import java.io.*;

public class RecursionLab{
    
    public static void reversePrint (String inString){
    	
       
		if (inString.length( ) > 0)		// if string is not empty
		{
		
			// your code goes here
			System.out.print(inString.charAt(inString.length() - 1));
			reversePrint(inString.substring(0, inString.length()-1));
		}
		else
			System.out.println();
    }


    public static String reverseString(String inString){
    	

    	if (inString.length( ) == 0)
    	{
    		return inString;
    	}
    	else
    	{
    		//return reverseString(inString.substring(1, inString.length())) + inString.charAt(0);
    		return inString.charAt(inString.length()-1)+ reverseString(inString.substring(0, inString.length()-1));
    	}
    }
    
    public static boolean isPalindrome(String s)
    {
    	if (s.equals(reverseString(s)))
    			return true;
    	else
    			return false;
    }
    
    
    public static void main(String[] args){
        String inString = "abcde";
        String radar = "radar";

		// test reversePrint
		reversePrint(inString);	
		// test reverseString5
		String revString = reverseString(inString);
		System.out.println(revString);
		System.out.println(isPalindrome(radar));
		System.out.println(isPalindrome(inString));
    }
}
