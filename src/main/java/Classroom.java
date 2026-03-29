import java.util.ArrayList;
import java.util.List;

/**
 * A classroom identifiable by its name and containing a list of students.
 * <p>
 * Provides methods to manage students and compute class statistics. 
 * </p>
 */
public class Classroom {
    private List<Student> students;
    private String className;

    /**
     * Constructs a new {@code Classroom} with specified name.
     * 
     * @param className the name of the {@code Classroom} 
     */
    public Classroom(String className) {
        this.className = className;
        this.students = new ArrayList<>();  //Always initialized: never null
    }

    /** Returns the name of the {@code Classroom}. */
    public String getClassName() { return className; }
    /** Returns the list of students contained in the {@code Classroom}. */
    public List<Student> getStudents() {
        return new ArrayList<>(students);
    }

    /**
     * Sets the name of the classroom and handles validation.
     * <p>
     * Class name must not be empty.
     * </p>
     * 
     * @param className new name of the {@code Classroom}
     * @throws IllegalArgumentException if class name is empty
     */
    public void setClassName(String className) {
        if (className == null || className.trim().isEmpty()) {
            throw new IllegalArgumentException("Class name cannot be empty.");
        }
        this.className = className;
    }

    /**
     * Adds a new student to the existing list of students.
     * <p>
     * The {@code student} should not be {@code null}.
     * </p>
     * 
     * @param student the {@code Student} which has to be added
     * @throws IllegalArgumentException if student details are {@code null}
     */
    public void addStudent(Student student) {
        if (student == null) {
            throw new IllegalArgumentException("Student cannot be null.");
        }
        students.add(student);
    }

    /**
     * Calculates the average marks of a classroom.
     * <p>
     * Average is the sum of all marks divided by {@code Classroom.size()}.
     * </p>
     * 
     * @return the average marks as a double,
     *         or return {@code 0.0} if the list is empty
     */
    public double getAverageMarks() {
        return Student.getAvgMarks(students);
    }

    /**
     * Calculates the pass percentage of a classroom.
     * <p>
     * A student passes if their marks {@code >= 40}.
     * </p>
     * 
     * @return the pass percentage as a double,
     *             or {@code 0.0} if the list is empty
     */
    public double getPassPercentage() {
        return Student.getPassPercentage(students);
    }

    /**
     * Finds the number of students who passed in a classroom.
     * <p>
     * Passing criteria: marks {@code >= 40}.
     * </p>
     * 
     * @return the number of students who passed,
     *                    or {@code 0} if the list is empty
     */
    public int getNumberPassed() {
        return Student.countPassed(students);
    }

    /**
     * Finds the students with the highest marks from a classroom.
     * <p>
     * Handles empty list by returning null.
     * </p>
     * 
     * @return the student with the highest marks,
     *                     or {@code null} if the list is empty
     */
    public Student getHighestScorer() {
        return Student.findHighestScorer(students);
    }

    /**
     * Finds student with lowest marks from a classroom.
     * <p>
     * Handles empty list by returning null.
     * </p>
     * 
     * @return the student with the lowest marks,
     *                     or {@code null} if the list is empty
     */
    public Student getLowestScorer() {
        return Student.findLowestScorer(students);
    }

    /**
     * Generates a list of students who failed in the classroom.
     * <p>
     * Failing criteria: marks < 40.
     * </p>
     * 
     * @return a list of students who failed, 
     *           or an empty list if the list of students is empty or if no students fail
     */
    public List<Student> getFailedStudents() {
        return Student.getFailedStudents(students);
    }

    /**
     * Generates a detailed summary report for a classroom.
     * <p>
     * Includes the total number of students, average marks, pass percentage, number of passed and failed students.
     * </p>
     * 
     * @return the summary as a formatted string:
     * <pre>
     * {@code
     * Class Summary (3 students): 
     * Average Marks: 67.69
     * Pass Percentage: 66.67
     * Passed: 2 | Failed: 1
     * Highest Scorer: Urahara
     * Lowest Scorer: Ichigo}
     * </pre>               OR "No students in the class." if the list is empty
     */
    public String getSummary() {
        return Student.getClassSummary(students);
    }

