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
    private ST<String,Student> dssv;
    List<Monhoc>[] monhk =(List<Monhoc> [])  new ArrayList[9];
    
    public static String DSSV = "student.csv";
    public static String CSDL = "tCsdl_03_04.csv";
    public static String JAVA = "tJava_03_04.csv";
    public static String TRR = "tTrr_03_03.csv";
    /**
     * Constructor for objects of class TKHK
     */
    public TKHK(){
        
        dssv = new ST<String,Student>();
        for(int i=1 ; i< monhk.length; i++)
        {
            monhk[i] = new ArrayList<>();
        }
        
        }
    
    public TKHK(In in){
         dssv = new ST<String,Student>();
        for(int i=1 ; i< monhk.length; i++)
        {
            monhk[i] = new ArrayList<>();
        }
        
        while(in.hasNextLine()){
            String line = in.readLine();
            String[] parts = line.split(",");
            String key = parts[0];
            Student val = new Student(line);
            dssv.put(key,val);
        }
     }
    
    
    
    private void updateRate(String filename){
        
        String [] a= filename.split("\\.");
        String token= a[0];
        String [] b= token.split("_");
        String tenmon= b[0];
        int tinchi= Integer.parseInt(b[1]);
        int kythu= Integer.parseInt(b[2]);
        // tạo đối tượng môn học
        Monhoc monhoc= new Monhoc(tenmon,tinchi,kythu);
        monhk[kythu].add(monhoc);
        
        In in = new In(filename);
        while(in.hasNextLine()){
            String line = in.readLine();
            String[] parts = line.split(",");
            // lấy masv từ phần tách
            String masv= parts[0];
            // chuyển điểm sang kiểu double
            Double diem= Double.parseDouble(parts[1]);
            //lấy sinh viên theo masv
            Student A = dssv.get(masv);
            //cập nhật bảng điểm
            A.bangdiem().put(monhoc,diem);
            //cập nhật sinh viên lại danh sách sv
            dssv.put(masv,A);
        }

    }
    
    public void tkhk(){
        //StdOut.printf("MSV         ");
        //    for(int i=1 ;i< monhk.length;i++ ){
        //       if (monhk[i].size() != 0){
        //           StdOut.printf("  "+ "kỳ"+ i +"   ");
        //        }
                
        //   }
        StdOut.println("");

        for( String msv : dssv.keys()){
            //
            Student student = dssv.get(msv);
            //
            StdOut.printf("%s    ", student.maSv());
            for(int i=1 ;i< monhk.length;i++ ){
                
                if (monhk[i].size() != 0){
                    double diem = student.Tbhocky(i);
                    //
                    StdOut.printf("%4.4f "+ "kỳ"+ i +xetthidua(diem)+"            ", diem);
                }
                
            }
            StdOut.println("");
            //
        }
    }
    
    private String xetthidua(double diem){
        if(diem>= 3.6) return "xuat_sac";
        if(diem< 3.6 && diem>= 3.2) return "gioi";
        if(diem>= 2.5 && diem<3.2) return "kha";
        if(diem>= 2.0 && diem< 2.5) return "trungbinh";
        return "yeu";
    }
    
    public void tbhk(String msv,int kythu){
        Student student =(Student) dssv.get(msv);
        StdOut.printf("%10s %10s %10s %10.4f" + "ky"+kythu, student.maSv(), student.hodem(),student.ten(),student.Tbhocky(kythu));
        StdOut.println("");
    }
    
    public static void main(String args[]){
        TKHK tongket= new TKHK(new In(DSSV));
        
        
        //nhập điểm môn
        tongket.updateRate(TRR);
        tongket.updateRate(CSDL);
        tongket.updateRate(JAVA);
        //
        tongket.tbhk("111111111",4);
        System.out.println("---------");
        tongket.tkhk();
        
    }
    
    }
