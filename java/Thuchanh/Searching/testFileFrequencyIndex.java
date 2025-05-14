

public class testFileFrequencyIndex
{

    /**
     * Constructor for objects of class testFileFrequencyIndex
     */
    public testFileFrequencyIndex()
    {
        
    }

    public static void main (String args[]) throws java.io.FileNotFoundException {
        FileFrequencyIndex fi= new FileFrequencyIndex();
        fi.create(args);
        fi.frequency("the");
        fi.frequency("tank");
    }
}