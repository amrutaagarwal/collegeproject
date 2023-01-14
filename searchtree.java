package ass7;
import java.util.*;

class Node {
	Node left, right;
	String word;
	String meaning;

	Node(String word, String meaning) {
		left = right = null;
		this.word = word;
		this.meaning = meaning;
	}
}

class Tree {// class to hold all functions and root
	Scanner sc = new Scanner(System.in);
	Node root;

	public void create() {
		// function to create a bst and insert new node if needed
		String word, meaning;
		char ch;
		do {
			System.out.println("Enter word: ");
			word = sc.nextLine();
			System.out.println("Enter the meaning of above word: ");
			meaning = sc.nextLine();
			// node for traversal
			Node temp = new Node(word, meaning);
			if (root == null) {// empty bst condition
				root = temp;
			} else {
				Node ptr = root;
				while (ptr != null) {
					if (temp.word.compareTo(ptr.word) < 0) {
						// key in left subtrees are smaller than key in root
						if (ptr.left == null) {
							ptr.left = temp;
							break;
						}
						ptr = ptr.left;
					} else if (temp.word.compareTo(ptr.word) > 0) {
						// key in right subtree are greater than key in root
						if (ptr.right == null) {
							ptr.right = temp;
							break;
						}
						ptr = ptr.right;
					} else {
						System.out.println("The word is already present");
						break;
					}
				}
			}
			System.out.println("Do you want to add more nodes ?");
			System.out.println("Type y or n: ");
			ch = sc.nextLine().charAt(0);
		} while (ch == 'y');// goes back to accepting nodes
		System.out.println("Dictionary is created");
	}

	public void display() {// function to display the bst
		inorder(root);
	}

	public void inorder(Node root) {// in-order display of nodes and their keys
		if (root != null) {
			inorder(root.left);
			System.out.println("Word: " + root.word + "\nMeaning: " + root.meaning);
			inorder(root.right);
		}
	}

	void search() {// function to search for a node in the bst
		System.out.println("Enter word: ");
		String word1 = sc.nextLine();
		Node ptr = root;
		while (ptr != null) {
			if (word1.equals(ptr.word)) {
				// if the word needed is present at the root
				System.out.println("Word has been found in the dictionary.");
				System.out.println("It's meaning is: " + ptr.meaning);
				return;
			} else if (word1.compareTo(ptr.word) < 0) {
				// searching the left subtree for the said node
				ptr = ptr.left;
			} else {
				// searching the right subtree for the said node
				ptr = ptr.right;
			}
		}
		// if word is not in the tree
		System.out.println("Word not found");
	}

	public void update() {
		System.out.println("Enter word to be updated: ");
		String word1 = sc.nextLine();
		Node ptr = root;
		while (ptr != null) {
			if (word1.equals(ptr.word)) {
				// if word to be updated is at the root
				System.out.println("Enter meaning: ");
				ptr.meaning = sc.nextLine();
				System.out.println("Word has been updated.");
				return;
			} else if (word1.compareTo(ptr.word) < 0) {
				// searching the left subtree for the said node
				ptr = ptr.left;
			} else {
				// searching the right subtree for the said node
				ptr = ptr.right;
			}
		}
		// if word is not in the tree
		System.out.println("Word not found");
	}

	public void del() {
		System.out.println("Enter word to be deleted: ");
		String word1 = sc.nextLine();
		this.root = delete(this.root, word1);
		System.out.println("Word is deleted.");
	}

	public Node delete(Node root, String word1) {
		if (root == null)// empty bst condition
			return root;
		if (word1.compareTo(root.word) > 0) {
			// deletion if the word is on the right subtree
			root.right = delete(root.right, word1);
		} else if (word1.compareTo(root.word) < 0) {
			// deletion if the word is on the left subtree
			root.left = delete(root.left, word1);
		}
		// when node to be deleted is not a leaf node
		else {
			// node has only one child or no child
			if (root.left == null)
				return root.right;
			else if (root.right == null)
				return root.left;
			// node with two children: find in-order successor to delete
			Node temp = successor(root.right);
			root.word = temp.word;
			root.meaning = temp.meaning;
			root.right = delete(root.right, root.word);
		}
		return root;
	}

	// function to find in-order successor
	public Node successor(Node n) {
		String word = n.word;
		String meaning = n.meaning;
		while (n.left != null) {
			word = n.left.word;
			meaning = n.left.meaning;
			n = n.left;
		}
		return new Node(word, meaning);
	}
	public class searchtree
	{
		public static void main(String args[]) {
			// object creation
			Tree obj = new Tree();
			Scanner sc = new Scanner(System.in);

			int choice;
			do {// menu display
				System.out.println("\nMENU");
				System.out.println("1. Create/insert words in the dictionary");
				System.out.println("2. Display the dictionary");
				System.out.println("3. Search words and their meanings in the dictionary");

				System.out.println("4. Update meaning of a word in the dictionary");

				System.out.println("5. Delete words and their meanings in the dictionary");

				System.out.println(" Enter choice: ");
				choice = sc.nextInt();
				switch (choice) {// switch case
				case 1:
					obj.create();
					break;
				case 2:
					if (obj.root == null) {
						System.out.println("Dictionary is empty.");
					}
					obj.display();
					break;
				case 3:
					obj.search();
					break;
				case 4:
					obj.update();
					break;
				case 5:
					obj.del();
					break;
				case 6:
					System.exit(6);
				default:
					System.out.println("invalid choice");
				}
			} while (choice != 6);
			sc.close();
		}
	}
}


