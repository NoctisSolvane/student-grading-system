import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.util.List;

public class ClassroomTest {

    @Test
    void testAddStudents_and_GetCount() {
        Classroom classroom = new Classroom("10A");
        classroom.addStudent(new Student("Nephis", 1, 99.3));
        classroom.addStudent(new Student("Cassie", 2, 78.3));

        assertEquals(2, classroom.getStudents().size());
    }

    @Test
    void testGetSummary_EmptyClassroom() {
        Classroom classroom = new Classroom("EmptyClassroom");
        assertEquals("No students in the class.", classroom.getSummary());
    }

    @Test
    void testGetHighestScorer() {
        Classroom classroom = new Classroom("Math");
        classroom.addStudent(new Student("Grok", 1, 43.2));
        classroom.addStudent(new Student("Solvane", 2, 54.2));
        classroom.addStudent(new Student("Kai", 3, 12.3));
        classroom.addStudent(new Student("Maneskin", 4, 32.2));

        assertEquals("Solvane", classroom.getHighestScorer().getName());
    }

    @Test
    void testGetPassPercentage() {
        Classroom classroom = new Classroom("Science");
        classroom.addStudent(new Student("Grok", 1, 56.2));
        classroom.addStudent(new Student("Goku", 2, 34.1));

        assertEquals(50.0, classroom.getPassPercentage(), 0.001);
    }

    @Test
    void testAddMultipleStudents_NormalList() {
        Classroom classroom = new Classroom("10A");
        List<Student> newStudents = new ArrayList<>();
        newStudents.add(new Student("Grok", 1, 34.2));
        newStudents.add(new Student("Caster", 2, 43.2));

        classroom.addMultipleStudents(newStudents);

        assertEquals(2, classroom.getStudents().size());
    }

    @Test
    void testAddMultipleStudents_NullList_ThrowsException() {
        Classroom classroom = new Classroom("10A");

        assertThrows(IllegalArgumentException.class, () -> classroom.addMultipleStudents(null));
    }

    @Test
    void testAddMultipleStudents_NullEntries_ThrowsException() {
        Classroom classroom = new Classroom("10A");
        List<Student> newStudents = new ArrayList<>();
        newStudents.add(new Student("Grok", 1, 13.2));
        newStudents.add(null);

        assertThrows(IllegalArgumentException.class, () -> classroom.addMultipleStudents(newStudents));
    }
}