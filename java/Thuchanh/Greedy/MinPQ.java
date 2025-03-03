import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.io.*;

/**
 * The {@code MinPQ} class represents a priority queue of generic keys.
 * It supports the usual <em>insert</em> and <em>delete-the-min</em>
 * operations, along with methods for peeking at the min key,
 * testing if the priority queue is empty, and iterating through
 * the keys.
 * <p>
 * This implementation uses a binary heap.
 * The <em>insert</em> and <em>delete-the-min</em> operations take
 * logarithmic amortized time.
 * The <em>min</em>, <em>size</em>, and <em>is-empty</em> operations take
 * constant time.
 * Construction takes time proportional to the specified capacity or the number
 * of
 * items used to initialize the data structure.
 * <p>
 * For additional documentation, see
 * <a href="https://algs4.cs.princeton.edu/24pq">Section 2.4</a> of
 * <i>Algorithms, 4th Edition</i> by Robert Sedgewick and Kevin Wayne.
 *
 * @author Robert Sedgewick
 * @author Kevin Wayne
 *
 * @param <Key> the generic type of key on this priority queue
 */

public class MinPQ<Key> implements Iterable<Key> {
    private Key[] pq; // lưu giữ các phần tử tại chỉ số từ 1 đến n
    private int n; // số phần tử trong priority queue
    private Comparator<Key> comparator; // bộ so sánh tuỳ chọn

    /**
     * khởi tạo priority queue với dung lượng ban đầu được chỉ định
     *
     * @param initCapacity là dung lượng ban đầu của hàng đợi ưu tiên này priority
     *                     queue
     */
    public MinPQ(int initCapacity) {
        // khởi tạo mảng object và ép sang mảng kiểu Key
        pq = (Key[]) new Object[initCapacity + 1];
        n = 0;
    }

    /**
     * khởi tạo priority queue rỗng .
     */
    public MinPQ() {
        this(1);
    }

    /**
     * khởi tạo PQ rỗng với dung lượng được chỉ định là initCapacity
     * sử dụng bộ so sánh đã cho comparable
     *
     * @param initCapacity the initial capacity of this priority queue
     * @param comparator   the order in which to compare the keys
     */
    public MinPQ(int initCapacity, Comparator<Key> comparator) {
        this.comparator = comparator;
        pq = (Key[]) new Object[initCapacity + 1];
        n = 0;
    }

    /**
     * Initializes an empty priority queue using the given comparator.
     *
     * @param comparator là cách thức so sánh các khoá
     */
    public MinPQ(Comparator<Key> comparator) {
        this(1, comparator);
    }

    /**
     * Khởi tạo hàng đợi ưu tiên từ một mảng các khoá.
     * Takes time proportional to the number of keys, Sử dụng các xây dựng heap tên
     * phương pháp sink
     *
     * @param keys là một mảng các khoá
     */
    public MinPQ(Key[] keys) {
        n = keys.length;
        pq = (Key[]) new Object[keys.length + 1];
        // mảng các khoá bắt đầu chỉ số là 0, còn PQ chỉ số bắt đầu là 1
        for (int i = 0; i < n; i++)
            pq[i + 1] = keys[i];
        for (int k = n / 2; k >= 1; k--)
            sink(k);
        assert isMinHeap();
    }

    /**
     * Returns true if this priority queue is empty.
     *
     * @return {@code true} if this priority queue is empty;
     *         {@code false} otherwise
     */
    public boolean isEmpty() {
        return n == 0;
    }

    /**
     * Returns the number of keys on this priority queue.
     *
     * @return the number of keys on this priority queue
     */
    public int size() {
        return n;
    }

    /**
     * Trả về khoá nhỏ nhất trong priority queue.
     *
     * @return a largest key on this priority queue
     * @throws NoSuchElementException Xử lý nén lỗi nếu PQ này rỗngư
     */
    public Key min() {
        if (isEmpty())
            throw new NoSuchElementException("Priority queue underflow");
        return pq[1];
    }

    // hàm trợ giúp để tăn gấp đoi kích thước của mảng heap
    private void resize(int capacity) {
        assert capacity > n;
        Key[] temp = (Key[]) new Object[capacity];
        for (int i = 1; i <= n; i++) {
            temp[i] = pq[i];
        }
        pq = temp;
    }

