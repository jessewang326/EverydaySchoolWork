import java.io.*;

/**
 * Class that tests both the Ingredient class and ListIngredient class
 * @author Jiaxi Wang
 */
public class TestListIngredient{
  public static void main(String[] args) throws Exception{

    String database = "table.dat";
    String recipe = "pasta.txt";
      
    // add your code here
    // create two ListIngredient object for find method and count method
    ListIngredient ingredientList1 = new ListIngredient();
    ListIngredient ingredientList2 = new ListIngredient();
    
    // read the database
    ingredientList1.readList(database);
    // find the calorieCount for butter
    System.out.println("The calorie count for butter is " + ingredientList1.find("butter"));
    // calculate the total calorieCount for pasta.txt 
    System.out.println("The total calorie count for" + recipe + "is " + ingredientList2.count(recipe));
    
    
    
  }
}
