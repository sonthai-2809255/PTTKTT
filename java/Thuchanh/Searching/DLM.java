import java.util.*;
import java.io.*;

public class DLM{
    private Monhoc monhoc;
    private ST<String,Student> dssv;
    
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
        dssv = update(fileMon);
    }
    
    private ST update(String fileMon){
        
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
            //lấy sinh viên theo masv
            Student A = dssv.get(masv);
            //cập nhật bảng điểm
            A.bangdiem().put(monhoc,diem);
            //cập nhật sinh viên lại danh sách sv
            dssv.put(masv,A);
        }
        return dssv;
    }
    
    public static void main(String args[]){
        
        DLM dlm= new DLM(new In(args[0]));
    
        dlm.updateRate(args[1]);
        dlm.updateRate(args[2]);
        dlm.updateRate(args[3]);
        
        ST<String,Student> dssv = dlm.getdssv();
        for(String c : dssv){
            Student student = dssv.get(c);
            StdOut.print(student+" ");
            ST<Monhoc,Double> bangdiem= student.bangdiem();
            for(Monhoc a: bangdiem){
                StdOut.printf("%4.2f",student.bangdiem().get(a));
            }
            StdOut.println();
        }
        
        
}
}


