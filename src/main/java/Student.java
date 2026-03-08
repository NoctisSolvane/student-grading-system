// So fucking tired
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class Student {
    private String name;
    private int rollNumber;
    private double marks;

    public Student(String name, int rollNumber, double marks) {
        if (marks < 0 || marks > 100) {
            throw new IllegalArgumentException("Marks must be between 0 and 100.");
        }
        this.name = name;
        this.rollNumber = rollNumber;
        this.marks = marks;
        }

    public String getName() { return name; }
    public int getRollNumber() { return rollNumber; }
    public double getMarks() { return marks; }

    public void setMarks(double marks) {
            if (marks < 0 || marks > 100) {
                throw new IllegalArgumentException("Marks must be between 0 and 100.");
            }
            this.marks = marks;
    }

    public static Student findHighestScorer(List<Student> Students) {
        if (Students == null || Students.isEmpty()) return null;
        
        Student highest = Students.get(0);
        for (Student s: Students) {
            if (s.getMarks() > highest.getMarks()) {
                highest = s;
            }
        }
        return highest;
    }

    public static Student findLowestScorer(List<Student> Students) {
        if (Students == null || Students.isEmpty()) return null;

        Student lowest = Students.get(0);
        for (Student s: Students) {
            if (s.getMarks() < lowest.getMarks()) {
                lowest = s;
            }
        }
        return lowest;
    } 

    public static int countPassed(List<Student> Students) {
        if (Students == null || Students.isEmpty()) return 0;

        int count = 0;
        for (Student s: Students) {
            if (s.isPassed()) {
                count++;
            }
        }
        return count;
    }

    public static double getPassPercentage(List<Student> Students) {
        if (Students == null || Students.isEmpty()) return 0.0;

        int passed = countPassed(Students);
        return (passed * 100.0) / Students.size();
    }

    public boolean isPassed() {
        if (marks >= 40) return true;
        return false;
    }

    public String getGrade() {
        if (marks >= 90) return "A";
        if (marks >= 75) return "B";
        if (marks >= 60) return "C";
        if (marks >= 40) return "D";
        return "F";
    }

    public static double getAvgMarks(List<Student> Students) {
        if (Students.isEmpty()) return 0;

        double sum = 0;
        for (Student s: Students) {
            sum += s.marks;
        }
        return sum / Students.size();
    }

    @Override
    public String toString() {
        return String.format("Roll: %d | Name: %s | Marks: %.2f | Grade: %s | Status: %s", rollNumber, name, marks, getGrade(),  isPassed() ? "Passed" : "Failed");
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Student> Students = new ArrayList<>();

        System.out.println("How many students? ");
        int num = getValidInt(scanner, "Number of students", 1, 10);

        for (int i = 0; i < num; i++) {
            System.out.println("\n --- Student " + (i + 1) + " ---");

            String name = getValidString(scanner, "Name");
            int rollNumber = getValidInt(scanner, "Roll no.", 1, 40);
            double marks = getValidDouble(scanner, "Marks", 1, 100);

            Student s = new Student(name, rollNumber, marks);
            Students.add(s);

            System.out.println("\n" + s);
        }
        System.out.println("\n=== SUMMARY ===");
        for (int i = 0; i < Students.size(); i++) {
            Student s = Students.get(i);
            System.out.printf("Student %d: %s%n", (i + 1), s);
        }
        double avgMarks = getAvgMarks(Students);
        System.out.println("\n Average marks of the class: " + avgMarks);
        scanner.close();
    }

        private static int getValidInt(Scanner sc, String prompt, int min, int max) {
        while (true) {
            try {
                System.out.print(prompt + ": ");
                int val = sc.nextInt();
                sc.nextLine();
                if (val < min || val > max) {
                    System.out.printf("Must be between %d and %d. Try again. \n", min, max);
                    continue;
                }
                return val;
            }
            catch (Exception e) {
                System.out.println("That's not a number, idiot. Try again. \n");
                sc.nextLine();
            }
        }
    }
   
    private static double getValidDouble(Scanner sc, String prompt, double min, double max) {
        while (true) {
            try {
                System.out.print(prompt + ": ");
                double val = sc.nextDouble();
                sc.nextLine();
                if (val < min || val > max) {
                    System.out.printf("Must be between %.1f and %.1f. Try again. \n", min, max);
                    continue;
                }
                return val;
            }
            catch (Exception e) {
                System.out.println("That's not a number, idiot. Try again. \n");
                sc.nextLine();
            }
        }
    }
   
    private static String getValidString(Scanner sc, String prompt) {
        while (true) {
            System.out.print(prompt + ": ");
            String input = sc.nextLine().trim();
            if (!input.isEmpty()) return input;
            System.out.println("Don't leave it empty, dumbass.");
        }
    }
}