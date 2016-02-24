package edu.uta.cse.model;

import java.util.ArrayList;

/**
 * CtCI Chapter 4: Trees & Graphs
 * @author ruby_
 *
 * @param <E>
 */
public class Node<E> {
	private E item;
	private boolean visited = false;
	private ArrayList<String> markTags = new ArrayList<>();
	
	public Node(E pItem) {
		item = pItem;
	}
	
	public E getItem() {
		return item;
	}
	
	public boolean mark(boolean flag) {
		return visited = flag;
	}
	
	public boolean isMarked() {
		return visited;
	}
	
	public void tag(String tag) {
		markTags.add(tag);
	}
	
	public boolean isTaggedWith(String tag) {
		return markTags.contains(tag);
	}
	
	public boolean isTagged() {
		return !markTags.isEmpty();
	}
}
