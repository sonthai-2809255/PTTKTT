public class DSTS{
    private SeparateChainingHashST<Thisinh,ST<String,Double>> dsts;

    public SeparateChainingHashST<Thisinh,ST<String,Double>> getdsts(){
        return dsts;
    }
    
    public DSTS(In in){
        dsts= new SeparateChainingHashST<Thisinh, ST<String,Double>>();
        while(in.hasNextLine()){
            String line= in.readLine();
            String[] a = line.split(",");
            Thisinh ts = new Thisinh(a[0],a[1],a[2],a[3],a[4]);
            dsts.put(ts,new ST<String,Double>());
            dsts.get(ts).put("toan",Double.parseDouble(a[5]));
            dsts.get(ts).put("ly",Double.parseDouble(a[6]));
            dsts.get(ts).put("hoa",Double.parseDouble(a[7]));
        }
    }

    
}