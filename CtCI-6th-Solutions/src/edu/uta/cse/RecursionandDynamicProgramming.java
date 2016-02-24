package edu.uta.cse;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.NoSuchElementException;
import java.util.Set;

/**
 * CtCI: Chapter 8 Recursion and Dynamic Programming
 * 
 * @author ruby_
 *
 */
public class RecursionandDynamicProgramming {

	// ----------------------------
	// Example: Fibonacci
	// ----------------------------
	/**
	 * fibonacci implementation without recursion
	 * 
	 * @param n
	 * @return
	 */
	public int fibonacci(int n) {
		/*
		 * a is used to save the lower value, starting from f(0) = 0 this value
		 * will be updated to b after each calculation
		 */
		int a = 0;
		/*
		 * b is usedd to save the higher value, starting from f(1) = 1 this
		 * value will be upddated to c after each calculation
		 */
		int b = 1;
		/* c is used to save partial result */
		int c = 0;

		if (n == 0)
			return 0;
		else if (n == 1)
			return 1;

		for (int i = 2; i < n + 1; i++) {
			c = a + b;
			a = b;
			b = c;
		}

		return c;
	}

	HashMap<Integer, Integer> fibCache = new HashMap<>();

	/**
	 * fibonacci implementation with recursion, use fibCache to save partial
	 * results
	 * 
	 * @param n
	 * @return
	 */
	public int recursiveFib(int n) {
		fibCache.put(0, 0);
		fibCache.put(1, 1);

		if (fibCache.containsKey(n))
			return fibCache.get(n);
		else
			fibCache.put(n, recursiveFib(n - 1) + recursiveFib(n - 2));

		return recursiveFib(n);
	}

	// ----------------------------
	// Q8.1 Triple Step
	// ----------------------------
	/**
	 * Count of ways of child running upstairs, he can hop either 1, 2 or 3
	 * steps. Assume as long as moving strategy is different the way is count,
	 * e.g. 3 = 1 + 1 + 1 = 1 + 2 = 2 + 1 = 3 (total 4 cnts) Think: Move
	 * strategies till step n can be categorized into 3 types based on the last
	 * move (1 or 2 or 3), then the count of ways is equal to the sum of these 3
	 * kinds
	 * 
	 * [NOTE]: Use BigInteger to get arround the possible overflow...
	 * 
	 * @param n
	 *            total of n steps of stair
	 * @return
	 */
	HashMap<Integer, BigInteger> waysUpStairsCache = new HashMap<>();

	public BigInteger cntWaysUpStairsInStepOf(int n) {
		waysUpStairsCache.put(0, BigInteger.valueOf(0));
		waysUpStairsCache.put(1, BigInteger.valueOf(1));
		waysUpStairsCache.put(2, BigInteger.valueOf(2));
		waysUpStairsCache.put(3, BigInteger.valueOf(4));

		if (waysUpStairsCache.containsKey(n))
			return waysUpStairsCache.get(n);
		else
			waysUpStairsCache.put(
					n,
					cntWaysUpStairsInStepOf(n - 1).add(
							cntWaysUpStairsInStepOf(n - 2).add(
									cntWaysUpStairsInStepOf(n - 3))));

		return cntWaysUpStairsInStepOf(n);
	}

	// ----------------------------
	// Q8.2 Robot in a Grid
	// ----------------------------
	/* Q&D implementation of point and grids used for testing purpose */
	public class GridPoint {
		int x;
		int y;

		GridPoint(int pX, int pY) {
			x = pX;
			y = pY;
		}

		@Override
		public boolean equals(Object obj) {
			if (!(obj instanceof GridPoint))
				return false;

			if (obj == this)
				return true;

			GridPoint p = (GridPoint) obj;
			return (x == p.x && y == p.y);
		}

		@Override
		public String toString() {
			return "(" + x + "," + y + ")";
		}
	}

	public ArrayList<GridPoint> getPath(boolean[][] maze) {
		ArrayList<GridPoint> path = new ArrayList<>();
		getPath(maze, 0, 0, path);
		return path;
	}

	HashMap<GridPoint, Boolean> pathCache = new HashMap<>();

