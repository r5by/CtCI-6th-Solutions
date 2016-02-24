package edu.uta.cse;

/**
 * Chapter 1: Arrays and Strings
 * 
 * @author ruby_
 *
 */
public class ArraysandStrings {
//----------------------------
//       Question 1.1: Is Unique
//----------------------------
	
	/*
	 * In this question, we write a method to determine if given string has all unique characters
	 * To simplify, we assume the character set is from 'a' to 'z' ASCII chars
	 */
	private static final int MAX_CHAR_CNT = 26;
	private static final int INIT_CHAR = 'a';
	
	public boolean isUniqueChars(String str) {
		if(str.length() > MAX_CHAR_CNT)
			return false;
		
		int check = 0;
		for (int i = 0; i < str.length(); i++) {
			int currentCheck = str.charAt(i) - INIT_CHAR;
			if((check & (1 << currentCheck)) != 0)
				return false;
			
			check |= (1 << currentCheck);
		}
		
		return true;
	}
	
	/* Extension: Full ASCII char support */
	private static final int MAX_CHAR_CNT_ASCII = 256;
	
	public boolean isUniqueASCIIChars(String str) {
		if(str.length() > MAX_CHAR_CNT_ASCII)
			return false;
		
		//Using 4 checks (long, each holds 64 bits) to check each of 4 sections of 256 chars in ASCII
		long check[] = {0, 0, 0, 0};

		for (int i = 0; i < str.length(); i++) {
			int target = str.charAt(i);
			int targetSection = charSection(target);
			switch(targetSection) {
			case 0:
				if((check[0] & (1<<target))!=0)
					return false;
				check[0] |= (1<<target);
				break;
			
			case 1:
				if((check[1] & (1<<(target-64)))!=0)
					return false;
				check[1] |= (1<<(target-64));
				break;
			
			case 2:
				if((check[2] & (1<<(target-128)))!=0)
					return false;
				check[2] |= (1<<(target-128));
				break;
			
			default:
				if((check[3] & (1<<(target-192)))!=0)
					return false;
				check[3] |= (1<<(target-192));
				break;
			}
		}
		return true;
	}
	
	/* Devide all 256 ASCII chars into 4 sections, determine the section ID of per char by passing the integer value */
	private int charSection(int pChar) {
		if(pChar < 64)
			return 0;
		else if(pChar < 128)
			return 1;
		else if(pChar < 192)
			return 2;
		else
			return 3;
	}
	
//----------------------------
//       1.2 String Permutation
//----------------------------
	
	public String sortString(String str) {
		char[] content = str.toCharArray();
		java.util.Arrays.sort(content);
		return new String(content);
	}
	
	public boolean permutationSensitive(String str1, String str2) {
		if(str1.length() != str2.length())
			return false;
		
		return sortString(str1).equals(sortString(str2));
	}
	
	public boolean permutationNonSensitive(String str1, String str2) {
		if(str1.length() != str2.length())
			return false;
		
		return sortString(str1.toLowerCase()).equals(sortString(str2.toLowerCase()));
	}
	
//----------------------------
//       1.3 URLify
//----------------------------
	
	public String URLify(String str) {
		String result = "";
		char[] content = str.toCharArray();
		for (int i = 0; i < checkEnd(content) + 1; i++) {
			if(content[i] == ' ')
				result+="20%";
			else
				result+=String.valueOf(content[i]);
		}
		
		return result;
	}
	
	/**
	 * Return the position of the last non-space char
	 * @param content
	 * @return
	 */
	private int checkEnd(char[] content) {
		int end = content.length - 1;
		while(content[end] == ' ')
			end--;
		
		return end;
	}
	
//----------------------------
//       1.4 Check palindrome permutation
//----------------------------
	/**
	 * A string is palindrome permuation if and only it only has 1 odd count number of chars
	 */
	public boolean checkPalindromePermutation(String str) {
		char[] content =  str.toLowerCase().toCharArray();
		java.util.Arrays.sort(content);
		
		int i = 0;
		while(i < content.length) {
			int j = moveIndex(i, content);
			if(i < j && (j-i+1)%2 == 1)
				return false;
			
			i = j+1;
		}
		
		return true;
	}
	
	/**
	 * Move index of array to the last same char position
	 * @param i
	 * @param array
	 * @return
	 */
	private int moveIndex(int i, char[] array) {
		while(i < array.length - 1 && array[i] == array[i+1])
				i++;
		
		return i;
	}
	
//----------------------------
//       1.5 One Edit Away
//----------------------------
	/**
	 * Check if given strings are one edit(replace/insert/delete only one char) away from another
	 */
	public boolean checkOneEditAway(String str1, String str2) {
		if(str1.length() == str2.length())
			return checkReplacement(str1, str2);
		else if (Math.abs(str1.length() - str2.length()) == 1)
			return checkInsertOrRemove(str1, str2);
		else
			return false;
	}
	
	private boolean checkReplacement(String str1, String str2) {
		char[] content1 = str1.toCharArray();
		char[] content2 = str2.toCharArray();
		int cnt = 0;
		for (int i = 0; i < content1.length; i++) {
			if(content1[i] != content2[i])
				cnt++;
		}
		if(cnt > 1)
			return false;
		
		return true;
	}
	
	private boolean checkInsertOrRemove(String str1, String str2) {
		char[] content1 = str1.toCharArray();
		char[] content2 = str2.toCharArray();
		int len = min(str1.length(), str2.length());
		int cnt = 0;
		if(content1.length == len) {
			for(int i = 0, j=0; i < len; i++, j++) {
				 if(content1[i] != content2[j]) {
					 j++;
					 cnt++;
				 }
			}
		} else {
			for(int i = 0, j = 0; i < len; i++, j++) {
				if(content2[i] != content1[j]) {
					j++;
					cnt++;
				}
			}
		}
		
		if(cnt > 1)
			return false;
		
		return true;
	}
	
	private int min(int a, int b) {
		return a < b ? a : b;
	}
	
//----------------------------
//       1.6 String Compression
//----------------------------
	public String basicStringCompress(String str) {
		String result = "";
		char[] content = str.toCharArray();
		int i = 0;
		while(i < content.length) {
			result += String.valueOf(content[i]);
			int start = i;
			i = moveIndex(i, content);
			
			if(i > start)
				result += String.valueOf(i-start+1);
			
			i++;
		}
		
		return result;
	}
	
//----------------------------
//       1.7, 1.8, 1.9 (Skipped)
//----------------------------
	
}
