import java.util.*;
import java.lang.*;
import java.io.*;

class qsort
{
	public static void main (String[] args)
    {
        //String[] a = StdIn.readAllStrings();
        Scanner input = new Scanner(System.in);
        ArrayList<Integer> arr = new ArrayList<Integer>();
        int val,k = Integer.parseInt(args[0]);
        while(input.hasNext()) {
            arr.add(input.nextInt());
        }
        long t0 = System.currentTimeMillis();
        quickSort(arr, 0, arr.size()-1, k);
        long t1 = System.currentTimeMillis();
        System.err.println( t1 - t0 );
        for(int i=0;i<arr.size();i++) {
            System.out.println(arr.get(i));
        }
    }
    
    public static void quickSort(ArrayList<Integer> arr, int start, int end, int k) {
        if(end<=start)
            return;
        if(end-start+1<=k) {
            bubbleSort(arr, start, end);
            return;
        }
        int p = end, i=start-1, j;
        while(++i < p && arr.get(i) <= arr.get(p));
        i--;
        j = i;
        while(++j < p && arr.get(j) > arr.get(p));
        while(j < p) {
            if(arr.get(j) < arr.get(p)) {
                int x = arr.get(j);
                arr.set(j, arr.get(i+1));
                arr.set(i+1, x);
                i++;
            }
            j++;
        }
        int x = arr.get(p);
        arr.set(p, arr.get(i+1));
        arr.set(i+1, x);
        i++;
        quickSort(arr,start,i-1,k);
        quickSort(arr,i+1,end,k);
    }
    
    public static void bubbleSort(ArrayList<Integer> arr, int start, int end) {
        for(int i=start; i<=end-1;i++) {
            for(int j=i+1; j<=end; j++) {
                if(arr.get(i)>arr.get(j)) {
                    int x = arr.get(j);
                    arr.set(j, arr.get(i));
                    arr.set(i, x);
                }
            }
        }
    }
    
}