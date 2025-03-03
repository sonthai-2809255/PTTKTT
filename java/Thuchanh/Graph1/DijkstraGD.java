import java.util.*;
import java.io.*;

public class DijkstraGD{
    public DijkstraGD() {};
    
    public static void main(String args[]){
        //khởi tạo luồng đầu vào để truyền file
        In in = new In(args[0]);
        // khởi tạo đối tượng đầu vào 
        EdgeWeightedDigraph G = new EdgeWeightedDigraph(in);
        // truyền đích của mỗi cặp (nguồn,đích)
        int dich= Integer.parseInt(args[1]);
        // cho một đích và tìm đường đi tất cả các điểm đếm đích
        
        for(int i=0;i< G.V();i++){
           DijkstraSP sp = new DijkstraSP(G,i);
           if (sp.hasPathTo(dich)) {
                StdOut.printf("%d to %d (%.2f)  ", i, dich, sp.distTo(dich));
                for (DirectedEdge e : sp.pathTo(dich)) {
                    StdOut.print(e + "   ");
                }
               StdOut.println();
            }
            else {
                StdOut.printf("%d to %d      không có đường đi\n", i, dich);
            }
        }
    }
}