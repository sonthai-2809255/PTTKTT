import java.io.*;
/******************************************************************************
 *  Biên dịch:  javac TopStudent.java
 *  Thực thi:   java TopStudent r < input.txt
 *  Phụ thuộc:  MinPQ.java Transaction.java StdIn.java StdOut.java
 *  Tệp dữ liệu: https://algs4.cs.princeton.edu/24pq/tinyBatch.txt
 * 
 *  Cho một số nguyên m từ dòng lệnh và một luồng đầu vào trong đó
 *  mỗi dòng chứa một chuỗi và một giá trị kiểu long, chương trình khách MinPQ này
 *  sẽ in ra m dòng có số lớn nhất.
 * 
 *  % java TopStudent 5 < listStudent.txt 
 *  buiminh        thuong  14/6/2005    33.34
    hoangduc        trong  26/3/2005    30.43
    trinhduc        quang  11/3/2005    29.73
    dangquang         son  28/9/2005    29.40
    dovan              an 12/12/2004    28.90  
 *
 ******************************************************************************/

 

/**
 *  Lớp {@code TopStudent} cung cấp một chương trình khách đọc một dãy sinh viên
 *  từ đầu vào chuẩn và in ra <em>r</em> điểm tb lớn nhất ra đầu ra chuẩn.
 *  Triển khai này sử dụng một {@link MinPQ} có kích thước tối đa là
 *  <em>r</em> + 1 để xác định <em>r</em> điểm tb lớn nhất
 *  và một {@link Stack} để xuất chúng theo thứ tự thích hợp.
 *  <p>
 *  Để biết thêm tài liệu, hãy xem <a href="https://algs4.cs.princeton.edu/24pq">Mục 2.4</a>
 *  của <i>Algorithms, 4th Edition</i> của Robert Sedgewick và Kevin Wayne.
 *
 *  @author Robert Sedgewick
 *  @author Kevin Wayne
 */
public class TopStudent {   

    private Stack<Student> TopStudent;
    
    public TopStudent(){ 
        this.TopStudent = new Stack<Student>();
    }
    
    /** solve init list */
    public void solve(String filename,int sizetop) throws FileNotFoundException {
        System.setIn(new FileInputStream(new File(filename)));
        MinPQ<Student> pq = new MinPQ<Student>(sizetop+1);

        while (StdIn.hasNextLine()) {
            // Tạo một mục nhập từ dòng tiếp theo và đặt vào PQ.
            String line = StdIn.readLine();
            Student student = new Student(line);
            pq.insert(student); 

            // Xóa phần tử nhỏ nhất nếu có nhiều hơn m+1 mục nhập trong PQ
            if (pq.size() > sizetop) 
                pq.delMin();
        }   // m mục nhập lớn nhất nằm trong PQ

        // In các mục nhập trong PQ theo thứ tự ngược lại
        Stack<Student> stack = new Stack<Student>();
        for (Student c : pq)
            this.TopStudent.push(c);
        
    }
    
    /** Display */ 
    public void display(){
        StdOut.println("Top " + this.TopStudent.size() + " Student");
        for(Student c : TopStudent){
            StdOut.println(c);
        }
    }
    
    /**
     *  Test
     */
    public static void main(String[] args) throws IOException{
        TopStudent top= new TopStudent();
        top.solve("listStudent.txt",5);
        top.display();
        
    } 
} 


