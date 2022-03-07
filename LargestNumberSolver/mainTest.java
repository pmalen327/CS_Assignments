package assign04;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertArrayEquals;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

import java.io.File;
import java.io.FileNotFoundException;
import java.lang.Integer;
import java.util.Scanner;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class LargestNumberSolverTester {
	
	private Integer[] smallIntA;
	private Integer[] smallIntB;
	private Integer[] smallIntC;
	private Integer[] smallIntD;
	
	private List<Integer[]> smallIntList;
	
	private BigInteger[] smallIntegerA;
	private BigInteger[] smallIntegerB;

	private List<Integer[]> testFile;
	
	
	
	@BeforeEach
	void setUp() throws Exception {
		
		
		smallIntA = new Integer[]{6, 18, 3, 9, 11};
		smallIntB = new Integer[]{34, 12, 23, 8, 4};
		smallIntC = new Integer[]{14, 3, 7, 11, 9};
		smallIntD = new Integer[]{43, 52, 1, 6, 9};


		smallIntList = new ArrayList<Integer[]>();
			smallIntList.add(smallIntA);
			smallIntList.add(smallIntB);
	}
	
	
	//1
	@Test
	public void findLargestIntSmallIntA() {
		assertEquals(9631811, LargestNumberSolver.findLargestInt(smallIntA));			
	}
	
	//2
	@Test
	public void findLargestIntSmallIntB() {
		assertEquals(84342312, LargestNumberSolver.findLargestInt(smallIntB));		
	}
	
	//3
	@Test
	public void findLargestNumberSmallIntA() {
		assertEquals(BigInteger.valueOf(9631811), LargestNumberSolver.findLargestNumber(smallIntA));				
	}
	
	//4
	@Test
	public void findLargestNumberSmallIntB() {
		assertEquals(BigInteger.valueOf(84342312), LargestNumberSolver.findLargestNumber(smallIntB));
		
	}
	
	//5
	@Test
	public void findLargestLongSmallIntA() {
		assertEquals(9631811L, LargestNumberSolver.findLargestLong(smallIntA));				
	}
	
	//6
	@Test
	public void findLargestLongSmallIntB() {
		assertEquals(84342312L, LargestNumberSolver.findLargestLong(smallIntB));			
	}
	
	//7
	@Test
	public void sumSmallIntList() {
		assertEquals(BigInteger.valueOf(84342312L + 9631811L), LargestNumberSolver.sum(smallIntList));	
	}
	
	//8
	@Test
	public void findKthLargestZero() {
		smallIntList.add(smallIntC);
		smallIntList.add(smallIntD);
		assertTrue(smallIntB.equals(LargestNumberSolver.findKthLargest(smallIntList, 0)));	
	}
	
	//9
	@Test public void findKthLargestOne() {
		smallIntList.add(smallIntC);
		smallIntList.add(smallIntD);
		assertTrue(smallIntC.equals(LargestNumberSolver.findKthLargest(smallIntList, 1)));		
	}
	
	//10
	@Test public void findKthLargestTwo() {
		smallIntList.add(smallIntC);
		smallIntList.add(smallIntD);
		assertTrue(smallIntD.equals(LargestNumberSolver.findKthLargest(smallIntList, 2)));		
	}
	
	//11
	@Test public void findKthLargestThree() {
		smallIntList.add(smallIntC);
		smallIntList.add(smallIntD);
		assertTrue(smallIntA.equals(LargestNumberSolver.findKthLargest(smallIntList, 3)));			
	}
	
	
	  //12	  
	  @Test public void basicReadFile() { testFile = new ArrayList<Integer[]>();
	  testFile = LargestNumberSolver.readFile(
	  "C:\\Users\\Stone\\eclipse-workspace\\CS_2420\\src\\assign04\\integers.txt");
	  Integer[] testArray = new Integer[] {88, 51};
	  assertTrue(testArray.equals(testFile.get(7)));
	  }
	 
	
	
	//13
	@Test public void sumReadFile() {
		testFile = new ArrayList<Integer[]>();
			testFile = LargestNumberSolver.readFile("C:\\Users\\Stone\\eclipse-workspace\\CS_2420\\src\\assign04\\integers.txt");		
		ArrayList<Integer[]> temp = new ArrayList<Integer[]>();
		BigInteger num1 = LargestNumberSolver.findLargestNumber(testFile.get(327));
		BigInteger num2 = LargestNumberSolver.findLargestNumber(testFile.get(562));
		BigInteger num3 = LargestNumberSolver.findLargestNumber(testFile.get(589));
		BigInteger num4 = LargestNumberSolver.findLargestNumber(testFile.get(648));
		BigInteger num5 = LargestNumberSolver.findLargestNumber(testFile.get(192));
		
		temp.add(testFile.get(327));
		temp.add(testFile.get(562));
		temp.add(testFile.get(589));
		temp.add(testFile.get(648));
		temp.add(testFile.get(192));
		
		BigInteger sum = num1.add(num2);
		sum = sum.add(num3);
		sum = sum.add(num4);
		sum = sum.add(num5);
		
		assertEquals(sum, LargestNumberSolver.sum(temp));
			
	}
	
}
