package edu.uta.cse;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;

import edu.uta.cse.model.Graph;
import edu.uta.cse.model.Node;

/**
 * CtCI Chapter 4: Trees & Graphs
 * @author ruby_
 *
 */
public class TreesAndGraphs<E> {
//----------------------------
//       Q4.1 Route Betw Nodes
//----------------------------
	private static final String MARK_TAG_1 = "RED";
	private static final String MARK_TAG_2 = "BLACK";

	
	public ArrayList<Integer> findRouteBtw(Node fromNode, Node toNode, Graph<E> graph) {
		/* Return a list of IDs found in the path */
		ArrayList<Integer> result = new ArrayList<>();
		Node meetNode = bidirectionalSearch(fromNode, toNode, graph);
		if(meetNode != null) {
			ArrayList<Integer> firstHalf = graph.retrieveBFSPath((Integer)meetNode.getItem(), (Integer)fromNode.getItem());
			ArrayList<Integer> secondHalf = graph.retrieveBFSPath((Integer)meetNode.getItem(), (Integer)toNode.getItem());
			Collections.reverse(firstHalf);
//			firstHalf.remove(firstHalf.size() - 1);
			result.addAll(firstHalf);
			result.addAll(secondHalf);
		}
		
		return result;
	}
	
	/* Queue used for bfs search */
	private Queue queue = new LinkedList<Node>();

	
	public void bfs(Node node, Graph<E> graph) {
		node.mark(true);
		queue.add(node);
		
		while(!queue.isEmpty()) {
			Node<Integer> r = (Node) queue.remove();
			
			for (Node<Integer> adjacentNode : graph.getAdjacentList(r)) {
				if(!adjacentNode.isMarked()) {
					adjacentNode.mark(true);
					queue.add(adjacentNode);
					
					//Record path information
					graph.putBFSReverseInfo((Integer)node.getItem(), adjacentNode.getItem(), r.getItem());
				}
			}
		}
	}
	
	/**
	 * Bidirectional search from node1 and node2
	 * @param node1
	 * @param node2
	 * @return
	 * 		return the frist node that has been reached from both node with BFS
	 * 		if there is no node meet the requirement, return null
	 */
	public Node bidirectionalSearch(Node node1, Node node2, Graph<E> graph) {
		
		Queue queue1 = new LinkedList<Node>();
		Queue queue2 = new LinkedList<Node>();
		
		node1.tag(MARK_TAG_1);
		queue1.add(node1);
		
		node2.tag(MARK_TAG_2);
		queue2.add(node2);
		
		while(!queue1.isEmpty() && !queue2.isEmpty()) {
			Node<Integer> r1 = (Node) queue1.remove();
			Node<Integer> r2 = (Node) queue2.remove();
			
			ArrayList<Node> adjacentNodes1 = graph.getAdjacentList(r1);
			ArrayList<Node> adjacentNodes2 = graph.getAdjacentList(r2);
			int searchRadius = Math.min(adjacentNodes1.size(), adjacentNodes2.size());
			
			for(int i = 0; i < searchRadius; i++) {
				if(adjacentNodes1.get(i).isTaggedWith(MARK_TAG_2))
					return adjacentNodes1.get(i);
				else if (adjacentNodes2.get(i).isTaggedWith(MARK_TAG_1))
					return adjacentNodes2.get(i);
			}

			for(Node<Integer> adjNode1 : adjacentNodes1)
				if(!adjNode1.isTaggedWith(MARK_TAG_1)) {
					adjNode1.tag(MARK_TAG_1);
					queue1.add(adjNode1);
					graph.putBFSReverseInfo((Integer)node1.getItem(), adjNode1.getItem(), r1.getItem());
				}
			
			for(Node<Integer> adjNode2 : adjacentNodes2)
				if(!adjNode2.isTaggedWith(MARK_TAG_2)) {
					adjNode2.tag(MARK_TAG_2);
					queue2.add(adjNode2);
					graph.putBFSReverseInfo((Integer)node2.getItem(), adjNode2.getItem(), r2.getItem());
				}
		}
		
		return null;
	}
}
