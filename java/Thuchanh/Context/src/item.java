import edu.princeton.cs.algs4.In;

import java.util.Comparator;
import java.util.Objects;

public class item implements Comparable<item>{
	int name;
	int profit;
	int weight;
	public int getName() {
		return name;
	}
	public void setName(int name) {
		this.name = name;
	}
	public int getProfit() {
		return profit;
	}
	@Override
	public String toString() {
		return "item [name=" + name + ", profit=" + profit + ", weight=" + weight + "]";
	}
	public void setProfit(int profit) {
		this.profit = profit;
	}
	public int getWeight() {
		return weight;
	}
	public void setWeight(int weight) {
		this.weight = weight;
	}
	public item(int name, int profit, int weight) {
		super();
		this.name = name;
		this.profit = profit;
		this.weight = weight;
	}
	public item (String line){
		String fields[] = line.split("\\s+");
		this.name = Integer.parseInt(fields[0]);
		this.profit = Integer.parseInt(fields[1]);
		this.weight= Integer.parseInt(fields[2]);
	}

	@Override
	public int hashCode() {
		return Objects.hash(name, profit, weight);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		item other = (item) obj;
		return name == other.name && profit == other.profit && weight == other.weight;
	}
	@Override
	public int compareTo(item that) {
		return Integer.compare(this.weight,that.weight);
	}
	
	public static class profitOrder implements Comparator<item>{
		public int compare(item v,item w) {
			return Double.compare(v.profit,w.profit);
		}
	}
	
	public static class nameOrder implements Comparator<item>{
		public int compare(item v,item w) {
			return Double.compare(v.name,w.name);
		}
	}
	
}