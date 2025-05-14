/******************************************************************************
 *  Compilation:  javac DijkstraSP.java
 *  Execution:    java DijkstraSP input.txt s
 *  Dependencies: EdgeWeightedDigraph_T.java IndexMinPQ.java Stack.java DirectedEdge.java
 *  Data files:   https://algs4.cs.princeton.edu/44sp/tinyEWD.txt
 *                https://algs4.cs.princeton.edu/44sp/mediumEWD.txt
 *                https://algs4.cs.princeton.edu/44sp/largeEWD.txt
 *
 *  Dijkstra's algorithm. Computes the shortest path tree.
 *  Assumes all weights are nonnegative.
 *
 *  % java DijkstraSD tinyEWD.txt 0
0 to 6 (1.51)  0->2  0.26   2->7  0.34   7->3  0.39   3->6  0.52   
1 to 6 (0.81)  1->3  0.29   3->6  0.52   
2 to 6 (1.25)  2->7  0.34   7->3  0.39   3->6  0.52   
3 to 6 (0.52)  3->6  0.52   
4 to 6 (1.28)  4->7  0.37   7->3  0.39   3->6  0.52   
5 to 6 (1.13)  5->1  0.32   1->3  0.29   3->6  0.52   
6 to 6 (0.00)  
7 to 6 (0.91)  7->3  0.39   3->6  0.52 
 *
 *  
 ******************************************************************************/





public class DijkstraSD {
    private double[] distTo;          // distTo[v] = distance  of shortest v->s path
    private DirectedEdge[] edgeTo;    // edgeTo[v] = last edge on shortest v->s path
    private IndexMinPQ<Double> pq;    // priority queue of vertices

    /**
     * Computes a shortest-paths tree from the source vertex {@code s} to every other
     * vertex in the edge-weighted digraph {@code G}.
     *
     * @param  G the edge-weighted digraph
     * @param  s the source vertex
     * @throws IllegalArgumentException if an edge weight is negative
     * @throws IllegalArgumentException unless {@code 0 <= s < V}
     */
    public DijkstraSD(EdgeWeightedDigraph_T G, int s) {
        for (DirectedEdge e : G.edges()) {
            if (e.weight() < 0)
                throw new IllegalArgumentException("edge " + e + " has negative weight");
        }

        distTo = new double[G.V()];
        edgeTo = new DirectedEdge[G.V()];

        validateVertex(s);

        for (int v = 0; v < G.V(); v++)
            distTo[v] = Double.POSITIVE_INFINITY;
        distTo[s] = 0.0;

        // relax vertices in order of distance from s
        pq = new IndexMinPQ<Double>(G.V());
        pq.insert(s, distTo[s]);

        // Bo sung vong while chon phan tu min trong PQ .......
        // nới lỏng các đỉnh theo thứ tự khoảng cách từ đỉnh nguồn s
        while(!pq.isEmpty()){
            int v= pq.delMin();//lấy đỉnh cuối của đường đi hiện tại có khoảng cách nhỏ nhất ra
            for(DirectedEdge e :G.adj(v))// duyệt qua các cạnh đi ra từ đỉnh v vừa lấy ra
            {
                relax(e);// nới lỏng các cạnh kề với đỉnh v hay các cạnh đi ra từ v
            }
        }


        // check optimality conditions
        assert check(G, s);
    }

    // relax edge e and update pq if changed
    //bản chất của relax là tìm đường đi có độ dài ngắn nhất từ nguồn đến e.to()
    private void relax(DirectedEdge e) {
        int w = e.from(), v = e.to();
        if (distTo[w] > distTo[v] + e.weight()) {
            distTo[w] = distTo[v] + e.weight();
            edgeTo[w] = e;
            if (pq.contains(w)) pq.decreaseKey(w, distTo[w]);
            else                pq.insert(w, distTo[w]);
        }
    }

    /**
     * Returns the length of a shortest path from the source vertex {@code s} to vertex {@code v}.
     * @param  v the destination vertex
     * @return the length of a shortest path from the source vertex {@code s} to vertex {@code v};
     *         {@code Double.POSITIVE_INFINITY} if no such path
     * @throws IllegalArgumentException unless {@code 0 <= v < V}
     */
    public double distTo(int v) {
        validateVertex(v);
        return distTo[v];
    }

