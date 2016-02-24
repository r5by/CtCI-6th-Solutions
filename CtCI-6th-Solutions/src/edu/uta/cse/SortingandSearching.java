package edu.uta.cse;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * CtCI Chapter 10: Sorting and Searching
 * 
 * @author ruby_
 *
 */
public class SortingandSearching {
	// ----------------------------
	// Warm-ups: Merge Sort
	// ----------------------------
	/**
	 * Sort array by merge sort
	 * 
	 * @param array
	 * @param startIndex
	 * @param endIndex
	 */
	public void mergeSort(int[] array, int startIndex, int endIndex) {
		if (startIndex < endIndex) {
			/* Recursively calls mergeSort to partition the array */
			int midIndex = (endIndex + startIndex) >> 1;
			mergeSort(array, startIndex, midIndex);
			mergeSort(array, midIndex + 1, endIndex);

			/* Merge Result */
			ArrayList<Integer> buffer = new ArrayList<>();
			int i = startIndex, j = midIndex + 1;
			while (i <= midIndex && j <= endIndex) {
				if (array[i] < array[j]) {
					buffer.add(array[i]);
					i++;
				} else {
					buffer.add(array[j]);
					j++;
				}
			}

			// NOTE: If the while-loop stop at j==endIndex+1, that means all
			// elements that are left with left array are greater than the right
			// in which case, these elements should be passed over to buffer as
			// well
			// otherwise if while-loop is stopped by i, there is no need to deal
			// with the rest elements in right array (save copy-over time)
			if (j == endIndex + 1)
				while (i <= midIndex)
					buffer.add(array[i++]);

			// Copy back from buffer
			for (int k = 0; k < buffer.size(); k++) {
				array[startIndex + k] = buffer.get(k);
			}
		}
	}

	// ----------------------------
	// Quick Sort
	// ----------------------------
	/**
	 * Quick sort implementation
	 * 
	 * @param array
	 */
	public void quickSort(int[] array) {
		if (array.length == 0)
			throw new Error("Empty array!");
		quickSort(array, 0, array.length - 1);
	}

	private void quickSort(int[] array, int startIndex, int endIndex) {
		if (startIndex < endIndex) {
			int piv = array[startIndex];

			int currentPointer = startIndex;
			for (int i = startIndex + 1; i <= endIndex; i++) {
				if (array[i] < piv) {
					array[currentPointer++] = array[i];
				}
			}

			if (currentPointer != startIndex)
				array[currentPointer] = piv;

			/* Recursively sort the left/right half array to the selected pivot */
			quickSort(array, startIndex, currentPointer - 1);
			quickSort(array, currentPointer + 1, endIndex);
		}
	}

	// ----------------------------
	// Bucket Sort
	// ----------------------------
	public void bucketSort(int[] array, int maxElement) {
		ArrayList<LinkedList<Integer>> buckets = new ArrayList<>();

		/* Initiate buckets */
		int bucketSize = mostSignificantBit(maxElement) + 1;
		for (int i = 0; i < bucketSize; i++) {
			buckets.add(new LinkedList<>());
		}

		/* Put elements in the array into bucket in sorted order of each bucket */
		for (int i = 0; i < array.length; i++) {
			int item = array[i];
			LinkedList<Integer> bucket = buckets.get(mostSignificantBit(item));

			if (bucket.isEmpty())
				bucket.addFirst(item);
			else {
				int j = 0;
				while (j < bucket.size()) {
					if (bucket.get(j) > item) {
						bucket.add(j, item);
						break;
					}
					j++;
				}

				if (j == bucket.size())
					bucket.addLast(item);

			}
		}

		/* Retrieve elements in all buckets and form the sorted array */
		int k = 0;
		for (LinkedList<Integer> bucket : buckets) {
			if (!bucket.isEmpty()) {
				for (Integer v : bucket)
					array[k++] = v;
			}
		}
	}

	/**
	 * Returns the most significant big position for integer x, the most
	 * significant i means 2^i - 1 < x < 2^(i+1)
	 * 
	 * @param x
	 * @return
	 */
	private int mostSignificantBit(int x) {
		int i = 0;

		if (x >= 0)
			while ((x >>= 1) != 0)
				i++;
		else
			while ((x >>= 1) != -1)
				i++;

		return i;
	}

