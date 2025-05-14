import java.util.*;
import java.io.File;

/**
 * Write a description of class IntervalScheduling here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class IntervalScheduling
{
    private List<Job2> jobs;
    
    public IntervalScheduling(){ jobs = new ArrayList<Job2>();}
    
    public void findLastNonConflictingJob(In in){
        MinPQ<Job2> pq= new MinPQ<Job2>();
        //read file
        while(!in.isEmpty()){
            double start = in.readDouble();
            double finish = in.readDouble();
            double profit = in.readDouble();
            pq.insert( new Job2(start,finish,profit));
        }
        //solve
        double LastFinishTime = 0;
        
        while(!pq.isEmpty()){
            Job2 select = pq.delMin();
            if(select.getStart() >= LastFinishTime){
                this.jobs.add(select);
                LastFinishTime = select.getFinish();
            }
        }
            
    }
    
    public void display(){
         for(Job2 c: this.jobs){
            System.out.println(c.toString() );
        }
    }
    
    public List<Job2> getJobs(){return this.jobs;}
    
        
    public static void main(String args[]){
        
        In in = new In(new File("job2.txt"));
        IntervalScheduling IS= new IntervalScheduling();
        IS.findLastNonConflictingJob(in);
        IS.display();
        
    }
}
