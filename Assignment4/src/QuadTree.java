/**
 * QuadTree class create a QuadTree data structure for Image Segmentation
 * The root node has four children and no parent
 * A leaf node has no children and 1 parent 
 * An intermediate (non-root, non-leaf) nodes have 4 children and 1 parent
 * @author JiaxiWang
 *
 */
public class QuadTree {

	//////////////////////////// Fields///////////////////////////////
	
	// The image for segmentation
	private MyPicture pic;
	// The root node
	private QuadNode root;
	// The integer of maxLevel to prevent to split the image too much
	private int maxLevel;
	// T color values for homogeneous test
	private double redT;
	private double greenT;
	private double blueT;
	// # of leaf nodes in node queue
	protected int count;


	/////////////////////// Constructors /////////////////////////
	
	/**
	 * the constructor of maze reads the image from files
	 * stores the image as the root node and splits the image into small regions
	 * if it failed the homogeneous test
	 * @param pic, the name of the image file
	 * @param root, a pointer to the root node
	 * @param maxLevel, set a max level for the QuadTree
	 * @param redT, greenT, blueT, color values for the homogeneous test
	 */
	public QuadTree(MyPicture pic, QuadNode root, int maxLevel, 
			double redT,double greenT, double blueT) {
		// set up the image for segmentation
		this.pic = pic;
		// count for leaf node in queue
		count = 0;
		// pointer to root node
		this.root = root;
		// set the max level to input level -1 since it starts from level 0
		this.maxLevel = maxLevel-1;
		// set the T values for homogeneous test
		this.redT = redT;
		this.greenT= greenT;
		this.blueT = blueT;
		// split the image if it failed the homogeneous test
		split(root); 

	}

	///////////////////////////method/////////////////////////////
	
	/**
	 * Split the image or bigger image regions into 4 smaller regions 
	 * if the image failed the the homogeneous test
	 * @param node, the node for splitting
	 */
	public void split(QuadNode node){
		// 4 children nodes split from the parent node
		QuadNode northEast,northWest,southEast,southWest;
		// integer for x, y coordinate, side length of the region and
		//new level for children node
		int x,y,length,newLevel;
		
		
		// if the region failed the  homogeneous test, split it into 4 smaller regions
		if(homogeneous(node))
		{
			// get the upper left coordinate of the parent region
			x=node.getX();
			y=node.getY();
			// get the new side length for the children region
			length=node.getSideLength()/2;
			// get the new level for the children region
			newLevel=node.getLevel() + 1;
			
			// recursively split the regions which failed the test into smaller regions
			node.setNorthWest(northWest = new QuadNode
					(pic, x, y, length, newLevel, pic.simpleStatistics(x, y, length)));
			split(northWest);
			node.setNorthEast(northEast = new QuadNode
					(pic, x+length, y, length, newLevel, pic.simpleStatistics(x+length, y, length)));
			split(northEast);
			node.setSouthWest(southWest = new QuadNode
					(pic, x, y + length, length, newLevel, pic.simpleStatistics(x, y+ length, length)));
			split(southWest);
			node.setSouthEast(southEast = new QuadNode
					(pic, x + length, y + length, length, newLevel, pic.simpleStatistics(x + length, y+ length, length)));
			split(southEast);
		}

	}
	
	
	/**
	 * Homogeneous test for the regions
	 * @param node, the node for testing
	 * @return true, if the node need to be split
	 * @return false, if the node don't need to be split
	 */
	public boolean homogeneous(QuadNode node){
		// return false if the region is uniformly colored
		// if the node reach the max level so that it do not need to be split
		if ((node.getSigmaRed() <= redT && node.getSigmaGreen() <= greenT 
				&& node.getSigmaBlue() <= blueT) || node.getLevel()==maxLevel)
			return false;
		// return true if it need to be split
		else
			return true;
	}
	
	
	/**
	 * Boolean test to check whether the node is leaf node or not
	 * @param node, the node for testing
	 * @return true, if the node is a leaf node without any child node
	 * @return false, if the node is not a leaf node
	 */
	public boolean isLeaf(QuadNode node){
		// if the node do not have any child node, return true
		if (node.getNorthEast() == null)
			return true;
		// else return false
		else
			return false;
	}


	/**
	 * recursively add all the leaf nodes into the node queue in preorder
	 * @param node, the root node of the QuadTree
	 * @param queue, a linked queue to store the leaf nodes
	 * @return queue, a linked queue stores all the leaf nodes of the QuadTree
	 */
	public LinkedQueue<QuadNode> preorder(QuadNode node, LinkedQueue<QuadNode> queue){
		// if the node is not null
		if (node != null){
			// if the node is a leaf node
			if(isLeaf(node))
			{
				// enqueue the node into the queue
				queue.enqueue(node);
				// the count plus one
				count = count + 1;
			}
			// if the node is not leaf node, recursively check its children nodes
			else{
				preorder(node.getNorthWest(), queue);
				preorder(node.getNorthEast(), queue);
				preorder(node.getSouthWest(), queue);
				preorder(node.getSouthEast(), queue);
			}
		}
		// return the queue with all the leaf nodes in
		return queue;
	}

	
	/**
	 * loop through all the nodes in the queue
	 * and paint the segmentation for  the regions of each node
	 * @param pic, the image for image segmentation
	 * @param queue, the queue stores all the leaf nodes
	 */
	public void drawSegmentation(MyPicture pic, LinkedQueue<QuadNode> queue){
		// a temp node dequeue from the linked queue
		QuadNode temp;
		
		// loop through all the leaf nodes in the queue
		for (int i=0; i<count; i++){
			// dequeue each node from the queue
			temp = queue.dequeue();
			// paint the region of the node
			pic.paintSegment(temp.getX(), temp.getY(), temp.getSideLength(), 
					temp.getMeanRed(), temp.getMeanGreen(), temp.getMeanBlue());
			// enqueue the node back to the queue
			queue.enqueue(temp);
		}
	}


	/**
	 * loop through all the nodes in the queue
	 * and paint the white square for  the regions of each node
	 * @param pic, the image for image segmentation
	 * @param queue, the queue stores all the leaf nodes
	 */
	public void paintSquares(MyPicture pic, LinkedQueue<QuadNode> queue){
		QuadNode temp;
		for (int i=0; i<count; i++){
			temp = queue.dequeue();
			pic.drawWhiteSquare(temp.getX(), temp.getY(), temp.getSideLength());
			queue.enqueue(temp);
		}
	}

}