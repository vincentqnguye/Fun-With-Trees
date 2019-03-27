
///////////////////////////////////////////////////////

class NodeWord { // Define node for BST

	// left and right references only needed for Binary Tree linked-list
	public NodeWord left; // left child
	public NodeWord right;// right child
	public int index; // CBT index
	public Word data; // the object

	// Constructor: construct the NodeWord class with the Word object
	public NodeWord(Word data) {
		left = null;
		right = null;
		this.data = data;
	}
}
