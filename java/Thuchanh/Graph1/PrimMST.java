import java.util.*;
import java.io.*;
public class PrimMST {
   
    private boolean[] mark;// đánh dấu các đỉnh đã có trong cây khung
    private Edge[] edgeto;//lưu các cạnh gần nhất nối vào đỉnh của cây khung
    private double[] distTo;//lưu trọng số của cạnh nhỏ nhất kết nối mỗi đỉnh của cây khung
    private IndexMinPQ<Double> pq;//hàng đợi ưu tiên theo chỉ mục để chọn cạnh có trọng số nhỏ nhất
    //private Stack<Edge> mst;// danh sách chứa các cạnh của cây khung nhỏ nhấtp
    private double weight;// tổng trọng số của cây khung nhỏ nhất
    /**
     *
     * @param  G đồ thị có trọng số cạnh
     * @param  s đỉnh nguồn
     * @throws IllegalArgumentException nếu trọng số cạnh là âm
     * @throws IllegalArgumentException trừ khi {@code 0 <= s < V}
     */
    public PrimMST(EdgeWeightedGraph G, int s) {
        for (Edge e : G.edges()) {
            if (e.weight() < 0)
                throw new IllegalArgumentException("cạnh " + e + " có trọng số âm");
        }
        edgeto = new Edge[G.V()];
        distTo = new double[G.V()];
        mark= new boolean[G.V()];
        weight= 0.0;
        validateVertex(s);

        for (int v = 0; v < G.V(); v++)
            distTo[v] = Double.POSITIVE_INFINITY;// khởi tạo trọng số của đỉnh nguồn(s) đến đỉnh khác đều là vô cùng lớn
        distTo[s] = 0.0;
        // khởi tạo đỉnh nguồn(đỉnh đầu tiên duyệt)
        pq = new IndexMinPQ<Double>(G.V());
        pq.insert(s, distTo[s]);
        
        // vong while chon phan tu min trong PQ.
         while (!pq.isEmpty()) {
            int v = pq.delMin(); // Lấy đỉnh có khoảng cách nhỏ nhất
            mark[v]= true;// đánh dấu đã thêm vào tập đỉnh có trong khung cây
            for (Edge e : G.adj(v)) //duyệt các cạnh nối v đến đỉnh khác
            {
            int w =e.other(v);//lấy đỉnh của được nối đến với đỉnh v bởi cạnh e
            if(!mark[w] && e.weight() < distTo[w])  //lựa chọn cạnh tốt nhất để cập nhật
                {
                    distTo[w]= e.weight();
                    edgeto[w]=e;// cập nhật cạnh của cây khung 
                    if(pq.contains(w)) pq.decreaseKey(w, distTo[w]);// cập nhật dữ liệu nếu đã có trong indexminPQ 
                    else pq.insert(w, distTo[w]);// thêm vào indexminPQ nếu chưa có
                }
             }   
            }

    
    }



    /**
     * Trả về một cây khung  .
     *
    
     * @throws IllegalArgumentException trừ khi {@code 0 <= v < V}
     */
    public Iterable<Edge> Mst() {
        
        Stack<Edge> mst = new Stack<Edge>();
        for (int i= 0;i< edgeto.length;i++) {
            if(edgeto[i]==null) continue;
            mst.push(edgeto[i]);
            this.weight+=edgeto[i].weight();
        }
        return mst;
    }

    private void validateVertex(int v) {
            int V = distTo.length;
            if (v < 0 || v >= V)
                throw new IllegalArgumentException("đỉnh " + v + " không nằm trong khoảng từ 0 đến " + (V-1));
        }
        // trả về tổng trọng số của cây khung
    public double weight(){
            return weight;
        }
 
   
        /**
         * Kiểm tra đơn vị kiểu dữ liệu {@code PrimMST}.
         *
         * @param args các đối số dòng lệnh
         */
        public static void main(String[] args) {
            // truyền luồng đầu vào: tên file args[0]="tinyEWDn.txt
            // args[0]="tinyEWDn.txt";
            In in = new In("tinyEWD.txt");
            EdgeWeightedGraph G = new EdgeWeightedGraph(in);
            
            // truyền đỉnh nguồn
            int s = Integer.parseInt("0");
    
            // tính toán đường đi ngắn nhất
            PrimMST pr = new PrimMST(G, s);
            //in ra các cạnh trong đồ thị đầu vào
            StdOut.println("Edge in Graph:");
            for(Edge e : G.edges()){
                StdOut.println(e);
            }
            //in ra các cạnh có trọng số và tổng trọng số của cây khung
             StdOut.println("Edge in MST:");
            for(Edge e : pr.Mst()){
                StdOut.println(e + " ");
                 }
            // in ra tổng trọng số của cây khung
            StdOut.printf("total weight = %.2f" , pr.weight());
        
    }
}