
/**
 * Write a description of class Job2 here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Job2 implements Comparable<Job2> 
{
   private double start;
   private double  finish;
   private double profit;
   
   public Job2(int s, int f, int p){
       this.start = s;
       this.finish = f;
       this.profit = p;
    }
public int compareTo(Job2 that){
    return Double.compare(this.finish , that.finish);
}

public double getStart(){ return this.start;}

public double getFinish(){ return this.finish;}

public double getProfit(){ return this.profit;}

public String toString(){
    return String.format( "start: "+ this.start +" finish: "+ this.finish+" profit: "+ this.profit);
}

}
