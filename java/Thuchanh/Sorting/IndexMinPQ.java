import java.util.*;
import java.io.*;

/**
 * Lớp {@code IndexMinPQ} biểu diễn một hàng đợi ưu tiên theo chỉ mục với các khóa tổng quát.
 * Nó hỗ trợ các thao tác như <em>chèn</em>, <em>xóa khóa nhỏ nhất</em>,
 * cùng với <em>xóa</em> và <em>thay đổi khóa</em>.
 * <p>
 * Để cho phép người dùng tham chiếu đến các phần tử trong hàng đợi ưu tiên,
 * mỗi khóa được liên kết với một số nguyên trong khoảng từ {@code 0} đến {@code maxN - 1}.
 * <p>
 * Triển khai này sử dụng một cây nhị phân heap cùng với một mảng để ánh xạ
 * các khóa với các chỉ mục trong phạm vi được chỉ định.
 * Các thao tác như <em>chèn</em>, <em>xóa khóa nhỏ nhất</em>, <em>xóa</em>,
 * <em>thay đổi khóa</em>, <em>giảm khóa</em> và <em>tăng khóa</em>
 * đều có thời gian thực thi logarithmic.
 * Các thao tác như <em>kiểm tra rỗng</em>, <em>kích thước</em>, <em>chỉ mục nhỏ nhất</em>, <em>khóa nhỏ nhất</em>
 * và <em>truy xuất khóa</em> có thời gian thực thi hằng số.
 * <p>
 * @param <Key> kiểu tổng quát của khóa trong hàng đợi ưu tiên này
 */
public class IndexMinPQ<Key extends Comparable<Key>> implements Iterable<Integer> {
    private int n;          // Số phần tử trong hàng đợi ưu tiên
    private int[] pq;       // Heap nhị phân sử dụng chỉ mục bắt đầu từ 1
    private int[] qp;       // Phản ánh của pq - qp[pq[i]] = pq[qp[i]] = i
    private Key[] keys;     // keys[i] = giá trị ưu tiên của i

    /**
     * Khởi tạo một hàng đợi ưu tiên theo chỉ mục rỗng với chỉ số từ {@code 0} đến {@code maxN - 1}.
     *
     * @param  maxN số lượng phần tử tối đa trong hàng đợi ưu tiên
     * @throws IllegalArgumentException nếu {@code maxN < 0}
     */
    public IndexMinPQ(int maxN) {
        if (maxN < 0) throw new IllegalArgumentException();
        n = 0;
        keys = (Key[]) new Comparable[maxN + 1];
        pq   = new int[maxN + 1];
        qp   = new int[maxN + 1];
        for (int i = 0; i <= maxN; i++)
            qp[i] = -1;
    }

    /**
     * Kiểm tra hàng đợi ưu tiên có rỗng không.
     *
     * @return {@code true} nếu rỗng, ngược lại {@code false}
     */
    public boolean isEmpty() {
        return n == 0;
    }

    /**
     * Kiểm tra xem chỉ mục {@code i} có trong hàng đợi ưu tiên không.
     *
     * @param  i chỉ mục cần kiểm tra
     * @return {@code true} nếu tồn tại, ngược lại {@code false}
     */
    public boolean contains(int i) {
        return qp[i] != -1;
    }

    /**
     * Trả về số lượng phần tử trong hàng đợi ưu tiên.
     *
     * @return số lượng phần tử
     */
    public int size() {
        return n;
    }

    /**
     * Chèn một khóa với chỉ mục tương ứng.
     *
     * @param  i chỉ mục
     * @param  key khóa tương ứng
     */
    public void insert(int i, Key key) {
        if (contains(i)) throw new IllegalArgumentException("Chỉ mục đã tồn tại trong hàng đợi ưu tiên");
        n++;
        qp[i] = n;
        pq[n] = i;
        keys[i] = key;
        swim(n);
    }

    /**
     * Trả về chỉ mục của khóa nhỏ nhất.
     *
     * @return chỉ mục của khóa nhỏ nhất
     */
    public int minIndex() {
        if (n == 0) throw new NoSuchElementException("Hàng đợi ưu tiên rỗng");
        return pq[1];
    }

    /**
     * Trả về khóa nhỏ nhất.
     *
     * @return khóa nhỏ nhất
     */
    public Key minKey() {
        if (n == 0) throw new NoSuchElementException("Hàng đợi ưu tiên rỗng");
        return keys[pq[1]];
    }

    /**
     * Xóa khóa nhỏ nhất và trả về chỉ mục của nó.
     *
     * @return chỉ mục của khóa nhỏ nhất
     */
    public int delMin() {
        if (n == 0) throw new NoSuchElementException("Hàng đợi ưu tiên rỗng");
        int min = pq[1];
        exch(1, n--);
        sink(1);
        qp[min] = -1;
        keys[min] = null;
        pq[n+1] = -1;
        return min;
    }

    /**
     * Giảm khóa tại chỉ mục {@code i}.
     *
     * @param  i chỉ mục cần giảm khóa
     * @param  key khóa mới (phải nhỏ hơn khóa hiện tại)
     */
    public void decreaseKey(int i, Key key) {
        if (!contains(i)) throw new NoSuchElementException("Chỉ mục không tồn tại trong hàng đợi ưu tiên");
        if (keys[i].compareTo(key) <= 0)
            throw new IllegalArgumentException("Khóa mới phải nhỏ hơn khóa hiện tại");
        keys[i] = key;
        swim(qp[i]);
    }

    /**
     * Hoán đổi hai phần tử trong heap.
     */
    private void exch(int i, int j) {
        int swap = pq[i];
        pq[i] = pq[j];
        pq[j] = swap;
        qp[pq[i]] = i;
        qp[pq[j]] = j;
    }

    /**
     * Đẩy một nút lên khi cần thiết.
     */
    private void swim(int k) {
        while (k > 1 && keys[pq[k]].compareTo(keys[pq[k/2]]) < 0) {
            exch(k, k/2);
            k = k/2;
        }
    }

    /**
     * Đẩy một nút xuống khi cần thiết.
     */
    private void sink(int k) {
        while (2*k <= n) {
            int j = 2*k;
            if (j < n && keys[pq[j]].compareTo(keys[pq[j+1]]) > 0) j++;
            if (keys[pq[k]].compareTo(keys[pq[j]]) <= 0) break;
            exch(k, j);
            k = j;
        }
    }
    
    // bộ duyệt @Override
public Iterator<Integer> iterator() {
    return new HeapIterator();
}

private class HeapIterator implements Iterator<Integer> {
    private IndexMinPQ<Key> copy;

    public HeapIterator() {
        copy = new IndexMinPQ<>(pq.length - 1);
        for (int i = 1; i <= n; i++) {
            copy.insert(pq[i], keys[pq[i]]);
        }
    }

    @Override
    public boolean hasNext() {
        return !copy.isEmpty();
    }

    @Override
    public Integer next() {
        if (!hasNext()) throw new NoSuchElementException();
        return copy.delMin();
    }
}

}
