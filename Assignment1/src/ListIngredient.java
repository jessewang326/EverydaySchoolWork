import java.util.*;

/**
 * Class that gives a list of ingredients (ingredients or calorie counts)
 * @author Jiaxi Wang
 */
public class ListIngredient {
	// default size of array
	private final int DEFAULT_MAX_INGREDIENT = 10;
	// array of ingredients (list of ingredients)	
	private Ingredient[] ingredientList;
	// current number of persons in list
	private int numIngredients;



	/**
	 * Constructor creates ingredient array of default size
	 */
	public ListIngredient ()
	{
		ingredientList = new Ingredient[DEFAULT_MAX_INGREDIENT];
		numIngredients = 0;
	}

	/**
	 * Constructor creates person array of specified size
	 * @param max maximum size of array
	 */
	public ListIngredient(int max)
	{
		ingredientList = new Ingredient[max];
		numIngredients = 0;
	}
	
	/**
	 * add method that takes an Ingredient as parameter
	 * and adds it to the list
	 * @param name   name of Ingredient
	 * @param carlorieCount   calorie count
	 */
	public void add(String name, double calorieCount){
		// create a new Ingredient object
		Ingredient ingredient = new Ingredient(name, calorieCount);
		
		// if array is not big enough, double its capacity automatically
		if (numIngredients == ingredientList.length)
			expandCapacity();
		
		//for (int i = 0; i < ingredientList.length; i++){
		// add it to the array of ingredients
		
		// add reference to ingredient at first free spot in array
		ingredientList[numIngredients] = ingredient;
		
		numIngredients++;
		
		}

	/**
	 * expandCapacity method is a helper method
	 * that creates a new array to store ingredients with twice the capacity
	 * of the existing one
	 */
	private void expandCapacity(){
		Ingredient[] largerList = new Ingredient[ingredientList.length * 2];
		
		for (int i = 0; i < ingredientList.length; i++)
			largerList[i] = ingredientList[i];
		
		ingredientList = largerList;
	}
	
	
	/**
	 * find method takes input of a name of ingredient
	 * and returns the calorie count of this ingredient
	 * @return calorieCount of the ingredient
	 * @param inputName  the name of ingredient needed to be found
	 */
	
	public double find(String inputName){
		final int NOT_FOUND = -1;
		int search = NOT_FOUND;
		double count = 0;	

		// if list is empty, can't find
		if (numIngredients == 0){
			throw new Error("ingredientList is empty");
		}
		// search the list for the specified ingredient
		for (int i = 0; i < numIngredients && search == NOT_FOUND; i ++){
			if (ingredientList[i].getName().equals(inputName))
				search = i;
		}
		
		// if not found, throw an error 
		if (search == NOT_FOUND)
			throw new Error("Ingredient not found");
		
		// get the calorie count of the ingredient
		count = ingredientList[search].getCalorieCount();
		
		return count;			
	}
	
	
	
	/**
	 * readList method adds into the university courses list from a file
	 * @param fileName	filename of file that contains course information
	 */
	
	public void readList (String fileName) throws Exception {		  
			
	  // create object that controls file reading and opens the file
	  InStringFile reader = new InStringFile(fileName);
	  System.out.println("\nReading from file " + fileName + "\n");
			  
	  String line;
	  StringTokenizer tokenizer;
	  String ingredientName;
	  double calorieCount;
	
		  do
		  {
			  line = (reader.read());
			  
			  // get ingredient and quantity information
			  // Note: it is assumed that each line of the disk file has
			  // read data from file one line at a time
			  tokenizer = new StringTokenizer(line);
			  ingredientName = tokenizer.nextToken();
			  calorieCount = Double.parseDouble(tokenizer.nextToken());
			  
			  // insert your code here  
			  //create a new ingredient object
			  Ingredient ingredient = new Ingredient(ingredientName, calorieCount);
			  
			  // if array is not big enough, double its capacity automatically
			  if (numIngredients == ingredientList.length)
				  expandCapacity();
			  
			  // add it to the array of ingredients
			  ingredientList[numIngredients] = ingredient;
			  
			  numIngredients++;
	  	
	    }while (!reader.endOfFile()); 
			   
	  reader.close(); 
	}
	
	
	/**
	 * count method reads a list of ingredients from a file
	 * and count of total calorie count of the all ingredients
	 * in the list
	 * @param targetFileName  filename of file that contains ingredients information
	 * @return the total calorie count
	 */		
	public double count(String targetFileName) throws Exception {
		
		// create a new ListIngredient object
		ListIngredient ingredientList1 = new ListIngredient();
		// get calorieCount information from database
		ingredientList1.readList("table.dat");
		// get the list of ingredient
		this.readList(targetFileName);
		  
		double total = 0.0;
		double quantity= 0;  
		double carlorieCount = 0;	  
		String ingredientName;
		  
		// Calculate the total calorieCount of each ingredient
		for(int i = 0; i < numIngredients; i++){
			
			// get the ingredientName from the list
			ingredientName = ingredientList[i].getName();
			// find the calorieCount of ingredient from the database 
			carlorieCount = ingredientList1.find(ingredientName);
			// get the quantity of ingredient from the list  
			quantity = ingredientList[i].getCalorieCount();
			// Add up all the calorieCount of each ingredient 
			total = total + quantity / 100 * carlorieCount;
		  }
		  return total;		 
		}
	
}
