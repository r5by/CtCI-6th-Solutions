package edu.uta.cse.model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class StackwithMinTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void test() {
		StackwithMin stack = new StackwithMin();
		stack.push(5);
		stack.push(6);
		stack.push(3);
		stack.push(7);
		
		assertEquals(3, stack.min());
		
		stack.pop();
		assertEquals(3, stack.min());

		stack.pop();
		assertEquals(5, stack.min());
	}

}