	private boolean getPath(boolean[][] maze, int row, int column,
			ArrayList<GridPoint> path) {
		// If the point is out of grid boarder, or if it's not reachable
		if (row > maze.length - 1 || column > maze[0].length - 1
				|| !(maze[row][column]))
			return false;

		GridPoint current = new GridPoint(row, column);
		GridPoint oneStepRight = new GridPoint(row, column + 1);
		GridPoint oneStepDown = new GridPoint(row + 1, column);

		// If reached the last point, or the examined next-hop point has been
		// traveled, put current point into cache and add it to path
		if (isEndPoint(row, column, maze)
				|| (pathCache.containsKey(oneStepRight) && pathCache
						.get(oneStepRight))
				|| (pathCache.containsKey(oneStepDown) && pathCache
						.get(oneStepDown))) {
			pathCache.put(current, true);
			path.add(current);
			return true;
		} else {
			// Else let's go check the next-hops, with recursive calls, be
			// careful at these logical short-circuit evaluation, it has to in
			// this way to avoid possible overlays
			boolean canReachEnd = getPath(maze, row + 1, column, path)
					|| getPath(maze, row, column + 1, path);

			if (canReachEnd) {
				pathCache.put(current, true);
				path.add(current);
			} else
				pathCache.put(current, false);

			return canReachEnd;
		}

	}

	private boolean isEndPoint(int row, int column, boolean[][] maze) {
		return row == maze.length - 1 && column == maze[0].length - 1;
	}

	// ----------------------------
	// Q8.3 Magic Index
	// ----------------------------
	/*
	 * array[i] == i then i is the magic index, following implementation is for
	 * arrays with distinct numbers
	 */
	public int magicIndex(int[] array) {
		return magicIndex(array, 0, array.length - 1);
	}

	private int magicIndex(int[] array, int startIndex, int endIndex) {
		if (array.length == 0)
			throw new NoSuchElementException();

		// Compare the element value at mid-index with mid-index to determine
		// which side the recursive call to magicindex will be called
		// If midIndex < array[midIndex], search should be on its left (smaller
		// value) side
		// or if midIndex > array[midIndex], search should be on its right
		// (larger value) side
		int midIndex = (startIndex + endIndex) / 2;

		if (midIndex == array[midIndex])
			return midIndex;
		else if (midIndex < array[midIndex])
			return magicIndex(array, startIndex, midIndex);
		else
			return magicIndex(array, midIndex, endIndex);
	}

	/* Extension: elements are not distinct */
	public int magicIndexNoDistinct(int[] array) {
		return magicIndexNoDistinct(array, 0, array.length - 1);
	}

	private int magicIndexNoDistinct(int[] array, int startIndex, int endIndex) {
		if (array.length == 0)
			throw new NoSuchElementException();

		if (startIndex < 0 || endIndex < 0 || startIndex > array.length - 1
				|| endIndex > array.length - 1 || startIndex > endIndex)
			return -1;

		// Compare the element value at mid-index with mid-index to determine
		// which side the recursive call
		// If midIndex < array[midIndex], search can be on two side:
		// on the left side, search [startIndex, midIndex - 1]
		// and also on its right side, search [array[midIndex], endIndex]
		// or midIndex > array[midIndex], search on:
		// left-side: [startIndex, array[midIndex]]
		// right-side: search [midIndex + 1, endIndex]
		// Thus in both situation, search [startIndex, min] on left and [max,
		// endIndex] on right
		int midIndex = (startIndex + endIndex) / 2;

		if (midIndex == array[midIndex])
			return midIndex;

		int p1 = magicIndexNoDistinct(array, startIndex,
				Math.min(midIndex - 1, array[midIndex]));
		if (p1 != -1)
			return p1;

		int p2 = magicIndexNoDistinct(array,
				Math.max(midIndex + 1, array[midIndex]), endIndex);
		if (p2 != -1)
			return p2;

		return -1;
	}

	// ----------------------------
	// Q8.4 Power Set
	// ----------------------------
	/**
	 * Simple ideas of constructing subset s[n-1] from s[n] by removing 0...n-1
	 * index of s[n]
	 * 
	 * @param array
	 * @return
	 */
	public Set<Set<Integer>> allSubset(int[] array) {
		Set<Set<Integer>> subset = new HashSet();
		Set<Integer> arraySet = new HashSet<Integer>();
		// No elegant way found to convert int[] to ArrayList<Integer> yet
		for (int i = 0; i < array.length; i++)
			arraySet.add(array[i]);

		trueSubset(subset, arraySet);

		return subset;
	}

