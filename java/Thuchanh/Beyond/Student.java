
import java.util.*;
import java.io.*;
/**
 * Write a description of class Student here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Student implements Comparable<Student> {
    // instance variables - replace the example below with your own
    private   String maSv;
    private   String hodem;
    private   String ten;
    private   String que;
 

    /**
     * Constructor for objects of class Student
     */
    public Student(String maSv, String hodem, String ten,String que)
    {
       this.maSv=maSv;
       this.hodem=hodem;
       this.ten=ten;
       this.que=que;
       
    }
    
    /** khởi một student với tham số đầu vào là một String*/
    public Student(String student) {
       String a[]= student.split("\\s+");
       maSv    =a[0];
       hodem   =a[1];
       ten     =a[2];
       que     =a[3];
    }
    
    public String maSv(){
        return maSv;
    }
    
    public String hodem(){
        return hodem;
    }
    
    public String ten(){
        return ten;
    }
    
  
    public String que(){
        return que;
    }
    
    
    /** tra ve chuoi bieu dien thong tin sinh vien*/
    @Override
    public String toString(){
        
     return String.format("%-10s | %10s %10s | %10s   ",maSv,hodem,ten,que);
        
    }
    
    /**          
     * so sanh hai sinh vien theo maSV
     */
    public static class maSvOrder implements Comparator<Student>{
        public int compare(Student v, Student w) {
            return v.maSv.compareTo(w.maSv);        }
    }   

    /** 
     * so sanh hai sinh vien theo hodem
     */
    public static class hodemOrder implements Comparator<Student>{
        public int compare(Student v, Student w) {
            return v.hodem.compareTo(w.hodem);        }
    }

    /** 
     * so sanh hai sinh vien theo ten
     */
    public static class tenOrder implements Comparator<Student>{
        public int compare(Student v, Student w) {
            return v.ten.compareTo(w.ten);        }
    }

    /*
     * so sanh hai sinh vien theo ngaysinh
     *
    public static class ngaysinhOrder implements Comparator<Student>{
        public int compare(Student v, Student w) {
            return v.ngaysinh.compareTo(w.ngaysinh);        }
    }
    */
   
    /** 
     * so sanh hai sinh vien theo que
     */
    public static class queOrder implements Comparator<Student>{
        public int compare(Student v, Student w) {
            return v.que.compareTo(w.que);        }
    }
    
    //
    public int compareTo(Student that) {
        if(this.ten == that.ten) return this.hodem.compareTo(that.hodem);
        return this.ten.compareTo(that.ten);
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
       Student that = (Student) other;
       return (this.maSv.equals(that.maSv)) 
               && (this.hodem.equals(that.hodem))
               && (this.ten.equals(that.ten))
               && (this.que.equals( that.que)) ;
   }
   
   
   
   /**
    * Trả về mã băm của sinh viên này.
    *
    * @return mã băm của sinh viên này
    */
   public int hashCode() {
       int hash = 1;
       hash = 31 * hash + maSv.hashCode();
       hash = 31 * hash + hodem.hashCode();
       hash = 31 * hash + ten.hashCode();
       hash = 31 * hash + que.hashCode();
       return hash;
       // return Objects.hash(maSv,hodem, ten, que);
   }
   
  
    
  public static void main(String[] args) throws FileNotFoundException {
     
    System.setIn(new FileInputStream(new File("students.txt")));
    List<Student> a = new ArrayList<>();
    
    while (!StdIn.isEmpty()){
        String line = StdIn.readLine();
        a.add(new Student(line));
    }
    
    StdOut.println("chưa săp  xếp:");
    Collections.sort(a);
    for(Student c: a) {
        StdOut.println(c);
    }
    
    StdOut.println("theo maSV :");
    Collections.sort(a, new Student.maSvOrder());
    for(Student c: a) {
        StdOut.println(c);
    }
    
    StdOut.println("theo ten :");
    Collections.sort(a, new Student.tenOrder());
    for(Student c: a) {
        StdOut.println(c);
    }
    
}
    
    
}
