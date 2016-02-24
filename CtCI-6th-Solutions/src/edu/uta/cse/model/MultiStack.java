package edu.uta.cse.model;

import java.util.EmptyStackException;

/**
 * Q3.1: Single array implementation of "three in one" stack
 * 
 * The core idea is to store the ith (stack ID starts from 0) stack's elements in the (i + n*) postion.
 * Problem about this implementation is it's not "balanced" structure, e.g. only one stack has elements will cause n times extra space cost
 * @author ruby_
 *
 */
public class MultiStack {
//----------------------------
//       Fields
//----------------------------
	private int[] array;
	private int numberOfStacks = 0;
	private int[] stackInfo;
	
	private static final int SIZE_SCALE_FACTOR = 2;
	private static final int DEFAULT_INITIAL_SIZE = 10;
//----------------------------
//       Constructors
//----------------------------
	public MultiStack(int n) {
		numberOfStacks = n;
		array = new int[DEFAULT_INITIAL_SIZE];
		
		stackInfo = new int[n];
		java.util.Arrays.fill(stackInfo, -1);
	}
//----------------------------
//       Methods
//----------------------------
	public void pushStack(int pData, int pStackID) {
		int currentPos = stackInfo[pStackID];
		if(currentPos > array.length - stackInfo.length)
			scale();
		
		if(currentPos != -1) {
			array[currentPos + stackInfo.length] = pData;
			stackInfo[pStackID] = currentPos + stackInfo.length;
		}
		else {
			array[pStackID % stackInfo.length] = pData;
			stackInfo[pStackID] = pStackID % stackInfo.length;
		}
		
	}
	
	
	public int pop(int pStackID) {
		if(stackInfo[pStackID] < 0)
			throw new EmptyStackException();
		
		int result = array[stackInfo[pStackID]];
		stackInfo[pStackID] -= stackInfo.length;
		
		return result;
	}
	
	public int peek(int pStackID) {
		if(stackInfo[pStackID] == -1)
			throw new EmptyStackException();
	
		return array[stackInfo[pStackID]];
	}
	
	public boolean isEmpty(int pStackID) {
		return stackInfo[pStackID] == -1;
	}
//----------------------------
//       Private
//----------------------------
	private void scale() {
		 int[] newArray = java.util.Arrays.copyOf(array, array.length * SIZE_SCALE_FACTOR);
		 array = newArray;
	}
}
