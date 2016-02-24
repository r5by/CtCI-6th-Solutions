package edu.uta.cse;

import java.util.EmptyStackException;
import java.util.Stack;

/**
 * @author ruby_
 *
 * CtCI Chapter 3: Stacks and Queues Solutions
 */
public class StacksandQueues<E> {
//----------------------------
//       Q3.1: Three in One
//----------------------------
	/* Implemented in separate file: MultiStack.class */
	
//----------------------------
//       Q3.2: Stack Min
//----------------------------
	/* Implemented in separate file: StackwithMin.class */
	
//----------------------------
//       Q3.3: Stack of Plates
//----------------------------
	/* Implemented in separate file: SetOfStacks.class */
	
//----------------------------
//       Q3.4: Queue via Stack
//----------------------------
	/*Implemented in separate file: QueueViaStacks.class */

//----------------------------
//       Q3.5: Sort Stack
//---------------------------
	/*Solution 1: Find each time the biggest elements and push it to the sorted depth, recursively solve the question O(N^2) cost*/
	private static Stack<Integer> tmpStack = new Stack<Integer>();
	
	public Stack<Integer> sortStack(Stack<Integer> stack, int sortedDepth) {
		if(stack.size() == 0)
			throw new EmptyStackException();
		else if(stack.size() > 1 && sortedDepth != stack.size() - 1) {
			Integer currentBiggest = stack.pop();
			int cnt = 0;
			int popCnt = stack.size() - 1 - sortedDepth;
			while(!stack.isEmpty() && cnt < popCnt) {
				Integer tmp = stack.pop();
				if(tmp < currentBiggest)
					tmpStack.push(tmp);
				else {
					tmpStack.push(currentBiggest);
					currentBiggest = tmp;
				}
				
				cnt++;
			}
			
			stack.push(currentBiggest);
			
			while(!tmpStack.isEmpty())
				stack.push(tmpStack.pop());
		} else {
			return stack;
		}
		
		sortedDepth++;
		
		return sortStack(stack, sortedDepth);
	}
	
	/* Solution 2: Making one stack sorted, for each poped element find the right place to "insert" it by moving the bigger element to the stack
	 * O(N^2) cost */
	public Stack<Integer> sortStack(Stack<Integer> stack) {
		Stack<Integer> sortedStack = new Stack<Integer>();
		while(!stack.isEmpty()) {
			Integer tmp = stack.pop();
			
			while(!sortedStack.isEmpty() && tmp > sortedStack.peek())
				stack.push(sortedStack.pop());
			
			sortedStack.push(tmp);
		}
		
		return sortedStack;
	}
}
