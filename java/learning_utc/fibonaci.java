
import java.util.Scanner;

public class fibonaci {
    public static long fibo(long n) {
        if (n == 0)
            return 0;
        if (n == 1)
            return 1;
        return fibo(n - 1) + fibo(n - 2);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("nhap n= ");
        long n = scanner.nextLong();
        System.out.println("gia tri fibo phan tu thu n= " + fibo(n));
    }
}