package edu.uta.cse;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import edu.uta.cse.RecursionandDynamicProgramming.GridPoint;

public class RecursionandDynamicProgrammingTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testFibonacci() {
		int n = new RecursionandDynamicProgramming().fibonacci(7);
		assertEquals(13, n);
		
		int n1 = new RecursionandDynamicProgramming().recursiveFib(6);
		assertEquals(8, n1);
	}

	@Test
	public void testTripleSteps() {
		String n = new RecursionandDynamicProgramming().cntWaysUpStairsInStepOf(5).toString();
		assertEquals(String.valueOf(13), n);
	}
	
	@Test
	public void testGetPath() {
		boolean[][] maze = new boolean[][] {
				{true, true, true},
				{true, false, true},
				{true, false, true}
		};
		
		ArrayList<GridPoint> array = new RecursionandDynamicProgramming().getPath(maze);
		
//		for(GridPoint p : array)
//			System.out.println(p);
	}
	
	@Test
	public void testMagicIndex() {
		int[] array1 = new int[]{-40, -20, -1, 1, 2, 3, 5, 7, 9, 12, 13};
		assertEquals(7, new RecursionandDynamicProgramming().magicIndex(array1));
		
		int[] array2 = new int[]{-10, -5, 1, 2, 2, 3, 4, 7, 9, 12, 13};
		assertEquals(7, new RecursionandDynamicProgramming().magicIndexNoDistinct(array2));
	}
	
	@Test
	public void testPowerset() {
		int[] array = new int[]{0, 1, 2, 3};
//		new RecursionandDynamicProgramming().allSubset(array);
//		new RecursionandDynamicProgramming().allSubsetFromBinary(array);
	}
	
	@Test
	public void testRecursiveMultiply() {
		int a = 101;
		int b = 254;
		
		assertEquals(a*b, new RecursionandDynamicProgramming().recursiveMultiplyByHalf(a, b));
//		assertEquals(a*b, new RecursionandDynamicProgramming().recursiveMultiply(a, b));
	}
	
	@Test
	public void testMoveHanoi() {
		ArrayList<Tower> towers = new ArrayList<>();
		
		for(int i = 0; i < 3; i++)
			towers.add(new Tower(i));
		
		for(int i = 5; i > 0; i--) {
			towers.get(0).addDisk(i);
		}
		
		new RecursionandDynamicProgramming().moveHanoi(towers.get(2), towers.get(0), towers.get(1));
//		System.out.println("DONE");
	}
	
	@Test
	public void testPermutationsWithoutDups() {
		String s = "tes";
		
		ArrayList<String> r = new RecursionandDynamicProgramming().permutationsWithoutDups(s);
		System.out.println("permutation total number is " + r.size() + ":");
		for(String s1 : r)
			System.out.println(s1);
	}
}
