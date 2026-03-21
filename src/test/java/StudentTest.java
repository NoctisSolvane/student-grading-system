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
    void testCountPassed_MixedPassFail_ReturnCorrectCount() {
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

    @Test
    void testgetPassPercentage_EmptyList() {
        List<Student> students = new ArrayList<>();
        assertEquals(0.0, Student.getPassPercentage(students), 0.001);
    }

    @Test
    void testgetPassPercentage_NullList() {
        assertEquals(0.0, Student.getPassPercentage(null), 0.001);
    }

    @Test
    void testgetPassPercentage_NormalList() {
        List<Student> students = new ArrayList<>();

        students.add(new Student("GPT", 1, 13.9));
        students.add(new Student("Kai", 2, 97.8));
        students.add(new Student("Effie", 3, 69.0));

        assertEquals(2, Student.countPassed(students));
        assertEquals(66.666, Student.getPassPercentage(students), 0.001);
    }

    @Test
    void testgetPassPercentage_AllPassed() {
        List<Student> students = new ArrayList<>();

        students.add(new Student("Nephis", 1, 99.0));
        students.add(new Student("Sunless", 2, 98.9));
        students.add(new Student("Noctis", 3, 87.7));

        assertEquals(3, Student.countPassed(students));
        assertEquals(100.0, Student.getPassPercentage(students), 0.001);
    }

    @Test
    void testgetPassPercentage_AllFailed() {
        List<Student> students = new ArrayList<>();

        students.add(new Student("Grok", 1, 0.0));
        students.add(new Student("Ichigo", 2, 18.9));
        students.add(new Student("Caster", 3, 39.99));

        assertEquals(0, Student.countPassed(students));
        assertEquals(0.0, Student.getPassPercentage(students), 0.001);
    }

    @Test
    void testgetMedianMarks_EmptyLIst() {
        List<Student> students = new ArrayList<>();
        assertEquals(0.0, Student.getMedianMarks(students), 0.001);
    }

    @Test
    void testgetMedianMarks_NullList() {
        assertEquals(0.0, Student.getMedianMarks(null), 0.001);
    }

    @Test
    void testgetMedianMarks_OddStudents() {
        List<Student> students = new ArrayList<>();

        students.add(new Student("Saiki", 1, 45.0));
        students.add(new Student("Ichigo", 2, 0.0));
        students.add(new Student("Goku", 2, 39.0));

        assertEquals(39.0, Student.getMedianMarks(students), 0.001);
    }

    @Test
    void testgetMedianMarks_EvenStudents() {
        List<Student> students = new ArrayList<>();

        students.add(new Student("Saiki", 1, 45.0));
        students.add(new Student("Ichigo", 2, 89.0));
        students.add(new Student("TokyoSims", 3, 69.0));
        students.add(new Student("SoulDevourer", 4, 56.0));

        assertEquals((56.0 + 69.0) / 2, Student.getMedianMarks(students), 0.001);
    }

    @Test
    void testgetMedianMarks_SingleStudent() {
        List<Student> students = new ArrayList<>();

        students.add(new Student("Deku", 1, 52.0));

        assertEquals(52.0, Student.getMedianMarks(students), 0.001);
    }

    @Test
    void testgetClassSummary_EmptyList() {
        assertEquals("No students in the class.", Student.getClassSummary(new ArrayList<>()));
    }

    @Test
    void testgetClassSummary_NullList() {
        assertEquals("No students in the class.", Student.getClassSummary(null));
    }

    @Test
    void testgetClassSummary_NormalList() {
        List<Student> students = new ArrayList<>();

        students.add(new Student("Cassie", 1, 90.0));
        students.add(new Student("Grok", 2, 20.0));
        students.add(new Student("Effie", 3, 40.0));

        String summary = Student.getClassSummary(students);
        assertTrue(summary.contains("Class Summary (3 students)"));
        assertTrue(summary.contains("Average Marks: 50.00"));
        assertTrue(summary.contains("Pass Percentage: 66.67"));
        assertTrue(summary.contains("Passed: 2 | Failed: 1"));
        assertTrue(summary.contains("Highest Scorer: Cassie"));
        assertTrue(summary.contains("Lowest Scorer: Grok"));
    }

    @Test
    void testGetTop3Students_NormalList() {
        List<Student> students = new ArrayList<>();

        students.add(new Student("Luffy", 1, 87.0));
        students.add(new Student("Zoro", 2, 39.8));
        students.add(new Student("Nami", 3, 67.0));
        students.add(new Student("Chopper", 4, 54.0));

        List<Student> top3 = Student.getTop3Students(students);
        assertEquals(3, top3.size());
        assertEquals("Luffy", top3.get(0).getName());
        assertEquals("Nami", top3.get(1).getName());
        assertEquals("Chopper", top3.get(2).getName());
    }

    @Test
    void testGetTop3Students_lessThan3() {
        List<Student> students = new ArrayList<>();

        students.add(new Student("Luffy", 1, 31.0));
        students.add(new Student("Zoro", 2, 49.0));

        List<Student> top3 = Student.getTop3Students(students);
        assertEquals(2, top3.size());
        assertEquals("Zoro", top3.get(0).getName());
    }

    @Test
    void testGetTop3Students_EmptyList() {
        List<Student> students = new ArrayList<>();
        List<Student> top3 = Student.getTop3Students(students);
        assertTrue(top3.isEmpty());
    }
    
    @Test
    void testGetBottom3Students_NormalList() {
        List<Student> students = new ArrayList<>();

        students.add(new Student("Luffy", 1, 87.0));
        students.add(new Student("Zoro", 2, 63.0));
        students.add(new Student("Nami", 3, 2.0));
        students.add(new Student("Chopper", 4, 52.0));

        List<Student> bottom3 = Student.getBottom3Students(students);
        assertEquals(3, bottom3.size());
        assertEquals("Nami", bottom3.get(0).getName());
        assertEquals("Chopper", bottom3.get(1).getName());
    }
    
    @Test
    void testGetBottom3Students_LessThan3() {
        List<Student> students = new ArrayList<>();

        students.add(new Student("Luffy",2 , 53.4));
        students.add(new Student("Xdrake", 1, 23.0));

        List<Student> bottom3 = Student.getBottom3Students(students);
        assertEquals(2, bottom3.size());
        assertEquals("Luffy", bottom3.get(1).getName());
    }
    
    @Test
    void testGetBottom3Students_NullList() {
        assertTrue(Student.getBottom3Students(null).isEmpty());
    }

    @Test
    void testGetFailedStudents_EmtpyList() {
        List<Student> students = new ArrayList<>();
        List<Student> failed = Student.getFailedStudents(students);
        assertTrue(failed.isEmpty());
    }

    @Test
    void testGetFailedStudents_NullList() {
        List<Student> failed = Student.getFailedStudents(null);
        assertTrue(failed.isEmpty());
    }

    @Test
    void testGetFailedStudents_NormalList() {
        List<Student> students = new ArrayList<>();

        students.add(new Student("Grok", 1, 0.0));
        students.add(new Student("Ichigo", 2, 18.2));
        students.add(new Student("Caster", 3, 31.2));
        students.add(new Student("Neph", 4, 95.2));
        students.add(new Student("Travis", 23, 54.0));

        List<Student> failed = Student.getFailedStudents(students);
        assertEquals(3, failed.size());
        assertTrue(failed.stream().anyMatch(s -> s.getName().equals("Grok")));
        assertTrue(failed.stream().anyMatch(s -> s.getName().equals("Ichigo")));
        assertTrue(failed.stream().anyMatch(s -> s.getName().equals("Caster")));
    }

    @Test
    void testGetPassPercentage_BoundaryExactly40() {
        List<Student> students = new ArrayList<>();

        students.add(new Student("Grok", 1, 30.0));
        students.add(new Student("Ichigo", 2, 40.0));

        assertEquals(50.0, Student.getPassPercentage(students), 0.001);
    }

    @Test
    void testGetPassPercentage_SingleStudent() {
        List<Student> students = new ArrayList<>();

        students.add(new Student("Grok", 2, 43.0));

        assertEquals(100.0, Student.getPassPercentage(students), 0.001);
    }

    @Test
    void testGetStudentsByGrade_NormalList() {
        List<Student> students = new ArrayList<>();
        students.add(new Student("Delilah", 1, 99.4));
        students.add(new Student("Julian", 2, 86));
        students.add(new Student("Aofie", 3, 67));
        students.add(new Student("Evelyn", 4, 49.2));
        students.add(new Student("Kiera", 5, 31.8));

        List<Student> aStudents = Student.getStudentsByGrade(students, 'A');
        assertEquals(1, aStudents.size());
        assertEquals("Delilah", aStudents.get(0).getName());

        List<Student> fStudents = Student.getStudentsByGrade(students, 'f');
        assertEquals(1, fStudents.size());
        assertEquals("Kiera", fStudents.get(0).getName());

        List<Student> bStudents = Student.getStudentsByGrade(students, 'B');
        assertEquals(1, bStudents.size());
        assertEquals("Julian", bStudents.get(0).getName());

        List<Student> cStudents = Student.getStudentsByGrade(students, 'C');
        assertEquals(1, cStudents.size());
        assertEquals("Aofie", cStudents.get(0).getName());

        List<Student> dStudents = Student.getStudentsByGrade(students, 'D');
        assertEquals(1, dStudents.size());
        assertEquals("Evelyn", dStudents.get(0).getName());
    }
    
    @Test
    void testGetStudentsByGrade_InvalidGrade() {
        List<Student> students = new ArrayList<>();
        students.add(new Student("Ichigo", 1, 80.0));

        assertTrue(Student.getStudentsByGrade(students, 'X').isEmpty());
    }
    
    @Test
    void testGetStudentByGrade_emptyORnull() {
        assertTrue(Student.getStudentsByGrade(new ArrayList<>(), 'A').isEmpty());
        assertTrue(Student.getStudentsByGrade(null, 'B').isEmpty());
    }
    
    @Test
    void testGetStudentByRoll_NormalList() {
        List<Student> students = new ArrayList<>();
        students.add(new Student("Luffy", 1, 32.3));
        students.add(new Student("Zoro", 2, 66.2));
        students.add(new Student("Sanji", 3, 42.9));
        students.add(new Student("Robin", 4, 73.2));
        students.add(new Student("Chopper", 5, 83.2));
        students.add(new Student("Nami", 6, 94.2));

        assertEquals("Nami", Student.getStudentByRoll(students, 6).getName());
    }

    @Test
    void testGetStudentByRoll_NotFound() {
        List<Student> students = new ArrayList<>();
        students.add(new Student("Franky", 1, 43.2));

        assertNull(Student.getStudentByRoll(students, 2423));
    }

    @Test
    void testGetStudentByRoll_emptyORnull_ReturnEmptyList() {
        assertNull(Student.getStudentByRoll(new ArrayList<>(), 32));
        assertNull(Student.getStudentByRoll(null, 43));
    }

    @Test
    void testGetStudentByRoll_InvalidRoll() {
        List<Student> students = new ArrayList<>();
        students.add(new Student("Leon", 43, 89.2));

        assertNull(Student.getStudentByRoll(students, 0));
        assertNull(Student.getStudentByRoll(students, -67));
    }

    @Test
    void testGetStudentByRoll_DuplicateRoll_ReturnsFirstMatch() {
        List<Student> students = new ArrayList<>();
        students.add(new Student("Kaneki", 1, 69.7));
        students.add(new Student("Touka", 1, 67.9));

        assertEquals("Kaneki", Student.getStudentByRoll(students, 1).getName());
    }

    @Test
    void testSortStudentsByName_normal() {
        List<Student> students = new ArrayList<>();
        students.add(new Student("Jonathan", 1, 67.3));
        students.add(new Student("Diego", 2, 13.3));
        students.add(new Student("Zappili", 3, 65.1));

        List<Student> sorted = Student.sortStudentsByName(students);
        assertEquals(3, sorted.size());
        assertEquals("Diego", sorted.get(0).getName());
        assertEquals("Jonathan", sorted.get(1).getName());
        assertEquals("Zappili", sorted.get(2).getName());

        // Original list unchanged:
        assertEquals("Jonathan", students.get(0).getName());
    }

    @Test
    void testSortStudentsByName_caseInsensitive() {
        List<Student> students = new ArrayList<>();
        students.add(new Student("ruskin", 1, 54.2));
        students.add(new Student("Kirk", 2, 43.1));
        students.add(new Student("NAOYA", 3, 76.2));

        List<Student> sorted = Student.sortStudentsByName(students);
        assertEquals("Kirk", sorted.get(0).getName());
        assertEquals("NAOYA", sorted.get(1).getName());
        assertEquals("ruskin", sorted.get(2).getName());
    }

    @Test
    void testSortStudentsByName_emptyORnull() {
        assertTrue(Student.sortStudentsByName(new ArrayList<>()).isEmpty());
        assertTrue(Student.sortStudentsByName(null).isEmpty());
    }

    @Test
    void testRemoveStudentByRoll_normal() {
        List<Student> students = new ArrayList<>();
        Student toRemove = new Student("RemoveMe", 1, 54.2);
        students.add(toRemove);
        students.add(new Student("KeepMe", 2, 65.2));

        boolean removed = Student.removeStudentByRoll(students, 1);
        assertTrue(removed);
        assertEquals(1, students.size());
        assertEquals("KeepMe", students.get(0).getName());
    }

    @Test
    void testRemoveStudentByRoll_notFoundOrInvalid() {
        List<Student> students = new ArrayList<>();
        students.add(new Student("Frey", 1, 97.3));

        assertFalse(Student.removeStudentByRoll(students, 99));   // not found
        assertFalse(Student.removeStudentByRoll(students, -67));    // invalid
        assertFalse(Student.removeStudentByRoll(null, 1));    // null list

        assertTrue(Student.removeStudentByRoll(students, 1));
    }
}

