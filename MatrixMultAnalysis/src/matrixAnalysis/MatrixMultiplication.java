package matrixAnalysis;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

public class MatrixMultiplication {
	
	public static void main(String args[]) {
		MatrixMultiplication m = new MatrixMultiplication();
		final int NoOfTIMES = 1;
		int g = 0;
		int s = 0;

		long timeRequiredClassic= 0;
		long timeRequiredBlock=0;
		long timeRequiredDC = 0;
		long timeRequiredstrassen = 0;
		long timeRequiredClassicThread=0;
		long timeRequiredOptimized=0;
		
		    XYSeriesCollection dataset = new XYSeriesCollection();
		    XYSeries series1 = new XYSeries("Classic Matrix");
		   // XYSeries series2 = new XYSeries("Block Matrix");
		    XYSeries series3 = new XYSeries("Divide and Conquer");
		    XYSeries series4 = new XYSeries("Strassens Matrix");
		    XYSeries series5 = new XYSeries("Classic Thread Matrix");
		    XYSeries series6 = new XYSeries("Optimized Algorithm");
		DefaultCategoryDataset datasetClassic = new DefaultCategoryDataset( );
		DefaultCategoryDataset datasetDC = new DefaultCategoryDataset( );
		DefaultCategoryDataset datasetStra = new DefaultCategoryDataset( );
		while(true) {
		s=(int) Math.pow(2,g);
			//s=4096;
			
		    g=g+1;
		    int n = s;
		    int[][] matrixA = m.generateMatrix(n);
		    int[][] matrixB = m.generateMatrix(n);
		    int[][] matrixC = new int[n][n];
		    if(n==1) {
		    	matrixC[0][0] = matrixA[0][0] * matrixB[0][0];
		    	System.out.println(matrixC[0][0]+" is the output and time required is 0");
		    }
		    else {
		    boolean flag=MatrixMultiplication.checkInput(matrixA, matrixB);	
		    if(flag)
		    {
		    for (int j = 0; j < NoOfTIMES; j++) {
		    	
		    //System.out.println("1");
			long startTimeClassic  = System.nanoTime();
		    m.classicMatrixMult(matrixA, matrixB, n);
			long endTimeClassic   = System.nanoTime();
			timeRequiredClassic+= endTimeClassic  - startTimeClassic ; 
			
			
			long startOptimized  = System.nanoTime();
		    m.optimizedAlgorithm(matrixA, matrixB,matrixC, n);
			long endOptimized   = System.nanoTime();
			timeRequiredOptimized += endOptimized  - startOptimized ;
            
	

					/*
					 * long startTimeBlock = System.nanoTime(); m.blockMatrix(matrixA, matrixB, n);
					 * long endTimeBlock = System.nanoTime(); timeRequiredBlock+= endTimeBlock -
					 * startTimeBlock ;
					 */



			long startTimeDC  = System.nanoTime();
			m.divideAndConquerMatrixMult(matrixA, matrixB, n);
			long endTimeDC  = System.nanoTime();
			timeRequiredDC += endTimeDC - startTimeDC;
			
			//System.out.println("1");
					
					  long startTimestrassen = System.nanoTime(); m.strassenMatrixMult(matrixA,
					  matrixB, n); long endTimestrassen = System.nanoTime();
					 
			
			timeRequiredstrassen += endTimestrassen - startTimestrassen;

			
			//System.out.println("1");
			long startTimeClassicThread = System.nanoTime();
			if (n < 2048)
			m.classicThreadImp(matrixA, matrixB,matrixC, n,10);
			else {
			m.classicThreadImp(matrixA, matrixB,matrixC, n,1000);
			}
			long endTimeClassicThread = System.nanoTime(); 
			
			timeRequiredClassicThread += endTimeClassicThread - startTimeClassicThread;

			
			//System.out.println("Total time required to run Classic is "+" for N = "+ n +" is "+ timeRequiredClassic +" milliseconds" );
			//System.out.println("Total time required to run Divide & Conquer is "+" for N = "+ n +" is "+ timeRequiredDC +" milliseconds" );
			//System.out.println("Total time required to run Strassen's is "+" for N = "+ n +" is "+ timeRequiredstrassen +" milliseconds" );
			
			//dataset.addValue( timeRequiredClassic , "Size of N" , Integer.toString(n) );
			//System.out.println();
			//System.out.println();
		    }
		    timeRequiredClassic = timeRequiredClassic / NoOfTIMES/1000000;
		    timeRequiredBlock = timeRequiredBlock / NoOfTIMES/1000000;
		    timeRequiredDC = timeRequiredDC/NoOfTIMES/ 10000000;
			timeRequiredstrassen = timeRequiredstrassen/ NoOfTIMES/1000000;
            timeRequiredOptimized = timeRequiredOptimized / NoOfTIMES/1000000;
			timeRequiredClassicThread = timeRequiredClassicThread/NoOfTIMES/1000000;
			

			
				
				  series1.add(n,timeRequiredClassic); 
				  //series2.add(n,timeRequiredBlock);
				  series3.add(n,timeRequiredDC);
				 
			series4.add(n,timeRequiredstrassen);
				 series5.add(n,timeRequiredClassicThread); 
			series6.add(n,timeRequiredOptimized);

			
			
			
		
			
			System.out
					.println("For n="
							+ n
							+ ": \n\t Classic Matrix Multiplication time: "
							+ timeRequiredClassic
							+ " milliseconds.\n\t Strassen's Matrix Multiplication time:"
							
								/*
								 * + timeRequiredBlock +
								 * " milliseconds.\n\t Strassen's Matrix Multiplication time: "
								 */
								 
							+ timeRequiredstrassen
							+ "milliseconds.\n\t Divide and Conquer time: "
							+ timeRequiredDC
							+ "milliseconds.\n\t Classic with Thread time "
							+ timeRequiredClassicThread
						
							
							+ "milliseconds.\n\t Optimized Matrix Multiplication time : "
							+ timeRequiredOptimized + "milliseconds");

			
			
			
			
			if(s == 64) {
					
					  dataset.addSeries(series1); 
					  //dataset.addSeries(series2);
					  dataset.addSeries(series3);
					 
				    dataset.addSeries(series4);
					 dataset.addSeries(series5); 
				    dataset.addSeries(series6);
				
			          Chart chart = new Chart(
				         "Time Taken Vs Size of Matrix" ,
				         "Time Taken vs Size of Matrix", dataset);
				      chart.pack( );
				     // RefineryUtilities.centerFrameOnScreen( chart );
				      chart.setVisible( true ); 
				break;
			
			}
			
		    }else {
		    	System.out.println("Invalid Matrix");
		    	break;
		    }
		}
		}
		
		
	
	}
	
	
	
	
	
