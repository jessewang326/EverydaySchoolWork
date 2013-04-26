import java.awt.*;
import javax.swing.*;

public class MazePanel extends JPanel  {
	final int SQUARE_SIZE = 20;
	final Color GREY = new Color(200, 200, 200);

	GraphicalObject curr;
	// maybe something here
	// Initializing a new queue of graphical objects
	LinkedQueue<GraphicalObject> graphicalQueue;

	void add(GraphicalObject obj){
		// your code here
		// adding the graphical object into the queue
		graphicalQueue.enqueue(obj);
	}

	MazePanel(int h, int w){
		// you may add something here
		graphicalQueue = new LinkedQueue<GraphicalObject>();
		Dimension g = new Dimension(SQUARE_SIZE*w, SQUARE_SIZE*h);
		setPreferredSize(g);
		setBackground(GREY);
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		// your code here
		// painting all the graphical objects in the queue
		for(int i = 0; i < graphicalQueue.size(); i++)
		{
			curr = graphicalQueue.dequeue();
			curr.draw(g,SQUARE_SIZE);
			graphicalQueue.enqueue(curr);
		}


	}
}