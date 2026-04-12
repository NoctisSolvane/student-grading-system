import java.util.Scanner;
import java.util.List;

public final class App {
    /**
     * Entry point of the Student Grading Application.
     * <p>
     * Prompts the user to enter details for multiple students and validates the input ({@code Student(name, rollNumber, marks)}).
     * <p>
     * Creates and stores {@code Student} objects and displays each student's details using {@link #toString()}.
     * <p>
     * Calculates class average marks using {@link #getAvgMarks(List)} and summary using formatted string.
     * </p>
     *   
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Classroom classroom = new Classroom("Main");

        System.out.println("How many students? ");
        int num = InputUtils.getValidInt(scanner, "Number of students", 1, 10);

        for (int i = 0; i < num; i++) {
            System.out.println("\n --- Student " + (i + 1) + " ---");

            String name = InputUtils.getValidString(scanner, "Name");
            int rollNumber = InputUtils.getValidInt(scanner, "Roll no.", 1, 40);
            double marks = InputUtils.getValidDouble(scanner, "Marks", 1, 100);

            Student s = new Student(name, rollNumber, marks);
            classroom.addStudent(s);

            System.out.println("\n" + s);
        }
        System.out.println("\n=== SUMMARY ===");
        System.out.println(classroom.getClassSummary());

        scanner.close();
    }
}