	private void optimizedAlgorithm(int[][] matrixA, int[][] matrixB, int[][] matrixC, int n) {
		// TODO Auto-generated method stub
		if (n <=32) {
			this.classicMatrixMult(matrixA, matrixB, n);
		} else if (n <= 512) {
			this.blockMatrix(matrixA, matrixB, n);
		} else if (n <= 2048) {
			this.classicThreadImp(matrixA, matrixB, matrixC, n, 10);
		} else if (n <= 4096){
			this.classicThreadImp(matrixA, matrixB, matrixC, n, 1000);
		}else {
			this.strassenMatrixOptimizedMult(matrixA, matrixB, n);
		}
		
	}





	private int[][] classicMatrixMult(int[][] matrixA, int[][] matrixB, int n) {
		
		
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
		
       return C;
	}
		
		
	
	
	private int[][]  blockMatrix(int[][] matrixA , int [][] matrixB , int n) {
	    int MATRIX_SIZE = n;
	    int block_size = 0;
	    int[][] product = new int[n][n];
	    if (n == 1024) {
	    	    block_size = 256;
	    } else  if (n == 2) {
	    	block_size = 2;
	    } else if (n == 4) {
	    	block_size = 2;
	    } else if (n == 8) {
	    	block_size = 4;
	    } else if (n == 16) {
	    	block_size = 4;
	    } else if (n == 32) {
	    	block_size = 8;
	    } else if(n == 64) {
	    	block_size = 16;
	    } else if (n == 128) {
	    	block_size = 32;
	    } else if (n == 256) {
	    	block_size = 128;
	    } else {
	    	block_size = 256;
	    }
		for (int k = 0; k < MATRIX_SIZE; k += block_size)
			for (int j = 0; j < MATRIX_SIZE; j += block_size)
				for (int i = 0; i < MATRIX_SIZE; ++i)
					for (int jj = j; jj < min(j + block_size, MATRIX_SIZE); ++jj)
						for (int kk = k; kk < min(k + block_size, MATRIX_SIZE); ++kk)
							product[i][jj] += matrixA[i][kk] * matrixB[kk][jj];
		
		
		return product;
		
	
	}
	
