import java.util.*;

/******************************************************************************
 *  Compilation:  javac InversionsStudent.java
 *  Execution:    java InversionsStudent < input.txt
 *  Dependencies: StdIn.java StdOut.java
 *  
 *  Read array of n integers and count number of inversions in n log n time.
 *
 ******************************************************************************/

 import java.io.*;

/**
 *  The {@code InversionsStudent} class provides static methods to count the 
 *  number of <em>inversions</em> in either an array of integers or comparables.
 *  An inversion in an array {@code a[]} is a pair of indicies {@code i} and
 *  {@code j} such that {@code i < j} and {@code a[i] > a[j]}.
 *  <p>
 *  This implementation uses a generalization of mergesort. The <em>count</em>
 *  operation takes time proportional to <em>n</em> log <em>n</em>,
 *  where <em>n</em> is the number of keys in the array.
 *  <p>
 *  For additional documentation, see <a href="https://algs4.cs.princeton.edu/22mergesort">Section 2.2</a>
 *  of <i>Algorithms, 4th Edition</i> by Robert Sedgewick and Kevin Wayne.
 *
 *  @author Robert Sedgewick
 *  @author Kevin Wayne
 */
public class InversionsStudent {

    // do not instantiate
    private InversionsStudent() { }

    // merge and count
    private static long merge(List<Student> a, List<Student> aux, int lo, int mid, int hi) {
        long inversions = 0;

        // copy to aux[]
        while (aux.size() < a.size()) aux.add(null);
        for (int k = lo; k <= hi; k++) {
            aux.set(k, a.get(k)); 
        }

        // merge back to a[]
        int i = lo, j = mid+1;
        for (int k = lo; k <= hi; k++) {

        // Bo sung noi dung vong for de tron lai .....
             if(i > mid ) {
                 a.set(k, aux.get(j++));
             }
             else if(j > hi){
                 a.set(k, aux.get(i++));
             }
             else if(less(aux.get(j),aux.get(i))){
                 a.set(k, aux.get(j++));
                 inversions += mid-i+1;
             }
             else {
                 a.set(k, aux.get(i++));
             }
        }
        return inversions;
    }

    // return the number of inversions in the subarray b[lo..hi]
    // side effect b[lo..hi] is rearranged in ascending order
    private static long count(List<Student> a, List<Student> b, List<Student> aux, int lo, int hi) {
        long inversions = 0;
        if (hi <= lo) return 0;
        int mid = lo + (hi - lo) / 2;
        inversions += count(a, b, aux, lo, mid);  
        inversions += count(a, b, aux, mid+1, hi);
        inversions += merge(b, aux, lo, mid, hi);
        assert inversions == brute(a, lo, hi);
        return inversions;
    }


    /**
     * Returns the number of inversions in the integer array.
     * The argument array is not modified.
     * @param  a the array
     * @return the number of inversions in the array. An inversion is a pair of 
     *         indicies {@code i} and {@code j} such that {@code i < j}
     *         and {@code a[i] > a[j]}.
     */
    public static long count(List<Student> a) {
        List<Student> b = new ArrayList<>();//int[] b   = new int[a.length];
        List<Student> aux = new ArrayList<>();//int[] aux = new int[a.length];
        for (int i = 0; i < a.size(); i++)
            b.add(a.get(i));
            
        long inversions = count(a, b, aux, 0, a.size() - 1);
        return inversions;
    }



    /**
     * Returns the number of inversions in the comparable array.
     * The argument array is not modified.
     * @param  a the array
     * @param <Key> the inferred type of the elements in the array
     * @return the number of inversions in the array. An inversion is a pair of 
     *         indicies {@code i} and {@code j} such that {@code i < j}
     *         and {@code a[i].compareTo(a[j]) > 0}.
     *
    public static <Key extends Comparable<Key>> long count(Key[] a) {
        Key[] b   = a.clone();
        Key[] aux = a.clone();
        long inversions = count(a, b, aux, 0, a.length - 1);
        return inversions;
    }*/


    // is v < w ?
    private static <Key extends Comparable<Key>> boolean less(Key v, Key w) {
        return (v.compareTo(w) < 0);
    }


    // count number of inversions in a[lo..hi] via brute force (for debugging only)
    private static long brute(List<Student> a, int lo, int hi) {
        long inversions = 0;
        for (int i = lo; i <= hi; i++)
            for (int j = i + 1; j <= hi; j++)
                if (less(a.get(j), a.get(i))) inversions++;
        return inversions;
    }

    /**
     * Reads a sequence of integers from standard input and
     * prints the number of inversions to standard output.
     *
     * @param args the command-line arguments
     */
    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream(new File("students.txt")));
        //
        List<Student> a = new ArrayList<>();
        while(!StdIn.isEmpty()){
            String line = StdIn.readLine();
            a.add(new Student(line));
        }
        //
        List<Student> b = new ArrayList<>();
        for (int i = 0; i < a.size(); i++) {
            b.add(a.get(i));
            StdOut.println(a.get(i));
        }
        //
        StdOut.println(InversionsStudent.count(a));
        StdOut.println(InversionsStudent.count(b));
    }
}

/******************************************************************************
 *  Copyright 2002-2016, Robert Sedgewick and Kevin Wayne.
 *
 *  This file is part of algs4.jar, which accompanies the textbook
 *
 *      Algorithms, 4th edition by Robert Sedgewick and Kevin Wayne,
 *      Addison-Wesley Professional, 2011, ISBN 0-321-57351-X.
 *      http://algs4.cs.princeton.edu
 *
 *
 *  algs4.jar is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  algs4.jar is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with algs4.jar.  If not, see http://www.gnu.org/licenses.
 ******************************************************************************/
