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
    public List<Student> getStudents() { return students; }

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

    @Override
    public String toString() {
        return String.format("Classroom: %s (%d students)%n%s", className, students.size(), getSummary());
    }
}