import java.util.ArrayList;
import java.util.List;
import java.util.Collections;

/**
 * A classroom identifiable by its name and containing a list of students.
 * <p>
 * Provides methods to manage students and compute class statistics. 
 * </p>
 */
public final class Classroom {
    private final List<Student> students;
    private final String className;

    /**
     * Constructs a new {@code Classroom} with specified name.
     * 
     * @param className the name of the {@code Classroom} 
     */
    public Classroom(String className) {
        this.className = validateString(className);
        this.students = new ArrayList<>();  //Always initialized: never null
    }

    private static String validateString(String className) {
        if (className == null || className.trim().isEmpty()) {
            throw new IllegalArgumentException("Class name cannot be empty.");
        }
        return className;
    }

    /** Returns the name of the {@code Classroom}. */
    public String getClassName() { return className; }
    /** Returns the list of students contained in the {@code Classroom}. */
    public List<Student> getStudents() {
        return new ArrayList<>(students);
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
     * Finds the student with highest marks from a classroom.
     * <p>
     * Handles empty list by returning {@code null}.
     * </p>
     * 
     * @param students the list of students to evaluate
     * @return the student with the highest marks,
     *                     or {@code null} if the list is empty
     */
    public Student findHighestScorer() {
        if (students.isEmpty()) return null;
        
        Student highest = students.get(0);
        for (Student s: students) {
            if (s.getMarks() > highest.getMarks()) {
                highest = s;
            }
        }
        return highest;
    }

    /**
     * Finds the student with lowest marks from a classroom.
     * <p>
     * Handles empty list by returning {@code null}.
     * </p>
     * 
     * @param students the list of students to evaluate
     * @return the student with the lowest marks,
     *                     or {@code null} if the list is empty
     */
    public Student findLowestScorer() {
        if (students.isEmpty()) return null;

        Student lowest = students.get(0);
        for (Student s: students) {
            if (s.getMarks() < lowest.getMarks()) {
                lowest = s;
            }
        }
        return lowest;
    } 

    /**
     * Counts the number of students who passed in a classroom.
     * <p>
     * Marks >= 40
     * </p>
     * 
     * @param students the list of students to evaluate
     * @return the number of students who passed,
     *                    or {@code 0} if the list is empty
     */
    public int countPassed() {
        if (students.isEmpty()) return 0;

        int count = 0;
        for (Student s: students) {
            if (s.isPassed()) {
                count++;
            }
        }
        return count;
    }

    /**
     * Calculate the pass percentage of students in a classroom.
     * <p>
     * A student is considered to have passed: marks >= 40.
     * <p>
     * Uses {@link #countPassed(List)} for finding the number of students who have passed.
     * </p>
     * 
     * @param students the list of students to evaluate
     * @return the pass percentage as a double,
     *             or {@code 0.0} if the list is empty
     */
    public double getPassPercentage() {
        if (students.isEmpty()) return 0.0;

        int passed = countPassed();
        return (passed * 100.0) / students.size();
    }

    /**
     * Calculates the median marks from a classroom. 
     * <p>
     * The original list is not modified and a new sorted list is created.
     * The median is the middle value of this modified list.
     * <p>
     * If there are even number of students, median is the average of the middle two values.
     * If there are odd number of students, the median is middle value.
     * </p>
     * 
     * @param students the list of students to evaluate
     * @return the median marks as a double,
     *             or {@code 0.0} if the list is empty
     */
    public double getMedianMarks() {
        if (students.isEmpty()) return 0.0;

        List<Double> marksList = new ArrayList<>();
        for (Student s: students) {
            marksList.add(s.getMarks());
        }
        Collections.sort(marksList);
        int n = marksList.size();
        if (n % 2 == 1) {
            return marksList.get( n / 2);
        } else {
            double mid1 = marksList.get(n / 2 - 1);
            double mid2 = marksList.get(n / 2);
            return (mid1 + mid2) / 2.0;
        }
    }

    /**
     * Generates a detailed summary report for a class of students.
     * <p>
     * Includes the total number of students, average marks, pass percentage, number of passed and failed students.
     * <p>
     * Used methods: 
     * <ul>
     *    <li>{@link #countPassed(List)}</li>
     *    <li>{@link #getPassPercentage(List)}</li>
     *    <li>{@link #getAvgMarks(List)}</li>
     *    <li>{@link #findHighestScorer(List)}</li>
     *    <li>{@link #findLowestScorer(List)}</li>
     * </ul>
     * </p>
     * 
     * @param students the list of students to evaluate
     * @return the summary report as a formatted string:
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
    public String getClassSummary() {
        if (students.isEmpty()) return "No students in the class.";

        int total = students.size();
        int passed = countPassed();
        double passPercentage = getPassPercentage();
        double avg = getAvgMarks();
        Student highest = findHighestScorer();
        Student lowest = findLowestScorer();

        String highestName = (highest != null) ? highest.getName() : "N/A";
        String lowestName = (lowest != null) ? lowest.getName() : "N/A";

        return String.format(
            "Class Summary (%d students): %n" +
            "Average Marks: %.2f%n" +
            "Pass Percentage: %.2f%%%n" +
            "Passed: %d | Failed: %d%n" +
            "Highest Scorer: %s%n" +
            "Lowest Scorer: %s",
            total, avg, passPercentage, passed, (total - passed), highestName, lowestName
        );
    }
 
    /**
     * Finds 3 students with the highest marks from a classroom.
     * <p>
     * The original list is not modified and a new sorted list is created.
     * <p>
     * If the number of students is less than 3 and greater than 0, the method returns those many students in a list, hence creating a view in a new {@code ArrayList}.
     * </p>
     * 
     * @param students the list of students to evaluate
     * @return a list of the top 3 students sorted by marks in decreasing order
     * <ul>
     *    <li>OR all students sorted by marks in decreasing order if {@code students.size()} is less than 3 and greater than 0</li>
     *    <li>OR an empty list if the list of students is empty </li>
     * </ul>
     */
    public List<Student> getTop3Students() {
        if (students.isEmpty()) return new ArrayList<>();

        List<Student> sorted = new ArrayList<>(students);
        sorted.sort((a, b) -> Double.compare(b.getMarks(), a.getMarks()));

        int limit = Math.min(3, sorted.size());
        return new ArrayList<>(sorted.subList(0, limit));
    }

    /**
     * Finds 3 students with the lowest marks from a classroom.
     * <p>
     * The original list is not modified and a new sorted list is created.
     * <p>
     * If the number of students is less than 3 and greater than 0, the method returns those many students in a list, hence creating a view in a new {@code ArrayList}.
     * </p>
     * 
     * @param students the list of students to evaluate
     * @return a list of the bottom 3 students sorted by marks in the ascending order 
     * <ul>
     *    <li>all students sorted by marks in ascending order if {@code students.size()} is less than 3 and greater than 0</li>
     *    <li>an empty list if the list of students is empty</li>
     * </ul>
     */
    public List<Student> getBottom3Students() {
        if (students.isEmpty()) return new ArrayList<>();

        List<Student> sorted = new ArrayList<>(students);
        sorted.sort((a, b) -> Double.compare(a.getMarks(), b.getMarks()));

        int limit = Math.min(3, sorted.size());
        return new ArrayList<>(sorted.subList(0, limit));
    }

    /**
     * Generates a list of students who failed from a classroom.
     * <p>
     * Failing criteria is: marks < 40.
     * </p>
     * 
     * @param students the list of students to evaluate
     * @return a list of students who failed, 
     *           or an empty list if the list of students is empty or if no students fail
     */
    public List<Student> getFailedStudents() {
        if (students.isEmpty()) return new ArrayList<>();

        List<Student> failed = new ArrayList<>();
        for (Student s: students) {
            if (!s.isPassed()) {
                failed.add(s);
            }
        }
        return failed;
    }

    /**
     * Calculates the average marks from the marks of students of a classroom.
     * <p>
     * Average is the sum of all marks divided by the {@code students.size()} -> the number of students.
     * </p>
     * 
     * @param students the list of students to evaluate
     * @return the average marks of the students as a double,
     *         or return {@code 0.0} if the list is empty
     */
    public double getAvgMarks() {
        if (students.isEmpty()) return 0.0;

        double sum = 0;
        for (Student s: students) {
            sum += s.getMarks();
        }
        return sum / students.size();
    }

    /**
     * Returns a list of all students who received the given grade.
     * <p>
     * Grade matching is case-insensitive (e.g. 'A' is the same as 'a').
     * <p>
     * The original list is not modified and an empty list is created.
     * This new list contains students which are of the given {@code char grade}.
     * </p>
     * 
     * @param students the list of students to search
     * @param grade the grade to match (A, B, C, D, F)
     * @return list of matching students with the given grade,
     *                 or an empty list if the list of students is empty
     */
    public List<Student> getStudentsByGrade(char grade) {
        if (students.isEmpty()) return new ArrayList<>();

        char upperGrade = Character.toUpperCase(grade); // Adds case-insensitivity.
        if (upperGrade != 'A' && upperGrade != 'B' && upperGrade != 'C' && upperGrade != 'D' && upperGrade != 'F') {
            return new ArrayList<>(); 
        }
        List<Student> result = new ArrayList<>();
        for (Student s: students) {
            String gradeStr = s.getGrade();
            if (Character.toUpperCase(gradeStr.charAt(0)) == upperGrade) {
                result.add(s);
            }
        }
        return result;
    }

    /**
     * Finds a student by their roll number from a classroom.
     * <p>
     * In case of matching roll numbers, the first instance of the roll number is considered.
     * </p>
     * 
     * @param students the list of students to search
     * @param roll the roll number to find, must be > 0
     * @return the matching student,
     *             or null if the list of students is empty or the roll number is {@code <= 0}
     */
    public Student getStudentByRoll(int roll) {
        if (students.isEmpty() || roll <= 0) return null;

        for (Student s: students) {
            if (s.getRollNumber() == roll) {
                return s;
            }
        }
        return null;
    }

    /**
     * Returns a new list of students sorted alphabetically by name.
     * <p>
     * Original list is not modified. Creates a new list - {@code List<Student> sorted(students)} which is a copy of the original one.
     * <p>
     * The new list is sorted in alphabetical order, while ignoring case sensitivity.
     * </p>
     * 
     * @param students list of students to modify
     * @return sorted list by name (A-Z),
     *                or empty list if the list of students is empty
     */
    public List<Student> sortStudentsByName() {
        if (students.isEmpty()) return new ArrayList<>();

        List<Student> sorted = new ArrayList<>(students);
        sorted.sort((a, b) -> a.getName().compareToIgnoreCase(b.getName()));
        return sorted;
    }

    /**
     * Removes the student with the given roll number from classroom.
     * <p>
     * roll number should be {@code > 0}.
     * </p>
     * 
     * @param students list of students to modify
     * @param roll the roll number to remove
     * @return {@code true} if a student was removed, {@code false} if no match or if the list of students is empty
     */
    public boolean removeStudentByRoll(int roll) {
        if (students.isEmpty() || roll <= 0) return false;

        return students.removeIf(s -> s.getRollNumber() == roll);
    }

    /**
     * Returns a new list containing students sorted by marks in descending order (highest first).
     * <p>
     * Original list is not modified. The sorting is stable for students with equal marks.
     * </p>
     * 
     * @param students the list of students to sort
     * @return new sorted list by marks in descending order,
     *              or an empty list if input is empty
     */
    public List<Student> sortByMarksDescending() {
        if (students.isEmpty()) return new ArrayList<>();

        List<Student> sorted = new ArrayList<>(students);
        sorted.sort((a, b) -> Double.compare(b.getMarks(), a.getMarks()));
        return sorted;
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
        return String.format("Classroom: %s (%d students)%n%s", className, students.size(), getClassSummary());
    }
}