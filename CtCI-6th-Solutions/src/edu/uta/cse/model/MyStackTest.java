package edu.uta.cse.model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class MyStackTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void test() {
		MyStack<String> stack = new MyStack<>();
		
		stack.push("1");
		stack.push("2");
		stack.push("3");
		
		assertEquals("3", stack.peek());
		
		stack.pop();
		assertEquals("2", stack.peek());
	}

}
