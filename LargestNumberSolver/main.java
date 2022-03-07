package assign04;

import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Array;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.lang.Integer;
import java.util.Scanner;


/**
 * @author Preston Malen and Emilie Wolfe
 *
 */
public class LargestNumberSolver {

	private CompareByConcatenate cmpConcat = new CompareByConcatenate();

	/**
	 * This sorts the given array using an insertion sort and the Comparator object
	 * that was passed
	 * @param <T> chosen object type
	 * @param arr the array to be sorted
	 * @param cmp the Comparator object
	 */
	public static <T> void insertionSort(T[] arr, Comparator<? super T> cmp) {
		for(int i = 1; i < arr.length; i++) {
			T val = arr[i];
			int j;
			for(j = i - 1; j >= 0 && cmp.compare(arr[j], val) < 0; j--)
				arr[j + 1] = arr[j];
			arr[j + 1] = val;
		}
	}

	/**
	 *This is a class designated to the concatenation method of comparing two objects
	 */
	static class CompareByConcatenate implements Comparator<Integer>{

		@Override
		public int compare(Integer A, Integer B) {

			return java.lang.Integer.compare(concatenate(A,B), concatenate(B,A));
		}
	}

	static int concatenate(Integer a, Integer b) {

		String sA = Integer.toString(a);
		String sB = Integer.toString(b);

		String sC = sA + sB;

		int c = Integer.parseInt(sC);
		return c;
	}

	/**
	 * This returns the largest number than can be created
	 * by concatenating the elements of a given Integer array
	 * @param arr the array of elements to be concatenated
	 * @return the largest number possible
	 */
	public static BigInteger findLargestNumber(Integer[] arr) {	
		Integer[] arrCopy = arr;
		//insertionSort(arrCopy, new CompareByConcatenate());
		Arrays.sort(arrCopy, new CompareByConcatenate());
		String sResult = "";
		for (Integer integer : arrCopy) {
			sResult += Integer.toString(integer);
		}
		return new BigInteger(sResult);
	}

	/**
	 * Similar to findLargestNumber method, this returns the largest int instead of
	 * the largest number
	 * @param arr the array of elements to be concatenated
	 * @return the largest int possible
	 * @throws OutOfRangeException if the return > 2,147,483,647
	 */
	public static int findLargestInt(Integer[] arr) throws OutOfRangeException{
		Integer[] arrCopy = arr;
		insertionSort(arrCopy, new CompareByConcatenate());
		String sResult = "";
		for (Integer integer : arrCopy) {
			sResult += Integer.toString(integer);
			}

		int result = Integer.parseInt(sResult);
		if(result > 2147483647){
			throw new OutOfRangeException("Result is too big for type int");
			}
		else {
			return result;
			}
		}

	/**
	 * Similar to findLargestNumber method, this returns the largest long instead of
	 * the largest number
	 * @param arr the array of elements to be concatenated
	 * @return the largest long possible
	 * @throws OutOfRangeException if the return > 9,223,372,036,854,775,807
	 */
	public static long findLargestLong(Integer[] arr) throws OutOfRangeException{
		Integer[] arrCopy = arr;
		insertionSort(arrCopy, new CompareByConcatenate());
		String sResult = "";
		for (Integer integer : arrCopy) {
			sResult += Integer.toString(integer);
			}

		long range = 9223372036854775807L;
		long result = Long.valueOf(sResult);
		if(result > range) {
			throw new OutOfRangeException("Result is too big for type int or BigInteger");
			}
		else {
			return result;

		}
	}


	/**
	 * This returns the sum of the largest number found in each array in the given list
	 * @param list a list of Integer arrays
	 * @return the sum
	 */
	public static BigInteger sum(List<Integer[]> list) {
		BigInteger sum = new BigInteger("0");
		for(int i = 0; i < list.size(); i ++) {
			sum = sum.add(findLargestNumber(list.get(i)));
		}
		return sum;
	}

	/**
	 * This method determines the kth largest number that can be formed by each array in the given list.
	 * E.g., if k=0 returns the largest overall, if k=list.size()-1 returns the smallest overall.
	 * This method returns the original array that represents the kth largest number, not the kth largest number itself.
	 * @param list
	 * @param k
	 * @return the kth largest number
	 * @throws IllegalArgumentException if k is not a valid position/index of the list
	 */
	public static Integer[] findKthLargest(List<Integer[]> list, int k) throws IllegalArgumentException{
		BigInteger[] originalResult = new BigInteger[list.size()];
		BigInteger[] sortedResult = new BigInteger[list.size()];
		for(int i = 0; i < list.size(); i ++) {
			originalResult[i] = findLargestNumber(list.get(i));
			sortedResult[i] = originalResult[i];
		}
		insertionSort(sortedResult, new CompareBigInteger());
		int index = 0;
		for(int i = 0; i < list.size(); i ++){
			if(sortedResult[k].equals(originalResult[i])) index = i;
		}
		return list.get(index);
	}
	static class CompareBigInteger implements Comparator<BigInteger>{
		@Override
		public int compare(BigInteger A, BigInteger B) {

			return A.compareTo(B);
		}
	}
	/**
	 * This method generates list of integer arrays from an input file, such that each line corresponds to
	 * one array of integers separated by blank spaces, and returns an empty list if the file does not exist.
	 * @param filename
	 * @return the new list containing all the integer arrays
	 */
	public static List<Integer[]> readFile(String filename) {
		
			List<Integer[]> list = new ArrayList<>();
			try{
				Scanner fileIn = new Scanner(new File(filename));
				while(fileIn.hasNextLine()){
					String line = fileIn.nextLine();
					String[] split = line.split(" ");
					Integer[] intArray = new Integer[split.length];
					for(int i = 0; i < intArray.length; i++) {
						intArray[i] = Integer.parseInt(split[i]);
					}
					list.add(intArray);
				}
			}catch(FileNotFoundException e){
				System.err.println(e.getMessage());
			}
			return list;
		}
}
