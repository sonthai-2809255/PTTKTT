/******************************************************************************
 *  Biên dịch:  javac BellmanFordSPToOD.java
 *  Thực thi:    java BellmanFordSPToOD filename.txt s
 *  Phụ thuộc:   EdgeWeightedDigraph.java DirectedEdge.java Queue.java
 *               EdgeWeightedDirectedCycle.java
 *  Tệp dữ liệu: https://algs4.cs.princeton.edu/44sp/tinyEWDn.txt
 *               https://algs4.cs.princeton.edu/44sp/mediumEWDnc.txt
 *
 *  Thuật toán đường đi ngắn nhất Bellman-Ford một đích . Tính toán cây đường đi ngắn nhất
 *  trong đồ thị có hướng có trọng số G từ đỉnh nguồn đến một đích s duyu nhất, hoặc tìm chu trình âm
 *  có thể đến được đỉnh đích s.
 *
 *Example với đỉnh s = 6 , và đồ thị từ file "tinyWED.txt"
 *  Thỏa mãn điều kiện tối ưu
0 đến 6 ( 1.45)  0->2  0.26   2->7  0.34   7->5 -0.28   5->1  0.32   1->3  0.29   3->6  0.52   
1 đến 6 ( 0.81)  1->3  0.29   3->6  0.52   
2 đến 6 ( 1.19)  2->7  0.34   7->5 -0.28   5->1  0.32   1->3  0.29   3->6  0.52   
3 đến 6 ( 0.52)  3->6  0.52   
4 đến 6 ( 1.22)  4->7  0.37   7->5 -0.28   5->1  0.32   1->3  0.29   3->6  0.52   
5 đến 6 ( 1.13)  5->1  0.32   1->3  0.29   3->6  0.52   
6 đến 6 ( 0.00)  
7 đến 6 ( 0.85)  7->5 -0.28   5->1  0.32   1->3  0.29   3->6  0.52   
 *  
 *  
 *
 ******************************************************************************/

 

/**
 *  Lớp {@code BellmanFordSPToOD} đại diện cho một kiểu dữ liệu để giải quyết bài toán
 *  đường đi ngắn nhất từ mội đỉnh trong đồ thị đến đỉnh đích duy nhất trong đồ thị có hướng có trọng số mà không có
 *  chu trình âm. Trọng số của các cạnh có thể là dương, âm hoặc bằng không.
 *  Lớp này tìm đường đi ngắn nhất từ  mọi đỉnh khác đến s hoặc một chu trình
 *  âm .
 *  
 */
public class BellmanFordSPToOD {
    private double[] distTo;               // distTo[v] = khoảng cách của đường đi ngắn nhất từ một v trong đồ thị -> s
    private DirectedEdge[] edgeTo;         // edgeTo[v] = cạnh cuối cùng trên đường đi ngắn nhất v->s
    private boolean[] onQueue;             // onQueue[v] = đỉnh v có đang trong hàng đợi không?
    private Queue<Integer> queue;          // hàng đợi các đỉnh cần nới lỏng
    private int cost;                      // số lần gọi đến relax()
    private Iterable<DirectedEdge> cycle;  // chu trình âm (hoặc null nếu không có chu trình âm)

    /**
     * Tính toán cây đường đi ngắn nhất từ mọi đỉnh khác đến {@code s}
     * đồ thị có hướng có trọng số {@code G}.
     * @param G đồ thị có hướng không có chu trình với túi cạnh vào
     * @param s đỉnh đích
     * @throws IllegalArgumentException nếu {@code 0 <= s < V} không thỏa mãn
     */
    public BellmanFordSPToOD(EdgeWeightedDigraph_T G, int s) {
        distTo  = new double[G.V()];
        edgeTo  = new DirectedEdge[G.V()];
        onQueue = new boolean[G.V()];
        for (int v = 0; v < G.V(); v++)
            distTo[v] = Double.POSITIVE_INFINITY;
        distTo[s] = 0.0;

        // Thuật toán Bellman-Ford
        queue = new Queue<Integer>();// chứa các đỉnh xét
        queue.enqueue(s);// chất vào hàng đợi đỉnh đích
        onQueue[s] = true;// đánh dấu là đỉnh đã có trong hàng đợi
        while (!queue.isEmpty() && !hasNegativeCycle()) // kiểm tra hàng đợi có trống không và có chu trình âm không
        {
            int v = queue.dequeue();// lấy đỉnh để xét
            onQueue[v] = false;// đánh dấu không còn trong hàng đợi
            relax(G, v);// nới lỏng đỉnh đấy trong đồ thị G
        }

        assert check(G, s);//kiểm tra tối ưu
    }

