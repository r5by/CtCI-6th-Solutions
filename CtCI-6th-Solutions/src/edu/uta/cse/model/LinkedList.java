/**
 * 
 */
package edu.uta.cse.model;

import java.util.Iterator;

/**
 * @author ruby_
 * Basic Linked List
 * (Used for CtCI Chapter 2: Linked Lists)
 *
 */
public class LinkedList<E> implements Iterable<E>{
//----------------------------
//       Fields
//----------------------------
	private int size = 0;
	private Node<E> head;
	private Node<E> tail;
	
//----------------------------
//       Constructors
//----------------------------
	public LinkedList() {}
	
//----------------------------
//       Iterable Interface
//----------------------------
	@Override
	public Iterator<E> iterator() {
		return new MIterator();
	}

//---------------------------
//       Methods
//----------------------------
	public void appendToTail(E item) {
		if(size==0)
			head = tail = new Node<E>(null, null, item);
		else {
			Node<E> current = getTail();
			tail = new Node<E>(current, null, item);
			current.next = tail;
		}
		
		size++;
	}
	
	public void appendToHead(E item) {
		if(size==0)
			head = tail = new Node<E>(null, null, item);
		else {
			Node<E> current = getHead();
			head = new Node<E>(null, current, item);
			current.prev = head;
		}
		
		size++;
	}
	
	public void deleteNode(Node<E> node) {
		if(node == head) {
			head = node.next;
			head.prev = null;
			
			//Help GC
			node.next = null;
		} else if(node == tail) {
			tail = node.prev;
			tail.next = null;
			
			node.prev = null;
		} else {
			//Reset the list
			node.next.prev = node.prev;
			node.prev.next = node.next;
		}
		
		size--;
		node.item = null;
		node = null;
	}
	
//----------------------------
//       Getters&Setters
//----------------------------
	
	public E getIndex(int position) {

		if(position==0)
			return head.item;
		
		Node<E> pointer = head;
		for (int i = 0; i < position; i++)
			pointer = pointer.next;
		
		return pointer.item;
	}
	
	public int size() {
		return size;
	}

	public Node<E> getHead() {
		return head;
	}

	public Node<E> getTail() {
		return tail;
	}

//----------------------------
//       Private
//----------------------------
	private static class Node<E> {
		E item;
		Node<E> prev;
		Node<E> next;
		
		public Node(Node<E> prev, Node<E> next, E item) {
			this.prev = prev;
			this.next = next;
			this.item = item;
		}
	}
	
	private class MIterator implements Iterator<E> {
		Node<E> node = head;
		
		@Override
		public boolean hasNext() {
			return node != null;
		}
		
		@Override
		public E next() {
			E currentItem = node.item;
			node = node.next;
			return currentItem;
		}
		
		@Override
		public void remove() {
			if(node!=null)
				deleteNode(node.prev);
			else
				deleteNode(tail);
		}
	
	}
}
