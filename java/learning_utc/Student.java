import java.util.Scanner;
import java.util.Vector;

public class Student {
    private int code;
    private String name;
    private int age;
    private String classes;
    private float score;

    public void input() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Nhap ma: ");
        code = scanner.nextInt();
        scanner.nextLine(); // Xử lý dòng trống

        System.out.print("nhap ten: ");
        name = scanner.nextLine();

        System.out.print("nhap tuoi: ");
        age = scanner.nextInt();
        scanner.nextLine(); // Xử lý dòng trống

        System.out.print("Nhap lop: ");
        classes = scanner.nextLine();

        System.out.print("nhap diem: ");
        score = scanner.nextFloat();

        scanner.close();
    }

    public int getCode() {
        return this.code;
    }

    public int getAge() {
        return this.age;
    }

    public String getName() {
        return this.name;
    }

    public String getClasses() {
        return this.classes;
    }

    public float getScore() {
        return this.score;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public void setClass(String classes) {
        this.classes = classes;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setScore(float score) {
        this.score = score;
    }

    public void output() {
        System.out.println(this.code + " " + this.name + " " + this.age + " " + this.classes + " " + this.score);
    }

    public static void main(String[] args) {
        Student sv1 = new Student();
        sv1.input();

        Student sv2 = new Student();
        sv2.setCode(117);
        sv2.setName("nguyen xuan son");
        sv2.setAge(25);
        sv2.setClass("dtqg Viet Nam");
        sv2.setScore(3);

        sv1.output();
        sv2.output();
        System.out.print("add to vector Student!!\n");
        Vector<Student> St = new Vector<>();
        St.add(sv1);
        St.add(sv2);
        for (Student student : St) {
            student.output();
        }
    }
}