    // Nới lỏng đỉnh v và đưa các đỉnh nguồn khác vào hàng đợi nếu có thay đổi
    private void relax(EdgeWeightedDigraph_T G, int v) // đầu vào là đồ thị và đỉnh v coi như đích của bài toán con
    {
        for (DirectedEdge e : G.adj(v)) // duyệt từng cạnh trong túi cạnh cùng đi vào đỉnh v
        {
            int w = e.from();// lấy đỉnh gốc của V trong cạnh e( là cạnh đang xét)
            if (distTo[w] > distTo[v] + e.weight()) //so sánh trọng số của đường đi từ đỉnh gốc w -> đích của bài toán lớn và 
                                                    // đường đi từ đỉnh gốc v -> đích của bài toán lớn + trọng số cạnh e
            {
                distTo[w] = distTo[v] + e.weight();// cập nhật lại trọng số
                edgeTo[w] = e;// cập nhật lại cạnh 
                if (!onQueue[w]) {// nếu đỉnh đấy chưa có trong hàng đợi
                    queue.enqueue(w);// thêm vào hàng đợi để lần sau còn xét các gốc của nó
                    onQueue[w] = true;// đánh dấu đã có trong hàng đợi
                }
               
            }
            if (cost++ % G.V() == 0) {
                findNegativeCycle();//tìm chu trình âm từ các cạnh trong edgeTo[]
                if (hasNegativeCycle()) return;  // tìm thấy chu trình âm
            }
        }
    }

    /**
     * Có chu trình âm nào  không?
     * 
     */
    public boolean hasNegativeCycle() {
        return cycle != null;
    }

    /**
     * Trả về một chu trình âm có từ đích, hoặc {@code null}
     * nếu không có chu trình như vậy.
     * @return một chu trình âm dưới dạng một iterable của các cạnh, và {@code null} nếu không có chu trình như vậy
     */
    public Iterable<DirectedEdge> negativeCycle() {
        return cycle;
    }

    // Tìm chu trình âm bằng cách tìm chu trình trong đồ thị tiền nhiệm
    private void findNegativeCycle() {
        int V = edgeTo.length;
        EdgeWeightedDigraph_T spt = new EdgeWeightedDigraph_T(V);
        for (int v = 0; v < V; v++)
            if (edgeTo[v] != null)
                spt.addEdge(edgeTo[v]);

        EdgeWeightedDirectedCycle_T finder = new EdgeWeightedDirectedCycle_T(spt);
        cycle = finder.cycle();
    }

    /**
     * Trả về độ dài của đường đi ngắn nhất từ đỉnh {@code v} đến đỉnh đích
     * @param  v đỉnh nguồn
     * @return độ dài của đường đi ngắn nhất từ đỉnh {@code v} đến đích;
     *         {@code Double.POSITIVE_INFINITY} nếu không có đường đi như vậy
     * @throws UnsupportedOperationException nếu có chu trình chi phí âm 
     *        
     * @throws IllegalArgumentException nếu {@code 0 <= đích < V} không thỏa mãn
     */
    public double distTo(int v) {
        validateVertex(v);
        if (hasNegativeCycle())// nếu có chu trình âm
            throw new UnsupportedOperationException("Tồn tại chu trình chi phí âm");
        return distTo[v];//trả về đường đi ngắn nhất(trọng số)
    }

    /**
     * Có đường đi từ đỉnh nguồn  {@code v} đến đích không?
     * @param  v đỉnh nguồn
     * @return {@code true} nếu có đường đi từ đỉnh nguồn
     *         {@code v} đến đỉnh đích, và {@code false} nếu ngược lại
     * @throws IllegalArgumentException nếu {@code 0 <= đích < V} không thỏa mãn
     */
    public boolean hasPathTo(int v) {
        validateVertex(v);
        return distTo[v] < Double.POSITIVE_INFINITY;
    }

