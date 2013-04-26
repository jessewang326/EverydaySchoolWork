
/*
 * a Stack implementation of a Explorator class
 * @author Jiaxi Wang
 */

public class StackExplorator implements Explorator {
	
	/////////////////////// Fields/////////////////////////
	// keep track the size of the stack
	private int count;  
	// pointer to top of stack 
	private LinearNode<Square> top; 

	/////////////////////// Constructors /////////////////////////
	//default constructor
	public StackExplorator()
	{
		count = 0;
		top = null;
	}

	//////////////////////////Methods //////////////////////////////////
	@Override
	public void clear() {

	}

	@Override
	// check whether the stack is empty or not
	public boolean isEmpty() {
		if (count==0)
			return true;
		else
			return false;
	}

	@Override
	// pop the next Square in the stack
	public Square getNext() {
		Square result = top.getElement();
		top = top.getNext();
		count--;

		return result;
	}

	@Override
	// push a Square into the stack
	public void add(Square s) {
		LinearNode<Square> temp = new LinearNode<Square> (s);

		temp.setNext(top);
		top = temp;

		count++;

	}

}
