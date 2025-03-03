import java.io.*;
/******************************************************************************
 *  Biên dịch:  javac TopR.java
 *  Thực thi:   java TopR r < input.txt
 *  Phụ thuộc:  MinPQ.java Transaction.java StdIn.java StdOut.java
 *  Tệp dữ liệu: https://algs4.cs.princeton.edu/24pq/tinyBatch.txt
 * 
 *  Cho một số nguyên m từ dòng lệnh và một luồng đầu vào trong đó
 *  mỗi dòng chứa một chuỗi và một giá trị kiểu long, chương trình khách MinPQ này
 *  sẽ in ra m dòng có số lớn nhất.
 * 
 *  % java TopR 5 < listStudent.txt 
 *  buiminh        thuong  14/6/2005    33.34
    hoangduc        trong  26/3/2005    30.43
    trinhduc        quang  11/3/2005    29.73
    dangquang         son  28/9/2005    29.40
    dovan              an 12/12/2004    28.90  
 *
 ******************************************************************************/

 

/**
 *  Lớp {@code TopR} cung cấp một chương trình khách đọc một dãy sinh viên
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
public class TopR {   

    // Lớp này không nên được khởi tạo.
    private TopR() { }

    /**
     *  Đọc một dãy giao dịch từ đầu vào chuẩn; lấy một số nguyên m từ dòng lệnh;
     *  in ra đầu ra chuẩn m giao dịch lớn nhất theo thứ tự giảm dần.
     *
     * @param args đối số dòng lệnh
     */
    public static void main(String[] args) throws IOException{
        System.setIn(new FileInputStream(new File("listStudent.txt")));
        int r = 5;
        MinPQ<Student> pq = new MinPQ<Student>(r+1);

        while (StdIn.hasNextLine()) {
            // Tạo một mục nhập từ dòng tiếp theo và đặt vào PQ.
            String line = StdIn.readLine();
            Student student = new Student(line);
            pq.insert(student); 

            // Xóa phần tử nhỏ nhất nếu có nhiều hơn m+1 mục nhập trong PQ
            if (pq.size() > r) 
                pq.delMin();
        }   // m mục nhập lớn nhất nằm trong PQ

        // In các mục nhập trong PQ theo thứ tự ngược lại
        Stack<Student> stack = new Stack<Student>();
        for (Student c : pq)
            stack.push(c);
        for (Student c : stack)
            StdOut.println(c);
    } 
} 


/******************************************************************************
 *  Bản quyền 2002-2016, Robert Sedgewick và Kevin Wayne.
 *
 *  Tệp này là một phần của algs4.jar, đi kèm với sách giáo khoa
 *
 *      Algorithms, 4th edition của Robert Sedgewick và Kevin Wayne,
 *      Addison-Wesley Professional, 2011, ISBN 0-321-57351-X.
 *      http://algs4.cs.princeton.edu
 *
 *
 *  algs4.jar là phần mềm tự do: bạn có thể phân phối lại hoặc chỉnh sửa
 *  theo các điều khoản của Giấy phép Công cộng GNU do
 *  Quỹ Phần mềm Tự do xuất bản, phiên bản 3 của Giấy phép hoặc
 *  bất kỳ phiên bản nào mới hơn.
 *
 *  algs4.jar được phân phối với hy vọng rằng nó sẽ hữu ích,
 *  nhưng KHÔNG CÓ BẢO ĐẢM nào, thậm chí không có bảo đảm NGẦM ĐỊNH về
 *  KHẢ NĂNG THƯƠNG MẠI hoặc PHÙ HỢP VỚI MỘT MỤC ĐÍCH CỤ THỂ. Xem
 *  Giấy phép Công cộng GNU để biết thêm chi tiết.
 *
 *  Bạn nên đã nhận được một bản sao của Giấy phép Công cộng GNU
 *  cùng với algs4.jar. Nếu không, hãy xem http://www.gnu.org/licenses.
 ******************************************************************************/
