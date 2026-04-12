import java.util.ArrayList;
import java.util.List;
import java.util.Collections;
import java.util.Objects;

/**
 * A student in a grading system identifiable by name and roll number with an associated marks score between 0 and 100 (inclusive).
 * <br>
 * Immutable and thread-safe.
 */
public final class Student implements Scorable {
    private final String name;
    private final int rollNumber;
    private final double marks;

    /**
     * Constructs a new {@code Student} with the specified name, roll number and marks.
     * 
     * @param name the name of the student, should not be {@code null} or empty
     * @param rollNumber the roll number of the student, positive (>0)
     * @param marks the marks of the student, should be between 0 and 100
     * @throws IllegalArgumentException if {@code name} is {@code null} or blank,
     *                                     {@code marks} is outside [0,100],
     *                                  or {@code rollNumber} is not positive
     */
    public Student(String name, int rollNumber, double marks) {
        this.name = validateName(name);
        this.rollNumber = validateRoll(rollNumber);
        this.marks = validateMarks(marks);
    }

    /** Returns the student's name. */
    public String getName() { return name; }
    /** Returns the student's roll number. */
    public int getRollNumber() { return rollNumber; }
    /** Returns the student's marks. */
    public double getMarks() { return marks; }
    
    private static String validateName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be null or empty.");
        }
        return name;
    }

    private static int validateRoll(int rollNumber) {
        if (rollNumber <= 0) {
            throw new IllegalArgumentException("Roll number cannot be zero or negative.");
        }
        return rollNumber;
    }

    private static double validateMarks(double marks) {
        if (marks < 0 || marks > 100) {
            throw new IllegalArgumentException("Marks must be between 0 and 100.");
        }
        return marks;
    }

    /**
     * Returns whether the student has passed based on their marks.
     * <p>
     * A student passes if their marks are {@code >= 40}.
     * </p>
     * 
     * @return {@code true} if the student passed, {@code false} otherwise
     */
    @Override
    public boolean isPassed() {
        return marks >= 40;
    }

    /**
     * Returns the grade of a student based on their marks.
     * <p>
     * The grading system is as follows:
     * <ul>
     *   <li>Marks {@code >= 90} -> Grade A </li>
     *   <li>Marks {@code >= 75} and {@code < 90} -> Grade B</li>
     *   <li>Marks {@code >= 60} and {@code < 75} -> Grade C</li>
     *   <li>Marks {@code >= 40} and {@code < 60} -> Grade D</li>
     *   <li>Marks {@code < 40} -> Grade F</li>
     * </ul>
     * </p>
     * 
     * @return the grade of the student as a string.
     */
    @Override
    public String getGrade() {
        if (marks >= 90) return "A";
        if (marks >= 75) return "B";
        if (marks >= 60) return "C";
        if (marks >= 40) return "D";
        return "F";
    }

    /**
     * Returns a string representation of the student object.
     * <p>
     * Includes their roll number, name, marks, grade, and pass/fail status.
     * </p>
     * 
     * @return a formatted string representing the student's details:
     * <pre>
     * {@code Roll: 2 | Name: Yoruichi | Marks: 89.37 | Grade: B | Status: Passed}
     * </pre>
     */
    @Override
    public String toString() {
        return String.format("Roll: %d | Name: %s | Marks: %.2f | Grade: %s | Status: %s", rollNumber, name, marks, getGrade(),  isPassed() ? "Passed" : "Failed");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Student)) return false;
        Student other = (Student) o;
        return rollNumber == other.rollNumber;
    }

    @Override
    public int hashCode() {
        return Objects.hash(rollNumber);
    }
    
    /**
     * Finds the student with highest marks from a list of students.
     * <p>
     * Handles {@code null} or empty list by returning null.
     * </p>
     * 
     * @param students the list of students to evaluate, may be {@code null}
     * @return the student with the highest marks,
     *                     or {@code null} if the list is empty
     * @deprecated Use {@link Classroom#getHighestScorer()} instead
     */
    @Deprecated
    public static <T extends Scorable> T findHighestScorer(List<? extends T> students) {
        if (students == null || students.isEmpty()) return null;
        
        T highest = students.get(0);
        for (T s: students) {
            if (s.getMarks() > highest.getMarks()) {
                highest = s;
            }
        }
        return highest;
    }

    /**
     * Finds the student with lowest marks from a list of students.
     * <p>
     * Handles {@code null} or empty list by returning null.
     * </p>
     * 
     * @param students the list of students to evaluate, may be {@code null}
     * @return the student with the lowest marks,
     *                     or {@code null} if the list is empty
     * @deprecated Use {@link Classroom#getLowestScorer()} instead
     */
    @Deprecated
    public static <T extends Scorable> T findLowestScorer(List<? extends T> students) {
        if (students == null || students.isEmpty()) return null;

        T lowest = students.get(0);
        for (T s: students) {
            if (s.getMarks() < lowest.getMarks()) {
                lowest = s;
            }
        }
        return lowest;
    } 

    /**
     * Counts the number of students who passed in the given list.
     * <p>
     * Marks >= 40
     * </p>
     * 
     * @param students the list of students to evaluate, may be {@code null}
     * @return the number of students who passed,
     *                    or {@code 0} if the list is {@code null} or empty
     * @deprecated Use {@link Classroom#countPassed()} instead
     */
    @Deprecated
    public static <T extends Scorable> int countPassed(List<? extends T> students) {
        if (students == null || students.isEmpty()) return 0;

        int count = 0;
        for (T s: students) {
            if (s.isPassed()) {
                count++;
            }
        }
        return count;
    }

    /**
     * Calculate the pass percentage of students in the given list.
     * <p>
     * A student is considered to have passed: marks >= 40.
     * <p>
     * Uses {@link #countPassed(List)} for finding the number of students who have passed.
     * </p>
     * 
     * @param students the list of students to evaluate, may be {@code null}
     * @return the pass percentage as a double,
     *             or {@code 0.0} if the list is {@code null} or empty
     * @deprecated Use {@link Classroom#getPassPercentage()} instead
     */
    @SuppressWarnings("deprecation")                               // Since it calls a deprecated method internally
    @Deprecated
    public static <T extends Scorable> double getPassPercentage(List<? extends T> students) {
        if (students == null || students.isEmpty()) return 0.0;

        int passed = countPassed(students);
        return (passed * 100.0) / students.size();
    }

    /**
     * Calculates the median marks from a list of students. 
     * <p>
     * The original list is not modified and a new sorted list is created.
     * The median is the middle value of this modified list.
     * <p>
     * If there are even number of students, median is the average of the middle two values.
     * If there are odd number of students, the median is middle value.
     * </p>
     * 
     * @param students the list of students to evaluate, may be {@code null}
     * @return the median marks as a double,
     *             or {@code 0.0} if the list is {@code null} or empty
     * @deprecated Use {@link Classroom#getMedianMarks()} instead
     */
    @Deprecated
    public static <T extends Scorable> double getMedianMarks(List<? extends T> students) {
        if (students == null || students.isEmpty()) return 0.0;

        List<Double> marksList = new ArrayList<>();
        for (T s: students) {
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
     * @param students the list of students to evaluate, may be {@code null}
     * @return the summary report as a formatted string:
     * <pre>
     * {@code
     * Class Summary (3 students): 
     * Average Marks: 67.69
     * Pass Percentage: 66.67
     * Passed: 2 | Failed: 1
     * Highest Scorer: Urahara
     * Lowest Scorer: Ichigo}
     * </pre>               OR "No students in the class." if the list is {@code null} or empty
     * @deprecated Use {@link Classroom#getClassSummary()} instead
     */
    @SuppressWarnings("deprecation")                             // Since it calls other deprecated methods internally
    @Deprecated
    public static <T extends Scorable> String getClassSummary(List<? extends T> students) {
        if (students == null || students.isEmpty()) return "No students in the class.";

        int total = students.size();
        int passed = countPassed(students);
        double passPercentage = (passed * 100.0) / students.size();
        double avg = getAvgMarks(students);
        T highest = findHighestScorer(students);
        T lowest = findLowestScorer(students);

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
     * Finds 3 students with the highest marks from a list of students.
     * <p>
     * The original list is not modified and a new sorted list is created.
     * <p>
     * If the number of students is less than 3 and greater than 0, the method returns those many students in a list, hence creating a view in a new {@code ArrayList}.
     * </p>
     * 
     * @param students the list of students to evaluate, may be {@code null}
     * @return a list of the top 3 students sorted by marks in decreasing order
     * <ul>
     *    <li>OR all students sorted by marks in decreasing order if {@code students.size()} is less than 3 and greater than 0</li>
     *    <li>OR an empty list if the list of students is {@code null} or empty </li>
     * </ul>
     * @deprecated Use {@link Classroom#getTop3Students()} instead
     */
    @Deprecated
    public static <T extends Scorable> List<T> getTop3Students(List<? extends T> students) {
        if (students == null || students.isEmpty()) return new ArrayList<>();

        List<T> sorted = new ArrayList<>(students);
        sorted.sort((a, b) -> Double.compare(b.getMarks(), a.getMarks()));

        int limit = Math.min(3, sorted.size());
        return new ArrayList<>(sorted.subList(0, limit));
    }

    /**
     * Finds 3 students with the lowest marks from a list of students.
     * <p>
     * The original list is not modified and a new sorted list is created.
     * <p>
     * If the number of students is less than 3 and greater than 0, the method returns those many students in a list, hence creating a {@code sublist}.
     * </p>
     * 
     * @param students the list of students to evaluate, may be {@code null}
     * @return a list of the bottom 3 students sorted by marks in the ascending order 
     * <ul>
     *    <li>all students sorted by marks in ascending order if {@code students.size()} is less than 3 and greater than 0</li>
     *    <li>an empty list if the list of students is {@code null} or empty</li>
     * </ul>
     * @deprecated Use {@link Classroom#getBottom3Students()} instead
     */
    @Deprecated
    public static <T extends Scorable> List<T> getBottom3Students(List<? extends T> students) {
        if (students == null || students.isEmpty()) return new ArrayList<>();

        List<T> sorted = new ArrayList<>(students);
        sorted.sort((a, b) -> Double.compare(a.getMarks(), b.getMarks()));

        int limit = Math.min(3, sorted.size());
        return sorted.subList(0, limit);
    }

    /**
     * Generates a list of students who failed from a list.
     * <p>
     * Failing criteria is: marks < 40.
     * </p>
     * 
     * @param students the list of students to evaluate, may be {@code null}
     * @return a list of students who failed, 
     *           or an empty list if the list of students is {@code null}, empty or if no students fail
     * @deprecated Use {@link Classroom#getFailedStudents()} instead
     */
    @Deprecated
    public static <T extends Scorable> List<T> getFailedStudents(List<? extends T> students) {
        if (students == null || students.isEmpty()) return new ArrayList<>();

        List<T> failed = new ArrayList<>();
        for (T s: students) {
            if (!s.isPassed()) {
                failed.add(s);
            }
        }
        return failed;
    }

    /**
     * Calculates the average marks from the marks of the list of students.
     * <p>
     * Average is the sum of all marks divided by the {@code students.size()} -> the number of students.
     * </p>
     * 
     * @param students the list of students to evaluate, may be {@code null}
     * @return the average marks of the students as a double,
     *         or return {@code 0.0} if the list is {@code null} or empty
     * @deprecated Use {@link Classroom#getAvgMarks()} instead
     */
    @Deprecated
    public static <T extends Scorable> double getAvgMarks(List<? extends T> students) {
        if (students == null || students.isEmpty()) return 0.0;

        double sum = 0;
        for (T s: students) {
            sum += s.getMarks();
        }
        return sum / students.size();
    }

    /**
     * Returns a list of all students who received the given grade.
     * <p>
     * Grade matching is case-insensitive (e.g. 'A' is the same as 'a').
     * <p>
     * The original list is not modified and an empty list {@code List<Student> result} is created.
     * This new list contains students which are of the given {@code char grade}.
     * </p>
     * 
     * @param students the list of students to search, may be {@code null}
     * @param grade the grade to match (A, B, C, D, F)
     * @return list of matching students with the given grade,
     *                 or an empty list if the list of students is {@code null} or empty
     * @deprecated Use {@link Classroom#getStudentsByGrade(char)} instead
     */
    @Deprecated
    public static <T extends Scorable> List<T> getStudentsByGrade(List<? extends T> students, char grade) {
        if (students == null || students.isEmpty()) return new ArrayList<>();

        char upperGrade = Character.toUpperCase(grade); // Adds case-insensitivity.
        if (upperGrade != 'A' && upperGrade != 'B' && upperGrade != 'C' && upperGrade != 'D' && upperGrade != 'F') {
            return new ArrayList<>(); 
        }
        List<T> result = new ArrayList<>();
        for (T s: students) {
            String gradeStr = s.getGrade();
            if (Character.toUpperCase(gradeStr.charAt(0)) == upperGrade) {
                result.add(s);
            }
        }
        return result;
    }

    /**
     * Finds a student by their roll number from a given list of students.
     * <p>
     * In case of matching roll numbers, the first instance of the roll number is considered.
     * </p>
     * 
     * @param students the list of students to search, may be {@code null}
     * @param roll the roll number to find, must be > 0
     * @return the matching student,
     *             or null if the list of students is {@code null} or empty or the roll number is {@code <= 0}
     * @deprecated Use {@link Classroom#getStudentByRoll(int)} instead
     */
    @Deprecated
    public static <T extends Scorable> T getStudentByRoll(List<? extends T> students, int roll) {
        if (students == null || students.isEmpty() || roll <= 0) return null;

        for (T s: students) {
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
     * @param students list of students to modify, may be {@code null}
     * @return sorted list by name (A-Z),
     *                or empty list if the list of students is {@code null} or empty
     * @deprecated Use {@link Classroom#sortStudentsByName()} instead
     */
    @Deprecated
    public static <T extends Scorable> List<T> sortStudentsByName(List<? extends T> students) {
        if (students == null || students.isEmpty()) return new ArrayList<>();

        List<T> sorted = new ArrayList<>(students);
        sorted.sort((a, b) -> a.getName().compareToIgnoreCase(b.getName()));
        return sorted;
    }

    /**
     * Removes the student with the given roll number from the list of students.
     * <p>
     * roll number should be {@code > 0}.
     * </p>
     * 
     * @param students list of students to modify, may be {@code null}
     * @param roll the roll number to remove
     * @return {@code true} if a student was removed, {@code false} if no match or if the list of students is {@code null} or empty
     * @deprecated Use {@link Classroom#removeStudentByRoll(int)} instead
     */
    @Deprecated
    public static <T extends Scorable> boolean removeStudentByRoll(List<? extends T> students, int roll) {
        if (students == null || students.isEmpty() || roll <= 0) return false;

        return students.removeIf(s -> s.getRollNumber() == roll);
    }

    /**
     * Returns a new list containing students sorted by marks in descending order (highest first).
     * <p>
     * Original list is not modified. The sorting is stable for students with equal marks.
     * </p>
     * 
     * @param students the list of students to sort, may be {@code null}
     * @return new sorted list by marks in descending order,
     *              or an empty list if input is {@code null} or empty
     * @deprecated Use {@link Classroom#sortByMarksDescending()} instead
     */
    @Deprecated
    public static <T extends Scorable> List<T> sortByMarksDescending(List<? extends T> students) {
        if (students == null || students.isEmpty()) return new ArrayList<>();

        List<T> sorted = new ArrayList<>(students);
        sorted.sort((a, b) -> Double.compare(b.getMarks(), a.getMarks()));
        return sorted;
    }
}