    /**
     * Trả về đường đi ngắn nhất từ đỉnh nguồn {@code v} đến đỉnh {@code s}.
     * @param  s đỉnh đích
     * @return đường đi ngắn nhất từ đỉnh nguồn {@code v} đến đỉnh {@code s}
     *         dưới dạng một iterable của các cạnh, và {@code null} nếu không có đường đi như vậy
     * @throws UnsupportedOperationException nếu có chu trình chi phí âm có thể đến được
     *         từ đỉnh nguồn {@code v}
     * @throws IllegalArgumentException nếu {@code 0 <= s < V} không thỏa mãn
     */
    public Iterable<DirectedEdge> pathTo(int v) {// đường đi từ v tới đích
        validateVertex(v);
        if (hasNegativeCycle())
            throw new UnsupportedOperationException("Tồn tại chu trình chi phí âm");
        if (!hasPathTo(v)) return null;
        Stack<DirectedEdge> pathtemp = new Stack<DirectedEdge>();
        for (DirectedEdge e = edgeTo[v]; e != null; e = edgeTo[e.to()]) {
            pathtemp.push(e);
        }
        // đảo ngược danh sách
        Stack<DirectedEdge> path = new Stack<DirectedEdge>();
         for(DirectedEdge e : pathtemp){
             path.push(e);
         }
        return path;
    }

    // Kiểm tra điều kiện tối ưu: 
    // (i) tồn tại chu trình âm có thể đến được từ s
    //     hoặc 
    // (ii)  với mọi cạnh e = v->w:            distTo[w] <= distTo[v] + e.weight()
    // (ii') với mọi cạnh e = v->w trên SPT:   distTo[w] == distTo[v] + e.weight()
   private boolean check(EdgeWeightedDigraph_T G, int s) {
    // Kiểm tra có chu trình âm không
    if (hasNegativeCycle()) {
        double weight = 0.0;
        for (DirectedEdge e : negativeCycle()) {
            weight += e.weight();
        }
        if (weight >= 0.0) {
            System.err.println("Lỗi: trọng số của chu trình âm = " + weight);
            return false;
        }
    } else {
        // Kiểm tra tính nhất quán của distTo[s] và edgeTo[s]
        if (distTo[s] != 0.0) {
            System.err.println("distTo[s] không bằng 0.0");
            return false;
        }

        // Kiểm tra tính nhất quán của distTo[] và edgeTo[]
        for (int v = 0; v < G.V(); v++) {
            if (v == s) continue;
            if (distTo[v] < Double.POSITIVE_INFINITY && edgeTo[v] == null) {
                System.err.println("distTo[" + v + "] có giá trị hữu hạn nhưng edgeTo[" + v + "] lại null");
                return false;
            }
        }

        // Kiểm tra điều kiện: distTo[from] <= distTo[to] + weight
        for (int v = 0; v < G.V(); v++) {
            for (DirectedEdge e : G.adj(v)) {
                int w = e.from();
                if (distTo[w] > distTo[v] + e.weight()) {
                    System.err.println("Cạnh " + e + " không được nới lỏng đúng");
                    return false;
                }
            }
        }

        // Kiểm tra điều kiện: distTo[from] == distTo[to] + weight với cạnh trên SPT
        for (int w = 0; w < G.V(); w++) {
            if (edgeTo[w] == null) continue;
            DirectedEdge e = edgeTo[w];
            int v = e.to();
            if (distTo[w] != distTo[v] + e.weight()) {
                System.err.println("Cạnh " + e + " trên đường đi ngắn nhất không chặt");
                return false;
            }
        }
    }

    StdOut.println("Thỏa mãn điều kiện tối ưu");
    return true;
}


    // ném ra ngoại lệ IllegalArgumentException nếu {@code 0 <= v < V} không thỏa mãn
    private void validateVertex(int v) {
        int V = distTo.length;
        if (v < 0 || v >= V)
            throw new IllegalArgumentException("Đỉnh " + v + " không nằm trong khoảng từ 0 đến " + (V-1));
    }

    /**
     * Kiểm thử đơn vị cho kiểu dữ liệu {@code BellmanFordSPToOD}.
     *
     * @param args các đối số dòng lệnh
     */
    public static void main(String[] args) {
        In in = new In(args[0]);
        int s = Integer.parseInt(args[1]);
        EdgeWeightedDigraph_T G = new EdgeWeightedDigraph_T(in);

        BellmanFordSPToOD sp = new BellmanFordSPToOD(G, s);

        // in chu trình âm
        if (sp.hasNegativeCycle()) {
            StdOut.printf("chu trình âm: \n");
            for (DirectedEdge e : sp.negativeCycle())
                StdOut.println(e);
        }

        // in các đường đi ngắn nhất
        else {
            for (int v = 0; v < G.V(); v++) {
                if (sp.hasPathTo(v)) {
                    StdOut.printf("%d đến %d (%5.2f)  ", v, s, sp.distTo(v));
                    for (DirectedEdge e : sp.pathTo(v)) {
                        StdOut.print(e + "   ");
                    }
                    StdOut.println();
                }
                else {
                    StdOut.printf("%d đến %d           không có đường đi\n", v, s);
                }
            }
        }

    }

}

