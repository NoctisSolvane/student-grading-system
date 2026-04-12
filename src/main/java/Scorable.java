/**
 * Anything that can be graded by marks. Evaluates in accordance with the name, roll number, passing status and grade of a student.
 * <p>
 * Used to make statistics methods generic instead of hard-coding Student.
 */
public interface Scorable {
    double getMarks();
    int getRollNumber();
    String getName();
    boolean isPassed();
    String getGrade();
}