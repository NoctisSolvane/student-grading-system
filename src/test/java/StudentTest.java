import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;

public class StudentTest{

    @Test
    void testGetGrade_A() {
        Student s = new Student("Test", 1, 95.0);
        assertEquals("A", s.getGrade());
    }

    @Test
    void testGetGrade_B() {
        Student s = new Student("Test", 1, 80.0);
        assertEquals("B", s.getGrade());
    }

    @Test
    void testGetGrade_F() {
        Student s = new Student("Test", 1, 30.0);
        assertEquals("F", s.getGrade());
    }

    @Test
    void testisPassed_True() {
        Student s = new Student("Test", 1, 50.0);
        assertTrue(s.isPassed());
    }

    @Test
    void testisPassed_False() {
        Student s = new Student("Test", 1, 39.0);
        assertFalse(s.isPassed());
    }

    @Test
    void testGetAvgMarks() {
        ArrayList<Student> students = new ArrayList<>();
        students.add(new Student("Test1", 1, 45.0));
        students.add(new Student("Test2", 1, 98.0));
        students.add(new Student("Test3", 1, 83.0));
        assertEquals(( 45.0 + 98.0 + 83.0 ) / 3, Student.getAvgMarks(students), 0.001);
    }
}

