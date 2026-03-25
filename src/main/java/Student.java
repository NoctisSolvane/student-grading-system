// So fucking tired
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import java.util.Collections;

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
    
    /**
     * Sets the marks for the student and handles validation. 
     * <p> 
     * Marks are allowed only between 0 and 100.
     * </p>
     * 
     * @param marks the new marks for the students
     * @throws IllegalArgumentException if marks are not between 0 and 100
     */
    public void setMarks(double marks) {
            if (marks < 0 || marks > 100) {
                throw new IllegalArgumentException("Marks must be between 0 and 100.");
            }
            this.marks = marks;
    }
    
    /**
     * Finds the student with highest marks from a list of students.
     * <p>
     * Handles {@code null} or empty list by returning null.
     * <p>
     * Uses marks from {@link #setMarks(double)}.
     * </p>
     * 
     * @param students the list of students to evaluate, may be {@code null}
     * @param marks the marks of a student, must be between 0 and 100
     * @return the student with the highest marks,
     *                     or {@code null} if the student is empty
     */
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
     * <p>
     * Uses marks from {@link #setMarks(double)}.
     * </p>
     * 
     * @param students the list of students to evaluate, may be {@code null}
     * @param marks the marks of a student, must be between 0 and 100
     * @return the student with the lowest marks,
     *                     or {@code null} if the student is empty
     */
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
     * Passing criteria is: marks >= 40.
     * <p>
     * Uses marks from {@link #setMarks(double)}.
     * </p>
     * 
     * @param students the list of students to evaluate, may be {@code null}
     * @param marks the marks of a student, must be between 0 and 100
     * @return the number of students who passed,
     *                    or 0 if the list is {@code null} or empty
     */
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
     * Pass percentage is the percentage of students who have passed.
     * <p>
     * A student is considered to have passed if their marks are 40 or above.
     * <p>
     * Uses {@link #countPassed(List)} for finding the number of students who have passed.
     * </p>
     * 
     * @param students the list of students to evaluate, may be {@code null}
     * @return the pass percentage as a double,
     *             or 0.0 if the list is {@code null} or empty
     */
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
     *             or 0.0 if the list is {@code null} or empty
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
     * Includes the total number of students, average marks, pass percentage, number of passed and failed students, and the number of students in each grade category (A, B, C, D, F).
     * <p>
     * Used methods: 
     * {@link #countPassed(List)}
     * {@link #getPassPercentage(List)}
     * {@link #getAvgMarks(List)}
     * {@link #findHighestScorer(List)}
     * {@link #findLowestScorer(List)}
     * </p>
     * 
     * @param students the list of students to evaluate, may be {@code null}
     * @return the summary report as a formatted string:
     * <pre>{@code
     * Class Summary (3 students): 
     * Average Marks: 67.69
     * Pass Percentage: 66.67
     * Passed: 2 | Failed: 1
     * Highest Scorer: Urahara
     * Lowest Scorer: Ichigo}</pre>
     * @return "No students in the class." if the list is {@code null} or empty
     */
    public static String getClassSummary(List<Student> students) {
        if (students == null || students.isEmpty()) return "No students in the class.";

        int total = students.size();
        int passed = countPassed(students);
        double passPercentage = getPassPercentage(students);
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
     * A {@code limit(int)} is defined for a list having less than 3 students.
     * If the no. students is less than 3 and greater than 0, the method returns those many students in a list, hence creating a {@code sublist}.
     * </p>
     * 
     * @param students the list of students to evaluate, may be {@code null}
     * @return a list of the top 3 students sorted by marks in decreasing order
     * @return all students sorted by marks in decreasing order if {@code students.size()} is less than 3 and greater than 0
     * @return an empty list if the list of students is {@code null} or empty
     */
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
     * A {@code limit(int)} is defined for a list having less than 3 students.
     * If the no. of students is less than 3 and greater than 0, the method returns those many students in a list, hence creating a {@code sublist}.
     * </p>
     * 
     * @param students the list of students to evaluate, may be {@code null}
     * @return a list of the bottom 3 students sorted by marks in the decreasing order 
     * @return all students sorted by marks in decreasing order if {@code students.size()} is less than 3 and greater than 0
     * @return an empty list if the list of students is {@code null} or empty
     */
    public static List<Student> getBottom3Students(List<Student> students) {
        if (students == null || students.isEmpty()) return new ArrayList<>();

        List<Student> sorted = new ArrayList<>(students);
        sorted.sort((a, b) -> Double.compare(a.getMarks(), b.getMarks()));

        int limit = Math.min(3, sorted.size());
        return sorted.subList(0, limit);
    }

    /**
     * Generates a list of students who failed from a list of students.
     * <p>
     * Failing criteria is: marks < 40.
     * <p>
     * Use {@link #setMarks(double)}.
     * </p>
     * 
     * @param students the list of students to evaluate, may be {@code null}
     * @return a list of students who failed, 
     *           or an empty list if the list of students is {@code null}, empty or if no students fail
     */
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
     * Determines if student has passed or failed based on their marks.
     * <p>
     * A student is considered to have passed if their marks are 40 or above, and failed if otherwise.
     * <p>
     * Uses {@link #setMarks(double)}.
     * </p>
     * 
     * @param marks the marks of a student, must be between 0 and 100
     * @return {@code true} if the student has passed, {@code false} if otherwise
     */
    public boolean isPassed() {
        if (marks >= 40) return true;
        return false;
    }

    /**
     * Determines the grade of a student based on their marks.
     * <p>
     * The grading system is as follows:
     * Marks >= 90: Grade A
     * Marks >= 75 and < 90: Grade B
     * Marks >= 60 and < 75: Grade C
     * Marks >= 40 and < 60: Grade D
     * Marks < 40: Grade F
     * </p>
     * 
     * @param marks the marks of a student, must be between 0 and 100
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
     * Average is the sum of all marks divided by the {@code students.size()} -> the no. of students.
     * </p>
     * @param students the list of students to evaluate, may be {@code null}
     * @param marks the marks of a student, must be between 0 and 100
     * @return the average marks of the students as a double,
     *         or return 0.0 if the list is {@code null} or empty
     */
    public static double getAvgMarks(List<Student> students) {
        if (students == null || students.isEmpty()) return 0.0;

        double sum = 0;
        for (Student s: students) {
            sum += s.marks;
        }
        return sum / students.size();
    }

    /**
     * Returns a list of all students who received the given grade.
     * <p>
     * Grade matching is case-insensitive (e.g. 'A' is the same as 'a').
     * A new {@code upperGrade(char)} is created for removing case-insensitivity.
     * <p>
     * The original list is not modified and an emtpy list {@code List<Student> result} is created.
     * This new list contains students which satisfy the following conditions:
     * <pre>
     * {@code if (s.getGrade().length == 1 && Charachter.toUpperCase(s.getGrade().charAt(0)) == Charachter.toUpperCase(char grade))}
     * </pre>
     * </p>
     * 
     * @param students the list of students to search, may be {@code null}
     * @param grade the grade to match (A, B, C, D, F).
     * @return list of matching students with the given grade,
     *                 or an empty list if the list of students is {@code null} or empty
     */
    public static List<Student> getStudentsByGrade(List<Student> students, char grade) {
        if (students == null || students.isEmpty()) return new ArrayList<>();

        char upperGrade = Character.toUpperCase(grade); // Removes case-insensitivity.
        if (upperGrade != 'A' && upperGrade != 'B' && upperGrade != 'C' && upperGrade != 'D' && upperGrade != 'F') {
            return new ArrayList<>(); 
        }
        List<Student> result = new ArrayList<>();
        for (Student s: students) {
            String gradeStr = s.getGrade();
            if (gradeStr.length() == 1 && Character.toUpperCase(gradeStr.charAt(0)) == upperGrade) {
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
     * @param students the list of students to search, may be {@code null}
     * @param roll the roll noumber to find, must be > 0
     * @return the matching student,
     *             or null if the list of students is {@code null} or empty or the roll number is <= 0
     */
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
     * The new list is sorted in alphabetical order, while ignoring case sensitivity, using the following:
     * <pre>
     * {@code sorted.sort((a,b) -> a.getName().compareToIgnoreCase(b.getName()))}
     * </pre>
     * </p>
     * 
     * @param students list of students to modify, may be {@code null}
     * @return sorted list by name (A-Z),
     *                or empty list if the list of students is {@code null} or empty
     */
    public static List<Student> sortStudentsByName(List<Student> students) {
        if (students == null || students.isEmpty()) return new ArrayList<>();

        List<Student> sorted = new ArrayList<>(students);
        sorted.sort((a, b) -> a.getName().compareToIgnoreCase(b.getName()));
        return sorted;
    }

    /**
     * Removes the student with the given roll number from the list of students.
     * <p>
     * Returns the modified list as:
     * <pre> 
     * {@code return students.removeIf(s -> s.getRollNumber() == roll)}
     * </pre>
     * </p>
     * 
     * @param students list of students to modify, may be {@code null}
     * @param roll the roll number to remove, must be > 0
     * @return {@code true} if a student was removed, {@code false} if no match or if the list of students is {@code null} or empty
     */
    public static boolean removeStudentByRoll(List<Student> students, int roll) {
        if (students == null || students.isEmpty() || roll <= 0) return false;

        return students.removeIf(s -> s.getRollNumber() == roll);
    }

    /**
     * Returns a new list of students containing the students sorted by marks in descending order (highest first).
     * <p>
     * Original list is not modified. The sorting is stable for students with equal marks.
     * </p>
     * 
     * @param students the list of students to sort, may be {@code null}
     * @return new sorted list by marks in descending order,
     *              or an empty list if input is {@code null} or empty
     */
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
     * @return a formatted string representing the student's details:
     * <pre>
     * {@code Roll: 2 | Name: Yoruichi | Marks: 89.37 | Grade: B | Status: Passed}
     * </pre>
     */
    @Override
    public String toString() {
        return String.format("Roll: %d | Name: %s | Marks: %.2f | Grade: %s | Status: %s", rollNumber, name, marks, getGrade(),  isPassed() ? "Passed" : "Failed");
    }

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