package a6;

/**
 * A DynamicArray2 behaves like an array of strings, except that it can grow and
 * shrink. It is indexed beginning with zero. When a DynamicArray2 is created,
 * it is empty. Methods are provided to report on the size, add, get, set, and
 * remove elements.
 * 
 * @author Prof. David E. Johnson (featuring Preston Malen)
 *
 */
public class DynamicArray2 {

	private String[] data; // the backing array
	private int virtualArrayLength; // the number of elements in the dynamic array

	/**
	 * Creates an empty dynamic array with room to grow. (DO NOT modify this
	 * method.)
	 */
	public DynamicArray2() {
		// Start with a few available extra spaces. Do not change this.
		data = new String[8];
		// But the virtual array is empty.
		virtualArrayLength = 0;
	}

	/**
	 * Returns the number of elements in the dynamic array.
	 * 
	 * @return the number of elements
	 */
	public int size() {
		// This is not the length of the backing array. It is the 
		// number of elements used by the virtual array.
		return virtualArrayLength;
	}


	/** This method adds the string "s" to the end of the dynamic array at the index of "this.size()".
	 * @param s: an arbitrary string
	 * There is no return for this method, it will just append "s" to the end of the dynamic array.
	 */
	public void add(String s) {
		add(this.size(), s);
	}


	/** This will add an element "s" at the given index of an array and shift all subsequent elements up by one.
	 * If index is out of bounds, an error is thrown.
	 * If there is not sufficient space for the new element, the size of the backing array is increased.
	 * @param index: the desired spot to add "s"
	 * @param s: an arbitrary string
	 * There is no return for this method, it will simply update the dynamic array
	 */
	public void add(int index, String s) {
		// Checking validity of index
		if (index < 0 || index >= data.length) {
			throw new IndexOutOfBoundsException();
		}		
		
		// Setting parameters for the length of the new array if one is necessary
		if (data.length < virtualArrayLength - 1) { 
			String[] dataB = new String[data.length * 2];
		// Copying items into dataB
			for (int i = 0; i < index; i++) {
				dataB[i] = data[i];
			}
			// Shift the items in data at or above index up by one,
			// to make room for s at index index.
			for (int j = index + 1; j < data.length - index; j++) {
				virtualArrayLength += 1;
				dataB[j] = dataB[j -1];
				dataB[index] = s;
			}
			data = dataB;
		}
		
		// Shift the items in data at or above index up by one,
		// to make room for s at index index.
		else {
			for (int j = index + 1; j < data.length - index; j++) {
				virtualArrayLength += 1;
				data[j] = data[j - 1];
				data[index] = s;
			}
		}
	}
	


	/** This method removes the element at the passed index and shifts the remaining elements in the dynamic array down by one
	 * @param index: the index of the desired element to remove
	 * This method has a void return type
	 */
	public void remove(int index) {
		// Checking validity of index
		if (index < 0 || index >= data.length) {
			throw new IndexOutOfBoundsException();
		}		
		for (int j = index; j < data.length - index; j ++) {
			data[j] = data[j + 1];			
		}		
	}

	// Throws an IndexOutOfBoundsException if index is not a valid index
	// of the dynamic array, otherwise returns the element at index.
	public String get(int index) {
		// Checking validity of index
		if (index < 0 || index >= data.length) {
			throw new IndexOutOfBoundsException();
		}
		return data[index];
	}


	/** This simply sets the element at the passed index to the passed string, "s" without shifting any of the other elements
	 * in the dynamic array.
	 * @param index: the desired index at which to change
	 * @param s: the desired string
	 * The return for this method is void
	 */
	public void set(int index, String s) {
		// Checking validity of index
		if (index < 0 || index >= data.length) {
			throw new IndexOutOfBoundsException();
		}
		data[index] = s;
	}

	/**
	 * Returns a formatted string version of this dynamic array.
	 * 
	 * @return the formatted string
	 */
	public String toString() {
		String result = "[";
		if (size() > 0)
			result += get(0);

		for (int index = 1; index < size(); index++)
			result += ", " + get(index);

		return result + "] backing size: " + data.length;
	}
	
	public static void main(String[] args) {
		// Testing the dynamic array and all the methods therein
		// I got a little carried away, I was just really happy that my code worked on the first try
		// Don't worry, I do not hate CS, I just get frustrated with it at times, but I think we all do
		DynamicArray d = new DynamicArray();
		d.add("Preston");
		d.add("at");
		d.add("CS");
		System.out.println(d);
		d.add(1, "sucks");
		System.out.println(d);
		d.add("but");
		d.add("he");
		d.add("sometimes");
		d.add("likes");
		d.add("it");
		System.out.println(d);
		d.remove(6);
		System.out.println(d);
		d.add("random");
		d.add("text");
		d.add("computer");
		d.add("stuff");
		System.out.println(d);
		d.add(8, "AHHHHHHHHHHHHHHHHHH");
		System.out.println(d);
		
	}
}
