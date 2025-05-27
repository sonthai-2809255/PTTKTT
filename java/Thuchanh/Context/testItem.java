import java.io.*;

public class testItem {

	public testItem() {
		super();
	}
	
	public static void main(String[] args) {
		item a= new item(1,23,77);
		item b = new item("2 23 2");
		StdOut.println(b);
		StdOut.print(a);
	}
}
