import java.io.File;
import java.util.*;
public class FileSearch {
    private FileFrequencyIndex ffi;
    
    public FileSearch(){
        ffi = new FileFrequencyIndex();
    }

    public void readfile(String args[]){
        ffi.readFile(args);
    }
    /// query a line
    public SET query(String word1,String word2){
        StdOut.println("searching for: " + word1 + "," + word2);
        String [] words = {word1,word2};
        Map<File,Integer> count = new HashMap<>();
        for(String w: words){
            ST<File,Integer> tempt1 = ffi.query(w);
            if (tempt1 == null) {
                return null;
            }
            for (File f : tempt1.keys()) {
                if (!count.containsKey(f)) //O(1)
                {
                    count.put(f, 0);//O(1)
                }
                count.put(f,count.get(f) + 1);//O(1)
            }
        }
        HashMap <File,Integer> section = new HashMap<>();
        for(File f: count.keySet()){
            if(count.get(f)==words.length)//O(1)
            {
                for(String w: words){
                    if(!section.containsKey(f)) section.put(f,0);
                    section.put(f,ffi.getST().get(w).get(f)+section.get(f));
                }
            }
        }
        SET<FileCount> set = new SET<>();
        for(File f: section.keySet()){
            set.add(new FileCount(f,section.get(f)));
        }
        return set;
    }
    public SET query(String String){
        StdOut.println("searching for: " + String);
        String [] words = String.split("\\s+");
        SET set = query(words);
        return set;
    }
    private SET query(String [] words){
        Map<File,Integer> count = new HashMap<>();
        for(String w: words){
            ST<File,Integer> tempt = ffi.query(w);
            if (tempt == null) {
                return null;
            }
            for (File f : tempt.keys()) {
                if (!count.containsKey(f)) //O(1)
                {
                    count.put(f, 0);//O(1)
                }
                count.put(f, count.get(f) + 1);//O(1)
            }
        }
        HashMap <File,Integer> section = new HashMap<>();
        for(File f: count.keySet()){
            if(count.get(f)==words.length)//O(1)
            {
                for(String w: words){
                    if(!section.containsKey(f)) section.put(f,0);
                    section.put(f,ffi.getST().get(w).get(f)+section.get(f));
                }
            }
        }
        SET<FileCount> set = new SET<>();
        for(File f: section.keySet()){
            set.add(new FileCount(f,section.get(f)));
        }
        return set;
    }

    public void print(SET<FileCount> set){
        if(set == null) {
            StdOut.println("not found");
            return;
        }
        for(FileCount f : set){
            StdOut.println(f);
        }
    }

    public static void main(String[] args) {
        FileSearch fs = new FileSearch();
        String filename[] ={"ex1.txt", "ex2.txt","ex3.txt","ex4.txt"};
        fs.readfile(filename);
        fs.print(fs.query("the best of times"));
        fs.print(fs.query("the","tank"));
        fs.print(fs.query("the","it"));
    }

}
