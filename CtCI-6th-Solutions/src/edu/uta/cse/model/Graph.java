package edu.uta.cse.model;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * CtCI Chapter 4: Trees & Graphs
 * @author ruby_
 *
 */
public class Graph<E> {

	private ArrayList<Node> nodes = new ArrayList<>();
	/* Adjacent list of node (IDs of graph) saved by Node-IDs key-value pair*/
	private HashMap<Node, ArrayList<Node>> adjacentList = new HashMap();
	
	public Graph(ArrayList<Node> pNodes) {
		nodes = pNodes;
	}
	
	public HashMap<Integer, BFSReversePathInfo> reverseBFS = new HashMap<>();
	/**
	 * Adding adjacent list information to given node, the first int passed is the target node, the rests are its adjacent nodes
	 * @param listOfNodes
	 */
	public void addAdjacentList(int...listOfNodes) {
		if(listOfNodes.length < 2)
			throw new IllegalArgumentException();
		
		ArrayList<Node> adjacentNodes = new ArrayList<Node>();
		
		for(int i = 1; i < listOfNodes.length; i++)
			adjacentNodes.add(getNode(listOfNodes[i]));
		
		adjacentList.put(getNode(listOfNodes[0]), adjacentNodes);
	}
	
	public ArrayList<Node> getAdjacentList(int nodeID) {
		return getAdjacentList(nodes.get(nodeID));
	}
	
	public ArrayList<Node> getAdjacentList(Node node) {
		return adjacentList.get(node);
	}
	
	public Node<E> getNode(int nodeID) {
		return nodes.get(nodeID);
	}
	
	public ArrayList<Node> getAllNodes() {
		return nodes;
	}
	
	/* Retrave path info in each BFS search */
	public void putBFSReverseInfo(int rootID, int nodeID, int bfsUpperNodeID) {
		if(reverseBFS.get(rootID) == null)
			reverseBFS.put(rootID, new BFSReversePathInfo(rootID));
		
		reverseBFS.get(rootID).setReverseInfo(nodeID, bfsUpperNodeID);
	}
	
	public Integer getBFSReverseInfo(int rootID, int nodeID) {
		return reverseBFS.get(rootID).getBFSUpperNodeID(nodeID);
	}
	
//----------------------------
//       Privates
//----------------------------
	private class BFSReversePathInfo {
		int rootNodeID;
		HashMap<Integer, Integer> bfsReversePathSet = new HashMap<>();
		
		public BFSReversePathInfo(int pRootNodeID) {
			rootNodeID = pRootNodeID;
		}
		
		public void setReverseInfo(int nodeID, int bfsUpperNodeID) {
			bfsReversePathSet.put(nodeID, bfsUpperNodeID);
		}
		
		public Integer getBFSUpperNodeID(int nodeID) {
			return bfsReversePathSet.get(nodeID);
		}
	}
	
	//TODO: Bug need to be fixed!! path 4->5->6
	public ArrayList<Integer> retrieveBFSPath(int endNodeID, int bfsRoot) {
		ArrayList<Integer> resultPath = new ArrayList<>();
		int linkedNodeID = endNodeID;

		while(linkedNodeID != bfsRoot) {
			resultPath.add(linkedNodeID);
			linkedNodeID = getBFSReverseInfo(bfsRoot, linkedNodeID);
		}
		
		resultPath.add(bfsRoot);
		return resultPath;
	}
}
