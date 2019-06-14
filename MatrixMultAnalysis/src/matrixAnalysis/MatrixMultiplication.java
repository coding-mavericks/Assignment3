package matrixAnalysis;

import java.util.Random;

public class MatrixMultiplication {
	
	
	
	
	public static void main(String args[]) {
		MatrixMultiplication m = new MatrixMultiplication();
		
		int i = 0;
		int j = 0;
		int g = 0;
		int s = 0;
		
		while(true) {
		
		s=(int) Math.pow(2,g);
		g=g+1;
		int n = s;
		int[][] matrixA = m.generateMatrix(n);
		int[][] matrixB = m.generateMatrix(n);
		
	
		
		
		
			/*System.out.println("Result matrix"  + " is "); 
			for (i = 0; i < matrixA.length; i++) 
				{ 
					for (j = 0; j < matrixA.length; j++) 
						System.out.print( matrixA[i][j]  + " "); 
					System.out.println(); 
				} */
			long startTime  = System.nanoTime();
			m.classicMatrixMult(matrixA, matrixB, n);
			long endTime  = System.nanoTime();
			long timeRequired = endTime - startTime;
			System.out.println("Total time required to run is "+" for N = "+ n +" is "+ timeRequired +" milliseconds" );
			System.out.println();
			System.out.println();
			System.out.println();
			
			if(s==256) {
				break;
				
			}
			
			
		}
			
	}
	
	
	
	private void classicMatrixMult(int[][] matrixA, int[][] matrixB, int n) {
		
		//int[][] outputMatrix = new int[N][N];
		int[][] C = new int[n][n];

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				C[i][j] = 0;
			}
		}

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				for (int k = 0; k < n; k++) {
					C[i][j] += matrixA[i][k] * matrixB[k][j];
				}
			}
		}
		/*for( int i = 0; i < N; i += 1 )
			for( int j = 0; j < N; j += 1 ) {
				outputMatrix[i][j] = 0;
				for( int k = 0; k < N; k += 1 )
					outputMatrix[i][j] += matrixA[i][k] * matrixB[k][j];
			}*/
		System.out.println("Matrix multiplication done");
		
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
