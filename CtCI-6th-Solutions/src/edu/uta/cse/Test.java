package edu.uta.cse;

/**
 * To explore a java compiling error about generic type
 * @author Luke Li
 * @param <E>
 *
 */
public class Test<E> {

	public void outerMethod1(int p) {
		//do something with build-in type
	}
	
	public void outerMethod2(E p) {
		//do something with generic type
	}
	
	private class Inner{
		
		int m;
		E genericTypeVar;
		
		public void testOuterMethods() {
			
			/* OK */
			outerMethod1(m);
			
			/* Compiler Error: Arguments type */
			outerMethod2(genericTypeVar);
		}
	}
}
