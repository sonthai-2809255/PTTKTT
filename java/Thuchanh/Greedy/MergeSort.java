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
    
   
        
    }
    
