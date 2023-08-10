import java.util.*;

class Solution{
    private boolean safe(int[][] arr,int r,int c,int x){
        //For row and column check
        for(int i=0;i<9;i++){
            if(i!=c && arr[r][i]==x)
                return false;
            if(i!=r && arr[i][c]==x)
                return false;
        }

        int n1=(r/3)*3;
        int n2=(c/3)*3;
        //For grid check
        for(int i=n1;i<n1+3;i++){
            for(int j=n2;j<n2+3;j++){
                if(i!=r && j!=c && arr[i][j]==x)
                    return false;
            }
        }
        return true;
    }
    private boolean helper(int[][] arr,int r,int c){
        //Final return statement
        if(r==9)
            return true;

        //new row and column for next call
        int nr,nc;
        if(c==8){
            nr=r+1;
            nc=0;
        }else{
            nr=r;
            nc=c+1;
        }

        //Checking for blank
        if(arr[r][c]!=0){
            return helper(arr,nr,nc);
        }else{
            for(int i=1;i<=9;i++){
                //Check wheater no is safe on that position
                if(safe(arr,r,c,i)){
                    arr[r][c]=i;
                    if(helper(arr,nr,nc))
                        return true;
                    else
                        arr[r][c]=0;
                    //if not valid than replace the no with again 0
                }
            }
        }
        return false;
    }
    public boolean solver(int[][] arr){
        //Check for valid Sudoku
        for(int i=0;i<9;i++){
            for(int j=0;j<9;j++){
                if(arr[i][j]!=0)
                    if(!safe(arr,i,j,arr[i][j]))
                        return false;
            }
        }
        return helper(arr,0,0);
    }
}

public class Main {
    public static void main(String args[]){
        Scanner sc=new Scanner(System.in);
        //create sudoku matrix
        int arr[][]=new int[9][9];

        System.out.println("Please enter a 9 * 9 sudoku matrix");
        System.out.println("(Note:Enter '0' in place of blank)");

        //taking sudoku matrix
        for(int i=0;i<9;i++){
            for(int j=0;j<9;j++){
                arr[i][j]=sc.nextInt();
            }
        }
        System.out.println();
        //Create Solution object
        Solution res=new Solution();
        if(res.solver(arr)){
            System.out.println("The solved sudoku is ");
            for(int i=0;i<9;i++){
                for(int j=0;j<9;j++){
                    System.out.print(arr[i][j]+" ");
                }
                System.out.println();
            }
        }else{
            System.out.println("You entered an invalid sudoku");
            System.out.println("This sudoku can't be solved");
        }
    }
}