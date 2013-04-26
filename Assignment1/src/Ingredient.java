/**
 * Class that creates a database of ingredients 
 * and use it to count the calories in a given recipe
 * @author Jiaxi Wang
 */
public class Ingredient {
	
	/////////////////////// Fields /////////////////////////
	/**
	 * the name of the ingredient
	 */
	private String name;
	/**
	 * the calorie count gives the number of
	 *  calories per 100 gram ingredient
	 */
	private double calorieCount;
	
	/////////////////////// Constructors /////////////////////////
	/**
	  * A constructor  
	  * @param name, gives the desired name of the ingredient
	  * @param calorieCount, gives the desired calorie count of the ingredient
	  */
	public Ingredient(String name, double calorieCount) 
	{
		this.name = name;
		this.calorieCount = calorieCount;
	}
	
	 ////////////////////////// Methods //////////////////////////////////
	/**
     * Method to get the name of the ingredient
     * @return the name of the ingredient
     */	
	public String getName() { return name; }
	 
    /**
     * Method to get the value of the calorie count
     * @return the value of the calorie count
     */	
	public double getCalorieCount() { return calorieCount; }
}
