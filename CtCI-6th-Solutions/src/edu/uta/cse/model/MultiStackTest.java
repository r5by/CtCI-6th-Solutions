package edu.uta.cse.model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class MultiStackTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void test() {
		MultiStack stack = new MultiStack(3);
		
		stack.pushStack(1, 0);
		stack.pushStack(2, 0);
		stack.pushStack(3, 0);
		
		stack.pushStack(4, 1);
		stack.pushStack(5, 1);
		
		assertEquals(3, stack.peek(0));
		
		stack.pop(0);
		assertEquals(2, stack.peek(0));
	
		assertEquals(true, stack.isEmpty(2));
	}

}