	// ----------------------------
	// Q10.1 Sorted Merge
	// ----------------------------
	/**
	 * Solution-1: Swap a[i], b[j] whenever a[i] > b[j] then maintain the order
	 * in array b, copy over b after all elements of a are compared.
	 * 
	 * @param mergedToSortedArrayA
	 * @param sortedArrayB
	 */
	public void sortMerge(int[] mergedToSortedArrayA, int[] sortedArrayB) {
		int indexA = 0;
		while (indexA <= getLastElemIndex(mergedToSortedArrayA)) {
			if (mergedToSortedArrayA[indexA] > sortedArrayB[0]) {
				swap(mergedToSortedArrayA, sortedArrayB, indexA, 0);
				maintainArrayOrder(sortedArrayB);
			}
			indexA++;
		}

		for (int i = 0; i < sortedArrayB.length; i++) {
			mergedToSortedArrayA[indexA++] = sortedArrayB[i];
		}
	}

	private void maintainArrayOrder(int[] array) {
		for (int i = 0; i < array.length - 1; i++) {
			if (array[i] > array[i + 1])
				swap(array, array, i, i + 1);
		}
	}

	private void swap(int[] arrayA, int[] arrayB, int indexA, int indexB) {
		int temp = arrayA[indexA];
		arrayA[indexA] = arrayB[indexB];
		arrayB[indexB] = temp;
	}

	/**
	 * Get the last non-0 elements of sorted array, e.g. array = {1, 2, 0, 0},
	 * return 1. NOTE: Multiple 0's in array are not considered in this
	 * solution!!
	 * 
	 * @param sortedArray
	 * @return
	 */
	public int getLastElemIndex(int[] sortedArray) {
		if (sortedArray.length == 0)
			throw new Error("Empty array!");

		int i = 0;
		if (sortedArray[0] == 0)
			return i;
		else {
			if (sortedArray.length > 1)
				while (sortedArray[i] != 0)
					i++;

			return i - 1;
		}
	}

	// Inspired by the book, copy from the end (bigger-side)
	public void sortMergeFromEnd(int[] mergedToArray, int[] array,
			int endIndexA, int endIndexB) {
		int lastIndexA = endIndexA - 1;
		int lastIndexB = endIndexB - 1;
		int lastIndexMerged = endIndexA + endIndexB - 1;

		while (lastIndexB >= 0 && lastIndexA >= 0) {
			if (array[lastIndexB] > mergedToArray[lastIndexA])
				mergedToArray[lastIndexMerged] = array[lastIndexB--];
			else
				mergedToArray[lastIndexMerged] = mergedToArray[lastIndexA--];

			lastIndexMerged--;
		}

		/* Copy over from B when all A's elements are compared */
		if (lastIndexA == -1)
			for (int i = 0; i < array.length; i++) {
				mergedToArray[i] = array[i];
			}
	}

	// ----------------------------
	// Q10.2 Grouping Anagrams
	// ----------------------------
	public void groupAnagrams(String[] array) {
		Arrays.sort(array, new AnagramsComparator());
	}

	class AnagramsComparator implements Comparator<String> {

		private String sortString(String s) {
			char[] charArray = s.toCharArray();
			Arrays.sort(charArray);
			return new String(charArray);
		}

		@Override
		public int compare(String o1, String o2) {

			/* NOTE: Full sort of string array, not required by the question */
			if (o1.length() < o2.length())
				return -1;
			else if (o1.length() > o2.length())
				return 1;
			else
				return sortString(o1).compareTo(o2);
		}
	}

	// –---------------------------
	// Q10.3 Search in Rotated Array
	// ----------------------------
	/**
	 * Find the target in rotated sorted array (non-duplication)
	 * 
	 * @param array
	 * @param target
	 * @return
	 */
	public int findIndexInRotatedArray(int[] array, int target) {
		int rotatedHeaderIndex = findRotationHeader(array);

		if (array.length == 1) {
			if (array[0] == target)
				return 0;
			else
				return -1;
		} else {
			if (target >= array[0] && target <= array[rotatedHeaderIndex - 1])
				return binSearch(target, array, 0, rotatedHeaderIndex - 1);

			if (target >= array[rotatedHeaderIndex]
					&& target <= array[array.length - 1])
				return binSearch(target, array, rotatedHeaderIndex,
						array.length - 1);
		}

		return -1;
	}

	/* Binary search */
	private int binSearch(int target, int[] array, int startIndex, int endIndex) {
		if (startIndex <= endIndex) {
			int midIndex = (startIndex + endIndex) >> 1;
			if (array[midIndex] == target)
				return midIndex;
			else if (array[midIndex] > target)
				return binSearch(target, array, startIndex, midIndex - 1);
			else
				return binSearch(target, array, midIndex + 1, endIndex);
		}

		return -1;
	}

	/**
	 * Find the true position of first element in originally sorted array in
	 * O(n)
	 * 
	 * @param array
	 * @return
	 */
	private int findRotationHeader(int[] array) {
		if (array.length == 0)
			throw new Error("Empty Array");

		int header = array[0];
		if (array.length == 1)
			return 0;
		else {
			int i = 1;
			while (i < array.length && array[i] > header) {
				i++;
			}

			return i;
		}

	}