	public int min(int A , int B) {
	if(A<B) {
		return A;
	}else {
		return B;
	}
		
	}
	
	
	public int[][] divideAndConquerMatrixMult(int[][] matrixA, int[][] matrixB, int n) {
		int[][] matrixC = new int[n][n];

		if (n == 1) {
			matrixC[0][0] = matrixA[0][0] * matrixB[0][0];
			return matrixC;
		} else {
			int[][] A11 = new int[n / 2][n / 2];
			int[][] A12 = new int[n / 2][n / 2];
			int[][] A21 = new int[n / 2][n / 2];
			int[][] A22 = new int[n / 2][n / 2];
			int[][] B11 = new int[n / 2][n / 2];
			int[][] B12 = new int[n / 2][n / 2];
			int[][] B21 = new int[n / 2][n / 2];
			int[][] B22 = new int[n / 2][n / 2];

			split(matrixA, A11, 0, 0);
			split(matrixA, A12, 0, n / 2);
			split(matrixA, A21, n / 2, 0);
			split(matrixA, A22, n / 2, n / 2);
			split(matrixB, B11, 0, 0);
			split(matrixB, B12, 0, n / 2);
			split(matrixB, B21, n / 2, 0);
			split(matrixB, B22, n / 2, n / 2);

			int[][] C11 = addMatrix(divideAndConquerMatrixMult(A11, B11, n / 2),
					divideAndConquerMatrixMult(A12, B21, n / 2), n / 2);
			int[][] C12 = addMatrix(divideAndConquerMatrixMult(A11, B12, n / 2),
					divideAndConquerMatrixMult(A12, B22, n / 2), n / 2);
			int[][] C21 = addMatrix(divideAndConquerMatrixMult(A21, B11, n / 2),
					divideAndConquerMatrixMult(A22, B21, n / 2), n / 2);
			int[][] C22 = addMatrix(divideAndConquerMatrixMult(A21, B12, n / 2),
					divideAndConquerMatrixMult(A22, B22, n / 2), n / 2);

			combine(C11, matrixC, 0, 0);
			combine(C12, matrixC, 0, n / 2);
			combine(C21, matrixC, n / 2, 0);
			combine(C22, matrixC, n / 2, n / 2);
		}

		return matrixC;
	}
	public int[][] strassenMatrixMult(int[][] matrixA, int[][] matrixB, int n) {
		int[][] C = new int[n][n];
		strassenMatrixMultFormula(matrixA, matrixB, C, n);
		return C;
	}
	
	
	public void strassenMatrixMultFormula(int[][] matrixA, int[][] matrixB, int[][] matrixC, int n) {
     
         if(n==2) 
        {
			matrixC[0][0] = (matrixA[0][0] * matrixB[0][0]) + (matrixA[0][1] * matrixB[1][0]);
			matrixC[0][1] = (matrixA[0][0] * matrixB[0][1]) + (matrixA[0][1] * matrixB[1][1]);
			matrixC[1][0] = (matrixA[1][0] * matrixB[0][0]) + (matrixA[1][1] * matrixB[1][0]);
			matrixC[1][1] = (matrixA[1][0] * matrixB[0][1]) + (matrixA[1][1] * matrixB[1][1]);
		} else {
			int[][] matrixA11 = new int[n / 2][n / 2];
			int[][] matrixA12 = new int[n / 2][n / 2];
			int[][] matrixA21 = new int[n / 2][n / 2];
			int[][] matrixA22 = new int[n / 2][n / 2];
			int[][] matrixB11 = new int[n / 2][n / 2];
			int[][] matrixB12 = new int[n / 2][n / 2];
			int[][] matrixB21 = new int[n / 2][n / 2];
			int[][] matrixB22 = new int[n / 2][n / 2];

			int[][] matrixP = new int[n / 2][n / 2];
			int[][] matrixQ = new int[n / 2][n / 2];
			int[][] matrixR = new int[n / 2][n / 2];
			int[][] matrixS = new int[n / 2][n / 2];
			int[][] matrixT = new int[n / 2][n / 2];
			int[][] matrixU = new int[n / 2][n / 2];
			int[][] matrixV = new int[n / 2][n / 2];

			split(matrixA, matrixA11, 0, 0);
			split(matrixA, matrixA12, 0, n / 2);
			split(matrixA, matrixA21, n / 2, 0);
			split(matrixA, matrixA22, n / 2, n / 2);
			split(matrixB, matrixB11, 0, 0);
			split(matrixB, matrixB12, 0, n / 2);
			split(matrixB, matrixB21, n / 2, 0);
			split(matrixB, matrixB22, n / 2, n / 2);

			strassenMatrixMultFormula(addMatrix(matrixA11, matrixA22, n / 2),
					addMatrix(matrixB11, matrixB22, n / 2), matrixP, n / 2);
			strassenMatrixMultFormula(addMatrix(matrixA21, matrixA22, n / 2), matrixB11, matrixQ, n / 2);
			strassenMatrixMultFormula(matrixA11, subtractMatrix(matrixB12, matrixB22, n / 2), matrixR, n / 2);
			strassenMatrixMultFormula(matrixA22, subtractMatrix(matrixB21, matrixB11, n / 2), matrixS, n / 2);
			strassenMatrixMultFormula(addMatrix(matrixA11, matrixA12, n / 2), matrixB22, matrixT, n / 2);
			strassenMatrixMultFormula(subtractMatrix(matrixA21, matrixA11, n / 2),
					addMatrix(matrixB11, matrixB12, n / 2), matrixU, n / 2);
			strassenMatrixMultFormula(subtractMatrix(matrixA12, matrixA22, n / 2),
					addMatrix(matrixB21, matrixB22, n / 2), matrixV, n / 2);

			int[][] C11 = addMatrix(
					subtractMatrix(addMatrix(matrixP, matrixS, matrixP.length), matrixT, matrixT.length), matrixV,
					matrixV.length);
			int[][] C12 = addMatrix(matrixR, matrixT, matrixR.length);
			int[][] C21 = addMatrix(matrixQ, matrixS, matrixQ.length);
			int[][] C22 = addMatrix(
					subtractMatrix(addMatrix(matrixP, matrixR, matrixP.length), matrixQ, matrixQ.length), matrixU,
					matrixU.length);

			combine(C11, matrixC, 0, 0);
			combine(C12, matrixC, 0, n / 2);
			combine(C21, matrixC, n / 2, 0);
			combine(C22, matrixC, n / 2, n / 2);
		}
	}
	
