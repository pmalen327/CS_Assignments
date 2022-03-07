package assign01;

/**
 * This class represents a simple row or column vector of numbers.
 * In a row vector, the numbers are written horizontally (i.e., along the columns).
 * In a column vector, the numbers are written vertically (i.e., along the rows).
 * 
 * @author Preston Malen//u0984531
 * @version January 20, 2021
 */
public class MathVector {

	// 2D array to hold the numbers of the vector, either along the columns or rows
	private double[][] data;      
	// set to true for a row vector and false for a column vector
	private boolean isRowVector;
	// count of elements in the vector
	private int vectorSize;
	
	/**
	 * Creates a new row or column vector.
	 * For a row vector, the input array is expected to have 1 row and a positive number of columns,
	 * and this number of columns represents the vector's length.
	 * For a column vector, the input array is expected to have 1 column and a positive number of rows,
	 * and this number of rows represents the vector's length.
	 * 
	 * @param data - a 2D array to hold the numbers of the vector
	 * @throws IllegalArgumentException if the numbers of rows and columns in the input 2D array is not 
	 *         compatible with a row or column vector
	 */
	public MathVector(double[][] data) {
		
		if(data.length == 0)
			throw new IllegalArgumentException("Number of rows must be positive.");
		if(data[0].length == 0)
			throw new IllegalArgumentException("Number of columns must be positive.");
		
		if(data.length == 1) {
			// This is a row vector with length = number of columns.
			this.isRowVector = true;
			this.vectorSize = data[0].length;
		}
		else if(data[0].length == 1) {
			// This is a column vector with length = number of rows.
			this.isRowVector = false;
			this.vectorSize = data.length;
		}
		else
			throw new IllegalArgumentException("Either the number of rows or the number of columns must be 1.");
		
		// Create the array and copy data over.
		if(this.isRowVector)
			this.data = new double[1][vectorSize];
		else
			this.data = new double[vectorSize][1];
		for(int i=0; i < this.data.length; i++) { 
			for(int j=0; j < this.data[0].length; j++) {
				this.data[i][j] = data[i][j];
			}
		}
	}
	
	/**
	 * Determines whether this vector is "equal to" another vector, where equality is
	 * defined as both vectors being row (or both being column), having the same 
	 * vector length, and containing the same numbers in the same positions.
	 * 
	 * @param other - another vector to compare
	 */
	public boolean equals(Object other) {
		
		if(!(other instanceof MathVector))
			return false;
		
		//this counter will act as a switch later in the loop
		int counter = 0;
		
		MathVector otherVec = (MathVector)other;
		
		if(this.isRowVector != otherVec.isRowVector)
			throw new IllegalArgumentException("Vectors must be the same type!!");
		else if (this.isRowVector == otherVec.isRowVector)
			if (this.vectorSize != otherVec.vectorSize)
				throw new IllegalArgumentException("Vectors must be the same size!!");
			else if (this.isRowVector)
				for(int i = 0; i < otherVec.vectorSize; i ++)
					if(otherVec.data[0][i] == this.data[0][i])
						counter = 0;
					else
						counter ++;
			else
				for(int i = 0; i < otherVec.vectorSize; i ++)
					if(otherVec.data[i][0] == this.data[i][0])
						counter = 0;
					else
						counter ++;
			
		//If the counter was modified at all then the vectors had a mismatched component
		//and are therefore not equal. Will seek a more elegant and concise way to do this.
		if(counter != 0)
			return false;
		else
			return true;
				
	}
	
	/**
	 * Generates a returns a new vector that is the transposed version of this vector.
	 */
	public MathVector transpose() {
		
		int size = this.vectorSize;
		double[][] colOut = new double[size][1];
		double[][] rowOut = new double[1][size];
		
		//if the vector is of size one, it will simply change the type
		if(this.isRowVector && this.vectorSize == 1) {
			this.isRowVector = false;
			MathVector transposed = new MathVector(this.data);
			return transposed;
		}
		
		else if(this.isRowVector == false && this.vectorSize == 1) {
			this.isRowVector = true;
			MathVector transposed = new MathVector(this.data);
			return transposed;
			
		}
		else if(this.isRowVector) {
			this.isRowVector = false;
			for(int i = 0; i < size; i++)
				colOut[i][0] = this.data[0][i];
			MathVector transposed = new MathVector(colOut);
			return transposed;
		}
		else {
			this.isRowVector = true;
			for(int i = 0; i < size; i++)
				rowOut[0][i] = this.data[i][0];
			MathVector transposed = new MathVector(rowOut);
			return transposed;
		}
			
		
	}
	
