import java.io.File;
import java.util.*;
public class FileSearch {
    private ST<String,ST<File,Integer>> filecount;
    private List<FileCount> result;
    public FileSearch(){
        filecount = new ST<>();
        result = new ArrayList<>();
    }

    public void readfile(String args[]){
        StdOut.println("Reading files:");
        for(String filename : args){
            StdOut.println("    " + filename);
            File file = new File (filename);
            file.setReadable(true);
            In in = new In(file);
            while(!in.isEmpty()){
                String word = in.readString();
                if(filecount.contains(word)) {
                    if(filecount.get(word).contains(file)) {
                        filecount.get(word).put(file, filecount.get(word).get(file) + 1);
                    }
                    else {
                        filecount.get(word).put(file, 1);
                    }
                }
                else {
                    filecount.put(word,new ST<File,Integer>());
                    filecount.get(word).put(file,1);
                }
            }
            in.close();
            if(filecount == null) StdOut.print("filecount is null");

        }

    }

    ///
    public static class FileCount implements Comparable<FileCount>{
        private File file;
        private int count;
        public FileCount(File file,int count){
            this.file= file;
            this.count= count;
        }
        public int compareTo(FileCount that){
            return Integer.compare(that.count, this.count);
        }
        public String toString(){
            return String.format(file + "   " + count);
        }
        public int hashCode(){
            int hash =1;
            hash = 31*hash + file.getName().hashCode();
            hash = 31*hash + ((Integer) count).hashCode();
            return hash;
        }
    }
    /// query a line
    public void query(String string){
        StdOut.println("query: " + string);
        String words[] = string.split("\\s+");
        query(words);
    }
    private void query(String [] words){
        if(words.length == 0) StdOut.println("word is null");
        ST<File,Integer> count = new ST<>();

        for(String w : words){
            StdOut.print("["+ w + "] ");

            if (filecount.contains(w)){
                for(File f : filecount.get(w).keys()){
                    if(!count.contains(f)) count.put(f,0);
                    //
                    int countf = filecount.get(w).get(f) ;
                    count.put(f,count.get(f)+countf);
                }
            }
        }
        StdOut.println();
        //
        for(File f : count.keys()){
            result.add(new FileCount(f,count.get(f)));
        }
        Collections.sort(result);
    }
    /// return a list sorted
    public List<FileCount> results(){
        return result;
    }

}
