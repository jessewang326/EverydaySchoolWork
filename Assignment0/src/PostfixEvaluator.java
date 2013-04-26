
/**
 *  Class gives an integer evaluator of postfix expressions 
 *  Assumes the operation result are one-digit numbers
 *  and a '?' for error operation result
 *  @author Jiaxi Wang
 */

import java.util.StringTokenizer;

public class PostfixEvaluator
{
  // constant for addition symbol 
  private final char ADD = '+';
  // constant for subtraction symbol
  private final char SUBTRACT = '-';
  // constant for multiplication symbol
  private final char MULTIPLY = '*';
  // constant for division symbol
  private final char DIVIDE = '/';
  // the stack
  private ArrayStack<Integer> stack;

  /**
   * Sets up the evaluator by creating a new stack.
   */
  public PostfixEvaluator()
  {
    stack = new ArrayStack<Integer>();
  }

  /**
   * Evaluates the specified postfix expression. If an operand is
   * encountered, it is pushed onto the stack. If an operator is
   * encountered, two operands are popped, the operation is
   * evaluated, and the result is pushed onto the stack.
   * @param postifx String, a line from the ProgramLines
   * @param variableList, gives the existing variables in the program and its value
   */
  public void evaluate (String postfix, VariableList variableList)
  {
	/**op1 and op2 are two int values for the two poped operands
	*result for the int result value of the operation
	*intValue for the casting from (char)token to int values
	*/
    int op1, op2, result, intValue = 0;
    
    //one letter for the token each time
    char token;
    
    /**index start at the third letter because the first two letters
     *are the variable name and a equals mark, such as "a=", they
     *should not be counted into the operation
     */
    int index = 2;
    
    //the name of the variable is the letter at very beginning of the line
    String variableName = String.valueOf(postfix.charAt(0));
    //string used to cast a char value to int value
    String charToInt;

    
    //process one letter at a time from the third letter to the end
    while (index < postfix.length())
    {	
    	//store each letter to the token
    	token = postfix.charAt(index);
    	
    	/**there are three cases for a token
    	 *operator, an existing variable, a blank, or a int value as a operand
    	 *identity the letter is a operator or not, if yes, do the calculation
    	 */
    	if (isOperator(token))
    	{
    		//test whether the stack is empty or not
    		if(!stack.isEmpty())
    		{	
    			//pop the last operand from the stack and cast it into a int value
    			op2 = (stack.pop()).intValue();
    			//test is there any data left in the stack or not
    			if(!stack.isEmpty())
    			{
    				//pop the last operand from the stack and cast it into a int value
    				op1 = (stack.pop()).intValue();
    				//do the calculation between two operands
    				result = evalSingleOp (token, op1, op2, variableList);
    			}
    			//if there is no operands in the stack, the operation result is 0
    			else
    				result = 0;
    		}
    		//if there is no operands in the stack, the operation result is 0
    		else
    			result = 0;
    		//push the operation result into the stack
    		stack.push (new Integer(result));
    	}
    	
    	
    	/**if the token is not an operator
    	 *it could be an existing variable, a blank, or a int value
    	 *test whether is the letter an existing variable or not
    	 */
    	else if(isVariable(token,variableList))
    	{
    		//if it is an existing variable, store its value as an operands in the stack
    		stack.push (new Integer(variableList.find(String.valueOf(token))));
    	}
    	
    	/**if the token is not an operator and an existing variable
    	 *it could be an a blank or a int value
    	 *test whether is the letter a blank or not to avoid error
    	 */
    	else if(token == ' '){  }
    	
    	
    	//the only case left, the token is a int value and stores it as a operand
    	else
    	{	
    		//cast the char token to a String
    		charToInt = String.valueOf(token);
    		//cast from String to int
    		intValue = Integer.parseInt(charToInt);
    		//push the int value to the stack as a operand
    		stack.push (new Integer(intValue));
    	}
    	//check and process the next letter
    	index++;
    }
    //pop the final result of the operation as a int value
    result = stack.pop();
    //save the new value of the variable
    variableList.putValue(variableName,result);
  }

  /**
   * Determines if the specified token is an operator.
   * @param token String representing a single token
   * @return boolean true if token is operator
   */
  private boolean isOperator (char token)
  {
    return ( token == '+' || token == '-' ||
    		token == '*' || token == '/' );
  }
  
  /**
   * Determines if the specified token is an existing variable
   * @param token
   * @param variableList
   * @return boolean true if token is an existing variable
   */
  private boolean isVariable(char token, VariableList variableList)
  {
	  //a fixed value for NOT_FOUND to check whether the variable is found or not
	  final int NOT_FOUND = -1;
	  int search = NOT_FOUND;
	  //if the variable is found, search will get a value differ to -1
	  search = variableList.find(String.valueOf(token));
	 //return true if the variable is found because search has a new value
	  return (search != NOT_FOUND);
		  
  }
  

  /**
   * Peforms integer evaluation on a single expression consisting of 
   * the specified operator and operands.
   * @param operation operation to be performed
   * @param op1 the first operand
   * @param op2 the second operand
   * @param variableList contains the the existing variable and value
   * @return int value of the expression
   */
  private int evalSingleOp (char operation, int op1, int op2, VariableList variableList)
  {
	  //initialized int result value to 0
	  int result = 0;
	  //if any of the operands with a value of ?, the operation result is ?
	  if(op1 == '?' || op2 == '?')
		  result='?';
	  //if neither of operands is ?, do the calculation
	  else
	  {
		  //check which operation we need to do
		  switch (operation)
		  {
		  //in case of add operation
		  case ADD:
			  //add up two operands and retain the right most digit
			  result = (op1 + op2)%10;
			  break;
		  case SUBTRACT:
			//subtract two operands and retain the right most digit
			  result = (op1 - op2)%10;
			  break;
		  case MULTIPLY:
			//multiply two operands and retain the right most digit
			  result = (op1 * op2)%10;
			  break;
		  case DIVIDE:
		  {
			  //check if the divisor is 0 to avoid error
			  if (op2 != 0)
			  {
				  //if the operation is safe, do the operation
				  result = op1 / op2;
				  //check the result and retain the right most digit
				  if(result != 0)
					  result = result%10;
			  }
			  //if the result is not safe
			  else
			  {
				  //make the result a ?
				  result = '?';
				  //and print out a warning
				  System.out.println("division by zero!");			
			  }
			  break;
		  }
		  }
	  }
	  //return the result
	  return result;
  }
}
