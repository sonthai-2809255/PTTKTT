
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
    /** Constructor for objects of class FileFrequencyIndex*/
    public FileFrequencyIndex() {
         st =new  ST <String, ST< File, Integer>>();
        
    }
    
    public ST query(String word){
        StdOut.println("searching : " + word);
        if(!st.contains(word))
                return null;
        return  st.get(word);
    }
    
    /** phương thức bổ trợ */
    public void print(ST<File,Integer> st){
        if(st == null) {
            StdOut.println("not found");
            return;
        }
        List <FileFrequency> list = new ArrayList<>();
        for(File c : st.keys()){
            list.add(new FileFrequency(c,st.get(c)));
        }
        Collections.sort(list);
        for(FileFrequency c: list){
            StdOut.println("  "+ c);
        }
    }
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
    
    public ST<String,ST<File,Integer>> getST(){ return st;}
    
    public static void main (String args[]) throws java.io.FileNotFoundException {
        FileFrequencyIndex fi= new FileFrequencyIndex();
        String filename[] ={"ex1.txt", "ex2.txt","ex3.txt","ex4.txt"};
        fi.readFile(filename);
        fi.print(fi.query("the"));
        fi.print(fi.query("tank"));
    }
}
