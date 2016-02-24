package edu.uta.cse;

import static org.junit.Assert.*;

import java.util.Iterator;

import org.junit.Before;
import org.junit.Test;

import edu.uta.cse.model.LinkedList;

public class LinkedListsTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testRemoveDuplications() {
		LinkedList<String> list = new LinkedList<String>();
		list.appendToTail("a");
		list.appendToTail("a");
		list.appendToTail("a");
		list.appendToTail("b");
		list.appendToTail("b");
		list.appendToTail("C");
		list.appendToTail("C");
		list.appendToTail("c");
		list.appendToTail("c");
		
		//Test remove dups from linkedlist with buffer
		new LinkedLists<String>().removeDuplications(list);
		//Test remove dups from linkedlist without buffer
		new LinkedLists<String>().removeDupNoBuff(list);
		
		
		Iterator<String> iter = list.iterator();
		
//		while(iter.hasNext())
//			System.out.println(iter.next());
	}
	
	@Test
	public void testGetKthToLastElm() {
		LinkedList<String> list = new LinkedList<String>();
		list.appendToTail("1");
		list.appendToTail("2");
		list.appendToTail("3");
		list.appendToTail("4");
		list.appendToTail("5");
		
		String two = new LinkedLists<String>().getTheKthToLastElement(2, list);
		assertEquals("4", two);
	}

	
	@Test
	public void testPartitionLinkedList() {
		LinkedList<String> list = new LinkedList<String>();
		list.appendToTail("4");
		list.appendToTail("2");
		list.appendToTail("3");
		list.appendToTail("1");
		list.appendToTail("3");
		list.appendToTail("5");
		
		LinkedList<String> partitioned = new LinkedLists().partitionLinkedList("3", list);
		
//		Iterator<String> iter = partitioned.iterator();
//		while(iter.hasNext())
//		System.out.println(iter.next());
	}
	
	@Test
	public void testSumLinkedLists() {
		LinkedList<Integer> list1 = new LinkedList<>();
		LinkedList<Integer> list2 = new LinkedList<>();
		
		list1.appendToTail(Integer.valueOf(6));
		list1.appendToTail(Integer.valueOf(6));
		list1.appendToTail(Integer.valueOf(1));
		list1.appendToTail(Integer.valueOf(7));

		list2.appendToTail(Integer.valueOf(5));
		list2.appendToTail(Integer.valueOf(9));
		list2.appendToTail(Integer.valueOf(1));
		
		LinkedList<Integer> sum = new LinkedLists().sumLinkedLists(list1, list2);
		
		Iterator<Integer> iter = sum.iterator();
		while(iter.hasNext())
		System.out.println(iter.next());

	}
	
}
