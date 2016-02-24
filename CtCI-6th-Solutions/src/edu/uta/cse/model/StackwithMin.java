package edu.uta.cse.model;

import java.util.EmptyStackException;

/**
 * CtCI Chapter 3 Stacks and Queues
 * @author ruby_
 *
 * Use a second stack to keep track of min value of the stack
 * To keep things simple, use int type as the stack elements
 */
public class StackwithMin {
//----------------------------
//       Fields
//----------------------------
	private MyStack<Integer> minStack = new MyStack();
	private int size = 0;
	private StackItem top;
	
//----------------------------
//       Methods
//----------------------------
	public void push(int p) {
		StackItem item = new StackItem(p);
		
		item.next = top;
		top = item;
		
		if(minStack.isEmpty() || minStack.peek().intValue() > p)
			minStack.push(Integer.valueOf(p));
		
		size++;
	}
	
	public int pop() {
		if(size == 0)
			throw new EmptyStackException();
		
		int result = top.data;
		top = top.next;
		
		size--;
		
		if(minStack.isEmpty() || minStack.peek().intValue() >= result )
			minStack.pop();
		
		return result;
	}
	
	public int peek() {
		if(size == 0)
			throw new EmptyStackException();
		
		return top.data;
	}
	
	public boolean isEmpty() {
		return size == 0;
	}
	
	public int min() {
		return minStack.peek().intValue();
	}
	
//----------------------------
//       Private
//----------------------------
	private class StackItem {
		int data;
		StackItem next;
		
		public StackItem(int p) {
			data = p;
		}
	}
	
}
