
/**
	 * Class that tests Variable class, VariableList class,
	 * PostfixEvaluator class, and ProgramLines class
	 * @author Jiaxi Wang
	 */

public class TestI {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//add the file of code1 to a string
		String code1 = "pgm1.txt";
		//add the file of code2 to a string
		String code2 = "pgm2.txt";
		
		//create two new ProgramLines objects
		ProgramLines program1 = new ProgramLines();
		ProgramLines program2 = new ProgramLines();
		//read the lines of the two files
		//to store the lines in the ArrayStack of two ProgramLines objects
		program1.readLines(code1);
		program2.readLines(code2);
		
		//processing the code in two files
		program1.processing();
		program2.processing();
		
	}

}
