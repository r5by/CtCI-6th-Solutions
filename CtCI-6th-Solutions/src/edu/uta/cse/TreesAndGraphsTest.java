package edu.uta.cse;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import edu.uta.cse.model.Graph;
import edu.uta.cse.model.Node;

public class TreesAndGraphsTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testIsRouteBtwn() {
		ArrayList<Node> nodes = new ArrayList<>();
		
		for (int i = 0; i < 7; i++) {
			Node<Integer> node = new Node(Integer.valueOf(i));
			nodes.add(node);
		}
		Graph graph = new Graph(nodes);
		graph.addAdjacentList(0, 1);
		graph.addAdjacentList(1, 2);
		graph.addAdjacentList(2, 0, 3);
		graph.addAdjacentList(3, 2);
		graph.addAdjacentList(4, 6);
		graph.addAdjacentList(5, 4);
		graph.addAdjacentList(6, 5);

		new TreesAndGraphs<Integer>().bfs(graph.getNode(0), graph);
		
		for (int i = 0; i < graph.getAllNodes().size(); i++) {
			if(graph.getNode(i).isMarked())
				System.out.println("node: " + i + " has been walked!");
		}
		
		ArrayList<Integer> path = new TreesAndGraphs<Integer>().findRouteBtw(graph.getNode(4), graph.getNode(6), graph);
		for(Integer i : path)
		System.out.println("Path in btw 3 and 0: " + i);	 
	}

}
