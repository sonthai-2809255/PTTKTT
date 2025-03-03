import java.io.*;
import java.util.*;

public class DijkstraAllpair{
    
   private DijkstraAllpair() { }
    
    public static void main(String[] args) {
        //khởi tạo luồng đầu vào để truyền file
        In in = new In(args[0]);
        EdgeWeightedDigraph G = new EdgeWeightedDigraph(in);
       //tìm tất cả các đường đi ngắn nhất cho mỗi cặp (gốc, đích) có ở trong đồ thị
        int sodinh= G.V();
        for(int dich=0;dich<sodinh;dich++){
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
}

