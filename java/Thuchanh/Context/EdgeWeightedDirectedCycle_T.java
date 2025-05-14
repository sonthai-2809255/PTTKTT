/******************************************************************************
 *  Biên dịch:  javac EdgeWeightedDirectedCycle.java
 *  Thực thi:    java EdgeWeightedDirectedCycle V E F
 *  Phụ thuộc:   EdgeWeightedDigraph.java DirectedEdge.java Stack.java
 *
 *  Tìm một chu trình có hướng trong đồ thị có hướng có trọng số.
 *  Thời gian chạy: O(E + V).
 *
 ******************************************************************************/

public class EdgeWeightedDirectedCycle_T {
    private boolean[] marked;             // marked[v] = đỉnh v đã được đánh dấu chưa?
    private DirectedEdge[] edgeTo;        // edgeTo[v] = cạnh trước đó trên đường đi tới v
    private boolean[] onStack;            // onStack[v] = đỉnh v có đang nằm trên stack không?
    private Stack<DirectedEdge> cycle;    // chu trình có hướng (hoặc null nếu không có chu trình)

    /**
     * Xác định xem đồ thị có hướng có trọng số {@code G} có chứa một chu trình có hướng hay không và,
     * nếu có, tìm chu trình đó.
     * @param G đồ thị có hướng có trọng số
     */
    
    /** với DirectedEdge e: w->v */ 
    
    public EdgeWeightedDirectedCycle_T(EdgeWeightedDigraph_T G) {
        marked  = new boolean[G.V()];
        onStack = new boolean[G.V()];
        edgeTo  = new DirectedEdge[G.V()];
        for (int v = 0; v < G.V(); v++)
            if (!marked[v]) dfs(G, v);

        // kiểm tra xem đồ thị có chu trình không
        assert check();
    }

    // kiểm tra xem thuật toán tính toán thứ tự tô-pô hoặc tìm thấy chu trình có hướng
    private void dfs(EdgeWeightedDigraph_T G, int v) {
        onStack[v] = true;
        marked[v] = true;
        for (DirectedEdge e : G.adj(v)) //duyệt trong túi cạnh vào đỉnh v
        {
            int w = e.from();
            
            // dừng ngay nếu tìm thấy chu trình có hướng
            if (cycle != null) return;
            // tìm thấy đỉnh mới, tiếp tục đệ quy
            else if (!marked[w]) {
                edgeTo[w] = e;
                dfs(G, w);
            }
            // truy vết lại chu trình có hướng
            else if (onStack[w]) {
                cycle = new Stack<DirectedEdge>();

                DirectedEdge f = e;
                while (f.to() != w) {
                    cycle.push(f);
                    f = edgeTo[f.to()];
                }
                cycle.push(f);

                return;
            }
        }
        onStack[v] = false;
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
    public Iterable<DirectedEdge> cycle() {
        return cycle;
    }


    // xác minh rằng đồ thị hoặc là không có chu trình hoặc có chứa chu trình có hướng
    private boolean check() {

        // đồ thị có hướng có trọng số có chu trình
        if (hasCycle()) {
            // xác minh chu trình
            DirectedEdge first = null, last = null;
            for (DirectedEdge e : cycle()) {
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
        EdgeWeightedDigraph_T G = new EdgeWeightedDigraph_T(in);
        
        StdOut.println(G);

        // tìm một chu trình có hướng
        EdgeWeightedDirectedCycle_T finder = new EdgeWeightedDirectedCycle_T(G);
        if (finder.hasCycle()) {
            Stack<DirectedEdge> cycle = new Stack<>();
            StdOut.print("Chu trình: ");
            for (DirectedEdge e : finder.cycle()) {
                cycle.push(e);
            }
            for(DirectedEdge e: cycle){
                StdOut.print(e + " ");
            }
            StdOut.println();
        }
        else {
            StdOut.println("Không có chu trình có hướng");
        }
    }

}

