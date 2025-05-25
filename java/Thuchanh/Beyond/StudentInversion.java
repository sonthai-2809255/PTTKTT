import edu.princeton.cs.algs4.Merge;

import java.util.*;
import java.io.*;

public class StudentInversion {
    public StudentInversion(){};

    public static<Comparable extends java.lang.Comparable<Comparable>>
    long merge(Comparable[] a, Comparable[] aux , int lo, int mid, int hi ){
        long inversion = 0;

        //copy
        for(int k=lo ; k <= hi ; k++){
            aux[k] = a[k];
        }

        //merge
        int i = lo;
        int j = mid +1;

        for(int k = lo; k <= hi ; k++){
            if(i > mid ) a[k] = aux[j++];
            else if( j > hi ) a[k] = aux[i++];
            else if( less(aux[j] , aux[i])){
                a[k] = aux[j++];
                inversion +=(mid - i +1 );
            }
            else  a[k] = aux[i++];
        }

        return inversion;
    }

    private static<Comparable extends java.lang.Comparable<Comparable>> 
    boolean less(Comparable v , Comparable w){
            
            return (v.compareTo(w)) < 0;
    }

    private static <Coparable extends Comparable<Coparable>>  
    long count(Coparable[]a){
        
        Coparable[]b = a.clone();
        Coparable[] aux = a.clone();
        long inversion =  count(a,b,aux,0,a.length -1);
        return inversion;
    }

    private static <Coparable extends Comparable <Coparable>> 
    long count(Coparable[] a, Coparable[] b, Coparable[] aux, int lo, int hi){
        
        long inversion = 0;
        if (lo >= hi) return 0;
        int mid = lo +(hi-lo)/ 2;
        inversion += count(a,b,aux,lo,mid);
        inversion += count(a,b,aux,mid+1,hi);
        inversion += merge(b,aux,lo,mid,hi);

        return inversion;
    }

    public static void main(String[] args) throws IOException{
        File input = new File("students.txt");
        System.setIn(new FileInputStream( input));

        List<Student> list = new ArrayList();
        while(!StdIn.isEmpty()){
        String line = StdIn.readLine();
        list.add(new Student(line));
        }
       //
        Student students[] = new Student[list.size()];
        for(int i =0; i< list.size() ; i++){
            Student s = list.get(i);
           students[i] = s;
           System.out.println(s);
        }
        list.clear();
        System.out.println("inversion = "+ StudentInversion.count(students));

    }

}

