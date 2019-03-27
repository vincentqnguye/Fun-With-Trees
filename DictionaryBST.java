import java.io.*;
import java.util.*;

class DictionaryBST {

	private int nbwords;// number of words
	private NodeWord root;
	private int maxIndex;
	private int step;
	private int maxSizeWord;
	private String fileName;
	private String searchID;

	public DictionaryBST() {
		nbwords = 0;
		maxIndex = 0;
		step = 0;
		maxSizeWord = 0;
	}

	// Loads the specific dictionary and inserts it into the BST
	public void loadDictionary(String name) {
		// TODO Auto-generated method stub
		FileReader fr = null;
		// this.dname = name;
		BufferedReader br = null;
		try {
			setFileName(name);
			// this.file = name;
			fr = new FileReader("C:\\Users\\Vincent\\workspace\\Project 4\\src\\" + getFileName() + ".txt");
			br = new BufferedReader(fr);

			String input = br.readLine();

			if (input != null && getRoot() == null) {
				Word w = new Word(input, getFileName());
				nbwords = 1;
				setRoot(new NodeWord(w));
				maxSizeWord = getMaxSizeWord(w);
				input = br.readLine();
			}

			while (input != null) {
				nbwords++;
				Word w = new Word(input, getFileName());
				recinsert(getRoot(), w);
				maxSizeWord = getMaxSizeWord(w);
				input = br.readLine();
			}

		} catch (Exception E) {
			System.out.println("File cannot be found");
		}
	}

	// returns the information of the BST
	public void info() {
		// TODO Auto-generated method stub
		System.out.println("The size of the dictionary is " + nbwords + " using " + getBST() + " BST levels.");
	}

	// returns the level of the BST
	public int getBST() {
		// TODO Auto-generated method stub
		// return (int)Math.log(maxIndex);
		if (height(root) == -1)
			return 0;
		else if (height(root) == 2)
			return 1;
		else
			return height(root);
	}

	/*
	 * Compute the "height" of a tree -- the number of nodes along the longest
	 * path from the root node down to the farthest leaf node.
	 */
	public int height(NodeWord root) {
		if (root == null)
			return -1;
		else {
			/* compute height of each subtree */
			int lheight = height(root.left);
			int rheight = height(root.right);

			/* use the larger one */
			if (lheight > rheight)
				return (lheight + 1);
			else
				return (rheight + 1);
		}
	}

	// returns the size of the BST (non-nulls)
	public int getSize() {
		// TODO Auto-generated method stub
		return nbwords;
	}

	// displays the BST in order as well as prints the information of
	public void displayInOrder() {
		// TODO Auto-generated method stub
		System.out.println("");
		System.out.println("Display in-order " + nbwords + " words.");
		recInOrder(getRoot());
		System.out.println("");
		System.out.println("");
	}

	// displayInOrder helper that goes through the BST
	private void recInOrder(NodeWord current) {
		if (current != null) {
			recInOrder(current.left);
			current.data.info();
			recInOrder(current.right);
		}
	}

	// traverses through BST and prints the word string
	private void recShowWord(NodeWord current, String indStr) {
		if (current != null) {
			recShowWord(current.right, indStr + "     ");
			System.out.println(indStr + current.data.toString());
			recShowWord(current.left, indStr + "     ");
		}
	}

	// traverses through BST and prints the dictionary of where the word came
	// from
	private void recShowID(NodeWord current, String indStr) {
		if (current != null) {
			recShowID(current.right, indStr + "     ");
			System.out.println(indStr + current.data.getID());
			recShowID(current.left, indStr + "     ");

		}
	}

	// traverses through BST and prints the index of the word
	private void recShowIndex(NodeWord current, String indStr) {
		if (current != null) {
			recShowIndex(current.right, indStr + "     ");
			System.out.println(indStr + current.index);
			recShowIndex(current.left, indStr + "     ");
		}
	}

	// shows the BST accordingly
	public void show(String string) {
		System.out.println("The tree looks like: ");
		switch (string) {
		case "word":
			recShowWord(getRoot(), " ");
			System.out.println("");
			break;
		case "id":
			recShowID(getRoot(), " ");
			System.out.println("");
			break;
		case "index":
			recShowIndex(getRoot(), " ");
			System.out.println("");
			break;
		}
	}

