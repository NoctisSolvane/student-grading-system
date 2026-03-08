import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.util.List;

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
        List<Student> students = new ArrayList<>();
        students.add(new Student("Test1", 1, 45.0));
        students.add(new Student("Test2", 1, 98.0));
        students.add(new Student("Test3", 1, 83.0));
        assertEquals(( 45.0 + 98.0 + 83.0 ) / 3, Student.getAvgMarks(students), 0.001);
    }

    @Test
    void testGetMarks_40() {
        Student s = new Student("Test", 1,  40.0);
        assertEquals("D", s.getGrade());
        assertTrue(s.isPassed());
    }

    @Test
    void testGetMarks_0() {
        Student s = new Student("Test", 1, 0.0);
        assertEquals("F", s.getGrade());
        assertFalse(s.isPassed());
    }

    @Test
    void testGetMarks_100() {
        Student s = new Student("Test", 1, 100.0);
        assertEquals("A", s.getGrade());
        assertTrue(s.isPassed());
    }

    @Test
    void testGetMarks_Boundary() {
        Student s = new Student("Test", 1, 39.99);
        assertEquals("F", s.getGrade());
        assertFalse(s.isPassed());
    }

    @Test
    void testGetMarks_Boundary2() {
        Student s = new Student("Test", 1, 99.99);
        assertEquals("A", s.getGrade());
        assertTrue(s.isPassed());
    }

    @Test
    void testGetMarks_Boundary3() {
        Student s = new Student("Test", 1, 0.01);
        assertEquals("F", s.getGrade());
        assertFalse(s.isPassed());
    }

    @Test
    void testSetMarks_Invalid_throwsException() {
        assertThrows(IllegalArgumentException.class, () -> new Student("Test", 1, -1.0));
    }

    @Test
    void testEmptyStudentList_AvgMarks() {
        List<Student> students = new ArrayList<>();
        assertEquals(0, Student.getAvgMarks(students), 0.001);
    }

    @Test
    void testEmptyStudentList_HighestScorer() {
        List<Student> students = new ArrayList<>();
        assertNull(Student.findHighestScorer(students));
    }

    @Test
    void testFind_HighestScorer() {
        List<Student> students = new ArrayList<>();
        
        students.add(new Student("Grok", 1, 1.0));
        students.add(new Student("Noctis", 2, 99.99));
        students.add(new Student("Cassia", 3, 99.98));

        Student highest = Student.findHighestScorer(students);
        assertNotNull(highest);
        assertEquals("Noctis", highest.getName());
        assertEquals(99.99, highest.getMarks(), 0.001);

    }

    @Test
    void testFind_LowestScorer_in_EmptyList() {
        List<Student> students = new ArrayList<>();
        assertNull(Student.findLowestScorer(students));
    }

    @Test
    void testFind_LowestScorer() {
        List<Student> students = new ArrayList<>();
        
        students.add(new Student("Grok", 1, 0.001));
        students.add(new Student("Noctis", 2, 0.01));
        students.add(new Student("Nephis", 3, 100.0));

        Student lowest = Student.findLowestScorer(students);
        assertNotNull(lowest);
        assertEquals("Grok", lowest.getName());
        assertEquals(0.001, lowest.getMarks(), 0.001);
    }

    @Test
    void testCountPassed_EmptyList() {
        List<Student> students = new ArrayList<>();
        assertEquals(0, Student.countPassed(students));
    }

    @Test
    void testCountPassed() {
        List<Student> students = new ArrayList<>();

        students.add(new Student("Grok", 1, 31.0));
        students.add(new Student("Noctis", 2, 99.0));
        students.add(new Student("Sunless", 67, 39.9));

        assertEquals(1, Student.countPassed(students));
    }

    @Test
    void testCountPassed_AllFailed() {
        List<Student> students = new ArrayList<>();

        students.add(new Student("Grok", 1, 0.0));
        students.add(new Student("Kaneki", 2, 27.0));
        students.add(new Student("Caster", 3, 39.99));

        assertEquals(0, Student.countPassed(students));
    }
}

