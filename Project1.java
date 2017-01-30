////////////////////////////  STUDENT INFORMATION  ////////////////////////////
//	          															
//	Name:        James McCarthy                     
//	Assignment:  Project 1                            
//	Course:      CS241 Data Structures & Algorithms II  
//	Instructor:  Hao Ji                           
//	Institution: Cal Poly Pomona              
//	Date Due:    30 January 2017           
//				                                                         
///////////////////////////////////////////////////////////////////////////////

import java.util.Arrays;
import java.util.Scanner;

/** The class Project1 is part of an implementation of an interactive binary
 * search tree. This class processes user inputs and performs commands on the 
 * tree accordingly. Serving as the front-end, this class contains the main
 * functionality of the program itself and serves as an entry point.
 * 
 * @author J. Donald McCarthy
 *
 */
public class Project1 {
	private static Scanner scanner; //scanner for reading inputs
	
	private static BinarySearchTree<Integer> tree; //the binary search tree
	
	private static boolean isRunning; //boolean flag for exiting
	
	public static void main(String [] args) {
		
		scanner = new Scanner(System.in); //initializing scanner
		tree = new BinarySearchTree<Integer>(); //initializing BST
		isRunning = true; //setting the flag to true
		
		//putting valid user input into array		
		int[] sequence = getUserSequence();	
		
		//using array to fill tree in the order in which inputs were given
		for (int i = 0; i < sequence.length; i++){
			tree.add(sequence[i]);
		}
		
		//printing preorder, inorder, and postorder traversals
		displayTraversals();
		
		System.out.println("Enter 'H' For a list of commands.");
		
		//continually prompting user for menu commands until exit is chosen
		while(isRunning)
			getAndPerformUserCommand();
	}
	
	/* Preconditions: User has entered 'H' when prompted for a command
	 * Postconditions: A list of commands has been printed to the console
	 * 
	 * Prints a list of commands to the console after user enters 'H' */
	public static void displayMenuOptions() {
		System.out.println(" I  Insert a value");
		System.out.println(" D  Delete a value");
		System.out.println(" P  Find predecessor");
		System.out.println(" S  Find successor");
		System.out.println(" E  Exit the program");
		System.out.println(" H  Display this message");
	}
	
	/* Preconditions: A sequence of integers has been added to "tree"
	 * Postconditions: The values in the nodes of "tree" have been printed
	 * 					in preorder, inorder, and postorder
	 * 
	 * Prints the user entered sequence from "tree" using the recursive
	 * traversal methods of tree to build a string in the correct order */
	public static void displayTraversals(){
		System.out.println("Pre-order: "  + tree.getPreorderString());
		System.out.println("In-order: "   + tree.getInorderString());
		System.out.println("Post-order: " + tree.getPostorderString());
		
	}
	
	/* Precondition: "key" is a single capitalized alphabetical character and
	 * 				  value is an integer entered by the user
	 * 
	 * Postcondition: the command corresponding to "key" has been performed 
	 * 				  using a correct integer value or either "key" or "val"
	 * 				  have been rejected.
	 * 
	 * Performs operations on the tree based on valid user input. 
	 * If a command is not recognized, prints a notification and attempts to
	 * acquire a new command. If a command is recognized, but the value
	 * provided cannot be found or is a duplicate, notifies the user of the
	 * mistake. If the command is recognized and value is valid, performs the
	 * requested action.*/
	public static void performMenuAction(char key, int val) {
		switch (key) {
			case 'I':
				addValueAndPrint(val);
				break;
			case 'D':
				deleteValueAndPrint(val);
				break;
			case 'P':
				printPredecessorValue(val);
				break;
			case 'S':
				printSuccessorValue(val);
				break;
			default:
				break;
		}
	}
	
	/* Attempts to add a given value to the tree and either prints out the 
	 * inorder listing of the tree's data or a message informing the user 
	 * that there are no duplicates allowed */
	public static void addValueAndPrint(int val) {
		if(tree.add(val) == null){
			System.out.println("In-order: " + tree.getInorderString());
		} else 
			System.out.println(val + " already exists, ignore.");
	}
	
	/*Attempts to delete a given value from the tree and either prints out the
	* inorder listing or a message informing the user that the given value 
	* does not exist inside the tree. */
	public static void deleteValueAndPrint(int val) {
		if(tree.delete(val) != null) {
			System.out.println("In-order: " + tree.getInorderString());
		} else
			System.out.println(val + " doesn't exist!");
	}
	
