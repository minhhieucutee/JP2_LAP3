
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

class Student implements Serializable {
    private String name;
    private int age;
    private double mark;

    public Student(String name, int age, double mark) {
        this.name = name;
        this.age = age;
        this.mark = mark;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public double getMark() {
        return mark;
    }

    @Override
    public String toString() {
        return name + "\t" + age + "\t" + mark;
    }
}

public class StudentApp {
    private static final String FILENAME = "students.txt";

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        ArrayList<Student> students = new ArrayList<>();

        System.out.println("Menu");
        System.out.println("1 : Add a list Students and save to file");
        System.out.println("2 : Loading list of sTudents from a File");
        System.out.println("3 : Exit");

        while (true) {
            System.out.print("Your choice : ");
            int choice = input.nextInt();

            switch (choice) {
                case 1:
                    addStudents(students);
                    saveToFile(students);
                    break;
                case 2:
                    students = loadFromFile();
                    displayStudents(students);
                    break;
                case 3:
                    System.out.println("Goodbye!");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice");
            }
        }
    }

    private static void addStudents(ArrayList<Student> students) {
        Scanner input = new Scanner(System.in);

        while (true) {
            System.out.print("Enter name (or press Enter to stop): ");
            String name = input.nextLine().trim();

            if (name.isEmpty()) {
                break;
            }

            System.out.print("Enter age: ");
            int age = input.nextInt();

            System.out.print("Enter mark: ");
            double mark = input.nextDouble();

            input.nextLine(); // consume newline left by nextDouble()

            Student student = new Student(name, age, mark);
            students.add(student);
        }
    }

    private static void saveToFile(ArrayList<Student> students) {
        try (FileOutputStream fos = new FileOutputStream(FILENAME);
             BufferedOutputStream bos = new BufferedOutputStream(fos);
             ObjectOutputStream oos = new ObjectOutputStream(bos)) {

            oos.writeObject(students);
            System.out.println("Students saved to file " + FILENAME);
        } catch (IOException e) {
            System.out.println("Error saving students to file " + FILENAME);
            e.printStackTrace();
        }
    }

    private static ArrayList<Student> loadFromFile() {
        ArrayList<Student> students = new ArrayList<>();

        try (FileInputStream fis = new FileInputStream(FILENAME);
             BufferedInputStream bis = new BufferedInputStream(fis);
             ObjectInputStream ois = new ObjectInputStream(bis)) {

            Object obj = ois.readObject();
            if (obj instanceof ArrayList) {
                students = (ArrayList<Student>) obj;
            }
            System.out.println("Students loaded from file " + FILENAME);
        } catch (ClassNotFoundException | IOException e) {
            System.out.println("Error loading students from file " + FILENAME);
            e.printStackTrace();
        }

        return students;
    }

    private static void displayStudents(ArrayList<Student> students) {
        System.out.println("Name\tAge\tMark");
        for (Student student : students) {
            System.out.println(student);
        }
    }
}