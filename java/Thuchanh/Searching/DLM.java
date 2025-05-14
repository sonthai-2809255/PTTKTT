import java.util.*;
import java.io.*;
import java.util.ArrayList;

public class DLM{
    private Monhoc monhoc;
    private ST<String,Student> dssv;
    
    public static String DSSV = "student.csv";
    public static String CSDL = "tCsdl_03_04.csv";
    public static String JAVA = "tJava_03_04.csv";
    public static String TRR = "tTrr_03_03.csv";

    // Constructer Danh Sách lớp Môn với đầu vào là một In 
    //cho Danh sách sinh viên
    public DLM(In in){
        dssv = new ST<String,Student>();
        
        while(in.hasNextLine()){
            String line = in.readLine();
            String[] parts = line.split(",");
            String key = parts[0];
            Student val = new Student(line);
            dssv.put(key,val);
        }
        
    }
    public ST getdssv(){
        return dssv;
    }
    
    public void updateRate(String fileMon){
        
        String [] a= fileMon.split("\\.");
        String token= a[0];
        String [] b= token.split("_");
        String tenmon= b[0];
        int tinchi= Integer.parseInt(b[1]);
        int kythu = Integer.parseInt(b[2]);
        // tạo đối tượng môn học
        monhoc = new Monhoc(tenmon,tinchi,kythu);
        
        In in = new In(fileMon);
        while(in.hasNextLine()){
            String line = in.readLine();
            String[] parts = line.split(",");
            // lấy masv từ phần tách
            String masv= parts[0];
            // chuyển điểm sang kiểu double
            Double diem= Double.parseDouble(parts[1]);
            dssv.get(masv).bangdiem().put(monhoc,diem);
            
        }

    }
    
    public void displaySortByDiem(){
        List<Student> liststudent = new ArrayList();
        for(String c: dssv.keys()){
            liststudent.add(dssv.get(c));
        }
        //sort
        Collections.sort(liststudent);
        
        for(Student student : liststudent){
             StdOut.println(student+"\t");
        }
    }
    
    public void display(){
        for(String c : dssv.keys()){
            //
            Student student = dssv.get(c);
            StdOut.print(student+"\t");
            //
            ST<Monhoc,Double> bangdiem= student.bangdiem();
            //duyệt từng môn trong bảng điểm
            for(Monhoc a: bangdiem.keys()){
                StdOut.printf("%4.2f "+(a.tenmon()+ "   ") ,bangdiem.get(a));
            }
            StdOut.println();
        }
    }
    
    public double TBC(){
        double sum=0.0;
        for(String c: dssv.keys())
        {
            sum+= dssv.get(c).diemTb();
        }
        return (double) sum/dssv.size();
    }
    
    
    public static void main(String args[]){
        //tạo ds
        DLM dlm= new DLM(new In(DSSV));
        
        //sắp xếp theo điểm tăng dần
        dlm.displaySortByDiem();
        //tính điểm trung bình chung cả lớp
        System.out.println("---------");
        System.out.println("điểm trung bình chung: "+ dlm.TBC());
        
        //nhập điểm môn
        dlm.updateRate(TRR);
        dlm.updateRate(CSDL);
        dlm.updateRate(JAVA);
        
        System.out.println("---------");
        dlm.display();
                
        }
        
    
}


