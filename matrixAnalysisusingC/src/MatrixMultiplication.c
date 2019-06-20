
#include<stdio.h>
#include<stdlib.h>
#include <time.h>
clock_t start, end;
double cpu_time_used;
typedef enum { false, true } bool;
int N;
int **mat1, **mat2,**res,i,j,N,k;
int x,y,g,j;

 int main(){

 g = 0;

 for(x = 0 ;true;  y++)
{
 N = pow(2,g);
 //N = 4096;
 printf("the value of N is %d", N);
 g= g+1;
//free(res);


 mat1= (int**) malloc(N*sizeof(int*));

 mat2= (int**) malloc(N*sizeof(int*));

 res=(int**)calloc(N,sizeof(int*));

 int i, o;

 srand(time(NULL));
 for(o = 0; o<N; o++)
 {

	 mat1[o]=(int*)malloc(N*sizeof(int));
	 mat2[o]=(int*)malloc(N*sizeof(int));
	 res[o]=(int*)calloc(N,sizeof(int));
	 for(i = 0; i<N; i++)
	 {

	 	         // mat1[i]=(int*)malloc(N*sizeof(int));
	 	          mat1[o][i] = (rand() % 1000000)+1;

	 	         // mat2[i]=(int*)malloc(N*sizeof(int));
	 	          mat2[o][i] = (rand() % 1000000)+1;
	 	         // res[i]=(int*)calloc(N,sizeof(int));

	 }
}


	 	   //  long int res[N][N]; // To store result
start = clock();
	 	     //classicMatrix(mat1, mat2, res, N);
for(i=0;i<N;i++)
{
	 	  for(j=0;j<N;j++)
	 	  {
	 	            res[i][j]=0;
	 	                 for(k=0; k < N;k++)
	 	                 {
	 	                     res[i][j]+= mat1[i][k]*mat2[k][j];
	 	                    //free(res[i]);
	 	                 }


	 	   }

	 	   //printf("\n");
}
free(mat1);
free(mat2);
free(res);


end = clock();
double r = end - start;
cpu_time_used = ((double) r) / CLOCKS_PER_SEC;

printf("\n");

printf("Result matrix for N = %d  time taken is %lf  \n", N, cpu_time_used);
if(N == 1024) break;
	 	      }

return 0;

 }
//Computation


//Multiplication
void classicMatrix(int mat1[][N], int mat2[][N], int res[][N], int N)
{
    for(i=0;i<N;i++){
        for(j=0;j<N;j++){
                res[i][j]=0;
                for(k=0; k < N;k++){
                    res[i][j]+= mat1[i][k]*mat2[k][j];
                }

        }
       //2 free(res);
        printf("\n");
    }

}