    /**
     * Adds a new key to this priority queue.
     * Thêm một khoá mới vào PQ này
     * 
     * @param x the new key to add to this priority queue
     *          x là khoá mới cần thêm vào
     */
    public void insert(Key x) {

        // double size of array if necessary
        // tăng kích thước gâop đôi nếu cần thiết
        if (n == pq.length - 1)
            resize(2 * pq.length);

        // add x, and percolate it up to maintain heap invariant
        // thêm x, và di chuyển lên để đảm bảo tính chất cây đống

        // thêm vào cuối và vun đống lại
        pq[++n] = x;
        swim(n);

        assert isMinHeap();
    }

    /**
     * Removes and returns a minest key on this priority queue.
     * loại bỏ và trả về khoá nhỏ nhất trong PQ
     * 
     * @return a largest key on this priority queue
     * @throws NoSuchElementException if this priority queue is empty
     */
    public Key delMin() {
        // nén lỗi nếu PQ rỗng
        if (isEmpty())
            throw new NoSuchElementException("Priority queue underflow");
        Key min = pq[1];
        exch(1, n--);
        sink(1);
        pq[n + 1] = null; // to avoid loiterig and help with garbage collection
                          // để tránh lưu lạc và hỗ trợ thu gom rác
        if ((n > 0) && (n == (pq.length - 1) / 4))
            resize(pq.length / 2);
        assert isMinHeap();
        return min;
    }

    /***************************************************************************
     * Helper functions to restore the heap invariant
     * các hàm trơ giúp để khôi phục tính chất cây đống.
     ***************************************************************************/

    private void swim(int k) {
        while (k > 1 && less(k, k / 2)) {
            exch(k, k / 2);
            k = k / 2;
        }
    }

    private void sink(int k) {
        while (2 * k <= n) {
            int j = 2 * k;
            if (j < n && less(j + 1, j))
                j++;
            if (less(k, j))
                break;
            exch(k, j);
            k = j;
        }
    }

    /***************************************************************************
     * Helper functions for compares and swaps.
     * các hàm trợ giúp so sánh và hoán đổi
     ***************************************************************************/
    private boolean less(int i, int j) {
        if (comparator == null) {
            return ((Comparable<Key>) pq[i]).compareTo(pq[j]) < 0;
        } else {
            return comparator.compare(pq[i], pq[j]) < 0;
        }
    }

    private void exch(int i, int j) {
        Key swap = pq[i];
        pq[i] = pq[j];
        pq[j] = swap;
    }

    // is pq[1..N] a min heap?
    // đây có phải lá một minPQ không
    private boolean isMinHeap() {
        return isMinHeap(1);
    }

    // is subtree of pq[1..n] rooted at k a max heap?
    private boolean isMinHeap(int k) {
        if (k > n)
            return true;
        int left = 2 * k;
        int right = 2 * k + 1;
        if (left <= n && less(left, k))
            return false;
        if (right <= n && less(right, k))
            return false;
        return isMinHeap(left) && isMinHeap(right);
    }

    /***************************************************************************
     * Iterator.
     * bộ lặp
     ***************************************************************************/

    /**
     * Returns an iterator that iterates over the keys on this priority queue
     * trả về bộ lặp qua các khoá trong PQ
     * in increasing order.
     * theo thứ tự tăng dần
     * The iterator doesn't implement {@code remove()} since it's optional.
     *
     * @return an iterator that iterates over the keys in increasing order
     */
    public Iterator<Key> iterator() {
        return new HeapIterator();
    }

    private class HeapIterator implements Iterator<Key> {

        // create a new pq
        private MinPQ<Key> copy;

        // add all items to copy of heap
        // takes linear time since already in heap order so no keys move
        public HeapIterator() {
            if (comparator == null)
                copy = new MinPQ<Key>(size());
            else
                copy = new MinPQ<Key>(size(), comparator);
            for (int i = 1; i <= n; i++)
                copy.insert(pq[i]);
        }

        public boolean hasNext() {
            return !copy.isEmpty();
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        public Key next() {
            if (!hasNext())
                throw new NoSuchElementException();
            return copy.delMin();
        }
    }

}
