import java.util.ArrayList;
import java.util.List;

/**
 * Represents a classroom containing a list of students.
 * Provides methods to manage students and compute class statistics.
 */
public class Classroom {
    private List<Student> students;
    private String className;

    public Classroom(String className) {
        this.className = className;
        this.students = new ArrayList<>();
    }

    public String getClassName() { return className; }
    public List<Student> getStudents() {
        return new ArrayList<>(students);
    }

    /**
     * Adds a new student to the existing list of students.
     * @param student the list of students to modify.
     * @return the modified list of students.
     * @throws IllegalArgumentException if student details are null.
     */
    public void addStudent(Student student) {
        if (student == null) {
            throw new IllegalArgumentException("Student cannot be null.");
        }
        students.add(student);
    }

    public double getAverageMarks() {
        return Student.getAvgMarks(students);
    }

    public double getPassPercentage() {
        return Student.getPassPercentage(students);
    }

    public int getNumberPassed() {
        return Student.countPassed(students);
    }

    public Student getHighestScorer() {
        return Student.findHighestScorer(students);
    }

    public Student getLowestScorer() {
        return Student.findLowestScorer(students);
    }

    public List<Student> getFailedStudents() {
        return Student.getFailedStudents(students);
    }

    public String getSummary() {
        return Student.getClassSummary(students);
    }

    /**
     * Adds multiple students to the classroom at once.
     * @param newStudents the list of students to be added.
     * @throws IllegalArgumentException if the input is null.
     */
    public void addMultipleStudents(List<Student> newStudents) {
        if (newStudents == null) {
            throw new IllegalArgumentException("Student list cannot be null.");
        }
        for (Student s: newStudents) {
            if (s == null) {
                throw new IllegalArgumentException("Individual student cannot be null.");
            }
            students.add(s);
        }
    }

    @Override
    public String toString() {
        return String.format("Classroom: %s (%d students)%n%s", className, students.size(), getSummary());
    }
}