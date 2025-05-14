import java.util.*;

/**
 * Write a description of class Job3 here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Job3 implements Comparable<Job3>
{
    private double time;
    private double deadline;
    
    public Job3(double t, double d){
        this.time = t;
        this.deadline = d;
    }
    
    public double getTime(){
        return time;
    }
    
    public double getDeadline(){
        return deadline;
    }
    
    public int compareTo(Job3 that){
        if(this.deadline != that .deadline){
            return Double.compare(this.deadline, that.deadline);
        }
        else{
            return Double.compare(this.time,that.time);
        }
    }
    
    public String toString(){
        return String.format("time: " + this.time + "     deadline: " + this.deadline);
    }
    
    public static void main(String args[]){
        List<Job3> job3s = new ArrayList<>();
        job3s.add(new Job3(12, 20));
        job3s.add(new Job3(12, 12));
        job3s.add(new Job3(2, 2));
        job3s.add(new Job3(1, 100));
        job3s.add(new Job3(5, 100));
        
        StdOut.println("before: ");
        for(Job3 job: job3s){
            StdOut.println(job);
        }
        
        StdOut.println("affter: ");
        Collections.sort(job3s);
        for(Job3 job: job3s){
            StdOut.println(job);
        }
        
    }
}
