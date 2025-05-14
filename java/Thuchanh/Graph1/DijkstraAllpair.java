import java.io.*;
import java.util.*;

public class DijkstraAllpair{
    
   private DijkstraAllpair() { }
   
    public void find(EdgeWeightedDigraph G){
        int sodinh= G.V();
        for(int dich=1;dich<sodinh;dich++){
        DijkstraSP[] sp = new DijkstraSP[sodinh];
        for(int goc=0;  goc<sodinh; goc++){
            sp[goc]= new DijkstraSP(G, goc);
            if (sp[goc].hasPathTo(dich)) {
                StdOut.printf("%d to %d (%.2f)  ", goc, dich, sp[goc].distTo(dich));
                for (DirectedEdge e : sp[goc].pathTo(dich)) {
                    StdOut.print(e + "   ");
                }
               StdOut.println();
            }
            else {
                StdOut.printf("%d to %d         không có đường đi\n", goc, dich);
            }
        }
      }
    }
    
    public static void main(String[] args) {
        //khởi tạo luồng đầu vào để truyền file
        In in = new In("tinyEWD.txt");
        EdgeWeightedDigraph G = new EdgeWeightedDigraph(in);
       //tìm tất cả các đường đi ngắn nhất cho mỗi cặp (gốc, đích) có ở trong đồ thị
        DijkstraAllpair AP = new DijkstraAllpair();
        //
        AP.find(G);
    }
}

