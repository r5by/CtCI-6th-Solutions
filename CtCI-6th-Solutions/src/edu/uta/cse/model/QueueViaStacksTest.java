package edu.uta.cse.model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class QueueViaStacksTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void test() {
		QueueViaStacks<String> queue = new QueueViaStacks<>();
		
		for (int i = 0; i < 10; i++) {
			queue.enqueue(String.valueOf(i));
		}
		
		assertEquals("0", queue.peek());
		
		queue.dequeue();
		assertEquals("1", queue.peek());
	}

}
