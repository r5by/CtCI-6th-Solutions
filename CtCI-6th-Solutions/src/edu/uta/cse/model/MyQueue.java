package edu.uta.cse.model;

import java.util.NoSuchElementException;

/**
 * @author ruby_
 *  Simple queue implementation used for
 *	CtCI Chapter 3: Stacks and Queues
 */
public class MyQueue<E> {

//–---------------------------
//       Fields
//----------------------------
	private QueueNode first;
	private QueueNode last;
	private int size;
//----------------------------
//       Methods
//----------------------------
	
	/**
	 * Add item to the tail of queue
	 * @param pItem
	 */
	public void add(E pItem) {
		
		QueueNode newNode = new QueueNode(pItem);
		
		if(size == 0)
			first = last = newNode;
		else {
			last.next = newNode;
			newNode.prev = last;
			last = newNode;
		}

		size++;
	}
	
	/**
	 * Remove Item from head of queue
	 * @return
	 */
	public E remove() {
		if(size == 0) throw new NoSuchElementException();
		
		E removedItem = first.data;
		
		first = first.next;
		first.prev = null;
		
		size --;
		
		return removedItem;
	}
	
	/**
	 * Peek the queue head
	 * @return
	 */
	public E peek() {
		if(size == 0) throw new NoSuchElementException();
		
		return first.data;
	}
	
	public boolean isEmpty() {
		return size == 0;
	}
	
//----------------------------
//       Privates
//----------------------------
	private class QueueNode {
		QueueNode prev;
		QueueNode next;
		E data;
		
		public QueueNode(E pData) {
			data = pData;
		}
	}
}
