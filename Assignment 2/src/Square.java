import java.awt.*;
import javax.swing.*;


/**
 * Square class create a square object for maze
 * each square can be a entrance/exit/wall/free square
 * @author JiaxiWang
 */
public class Square {

	/////////////////////// Fields/////////////////////////
	// integer for x coordinate
	protected int x;
	// integer for x coordinate
	protected int y;
	// boolean for the accessibility, true for free, false for wall
	private boolean acce;
	// boolean for whether visited or not
	private boolean visited;
	// boolean for checking the entrance
	private boolean entrance;
	// boolean for checking the exit
	private boolean exit;
	private int orientation;
	// color Red
	final Color RED = new Color(255, 0, 0);


	/////////////////////// Constructors /////////////////////////
	/**
	 * A default constructor
	 * A default Square is an unvisited, free square with a (0,0) coordinate
	 */
	public Square(){
		this.x = 0;
		this.y = 0;
		this.acce = true;
		this.visited = false;
		this.entrance = false;
		this.exit = false;
		this.orientation = -1;
	}
	/**
	 * A constructor create a Square with given coordinate
	 * Creating an unvisited, free square with a (x,y) coordinate
	 * @param x, x coordinate
	 * @param y, y coordinate
	 */
	public Square(int x, int y ){
		this.x = x;
		this.y = y;
		this.acce = true;
		this.visited = false;
		this.entrance = false;
		this.exit = false;
		this.orientation = -1;
	}


	//////////////////////////Methods //////////////////////////////

	// Helper method to get the x coordinate
	//@return an integer for the x coordinate
	public int getX(){
		return x;
	}

	// Helper method to get the y coordinate
	//@return an integer for the y coordinate
	public int getY(){
		return y;
	}

	// Helper method to get the orientation of a Square
	//@return an integer for the orientation
	// 0 for right, 1 for down, 2 for left, 3 for up
	public int getOrientation(){
		return orientation;
	}

	// Helper method to set the orientation of a Square
	//@param an integer for the orientation
	// 0 for right, 1 for down, 2 for left, 3 for up
	public void setOrientation(int orientation){
		this.orientation = orientation;
	}

	// Helper method to check the accessibility of the square
	// @return true for a free square, false for a wall square
	public boolean isAcce(){
		return acce;
	}

	// Helper method to check did we visit the square
	// @return true for visited, false for unvisited
	public boolean isVisited(){
		return visited;
	}
	// Helper method to set the square visited
	public void visit(){
		this.visited = true;
	}
	// Helper method to set the square unvisited
	public void notVisited(){
		this.visited = false;
	}

	// Helper method to set the square to a wall square
	public void isWall(){
		this.acce = false;
	}

	// Helper method to check whether the square is the exit or not
	// @return true for a exit
	public boolean isExit(Square exit){
		if(this.getX() == exit.getX() && this.getY() == exit.getY())
			return true;
		else
			return false;
	}

	// Helper method to set a square the entrance
	public void setEntrance(){
		this.entrance = true;
	}

	// Helper method to set a square the Exit
	public void setExit(){
		this.exit = true;
	}

	// Helper method to print off all the info of a Square
	public String toString(){
		String s = "Coordinate:" + getX() + "," + getY() + ",Accessiblity:" + isAcce() + ",checked: " 
				+ isVisited() + ", orientation:" + getOrientation() +", is entrance:" +  entrance + ",is exit:" +exit;
		return s;
	}

	// Helper method to get the coordinate of a Square
	public String getCoordinate(){
		String s =  getX() + "," + getY() + ";" ;
		return s;
	}
}
