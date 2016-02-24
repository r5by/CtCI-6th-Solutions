package edu.uta.cse.model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class SetOfStacksTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void test() {
		SetOfStacks<String> setStack = new SetOfStacks<>();
		
		for (int i = 0; i < 10; i++) {
			setStack.push(String.valueOf(i));
		}
		
		assertEquals("9", setStack.peek());
		assertEquals(4, setStack.numOfStacks());
		
		setStack.pop();
		assertEquals(3, setStack.numOfStacks());
		
		setStack.pop();
		assertEquals("7", setStack.peek());
		assertEquals(3, setStack.numOfStacks());
	}

}
