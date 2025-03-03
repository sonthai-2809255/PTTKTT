import java.util.*;
import java.io.*;

public class Monhoc implements Comparable<Monhoc>{
    private final String tenmon;
    private final int tinchi;
    private final int kythu;
    
    // khoi tao mot doi tuong Monhoc
    public Monhoc(String tenmon,int tinchi,int kythu){
        this.tenmon=tenmon;
        this.tinchi=tinchi;
        this.kythu= kythu;
    }
    
    public String tenmon(){
        return tenmon;
    }
    
    public int tinchi(){
        return tinchi;
    }
    
    public int kythu(){
        return kythu;
    }
    
    public int compareTo(Monhoc that){
        return Integer.compare(this.tinchi, that.tinchi);
    }
    
    public boolean equals(Object other){
        if(other == this) 
            return true;
        if(other == null) 
            return false;
        if(other.getClass() != this.getClass())
            return false;
        Monhoc that= (Monhoc) other;
        return (this.tenmon.equals(that.tenmon)) 
            && (this.tinchi == that.tinchi)
            && (this.kythu == that.kythu);
    }
    
    public int hashCode(){
        int hash=1;
        hash = 31 * hash + tenmon.hashCode();
        hash = 31 * hash + ((Integer)tinchi).hashCode();
        hash = 31 * hash + ((Integer)kythu).hashCode();
        return hash; 
    }
    // trave mot chuoi bieu dien thong tin 
    public String toString(){
        return String.format("%-10s %8d %8d",tenmon,tinchi,kythu); 
    }
    
    
    public static void main(String args[]){
        Monhoc mon1= new Monhoc("CSDL",3,4);
        StdOut.println(mon1);
    }
}
