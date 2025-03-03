import java.sql.Date;
import java.util.Arrays;
import java.util.Comparator;

/**
 * Lớp {@code Student} là một kiểu dữ liệu bất biến để mô tả thông tin sinh viên
 * 
 * <p>
 * Để biết thêm tài liệu, xem thêm tại
 * <a href="https://algs4.cs.princeton.edu/12oop">Section 1.2</a> của sách
 * <i>Algorithms, 4th Edition</i> bởi Robert Sedgewick và Kevin Wayne.
 *
 * @author Robert Sedgewick
 * @author Kevin Wayne
 */
public class Student implements Comparable<Student> {
    private final String hodem; // họ và tên đệm
    private final String ten; // tên
    private final VnDate ngaysinh; // ngày sinh
    private final double diemTb; // Điểm trung bình

    /**
     * Khởi tạo một sinh viên mới với các tham số được cung cấp.
     *
     * @throws IllegalArgumentException nếu {@code diemtb}
     *                                  là {@code Double.NaN},
     *                                  {@code Double.POSITIVE_INFINITY},
     *                                  hoặc {@code Double.NEGATIVE_INFINITY}
     */
    public Student(String hodem, String ten, VnDate ngaysinh, double diemTb) {
        if (Double.isNaN(diemTb) || Double.isInfinite(diemTb))
            throw new IllegalArgumentException("Diểm trung bình không thể là NaN hoặc vô hạn");
        this.hodem = hodem;
        this.ten = ten;
        this.ngaysinh = ngaysinh;
        this.diemTb = diemTb;
    }

    /**
     * Khởi tạo một Sinh viên mới bằng cách phân tích một chuỗi theo định dạng HODEM
     * TEN NGAYSINH DIEMTB.
     *
     * @param student chuỗi cần phân tích
     * @throws IllegalArgumentException nếu {@code amount}
     *                                  là {@code Double.NaN},
     *                                  {@code Double.POSITIVE_INFINITY},
     *                                  hoặc {@code Double.NEGATIVE_INFINITY}
     */
    public Student(String student) {
        String[] a = student.split("\\s+");
        hodem = a[0] + a[1];
        ten = a[2];
        ngaysinh = new VnDate(a[3]);
        diemTb = Double.parseDouble(a[4]);
        if (Double.isNaN(diemTb) || Double.isInfinite(diemTb))
            throw new IllegalArgumentException("Điểm trung bình không thể là NaN hoặc vô hạn");
    }

    /**
     * Trả về họ đệm của sinh viên .
     *
     * @return họ đệm của sinh viên.
     */
    public String hodem() {
        return hodem;
    }

    /* trả về tên của sinh viên */
    public String ten() {
        return ten;
    }

    /**
     * Trả về ngày tháng năm sinh của sinh viên
     *
     * @return ngày tháng năm sinh của sinh viên
     */

    public VnDate ngaysinh() {
        return ngaysinh;
    }

    /**
     * trả về điểm trung bình của sinh viên
     *
     * @return điểm trung bình của sinh viên
     */
    public double diemTb() {
        return diemTb;
    }

    /**
     * Trả về chuỗi biểu diễn thông tin sinh vên.
     *
     * @return chuỗi biểu diễn thông tin sinh viên
     */
    @Override
    public String toString() {
        return String.format("%-10s %10s %10s %8.2f", hodem, ten, ngaysinh, diemTb);
    }

    /**
     * So sánh hai sinh viên theo diem trung bình.
     *
     * @param that sinh viên khác
     * @return { một số nguyên âm, số 0, hoặc một số nguyên dương},
     *         tùy thuộc vào việc điểm của sinh viên này { nhỏ hơn, bằng, hay lớn
     *         hơn }
     *         điểm của giao dịch khác
     */
    public int compareTo(Student that) {
        return Double.compare(this.diemTb, that.diemTb);
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
        return (this.diemTb == that.diemTb) && (this.hodem.equals(that.hodem))
                && (this.ten.equals(that.ten))
                && (this.ngaysinh.equals(that.ngaysinh));
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
        hash = 31 * hash + ((Double) diemTb).hashCode();
        return hash;
        // return Objects.hash(hodem, ten, ngaysinh, diemTb);
    }

