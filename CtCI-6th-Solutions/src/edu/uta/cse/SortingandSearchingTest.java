package edu.uta.cse;

import static org.junit.Assert.*;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

public class SortingandSearchingTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testMergeSort() {
		int[] array = new int[] { 6, 5, 4, 3 };
		new SortingandSearching().mergeSort(array, 0, 3);
		assertArrayEquals(new int[] { 3, 4, 5, 6 }, array);
	}

	@Test
	public void testQuickSort() {
		int[] array = new int[] { 6, 5, 4, 3 };
		new SortingandSearching().quickSort(array);
		assertArrayEquals(new int[] { 3, 4, 5, 6 }, array);
	}

	@Test
	public void testBucketSort() {
		int[] array = new int[] { 6, 5, 4, 3, 19, 25 };
		new SortingandSearching().bucketSort(array, 25);
		assertArrayEquals(new int[] { 3, 4, 5, 6, 19, 25 }, array);
	}

	@Test
	public void testSortMerge() {
		int[] arrayA = new int[] { 1, 7, 9, 10, 0, 0, 0, 0 };
		int[] arrayB = new int[] { 4, 6, 8 };
		int[] arrayA1 = new int[] { 9, 10, 11, 12, 0, 0, 0, 0 };

		// new SortingandSearching().sortMerge(arrayA, arrayB);
		// assertArrayEquals(new int[]{1, 4, 6, 7, 8, 9, 10, 0}, arrayA);

		new SortingandSearching().sortMergeFromEnd(arrayA1, arrayB, 4, 3);
		assertArrayEquals(new int[] { 4, 6, 8, 9, 10, 11, 12, 0 }, arrayA1);
	}

	@Test
	public void testGroupingAnagram() {
		String[] array = new String[] { "abc", "ab", "bac", "cdb", "ba", "c" };
		new SortingandSearching().groupAnagrams(array);

		// System.out.println("DONE");
	}

	@Test
	public void testFindIndexInRotatedArray() {
		int[] array = new int[] { 15, 16, 19, 20, 25, 1, 3, 4, 5, 7, 10, 14 };
		// assertEquals(5, new SortingandSearching().findRotationHeader(array));
		assertEquals(8,
				new SortingandSearching().findIndexInRotatedArray(array, 5));
	}

	@Test
	public void testSortedSearchNoSize() {
		int[] array = new int[1<<10];
		int hold = (1 << 5);
		for (int i = 0; i < array.length; i++) {
			if (i < hold)
				array[i] = i;
			else
				array[i] = -1;

		}

		Listy list = new Listy(array);
		assertEquals(hold - 10, new SortingandSearching().sortedSearchNoSize(hold -10, list));

	}
	
	@Test
	public void testNumOfTotalPatterns() {
		assertEquals(2, new SortingandSearching().numOfTotalPatterns(1));
		assertEquals(23, new SortingandSearching().numOfTotalPatterns(5));
	}
	
	@Test
	public void testSparseSearch() {
		String[] sparseArray = new String[] {"at", "", "", "", "ball", "", "", "car", "", "", "dad", "", ""};
		assertEquals(4, new SortingandSearching().sparseSearch("ball", sparseArray));
	}
	
	@Test
	public void testFindMissingInt() throws IOException {
		try {
			
			 File data = new File("integers_test_2.data");
		      
		      if(!data.exists()) {
		    	  data.createNewFile();
		    	  
		    	  FileWriter writer = new FileWriter(data.getName(), true);
			      BufferedWriter buf = new BufferedWriter(writer);
			      
			      for(int i = 0; i < 1000000; i++) {
			    	  if(i != 140000)
			    		  buf.write(i + "\n");
			      }
			      
			      buf.close();
		      }

			assertEquals(12, new SortingandSearching().findMissingInt("integers_test.data"));
			assertEquals(140000, new SortingandSearching().findMissingIntLimitedMemory("integers_test_2.data"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
