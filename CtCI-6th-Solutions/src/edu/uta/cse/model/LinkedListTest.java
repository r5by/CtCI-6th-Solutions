package edu.uta.cse.model;

import org.junit.Before;
import org.junit.Test;

public class LinkedListTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void test() {
		LinkedList<String> list = new LinkedList<String>();
		list.appendToTail("a");
		list.appendToTail("b");
		list.appendToTail("C");
		
		System.out.println(list.getIndex(1));
	}

}
