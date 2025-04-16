import java.util.*;
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
    
    public void findLastNonConflictingJob(List<Job2> array){
        MinPQ<Job2> pq= new MinPQ<Job2>();
        
        for(Job2 job: array){
            pq.insert(job);
        }
        
        double LastFinishTime = 0;
        
        while(!pq.isEmpty()){
            Job2 select = pq.delMin();
            if(select.getStart() >= LastFinishTime){
                this.jobs.add(select);
                LastFinishTime = select.getFinish();
            }
        }
            
    }
    
    public List<Job2> getJobs(){return this.jobs;}
    
        
    public static void main(String args[]){
        List<Job2> jobs = Arrays.asList(new Job2(0, 6, 60),
                                     new Job2(1, 4, 30),
                                     new Job2( 3, 5, 10 ),
                                     new Job2( 5, 7, 30 ),
                                     new Job2( 5, 9, 50 ),
                                     new Job2( 7, 8, 10 )   );
        IntervalScheduling is= new IntervalScheduling();
        is.findLastNonConflictingJob(jobs);
         for(Job2 c: is.getJobs()){
            System.out.println(c.toString() );
        }
    }
}
