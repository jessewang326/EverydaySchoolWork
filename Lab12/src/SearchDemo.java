
import java.util.Arrays;
import javax.swing.JOptionPane;

public class SearchDemo {
   
   public static void main ( String [] args ){
	   int SIZE = 256;
	   int[] list = new int[SIZE];
	   for (int i=0;i<SIZE;i++)
		   list[i] = i+1;
      System.out.println( "  Initial list:\n" );
      print(list);

      String input = JOptionPane.showInputDialog(
         "Enter the value to search in the array: " );

      if ( input == null )
         System.exit( 1 );

      int key = Integer.parseInt( input );

      int id = linearSearch( key, list );
      if ( id == -1)
         System.out.println( "\nNo item found with value " + key + "!\n" );
      else
         System.out.println( "\nFound item, value = " + key + " and id = " + id);

      System.out.println( "\n  Sorted list:\n" );
      Arrays.sort( list );
      print(list);
      id = binarySearch( key, list );
      if ( id == -1 )
         System.out.println( "\nNo item found with value " + key + "!\n" );
      else
         System.out.println( "\nFound item, value = " + key + " and id = " + id);

      System.exit( 0 );
   }
   
   public static void print(int[] list){
      for ( int i = 0; i < list.length; ++i )
         System.out.print( list[i] + " " );
      System.out.println();
   }

   public static int linearSearch ( int key, int [] a ){
	   int c = 0;
	   int k=0;
	   while ( k < a.length-1 && a[k]!=key){
		   k++;
		   c+=2;
	   }
	   System.out.print( "The total number of comparisons " +  c );
	   if ( a[k] == key )
		   return k;
	   else 
		   return -1;
      
   }

   public static int binarySearch ( int key, int[] a ){
      int first = 0, last = a.length-1;
      int c = 0;
	  int id = -1;
	  int mid = 0;
      do 
      {
         mid = (first + last) / 2; 
         
         if ( key < a[mid] ){
        	last = mid - 1;
         }
         else {
        	first = mid + 1;
         }
         c+=3;
      } while ( first <= last && a[mid]!=key);
      System.out.print( "The total number of comparisons " +  c );
      if (a[mid] == key)
    	  return mid;
      else 
    	  return -1;
   }
}
