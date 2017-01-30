/** The class BinaryNode is involved in the implementation of a binary tree
 *  using node representation. The tree itself is defined elsewhere, however
 *  the branching nodes of the tree will be composed of members belonging to
 *  this class.
 * 
 * @author J. Donald McCarthy
 *
 * @param <T> A generic data type to be stored in this node and its children
 */
public class BinaryNode<T> {

	private T data; //the data contained in the node
	private BinaryNode<T> left; //the root of the right branch of the node
	private BinaryNode<T> right; //the root of the left branch of the node
	
	/*single constructor to initialize node with root data
	there is no need for multiple constructors in this project */
	public BinaryNode(T data) {this.data = data;}
	
	//a function that returns true if the nodes' left child has value
	public boolean hasLeftChild() {return left != null;}
	
	//a function that returns true if the nodes' right child has value
	public boolean hasRightChild() {return right != null;}
	
	///////////////////////// GETTERS AND SETTERS ///////////////////////// 
	///      Various methods to alter and retrieve the nodes' data.     ///
    ///////////////////////////////////////////////////////////////////////
	
	public T getData() {return data;}
	
	public void setData(T newData) {data = newData;}
	
	public BinaryNode<T> getLeftChild() {return left;}
	
	public BinaryNode<T> getRightChild() {return right;}
	
	public void setLeftChild(BinaryNode<T> newLeft) {left = newLeft;}
	
	public void setRightChild(BinaryNode<T> newRight) {right = newRight;}
	
     ///////////////////////////////////////////////////////////////////////
	

	
}