	public int[][] strassenMatrixOptimizedMult(int[][] matrixA, int[][] matrixB, int n) {
		int[][] C = new int[n][n];
		strassenMatrixOptimizedMultFormula(matrixA, matrixB, C, n);
		return C;
	}
	
	
	public void strassenMatrixOptimizedMultFormula(int[][] matrixA, int[][] matrixB, int[][] matrixC, int n) {
		if (n <=32) {
			this.classicMatrixMult(matrixA, matrixB, n);
		} else if (n <= 512) {
			this.blockMatrix(matrixA, matrixB, n);
		} else if (n <= 2048) {
			this.classicThreadImp(matrixA, matrixB, matrixC, n, 10);
		} else if (n <= 4096){
			this.classicThreadImp(matrixA, matrixB, matrixC, n, 1000);
		}
        else if(n==2) 
        {
			matrixC[0][0] = (matrixA[0][0] * matrixB[0][0]) + (matrixA[0][1] * matrixB[1][0]);
			matrixC[0][1] = (matrixA[0][0] * matrixB[0][1]) + (matrixA[0][1] * matrixB[1][1]);
			matrixC[1][0] = (matrixA[1][0] * matrixB[0][0]) + (matrixA[1][1] * matrixB[1][0]);
			matrixC[1][1] = (matrixA[1][0] * matrixB[0][1]) + (matrixA[1][1] * matrixB[1][1]);
		} else {
			int[][] matrixA11 = new int[n / 2][n / 2];
			int[][] matrixA12 = new int[n / 2][n / 2];
			int[][] matrixA21 = new int[n / 2][n / 2];
			int[][] matrixA22 = new int[n / 2][n / 2];
			int[][] matrixB11 = new int[n / 2][n / 2];
			int[][] matrixB12 = new int[n / 2][n / 2];
			int[][] matrixB21 = new int[n / 2][n / 2];
			int[][] matrixB22 = new int[n / 2][n / 2];

			int[][] matrixP = new int[n / 2][n / 2];
			int[][] matrixQ = new int[n / 2][n / 2];
			int[][] matrixR = new int[n / 2][n / 2];
			int[][] matrixS = new int[n / 2][n / 2];
			int[][] matrixT = new int[n / 2][n / 2];
			int[][] matrixU = new int[n / 2][n / 2];
			int[][] matrixV = new int[n / 2][n / 2];

			split(matrixA, matrixA11, 0, 0);
			split(matrixA, matrixA12, 0, n / 2);
			split(matrixA, matrixA21, n / 2, 0);
			split(matrixA, matrixA22, n / 2, n / 2);
			split(matrixB, matrixB11, 0, 0);
			split(matrixB, matrixB12, 0, n / 2);
			split(matrixB, matrixB21, n / 2, 0);
			split(matrixB, matrixB22, n / 2, n / 2);

			strassenMatrixOptimizedMultFormula(addMatrix(matrixA11, matrixA22, n / 2),
					addMatrix(matrixB11, matrixB22, n / 2), matrixP, n / 2);
			strassenMatrixOptimizedMultFormula(addMatrix(matrixA21, matrixA22, n / 2), matrixB11, matrixQ, n / 2);
			strassenMatrixOptimizedMultFormula(matrixA11, subtractMatrix(matrixB12, matrixB22, n / 2), matrixR, n / 2);
			strassenMatrixOptimizedMultFormula(matrixA22, subtractMatrix(matrixB21, matrixB11, n / 2), matrixS, n / 2);
			strassenMatrixOptimizedMultFormula(addMatrix(matrixA11, matrixA12, n / 2), matrixB22, matrixT, n / 2);
			strassenMatrixOptimizedMultFormula(subtractMatrix(matrixA21, matrixA11, n / 2),
					addMatrix(matrixB11, matrixB12, n / 2), matrixU, n / 2);
			strassenMatrixOptimizedMultFormula(subtractMatrix(matrixA12, matrixA22, n / 2),
					addMatrix(matrixB21, matrixB22, n / 2), matrixV, n / 2);

			int[][] C11 = addMatrix(
					subtractMatrix(addMatrix(matrixP, matrixS, matrixP.length), matrixT, matrixT.length), matrixV,
					matrixV.length);
			int[][] C12 = addMatrix(matrixR, matrixT, matrixR.length);
			int[][] C21 = addMatrix(matrixQ, matrixS, matrixQ.length);
			int[][] C22 = addMatrix(
					subtractMatrix(addMatrix(matrixP, matrixR, matrixP.length), matrixQ, matrixQ.length), matrixU,
					matrixU.length);

			combine(C11, matrixC, 0, 0);
			combine(C12, matrixC, 0, n / 2);
			combine(C21, matrixC, n / 2, 0);
			combine(C22, matrixC, n / 2, n / 2);
		}
	}
	
	
	 private static boolean checkInput(int[][] matrixA, int[][] matrixB) 		    
	    {		
		    boolean flag=true;		
	        int p = matrixA.length;		
	        int q1= matrixB.length;		
	       		
	        		
	        if (p==0 ||q1==0)		
	        {   flag=false;		
	        	System.out.println("Matrix A or Matrix B is a Null matrix");		
	        			
	        }else{		
	        int n=p;		
	        int m=q1;		
	        while (m>1)		
	        {		
	            if (m%2 != 0)		
	            {		
	            	flag=false;		
	            	System.out.println("Matrix B is non-power of (2) matrix");		
	            			
	            }		
	            m/=2;		
	            		
	        }		
	        while (n>1)		
	        {		
	            if (n%2 != 0)		
	            {		
	            	flag=false;		
	            	System.out.println("Matrix A is non-power of (2) matrix");		
	            		
	            }		
	            n/=2;		
	        }		
	        		
	       		
	        if (matrixB.length != p)		
	        {		
	        	flag=false;		
	        	System.out.println("Matrix B and Matrix A are of inconsistent dimensions");		
	        			
	        }		
	        int q = matrixA[0].length;		
	        int r = matrixB[0].length;		
	        for (int i=1;i<matrixA.length;i++)		
	        {		
	            if ((matrixA[i].length != q))		
	            {		
	            	flag=false;		
	            	System.out.println("Matrix A is inconsistent matrix");		
	            			
	            }		
	            if ((q==0)||(r==0))		
		        {   flag=false;		
		            System.out.println("Either Matrix A or Matrix B are Empty.Please enter value in Matrix");		
		           		
		        }		
	            		
	        }		
	       		
	        for (int i=1; i<matrixB.length;i++)		
	        {		
	            if (matrixB[i].length != r)		
	            {		
	            	flag=false;		
	            	System.out.println("Matrix B is inconsistent matrix");		
	            			
	            }		
	        }		
	        }		
	        return flag;		
	    }
	
	
	private void split(int[][] initial_Matrix,
			int[][] new_Matrix, int m, int n) {
		int y = n;
		for (int i = 0; i < new_Matrix.length; i++) {
			for (int j = 0; j < new_Matrix.length; j++) {
				new_Matrix[i][j] = initial_Matrix[m][y++];
			}
			y = n;
			m++;
		}
	}
	/**
	 * Creates a new matrix based off of part of another matrix
	 * 
	 * @param initial_Matrix---
	 *            the initial matrix
	 * @param new_Matrix---
	 *            the new matrix created from the initial matrix
	 * @param m----
	 *            the initial row position of initialMatrix used when creating
	 *            newMatrix
	 * @param n---
	 *            the initial column position of initialMatrix used when
	 *            creating newMatrix
	 */
	private void combine(int[][] initial_Matrix,
			int[][] new_Matrix, int m, int n) {

		int y = n;

		for (int i = 0; i < initial_Matrix.length; i++) {
			for (int j = 0; j < initial_Matrix.length; j++) {
				new_Matrix[m][y++] = initial_Matrix[i][j];
			}
			y = n;
			m++;
		}
	}
	
