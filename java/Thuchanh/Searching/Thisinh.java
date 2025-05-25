 
import java.util.*;
import java.io.*;

public class Thisinh implements Comparable<Thisinh> {
    
    private final String hodem;
    private final String ten;
    private final VnDate ngaysinh;
    private final String que;
    private  double diemTb;

    /**
     * Constructor for objects of class Thisinh
     */
    public Thisinh( String hodem, String ten,String ngaysinh,String que)
    {
       this.hodem=hodem;
       this.ten=ten;
       this.ngaysinh= new VnDate(ngaysinh);
       this.que=que;
       this.diemTb=0.0;
    }
    
    /** khởi một student với tham số đầu vào là một String*/
    public Thisinh(String student) {
        
       String a[]= student.split(",");
       
       if (a.length < 4) {
        throw new IllegalArgumentException("Dữ liệu không hợp lệ: " + student);
       }
       hodem   =a[0];
       ten     =a[1];
       ngaysinh=new VnDate(a[2]);
       que     =a[3];
       diemTb  =0.0;
    }
    
    public String hodem(){
        return hodem;
    }
    public String ten(){
        return ten;
    }
    public VnDate ngaysinh(){
        return ngaysinh;
    }
    public String que(){
        return que;
    }
     public double diemTb(){
         return diemTb;
    }

    /** tra ve chuoi bieu dien thong tin sinh vien*/
    @Override
    public String toString(){
        
     return String.format("%-10s %10s %10s %10s %8.2f",hodem,ten,ngaysinh,que,diemTb);
        
    }
    
    /** 
     * so sanh hai sinh vien theo ten
     */
    public static class tenOrder implements Comparator<Thisinh>{
        public int compare(Thisinh v, Thisinh w) {
            if(v.ten != w.ten)  return v.ten.compareTo(w.ten);  
            return v.hodem.compareTo(w.hodem);
        }
    }
    /** 
     * so sanh hai sinh vien theo ngaysinh
     */
    public static class ngaysinhOrder implements Comparator<Thisinh>{
        public int compare(Thisinh v, Thisinh w) {
            return v.ngaysinh.compareTo(w.ngaysinh);        }
    }
    /** 
     * so sanh hai sinh vien theo que
     */
    public static class queOrder implements Comparator<Thisinh>{
        public int compare(Thisinh v, Thisinh w) {
            return v.que.compareTo(w.que);        }
    }
     /** 
     * so sanh hai sinh vien theo diemTb
     */
    public static class diemOrder implements Comparator<Thisinh>{
        public int compare(Thisinh v, Thisinh w) {
            return Double.compare(v.diemTb,w.diemTb);        }
    }

    public int compareTo(Thisinh that){
        return Double.compare(this.diemTb,that.diemTb);
    }
    
    public void setDTB(double [] diem) throws Exception {
        double sum=0.0;
        int n = diem.length;
        if(n==0) throw new Exception("khong ton tai diem");
        for(int i =0; i< n ; i++){
            sum+= diem[i];
        }
        this.diemTb = (double)sum/n;
        
    }
    
    /**
    * So sánh sinh viên này với một đối tượng khác.
    *
    * @param other sinh viên khác
    * @return true nếu sinh viên này bằng {@code other}; false nếu ngược lại
    */
   @Override
   public boolean equals(Object other) {
       if (other == this)
           return true;
       if (other == null)
           return false;
       if (other.getClass() != this.getClass())
           return false;
       Thisinh that = (Thisinh) other;
       return (this.diemTb == that.diemTb) 
               && (this.hodem.equals(that.hodem))
               && (this.ten.equals(that.ten))
               && (this.ngaysinh.equals(that.ngaysinh))
               && (this.que.equals( that.que)) ;
   }
   
   /**
    * Trả về mã băm của sinh viên này.
    *
    * @return mã băm của sinh viên này
    */
   public int hashCode() {
       int hash = 1;
       hash = 31 * hash + hodem.hashCode();
       hash = 31 * hash + ten.hashCode();
       hash = 31 * hash + ngaysinh.hashCode();
       hash = 31 * hash + que.hashCode();
       hash = 31 * hash + ((Double) diemTb).hashCode();
       return hash;
       // return Objects.hash(maSv,hodem, ten, ngaysinh, diemTb);
   }
   

   

  public static void main(String[] args) {
    Thisinh a[] = new Thisinh[4];
    a[0] =new Thisinh("dangquang,son,28/09/2005,nghean");
    a[1] =new Thisinh("hoangduc,trong,26/3/2005,thainuyen");
    a[2] = new Thisinh("buiminh,thuong,14/6/2005,hoabinh");
    a[3] = new Thisinh("dovan,an,12/2/2004,thaibinh");
    
    StdOut.println("chưa săp  xếp:");
    Arrays.sort(a);
    for(Thisinh c: a) {
        StdOut.println(c);
    }
    
    StdOut.println("theo ten :");
    Arrays.sort(a, new Thisinh.tenOrder());
    for(Thisinh c: a) {
        StdOut.println(c);
    }
    String s=new String("dangquang,son,28/09/2005,nghean");
    String[] b = s.split(",");
    
    Thisinh c= new Thisinh(b[0],b[1],b[2],b[3]);
    StdOut.println(c);
    
}
}
