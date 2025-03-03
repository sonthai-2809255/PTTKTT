 
import java.util.*;
import java.io.*;
/**
 * Write a description of class Thisinh here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Thisinh implements Comparable<Thisinh> {
    // instance variables - replace the example below with your own
    private final String hodem;
    private final String ten;
    private final VnDate ngaysinh;
    private final String que;
    private final double diemTb;

    /**
     * Constructor for objects of class Thisinh
     */
    public Thisinh( String hodem, String ten,String ngaysinh,String que, String diemTb)
    {
       this.hodem=hodem;
       this.ten=ten;
       this.ngaysinh= new VnDate(ngaysinh);
       this.que=que;
       this.diemTb=Double.parseDouble(diemTb);
    }
    
    /** khởi một student với tham số đầu vào là một String*/
    public Thisinh(String student) {
        
       String a[]= student.split(",");
       
       if (a.length < 5) {
        throw new IllegalArgumentException("Dữ liệu không hợp lệ: " + student);
    }
       hodem   =a[0];
       ten     =a[1];
       ngaysinh=new VnDate(a[2]);
       que     =a[3];
       diemTb  =Double.parseDouble(a[4]);
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
     * so sanh hai sinh vien theo hodem
     */
    public static class hodemOrder implements Comparator<Thisinh>{
        public int compare(Thisinh v, Thisinh w) {
            return v.hodem.compareTo(w.hodem);        }
    }

    /** 
     * so sanh hai sinh vien theo ten
     */
    public static class tenOrder implements Comparator<Thisinh>{
        public int compare(Thisinh v, Thisinh w) {
            return v.ten.compareTo(w.ten);        }
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
    a[0] =new Thisinh("dangquang,son,28/09/2005,nghean,29.0");
    a[1] =new Thisinh("hoangduc,trong,26/3/2005,thainuyen,30.43");
    a[2] = new Thisinh("buiminh,thuong,14/6/2005,hoabinh,33.34");
        a[3] = new Thisinh("dovan,an,12/2/2004,thaibinh,28.90");
    
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
    String s=new String("dangquang,son,28/09/2005,nghean,29.0");
    String[] b = s.split(",");
    
    Thisinh c= new Thisinh(b[0],b[1],b[2],b[3],b[4]);
    StdOut.println(c);
    
}
}
