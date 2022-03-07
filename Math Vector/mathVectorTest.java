package assign01;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * This tester class assesses the correctness of the Vector class.
 * 
 * IMPORTANT NOTE: The tests provided to get you started rely heavily on a 
 *                 correctly implemented equals method.  Be careful of false
 *                 positives (i.e., tests that pass because your equals method
 *                 incorrectly returns true). 
 * 
 * @author Preston Malen//u0984531
 * @version January 20, 2021
 */
class MathVectorTester {
	
	private MathVector rowVec, rowVecTranspose, unitVec, sumVec, colVec, vecA, vecB, vecC, vecD, vecE, vecF;

	@BeforeEach
	void setUp() throws Exception {
		// Creates a row vector with three elements: 3.0, 1.0, 2.0
		rowVec = new MathVector(new double[][]{{3, 1, 2}});
		
		// Creates a column vector with three elements: 3.0, 1.0, 2.0
		rowVecTranspose = new MathVector(new double[][]{{3}, {1}, {2}});
		
		// Creates a row vector with three elements: 1.0, 1.0, 1.0
		unitVec = new MathVector(new double[][]{{1, 1, 1}});
		
		// Creates a row vector with three elements: 4.0, 2.0, 3.0
		sumVec = new MathVector(new double[][]{{4, 2, 3}});
		
		// Creates a column vector with five elements: -11.0, 2.5, 36.0, -3.14, 7.1
		colVec = new MathVector(new double[][]{{-11}, {2.5}, {36}, {-3.14}, {7.1}});	
		
		
		// Vectors for tests added by Preston:
		
		//Two small row vectors
		vecA = new MathVector(new double[][]{{1, 4, 3}});
		vecB = new MathVector(new double[][]{{0, 5, 9}});
		
		//Two orthogonal row vectors
		vecC = new MathVector(new double[][]{{0, 6, -3}});
		vecD = new MathVector(new double[][]{{8, -2, -4}});
		
		//Larger column vectors
		vecE = new MathVector(new double[][]{{3}, {2.718}, {4.2}, {3.14}, {7.11}});	
		vecF = new MathVector(new double[][]{{1.1}, {-.354}, {1234}, {-38}, {1}});	
		
	}

	@AfterEach
	void tearDown() throws Exception {
	}
	
	@Test
	void smallRowVectorEquality() {
		assertTrue(rowVec.equals(new MathVector(new double[][]{{3, 1, 2}})));
	}
	
	@Test
	void smallRowVectorInequality() {
		assertFalse(rowVec.equals(unitVec));
	}

	@Test
	public void createVectorFromBadArray() {
	  double arr[][] = {{1, 2}, {3, 4}};
	  assertThrows(IllegalArgumentException.class, () -> { new MathVector(arr); });
	  // NOTE: The code above is an example of a lambda expression. See Lab 1 for more info.
	}
	
	@Test
	void transposeSmallRowVector() {
		MathVector transposeResult = rowVec.transpose();
		assertTrue(transposeResult.equals(rowVecTranspose));
	}
	
	@Test
	public void addRowAndColVectors() {
	  assertThrows(IllegalArgumentException.class, () -> { rowVec.add(colVec); });
	  // NOTE: The code above is an example of a lambda expression. See Lab 1 for more info.
	}
	
	@Test
	void addSmallRowVectors() {
		MathVector addResult = rowVec.add(unitVec);
		assertTrue(addResult.equals(sumVec));		
	}

	@Test
	void dotProductSmallRowVectors() {
		double dotProdResult = rowVec.dotProduct(unitVec);
		assertEquals(dotProdResult, 3.0 * 1.0 + 1.0 * 1.0 + 2.0 * 1.0);		
	}
	
	@Test
	void smallRowVectorLength() {
		double vecLength = rowVec.magnitude();
		assertEquals(vecLength, Math.sqrt(3.0 * 3.0 + 1.0 * 1.0 + 2.0 * 2.0));		
	}
	
	@Test
	void smallRowVectorNormalize() {
		MathVector normalVec = rowVec.normalize();
		double length = Math.sqrt(3.0 * 3.0 + 1.0 * 1.0 + 2.0 * 2.0);
		assertTrue(normalVec.equals(new MathVector(new double[][]{{3.0 / length, 1.0 / length, 2.0 / length}})));		
	}
	
	@Test
	void smallColVectorToString() {
		String resultStr = "-11.0\n2.5\n36.0\n-3.14\n7.1";
		assertEquals(resultStr, colVec.toString());		
	}
	
	
	//Tests added by Preston

	@Test //testing dotProduct and magnitude in a single test
	void cauchySchwarzInequality() {
		double LHS = Math.abs(vecA.dotProduct(vecB));
		double RHS = vecA.magnitude() * vecB.magnitude();
		assertTrue(LHS <= RHS);				
	}

	@Test //testing add and magnitude in a single test
	void triangleInequality() {
		double LHS = vecC.add(vecD).magnitude();
		double RHS = vecC.magnitude() + vecD.magnitude();
		assertTrue(LHS <= RHS);		
	}
	
	@Test //testing two orthogonal vectors for a zero dot product
	void zeroDotProduct() {
		assertEquals(vecD.dotProduct(vecC), 0);		
	}
	
	@Test //testing dot product and magnitude
	void angleBetweenVectors() {
		double numerator = vecC.dotProduct(vecD);
		double denominator = (vecC.magnitude()) * (vecD.magnitude());
		double theta = Math.acos(numerator / denominator);
		assertEquals(theta, .5*(Math.PI));		
	}
	
	@Test //testing dot product of larger column vectors
	void largeDotProduct() {
		assertEquals(vecE.dotProduct(vecF), (3 * 1.1) + (2.718 * -.354) + (4.2 * 1234) + (3.14 * -38) + (7.11 * 1));			
	}
	
	@Test //testing the adding of multiple vectors
	void polyAdd() {
		MathVector testSum = new MathVector(new double[][]{{1, 15, 9}});
		assertEquals((vecA.add(vecB).add(vecC)), testSum);		
	}
	
	@Test //dotting a singular column vector with itself
	void polyDotCol() {
		assertEquals(vecE.dotProduct(vecE), Math.pow(vecE.magnitude(), 2));				
	}
	
	@Test //dotting a singular row vector with itself
	void polyDotRow() {
		assertEquals(vecB.dotProduct(vecB), (int)Math.pow(vecB.magnitude(), 2)); //for some reason this was giving me problems, when kept as a double, the expected value added the extra n 																				 //number of zeroes and then a 1 after the decimal, something to do with how java interprets doubles
	}
	
}
