package edu.uta.cse;

import java.util.Stack;

import org.junit.Before;
import org.junit.Test;

public class StacksandQueuesTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testSortStack() {
		Stack<Integer> stack = new Stack<>();
		stack.push(Integer.valueOf(5));
		stack.push(Integer.valueOf(1));
		stack.push(Integer.valueOf(4));
		stack.push(Integer.valueOf(2));
		stack.push(Integer.valueOf(3));

//		while(!stack.isEmpty())
//			System.out.println(stack.pop());
		
//		Stack<Integer> stackSorted = new StacksandQueues().sortStack(stack, 0);
		Stack<Integer> stackSorted = new StacksandQueues().sortStack(stack);

		
		while(!stackSorted.isEmpty())
			System.out.println(stackSorted.pop());
	}

}
