public class BellmanFordAllPair
{
    BellmanFord_T [] bellmanfordSP_onedestination;

    public BellmanFordAllPair(){
        
    }
    
    /** giải quyết vấn đề bằng một mảng các đích từ bài toán BellmanFordSPToOneDestination*/
    public  BellmanFordAllPair (EdgeWeightedDigraph_T G)
    {
        bellmanfordSP_onedestination = new BellmanFord_T[G.V()]; 
        
        for(int s = 0; s < G.V(); s++)
        {
            bellmanfordSP_onedestination[s] = new BellmanFord_T(G, s);
        }
            
    }
    /**hiển thị kết quả bài toán
     đưa ra danh sách moij đỉnh đến mọi đích hoặc chu trình âm nếu có*/
    public void display(){
        for(int s=0 ; s < bellmanfordSP_onedestination.length; s++){
            BellmanFord_T sp = bellmanfordSP_onedestination[s];
            if (sp.hasNegativeCycle()) {
            StdOut.printf("chu trình âm: \n");
            for (DirectedEdge e : sp.negativeCycle())
                StdOut.println(e);
        }
        
        // in các đường đi ngắn nhất
        else {
            for (int v = 0; v < bellmanfordSP_onedestination.length; v++) {
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
    
    /** kiểm thử*/ 
    public static void main(String args[]){
        In in = new In("tinyEWD.txt");
        EdgeWeightedDigraph_T G= new EdgeWeightedDigraph_T(in);
        BellmanFordAllPair bfa = new BellmanFordAllPair(G);
        bfa.display();
    }
    
}