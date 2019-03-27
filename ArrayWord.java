import java.io.*;
import java.util.*;

///////////////////////////////////////////////////////
class ArrayWord {
	private int index;
	private int size;
	private Word[] array;
	private NodeWord[] list;
	private int notNull;
	private int count = 0;

	// initializes some variables
	public ArrayWord(int s) {
		setSize(s);
		setArray(new Word[size]);
		index = size - 1;
		list = new NodeWord[500000];
	}

	// displays the information of the arrayword
	public void display() {
		// TODO Auto-generated method stub
		System.out.println("");
		System.out.println("Array extraction using in-order traversal");
		for (int i = 0; i < getSize(); i++) {
			array[i].info();
		}
	}

	// gets a random Word object in the array
	public Word getRandomWord() {
		// TODO Auto-generated method stub
		Random rand = new Random(100);
		int r = rand.nextInt(getSize());
		return array[r];
	}

	// inserts Word from the back
	public void insert(NodeWord current) {
		// TODO Auto-generated method stub
		// index--;
		array[index--] = current.data;
		// size++;
	}

	// inserts word at a specific index
	public void insert(NodeWord current, int i) {
		// TODO Auto-generated method stub
		// index--;
		list[i] = current;
		array[i] = current.data;
		// size++;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public Word getWord(int i) {
		return array[i];
	}

	// shuffles the arrayword.
	public void shuffle() {
		Random rnd = new Random(100);
		int out, index;
		Word temp;
		for (out = size - 1; out > 0; out--) // outer loop (backward)
		{
			index = rnd.nextInt(out + 1); // select random number in [0:out]
			// simple swap
			temp = array[index];
			array[index] = array[out];
			array[out] = temp;
		}

	} // end shuffleArray()

	public Word[] getArray() {
		return array;
	}

	public void setArray(Word[] array) {
		this.array = array;
	}

	// displays the arrayword in order by indices
	public void displayAll() {
		// TODO Auto-generated method stub
		for (int i = 0; i < size; i++) {
			if (list[i] != null) {
				System.out.print(list[i].index + " ");
				array[i].info();
				notNull = i;
			}
		}
	}

	// plots the arrayword
	public void plotBST(int lx, int ly) {
		// TODO Auto-generated method stub
		StdDraw.setCanvasSize(lx, ly);
		StdDraw.setXscale(0.0, lx);
		StdDraw.setYscale(0.0, ly);
		// StdDraw.setPenRadius(.01);
		StdDraw.setPenColor(StdDraw.BLACK);

		int maxIndex = list[notNull].index;
		int sum = 0;
		int i;
		for (i = 0; i < maxIndex; i++) {
			sum += Math.pow(2, i);
			if (sum > maxIndex) {
				break;
			}
		}
		int maxPoint = sum;
		int levels = i;

		plothelp(600, 360, 600, 360, 300,0, maxPoint, levels);
		StdDraw.show();
	}

	// plotBST helper recursion
	public void plothelp(double x, double y, double xprev, double yprev, double xshift, int index,int maxPoint, int levels) {
		if (count <= maxPoint && index < maxPoint) {
			count++;

			plothelp(x - xshift, y - 30, x, y, xshift / 2, index*2+1 , maxPoint, levels);
			
			for(int i = 0; i< maxPoint; i++)
			{
				if(list[i] != null && index == list[i].index)
				{
					StdDraw.setPenColor(StdDraw.BLUE);
				}
			}
			
			StdDraw.filledCircle(x, y, 5);
			StdDraw.line(x, y, xprev, yprev);
			StdDraw.setPenColor(StdDraw.BLACK);
			plothelp(x + xshift, y - 30, x, y, xshift / 2, index*2+2 , maxPoint, levels);

		}
		else
		{
			return;
		}
	}
}
// to complete
