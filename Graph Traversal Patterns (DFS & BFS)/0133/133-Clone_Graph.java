/*
// Definition for a Node.
class Node {
    public int val;
    public List<Node> neighbors;
    public Node() {
        val = 0;
        neighbors = new ArrayList<Node>();
    }
    public Node(int _val) {
        val = _val;
        neighbors = new ArrayList<Node>();
    }
    public Node(int _val, ArrayList<Node> _neighbors) {
        val = _val;
        neighbors = _neighbors;
    }
}
*/

class Solution {
    public Node cloneGraph(Node node) {
        if (node == null) return null;
        //Create a queue of all the neighbours in the graph
        HashMap<Node, Node> seen = new HashMap<>();
        Queue<Node> q = new LinkedList<>();
        Node clonedGraph = new Node(node.val);

        seen.put(node, clonedGraph);
        q.offer(node);
        

        while (!q.isEmpty()) {
            
            Node current = q.poll();

            //Add all the neighbours to the queue if they haven't been seen
            for (int j = 0; j < (current.neighbors).size(); j++) {
                Node neighbor = current.neighbors.get(j);
                if (!seen.containsKey(neighbor)) {
                    //The neighbour hasn't been seen, create a copy and add it to the map
                    Node copyNeighbor = new Node(neighbor.val);

                    seen.put(neighbor, copyNeighbor);
                    q.offer(neighbor);
                }
                //Connect the edge 
                seen.get(current).neighbors.add(seen.get(neighbor));
            }
            
        }

        return clonedGraph;
    }
}
