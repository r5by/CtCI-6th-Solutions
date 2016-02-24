package edu.uta.cse.model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class MyQueueTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void test() {
		MyQueue<String> queue = new MyQueue<>();
		
		queue.add("1");
		queue.add("2");
		queue.add("3");
		
		assertEquals("1", queue.peek());
		
		queue.remove();
		assertEquals("2", queue.peek());
		
		queue.remove();
		assertEquals("3", queue.peek());
	}

}
