
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
    private Queue<Monhoc> dsmon;
    
    /**
     * Constructor for objects of class TKHK
     */
    public TKHK(){
        dssv = new ST<String,Student>();
        dsmon = new Queue<Monhoc>();
    }
    
    public TKHK(In in){
         dssv = new ST<String,Student>();
        dsmon = new Queue<Monhoc>();
        while(in.hasNextLine()){
            String line = in.readLine();
            String[] parts = line.split(",");
            String key = parts[0];
            Student val = new Student(line);
            dssv.put(key,val);
        }
     }
    
    public void readfileRate(String filename){
        dssv = update(filename);
    }
    
    private ST update(String filename){
        
        String [] a= filename.split("\\.");
        String token= a[0];
        String [] b= token.split("_");
        String tenmon= b[0];
        int tinchi= Integer.parseInt(b[1]);
        int kythu= Integer.parseInt(b[2]);
        // tạo đối tượng môn học
        Monhoc monhoc= new Monhoc(tenmon,tinchi,kythu);
        dsmon.enqueue(monhoc);
    
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
        return dssv;
    }
    
    }