	// ----------------------------
	// Q10.4 Sorted Search, No size
	// ----------------------------

	public int sortedSearchNoSize(int target, Listy list) {

		int i = 0;

		int regionEnd = 0, regionStart = 0;
		do {
			regionStart = list.elementAt(1 << i);
			regionEnd = list.elementAt(1 << (i + 1));

			if (target >= regionStart && target <= regionEnd)
				return binSearch(target, list, 1 << i, 1 << (i + 1));

			i++;
		} while (regionEnd != -1);

		/*
		 * Otherwise, do binary search in the last region (cut the tails of -1's
		 * using findLast() method)
		 */
		int lastPos = findLast(list, 1 << (i - 1), 1 << i);
		return binSearch(target, list, 1 << (i - 1), lastPos);

	}

	public int findLast(Listy list, int startIndex, int endIndex) {
		if (startIndex <= endIndex) {
			int midIndex = (startIndex + endIndex) >> 1;

			if (list.elementAt(midIndex) != -1
					&& list.elementAt(midIndex + 1) == -1)
				return midIndex;
			else if (list.elementAt(midIndex) == -1)
				return findLast(list, startIndex, midIndex - 1);
			else
				return findLast(list, midIndex + 1, endIndex);

		}

		return -1;
	}

	private int binSearch(int target, Listy list, int startIndex, int endIndex) {
		if (startIndex <= endIndex) {
			int midIndex = (startIndex + endIndex) >> 1;
			if (list.elementAt(midIndex) == target)
				return midIndex;
			else if (list.elementAt(midIndex) > target)
				return binSearch(target, list, startIndex, midIndex - 1);
			else
				return binSearch(target, list, midIndex + 1, endIndex);
		}

		return -1;
	}
	
	//----------------------------
	//       Q10.5 Sparse Search
	//----------------------------
	/**
	 * Find string target within the sparseArray, return the first found with its location. If not found, return -1.
	 * @param target
	 * @param sparseArray
	 * @return
	 */
	public int sparseSearch(String target, String[] sparseArray) {
		if(target.isEmpty())
			throw new Error("Empty string value passed error");
		
		return binarySearchSparseArray(target, sparseArray, 0, sparseArray.length - 1);
	}
	
	/** Binary search the sparse array with adjusted mid positions */
	private int binarySearchSparseArray(String target, String[] sparseArray, int startIndex, int endIndex) {
		int mid = (startIndex + endIndex) >> 1;
		int adjustedMid = findFirstNonEmptyStringToLeft(mid, sparseArray);
		
		/* Compare the target String value with the String element at adjusted mid position of sparse array */
		int comparison = target.compareTo(sparseArray[adjustedMid]);
		
		if(comparison == 0)
			return adjustedMid;
		else if(comparison < 0) {
			//If less, search the left part
			return binarySearchSparseArray(target, sparseArray, startIndex, adjustedMid - 1);
		} else
			//If greater, search the right part
			return binarySearchSparseArray(target, sparseArray, mid + 1, endIndex);
	}
	
	/** Find the first location to the startIndex in sparseArray where the element is not an empty String */
	private int findFirstNonEmptyStringToLeft(int startIndex, String[] sparseArray) {
		int i = startIndex;
		
		while(i>=0 && sparseArray[i].isEmpty())
			i--;
		
		return i;
	}
	
	//----------------------------
	//       Q10.6 Sort Big File
	//----------------------------
	/**
	 * Explain the idea (without code or test cases)
	 * 
	 * 1) 20GB exceeds normal memory capacity, so we'll chunk the big file first into x megabits files
	 * 2) Sort each file separately and save back to file system
	 * 3) Merge the files 
	 */
	
	//----------------------------
	//       Q10.7 Missing Int
	//----------------------------
	
	/* There are 2^32 distinct integers possible and among them there are 2^31 possible non-negetive integers ( > 2 billion)
	 * NOTE: DON'T use (1<<31) here since the first bit will be marked as a negative sign */
	public static final long TOTAL_NUM_NON_NEGATIVE_INTEGER = (long)Integer.MAX_VALUE + 1;
	
