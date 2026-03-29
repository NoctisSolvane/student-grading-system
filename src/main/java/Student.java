import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import java.util.Collections;

/**
 * A student in a grading system identifiable by name and roll number with an associated marks score between 0 and 100 (inclusive).
 */
public class Student {
    private String name;
    private int rollNumber;
    private double marks;

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
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be empty.");
        }
        if (marks < 0 || marks > 100) {
            throw new IllegalArgumentException("Marks must be between 0 and 100.");
        }
        if (rollNumber <= 0) {
            throw new IllegalArgumentException("Roll number cannot be zero or negative.");
        }
        this.name = name;
        this.marks = marks;
        this.rollNumber = rollNumber;
    }

    /** Returns the student's name. */
    public String getName() { return name; }
    /** Returns the student's roll number. */
    public int getRollNumber() { return rollNumber; }
    /** Returns the student's marks. */
    public double getMarks() { return marks; }
    
    /**
     * Sets the marks for the student and handles validation. 
     * <p> 
     * Marks are allowed only between 0 and 100.
     * </p>
     * 
     * @param marks the new marks for the {@code Student}
     * @throws IllegalArgumentException if marks are not between 0 and 100
     */
    public void setMarks(double marks) {
            if (marks < 0 || marks > 100) {
                throw new IllegalArgumentException("Marks must be between 0 and 100.");
            }
            this.marks = marks;
    }

    /**
     * Sets the name for the student and handles validation.
     * <p>
     * Name must not be empty.
     * </p>
     * 
     * @param name the new name for the {@code Student} 
     * @throws IllegalArgumentException if name is empty
     */
    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be empty.");
        }
        this.name = name;
    }

    /**
     * Sets the roll number for the student and handles validation.
     * <p>
     * Roll number must be {@code > 0}.
     * </p>
     * 
     * @param rollNumber the new roll number for the {@code Student}
     * @throws IllegalArgumentException if roll {@code <= 0}
     */
    public void setRollNumber(int rollNumber) {
        if (rollNumber <= 0) {
            throw new IllegalArgumentException("Roll number cannot be zero or negative.");
        }
        this.rollNumber = rollNumber;
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
    public static Student findHighestScorer(List<Student> students) {
        if (students == null || students.isEmpty()) return null;
        
        Student highest = students.get(0);
        for (Student s: students) {
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
    public static Student findLowestScorer(List<Student> students) {
        if (students == null || students.isEmpty()) return null;

        Student lowest = students.get(0);
        for (Student s: students) {
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
     * @deprecated Use {@link Classroom#getNumberPassed()} instead
     */
    @Deprecated
    public static int countPassed(List<Student> students) {
        if (students == null || students.isEmpty()) return 0;

        int count = 0;
        for (Student s: students) {
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
    public static double getPassPercentage(List<Student> students) {
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
     */
    public static double getMedianMarks(List<Student> students) {
        if (students == null || students.isEmpty()) return 0.0;

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
     * @deprecated Use {@link Classroom#getSummary()} instead
     */
    @SuppressWarnings("deprecation")                             // Since it calls other deprecated methods internally
    @Deprecated
    public static String getClassSummary(List<Student> students) {
        if (students == null || students.isEmpty()) return "No students in the class.";

        int total = students.size();
        int passed = countPassed(students);
        double passPercentage = (passed * 100.0) / students.size();
        double avg = getAvgMarks(students);
        Student highest = findHighestScorer(students);
        Student lowest = findLowestScorer(students);

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
     * If the number of students is less than 3 and greater than 0, the method returns those many students in a list, hence creating a {@code sublist}.
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
    public static List<Student> getTop3Students(List<Student> students) {
        if (students == null || students.isEmpty()) return new ArrayList<>();

        List<Student> sorted = new ArrayList<>(students);
        sorted.sort((a, b) -> Double.compare(b.getMarks(), a.getMarks()));

        int limit = Math.min(3, sorted.size());
        return sorted.subList(0, limit);
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
    public static List<Student> getBottom3Students(List<Student> students) {
        if (students == null || students.isEmpty()) return new ArrayList<>();

        List<Student> sorted = new ArrayList<>(students);
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
    public static List<Student> getFailedStudents(List<Student> students) {
        if (students == null || students.isEmpty()) return new ArrayList<>();

        List<Student> failed = new ArrayList<>();
        for (Student s: students) {
            if (!s.isPassed()) {
                failed.add(s);
            }
        }
        return failed;
    }

    /**
     * Returns whether the student has passed based on their marks.
     * <p>
     * A student passes if their marks are {@code >= 40}.
     * </p>
     * 
     * @return {@code true} if the student passed, {@code false} otherwise
     */
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
    public String getGrade() {
        if (marks >= 90) return "A";
        if (marks >= 75) return "B";
        if (marks >= 60) return "C";
        if (marks >= 40) return "D";
        return "F";
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
     * @deprecated Use {@link Classroom#getAverageMarks()} instead
     */
    @Deprecated
    public static double getAvgMarks(List<Student> students) {
        if (students == null || students.isEmpty()) return 0.0;

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
    public static List<Student> getStudentsByGrade(List<Student> students, char grade) {
        if (students == null || students.isEmpty()) return new ArrayList<>();

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
    public static Student getStudentByRoll(List<Student> students, int roll) {
        if (students == null || students.isEmpty() || roll <= 0) return null;

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
     * @param students list of students to modify, may be {@code null}
     * @return sorted list by name (A-Z),
     *                or empty list if the list of students is {@code null} or empty
     * @deprecated Use {@link Classroom#sortStudentsByName()} instead
     */
    @Deprecated
    public static List<Student> sortStudentsByName(List<Student> students) {
        if (students == null || students.isEmpty()) return new ArrayList<>();

        List<Student> sorted = new ArrayList<>(students);
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
    public static boolean removeStudentByRoll(List<Student> students, int roll) {
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
    public static List<Student> sortByMarksDescending(List<Student> students) {
        if (students == null || students.isEmpty()) return new ArrayList<>();

        List<Student> sorted = new ArrayList<>(students);
        sorted.sort((a, b) -> Double.compare(b.getMarks(), a.getMarks()));
        return sorted;
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
        List<Student> students = new ArrayList<>();

        System.out.println("How many students? ");
        int num = getValidInt(scanner, "Number of students", 1, 10);

        for (int i = 0; i < num; i++) {
            System.out.println("\n --- Student " + (i + 1) + " ---");

            String name = getValidString(scanner, "Name");
            int rollNumber = getValidInt(scanner, "Roll no.", 1, 40);
            double marks = getValidDouble(scanner, "Marks", 1, 100);

            Student s = new Student(name, rollNumber, marks);
            students.add(s);

            System.out.println("\n" + s);
        }
        System.out.println("\n=== SUMMARY ===");
        for (int i = 0; i < students.size(); i++) {
            Student s = students.get(i);
            System.out.printf("Student %d: %s%n", (i + 1), s);
        }
        double avgMarks = getAvgMarks(students);
        System.out.println("\n Average marks of the class: " + avgMarks);
        scanner.close();
    }

    /**
     * Validates integer input on scanner and returns the scanner if invalid.
     * <p>
     * The value of the integer should be between the given {@code int min} and {@code int max}.
     * <p> 
     * Repeatedly prompts until a valid integer within the specified range is entered.
     * </p>
     * 
     * @param sc the text {@code Scanner sc} which parses primitive types and breaks its input into tokens
     * @param prompt the additional {@code String} that is provided by the user along with the integer input
     * @param min the minimum value of the integer input (provided by the user)
     * @param max the maximum value of the integer input (provided by the user)
     * @return the integer input of {@code Scanner sc} ({@code int val}) if it is between {@code min} and {@code max}
     * <ul>
     *    <li>"Must be between {@code int min} and {@code int max}. Try again." and another {@code Scanner sc} if the input is an invalid integer</li>
     *    <li>"Invalid input. Input must be a number" and another {@code Scanner sc} if the input is not an integer</li>
     * </ul>
     */    
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
                System.out.println("Invalid input. Input must be number.\n");
                sc.nextLine();
            }
        }
    }
   
    /**
     * Validates double (decimal) input on scanner, and returns the scanner if invalid.
     * <p>
     * The value of the double should be between the given {@code double min} and {@code double max}.
     * <p>
     * Repeatedly prompts until a valid double within the specified range is entered.
     * </p>
     * 
     * @param sc the text {@code Scanner sc} which parses primitive types and breaks its input as tokens
     * @param prompt the additional {@code String} that is provided by the user along with the double input
     * @param min the minimum value of the double input (provided by the user)
     * @param max the maximum value of the double input (provided by the user)
     * @return the double input of {@code Scanner sc} ({@code double val}) if it is between {@code min} and {@code max}
     * <ul>
     *    <li>"Must be between {@code double min} and {@code double max}. Try again." and another {@code Scanner sc} if the input is an invalid double</li>
     *    <li>"Invalid input. Input must be a number." and another {@code Scanner sc} if the input is not a double</li>
     * </ul>
     */
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
                System.out.println("Invalid input. Input must be a number.\n");
                sc.nextLine();
            }
        }
    }
   
    /**
     * Validates string input on scanner, and returns the scanner if invalid.
     * <p>
     * The input of {@code Scanner sc} should not be empty.
     * <p>
     * Repeatedly prompts until a valid string is entered.
     * </p>
     *  
     * @param sc the text {@code Scanner sc} which parses primitive types and breaks its input as tokens
     * @param prompt the additional {@code String} that is provided by the user along with the string input
     * @return the string input of {@code Scanner sc} ({@code String input}) if the input is not empty
     * <ul>
     *    <li>"Input cannot be empty." if {@code input.isEmpty()}</li>
     * </ul>
     */
    private static String getValidString(Scanner sc, String prompt) {
        while (true) {
            System.out.print(prompt + ": ");
            String input = sc.nextLine().trim();
            if (!input.isEmpty()) return input;
            System.out.println("Input cannot be empty.");
        }
    }
}