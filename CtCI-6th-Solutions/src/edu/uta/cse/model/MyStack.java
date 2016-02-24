/**
 * 
 */
package edu.uta.cse.model;

import java.util.EmptyStackException;

/**
 * @author ruby_
 *
 * Simple implementation of stacks
 * CtCI: Chapter 3 Stacks and Queues
 */
public class MyStack<E> {
//----------------------------
//       Fields
//----------------------------
	private StackItem top;
	private int size = 0;
//----------------------------
//       Constructors
//----------------------------
	
//–---------------------------
//       Methods
//----------------------------
	public void push(E data) {
		StackItem newTop = new StackItem(data);
		newTop.next = top;
		top = newTop;
		
		size++;
	}
	
	public E pop() {
		if(top == null) throw new EmptyStackException();
		
		E result = top.data;
		top = top.next;
		
		size--;
		
		return result;
	}
	
	public E peek() {
		if(top == null) throw new EmptyStackException();
		return top.data;
	}
	
	public boolean isEmpty() {
		return size == 0;
	}
	
	public int size() {
		return size;
	}
//----------------------------
//       Privates
//----------------------------
	private class StackItem {
		E data;
		StackItem next;
		
		public StackItem(E pData) {
			data = pData;
		}
		
	}
}
