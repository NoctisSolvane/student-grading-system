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
     * @param marks the new marks for the students
     * @throws IllegalArgumentException if marks are not between 0 and 100.
     */
    public void setMarks(double marks) {
            if (marks < 0 || marks > 100) {
                throw new IllegalArgumentException("Marks must be between 0 and 100.");
            }
            this.marks = marks;
    }
    
    /**
     * Finds the student with highest marks from a list of students.
     * Handles null or empty list by returning null.
     * @param students the list of students.
     * @return the student with the highest marks, or null if the list is empty or null.
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
     * Finds the student with highest marks from a list of students.
     * Handles null or empty list by returning null.
     * @param students the list of students.
     * @return the student with the lowest marks, or null if the list is empty or null.
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
     * Counts the number of students who passed (marks >= 40) in the given list.
     * @param students the list of students to evaluate.
     * @return the number of students who passed. Returns 0 if the list is null or empty.
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
     * A student is considered to have passed if their marks are 40 or above.
     * @param students the list of students to evaluate.
     * @return the pass percentage as a double. Return 0.0 if the list is null or empty.
     */
    public static double getPassPercentage(List<Student> students) {
        if (students == null || students.isEmpty()) return 0.0;

        int passed = countPassed(students);
        return (passed * 100.0) / students.size();
    }

    /**
     * Calculates the median marks from a list of students. 
     * The median is the middle value when the marks are sorted.
     * If there are even number of students, median is the average of the middle two values.
     * If there are odd number of students, the median is middle value.
     * @param students the list of students to evaluate.
     * @return the median marks as a double. Returns 0.0 if the list is null or empty.
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
     * Generates a summary report for a class of students.
     * Includes the total number of students, average marks, pass percentage, number of passed and failed students, and the number of students in each grade category (A, B, C, D, F).
     * @param students the list of students to evaluate.
     * @return the summary report as a formatted string. Returns "No students in the class." if the list is null or empty.
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
     * Finds the top 3 students with the highest marks from a list of students.
     * @param students the list of students to evaluate.
     * @return a list of the top 3 students sorted by marks in decreasing order. If there are fewer than 3 students, returns all them sorted. Returns an empty list if the list is null or empty.
     */
    public static List<Student> getTop3Students(List<Student> students) {
        if (students == null || students.isEmpty()) return new ArrayList<>();

        List<Student> sorted = new ArrayList<>(students);
        sorted.sort((a, b) -> Double.compare(b.getMarks(), a.getMarks()));

        int limit = Math.min(3, sorted.size());
        return sorted.subList(0, limit);
    }

    /**
     * Finds the bottom 3 students with the lowest marks from a list of students.
     * @param students the list of students to evaluate.
     * @return a list of the bottom 3 students sorted by marks in the decreasing order. If there are fewer than 3 students, returns all them sorted. Returns an empty list if the list is null or empty.
     */
    public static List<Student> getBottom3Students(List<Student> students) {
        if (students == null || students.isEmpty()) return new ArrayList<>();

        List<Student> sorted = new ArrayList<>(students);
        sorted.sort((a, b) -> Double.compare(a.getMarks(), b.getMarks()));

        int limit = Math.min(3, sorted.size());
        return sorted.subList(0, limit);
    }

    /**
     * Generates a list of students who failed (marks < 40) from a list of students.
     * @param students the list of students to evaluate.
     * @return a list of students who failed. Returns an empty list if the list is empty, null, or if no students failed.
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
     * A student is considered to have passed if their marks are 40 or above, and failed if otherwise.
     * @return true if the student has passed, false if otherwise.
     */
    public boolean isPassed() {
        if (marks >= 40) return true;
        return false;
    }

    /**
     * Determines the grade of a student based of their marks.
     * The grading system is as follows:
     * Marks >= 90: Grade A
     * Marks >= 75 and < 90: Grade B
     * Marks >= 60 and < 75: Grade C
     * Marks >= 40 and < 60: Grade D
     * Marks < 40: Grade F
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
     * Calculates the average marks of a list of students.
     * @param students the list of students to evaluate.
     * @return the average marks of the students as a double. Return 0.0 if the list is null or empty.
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
     * Grade matching is case-insensitive (e.g. 'A' is the same as 'a').
     * @param students the list of students to search.
     * @param grade the grade to match (A, B, C, D, F).
     * @return list of matching students; empty list if none match or input is invalid.
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
            if (gradeStr.length() == 1 && Character.toUpperCase(s.getGrade().charAt(0)) == upperGrade) {
                result.add(s);
            }
        }
        return result;
    }

    /**
     * Finds a student by their roll number.
     * @param students the list of students to search.
     * @param roll the roll no. to find.
     * @return the matching student or null if not found or list is null/empty.
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
     * Returns a new list of students sorted alphabetically by name (case-insensitive).
     * Original list is not modified.
     * @param students list of students to modify.
     * @return sorted list by name (A-Z), empty list if input is empty or null.
     */
    public static List<Student> sortStudentsByName(List<Student> students) {
        if (students == null || students.isEmpty()) return new ArrayList<>();

        List<Student> sorted = new ArrayList<>(students);
        sorted.sort((a, b) -> a.getName().compareToIgnoreCase(b.getName()));
        return sorted;
    }

    /**
     * Removes the student with the given roll number from the list.
     * @param students list of students to modify.
     * @param roll the roll number to remove.
     * @return true if a student was removed, false if no match or input invalid.
     */
    public static boolean removeStudentByRoll(List<Student> students, int roll) {
        if (students == null || students.isEmpty() || roll <= 0) return false;

        return students.removeIf(s -> s.getRollNumber() == roll);
    }

    /**
     * Returns a list of students sorted by marks in descending order (highest first).
     * Original list isn't modified.
     * @param students the list of students to sort
     * @return new sorted list, empty if input is null or empty
     */
    public static List<Student> sortByMarksDescending(List<Student> students) {
        if (students == null || students.isEmpty()) return new ArrayList<>();

        List<Student> sorted = new ArrayList<>(students);
        sorted.sort((a, b) -> Double.compare(b.getMarks(), a.getMarks()));
        return sorted;
    }

    /**
     * Return a string representation of the student object, including their roll number, name, marks, grade, and pass/fail status.
     * @return a formatted string representing the student's details.
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