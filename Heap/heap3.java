import java.util.*;
import java.lang.*;
import java.io.*;

class heap3
{
	public static void main (String[] args)
    {
        //String[] a = StdIn.readAllStrings();
        Scanner input = new Scanner(System.in);
        ArrayList<Integer> minHeap = new ArrayList<Integer>();
        String a;
        int val;
        while(input.hasNext()) {
            a = input.next();
            if (a.equals("add")) {
                val = input.nextInt();
                minHeap.add(val);
                insert(minHeap);
            }
            else if (a.equals("remove")) {
                System.out.println(removeMin(minHeap));
            }
            /*for(int k=0;k<minHeap.size();k++) {
                System.out.print(minHeap.get(k) + ", ");
            }
            System.out.println();*/
        }
    }
    
    public static void insert(ArrayList<Integer> minHeap) {
        int n = minHeap.size(),x;
        for(int i=n-1; i>0;) {
            if(minHeap.get(i) < minHeap.get((i-1)/3)) {
                x = minHeap.get(i);
                minHeap.set(i, minHeap.get((i-1)/3));
                minHeap.set((i-1)/3, x);
                i = (i-1)/3;
            }
            else
                break;
        }
    }
    
    public static int removeMin(ArrayList<Integer> minHeap) {
        int x = minHeap.get(minHeap.size()-1);
        minHeap.set(minHeap.size()-1, minHeap.get(0));
        minHeap.set(0, x);
        x = minHeap.get(minHeap.size()-1);
        minHeap.remove(minHeap.size()-1);
        heapify(minHeap,0);
        return x;
    }
    
    public static void heapify(ArrayList<Integer> minHeap, int i) {
        int n = minHeap.size(),minI;
        if(i>=n)
            return;
        minI = i;
        if(i*3 + 1 < n && minHeap.get(i*3 + 1) < minHeap.get(minI))
            minI = i*3 + 1;
        if(i*3 + 2 < n && minHeap.get(i*3 + 2) < minHeap.get(minI))
            minI = i*3 + 2;
        if(i*3 + 3 < n && minHeap.get(i*3 + 3) < minHeap.get(minI))
            minI = i*3 + 3;
        if(minI != i) {
            int x = minHeap.get(minI);
            minHeap.set(minI, minHeap.get(i));
            minHeap.set(i, x);
            heapify(minHeap,minI);
        }
        return;
    }
}