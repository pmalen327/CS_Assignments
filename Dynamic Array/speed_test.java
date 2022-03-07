package a6;
/***
 * Tests the time to add N elements to a DynamicArray compared to DynamicArray2
 * @author Preston Malen
 *
 */
public class SpeedTest {

	public static void main(String[] args) {
		
		// Create an object of each array type
		DynamicArray slowDA = new DynamicArray();
		DynamicArray2 fastDA = new DynamicArray2();
		
		for (int N = 10000; N <= 40000; N += 5000) {
		// Set the desired number of iterations
		
		// Collect the time required to add N elements to the slow DA.
		long startTime = System.nanoTime();
		for(int i = 0; i < N; i++) {
			slowDA.add("" + i);  // adds to the end of the array
		}
		long endTime = System.nanoTime();
		System.out.println("DynamicArray took " + (endTime - startTime) / 1000000000.0 + 
				" sec to add " + N + " elements.");

		// Collect the time required to add N elements to the fast DA.
		startTime = System.nanoTime();
		for(int i = 0; i < N; i++) {
			fastDA.add("" + i);   // adds to the end of the array
		}
		endTime = System.nanoTime();
		System.out.println("DynamicArray2 took " + (endTime - startTime) / 1000000000.0 + 
				" sec to add " + N + " elements.");
		}
	}

}