	// inserts the word and sets the root if there is nothing in the tree
	public void insert(Word i) {
		if (root == null) {
			root = new NodeWord(i);
			return;
		} else {
			recinsert(root, i);
		}
	}

	// inserts each word recursively.
	public void recinsert(NodeWord current, Word i) {
		if (root == null) {
			root = new NodeWord(i);
			return;
		}
		if (i.toString().compareToIgnoreCase(current.data.toString()) < 0) { // search
																				// left
			if (current.left == null)// node needs to be inserted
			{
				current.left = new NodeWord(i);
				current.left.index = current.index * 2 + 1;
				if (maxIndex < current.left.index)
					maxIndex = current.left.index;
			} else {
				recinsert(current.left, i); // keep searching
			}
		} else if (i.toString().compareToIgnoreCase(current.data.toString()) > 0) {// search
			// right
			if (current.right == null)// node needs to be inserted
			{
				current.right = new NodeWord(i);
				current.right.index = current.index * 2 + 2;
				if (maxIndex < current.right.index)
					maxIndex = current.right.index;
			} else {
				recinsert(current.right, i);// keep searching
			}
		}
	}

	// extracts the array in order
	public ArrayWord extractArrayInOrder() {
		// TODO Auto-generated method stub
		ArrayWord array = new ArrayWord(nbwords);
		recGetWord(getRoot(), array);
		return array;
	}

	// gets and inserts the word inside ArrayWord
	public void recGetWord(NodeWord current, ArrayWord array) {
		if (current != null) {
			recGetWord(current.right, array);
			array.insert(current);
			recGetWord(current.left, array);
		}
	}

	// searches if the word exists in the dictionary and returns true if it is
	// found
	public boolean search(String i) {
		// TODO Auto-generated method stub
		NodeWord current = root; // start at root
		step = 0;
		while (current != null) {
			if (i.compareToIgnoreCase(current.data.toString()) == 0) {
				searchID = current.data.getID();
				return true;
			}
			if (i.compareToIgnoreCase(current.data.toString()) < 0) { // go
				// System.out.println("dead"); // left
				current = current.left;
				step++;
			} else if (i.compareToIgnoreCase(current.data.toString()) > 0) { // go
																				// right
				current = current.right;
				step++;
			}
		}

		return false; // return Node or null
	}

	public int getStep() {
		// TODO Auto-generated method stub
		return this.step;
	}

	// compares the size of the word and updates the maxsizeword
	public int getMaxSizeWord(Word i) {
		if (i.toString().length() > maxSizeWord) {
			maxSizeWord = i.toString().length();
		}
		return maxSizeWord;
	}

	// gets the array of the specific size
	public ArrayWord extractSubArrayInOrder(int s) {
		ArrayWord aw = extractArrayInOrder();
		ArrayList<Word> list = new ArrayList<Word>();
		for (int i = 0; i < aw.getSize(); i++) {
			if (aw.getWord(i) != null && aw.getWord(i).toString().length() == s) {
				list.add(aw.getWord(i));
			}
		}

		// arraylist to arrayword
		ArrayWord subArray = new ArrayWord(list.size());
		for (int i = 0; i < list.size(); i++) {
			NodeWord current = new NodeWord(list.get(i));
			subArray.insert(current, i);
		}
		return subArray;
	}

	public int getMaxSizeWord() {
		return maxSizeWord;
	}

