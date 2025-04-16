/******************************************************************************
 *  Biên dịch:  javac EdgeWeightedDirectedCycle.java
 *  Thực thi:    java EdgeWeightedDirectedCycle V E F
 *  Phụ thuộc:   EdgeWeightedDigraph.java DirectedEdge.java Stack.java
 *
 *  Tìm một chu trình có hướng trong đồ thị có hướng có trọng số.
 *  Thời gian chạy: O(E + V).
 *
 ******************************************************************************/

 

/**
 *  Lớp {@code EdgeWeightedDirectedCycle} đại diện cho một kiểu dữ liệu để 
 *  xác định xem một đồ thị có hướng có trọng số có chứa một chu trình có hướng hay không.
 *  Phương thức <em>hasCycle</em> xác định xem đồ thị có hướng có trọng số có chứa
 *  một chu trình có hướng hay không và, nếu có, phương thức <em>cycle</em>
 *  sẽ trả về một chu trình như vậy.
 *  <p>
 *  Cài đặt này sử dụng tìm kiếm theo chiều sâu (DFS).
 *  Constructor mất thời gian tỷ lệ với <em>V</em> + <em>E</em>
 *  (trong trường hợp xấu nhất),
 *  trong đó <em>V</em> là số đỉnh và <em>E</em> là số cạnh.
 *  Sau đó, phương thức <em>hasCycle</em> mất thời gian hằng số;
 *  phương thức <em>cycle</em> mất thời gian tỷ lệ
 *  với độ dài của chu trình.
 *  <p>
 *  Xem {@link Topological} để tính thứ tự tô-pô nếu đồ thị có hướng
 *  không có chu trình.
 *  <p>
 *  Để biết thêm tài liệu,   
 *  xem <a href="https://algs4.cs.princeton.edu/44sp">Mục 4.4</a> của   
 *  <i>Algorithms, 4th Edition</i> bởi Robert Sedgewick và Kevin Wayne. 
 *
 *  @author Robert Sedgewick
 *  @author Kevin Wayne
 */
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

        // tạo đồ thị có hướng ngẫu nhiên với V đỉnh và E cạnh; sau đó thêm F cạnh ngẫu nhiên
        int V = Integer.parseInt(args[0]);
        int E = Integer.parseInt(args[1]);
        int F = Integer.parseInt(args[2]);
        EdgeWeightedDigraph_T G = new EdgeWeightedDigraph_T(V);
        int[] vertices = new int[V];
        for (int i = 0; i < V; i++)
            vertices[i] = i;
        StdRandom.shuffle(vertices);
        for (int i = 0; i < E; i++) {
            int v, w;
            do {
                v = StdRandom.uniform(V);
                w = StdRandom.uniform(V);
            } while (v >= w);
            double weight = StdRandom.uniform();
            G.addEdge(new DirectedEdge(v, w, weight));
        }

        // thêm F cạnh bổ sung
        for (int i = 0; i < F; i++) {
            int v = StdRandom.uniform(V);
            int w = StdRandom.uniform(V);
            double weight = StdRandom.uniform(0.0, 1.0);
            G.addEdge(new DirectedEdge(v, w, weight));
        }

        StdOut.println(G);

        // tìm một chu trình có hướng
        EdgeWeightedDirectedCycle_T finder = new EdgeWeightedDirectedCycle_T(G);
        if (finder.hasCycle()) {
            StdOut.print("Chu trình: ");
            for (DirectedEdge e : finder.cycle()) {
                StdOut.print(e + " ");
            }
            StdOut.println();
        }

        // hoặc in ra thứ tự tô-pô
        else {
            StdOut.println("Không có chu trình có hướng");
        }
    }

}

/******************************************************************************
 *  Bản quyền 2002-2016, Robert Sedgewick và Kevin Wayne.
 *
 *  Tệp này là một phần của algs4.jar, đi kèm với sách giáo khoa
 *
 *      Algorithms, 4th edition bởi Robert Sedgewick và Kevin Wayne,
 *      Addison-Wesley Professional, 2011, ISBN 0-321-57351-X.
 *      http://algs4.cs.princeton.edu
 *
 *
 *  algs4.jar là phần mềm miễn phí: bạn có thể phân phối lại và/hoặc sửa đổi
 *  nó theo các điều khoản của Giấy phép Công cộng GNU (GNU General Public License)
 *  như được công bố bởi Free Software Foundation, phiên bản 3 của Giấy phép,
 *  hoặc (tùy chọn) bất kỳ phiên bản nào mới hơn.
 *
 *  algs4.jar được phân phối với hy vọng rằng nó sẽ hữu ích,
 *  nhưng KHÔNG CÓ BẤT KỲ BẢO HÀNH NÀO; ngay cả bảo hành ngầm về KHẢ NĂNG BÁN ĐƯỢC
 *  hoặc PHÙ HỢP VỚI MỤC ĐÍCH CỤ THỂ. Xem Giấy phép Công cộng GNU
 *  để biết thêm chi tiết.
 *
 *  Bạn nên nhận được một bản sao của Giấy phép Công cộng GNU
 *  cùng với algs4.jar. Nếu không, xem http://www.gnu.org/licenses.
 ******************************************************************************/