    /**
     * Calculates the median marks for a classroom.
     * <p>
     * If there are even number of students, median is the average of the middle two values.
     * <br>If there are odd number of students, the median is middle value.
     * </p>
     * 
     * @return the median marks as a double,
     *             or {@code 0.0} if the list is empty
     */
    public double getMedianMarks() {
        return Student.getMedianMarks(students);
    }
    
    /**
     * Finds 3 students with the highest marks in a classroom.
     * <p>
     * If the number of students is less than 3 and greater than 0, returns those many students in a list, hence creating a {@code sublist}.
     * </p>
     * 
     * @return a list of the top 3 students sorted by marks in decreasing order
     * <ul>
     *    <li>OR all students sorted by marks in decreasing order if {@code students.size()} is less than 3 and greater than 0</li>
     *    <li>OR an empty list if the list of students is empty </li>
     * </ul>
     */
    public List<Student> getTop3Students() {
        return Student.getTop3Students(students);
    }

    /**
     * Finds 3 students with the lowest marks in a classroom.
     * <p>
     * If the number of students is less than 3 and greater than 0, returns those many students in a list, hence creating a {@code sublist}.
     * </p>
     * 
     * @return a list of the bottom 3 students sorted by marks in the ascending order 
     * <ul>
     *    <li>all students sorted by marks in ascending order if {@code students.size()} is less than 3 and greater than 0</li>
     *    <li>an empty list if the list of students is empty</li>
     * </ul>
     */
    public List<Student> getBottom3Students() {
        return Student.getBottom3Students(students);
    }

    /**
     * Returns a list of all students who received the given grade.
     * <p>
     * Grade matching is case-insensitive.
     * </p>
     * 
     * @param grade the grade to match (A, B, C, D, F)
     * @return list of matching students with the given grade,
     *                 or an empty list if the list of students is empty
     */
    public List<Student> getStudentsByGrade(char grade) {
        return Student.getStudentsByGrade(students, grade);
    }

    /**
     * Finds a student by their roll number from a classroom.
     * <p>
     * In case of matching roll numbers, the first instance of the roll number is considered.
     * </p>
     * 
     * @param roll the roll number to find ({@code > 0})
     * @return the matching student,
     *             or null if the list of students is empty or the roll number is {@code <= 0}
     */
    public Student getStudentByRoll(int roll) {
        return Student.getStudentByRoll(students, roll);
    }

    /**
     * Returns a new list of students alphabetically by name.
     * <p>
     * Sorting is case-insensitive.
     * </p>
     * 
     * @return sorted list by name (A-Z),
     *                or empty list if the list of students is empty
     */
    public List<Student> sortStudentsByName() {
        return Student.sortStudentsByName(students);
    }

    /**
     * Removes the student with the given roll number from a classroom.
     * <p>
     * roll number: {@code > 0}.
     * </p>
     * 
     * @param roll the roll number to remove
     * @return {@code true} if a student was removed, {@code false} if no match or if the list of students is empty
     */
    public boolean removeStudentByRoll(int roll) {
        return Student.removeStudentByRoll(students, roll);
    }

    /**
     * Returns a new list containing students in descending order by marks (highest first).
     * <p>
     * The sorting is stable for students with equal marks.
     * </p>
     * 
     * @return new sorted list by marks in descending order,
     *              or an empty list if input is empty
     */
    public List<Student> sortByMarksDescending() {
        return Student.sortByMarksDescending(students);
    }

    /**
     * Adds multiple students to the classroom at once.
     * <p>
     * The new {@code Student}(s) should not be null.
     * </p>
     * @param newStudents the list of students to be added to {@code students}, cannot be {@code null}
     * @throws IllegalArgumentException if the new students are {@code null}
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

    /**
     * Returns a string representation of the classroom object.
     * <p>
     * Includes the name of the class, number of students, and returns the the class summary.
     * </p>
     * 
     * @return a formatted string representing the classroom's details:
     * <pre>
     * {@code Classroom: 10A (4 students)
     * Class summary (4 students): 
     * Average Marks: 45.19
     * Pass Percentage: 50.00
     * Passed: 2 | Failed: 2
     * Highest Scorer: Vegeta
     * Lowest Scorer: Goku}
     * </pre>
     */
    @Override
    public String toString() {
        return String.format("Classroom: %s (%d students)%n%s", className, students.size(), getSummary());
    }
}