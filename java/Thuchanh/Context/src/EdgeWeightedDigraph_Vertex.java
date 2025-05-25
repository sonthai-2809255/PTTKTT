import edu.princeton.cs.algs4.ST;

/**
 * Example :kết quả với các túi cạnh vào , file"tinyEWDn.txt"
8 15
0: 6->0  0.58  
1: 5->1  0.32  
2: 6->2  0.40  0->2  0.26  
3: 1->3  0.29  7->3  0.39  
4: 6->4  0.93  0->4  0.38  5->4  0.35  
5: 7->5  0.28  4->5  0.35  
6: 3->6  0.52  
7: 2->7  0.34  5->7  0.28  4->7  0.37    
 */
public class EdgeWeightedDigraph_Vertex {
    private static final String NEWLINE = System.getProperty("line.separator");

    private final int V;                // number of vertices in this digraph
    private int E;                      // number of edges in this digraph
    private ST<Vertex, Bag<DirectedEdge_Vertex>> adj;    // adj[v] = list for s-> v
    private ST<Vertex, Integer> indegree;             // indegree[v] = indegree of vertex v

    /**
     * Initializes an empty edge-weighted digraph with {@code V} vertices and 0 edges.
     *
     * @param  V the number of vertices
     * @throws IllegalArgumentException if {@code V < 0}
     */
    public EdgeWeightedDigraph_Vertex(int V) {
        if (V < 0) throw new IllegalArgumentException("Number of vertices in a Digraph must be nonnegative");
        this.V = V;
        this.E = 0;
        this.indegree = new ST<>();
        adj = new ST<>();
        for (int v = 0; v < V; v++){
            Vertex w = new Node(v);
            if(!adj.contains(w))
            {
                adj.put(w, new Bag<DirectedEdge_Vertex>());
            }
            if(!indegree.contains(w))
            {
                indegree.put(w, 0);
            }
        }
    }

    /**
     * Initializes a random edge-weighted digraph with {@code V} vertices and <em>E</em> edges.
     *
     * @param  V the number of vertices
     * @param  E the number of edges
     * @throws IllegalArgumentException if {@code V < 0}
     * @throws IllegalArgumentException if {@code E < 0}
     */
    public EdgeWeightedDigraph_Vertex(int V, int E) {
        this(V);
        if (E < 0) throw new IllegalArgumentException("Number of edges in a Digraph must be nonnegative");
        for (int i = 0; i < E; i++) {
            int u = StdRandom.uniform(V);
            Vertex v = new Node(u);
            int r = StdRandom.uniform(V);
            Vertex w = new Node(r);
            double weight = 0.01 * StdRandom.uniform(100);
            DirectedEdge_Vertex e = new DirectedEdge_Vertex(v, w, weight);
            addEdge(e);
        }
    }

    /**
     * Initializes an edge-weighted digraph from the specified input stream.
     * The format is the number of vertices <em>V</em>,
     * followed by the number of edges <em>E</em>,
     * followed by <em>E</em> pairs of vertices and edge weights,
     * with each entry separated by whitespace.
     *
     * @param  in the input stream
     * @throws IllegalArgumentException if the endpoints of any edge are not in prescribed range
     * @throws IllegalArgumentException if the number of vertices or edges is negative
     */
    public EdgeWeightedDigraph_Vertex(In in) {
        this(in.readInt());
        int E = in.readInt();
        if (E < 0) throw new IllegalArgumentException("Number of edges must be nonnegative");
        for (int i = 0; i < E; i++) {
            Vertex v = new Node(in.readInt());
            Vertex w = new Node(in.readInt());

            validateVertex(v);
            validateVertex(w);
            double weight = in.readDouble();
            addEdge(new DirectedEdge_Vertex(v, w, weight));
        }
    }

    /**
     * Initializes a new edge-weighted digraph that is a deep copy of {@code G}.
     *
     * @param  G the edge-weighted digraph to copy
     */
    public EdgeWeightedDigraph_Vertex(EdgeWeightedDigraph_Vertex G) {
        this(G.V());
        this.E = G.E();
        for (int v = 0; v < G.V(); v++) {
            Node w = new Node(v);
            this.indegree.put(w,G.indegree(w));
        }
        for (int v = 0; v < G.V(); v++) {
            Node w = new Node(v);
            // reverse so that adjacency list is in same order as original
            Stack<DirectedEdge_Vertex> reverse = new Stack<DirectedEdge_Vertex>();
            for (DirectedEdge_Vertex e : G.adj.get(w)) {
                reverse.push(e);
            }
            for (DirectedEdge_Vertex e : reverse) {
                adj.get(w).add(e);
                indegree.put(w, indegree.get(w)+1);
            }
        }
    }

