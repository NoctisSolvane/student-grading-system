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

    @Test
    void testAddStudents_and_getAvgMarks() {
        Classroom classroom = new Classroom("11B");
        classroom.addStudent(new Student("Claude", 1, 56));
        classroom.addStudent(new Student("Effie", 2, 35.1));
        classroom.addStudent(new Student("Cassia", 3, 92.3));

        assertEquals((56 + 35.1 + 92.3) / 3, classroom.getAverageMarks());
    }

    @Test
    void testAddMultipleStudents_and_getHighestScorer() {
        Classroom classroom = new Classroom("10C");
        List<Student> newStudents = new ArrayList<>();
        newStudents.add(new Student("Rick", 1, 23.2));
        newStudents.add(new Student("Riordan", 2, 56.2));
        newStudents.add(new Student("Percy", 3, 13.2));
        newStudents.add(new Student("Annabeth", 4, 97.2));
        classroom.addMultipleStudents(newStudents);

        assertEquals("Annabeth", classroom.getHighestScorer().getName());
    }

    @Test
    void testAddStudents_and_getLowestScorer() {
        Classroom classroom = new Classroom("5A");
        classroom.addStudent(new Student("Harry", 1, 46.2));
        classroom.addStudent(new Student("Ron", 2, 42.2));
        classroom.addStudent(new Student("Hermoine", 3,93.2));

        assertEquals("Ron", classroom.getLowestScorer().getName());
    }

    @Test
    void testGetFailedStudent() {
        Classroom classroom = new Classroom("4B");
        classroom.addStudent(new Student("Denji", 1, 12.2));
        classroom.addStudent(new Student("Quanxi", 2, 67.3));
        classroom.addStudent(new Student("Power", 3, 3.2));
        classroom.addStudent(new Student("Markima", 4, 97.4));

        assertEquals(2, classroom.getFailedStudents().size());
        assertFalse(classroom.getFailedStudents().isEmpty());
    }

    @Test
    void testGetMedianMarks() {
        Classroom classroom = new Classroom("6C");
        classroom.addStudent(new Student("Yoru", 1, 43.2));
        classroom.addStudent(new Student("Little_D", 2, 65.5));
        classroom.addStudent(new Student("Nayuta", 3, 54.4));

        assertEquals(54.4, classroom.getMedianMarks());
    }

    @Test
    void testGetTop3Students_lessThan3() {
        Classroom classroom = new Classroom("5D");
        classroom.addStudent(new Student("Nobara", 1, 49.2));
        classroom.addStudent(new Student("Yuji", 2, 27.1));

        assertFalse(classroom.getTop3Students().isEmpty());
        assertEquals(2, classroom.getTop3Students().size());
        assertEquals("Yuji", classroom.getTop3Students().get(1).getName());
    }

    @Test
    void testBottom3Students_lessThan3() {
        Classroom classroom = new Classroom("7A");
        List<Student> newStudents = new ArrayList<>();
        newStudents.add(new Student("Fushiguro", 1, 78.1));
        newStudents.add(new Student("Sukuna", 2, 99.9));
        classroom.addMultipleStudents(newStudents);

        assertFalse(classroom.getBottom3Students().isEmpty());
        assertEquals("Sukuna", classroom.getBottom3Students().get(1).getName());
        assertEquals(99.9, classroom.getBottom3Students().get(1).getMarks());
    }

    @Test
    void testGetStudentsbyGrade_EmptyList() {
        Classroom classroom = new Classroom("3B");
        
        assertTrue(classroom.getStudentsByGrade('A').isEmpty());
    }

    @Test
    void testGetStudentByRoll_DuplicateRoll_ReturnsFirstMatch() {
        Classroom classroom = new Classroom("2A");
        classroom.addStudent(new Student("Rukia", 1, 74.2));
        classroom.addStudent(new Student("Subaru", 1, 63.2));

        assertEquals("Rukia", classroom.getStudentByRoll(1).getName());
    }

    @Test
    void testSortStudentsByName_caseInsensitive() {
        Classroom classroom = new Classroom("8D");
        classroom.addStudent(new Student("Rem", 1, 67.2));
        classroom.addStudent(new Student("four", 2, 64.2));
        classroom.addStudent(new Student("TRES", 3, 23.2));

        assertEquals("four", classroom.sortStudentsByName().get(0).getName());
        assertEquals("TRES", classroom.sortStudentsByName().get(2).getName());
    }

    @Test
    void testRemoveStudentByRoll_notFoundOrInvalid() {
        Classroom classroom = new Classroom("9B");
        classroom.addStudent(new Student("Zoro", 1, 53.2));

        assertFalse(classroom.removeStudentByRoll(3));
        assertTrue(classroom.removeStudentByRoll(1));
    }

    @Test
    void testSortByMarksDescending_duplicatemarks() {
        Classroom classroom = new Classroom("12B");
        List<Student> newStudents = new ArrayList<>();
        newStudents.add(new Student("Sunless", 1, 87.6));
        newStudents.add(new Student("Nephis", 2, 98.4));
        newStudents.add(new Student("Cassia", 3, 87.6));
        classroom.addMultipleStudents(newStudents);

        assertEquals("Sunless", classroom.sortByMarksDescending().get(1).getName());
        assertEquals("Cassia", classroom.sortByMarksDescending().get(2).getName());
        // Order among equal marks can be any.
    }
}