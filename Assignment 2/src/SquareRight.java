
public class SquareRight extends Square{
	
		  SquareRight(int x, int y){
		    super(x, y);
		  }

		  GraphicalObject step(){
		    return new TriangleRightObject(RED, x, y);
		  }
		}
