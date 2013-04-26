

/*
 * A queue implementation of a Explorator class
 * @author Jiaxi Wang
 */
public class QueueExplorator implements Explorator {
	
	
	/////////////////////// Fields/////////////////////////
	// keep track the size of the queue
	private int count;
	// pointer to front and rear of the queue
	private LinearNode<Square> front, rear;
	
	
	/////////////////////// Constructors /////////////////////////
	//default constructor
	public QueueExplorator(){
		this.count = 0;
		this.front = null;
		this.rear = null;
	}

	@Override
	public void clear() {

	}

	@Override
	// check whether the queue is empty or not
	public boolean isEmpty() {
		if ( count ==0)
			return true;
		else
		return false;
	}

	@Override
	// pop out the Square at the front
	public Square getNext() {
		Square result =  front.getElement();
		front = front.getNext();
		count--;
		if (count ==0)
			rear = null;
		return result;
	}

	@Override
	// add a Square at the rear
	public void add(Square s) {
		LinearNode<Square> node = new LinearNode<Square>(s);
		if(isEmpty())
			front = node;
		else
			rear.setNext(node);
		
		rear = node;
		count++;
	}
	

}
