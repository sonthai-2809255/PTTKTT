import java.util.*;
import java.io.*;

// cấu trúc công việc
class Job02{
    int start,finish, propit;

    Job02(int start, int finish, int profit)
    {
        this.start=start;
        this.finish=finish;
        this.propit=profit;

    }
}

// so sánh 2 job dựa trên thời gian kết thúc (dùng cho PQ)
class Jobcomperator implements Comparator<Job02>{
    public int compare(Job02 j1, Job02 j2){
        // ưu tiên job có thời gian kết thúc sớm nhất
        return Integer.compare(j1.finish, j2.finish);
    }
}
// cấu trúc xử lý công việc bằng MinPriorityQueue
public class IntervalSchedulingPQ{
    // hàm xử lý trả về là một danh sách công việc hợp lệ
    public static List<Job02> listJobvalid(List<Job02> jobs){
        // sử dụng minPQ để tự động sắp xếp công việc theo thời gian kết thúc sớm nhất
        MinPQ<Job02> minJob = new MinPQ<>(new Jobcomperator());
        // đưa tất cả job và minPQ
        for(Job02 c: jobs) minJob.insert(c);
        //Collection.addAll(minJob, jobs);
        
        //tạo một mảng để lưu các job phù hợp
        List<Job02> finalJobs = new ArrayList<>();
        
        //khai báo một variable để token thời gian kết thúc của job đã chọn gần nhất
        int lastFinishTime = 0;

        while (!minJob.isEmpty()) {
            Job02 job = minJob.delMin(); // Lấy công việc có thời gian kết thúc sớm nhất
            if (job.start >= lastFinishTime) {
                finalJobs.add(job);
                lastFinishTime = job.finish; // Cập nhật thời gian kết thúc cuối cùng
            }
        }

        return finalJobs;
    }
    public static void display(List<Job02> jobs){
        for(Job02 c: jobs){
            System.out.println("start: "+ c.start +" finish: "+ c.finish+" profit: "+ c.propit );
        }
    }
    
    public static void main(String args[]){
    List<Job02> jobs = Arrays.asList(new Job02(0, 6, 60),
                                     new Job02(1, 4, 30),
                                     new Job02( 3, 5, 10 ),
                                     new Job02( 5, 7, 30 ),
                                     new Job02( 5, 9, 50 ),
                                     new Job02( 7, 8, 10 )   );
    List<Job02> finalList= IntervalSchedulingPQ.listJobvalid(jobs);
        display(finalList);
}}