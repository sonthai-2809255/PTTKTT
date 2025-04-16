import java.util.List;
import java.util.ArrayList;

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
    
    public void findLastRoomsNonConflictingLecture(Lecture[] lectures){
        MinPQ<Lecture> pq = new MinPQ<>();
        
        for(Lecture c : lectures)
        {
            pq.insert(c);
        }
        
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
    
    public List<Queue<Lecture>> getRooms(){return this.room;}
    
    public static void main(String args[]){
        Lecture []lectures = new Lecture[]{new Lecture(0, 6, 60),
                                     new Lecture(1, 4, 30),
                                     new Lecture( 3, 5, 10 ),
                                     new Lecture( 5, 7, 30 ),
                                     new Lecture( 5, 9, 50 ),
                                     new Lecture( 7, 8, 10 ),
                                    new Lecture( 2, 4, 10 ),
                                    new Lecture( 5, 8, 10 ),
                                    new Lecture( 1, 8, 10 ),
                                    new Lecture( 8, 12, 10 ),};
        IntervalPartitioning IP = new IntervalPartitioning();
        IP.findLastRoomsNonConflictingLecture(lectures);
         int num = 1;
        for(Queue<Lecture> room: IP.getRooms()){
            StdOut.println("Room: " + num);
            for(Lecture lecture : room){
                StdOut.println("   " + lecture);
            }
            num++;
        }
                                     
    }
}
