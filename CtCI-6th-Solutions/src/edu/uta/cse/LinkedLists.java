package edu.uta.cse;

import java.util.HashSet;
import java.util.Iterator;

import edu.uta.cse.model.LinkedList;

/**
 * Chapter 2: Linked Lists
 * @author ruby_
 * @param <E>
 *
 */
public class LinkedLists<E> {

//----------------------------
//       2.1 Remove Dups
//----------------------------
	/* Solution 1: With temp buffer */
	public void removeDuplications(LinkedList<E> list) {
		 HashSet<E> set = new HashSet<>();
		 
		 Iterator<E> iterator = list.iterator();
		 while(iterator.hasNext()) {
			E current = iterator.next();
			
			if(set.contains(current))
				iterator.remove();
			else
				set.add(current);
		 }
	}
	
	/*Solution 2: Without local storage */
	public void removeDupNoBuff(LinkedList<E> list) {
		Iterator<E> iterator = list.iterator();
		
		E target = null;
		while(iterator.hasNext()) {
			E current = iterator.next();
			
			if(current.equals(target))
				iterator.remove();
			else
				target=current;
		}
	}
	
//----------------------------
//       2.2 Return the Kth to last element (singly-linked list)
//----------------------------
	//Two pointers(iterator) solution
	public E getTheKthToLastElement(int k, LinkedList<E> list) {
		Iterator<E> iter1 = list.iterator();
		Iterator<E> iter2 = list.iterator();
		
		for(int i = 0; i < k; i++)
			iter1.next();
		
		while(iter1.hasNext()) {
				iter1.next();
				iter2.next();
		}
		
		return iter2.next();
	}
	
//----------------------------
//       2.3 Delete Middle Node (singly-linked list)
//----------------------------
	/*Skiped: Implemented in the linkedlist data structure */
	
//----------------------------
//       2.4 Partition
//----------------------------
	
	/**
	 * Partition a linkedlist with given value, such that all elements with smaller/greater value to its left/right
	 * @param pValue
	 * @param pList
	 * @return
	 */
	public LinkedList<E> partitionLinkedList(E pValue, LinkedList<E> pList) {
		LinkedList<E> list = new LinkedList<>();
		list.appendToHead(pValue);
		boolean onceFlag = true;
		
		Iterator<E> iter = pList.iterator();
		String value = pValue.toString();

		
		while(iter.hasNext()) {
			E current = iter.next();
			//NOTE: To simply the question using String comparable implementation
			if(value.compareTo(current.toString()) > 0)
				list.appendToHead(current);
			else if(value.compareTo(current.toString()) < 0)
				list.appendToTail(current);
			else
				//NOTE: The position of partition value doesn't matter as claimed in the question
				if(onceFlag)
					onceFlag = false;
				else
					list.appendToTail(current);
		}
		
		return list;
	}
	
//----------------------------
//       2.5 Sum Lists
//----------------------------

	public LinkedList<Integer> sumLinkedLists(LinkedList<Integer> pList1, LinkedList<Integer> pList2) {
		LinkedList<Integer> list = new LinkedList<>();
		
		int size1 = pList1.size();
		int size2 = pList2.size();
		
		if(size1 > size2)
			padList(pList2, size1 - size2);
		else
			padList(pList1, size2 - size1);
			
		Iterator<Integer> iter1 = pList1.iterator();
		Iterator<Integer> iter2 = pList2.iterator();
		
		boolean headerCarryFlag = adding(list, iter1, iter2, false);
		if(headerCarryFlag)
			list.appendToHead(Integer.valueOf(1));
		
		return list;
	}
	
	private void padList(LinkedList<Integer> pList, int numOfZeros) {
		for(int i = 0; i < numOfZeros; i++)
			pList.appendToHead(Integer.valueOf(0));
	}
	
	private boolean adding(LinkedList<Integer> pList, Iterator<Integer> pIter1, Iterator<Integer>pIter2, boolean carryFlag) {
		int v;
		boolean carry = false;
		if(pIter1.hasNext()) {
			
			final int v1 = pIter1.next().intValue();
			final int v2 = pIter2.next().intValue();
			
			//Recursively adding the elements to the linked list
			carry = adding(pList, pIter1, pIter2, carryFlag);
			
			if(carry)
				v = v1 + v2 + 1;
			else
				v = v1 + v2;
			
			pList.appendToHead(Integer.valueOf(v%10));
			
			if(v > 10)
				carry = true;
			else
				carry = false;
		}
		
		return carry;
	}
}

