package edu.uta.cse;

import java.util.NoSuchElementException;
import java.util.Stack;

/**
 * Used for CtCI: Chapter 8 Recursion and Dynamic Programming Q8.6: Moving Hanoi
 * 
 * @author ruby_
 *
 */
public class Tower {

	private Stack<Integer> disks;
	private int id;

	public Tower(int pID) {
		id = pID;
		disks = new Stack<Integer>();
	}

	public int getID() {
		return id;
	}

	public int size() {
		return disks.size();
	}

	public void addDisk(int i) {
		if (!disks.isEmpty() && i > disks.peek())
			throw new Error(
					"Placing disk on top of tower error: can't place larger disk on smaller ones!");

		disks.push(i);
	}

	public void moveTopDiskToTower(Tower t) {
		t.addDisk(disks.pop());
	}

	public void moveTopNDisksToTower(int n, Tower t, Tower buffer) {
		if (n > disks.size())
			throw new NoSuchElementException();

		/*NOTE: The guard condition n > 0 is crutial to prevent stack overflow!*/
		if (n > 0) {
			moveTopNDisksToTower(n - 1, buffer, t);
			moveTopDiskToTower(t);
			buffer.moveTopNDisksToTower(n - 1, t, this);
		}

	}

}