    /**
     * So sánh hai sinh viên theo tên.
     */
    public static class tenOrder implements Comparator<Student> {

        @Override
        public int compare(Student v, Student w) {
            return v.ten.compareTo(w.ten);
        }
    }

    /**
     * so sánh hai sinh viên theo họ đệm
     */
    public static class hodemOrder implements Comparator<Student> {
        @Override
        public int compare(Student v, Student w) {
            return v.hodem.compareTo(w.hodem);
        }
    }

    /**
     * So sánh hai sinh viên theo ngày sinh.
     */
    public static class ngaysinhOrder implements Comparator<Student> {

        @Override
        public int compare(Student v, Student w) {
            return v.ngaysinh.compareTo(w.ngaysinh);
        }
    }

    /**
     * So sánh hai sinh viên theo điểm trung bình.
     */
    public static class diemTbOrder implements Comparator<Student> {

        @Override
        public int compare(Student v, Student w) {
            return Double.compare(v.diemTb, w.diemTb);
        }
    }

    /**
     * Kiểm thử đơn vị cho kiểu dữ liệu {@code Student}.
     *
     * @param args tham số dòng lệnh
     */
    public static void main(String[] args) {
        Student[] a = new Student[4];
        a[0] = new Student("dang quang son   28/9/2005  29.4");
        a[1] = new Student("hoang duc trong  26/3/2005  30.43");
        a[2] = new Student("bui minh thuong  14/6/2005  33.34");
        a[3] = new Student("do van an        12/2/2004  28.90");

        StdOut.println("Chưa sắp xếp");
        for (int i = 0; i < a.length; i++)
            StdOut.println(a[i]);
        StdOut.println();

        StdOut.println("Sắp xếp theo CompareTo");
        Arrays.sort(a);
        for (int i = 0; i < a.length; i++)
            StdOut.println(a[i]);
        StdOut.println();

        StdOut.println("Sắp xếp theo ngày sinh");
        Arrays.sort(a, new Student.ngaysinhOrder());
        for (int i = 0; i < a.length; i++)
            StdOut.println(a[i]);
        StdOut.println();

        StdOut.println("Sắp xếp theo tên");
        Arrays.sort(a, new Student.tenOrder());
        for (int i = 0; i < a.length; i++)
            StdOut.println(a[i]);
        StdOut.println();

        StdOut.println("Sắp xếp theo họ đệm");
        Arrays.sort(a, new Student.hodemOrder());
        for (int i = 0; i < a.length; i++)
            StdOut.println(a[i]);
        StdOut.println();

        StdOut.println("Sắp xếp theo điểm trung bình");
        Arrays.sort(a, new Student.diemTbOrder());
        for (int i = 0; i < a.length; i++)
            StdOut.println(a[i]);
        StdOut.println();
    }
}

/******************************************************************************
 * Bản quyền 2002-2016, Robert Sedgewick và Kevin Wayne.
 *
 * Tệp này là một phần của algs4.jar, đi kèm với sách giáo khoa
 *
 * Algorithms, 4th edition bởi Robert Sedgewick và Kevin Wayne,
 * Addison-Wesley Professional, 2011, ISBN 0-321-57351-X.
 * http://algs4.cs.princeton.edu
 *
 *
 * algs4.jar là phần mềm miễn phí: bạn có thể phân phối lại và/hoặc sửa đổi
 * nó theo các điều khoản của Giấy phép Công cộng GNU do Free Software
 * Foundation
 * xuất bản, phiên bản 3 của Giấy phép, hoặc (theo lựa chọn của bạn) bất kỳ
 * phiên bản nào sau đó.
 *
 * algs4.jar được phân phối với hy vọng rằng nó sẽ hữu ích,
 * nhưng KHÔNG CÓ BẢO HÀNH NÀO, kể cả bảo đảm ngầm định
 * VỀ KHẢ NĂNG TIÊU THỤ HOẶC PHÙ HỢP CHO MỤC ĐÍCH CỤ THỂ.
 * Xem Giấy phép Công cộng GNU để biết thêm chi tiết.
 *
 * Bạn sẽ nhận được một bản sao của Giấy phép Công cộng GNU
 * cùng với algs4.jar. Nếu không, hãy xem http://www.gnu.org/licenses.
 ******************************************************************************/
