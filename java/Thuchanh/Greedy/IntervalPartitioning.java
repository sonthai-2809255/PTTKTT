import java.util.List;
import java.util.ArrayList;
import java.io.File;

/**
 * Write a description of class IntervalPartitioning here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class IntervalPartitioning
{
    private List<Queue<Lecture>>room;
    
    public IntervalPartitioning (){
        this.room = new ArrayList<>();
        
        for(Queue c: room){
            c = new Queue<>();
        }
    }
    
    public void findLastRoomsNonConflictingLecture(In in){
        MinPQ<Lecture> pq = new MinPQ<>();
        
        //read file
        while(!in.isEmpty()){
            double start = in.readDouble();
            double finish = in.readDouble();
            double profit = in.readDouble();
            pq.insert(new Lecture(start,finish,profit));
        }
        
        //solve
        while(!pq.isEmpty()){
            Lecture select = pq.delMin();
            boolean comparetible = false;
            
            for(Queue<Lecture> c: room){
                if(!c.isEmpty()){
                    Lecture current = c.peekLast();
                    if(current.finish() <= select.start()){
                        c.enqueue(select);
                        comparetible = true;
                        break;
                    }

                }
                
            }

            if(!comparetible){
                Queue<Lecture> queue = new Queue<>();
                queue.enqueue(select);
                room.add(queue);
            }
        }
    }
    
    public void display(){
        int num = 1;
        for(Queue<Lecture>  r: this.room){
            StdOut.println("Room " + num);
            for(Lecture lecture : r){
                StdOut.println("   " + lecture);
            }
            num++;
        }
    }
    public List<Queue<Lecture>> getRooms(){return this.room;}
    
    public static void main(String args[]){
       
        In in = new In(new File("lecture.txt"));
        IntervalPartitioning IP = new IntervalPartitioning();
        IP.findLastRoomsNonConflictingLecture(in);
        IP.display();
                                     
    }
}