	/**
	 * Given a file with (non-negative) integer in each line, return the missing integers; if no missing integers were found, return -1
	 * @param fileName
	 * @return
	 * @throws FileNotFoundException
	 */
	public int findMissingInt(String fileName) throws FileNotFoundException {
		/* 1GB of memory available, that is 8 billion bits (1 billion bytes), we'll allocate the byte vector (BV) into memory to mark all distinct integers */
		byte[] byteVector = new byte[(int)(TOTAL_NUM_NON_NEGATIVE_INTEGER / 8)];
		
		/* Scan through the file and mark the BV */
		Scanner scanner = new Scanner(new File(fileName));
		while(scanner.hasNextInt()) {
			int current = scanner.nextInt();
			
			//mark BV
			byteVector[current / 8] |= (1 << (current % 8) );
		}
		
		/* Loop through the marked BV and return the first integer that is missing (not marked) */
		for(int i = 0; i < byteVector.length; i++)
			for(int j = 0; j < 8; j++) {
				byte b = byteVector[i];
				
				if((b & (1 << j)) == 0)
					return backToInteger(i, j);
			}
		
		return -1;
	}
	
	private int backToInteger(int position, int shift) {
		return 8 * position + shift;
	}
	
	/**
	 * Follow Up: given 10MB limit of memory and distinct lest than one billion non-negative integers, find a missing integer
	 * @param fileName
	 * @return
	 */
	public int findMissingIntLimitedMemory(String fileName) {
		/* 10MB of available memory, we'll have to split the dataset into small chunks so we can use BV to represent each of the section 
		 * We'll choose 2^20 as the chunk size for BV; the size if flaxible, bigger the size, larger memory will be taken, but speed will be faster */
		final int rangeInitBitPower = 20;
		final int rangeFactor = 1 << rangeInitBitPower;
		final int maxRangeID = Integer.MAX_VALUE / rangeFactor;
		
		for(int i = 0; i <= maxRangeID; i++) {
//			System.out.println(i);
			int result;
			try {
				result = findMissingIntLimitedMemory(fileName, i, rangeFactor);
				
				if(result != -1)
					return result;
				
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
			
		return -1;
	}
	
	public int findMissingIntLimitedMemory(String fileName, final int rangeID, final int rangeFactor) throws FileNotFoundException {
		byte[] byteVector = new byte[rangeFactor / 8];
		
		Scanner scanner = new Scanner(new File(fileName));
		while(scanner.hasNextInt()) {
			int current = scanner.nextInt();
			
			if((current / rangeFactor) == rangeID) {
				//Apply BV-marking if it's within the current range
				int currentResidue = current % rangeFactor;
				
				//Mark BV
				byteVector[currentResidue / 8] |= (1 << (currentResidue % 8));
			}
		}
		
		//Check marked BV to return the missing integer or -1 if no missing integers found in this range
		for(int i = 0; i < byteVector.length; i++)
			for(int j = 0; j < 8; j++) {
				byte b = byteVector[i];
				
				if((b & (1 << j)) == 0)
					return backToInteger(i, j) + rangeFactor * rangeID;
			}
		
		//help GC
		byteVector = null;
		
		return -1;
	}

	// ----------------------------
	// Extensions
	// ----------------------------
	
	/* (Dynamic programming) Question: Given n bits (0's and 1's), write a method to return the total number of possible combinations that doesn't have or have only 1 "00" */
	
	/**
	 * map is used to save each n's information: n is the key, the arrangement of associated arraylist is 
	 * 
	 * 		0 | 1
	 * 		- - -
	 * 		2 | 3
	 * 
	 * where any possible combination will fall into the four types:
	 * 
	 * Type 0: Tail with 0 and has 00
	 * Type 1: Tail with 0 without 00
	 * Type 2: Tail with 1 and has 00
	 * Type 3: Tail with 1 without 00
	 * 
	 * The update of the arraylist of n is totally depending (n-1) where:
	 * 
	 * An(0) = An-1(1) + An-1(2)
	 * An(1) = An-1(3)
	 * An(2) = An-1(0) + An-1(2)
	 * An(3) = An-1(3) + An-1(1)
	 */
	private HashMap<Integer, ArrayList<Integer>> map = new HashMap<>();
	public int numOfTotalPatterns(int n) {
		if(n==1) {
			//Initialize the map
			ArrayList<Integer> array1 = new ArrayList<>();
			array1.add(0);
			array1.add(1);
			array1.add(0);
			array1.add(1);
			
			map.put(0, array1);
		} else {
			numOfTotalPatterns(n - 1);
			
			ArrayList<Integer> arrayn = new ArrayList<>();
			ArrayList<Integer> arraynprevious = map.get(n-2);
			arrayn.add(arraynprevious.get(1) + arraynprevious.get(2));
			arrayn.add(arraynprevious.get(3));
			arrayn.add(arraynprevious.get(0) + arraynprevious.get(2));
			arrayn.add(arraynprevious.get(3) + arraynprevious.get(1));
			
			map.put(n-1, arrayn);
		}
		
		return sumArrayList(map.get(n - 1));
		
	}
	
	private int sumArrayList(ArrayList<Integer> array) {
		int sum = 0;
		for(Integer i : array)
			sum += i;
		
		return sum;
	}

	// END
}