    /**
     * Returns true if there is a path from the source vertex {@code s} to vertex {@code v}.
     *
     * @param  v the destination vertex
     * @return {@code true} if there is a path from the source vertex
     *         {@code s} to vertex {@code v}; {@code false} otherwise
     * @throws IllegalArgumentException unless {@code 0 <= v < V}
     */
    public boolean hasPathTo(int v) {
        validateVertex(v);
        return distTo[v] < Double.POSITIVE_INFINITY;
    }

    /**
     * Returns a shortest path from the source vertex {@code s} to vertex {@code v}.
     *
     * @param  v the destination vertex
     * @return a shortest path from the source vertex {@code s} to vertex {@code v}
     *         as an iterable of edges, and {@code null} if no such path
     * @throws IllegalArgumentException unless {@code 0 <= v < V}
     */
    public Iterable<DirectedEdge> pathTo(int v) {
        validateVertex(v);
        if (!hasPathTo(v)) return null;
        Stack<DirectedEdge> buf = new Stack<DirectedEdge>();
        Stack<DirectedEdge> path = new Stack<DirectedEdge>();
        for (DirectedEdge e = edgeTo[v]; e != null; e = edgeTo[e.to()]) {
            buf.push(e);
        }
        //
        for(DirectedEdge edge : buf){
            path.push(edge);
            buf.pop();
        }
        //
        return path;
    }


    // check optimality conditions:
    // (i) for all edges e:            distTo[e.to()] <= distTo[e.from()] + e.weight()
    // (ii) for all edge e on the SPT: distTo[e.to()] == distTo[e.from()] + e.weight()
    private boolean check(EdgeWeightedDigraph_T G, int s) {

        // check that edge weights are nonnegative
        for (DirectedEdge e : G.edges()) {
            if (e.weight() < 0) {
                System.err.println("negative edge weight detected");
                return false;
            }
        }

        // check that distTo[v] and edgeTo[v] are consistent
        if (distTo[s] != 0.0 || edgeTo[s] != null) {
            System.err.println("distTo[s] and edgeTo[s] inconsistent");
            return false;
        }
        
        for (int v = 0; v < G.V(); v++) {
            if (v == s) continue;
            if (edgeTo[v] == null && distTo[v] != Double.POSITIVE_INFINITY) {
                System.err.println("distTo[] and edgeTo[] inconsistent");
                return false;
            }
        }

        // check that all edges e = w->v satisfy distTo[w] <= distTo[v] + e.weight()
        for (int v = 0; v < G.V(); v++) {
            for (DirectedEdge e : G.adj(v)) {
                int w = e.from();
                if (distTo[v] + e.weight() < distTo[w]) {
                    System.err.println("edge " + e + " not relaxed");
                    return false;
                }
            }
        }

        // check that all edges e = w-> v on SPT satisfy distTo[w] == distTo[v] + e.weight()
        for (int w = 0; w < G.V(); w++) {
            if (edgeTo[w] == null) continue;
            DirectedEdge e = edgeTo[w];
            int v = e.to();
            if (w != e.from()) return false;
            if (distTo[v] + e.weight() != distTo[w]) {
                System.err.println("edge " + e + " on shortest path not tight");
                return false;
            }
        }
        return true;
    }

    // throw an IllegalArgumentException unless {@code 0 <= v < V}
    private void validateVertex(int v) {
        int V = distTo.length;
        if (v < 0 || v >= V)
            throw new IllegalArgumentException("vertex " + v + " is not between 0 and " + (V-1));
    }

    /**
     * Unit tests the {@code DijkstraSP} data type.
     *
     * @param args the command-line arguments
     */
    public static void main(String[] args) {
        //args[0]="tinyEWD.txt";
        In in = new In("tinyEWD.txt");
        EdgeWeightedDigraph_T G = new EdgeWeightedDigraph_T(in);

        //truyền đỉnh nguồn
        int s = Integer.parseInt("6");

        // compute shortest paths
        DijkstraSD sp = new DijkstraSD(G, s);

        // print shortest path
        for (int t = 0; t < G.V(); t++) {
            if (sp.hasPathTo(t)) {
                StdOut.printf("%d to %d (%.2f)  ", t, s, sp.distTo(t));
                for (DirectedEdge e : sp.pathTo(t)) {
                    StdOut.print(e + "   ");
                }
                StdOut.println();
            }
            else {
                StdOut.printf("%d to %d         no path\n", t, s);
            }
        }
    }

}