	/**
	 * Generates and returns a new vector representing the sum of this vector and another vector.
	 * 
	 * @param other - another vector to be added to this vector
	 * @throws IllegalArgumentException if the other vector and this vector are not both row vectors of
	 *         the same length or column vectors of the same length
	 */
	public MathVector add(MathVector other) {
		
		int size = this.vectorSize;
		double[][] sumRow = new double[1][size];
		double[][] sumCol = new double[size][1];
		
		
		if(this.vectorSize != other.vectorSize) {
			throw new IllegalArgumentException("Vectors must be the same size!!");
		}
		else if(this.isRowVector != other.isRowVector) {
			throw new IllegalArgumentException("Vectors must be the same type!!");
		}
		else if(this.isRowVector && other.isRowVector) {
			for(int i = 0; i < size; i++)
				sumRow[0][i] += (this.data[0][i] + other.data[0][i]);
			
		}
		
		else if(this.isRowVector == false && other.isRowVector == false) {
			for(int i = 0; i < size; i++)
				sumCol[i][0] += (this.data[i][0] + other.data[i][0]);
						
		}
		
		
		
		if(this.isRowVector && other.isRowVector) {
			MathVector sumR = new MathVector(sumRow);
			return sumR;
		}
		else {
			MathVector sumC = new MathVector(sumCol);
			return sumC;
		}	
	}
	
	/**
	 * Computes and returns the dot product of this vector and another vector.
	 * 
	 * @param other - another vector to be combined with this vector to produce the dot product
	 * @throws IllegalArgumentException if the other vector and this vector are not both row vectors of
	 *         the same length or column vectors of the same length
	 */	
	public double dotProduct(MathVector other) {
		
		double sum = 0;
		if(this.isRowVector != other.isRowVector) 
			throw new IllegalArgumentException("Vectors must be the same type!!");
				
		else 
			if(this.isRowVector) 
				for(int i = 0; i < this.vectorSize; i++)
					sum += (this.data[0][i] * other.data[0][i]);		
			else if(this.isRowVector == false)
				for(int i = 0; i < this.vectorSize; i++)
					sum += (this.data[i][0] * other.data[i][0]);
															
		return sum;
	}
	
	/**
	 * Computes and returns this vector's magnitude (also known as a vector's length) .
	 */
	public double magnitude() {
		double sum = 0;
		double mag = 0;	
		
		if(this.isRowVector)
			for(int i = 0; i < this.vectorSize; i++)
				sum += Math.pow(this.data[0][i], 2);
		else
			for(int i = 0; i < this.vectorSize; i++)
				sum += Math.pow(this.data[i][0], 2);
		
		mag = Math.sqrt(sum);
			
	return mag;
	}
	
	/** 
	 * Generates and returns a normalized version of this vector.
	 */
	public MathVector normalize() {
		
		double mag = this.magnitude();
		double[][] rowVec = new double[1][this.vectorSize];
		double[][] colVec = new double[this.vectorSize][1];
		
		if(this.isRowVector) {
			for(int i = 0; i < this.vectorSize; i++)
				rowVec[0][i] = (this.data[0][i] / mag);
				MathVector rowNorm = new MathVector(rowVec);
				return rowNorm;
			}	
		else {
			for(int i = 0; i < this.vectorSize; i++)
				colVec[i][0] = (this.data[i][0] / mag);
				MathVector colNorm = new MathVector(colVec);
				return colNorm;
		}		
	}
	
	/**
	 * Generates and returns a textual representation of this vector.
	 * For example, "1.0 2.0 3.0 4.0 5.0" for a sample row vector of length 5 and 
	 * "1.0
	 *  2.0
	 *  3.0
	 *  4.0
	 *  5.0" for a sample column vector of length 5. 
	 *  In both cases, notice the lack of a newline or space after the last number.
	 */
	public String toString() {
		
		if(this.isRowVector) {
			String rowVec = "";
			for(int i = 0; i < this.vectorSize; i++)
				rowVec += this.data[0][i];
			return rowVec;
		}
		else {
			String colVec = "";
			for(int i = 0; i < this.vectorSize - 1; i++)
				colVec += this.data[i][0] + "\n";
			colVec += this.data[this.vectorSize - 1][0];
			return colVec;							
		}			
	}
}