	private void trueSubset(Set<Set<Integer>> totalSubset, Set<Integer> pSet) {
		for (int i = 0; i < pSet.size(); i++) {
			Set<Integer> trueset = new HashSet<>(pSet);
			trueset.remove(i);
			if (!trueset.isEmpty() && !totalSubset.contains(trueset)) {
				totalSubset.add(trueset);
				trueSubset(totalSubset, trueset);
			}
		}
	}

	/**
	 * Return the all (except for the empty set) subsets of a set by
	 * constructing subset use number 0-2^n, where n is the total number of
	 * elements of the set
	 * 
	 * @param array
	 * @return
	 */
	public Set<Set<Integer>> allSubsetFromBinary(int[] array) {
		Set<Set<Integer>> subset = new HashSet<>();
		Set<Integer> arraySet = new HashSet<Integer>();
		for (int i = 0; i < array.length; i++)
			arraySet.add(array[i]);

		// Calculate power of 2 using bit-manipulation other than Math.pow()!!
		int totalPossibles = 1 << array.length;

		// Translate the bit-representation of possible subset into real one
		for (int i = 0; i < totalPossibles; i++) {
			Set<Integer> trueset = new HashSet<>();
			for (int k = 0; k < totalPossibles; k++) {
				if (getBit(i, k))
					trueset.add(array[k]);
			}

			if (!trueset.isEmpty())
				subset.add(trueset);
		}

		return subset;
	}

	private boolean getBit(int num, int i) {
		return ((num & (1 << i)) != 0);
	}

	// ----------------------------
	// Q8.5 Recursive Multiply
	// ----------------------------
	/* Bit-manipulate impl: didn't consider optimation */
	public int recursiveMultiply(int a, int b) {
		int c = 0;
		for (int i = 0; (1 << i) < b; i++) {
			if (getBit(a, i))
				c += (b << i);
		}
		return c;
	}

	/**
	 * a*b == a*(b/2) * 2, when b is even; or a*b = a*(b-1)/2 * 2 + a when b is
	 * odd
	 * 
	 * @param a
	 * @param b
	 * @return
	 */
	public int recursiveMultiplyByHalf(int a, int b) {
		if (b == 0)
			return 0;
		else if (b == 1)
			return a;

		if (b % 2 == 0)
			return (recursiveMultiplyByHalf(a, b >> 1) << 1);
		else
			return ((recursiveMultiplyByHalf(a, (b - 1) >> 1) << 1) + a);
	}

	// ----------------------------
	// Q8.6 Towers of Hanoi
	// ----------------------------
	public void moveHanoi(Tower distTower, Tower srcTower, Tower bufferTower) {
		int n = srcTower.size();
		srcTower.moveTopNDisksToTower(n, distTower, bufferTower);
	}

	// ----------------------------
	// Q8.7 Permutations without Dups
	// ----------------------------
	/**
	 * Return permutatoins of string s
	 * @param s
	 * @return
	 */
	public ArrayList<String> permutationsWithoutDups(String s) {
		ArrayList<String> permus = new ArrayList<String>();
		char[] sArray = s.toCharArray();
		
		if(sArray.length == 1)
			permus.add(s); 
		else {
			ArrayList<String> subs = subString(s);
			for(int i = 0; i < subs.size(); i++) {
				for(String ss : permutationsPlus(subs.get(i), s.charAt(i)))
					permus.add(ss);
			}
		}

		return permus;
	}

	/**
	 * Returns a list of permutation of s that plus c at last
	 * @param s
	 * @param c
	 * @return
	 */
	private ArrayList<String> permutationsPlus(String s, char c) {
		ArrayList<String> hold = new ArrayList<>();

		hold = permutationsWithoutDups(s);
		for (int i = 0; i < hold.size(); i++)
			hold.set(i, hold.get(i) + String.valueOf(c));

		return hold;
	}

	/**
	 * Returns substring of String s by removing 1st, 2nd.. last String. Example: 
	 * Input: "test"
	 * Output: "est" "tst" "tet" "tes" (in order)
	 * @param s
	 * @return
	 */
	private ArrayList<String> subString(String s) {
		ArrayList<String> result = new ArrayList<>();
		char[] sArray = s.toCharArray();

		for (int i = 0; i < sArray.length; i++) {
			String sR = "";
			for (int j = 0; j < sArray.length; j++) {
				if (j != i)
					sR += sArray[j];
			}

			result.add(sR);
		}

		return result;
	}

}
