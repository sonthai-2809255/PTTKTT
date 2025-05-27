import java.io.*;

/**
 * Write a description of class FileFrequency here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class FileCount implements Comparable<FileCount>
{
    private File file;
    private int count;
    public FileCount(File file, int frequency){
        this.file= file;
        this.count = frequency;
        
    }
    public int hashCode(){
        int hash =1;
        hash = 31*hash + file.getName().hashCode();
        hash = 31*hash + ((Integer) count).hashCode();
        return hash;
    }
    public int compareTo(FileCount that){
        return Integer.compare(that.count, this.count);
    }
    public String toString(){
        return String.format(file + "   " + count);
    }
}
