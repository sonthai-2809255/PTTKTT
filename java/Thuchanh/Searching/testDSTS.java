
public class testDSTS {
    testDSTS(){}
    public static void main(String[] args) {
         In in= new In("thisinh.csv");
         try{
            DSTS ds= new DSTS(in);
            
            for(Thisinh ts : ds.getdsts().keys()){
             StdOut.print(ts);
             ST<String, Double> scores = ds.getdsts().get(ts);
             for (String mon : scores.keys()){
                 StdOut.print(" "+mon +":" +scores.get(mon) + " " );
             }
             StdOut.println();
         }
         }catch(Exception e){
             StdOut.println(e.getMessage());
         }
    }
}
