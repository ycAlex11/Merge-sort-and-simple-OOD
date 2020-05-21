

import java.util.*;

//Imports an Array of strings with multiple columns
//and uses merge sort to sort by first column.

public class MergeSort
{
	//Global array to be sorted
	public String[] a = new String[1000];

	public String[] sort(String[] inArray, int low,int high)
	{
		a = inArray; //Sets global variable to import Array
		mergeSort(low, high);
		return a; //Exports sorted array
	}

	private void mergeSort(int low,int high)
	{
		int mid;
		//If there is more than one element in the array
		if(low < high)
		{
			//Divide array into sub problems
			//Find where to split the data
			mid=(low + high) / 2;

			//Solve the sub problems
			//Combine all solutions
    	mergeSort(low, mid);
    	mergeSort(mid + 1, high);
    	merge(low, mid, high);
   	 }
	}
	private void merge(int low,int mid,int high)
	{
 		int h, i, j, k;	//Initializes bunch of temp counters

 		String b[] = new String[1000]; //Holds values to assist with sorting
		StringTokenizer token1 = null;
		StringTokenizer token2 = null;
		Double tempNum1, tempNum2;

 		h = low;
 		i = low;
 		j = mid + 1;

		//Sorts the array a[] into b[]
		while((h <= mid)&&(j <= high))
 		{
				//Tokenizes the string at low and mid+1
				token1 = new StringTokenizer(a[h], ", ");
				token2 = new StringTokenizer(a[j], ", ");

				//Converts first column of these token elements to doubles
				//So they can be compared in an if statement
				tempNum1 = Double.parseDouble(token1.nextToken());
				tempNum2 = Double.parseDouble(token2.nextToken());

			//assigns value to temporary array if no sorting needed
			//increments h to check through rest of LHS subset next loop
  		if(tempNum1 <= tempNum2)
  		{
  			 b[i] = a[h];
  			 h++;
 			}
			//Swaps value if sorting is needed and swaps into helper array
			//increments j to check through rest of RHS subset next loop
  		else
  		{
   			b[i] = a[j];
   			j++;
  		}

			//increments the check on LHS as current a[i] should
			//always be sorted after IF/ELSE.
  		i++;
 		}
		
		//Combines array
		//If current LHS > RHS, assigns all following RHS to temp array
 		if(h > mid)
 		{
  		for(k = j; k <= high; k++)
  		{
   			b[i] = a[k];
   			i++;
  		}
 		}

		//Combines array
		//If current LHS <= RHS assigns LHS to temp array
 		else
 		{
  		for(k = h; k <= mid; k++)
  		{
   			b[i] = a[k];
   			i++;
  		}
 		}
		//Assigns the now sorted temporary array
		//to the global array a.
 		for(k = low; k <= high; k++)
		{
			 a[k] = b[k];
		}
	}
}

