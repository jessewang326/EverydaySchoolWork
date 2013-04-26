import java.io.*;

public class ExceptionExample11 {

   public static void main (String[] args) throws Exception {

      /* 
         - this handles the NumberFormatException

      */

      BufferedReader keyboard=
         new BufferedReader (new InputStreamReader(System.in),1);
      	int value = -1;
      
      while(value == -1){
      System.out.print("Enter an integer: ");
      String userTyped = keyboard.readLine();

      try {
         value = Integer.parseInt(userTyped);
      }
      catch (NumberFormatException e) {
         System.out.println("Hey, " + e.getMessage() + " is not an integer!");
      }
      }

   }
}