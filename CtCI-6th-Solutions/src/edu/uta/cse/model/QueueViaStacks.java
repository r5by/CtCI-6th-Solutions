package edu.uta.cse.model;

import java.util.NoSuchElementException;

/**
 * CtCI Chapter 3 Stacks and Queues Q3.4
 * @author ruby_
 *
 * "Lazy" Approach: Have the newest element on top of newestStack and oldest element on top of oldestStack, 
 *  donot move elements to oldestStack until it's empty! 
 */
public class QueueViaStacks<E> {
//----------------------------
//       Fields
//----------------------------
	private MyStack<E> newestStack = new MyStack<>();
	private MyStack<E> oldestStack = new MyStack<>();
	private int size = 0;
//----------------------------
//       Methods
//----------------------------
	public void enqueue(E item) {
		newestStack.push(item);
		size++;
	}
	
	public E dequeue() {
		if(size == 0)
			throw new NoSuchElementException();
		
		if(oldestStack.isEmpty())
			while(!newestStack.isEmpty())
				oldestStack.push(newestStack.pop());
		
		size--; 
	
		return oldestStack.pop();
	}
	
	public E peek() {
		if(size == 0)
			throw new NoSuchElementException();
		
		if(oldestStack.isEmpty())
			while(!newestStack.isEmpty())
				oldestStack.push(newestStack.pop());
		
		return oldestStack.peek();
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

}
