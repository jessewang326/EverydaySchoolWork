import java.awt.*;
import javax.swing.*;

public class MazeFrame extends JFrame{
	final static Color BLACK = new Color(0, 0, 0);
	final static Color WHITE = new Color(255, 255, 255);
	final static Color RED = new Color(255, 0, 0);
	final static Color GREEN = new Color(0, 255, 0);
	final static Color BLUE = new Color(0, 0, 255);

	private MazePanel panel;

	void add(GraphicalObject obj){
		panel.add(obj);
		repaint();
	}

	MazeFrame(Maze maze){
		super("Maze");  

		int h = maze.getHeight();
		int w = maze.getWidth();

		panel = new MazePanel(h, w);  
		add(panel);  

		// your code here for drawing the maze
		//adding the free and wall square to the graphical queue to draw the maze
		for (int x = 0; x < maze.getWidth(); x++){
			for (int y = 0; y < maze.getHeight(); y++){
				if (maze.isFree(x, y))
					panel.add(new SquareObject(WHITE, y, x));
				else
					panel.add(new SquareObject(BLACK, y, x));
			}
		}

		// adding the entrance and exit square to the graphical queue
		this.add(new CircleObject(GREEN, maze.getEntranceY(), maze.getEntranceX()));
		this.add(new CircleObject(BLUE, maze.getExitY(), maze.getExitX()));


		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
		pack();
		setResizable(false);
		setVisible(true);
		repaint();
	}
}