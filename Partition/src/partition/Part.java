package partition;


public class Part {

	public static int red = 1;
	public static int white = 2;
	public static int blue = 3;


	/**
	 * 
	 * Function: to partition an array of integers
	 * @param arr: array of elements 
	 * @param low: left-most element of all elements that
	 * are examined in arr
	 * @param high: right-most element of all elements that
	 * are examined in arr
	 * @return: i, index of the middle element. 
	 * elements with indices less than i are 
	 * smaller than arr[i]. 
	 * elements with indices greater than i are 
	 * greater than arr[i]. 
	 */
	public static int partition (int[] arr, int low,int high)
	{
		int pivot = arr[low];  

		int i = low;  // Index of smaller element

		for (int j = low+1; j <= high; j++)
		{
			// If current element is smaller than or
			// equal to pivot
			if (arr[j] <= pivot)
			{
				i++;    // increment index of smaller element
				//swap smaller element with current element
				int temp = arr[i];
				arr[i] = arr[j];
				arr[j] = temp;
			}
		}
		//place pivot at it's correct spot
		int temp = arr[i];
		arr[i] = arr[low];
		arr[low] = temp;

		return i;
	}

	/**
	 * Function: to determine the k'th smallest element 
	 * in array.
	 * @param arr: array of integers
	 * @param n: number of elements in array
	 * @param k: the k'th smallest element to be found
	 * @return: the k'th smallest element
	 */
	public static int select (int arr[], int k) {

		int left = 0;
		int right = arr.length-1;
		while (right-left > -1) {

			// check the middle of the array
			int middle = partition(arr, left, right);
			if (k == ((middle)-left)+1 ) {  
				System.out.println("k2: "+arr[middle]);
				return arr[middle];
			}
			// if k is in the left half
			if (k <= middle-left) {
				//search left half
				right = middle-1;

				// if k is in the right half
			} else if (k >= middle-left) {
				//search k in the right half
				k = k-(middle-left)-1;   
				left = middle+1;

			}
		}

		return arr[right];


	}

	/**
	 * Function: to find the median in
	 * an array.
	 * @param arr: array of integers
	 * @param n: length of the array
	 * @return: median of the array.
	 * @postCond: if the array.length is odd, median
	 * is the number in the middle of the sorted array.
	 * If the array.length is even, array is the
	 * average of two middle numbers in the middle
	 * of the sorted array.
	 */
	public static int median(int[] arr) {
		//calculate median for arrays with an odd length
		if (arr.length % 2 == 1) {
			int middle = (arr.length+1)/2;

			return select(arr,  middle);
		} else 
		{
			//calculate median for arrays with an even length
			int half_len = (arr.length)/2;
			int middle_one = select(arr,half_len);
			int middle_two = select(arr,half_len-1);
			return (middle_one + middle_two)/2;
		}
	}


	/**
	 * Our implementation of quicksort.
	 * Sorts the elements in the array using the
	 * partition function.
	 * @param arr: array of elements to be sorted
	 * @param high: right-most element of those 
	 * being examined in the array
	 * @param low: left-most element of those 
	 * being examined in the array
	 */
	public static void quicksort(int[] arr, int high,int low) {
		if (low < high) {
			int middle = partition(arr,low,high);

			quicksort(arr,high, middle+1);
			quicksort(arr,middle-1, 0);
		}
	}

	/**
	 * The solution for the Dutch National
	 * Flag problem.
	 * Invariant A.
	 * For each iteration, we have red_first,
	 * white_first,blue_first and unproc_first
	 * being the first elements of reds, whites,
	 * blues, and unprocessed values respectively.
	 * In the beginning, unprocessed occupies all
	 * of the array, so inproc_first = 0. 
	 * Other colors are absent, so other indices
	 * are set at -1.
	 * As we examine the elements in the array,
	 * we keep unexamined elements in the left-most
	 * part of the array. If we find a blue element, 
	 * we move on cause it's already in the right location.
	 * If we find a white element, we swap it with the
	 * first blue element. If we find a red element we 
	 * swap it with the first blue element and then with 
	 * the first white element.
	 * Thus, we maintain the following order of elements:
	 * red,white,blue,unexamined
	 * @param arr: array of colors
	 */
	public static void dutchFlagA(int arr[]) {

		int unproc_first = 0; 
		int red_first = -1;
		int white_first = -1;
		int blue_first = -1;

		while (unproc_first < arr.length) {
			//if we get a blue one, just increase
			// blue square
			if (arr[unproc_first] == blue) {
				//if we find blue: do nothing

			}
			//if we get a white one
			//the last blue element
			//becomes white and the 
			//last unprocessed 
			//element becomes blue
			if (arr[unproc_first] == white) {
				blue_first++;
				int temp = arr[unproc_first];
				arr[unproc_first] = arr[blue_first];
				arr[blue_first] = temp;
			}

			// if we get a red one, 
			// we move red back through 2 swaps
			if (arr[unproc_first] == red) {
				//switch red with blue_first
				blue_first++;
				int temp = arr[unproc_first];
				arr[unproc_first] = arr[blue_first];
				arr[blue_first] = temp;

				white_first++;
				//switch red with white_first
				temp = arr[blue_first];
				arr[blue_first] =arr[white_first];
				arr[white_first] = temp;

				if (red_first == -1) {
					red_first++;
				}

			}
			unproc_first++;
		}

	}

