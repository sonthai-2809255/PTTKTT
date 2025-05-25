
/**
 * Write a description of class FileFrequencyIndex here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
import java.io.File;
import java.util.*;
import java.util.ArrayList;

public class FileFrequencyIndex
{
    private ST <String, ST< File, Integer>> st ;
    
    /**
     * Constructor for objects of class FileFrequencyIndex
     */
    public FileFrequencyIndex() {
         st =new  ST <String, ST< File, Integer>>();
        
    }
    
    
    public void query(String word){
         StdOut.println(word);
         List<FileFrequency> list = new ArrayList<>();

        if(!st.contains(word)) 
            {
                StdOut.println("   not contains " + word);
                return ;
            }
            
        ST<File,Integer> a= st.get(word);
            
        for(File c : a.keys()){
            list.add(new FileFrequency(c,a.get(c)));
        }
        Collections.sort(list);
        for(FileFrequency c: list){
            StdOut.println("  "+ c);
        }
    }
    
    /** phương thức bổ trợ */
    public void readFile (String args[]){
        
         StdOut.println("Indexing files");
        for(String filename : args){
            StdOut.println("    " + filename);
            //tao lai file
            File file= new File(filename);
            //truyen vao In
            file.setReadable(true);
            In in= new In(file);
            while(!in.isEmpty()){
                String word = in.readString();
                if(!st.contains(word)){
                    st.put(word, new ST<File,Integer>());
                    st.get(word).put(file,1);
                }
                else{
                    if(!st.get(word).contains(file)){
                        st.get(word).put(file,1);
                    }
                    else st.get(word).put(file,st.get(word).get(file) +1);
                }
            }
            in.close();
        }
    }
}
