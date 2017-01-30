/** The class BinarySearchTree is a framework of functions for building and 
 *  manipulating binary search tree using node representation. 
 *  
 *  (Note: Although this project only requires a tree of integers, <T> was 
 *  used for the purpose of potential future code re-use).
 * 
 * @author J. Donald McCarthy
 *
 * @param <T> A generic data type for the data in the nodes of this tree.
 * 			  Comparability of T is enforced by extending Comparable.
 * 
 * 			  
 */
/**
 * @author james_Local
 *
 * @param <T>
 */
public class BinarySearchTree<T extends Comparable<? super T>> {

	private BinaryNode<T> root; // the root node of the entire tree
	
	
	/*A single constructor that initializes this tree's root to null.
	 * other constructors are not necessary for this program*/
	public BinarySearchTree() {
		root = null;
	}
	
	/* Assigns a new value to "root" if "root" is null or calls the recursive
	 * add method otherwise. Additionally returns the given value if the value
	 * could not be successfully added. */
	public T add(T val) {
		
		T addedVal = null; //a generic variable set to null
		
		//choosing to add recursively or add directly to root
		if (root != null) 
			addedVal = addRecursively(root, val);
		else
			root = new BinaryNode<T>(val);
		
		
		return addedVal; //if value is added successfully returns null
	}
	
	/* Adds a value to the tree in accordance to the rules of a binary search
	 * tree (left < root < right) using recursion to place the value in the
	 * appropriate position. */
	private T addRecursively(BinaryNode<T> rootNode, T val) {
		
		//a variable that will remain null in the case of successful add
		T addedVal = null; 
		
		//checking if the given value is a duplicate
		if (val.compareTo(rootNode.getData()) == 0) {
			rootNode.setData(val); //replace with found duplicate
			addedVal = val; //set return variable to duplicate value
			
	    //checking if the given value is less than the root value
		} else if (val.compareTo(rootNode.getData()) > 0) {
			if (rootNode.hasRightChild())
				//calling this method recursively on the right branch
				addedVal = addRecursively(rootNode.getRightChild(), val);
			else
				//creating a new node on right branch if possible
				rootNode.setRightChild(new BinaryNode<T>(val));

		//default to the node having data value less than given value
		} else {
			
			//creating a new node on left branch of current node or traversing
			//until a possible
			if (rootNode.hasLeftChild())
				addedVal = addRecursively(rootNode.getLeftChild(), val);
			else
				rootNode.setLeftChild(new BinaryNode<T>(val));
		}
	
		return addedVal; //bounce duplicate value to be handled elsewhere
	}

	//calls the recursive delete method and returns oldRoot if root removed
	public T delete(T val) {
		BinaryNode<T> oldRoot = new BinaryNode<T>(null); 
		
		root = recursiveDelete(root, val, oldRoot);
		
		return oldRoot.getData();
	}
	
	public BinaryNode<T> recursiveDelete(BinaryNode<T> rootNode, T val, 
										               BinaryNode<T> oldRoot) {
		//checking if tree is not empty
		if (rootNode != null) {
			//checking if given value matches value in root
			if (val.equals(rootNode.getData())) {
				oldRoot.setData(rootNode.getData());
				rootNode = deleteRoot(rootNode);

			//traversing right subtree recursively if given value is larger
			} else if (val.compareTo(rootNode.getData()) > 0) {
				rootNode.setRightChild(
					 recursiveDelete(rootNode.getRightChild(), val, oldRoot));
			//otherwise traversing left subtree recursively
			} else {
				rootNode.setLeftChild(
					  recursiveDelete(rootNode.getLeftChild(), val, oldRoot));
			}
		}
		
		return rootNode;
	}
	
	//Given a node, removes its data and returns the new root root
	public BinaryNode<T> deleteRoot(BinaryNode<T> rootNode) {
		
		//checking if the node has two children
		if(rootNode.hasLeftChild() && rootNode.hasRightChild()) {
			
			//finding the max value in the left subtree
			rootNode.setData(getRightmost(rootNode.getLeftChild()).getData());
			
			//replacing the data in the root
			rootNode.setLeftChild(deleteRightmost(rootNode.getLeftChild()));
		} 
		
		//checking if the node has only one child
		else if (rootNode.hasLeftChild()) 
			rootNode = rootNode.getLeftChild(); //swapping with left child
		else 
			rootNode = rootNode.getRightChild(); //swapping with right child
		
		return rootNode;
	}
	
	//returns the data value of the node that is the inorder predecessor to the
	//node containing the given value
	public T getPredecessorValue(T val) {	
		BinaryNode<T> predecessor = findPredecessor(root, val);
		
		if(predecessor != null)
			return predecessor.getData();
		else return null;
	}
	
	//returns the data value of the node that is the inorder successor to the
	//node containing the given value
	public T getSuccessorValue(T val) {
		BinaryNode<T> successor = findSuccessor(root, val);
		
		if(successor != null)
			return successor.getData();
		else return null;
	}
	
