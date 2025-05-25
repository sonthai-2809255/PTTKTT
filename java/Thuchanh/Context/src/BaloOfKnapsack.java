/******************************************************************************
 *  Compilation:  javac BaloOfKnapsack.java
 *  Execution:    java BaloOfKnapsack N W
 *
 *  Generates an instance of the 0/1 knapsack problem with N items
 *  and maximum weight W and solves it in time and space proportional
 *  to N * W using dynamic programming.
 *
 *  For testing, the inputs are generated at random with weights between 0
 *  and W, and profits between 0 and 1000.
 *
 *  %  java BaloOfKnapsack 6 2000 
 *  item    profit  weight  take
 *  1       874     580     true
 *  2       620     1616    false
 *  3       345     1906    false
 *  4       369     1942    false
 *  5       360     50      true
 *  6       470     294     true
 *
 ******************************************************************************/
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.*;

public class BaloOfKnapsack {
    
    private  int weightdp ; // maximum weight
    private  int profitdp ;// maximum profit
    private List<item> items;
    
    
   
	@Override
	public String toString() {
		return "BaloOfKnapsack [weightdp=" + weightdp + ", profitdp=" + profitdp + "| items=" + items + "]";
	}

	public BaloOfKnapsack() {
		super();
		this.weightdp = 0;
		this.profitdp = 0;
		this.items = new ArrayList<>();
	}

	public List<item> getItems() {
		return items;
	}

	public void setItems(List<item> items) {
		this.items = items;
	}

	public int getWeightdp() {
		return weightdp;
	}

	public int getProfitdp() {
		return profitdp;
	}

	public void solve(List<item> arr, int maxweight) {
		int N = arr.size();
		int W = maxweight;
		int [] profit = new int[N+1];
		int [] weight = new int[N+1];
		// tạo bảng items 1....arr.size-1
		for(item c : arr) {
			int i= c.name;
			profit[i] = c.profit;
			weight[i] = c.weight;
		}
		// opt[n][w] = max profit of packing items 1...n with weight limit w
        // sol[n][w] = does opt solution to pack items 1...n with weight limit w include item n?
        int[][] opt = new int[N+1][W+1];
        boolean[][] sol = new boolean[N+1][W+1];
		
        for(int n = 1 ; n <= N ; n++) {
        	for(int w = 1 ; w <= W ; w++) {
        		
        		// không lấy item n
        		int option1 = opt[n-1][w];
        		
        		//lấy item n
        		int option2 = Integer.MIN_VALUE;
        		if(w >= weight[n] ) option2 = profit[n] + opt[n-1][w-weight[n]];
        		
        		//select option tốt hơn
        		opt[n][w] = Math.max(option1, option2);
        		sol[n][w] = (option2 > option1); // mục đích :
        	}
        }
        
        // detemine item nào được chọn and add to list items
  
        for(int n= N, w=W ; n > 0 ; n--) {
        	if(sol[n][w]) {
        		items.add(new item(n,profit[n],weight[n]));
        		weightdp += weight[n];
        		profitdp += profit[n];
        		w = w - weight[n];
        	}
        }
        
        
	}
	
	public static void main(String[] args) throws FileNotFoundException {

        int W = Integer.parseInt("11");   // maximum weight of knapsack
        List<item> a= new ArrayList<>();
		System.setIn(new FileInputStream(new File("items.txt")));
		String line = null;
		while((line = StdIn.readLine())!= null){
			a.add(new item(line));
		}

        BaloOfKnapsack dp = new BaloOfKnapsack();
		dp.solve(a,W);
		StdOut.print(dp);
	}
		
}