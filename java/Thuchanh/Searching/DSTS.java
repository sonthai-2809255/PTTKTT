public class DSTS{
    private SeparateChainingHashST<Thisinh,ST<String,Double>> dsts;

    public SeparateChainingHashST<Thisinh,ST<String,Double>> getdsts(){
        return dsts;
    }
    
    public DSTS(In in) throws Exception {
        dsts= new SeparateChainingHashST<Thisinh, ST<String,Double>>();
        while(in.hasNextLine()){
            String line= in.readLine();
            String[] a = line.split(",");
            Thisinh ts = new Thisinh(a[0],a[1],a[2],a[3]);
            double []diem = { Double.parseDouble(a[4]),Double.parseDouble(a[5]),Double.parseDouble(a[6])};
            ts.setDTB(diem);
            dsts.put(ts,new ST<String,Double>());
            dsts.get(ts).put("toan",diem[0]);
            dsts.get(ts).put("ly",diem[1]);
            dsts.get(ts).put("hoa",diem[2]);
            

        }
    }

    
}