	/**	Finds the inorder predecessor of a node with the given value.
	 * 
	 * @param rootNode	the current root
	 * @param val	the value to find the predecessor of
	 * @return	the inorder predecessor node of the node with the given value
	 */
	private BinaryNode<T> findPredecessor(BinaryNode<T> rootNode, T val) {
		BinaryNode<T> ancestor = null;
		BinaryNode<T> predecessor = rootNode;
		
		//continuing loop until a node is set to null or has its data set
		//to the given value
		while(predecessor != null && !val.equals(predecessor.getData())) {
			//traversing the left subtree
			if(val.compareTo(predecessor.getData()) > 0) {
				ancestor = predecessor;
				predecessor = predecessor.getRightChild();
			//traversing the right subtree
			} else
				predecessor = predecessor.getLeftChild();
		}
		//returning null if value is not in tree
		if(predecessor == null) return null;
		
		//handling the case where node has no left child
		if (predecessor.getLeftChild() == null) return ancestor;
		
		//otherwise traversing the left subtree for largest value
		return getRightmost(predecessor.getLeftChild());
	}
	

	/** Finds the inorder successor of a node with the given value
	 * @param rootNode	the current root
	 * @param val	the value to find the successor of
	 * @return	the inorder successor node of the node with the given value
	 */
	private BinaryNode<T> findSuccessor(BinaryNode<T> rootNode, T val) 
	{
		BinaryNode<T> ancestor = null; 
		BinaryNode<T> successor = rootNode;
			
		//continuing loop until a node is set to null or has its data set
		//to the given value
		while ( successor != null && !val.equals(successor.getData())) {
				
			//traverse the left subtree
			if (val.compareTo(successor.getData()) < 0){
				ancestor = successor; //keeping track of ancestor node
				successor = successor.getLeftChild();
			//traverse the right subtree
			} else 
				successor = successor.getRightChild();
		}
			
		//returning null if value is not in tree
		if (successor == null) return null;
			
		//handling the case where node has no right child
		if (successor.getRightChild() == null) return ancestor;
			
		//otherwise traversing the right subtree for smallest value
		return getLeftmost(successor.getRightChild());
	}
	
	//traverses down a branch continuing to pass from left to left
	//until a node without a left child is reached and returned
	private BinaryNode<T> getLeftmost(BinaryNode<T> rootNode) {
		if (rootNode.hasLeftChild())
			rootNode = getLeftmost(rootNode.getLeftChild());
		
		return rootNode;
	}
	
	//traverses down a branch continuing to pass from right to right
	//until a node without a right child is reached and returned
	private BinaryNode<T> getRightmost(BinaryNode<T> rootNode) {
		if (rootNode.hasRightChild())
			rootNode = getRightmost(rootNode.getRightChild());
		
		return rootNode;
	}
	

	private BinaryNode<T> deleteRightmost(BinaryNode<T> root) {
		if (root.hasRightChild()) 
			root.setRightChild(deleteRightmost(root.getRightChild()));
		else
			root = root.getLeftChild();
		return root;
		
	}
	

	//calls the recursive string builder method and returns finalized string
	//will return a string of tree data in preorder sorting
	public String getPreorderString() {
		return recursiveGetPreorderString(root);
	}
	
	//a recursive method that travers the tree in a postorder pattern
	//building a string as it goes
	private String recursiveGetPreorderString(BinaryNode<T> node) {
		String str = "";
		
		//break recursion when childless node is reached
		if (node != null) {
			str += node.getData() + " ";
			str += recursiveGetPreorderString(node.getLeftChild());
			str += recursiveGetPreorderString(node.getRightChild());
		}
		
		return str;
	}
	
	//calls the recursive string builder method and returns finalized string
	//will return a string of tree data in inorder sorting
	public String getInorderString() {
		return recursiveGetInorderString(root);
	}
	
	//a recursive method that travers the tree in a postorder pattern
	//building a string as it goes
	private String recursiveGetInorderString(BinaryNode<T> node) {
		String str = "";
		
		//break recursion when childless node is reached
		if (node != null) {
			str += recursiveGetInorderString(node.getLeftChild());
			str += node.getData() + " ";
			str += recursiveGetInorderString(node.getRightChild());
		}
		
		return str;
	}
	
	//calls the recursive string builder method and returns finalized string
	//will return a string of tree data in postorder sorting
	public String getPostorderString() {
		return recursiveGetPostorderString(root);
	}
	
	//a recursive method that travers the tree in a postorder pattern
	//building a string as it goes
	private String recursiveGetPostorderString(BinaryNode<T> node) {
		String str = "";
		
		//break recursion when childless node is reached
		if (node != null) {
			str += recursiveGetPostorderString(node.getLeftChild());
			str += recursiveGetPostorderString(node.getRightChild());
			str += node.getData() + " ";
		}
		
		return str;
	}
	

}
