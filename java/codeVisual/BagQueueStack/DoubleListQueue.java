import java.util.Iterator;
import java.util.NoSuchElementException;
import java.io.*;
import java.util.Scanner;

public class DoubleListQueue<Item> implements Iterable<Item> {
    private class node {
        private Item elem;
        private node next;
        private node prev;
    }

    private int n;// number of elements on queue
    private node head;// beginning of queue
    private node trail;// end of queue

    // initializes an queue empty
    public DoubleListQueue() {
        n = 0;
        head = trail = null;
        assert check();
    }

    // queue is empty?
    public boolean empty() {
        return head == null;
    }

    // examese the current number in this queue
    public int size() {
        return n;
    }

    // return the item least recently added to this queue
    // @throws java.util.NoSuchElementException if queue is empty
    public Item peek() {
        if (empty())
            throw new NoSuchElementException("Queue underflow");
        return head.elem;
    }

    // adds the item to this queue
    public void enqueue(Item elem) {
        node oldtrail = trail;
        trail = new node();
        trail.elem = elem;
        trail.next = null;
        if (empty())
            head = trail;
        else {
            oldtrail.next = trail;
            trail.prev = oldtrail;
        }
        n++;
        assert check();

    }

    // remove and return an the item in this queue that was recently added
    public Item dequeue() {
        if (empty())
            throw new NoSuchElementException();
        Item elem = head.elem;
        head = head.next;
        head.prev = null;
        n--;
        if (empty())
            trail = null;
        assert check();
        return elem;
    }

    /*
     * Returns a string representation of this queue
     * 
     * @return the sequeue of items in FIFO order, separeated by spaces
     * 
     */
    public String toString() {
        StringBuilder s = new StringBuilder();
        for (Item elem : this)
            s.append(elem + " ");
        return s.toString();

    }

    // check internal consistency
    private boolean check() {
        if (n < 0) {
            return false;
        } else if (n == 0) {
            if (head != null)
                return false;
            if (trail != null)
                return false;
        } else if (n == 1) {
            if (head == null || trail == null)
                return false;
            if (head != trail)
                return false;
            if (head.next != null || trail.prev != null)
                return false;
        } else {
            if (head == null || trail == null)
                return false;
            if (head == trail)
                return false;
            if (head.next == null)
                return false;
            if (trail.prev == null)
                return false;

            // check internal consistency of variable n
            int numberOfNodes = 0;
            node x = head;
            for (; x != null && numberOfNodes <= n; x = x.next)
                numberOfNodes++;
            if (numberOfNodes != n)
                return false;

            // check internal consistency of variable node trail
            node trailNode = head;
            for (; trailNode.next != null;) {
                trailNode = trailNode.next;
            }
            if (trail != trailNode)
                return false;
        }

        return true;
    }

    /*
     * @return iterator duyệt qua các phần tử trong queue theo thứ tự FIFO
     */
    public Iterator<Item> iterator() {
        return new ListIterator();
    }

    // một iterator
    private class ListIterator implements Iterator<Item> {
        private node current = head;

        // kiểm tra xem node hiện tại đang duyệt có tồn tại không?
        public boolean hasNext() {
            return current != null;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        // lấy ra elem của node hiện tại
        public Item next() {
            if (!hasNext())
                throw new NoSuchElementException();
            Item elem = current.elem;
            current = current.next;
            return elem;
        }

        public void main(String[] args) {
            try {
                Scanner scanner = new Scanner(new FileInputStream(new File("tobe.txt")));
                DoubleListQueue<String> queue = new DoubleListQueue<String>();
                while (scanner.hasNext()) {
                    String elem = scanner.next();
                    if (!elem.equals("-")) {
                        queue.enqueue(elem);
                    } else if (!queue.empty()) {
                        System.out.print(queue.dequeue() + " ");
                    }
                    System.out.println(queue.size() + " left on queue");
                }
            } catch (FileNotFoundException e) {
                System.err.println("File not found: " + e.getMessage());
            }

        }
    }
}
