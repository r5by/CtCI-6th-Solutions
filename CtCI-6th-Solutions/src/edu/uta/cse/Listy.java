package edu.uta.cse;

import java.util.ArrayList;
import java.util.Collections;
import java.util.NoSuchElementException;

/**
 * An array like data structure holds only positive integers
 * Used for CtCI Chapter 10 Question 10.4
 * @author ruby_
 *
 */
public class Listy {
	ArrayList<Integer> array;

	Listy() {
		array = new ArrayList<>();
	}

	Listy(int[] pArray) {
		array = new ArrayList<>();
		for (int i = 0; i < pArray.length; i++) {
			array.add(pArray[i]);
		}
	}

	void sort() {
		if (!array.isEmpty())
			Collections.sort(array);
	}

	int elementAt(int i) {
		if (array.isEmpty())
			throw new NoSuchElementException();

		if (i >= 0 && i < array.size())
			return array.get(i);
		else
			return -1;
	}
}
