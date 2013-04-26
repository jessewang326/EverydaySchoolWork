
/**
 * 
 * Final Exercise
 * @author Jesse
 *
 */
public class UnorderedListStack<T> implements StackADT<T> {
	//Attribute -----------------------------------------------------
	   
	   private ArrayUnorderedList<T> list; 

	   //-----------------------------------------------------------------
	   //  Creates an empty stack.
	   //-----------------------------------------------------------------
	   public UnorderedListStack()
	   {	
	      list = new ArrayUnorderedList<T>();


	   }
	   
	   //-----------------------------------------------------------------
	   //  Returns true if the stack is empty and false otherwise. 
	   //-----------------------------------------------------------------
	   public boolean isEmpty()
	   {
		   return list.isEmpty();
	   } 

	   //-----------------------------------------------------------------
	   //  Returns the number of elements in the stack.
	   //-----------------------------------------------------------------
	   public int size()
	   {

		   return list.size();
	   }
	 
	   //-----------------------------------------------------------------
	   //  Adds the specified element to the top of the stack.
	   //-----------------------------------------------------------------
	   public void push (T element)
	   {
		   list.addToFront(element); 
	   }
	   //-----------------------------------------------------------------
	   //  Removes the element at the top of the stack and returns a
	   //  reference to it. Throws an EmptyStackException if the stack
	   //  is empty.
	   //-----------------------------------------------------------------
	   public T pop() throws EmptyStackException
	   {
		   if(list.isEmpty())
			   throw new EmptyStackException("stack");
		   
		   return list.removeFirst();
			      
	   }
	   //-----------------------------------------------------------------
	   //  Returns a reference to the element at the top of the stack.
	   //  The element is not removed from the stack.  Throws an
	   //  EmptyStackException if the stack is empty.  
	   //-----------------------------------------------------------------
	   public T peek() throws EmptyStackException
	   {
		   if (list.isEmpty())
			   throw new EmptyStackException("stack");
		   return list.first();

	   }
	   //-----------------------------------------------------------------
	   //  Returns a string representation of the stack. 
	   //-----------------------------------------------------------------
	   public String toString()
	   {
		   return list.toString();
	   }
	}



