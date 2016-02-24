package edu.uta.cse;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class ArraysandStringsTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testIsUniqueChars() {
		String str1 = "test";
		String str2 = "tesa";
		assertEquals(true, new ArraysandStrings().isUniqueChars(str2));
		assertEquals(false, new ArraysandStrings().isUniqueChars(str1));
	}
	
	@Test
	public void testIsUniqueASCIIChars() {
		String str1 = "test_123";
		String str2 = "t@se_123";
		assertEquals(true, new ArraysandStrings().isUniqueASCIIChars(str2));
		assertEquals(false, new ArraysandStrings().isUniqueASCIIChars(str1));
	}
	
	@Test
	public void testPermutation() {
		String str1 = "test_1";
		String str2 = "te1st_";
		String str3 = "test_2";
		String str4 = "TEST_1";
		
		assertEquals(true, new ArraysandStrings().permutationSensitive(str1, str2));
		assertEquals(false, new ArraysandStrings().permutationSensitive(str1, str3));
		assertEquals(true, new ArraysandStrings().permutationNonSensitive(str1, str4));
	}
	
	@Test
	public void testURLify() {
		String str1 = "Mr John  Smith    ";
//		System.out.println(new ArraysandStrings().URLify(str1));
	}
	
	@Test
	public void testPalindromePermutation() {
		String str1 = "Tact Coa";
		assertEquals(true, new ArraysandStrings().checkPalindromePermutation(str1));		
	}
	
	@Test
	public void testCheckOneEditAway() {
		String str1 = "test";
		String str2 = "teet";
		String str3 = "teee";
		String str4 = "tes";
		String str5 = "teest";
		String str6 = "test1";
		String str7 = "testxxx";
		
		assertEquals(true, new ArraysandStrings().checkOneEditAway(str1, str2));
		assertEquals(false, new ArraysandStrings().checkOneEditAway(str1, str3));
		assertEquals(true, new ArraysandStrings().checkOneEditAway(str1, str4));
		assertEquals(true, new ArraysandStrings().checkOneEditAway(str1, str5));
		assertEquals(true, new ArraysandStrings().checkOneEditAway(str1, str6));
		assertEquals(false, new ArraysandStrings().checkOneEditAway(str1, str7));
	}
	
	@Test
	public void testsBasicStringCompress() {
		String str1 = "aabcccccaaa";
		System.out.println(new ArraysandStrings().basicStringCompress(str1));
	}
}