    /**
     * Returns the number of vertices in this edge-weighted digraph.
     *
     * @return the number of vertices in this edge-weighted digraph
     */
    public int V() {
        return V;
    }

    /**
     * Returns the number of edges in this edge-weighted digraph.
     *
     * @return the number of edges in this edge-weighted digraph
     */
    public int E() {
        return E;
    }

    // throw an IllegalArgumentException unless {@code 0 <= v < V}
    private void validateVertex(Vertex v) {
        if (Integer.parseInt(v.toString()) < 0 || Integer.parseInt(v.toString()) >= V)
            throw new IllegalArgumentException("vertex " + v + " is not between 0 and " + (V-1));
    }

    /**
     * Adds the directed edge {@code e} to this edge-weighted digraph.
     *
     * @param  e the edge
     * @throws IllegalArgumentException unless endpoints of edge are between {@code 0}
     *         and {@code V-1}
     */
    public void addEdge(DirectedEdge_Vertex e) {
        Vertex v = e.to();// w-> v
        Vertex w = e.from();
        validateVertex(v);
        validateVertex(w);
        if(!adj.contains(v)) adj.put(v, new Bag<DirectedEdge_Vertex>());
        adj.get(v).add(e);
        indegree.put(w, indegree.get(w)+1);
        E++;
    }


    /**
     * Returns the directed edges incident from vertex {@code v}.
     *
     * @param  v the vertex
     * @return the directed edges incident from vertex {@code v} as an Iterable
     * @throws IllegalArgumentException unless {@code 0 <= v < V}
     */
    public Iterable<DirectedEdge_Vertex> adj(Vertex v) {
        validateVertex(v);
        return adj.get(v);
    }

    /**
     * Returns the number of directed edges incident from vertex {@code v}.
     * This is known as the <em>outdegree</em> of vertex {@code v}.
     *
     * @param  v the vertex
     * @return the outdegree of vertex {@code v}
     * @throws IllegalArgumentException unless {@code 0 <= v < V}
     */
    public int outdegree(Vertex v) {
        validateVertex(v);
        return indegree.get(v);
    }

    /**
     * Returns the number of directed edges incident to vertex {@code v}.
     * This is known as the <em>indegree</em> of vertex {@code v}.
     *
     * @param  v the vertex
     * @return the indegree of vertex {@code v}
     * @throws IllegalArgumentException unless {@code 0 <= v < V}
     */
    public int indegree(Vertex v) {
        validateVertex(v);
        return indegree.get(v);
    }

    /**
     * Returns all directed edges in this edge-weighted digraph.
     * To iterate over the edges in this edge-weighted digraph, use foreach notation:
     * {@code for (DirectedEdge e : G.edges())}.
     *
     * @return all edges in this edge-weighted digraph, as an iterable
     */
    public Iterable<DirectedEdge_Vertex> edges() {
        Bag<DirectedEdge_Vertex> list = new Bag<DirectedEdge_Vertex>();
        for (int v = 0; v < V; v++) {
            Node w = new Node(v);
            for (DirectedEdge_Vertex e : adj.get(w)) {
                list.add(e);
            }
        }
        return list;
    } 

    /**
     * Returns a string representation of this edge-weighted digraph.
     *
     * @return the number of vertices <em>V</em>, followed by the number of edges <em>E</em>,
     *         followed by the <em>V</em> adjacency lists of edges
     */
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(V + " " + E + NEWLINE);
        for (int v = 0; v < V; v++) {
            Node w = new Node(v);
            s.append(w + ": ");
            for (DirectedEdge_Vertex e : adj.get(w)) {
                s.append(e + "  ");
            }
            s.append(NEWLINE);
        }
        return s.toString();
    }

    /**
     * Unit tests the {@code EdgeWeightedDigraph_T} data type.
     *
     * @param args the command-line arguments
     */
    public static void main(String[] args) {
        In in = new In("tinyEWD.txt");
        EdgeWeightedDigraph_Vertex G = new EdgeWeightedDigraph_Vertex(in);
        StdOut.println(G);
    }

}

/******************************************************************************
 *  Copyright 2002-2016, Robert Sedgewick and Kevin Wayne.
 *
 *  This file is part of algs4.jar, which accompanies the textbook
 *
 *      Algorithms, 4th edition by Robert Sedgewick and Kevin Wayne,
 *      Addison-Wesley Professional, 2011, ISBN 0-321-57351-X.
 *      http://algs4.cs.princeton.edu
 *
 *
 *  algs4.jar is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  algs4.jar is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with algs4.jar.  If not, see http://www.gnu.org/licenses.
 ******************************************************************************/
