
/**
 * Write a description of class Lecture here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Lecture implements Comparable<Lecture> 
{
   private double start;
   private double  finish;
   private double profit;
   
   public Lecture(double s, double f, double  p){
       this.start = s;
       this.finish = f;
       this.profit = p;
    }
public int compareTo(Lecture that){
    return Double.compare(this.start , that.start);
}

public double start(){ return this.start;}

public double finish(){ return this.finish;}

public double profit(){ return this.profit;}

public String toString(){
    return String.format( "start: "+ this.start +" finish: "+ this.finish+" profit: "+ this.profit);
}

}
