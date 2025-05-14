import java.util.*;

/**
 * Write a description of class MergeSort here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public  class MergeSort
{
    public MergeSort(){}
    
    private static <Key extends Comparable<Key>> boolean less(Key v, Key w) {
        return (v.compareTo(w) < 0);
    }
    
    public static <Key extends Comparable<Key>> void mergeSort(Key [] a){
        Key [] aux = (Key[]) new Comparable[a.length];
        
        mergeSort(a, aux, 0, a.length -1);
    }
    
    private static <Key extends Comparable<Key>> void mergeSort(Key [] a, Key [] aux , int lo, int hi){
        if (hi <= lo ) return ;
        int mid = lo + (hi - lo)/2;
        mergeSort(a, aux, lo , mid);
        mergeSort(a, aux, mid+1, hi);
        merge(a,aux,lo , mid, hi);
        
    };
    
    
    private static <Key extends Comparable<Key>> void merge(Key [] a, Key [] aux, int lo, int mid, int hi){
         for(int k=lo ; k<= hi; k++){
            aux[k ] = a[k];
        }
        
        int i=lo;
        int j=mid+1;
        // merge
        for(int k = lo; k<=hi; k++){
            if(i> mid ) a[k] = aux[j++];
            else if(j> hi ) a[k] = aux[i++];
            else if(less(aux[j],aux[i])) a[k] = aux[j++];
            else a[k] = aux[i++];
        }
    };
    
    public static void main(String args[]){
        Thisinh a[] = new Thisinh[4];
    a[0] =new Thisinh("dangquang,son,28/09/2005,nghean,29.0");
    a[1] =new Thisinh("hoangduc,trong,26/3/2005,thainuyen,30.43");
    a[2] = new Thisinh("buiminh,thuong,14/6/2005,hoabinh,33.34");
        a[3] = new Thisinh("dovan,an,12/2/2004,thaibinh,28.90");
    
    StdOut.println("chưa săp  xếp:");
    for(Thisinh c: a) {
        StdOut.println(c);
    }
    
    StdOut.println("theo diemtb:");
    //sort
    mergeSort(a);
    for(Thisinh c: a) {
        StdOut.println(c);
    }
    
    
}
        
    }
    
