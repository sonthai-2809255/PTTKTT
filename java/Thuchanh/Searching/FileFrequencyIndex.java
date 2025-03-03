
/**
 * Write a description of class FileFrequencyIndex here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
import java.io.File;
public class FileFrequencyIndex
{
    private ST <String, ST< File, Integer>> st ;
    
    /**
     * Constructor for objects of class FileFrequencyIndex
     */
    public FileFrequencyIndex() {
         st =new  ST <String, ST< File, Integer>>();
        
    }
    
    // Lớp hỗ trợ để lưu trữ file và số lần xuất hiện của từ
    public static class FileFrequency implements Comparable<FileFrequency> {
        File file;
        int frequency;// số lần xuất hiện của từ khoá 

        FileFrequency(File file, int frequency) {
            this.file = file;
            this.frequency = frequency;
        }

        @Override
        public int compareTo(FileFrequency other) {
            return Integer.compare(this.frequency, other.frequency);
        }
        
        @Override
        public String toString (){
            return String.format(file +"    " + frequency);
        }
    }
    
    public void checked(String word){
         StdOut.println(word);
        if(!st.contains(word)) 
            {
                StdOut.println("   not contains " + word);
            }
        else{
            ST<File,Integer> a= st.get(word);
            MaxPQ<FileFrequency> pq = new MaxPQ<FileFrequency>();
            for(File c : a.keys()){
                pq.insert(new FileFrequency(c,a.get(c)));
            }
            for(FileFrequency c : pq ){
                StdOut.println("   " + c);
            }
        }
    }
    
    /**tạo dữ liệu với các file đầu vào đã chọn*/
    public void create(String args[]){
        st = Update(args);
    }
    
    /** phương thức bổ trợ */
    private   ST Update(String args[]){
        
         ST <String, ST< File, Integer>> cp = st;
         StdOut.println("Indexing files");
        for(String filename : args){
            StdOut.println("    " + filename);
            //tao lai file
            File file= new File(filename);
            //truyen vao In
            In in= new In(file);
            while(!in.isEmpty()){
                String word = in.readString();
                if(!cp.contains(word)){
                    cp.put(word, new ST<File,Integer>());
                    cp.get(word).put(file,1);
                }
                else{
                    if(!cp.get(word).contains(file)){
                        cp.get(word).put(file,1);
                    }
                    else cp.get(word).put(file,cp.get(word).get(file) +1);
                }
            }
        }
        return cp;
    }
}
