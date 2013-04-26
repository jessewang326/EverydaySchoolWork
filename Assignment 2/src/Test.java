/**
 * Testing class
 */
public class Test {

	public static void main(String args[])
	{
		// creating a maze by reading the file
		Maze maze = new Maze("maze2.txt");
		// creating a new stack explorator
		Explorator s = new StackExplorator();
		// exploring the maze by the stack explorator
		System.out.println(maze.explore(s));
		// creating a new queue explorator
		Explorator q = new QueueExplorator();
		// exploring the maze by the queue explorator
		System.out.println(maze.explore(q));
	}

}
