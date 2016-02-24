package edu.uta.cse.model;

import java.util.ArrayList;
import java.util.EmptyStackException;

/**
 * CtCI: Chapter 3 Stacks and Queues 
 * @author ruby_
 * Question 3.3: Stack of plates
 */
public class SetOfStacks<E> {
//–---------------------------
//       Fields
//----------------------------
	private static final int DEFAULT_THRESHOLD = 3;
	private ArrayList<MyStack> stackSet = new ArrayList<>();
	private int size = 0;
//----------------------------
//       Methods
//----------------------------
	public void push(E item) {
		
		if(size != 0 && getLastStack().size() < DEFAULT_THRESHOLD)
			getLastStack().push(item);
		else {
			MyStack<E> stack = new MyStack<>();
			stack.push(item);
			stackSet.add(stack);
		}
		
		size++;
	}
	
	public E pop() {
		if(size == 0)
			throw new EmptyStackException();
		
		E poped = getLastStack().pop();
		if(getLastStack().size() == 0)
			removeLastStack();
		
		return poped;
			
	}
	
	public E peek() {
		if(size == 0)
			throw new EmptyStackException();
		
		return getLastStack().peek();
	}
	
	public boolean isEmpty() {
		return size == 0;
	}
	
	public int numOfStacks() {
		return stackSet.size();
	}
//----------------------------
//       Private
//----------------------------
	private MyStack<E> getLastStack() {
		if(size == 0)
			throw new EmptyStackException();
		
		return stackSet.get(stackSet.size() - 1);
	}
	
	private void removeLastStack() {
		if(size == 0)
			throw new EmptyStackException();
		
		stackSet.remove(stackSet.size() - 1);
	}
}
