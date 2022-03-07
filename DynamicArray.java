package a6;

/**
 * A DynamicArray behaves like an array of strings, except that it can grow and
 * shrink. It is indexed beginning with zero. When a DynamicArray is created, it
 * is empty. Methods are provided to report on the size, add, get, set, and
 * remove elements.
 * 
 * @author Prof. David E. Johnson
 *
 */
public class DynamicArray {

	private String[] data; // the backing array

	/**
	 * Creates an empty dynamic array.
	 */
	public DynamicArray() {
		data = new String[0];
	}

	/**
	 * Adds a string s to the dynamic array at index i. Prior elements are shifted
	 * up.
	 * 
	 * @param index the index
	 * @param s the String to insert at index
	 */
	public void add(int index, String s) {
		// Check for a legal index value
		if (index < 0 || index > data.length)
			throw new IndexOutOfBoundsException();

		// Adding a new value requires more space, so make a new array.
		String[] newData = new String[data.length + 1];

		// Copy the values up to the ith position
		for (int copyIndex = 0; copyIndex < index; copyIndex++) {
			newData[copyIndex] = data[copyIndex];
		}

		// Add in the new value
		newData[index] = s;

		// Copy and shift the values above the new value.
		for (int shiftIndex = index + 1; shiftIndex < newData.length; shiftIndex++) {
			newData[shiftIndex] = data[shiftIndex - 1];
		}

		data = newData;
	}

	/**
	 * Adds string s to end of the dynamic array.
	 * 
	 * @param s a String to add to the end of the array
	 */
	public void add(String s) {
		add(data.length, s);
	}

	/**
	 * Returns the string stored at index in the dynamic array. Throws an
	 * exception if index out of bounds.
	 * 
	 * @param index the index
	 * @return the string stored at index i
	 */
	public String get(int index) {
		if (index < 0 || index >= data.length)
			throw new IndexOutOfBoundsException();

		return data[index];
	}

	/**
	 * Changes the value of the string stored at index in the dynamic array to s.
	 * Throws an exception if index out of bounds.
	 * 
	 * @param index the index
	 * @param s     the String to put at location i
	 */
	public void set(int index, String s) {
		if (index < 0 || index >= data.length)
			throw new IndexOutOfBoundsException();

		data[index] = s;
	}

	/**
	 * Removes the string at index in the dynamic array. Throws an exception if
	 * index out of bounds.
	 * 
	 * @param index the index
	 */
	public void remove(int index) {
		if (index < 0 || index >= data.length)
			throw new IndexOutOfBoundsException();

		// When a value is removed, less space is needed
		String[] newData = new String[data.length - 1];

		for (int copyIndex = 0; copyIndex < index; copyIndex++) {
			newData[copyIndex] = data[copyIndex];
		}

		for (int shiftIndex = index; shiftIndex < newData.length; shiftIndex++) {
			newData[shiftIndex] = data[shiftIndex + 1];
		}

		data = newData;
	}

	/**
	 * Returns the number of elements in the dynamic array.
	 * 
	 * @return the number of elements
	 */
	public int size() {
		return data.length;
	}

	/**
	 * Returns the dynamic array as a formatted string.
	 * 
	 * @return the formatted string
	 */
	public String toString() {
		String result = "[";
		if (size() > 0) {
			result += get(0);
		}
		for (int index = 1; index < size(); index++) {
			result += ", " + get(index);
		}
		return result + "]";
	}

	public static void main(String[] args) {
		DynamicArray d = new DynamicArray();
		d.add("David");
		d.add("Joe");
		System.out.println(d);
		System.out.println(d.get(0));
		d.set(1, "Mary");
		System.out.println(d.get(1));
	}
}
