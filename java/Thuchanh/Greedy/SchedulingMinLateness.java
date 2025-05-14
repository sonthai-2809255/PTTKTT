import java.util.*;
import java.io.*;


/**
 * Write a description of class SchedulingMinLateness here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class SchedulingMinLateness
{
   private double minimizelate;
    
   public SchedulingMinLateness(){
       minimizelate= 0.0;
       
   }
   
   public void find(Job3[] a){
     MergeSort merge = new MergeSort();
        merge.mergeSort(a);
        
        double end = 0.0;
        
        for(Job3 job : a){
            StdOut.println(job);
            
            end += job.getTime();
            StdOut.println("end : " + end);
            
            double late= end - job.getDeadline();
            minimizelate = Math.max(minimizelate, late);
            StdOut.println("late " + ((late <=0 )?0:late) );
        }
    }
   
   public double minimizelate(){
       return this.minimizelate;
    }
    
    public static void main(String args[]){
        Job3 []a ={ new Job3(12, 20),new Job3(20, 20),new Job3(1, 2),new Job3(8,8),new Job3(1, 100),
            new Job3(20, 30)};
        SchedulingMinLateness sml= new SchedulingMinLateness();
        sml.find(a);
        StdOut.println("");
        StdOut.println("minimizelate: "+ sml.minimizelate());
    }
}
