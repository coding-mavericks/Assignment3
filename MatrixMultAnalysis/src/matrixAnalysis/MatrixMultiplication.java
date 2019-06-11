package matrixAnalysis;

import java.util.Random;

public class MatrixMultiplication {
	
	
	
	
	public static void main(String args[]) {
		MatrixMultiplication m = new MatrixMultiplication();
		
		int i = 0;
		int j = 0;
		int g = 0;
		
		while(true) {
		g++;
		int n = g;
		int[][] matrix = m.generateMatrix(n);
		
		
		
			System.out.println("Result matrix"  + " is "); 
			for (i = 0; i < matrix.length; i++) 
				{ 
					for (j = 0; j < matrix.length; j++) 
						System.out.print( matrix[i][j]  + " "); 
					System.out.println(); 
				} 
			System.out.println();
			System.out.println();
			System.out.println();
			if(g==30) {
				break;
				
			}
		}
			
	}
	
	public static int[][] generateMatrix(int n) {
		Random r = new Random();
		int[][] matrix = new int[n][n];

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				matrix[i][j] = r.nextInt(100);
			}
		}
		return matrix;
	}

}