	/**
	 * The solution for the Dutch National
	 * Flag problem.
	 * Invariant C.
	 * For each iteration, we have red_first,
	 * white_first,blue_first and unproc_first
	 * being the first elements of reds, whites,
	 * blues, and unprocessed values respectively.
	 * In the beginning, unprocessed occupies all
	 * of the array, so inproc_first = 0. 
	 * Other colors are absent, so white_first 
	 * and blue_first are both arr.length since
	 * they're on the right of the unexamined elements.
	 * red_first is -1 because it's on the left of
	 * the unexamined elements.
	 * As we examine the elements in the array,
	 * we keep unexamined elements between the red and 
	 * white parts of the array. If we find a blue element, 
	 * we swap it with the last of the unexamined elements
	 * and then swap that element with the last white one.
	 * If we find a white element, we swap it with the
	 * last unexamined element. If we find a red element we 
	 * move on, since it's in the correct location.
	 * Thus, we maintain the following order of elements:
	 * red,white,unexamined,blue.
	 * @param arr: array of colors
	 */
	public static void dutchFlagC(int arr[]) {

		int unproc_first = 0; 
		int red_first = -1;
		int white_first = arr.length;
		int blue_first = arr.length;

		while (white_first > unproc_first) {

			//if we find a blue element, we
			// swap it with the last unexamined one
			// and then with the last white one
			if (arr[unproc_first] == blue) {
				int temp = arr[unproc_first];
				arr[unproc_first] = arr[white_first-1];
				arr[white_first-1] = temp;

				temp = arr[blue_first-1];
				arr[blue_first-1] = arr[white_first-1];
				arr[white_first-1] = temp;
				blue_first--;


			}
			//if we find a white one, we swap 
			//it with the last unexamined
			if (arr[unproc_first] == white) {
				int temp = arr[unproc_first];
				arr[unproc_first] = arr[white_first-1];
				arr[white_first-1] = temp;
				white_first--;
			}
			// if we find a red one, move on
			if (arr[unproc_first] == red) {
				unproc_first++;
				if (red_first == -1) {
					red_first++;
				}
			}
		}

	}



	public static void main (String args[]) {

		int[] array = {60,3,154,87,2,217,504,37,49,722,16,100,92};

		int[] array2 = {34,45,345,7,42,453,6,345,234,6,9};

		int resultPart = partition(array,0,array.length-1);
		System.out.println("\nresult partition: " + resultPart);

		int resultPart2 = partition(array,4,array.length-1);
		System.out.println("\nresult partition(2): " + resultPart2);

		int resultPart3 = partition(array,10,array.length-1);
		System.out.println("\nresult partition(3): " + resultPart3);

		int resultPart4 = partition(array2,0,array2.length-1);
		System.out.println("\nresult partition(4): " + resultPart4);

		int resultPart5 = partition(array2,2,array2.length-1);
		System.out.println("\nresult partition(5): " + resultPart5);

		int resultPart6 = partition(array2,6,array2.length-1);
		System.out.println("\nresult partition(6): " + resultPart6);

		int resultSelect = select(array, 7);
		System.out.println("\nresult select: " + resultSelect);

		int resultSelect2 = select(array, 1);
		System.out.println("\nresult select(2): " + resultSelect2);

		int resultSelect3 = select(array, 10);
		System.out.println("\nresult select(3): " + resultSelect3);

		int resultSelect4 = select(array2, 7);
		System.out.println("\nresult select: " + resultSelect4);

		int resultSelect5 = select(array2, 1);
		System.out.println("\nresult select(2): " + resultSelect5);

		int resultSelect6 = select(array2, 10);
		System.out.println("\nresult select(3): " + resultSelect6);

		int median = median(array);
		System.out.println("\n median!: " + median);

		int median2 = median(array2);
		System.out.println("\n median!: " + median2);

		quicksort(array,array.length-1,0);
		for(int i: array) {
			System.out.print(i + " ");
		}

		int arr[] = {white,blue,red,white,white,blue,white, white};
		dutchFlagC(arr);
		System.out.println("\ndutchFlag C:");
		for(int i: arr) {
			System.out.print(i + " ");
		}

		int arr2[] = {white,blue,red,white,white,blue,white, white};
		dutchFlagA(arr2);
		System.out.println("\ndutchFlag A:");
		for(int i: arr2) {
			System.out.print(i + " ");
		}

	}
}
/**
 * OUTPUT:
 * 
 *result partition: 5
 *
 *result partition(2): 4
 *
 *result partition(3): 12
 *
 *result partition(4): 4
 *
 *result partition(5): 3
 *
 *result partition(6): 10
 *k2: 87
 *
 *result select: 87
 *k2: 2
 *
 *result select(2): 2
 *k2: 154
 *
 *result select(3): 154
 *k2: 45
 *
 *result select: 45
 *k2: 6
 *
 *result select(2): 6
 *k2: 345
 *
 *result select(3): 345
 *k2: 87
 *
 * median!: 87
 *k2: 42
 *
 * median!: 42
 *2 3 16 37 49 60 87 92 100 154 217 504 722 
 *dutchFlag C:
 *1 2 2 2 2 2 3 3 
 *dutchFlag A:
 *1 2 2 2 2 2 3 3 
 */
