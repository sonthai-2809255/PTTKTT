import edu.princeton.cs.algs4.ST;/******************************************************************************
 *  Biên dịch:  javac EdgeWeightedDirectedCycle.java
 *  Thực thi:    java EdgeWeightedDirectedCycle V E F
 *  Phụ thuộc:   EdgeWeightedDigraph.java DirectedEdge.java Stack.java
 *
 *  Tìm một chu trình có hướng trong đồ thị có hướng có trọng số.
 *  Thời gian chạy: O(E + V).
 *
 ******************************************************************************/

public class EdgeWeightedDirectedCycle_Vertex {
    private ST<Vertex,Boolean> marked;             // marked[v] = đỉnh v đã được đánh dấu chưa?
    private ST<Vertex, DirectedEdge_Vertex>  edgeTo;        // edgeTo[v] = cạnh trước đó trên đường đi tới v
    private ST<Vertex, Boolean > onStack;            // onStack[v] = đỉnh v có đang nằm trên stack không?
    private Stack<DirectedEdge_Vertex> cycle;    // chu trình có hướng (hoặc null nếu không có chu trình)

    /**
     * Xác định xem đồ thị có hướng có trọng số {@code G} có chứa một chu trình có hướng hay không và,
     * nếu có, tìm chu trình đó.
     * @param G đồ thị có hướng có trọng số
     */

    /** với DirectedEdge e: w->v */

    public EdgeWeightedDirectedCycle_Vertex(EdgeWeightedDigraph_Vertex G) {
        marked  = new ST<>();
        onStack =new ST<>();
        edgeTo  = new ST<>();
        for(int v =0; v<G.V();v++){
            Vertex node = new Node(v);
            marked.put(node,false);
            onStack.put(node,false);
        }
        for (int v = 0; v < G.V(); v++) {
            Vertex node = new Node(v);
            if (!marked.get(node)) {
                dfs(G, node);
            }
        }
        // kiểm tra xem đồ thị có chu trình không
        assert check();
    }

    // kiểm tra xem thuật toán tính toán thứ tự tô-pô hoặc tìm thấy chu trình có hướng
    private void dfs(EdgeWeightedDigraph_Vertex G, Vertex v) {
        onStack.put(v,true);
        marked.put(v,true);
        for (DirectedEdge_Vertex e : G.adj(v)) //duyệt trong túi cạnh vào đỉnh v
        {
            Vertex w = e.from();
            // dừng ngay nếu tìm thấy chu trình có hướng
            if (cycle != null) return;
            // tìm thấy đỉnh mới, tiếp tục đệ quy
            else if (!marked.get(w)) {
                edgeTo.put(w,e);
                dfs(G, w);
            }
            // truy vết lại chu trình có hướng
            else if (onStack.get(w)) {
                cycle = new Stack<DirectedEdge_Vertex>();

                DirectedEdge_Vertex f = e;
                while (!f.to().equals(w)) {
                    cycle.push(f);
                    f = edgeTo.get(f.to());
                }
                cycle.push(f);

                return;
            }
        }
        onStack.put(v,false);
    }

    /**
     * Đồ thị có hướng có trọng số có chứa chu trình có hướng không?
     * @return {@code true} nếu đồ thị có chứa chu trình có hướng,
     * {@code false} nếu ngược lại
     */
    public boolean hasCycle() {
        return cycle != null;
    }

    /**
     * Trả về một chu trình có hướng nếu đồ thị có hướng có trọng số có chứa chu trình có hướng,
     * và {@code null} nếu ngược lại.
     * @return một chu trình có hướng (dưới dạng iterable) nếu đồ thị có chứa chu trình có hướng,
     *    và {@code null} nếu ngược lại
     */
    public Iterable<DirectedEdge_Vertex> cycle() {
        return cycle;
    }


    // xác minh rằng đồ thị hoặc là không có chu trình hoặc có chứa chu trình có hướng
    private boolean check() {

        // đồ thị có hướng có trọng số có chu trình
        if (hasCycle()) {
            // xác minh chu trình
            DirectedEdge_Vertex first = null, last = null;
            for (DirectedEdge_Vertex e : cycle()) {
                if (first == null) first = e;
                
                if (last != null) {
                    if (last.from() != e.to()) {
                        System.err.printf("Các cạnh chu trình %s và %s không liên tiếp\n", last, e);
                        return false;
                    }
                }
                last = e;
            }

            if (last.from() != first.to()) {
                System.err.printf("Các cạnh chu trình %s và %s không liên tiếp\n", last, first);
                return false;
            }
        }

        return true;
    }

    /**
     * Unit tests cho kiểu dữ liệu {@code EdgeWeightedDirectedCycle}.
     *
     * @param args các đối số dòng lệnh
     */
    public static void main(String[] args) {
        //tạo một đồ thị theo file "tinyEWD.txt"
        In in = new In("tinyEWD.txt");
        EdgeWeightedDigraph_Vertex G = new EdgeWeightedDigraph_Vertex(in);
        
        StdOut.println(G);

        // tìm một chu trình có hướng
        EdgeWeightedDirectedCycle_Vertex finder = new EdgeWeightedDirectedCycle_Vertex(G);
        if (finder.hasCycle()) {
            Stack<DirectedEdge_Vertex> cycle = new Stack<>();
            StdOut.print("Chu trình: ");
            for (DirectedEdge_Vertex e : finder.cycle()) {
                cycle.push(e);
            }
            for(DirectedEdge_Vertex e: cycle){
                StdOut.print(e + " ");
            }
            StdOut.println();
        }
        else {
            StdOut.println("Không có chu trình có hướng");
        }
    }

}

