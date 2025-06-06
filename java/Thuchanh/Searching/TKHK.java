import java.util.*;

/**
 * Write a description of class TKHK here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class TKHK 
{
    // instance variables - replace the example below with your own
    private DLM dssvlop;
    List<Monhoc>[] monhk =(List<Monhoc> [])  new ArrayList[9];
    
    public static String DSSV = "student.csv";
    public static String CSDL = "Csdl_03_04.csv";
    public static String JAVA = "Java_03_04.csv";
    public static String TRR = "Trr_03_03.csv";
    public TKHK(){
        dssvlop = new DLM();
        for(int i=1 ; i< monhk.length; i++)
        {
            monhk[i] = new ArrayList<>();
        }
    }
    
    public TKHK(String[] args){
        In in = new In(args[0]);
        dssvlop  =  new DLM(in);
        for(int i=1 ; i< monhk.length; i++)
        {
            monhk[i] = new ArrayList<>();
        }
        /// nhập điểm lớp môn
        for(int i =1 ;i< args.length; i++){
            inputRate(args[i]);
        }

     }
     /// trả về danh sách sinh viên của lớp
    public DLM getdssvlop(){ return dssvlop;}

    /// đọc file điểm môn của lớp
    private void inputRate(String filename){
        
        String [] a= filename.split("\\.");
        String token= a[0];
        String [] b= token.split("_");
        String tenmon= b[0];
        int tinchi= Integer.parseInt(b[1]);
        int kythu= Integer.parseInt(b[2]);
        // tạo đối tượng môn học
        Monhoc monhoc= new Monhoc(tenmon,tinchi,kythu);
        monhk[kythu].add(monhoc);

        dssvlop.inputRate(filename);
    }
    /// tổng kết học kỳ lớp
    public void tkhklop(){
        StdOut.println("");
        for( String msv : dssvlop.keys()){
            //
            Student student = dssvlop.getStudent(msv);
            //
            StdOut.printf("%s    ", student.maSv());
            for(int i=1 ;i< monhk.length;i++ ){
                if (monhk[i].size() != 0){
                    double diem = student.Tbhocky(i);
                    //
                    StdOut.printf("%4.4f "+ "kỳ"+ i +"            ", diem);
                }
            }
            StdOut.println("");
            //
        }
    }
    
    public void tkhklop(String kythu){
        int i = Integer.parseInt(kythu);
        if(monhk[i] == null) return;
        for(String msv : dssvlop.keys()){
            Student sv = dssvlop.getStudent(msv);
            StdOut.println(sv + " diem:"+ sv.Tbhocky(i));
        }
    }
    
    public void tbhk(String msv,int kythu){
        Student student =(Student) dssvlop.getStudent(msv);
        StdOut.printf("%10s %10s %10s %10.4f" + "ky"+kythu, student.maSv(), student.hodem(),student.ten(),student.Tbhocky(kythu));
        StdOut.println("");
    }
    
    public static void main(String args[]){
        TKHK tongket= new TKHK(args);
        tongket.tkhklop("4");
        
    }
    
    
    private String xetthidua(double diem){
        if(diem>= 3.6) return "xuat_sac";
        if(diem< 3.6 && diem>= 3.2) return "gioi";
        if(diem>= 2.5 && diem<3.2) return "kha";
        if(diem>= 2.0 && diem< 2.5) return "trungbinh";
        return "yeu";
    }
    public void Tbhk(){
        for(int i=1 ; i< monhk.length; i++){
            if(monhk [i] != null){
                for(Monhoc mon: monhk[i]){
                    double sum = 0.0;
                    for (String msv : dssvlop.keys()){
                        sum += (Double)(dssvlop.getStudent(msv).bangdiem().get(mon));
                    }
                    StdOut.println(mon.tenmon() + "    " + (Double)sum / dssvlop.size());
                }
            }
        }
    }
    }
