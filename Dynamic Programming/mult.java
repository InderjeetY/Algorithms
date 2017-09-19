import java.util.*;
import java.lang.*;
import java.io.*;

//Matrix Class, will have the row and column number of the matrix
class Matrix {
    private int row,col;
    public Matrix(int row, int col) {
        this.col = col;
        this.row = row;
    }
    public int get_row() {
        return this.row;
    }
    public int get_col() {
        return this.col;
    }
    public void set_row(int row) {
        this.row = row;
    }
    public void set_col(int col) {
        this.col = col;
    }
}

class mult
{
    public static void main (String[] args) throws java.lang.Exception
    {
        Scanner input = new Scanner(System.in);
        
        //get number of matrices
        int no_of_matrices = input.nextInt();
        
        //Array of Matrix class
        Matrix[] matrices = new Matrix[no_of_matrices];
        
        //Storing the number of rows of first matrix
        int prev = input.nextInt(), next, i=-1;
        
        //Create matrix classes with the row and column
        while(input.hasNext()) {
            next = input.nextInt();
            matrices[++i] = new Matrix(prev,next);
            prev = next;
        }
        
        //spce to store the cost of matrix multipliation, will have k value in the lower half of matrix
        int [][] dp = new int[no_of_matrices][no_of_matrices];
        int min,val,j;
        
        //Dynamic programming code
        for(int p=1;p<no_of_matrices;p++) {
            for(i=0; i<no_of_matrices-p;i++) {
                j=p+i;
                min = -1;
                for(int k=i;k<j;k++) {
                    val = dp[i][k]+dp[k+1][j]+matrices[i].get_row()*matrices[k].get_col()*matrices[j].get_col();
                    if(min == -1) {
                        min = val;
                        dp[j][i]=k;
                    }
                    else if(min>val) {
                        min=val;
                        dp[j][i]=k;
                    }
                }
                dp[i][j]=min;
            }
        }
        
        //get the order of matrix
        String ans = findOrder(dp, 0, no_of_matrices-1);
        
        //Print the order by removing the first and last brackets
        System.out.println(ans.substring(2,ans.length()-2));
    }
    
    //Recursie function to generate the order of matrix multiplication
    public static String findOrder(int [][] dp, int i, int j) {
        if(dp[i][j]==0) {
            return "M" + String.valueOf(i+1);
        }
        return "( " + findOrder(dp,i,dp[j][i]) + " * " + findOrder(dp,dp[j][i]+1,j) + " )";
    }
}