	/* Attempts to find and print the in-order successor value to the given
	 * value or informs the user that the given value does not exist inside
	 * the tree. */
	public static void printSuccessorValue(int initialValue) {
		Integer successorValue = tree.getSuccessorValue(initialValue);
		
		if (successorValue != null) 
			System.out.println(successorValue);
		 else 
			 System.out.println(initialValue + " doesn't exist!");
	}
	
	/* Attempts to find and print the in-order predecessor value to the given
	 * value or informs the user that the given value does not exist inside
	 * the tree.*/
	public static void printPredecessorValue(int initialValue) {
		Integer predecessorValue = tree.getPredecessorValue(initialValue);
		
		if (predecessorValue != null) 
			System.out.println(predecessorValue);
		 else 
			 System.out.println(initialValue + " doesn't exist!");
	}
	
	/* Precondition: "key" is a single capitalized alphabetical character, and
	 * 				 no integer value was entered by the user
	 * 
	 * Postcondition: The command corresponding to "key" has been performed or
	 * 				  the command has been rejected.
	 * 
	 * Attempts to either exit the program or display the menu options 
	 * depending on whether the key equals E or H, respectively. If "key" is
	 * another valid menu option, notify user that they must enter an integer
	 * to perform that action. If "key" does not match any valid menu option, 
	 * notify user of invalid entry. */
	public static void performMenuAction(char key){
		if(key == 'E') {
			System.out.println("Thank you for using my program!");
			isRunning = false;
		}
		else if (key == 'H') {
			displayMenuOptions();
			getAndPerformUserCommand();
		} else {
			System.out.println( "That command requires you to enter a value. "
															+ "Try again.");
			getAndPerformUserCommand();
		}

	}
	
	/* Preconditions: The user has entered a sequence of values and been given
	 * 				  a printing of three traversal types.
	 * 
	 * Postconditions: The user has entered a command in a valid format and the
	 * 					command has either been performed or rejected.
	 * 
	 * Continually attempts to get a valid input from the user in the format
	 * (char) or (char)_(int) where _ signifies a single space. */
	public static void getAndPerformUserCommand() {
		String entry;
		char command;
		int value = 0;
		
		/* continually prompting user to enter a command until the entry given
		 * matches a regex pattern either of a single character or a character
		 * followed by a space and and an integer */
		do {
			System.out.print("Command? ");
			entry = scanner.nextLine().toUpperCase();
		} while(!entry.matches("(?:[DEHIPS].*\\s.*[0-9]|[DEHIPS])"));
		
		/*at this point, the entry is valid and is either a single character
		 * or a character followed by a space and an integer"
		 */
		
		// setting command to the first and only character in valid user entry
		command = entry.charAt(0);
		
		// checking to see if command contains additional integer value
		if(entry.length() > 1) {
			//parsing integer from entry
			value = Integer.parseInt(entry.split(" ")[1]);
			//process commands that require integers values
			performMenuAction(command, value);
		}
		 else 
			 //processing commands that do not require integer values
			performMenuAction(command);
	}
	
	/** Preconditions: The program is running and in its initial state.
	 * 
	 *  Postconditions: A sequence of integers defined by the user
	 *  				has been returned in array form.
	 * 
	 * Continually attempts to acquire a sequence of integers from the user in
	 * the format (int)_(int)_(int)... where '_' signifies a space
	 * 
	 * @return an array of integers defined by the user in the order they were
	 * 		   entered
	 */
	public static int[] getUserSequence() {
		
		String entry;
		
		/* continually prompting user to enter a sequence until the entry given
		 * matches a regex pattern containing only integers and spaces */
		do {
			System.out.println("Please enter the initial sequence of values:");
			entry = scanner.nextLine();
		} while (!entry.matches("[0-9 ]+$"));
		
		/* using java's stream feature to separate the numbers as strings after 
		 * every space from the line input string, then parsing the numbers to
		 * integers, removing duplicate values, and finally dumping them to an 
		 * array.
		 * 
		 *  (Note: stream is a Java 8+ feature) */
		int[] sequence = Arrays.stream(entry.split(" "))
							 .mapToInt(Integer::parseInt).distinct().toArray();
				
		return sequence;
	}
}
