import java.io.*;
import java.util.*;

class CollectionBST {
	private int nbdicts;
	private int step;
	private DictionaryBST dict;
	private DictionaryBST Collection[];

	// initializes some variables
	public CollectionBST(DictionaryBST d) {
		// TODO Auto-generated constructor stub
		dict = d;
		Collection = new DictionaryBST[dict.getMaxSizeWord()];
		nbdicts = dict.getMaxSizeWord();
		ArrayWord a[] = new ArrayWord[dict.getMaxSizeWord()];
		for (int i = 0; i < Collection.length; i++) {
			a[i] = dict.extractSubArrayInOrder(i + 1);
		}

		for (int j = 0; j < Collection.length; j++) {
			Collection[j] = new DictionaryBST();
			if (a[j].getArray().length > 0) {
				Collection[j] = Collection[j].initDictionary(a[j]);
			}
		}
		System.out.println();
	}

	// searches a specific arrayword of that word length
	// this should be faster than DictionaryBST search
	public boolean search(String word) {
		// TODO Auto-generated method stub
		step = 0;
		NodeWord current = Collection[word.length() - 1].getRoot(); // start at
																	// root
		while (current != null) {
			if (word.compareToIgnoreCase(current.data.toString()) == 0) {
				return true;
			}
			if (word.compareToIgnoreCase(current.data.toString()) < 0) { // go
																			// left
				current = current.left;
				increaseStep();
			} else if (word.compareToIgnoreCase(current.data.toString()) > 0) { // go
																				// right
				current = current.right;
				increaseStep();
			}
		}

		return false;

	}
	// to complete

	public void increaseStep() {
		// TODO Auto-generated method stub
		step++;
	}

	public int getStep() {
		// TODO Auto-generated method stub
		return this.step;
	}

	// prints the information of the Collection
	public void info() {
		// TODO Auto-generated method stub
		System.out.println("The collection contains " + getnbdicts() + " BST dictionaries.");
		System.out.println("dict --> size --> nblevels");
		for (int dict = 0; dict < nbdicts; dict++) {
			System.out.println(dict + 1 + " --> " + Collection[dict].getSize() + " --> " + getnblevels(dict));
		}

	}

	// returns the number of dictionaries in Collection
	public int getnbdicts() {
		// TODO Auto-generated method stub'
		return nbdicts;
	}

	// gets the levels of the specific BST
	public int getnblevels(int index) {
		// TODO Auto-generated method stub
		int nblevels = 0;
		nblevels = Collection[index].getBST();
		return nblevels;
	}

}

////////////////////////////////////////////////////////////////////////////////

////////////////////////////////////////////////////////////////////////////////

////////////////////////////////////////////////////////////////////////////////
