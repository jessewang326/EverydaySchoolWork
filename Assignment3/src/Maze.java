import java.awt.*;

import javax.swing.*;

/**
 * Maze class create a maze by entrance/exit/free/wall squares
 * @author JiaxiWang
 *
 */

public class Maze {

	/////////////////////// Fields/////////////////////////

	// maze object is a 2D array of Square objects
	private Square[][] maze;
	// there is a specific Entrance Square
	private Square entrance;
	// there is a specific Exit Square
	private Square exit;
	// the height and width of the maze
	private int height;
	private int width;


	/////////////////////// Constructors /////////////////////////

	/**
	 * the constructor of maze reads the maze info from a *.txt file
	 * stores and Entrance, Exit, Free, Wall Squares in a 2D Square array
	 * @param the name of the maze file
	 */	
	public Maze(String mazeFile){
		// Integers for x, y coordinates of the Squares
		int x = 0;
		int y = 0;
		// String for the current line of the *.txt file
		String line;
		// A reader reading the file
		InStringFile reader = new InStringFile(mazeFile);
		System.out.println("Prof. Schost asked me to add this note to clarify that: " +
				"there are some differences from the my algorithms and the online algorithms, " +
				"but he will accept it. Thank you!");
		System.out.println("\nReading from file " + mazeFile + "\n");

		// reading the file to create the maze
		// reading the first line to get the height of the maze
		line = (reader.read());
		y = Integer.parseInt(line);
		// reading the next line to get the width of the maze
		line = (reader.read());
		x = Integer.parseInt(line);
		// creating a 2D array with x in width and y in height
		maze = new Square[x][y];

		// reading the file to find out the entrance
		// reading the next line to get the y coordinate of the entrance
		line = (reader.read());
		y = Integer.parseInt(line);
		// reading the next line to get the x coordinate of the entrance
		line = (reader.read());
		x = Integer.parseInt(line);
		// creating the a Square 
		entrance = new Square(x,y);
		// set the Square to a Entrance
		entrance.setEntrance();

		//reading the file to find out the exit
		// reading the next line to get the y coordinate of the exit
		line = (reader.read());
		y = Integer.parseInt(line);
		// reading the next line to get the x coordinate of the exit
		line = (reader.read());
		x = Integer.parseInt(line);
		// creating the a Square 
		exit = new Square(x,y);
		// set the Square to a Exit
		exit.setExit();

		//loop through the 2D array from maze[0][0]
		x = 0;
		y = 0;
		do
		{
			x = 0;
			line = (reader.read());

			//loop through all the Squares
			while (!line.isEmpty()){
				maze[x][y] = new Square(x,y);
				if( line.charAt(0) == '0')
					//set the square to a wall
					maze[x][y].isWall();
				// length of the line decrease for 1
				line = line.substring(1,line.length());
				// move to next position
				x++;
			}
			//move to next line of the file and next line of the array
			y++;
		}	
		while (!reader.endOfFile()); 

		// Set the width of the maze to x
		width = x;
		// Set the height of the maze to y
		height = y;
		System.out.println("Width is " + x +", height is " + y);
		reader.close(); 
		System.out.println("Entrance: " + entrance.toString());
		System.out.println("Exit: " + exit.toString());
	}

	//////////////////////////Methods //////////////////////////////////
	/**
	 * Explore each Squares one by one starts from entrance
	 * check whether it is a free square or a wall
	 * push the free neighbor squares to the Explorator
	 * check all the free squares till find out the exit
	 * @param Explorator used to store free squares
	 * @return boolean true if exit is found
	 */
	public boolean explore(Explorator e){
		// Creating a new MazeFrame for the Maze
		MazeFrame frame = new MazeFrame(this);
		// current Square
		Square curr = null;
		// color Red
		Color red = new Color(255, 0, 0);


		// Adding the entrance to the Explorator
		e.add(entrance);



		// While there are Squares in the Explorator
		while (!e.isEmpty()){
			curr = e.getNext();
			//check visited or not
			//if not, check it and its neighbors
			if(!curr.isVisited())
			{
			curr.visit();
			pause(100);

			// drawing the triangles
			//@param an integer for the orientation
			// 0 for right, 1 for down, 2 for left, 3 for up
			if (!(curr.getX() == entrance.getX() && curr.getY() == entrance.getY())){
				// drawing an right triangle
				if (curr.getOrientation() == 0)
					frame.add(new TriangleRightObject(red, curr.getY(), curr.getX()));
				// drawing an down triangle
				if (curr.getOrientation() == 1)
					frame.add(new TriangleDownObject(red, curr.getY(), curr.getX()));
				// drawing an left triangle
				if (curr.getOrientation() == 2)
					frame.add(new TriangleLeftObject(red, curr.getY(), curr.getX()));
				// drawing an up triangle
				if (curr.getOrientation() == 3)
					frame.add(new TriangleUpObject(red, curr.getY(), curr.getX()));
			}

			// checking the square is the exit or not
			if(curr.isExit(exit))
			{
				//if it is the exit, reset all the squares
				//and return true
				for(int x = 0; x < maze.length; x++){
					for(int y = 0; y < maze[x].length; y++ )
					{
						//reset all squares to unvisited
						maze[x][y].notVisited();
						//reset all squares to no orientation
						maze[x][y].setOrientation(-1);
						entrance.notVisited();
					}
				}
				//return its the exit, and end the method
				return true;
			}


			// adding free neighbors
			int x = curr.getX();
			int y = curr.getY();
			
			// adding the free neighbor at up side
			if( y-1 >=0 && maze[x][y-1].isAcce() && !maze[x][y-1].isVisited()){
				e.add(maze[x][y-1]);
				maze[x][y-1].setOrientation(3);
			}
			// adding the free neighbor at left side
			if(x-1 >=0 && maze[x-1][y].isAcce() && !maze[x-1][y].isVisited()){
				e.add(maze[x-1][y]);
				maze[x-1][y].setOrientation(2);
			}

			// adding the free neighbor at down side
			if(y+1 <= maze[x].length-1 && maze[x][y+1].isAcce() && !maze[x][y+1].isVisited()){
				e.add(maze[x][y+1]);
				maze[x][y+1].setOrientation(1);
			}
			// adding the free neighbor at right side
			if(x+1 <= maze.length-1 && maze[x+1][y].isAcce() && !maze[x+1][y].isVisited()){
				e.add(maze[x+1][y]);
				maze[x+1][y].setOrientation(0);
			}
		}
		}
		// return false if did not find the exit
		return false;
	}


	////////////////////////// Helper Methods //////////////////////////////////

	//@return integer for the height
	public int getHeight(){
		return height;
	}

	//@return integer for the width
	public int getWidth(){
		return width;
	}

	//@return integer for the x coordinate of the entrance
	public int getEntranceX(){
		return entrance.getX();
	}

	//@return integer for the y coordinate of the entrance
	public int getEntranceY(){
		return entrance.getY();
	}

	//@return integer for the x coordinate of the exit
	public int getExitX(){
		return exit.getX();
	}

	//@return integer for the y coordinate of the exit
	public int getExitY(){
		return exit.getY();
	}

	//check whether the square is free or not
	//@return true if the square is free, false if the square is a wall
	public boolean isFree(int x, int y){
		return maze[x][y].isAcce();
	}

	//pause the drawing step
	static void pause (int i) {
		try {
			Thread.sleep(i);
		} catch (InterruptedException e) { }
	}
}

