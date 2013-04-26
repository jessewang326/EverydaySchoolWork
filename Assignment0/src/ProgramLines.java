
/**
 * Class that read files line by line
 * stores the lines into array Stacks
 * and process the lines orderly
 * @author Jiaxi Wang
 */

import java.util.StringTokenizer;

public class ProgramLines {
	/////////////////////// Fields /////////////////////////
	//constant for keyword v to declare variables
	private final char VARIABLES = 'v';
	//constant for keyword # to indicate a comment
	private final char COMMENT = '#';
	//constant for keyword p to print a line
	private final char PRINTS = 'p';
	//constant for keyword e to end the program
	private final char END = 'e';
	// A array stack to store lines
	private ArrayStack<String> lines;
	
	
	/////////////////////// Constructors /////////////////////////
	/**
	  * A default constructor
	  */
	public ProgramLines(){
		lines = new ArrayStack<String>();
	}
	
	
	//////////////////////////Methods //////////////////////////////
	
	/**
	    * Method to read the file line by line
	    * and store the lines into a array stack
	    * @param the name of the program file
	    */	
	public void readLines(String program){
		// Create a InStringFile object to read the file
		InStringFile read = new InStringFile(program);
		//Create a temporary ArrayStack to store the lines in a inverse order
		ArrayStack<String> temp = new ArrayStack<String>();
		do{
		// push each lines to the stack
		temp.push(read.read());
		}
		while(!read.endOfFile());
		
		//to store the lines in a correct order
		while(!temp.isEmpty())
		{
		lines.push(temp.pop());
		}
	}
	
	
	/**
	    * Method to process the lines of the code in the ArrayStack
	    * Identify the function of each line by the keyword
	    * at the beginning of each line
	    * and finish the work of each lines accordingly
	    */
	public void processing(){
		
		//the int value of the variable
		int value = 0;
		//the keyword letter ant the beginning of the line
	    char keyword;
	    //a String of the current line
		String tempLine;
		//A string of the token
		String token;
		//Create a StringTokenizer
	    StringTokenizer tokenizer;
	    //Create a variableList to store the variables 
	    VariableList variableList = new VariableList();
		
	    //label for keyword e to end the program
	    outerEnd:
	    //to process line by line to the end of the file
		while(!lines.isEmpty())
		{
			//pop the last line in the stack
			tempLine = lines.pop();
			//print out this line first
			System.out.println(tempLine);
			//store the keyword at the very first of each line
			keyword = tempLine.charAt(0);
			
			

				//Identify the keyword and process the
				//rest code of the line according to the keyword
			switch (keyword)
			{
				
				
				//In case of variable declaration line
				case VARIABLES:
				{
					tokenizer = new StringTokenizer(tempLine);
					//skip the first token of keyword v
					token = tokenizer.nextToken();
					
					//divide the line in to different variables
					//and add them to the variable list
					while (tokenizer.hasMoreTokens())
					{
						variableList.add(tokenizer.nextToken());	
					}
					break;
				}
				
				
		        //In case of the comment line, do nothing
				case COMMENT: { break; }
				
					
				//In case of the prints line
				//print the appropriate strings 
				//and the values of variables or integer constants	
				case PRINTS:
				{	
					tokenizer = new StringTokenizer(tempLine);
					//skip the first token of keyword p
					token = tokenizer.nextToken();
					//divide the lines into different strings
					while (tokenizer.hasMoreTokens())
					{
						token = tokenizer.nextToken();
						
						//print the strings  
						if(token.startsWith("\"") && token.endsWith("\""))
								System.out.print(token.replaceAll("\"",""));
						
						//print the variables or integer constants
						else
						{
							value = variableList.find(token);
							//print ? if it is an existing variable with no value
							if (value == '?')
								System.out.print((char)value + " ");
							
							//print a ? and a warning if it is a undefined variable
							else if (value == -1)
							{
								variableList.add(token);
								System.out.print((char)variableList.find(token) + " ");
								System.out.print("Warning: Undefined variable!");
								
							}
							//print the value of the variable
							else
							System.out.print(variableList.find(token) + " ");
						}	
					}
					//start a new line
					System.out.println();
					break;
				}
				
				
				
				//In case of the end of the program
				//Stop the program by breaking the outerEnd label
				case END: { break outerEnd;}
				
				
				//In the default cases, mostly assignment statements
				default:
				{
					//evaluate the assignment statements by a postfix evaluator
					PostfixEvaluator evaluator = new PostfixEvaluator();
					evaluator.evaluate(tempLine, variableList);		
				}
			}
		}
	}
	    
	   
} 
