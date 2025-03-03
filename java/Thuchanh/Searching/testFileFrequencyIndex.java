

public class testFileFrequencyIndex
{

    /**
     * Constructor for objects of class testFileFrequencyIndex
     */
    public testFileFrequencyIndex()
    {
        
    }

    public static void main (String args[]){
        FileFrequencyIndex fi= new FileFrequencyIndex();
        fi.create(args);
        fi.checked("the");
        fi.checked("tank");
    }
}