	public void setMaxSizeWord(int maxSizeWord) {
		this.maxSizeWord = maxSizeWord;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	// this converts the arrayword to a DictBST
	public DictionaryBST initDictionary(ArrayWord arr) {
		arr.shuffle();
		DictionaryBST d = new DictionaryBST();
		int size = arr.getSize();
		int index = 0;

		String input = arr.getWord(index).toString();

		if (input != null && d.getRoot() == null) {
			Word w = new Word(input, getFileName());
			d.nbwords = 1;
			d.insert(w);
			d.maxSizeWord = d.getMaxSizeWord(w);
			input = arr.getWord(index++).toString();
		}

		while (index <= size) {
			// d.nbwords++;
			Word w = new Word(input, getFileName());
			d.insert(w);
			d.maxSizeWord = d.getMaxSizeWord(w);
			if (index + 1 > size)
				break;
			else {
				d.nbwords++;
				input = arr.getWord(index++).toString();
			}
		}
		return d;
	}

	public NodeWord getRoot() {
		return root;
	}

	public void setRoot(NodeWord root) {
		this.root = root;
	}

	// creates and anagram tree by finding all the words of the same length
	// and comparing all the characters in the word to the parameter
	public DictionaryBST createBSTAnagram(String word) {
		// TODO Auto-generated method stub
		int length = word.length();
		Character[] c = new Character[word.length()];
		for (int i = 0; i < length; i++) {
			c[i] = word.charAt(i);
		}
		bubbleSort(c);

		ArrayWord a = extractSubArrayInOrder(length);
		ArrayList<String> list = new ArrayList<String>();

		for (int i = 0; i < a.getSize(); i++) {
			int count = 0;
			String temp = a.getWord(i).toString();
			Character[] w = toCharArray(temp);
			bubbleSort(w);
			for (int j = 0; j < length; j++) {
				if (c[j].equals(w[j])) {
					count++;
				}
			}
			if (count == length) {
				list.add(temp);
			}
		}

		ArrayWord b = new ArrayWord(list.size());
		for (int i = 0; i < list.size(); i++) {
			Word w = new Word(list.get(i), getFileName());
			NodeWord current = new NodeWord(w);
			b.insert(current, i);
		}
		return initDictionary(b);
	}
	
	// converts a string into a Character object array
	public Character[] toCharArray(String temp) {
		if (temp == null) {
			return null;
		}
		int length = temp.length();
		Character[] array = new Character[length];
		for (int i = 0; i < length; i++) {
			array[i] = new Character(temp.charAt(i));
		}
		return array;
	}

	// sorts a Character array
	public void bubbleSort(Character[] c) {
		int n = c.length;
		for (int i = 0; i < n - 1; i++) {
			for (int j = 0; j < n - i - 1; j++) {
				if (c[j] > c[j + 1]) {
					// swap temp and arr[i]
					char temp = c[j];
					c[j] = c[j + 1];
					c[j + 1] = temp;
				}
			}
		}
	}

	// this checks each word if it exists in the DictBST and returns
	// where the word came from
	public void spellCheckFile(String file) {
		// TODO Auto-generated method stub
		FileReader fr = null;
		BufferedReader br = null;
		try {
			setFileName(file);
			fr = new FileReader("C:\\Users\\Vincent\\workspace\\Project 4\\src\\" + getFileName());
			br = new BufferedReader(fr);
			String input = br.readLine();
			while (input != null) {
				String[] arr = input.split(" ");
				String result = "[";
				for (int i = 0; i < arr.length; i++) {
					String word = arr[i].replaceAll("\\p{Punct}|\\d", "");
					if (search(word) == false) {
						result += "no ID-";
						arr[i] = "(" + word + ")";
					}
					if (search(word)) {
						result += searchID + "-";
					}
				}
				result += "]";
				for (int i = 0; i < arr.length; i++) {
					System.out.print(arr[i] + " ");
				}
				System.out.println("");
				System.out.println(result);
				// System.out.println("");
				input = br.readLine();
			}
		} catch (Exception E) {
			System.out.println("File not found");
			// continue;
		}
	}

	// orders the arrayword by index
	public ArrayWord convertToArrayInOrder() {
		// TODO Auto-generated method stub
		ArrayWord inorder = new ArrayWord(maxIndex + 1);
		ArrayList<NodeWord> alist = new ArrayList<NodeWord>();
		recGetWord(root, alist);

		for (int i = 0; i < alist.size(); i++) {
			inorder.insert(alist.get(i), alist.get(i).index);
		}
		return inorder;
	}

	// similiar to recGetWord for ArrayWord
	public void recGetWord(NodeWord current, ArrayList<NodeWord> alist) {
		if (current != null) {
			recGetWord(current.right, alist);
			alist.add(current);
			recGetWord(current.left, alist);
		}
	}

}

////////////////////////////////////////////////////////////////////////////////

////////////////////////////////////////////////////////////////////////////////

////////////////////////////////////////////////////////////////////////////////
