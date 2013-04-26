
/**
 * Class that gives a list of variables (variable names and values)
 * @author Jiaxi Wang
 */

public class VariableList {
	
	///////////////////////// Fields ////////////////////////////
	//fixed default size of the array
	private final int DEFAULT_MAX_Variable = 10;
	//array of variables
	private Variable[] variableList;
	//number of variables in the array
	private int numVariables;
	
	
	
	/////////////////////// Constructors /////////////////////////
	/**
	 * Constructor creates variable array of default size
	 */
	public VariableList ()
	{
		variableList = new Variable[DEFAULT_MAX_Variable];
		numVariables = 0;
	}
	
	
	/**
	 * Constructor creates variable array of specified size
	 */
	public VariableList (int max)
	{
		variableList = new Variable[max];
		numVariables = 0;
	}
	
	
	
	 ////////////////////////// Methods //////////////////////////////
	/**
	 * add method that add a new variable with its value to the array
	 * @param variableName   name of variable
	 * @param variableValue  the int value of the variable
	 */
	public void add(String variableName, int variableValue){
		// create a new variable object
		Variable variable = new Variable(variableName,variableValue );
		
		// if array is not big enough, double its capacity automatically
		if (numVariables == variableList.length)
			expandCapacity();
		
		// add reference to variable at first free spot in array
		variableList[numVariables] = variable;
		
		//increase the number of variables by one
		numVariables++;		
		}

	
	/**
	 * add method that add a new variable with a default value of ? to the array
	 * @param variableName   name of variable
	 * @param variableValue  the int value of the variable
	 */
	public void add(String variableName){
		// if the variable name is the reserved keyword
		if (variableName.equals("v") || variableName.equals("p")|| variableName.equals("e"))
		{
			System.out.println("Warning: "  + variableName + " is a illegal variable name!");
		}
		
		// create a new variable object
		else
		{
		Variable variable = new Variable(variableName, '?');
		
		// if array is not big enough, double its capacity automatically
		if (numVariables == variableList.length)
			expandCapacity();	
		
		// add reference to variable at first free spot in array
		variableList[numVariables] = variable;
		
		//increase the number of variables by one
		numVariables++;		
		}
	}
	
	
	/**
	 * expandCapacity method is a helper method
	 * that creates a new array to store ingredients with twice the capacity
	 * of the existing one
	 */
	private void expandCapacity(){
		//create a new largerList have a double size to the original array
		Variable[] largerList = new Variable[variableList.length * 2];
		
		//copy the variables into the largerList from the orininal array
		for (int i = 0; i < variableList.length; i++)
			largerList[i] = variableList[i];
		
		//relate two array
		variableList = largerList;
	}
	
	
	/**
	 * find method takes input of a name of variable
	 * and returns the value of this variable
	 * @return value of the variable
	 * @param inputName   the name of variable needed to be found
	 */
	public int find(String inputName){
		//a fixed value for NOT_FOUND to check whether the variable is found or not
		final int NOT_FOUND = -1;
		int search = NOT_FOUND;
		int variableValue = 0;	

		// if list is empty, can't find
		if (numVariables == 0){
			System.out.println(" Warning: variableList is empty.");
			return NOT_FOUND;
		}
		// search the list for the specified variable
		for (int i = 0; i < numVariables && search == NOT_FOUND; i ++){
		
			//char name = variableList[i].getName();
			if (variableList[i].getName().equals(inputName))
				search = i;
		}
		
		// if not found, return -1 to indicate not found 
		if (search == NOT_FOUND)
			return NOT_FOUND;
		
		// get the value of the Variable
		variableValue = variableList[search].getValue();
		
		return variableValue;			
	}
	
	
	public void putValue(String inputName, int newValue){
		//a fixed value for NOT_FOUND to check whether the variable is found or not
		final int NOT_FOUND = -1;
		int search = NOT_FOUND;

		// if list is empty, variable is undefined, add a new variable
		if (numVariables == 0){
			System.out.println(" Warning: variableList is empty.");
			this.add(inputName, newValue);	
		}
		// search the list for the specified variable
		for (int i = 0; i < numVariables && search == NOT_FOUND; i ++){
			//char name = variableList[i].getName();
			if (variableList[i].getName().equals(inputName))
				search = i;
		}
		
		// if not found, throw an error 
		if (search == NOT_FOUND)
		{
			this.add(inputName, newValue);
			System.out.println("warning: undefined variable");
		}
		
		// set the value of the Variable
		else
		variableList[search].setVariableValue(newValue);		
	}
	
	
}
