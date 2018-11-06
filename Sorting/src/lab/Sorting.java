package lab;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class Sorting {

	public static void quicksort (int [] a) {
		// method to sort using the quicksort
		    quicksortKernel (a, 0, a.length-1);
		}

	private static void quicksortKernel (int [] a, int first, int last) {
		   int left = last;
		   int right = last;
		   int temp;
		   System.out.println("Call with: first " + first +" last: " + last );
		    // begin by selecting a random integer, midIndex, between first and last,
		    // and then swap a[first] with a[midIndex].
		    int midIndex = first + (int)Math.floor((last-first)*Math.random());
		    temp = a[first];
		    a[first] = a[midIndex];
		    a[midIndex] = temp;

		   // progress through array, 
		   //     moving small elements to a[first+1] .. a[left-1]
		   //     and moving large lements to a[left] .. a[right-1]
		   while (left > first)
		     {
		       if (a[first] >= a[left])
		          left--;
		       else {
		          // swap a[left] and a[right]
		          temp = a[right];
		          a[right] = a[left];
		          a[left] = temp;
		          left--;
		          right--;
		       }
		     }
		    // put a[first] in its place
		    temp = a[first];
		    a[first] = a[right];
		    a[right] = temp;

		    // recursively apply algorithm to a[first]..a[left-2] 
		    // and a[left]..a[last], provided these segments contain >= 2 items
		    if (first < right) {
		    	System.out.println("calling on first < right");
		        quicksortKernel (a, first, right);
		    }
		    if (right < last - 2 ) {
		    	System.out.println("calling on  right < last ");
		        quicksortKernel (a, right, last - 2);   
		    }
		}
		
	public static void merge (int arr[], int arrDest[], int start1, int start2, int end2 ) {
		
		int index = start1;
		int initStart2 = start2;
		
		if (end2 >= arrDest.length) {
			end2 = arrDest.length-1;
		}
		while (start1 < initStart2 && start2 < end2 && index < arrDest.length && end2 <= arrDest.length) {
			if (arr[start1] < arr[start2]) {
				arrDest[index] = arr[start1];
				start1++;
				index++;
			} else {
				arrDest[index] = arr[start2];
				start2++;
				index++;
			}
		}
		
		if (start1 == initStart2 && start2 != end2) {
			for (int i = start2; i < end2 && index < arrDest.length; i++) {
				arrDest[index]= arr[i];
				index++;
			}
		}
		
		if (start1 != initStart2 && start2 >= end2) {
			for (int i = start1; i < initStart2 && index < arrDest.length; i++) {
				arrDest[index]= arr[i];
				index++;
			}
		}
		
	}
	
	public static  void  mergeSort (int initArr [ ]) {
		int resArr [ ] = new int [initArr.length]; //create array of the same size as the array to be sorted
		int a0[ ] = initArr;   // initial array
		int a1[ ] = resArr;		// array with merged sorted pieces of initial array
		boolean needCopyBack = false;   // bool showing if copying from a1 to a0 is needed
		
		int mergeSize = 1;     // start by merging arrays of size 1 within intial array
		int start1;			// beginning of the 1st part of the array that'll be merged with another part
		while (mergeSize < initArr.length)  // while size of merging is less than length of the array
		    {
			int end2;      // index where the second piece of the array that is being merged ends
			for (start1 = 0; start1 < initArr.length; start1 = end2) {    // let start1 be 0, and while its less then length of the array
			    int start2 = start1 + mergeSize;         // the second merging piece of array begins at start1 + merge size
			    end2 = start2 + mergeSize;				// the second merging piece of array ends at it's beginning + size of merge
			    merge (a0, a1, start1, start2, end2);	// call to merge two array pieces with starts/end defined above
			}				// now, start1 = start2, so effectively after 2 pieces are merged, move to merge next two pieces 

			// swap a0, a1 pointers, so a0 is starting array for next merge
			int temp [ ] = a0;
			a0 = a1;
			a1 = temp;
			mergeSize *= 2;

			// keep track of which array holds initArr object
			needCopyBack = !needCopyBack;
			
		    }

		//copy result into initArr, as needed
		if (needCopyBack)     // each time except the last one, we copy elements into initArray for call to merge
		    {
			for (int i = 0; i < initArr.length; i++)
			    initArr [i] = a0 [i];
		    }
	    }
	
		public static void main(String args[]) throws Exception {
		    // declarations: two arrays and I/O;
		    int size = 12;
		    int [] c = new int [size];
		    int [] d = new int [size];
		    InputStreamReader istream  = new InputStreamReader(System.in);
		    BufferedReader in = new BufferedReader(istream);
		    PrintWriter out = new PrintWriter(System.out, true);
		    String line;

		    // initial arrays to same values by reading and copying
		    out.println ("Please enter " + size + " integer values to sort,"
		                 + " with each number on a separate line");
		    for (int i = 0; i < size; i++) {
		        line = in.readLine();
		        c[i] = new Integer(line).intValue();
		        d[i] = c[i];
		    }

		    // sort and print first array
		    quicksort (c);
		    out.println ("Result of Quicksort:");
		    for (int i = 0; i < size; i++) 
		        out.print(c[i] + "\t");
		    out.println();

			}
			
			
		
}
