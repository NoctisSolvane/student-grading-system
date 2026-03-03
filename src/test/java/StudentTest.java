import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

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
    void testIsPassed_True() {
        Student s = new Student("Test", 1, 50.0);
        assertTrue(s.IsPassed());
    }

    @Test
    void testIsPassed_False() {
        Student s = new Student("Test", 1, 39.0);
        assertFalse(s.IsPassed());
    }
}

