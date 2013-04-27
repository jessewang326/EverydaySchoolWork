/**
 * Class
 * @author Jiaxi Wang
 */
public class Variable {
	/////////////////////// Fields /////////////////////////
	/**
	 * the name£¨ a letter or a numerical constant)
	 * of the the variable 
	 */
	private String name;

	/**
	 * the integer value of the variable
	 */
	private int value;
	
	/////////////////////// Constructors /////////////////////////
	/**
	  * A default constructor set the
	  * default value of the variable to '?'
	  * @param name, gives the name of the variable 
	  */
	public Variable(String name)
	{
		this.name = name;
		this.value = '?';
	}
	
	/**
	  * A constructor  
	  * @param name, gives the desired name of the variable
	  * @param value, gives the desired integer value of the variable
	  */
	public Variable(String name, int value) 
	{
		this.name = name;
		this.value = value;
	}
	
	 ////////////////////////// Methods //////////////////////////////////
	/**
    * Method to get the name of the variable
    * @return the name of the variable
    */	
	public String getName() 
	{ 
		return name; 
	}
	 
   /**
    * Method to get the value of the variable
    * @return the value of the variable
    */	
	public int getValue() 
	{ 
		return value; 
	}
	
	/**
	    * Method to set the value of the variable
	    * @param the new value of the variable
	    */
	public void setVariableValue(int newValue)
	{
		this.value  = newValue;
	}
}