	private int[][] addMatrix(int[][] matrixA, int[][] matrixB, int n) {

		int[][] matrixC = new int[n][n];

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				matrixC[i][j] = matrixA[i][j] + matrixB[i][j];
			}
		}
		return matrixC;
	}
	
	private int[][] subtractMatrix(int[][] matrixA, int[][] matrixB, int n) {

		int[][] matrixC = new int[n][n];

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				matrixC[i][j] = matrixA[i][j] - matrixB[i][j];
			}
		}
		return matrixC;
	}
	
	
	public int[][] generateMatrix(int n) {
		Random r = new Random();
		int[][] matrix = new int[n][n];

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				matrix[i][j] = r.nextInt(100);
			}
		}
		return matrix;
	}
	
	public void printMatrix(int [][] matrixC)
	{
		for (int i = 0; i < matrixC.length; i++) {
			for (int j = 0; j < matrixC.length; j++) {
				System.out.print(matrixC[i][j]+" ") ;
			}
			System.out.println();
		}
		
		System.out.println();
		System.out.println();
	}
	
	
	public void classicThreadImp(int[][] matrixA, int[][] matrixB, int [][] matrixC, int n, int threadpool)
	{
		//MatrixMultiplication ob = new MatrixMultiplication();
		
        /*** Commented Below code as printing huge matrix takes long time ***/

		
        long startTime = System.currentTimeMillis();

        // This method is for matrix multiplication without multithreading  
            long stopTime = System.currentTimeMillis();
            long elapsedTime = stopTime - startTime;
            //System.out.println(" \nExecution time of matrixMultiply function is : "+ elapsedTime +"ms\n");
    ///       printMatrix(A);
    /*** Commented Below code as printing huge matrix takes long time ***/

            //System.out.print(" \nPrint Matrix C without multithreading:\n\n");
            //printMatrix( C);



    /*** Below code is for matrix multiplication using multithreading ****/


            try{

            ExecutorService executor = Executors.newFixedThreadPool(threadpool);
            startTime = System.currentTimeMillis();
            for(int i=0;i<n;i++)
            {
                for(int j=0;j<n;j++)
                {
                	//System.out.println("i"+i+"j"+j);
                    RunnableC ob1 = new RunnableC(matrixA,  matrixB, matrixC, i,j, this, n);
                    executor.execute(ob1);
                }
            }

             executor.shutdown();
             while (!executor.isTerminated()) {
            }
            executor.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
            stopTime = System.currentTimeMillis();
            elapsedTime = stopTime - startTime;
            //System.out.println(" \nExecution time of matrixMultiply function  using multithreadingis : "+ elapsedTime +"ms\n");
            }catch(Exception e)
            {

            }
            
            

    }

    
	

}




class RunnableC implements Runnable
{
    int i,j;
    MatrixMultiplication ob;
    int [][] A;
    int [][] B;
    int [][] C;
    int n;
    RunnableC(int [][]matrixA, int [][]matrixB,int [][] matrixC,int ii, int jj, MatrixMultiplication o, int n)
    {

        i=ii;
        j=jj;
        A=matrixA;
        B=matrixB;
        C=matrixC;
        this.n=n;
    }

    /*** Below code of run method is performing matrix multiplication for Each cell  and placing output in the resultant
        matrix D  ***/  

     public void run()
    {

        int sum=0;

        for(int k=0;k<n;k++)
        {
        	//System.out.print("i"+i+"j"+j+"k"+k);
            sum+=A[i][k]*B[k][j];
            //System.out.print(A[i][k]+"___"+B[k][j]+"++ ");
            //System.out.println();
        }

        C[i][j]=sum;
        
       
    }
}
