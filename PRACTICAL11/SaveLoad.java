
import java.io.*;
class Student implements Serializable {
    int id;
    String name;
    transient String password;

    Student(int id, String name, String password) {
        this.id = id;
        this.name = name;
        this.password = password;
    }
}
public class SaveLoad {
    public static void main(String[] args) {
        try {
            Student[] s1 = {
                new Student(1, "Tanvi", "1234"),
                new Student(2, "Happy", "5678")
            };
            ObjectOutputStream out =
                new ObjectOutputStream(new FileOutputStream("student.dat"));
            out.writeObject(s1);
            out.close();
            ObjectInputStream in =
                new ObjectInputStream(new FileInputStream("student.dat"));
            Student[] s2 = (Student[]) in.readObject();
            in.close();
            System.out.println("Student Details After Loading:");
            for (Student s : s2) {
                System.out.println("ID: " + s.id);
                System.out.println("Name: " + s.name);
                System.out.println("Password: " + s.password);
                System.out.println();
            }

        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
