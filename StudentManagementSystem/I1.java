import java.util.*;
/**
 * An interface that provides grade mapping and enrollment behavior for academic entities such as courses.
 */
public interface I1 {
    /**
     * Enrolls a student into a course or enrolls a course into a student.
     *
     * @param string1  represents the course code or the student ID.
     * @param string2  represents the student ID or the course code.
     */
    void enroll(String string1,String string2);

    /**
     * A static unmodifiable grade-to-point mapping based on academic standards.
     * Keys represent letter grades and values are the corresponding GPA points.
     */
    Map<String, Double> GRADE_MAP = createGradeMap();

    static Map<String, Double> createGradeMap() {
        Map<String, Double> map = new HashMap<>();
        map.put("A1", 4.00);
        map.put("A2", 3.50);
        map.put("B1", 3.00);
        map.put("B2", 2.50);
        map.put("C1", 2.00);
        map.put("C2", 1.50);
        map.put("D1", 1.00);
        map.put("D2", 0.50);
        map.put("F3", 0.00);
        return map;
    }
}
