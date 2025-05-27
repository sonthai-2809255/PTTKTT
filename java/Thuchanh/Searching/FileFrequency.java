import java.io.File;

/**
 * Write a description of class FileFrequency here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class FileFrequency implements Comparable<FileFrequency>
{
    private File file;
    private int frequency;
    public FileFrequency(File file, int frequency){
        this.file= file;
        this.frequency = frequency;
        
    }
    public int hashCode(){
        int hash =1;
        hash = 31*hash + file.getName().hashCode();
        hash = 31*hash + ((Integer) frequency).hashCode();
        return hash;
    }
    public int compareTo(FileFrequency that){
        return Integer.compare(that.frequency, this.frequency);
    }
    public String toString(){
        return String.format(file + "   " + frequency);
    }
}
