import java.util.*;

/**
 * Represents a student in the academic system.
 * Inherits basic person information from {@link Persons} and implements course-related logic from {@link I1}.
 */
public class Student extends Persons implements I1 {
    private List<String> enrolledCourses = new ArrayList<>();
    private Map<String, String> completedCourses = new HashMap<>();
    double totalPoints = 0.0;
    int count = 0;

    /**
     * Constructs a Student object with basic personal details.
     *
     * @param name        the student's name
     * @param email       the student's email
     * @param department  the student's major or department
     */
    public Student(String name, String email, String department) {
        super(name, email, department);
    }

    /**
     * Enrolls the student in a course.
     *
     * @param studentID   the ID of the student (unused here but included for interface compatibility)
     * @param courseCode  the code of the course to enroll in
     */
    public void enroll(String studentID, String courseCode) {
        enrolledCourses.add(courseCode);
    }

    /**
     * Records a completed course with a letter grade.
     *
     * @param studentID   the ID of the student
     * @param courseCode  the completed course code
     * @param grade       the grade received
     */
    public void complete(String studentID, String courseCode, String grade) {
        completedCourses.put(courseCode, grade);
    }

    /**
     * Gets the list of enrolled courses.
     *
     * @return list of enrolled course codes
     */
    public List<String> getEnrolledCourses() {
        return enrolledCourses;
    }

    /**
     * Gets the map of completed courses and grades.
     *
     * @return map of course codes to letter grades
     */
    public Map<String, String> getCompletedCourses() {
        return completedCourses;
    }

    /**
     * Calculates the student's GPA based on completed courses and their credits.
     *
     * @param completedCourses  map of course codes to letter grades
     * @param allCourses        map of all course objects, needed for credit values
     * @return GPA as a double
     */
    public double calculatingGPA(Map<String, String> completedCourses, Map<String, Courses> allCourses) {
        for (Map.Entry<String, String> entry : completedCourses.entrySet()) {
            AcademicEntity course = allCourses.get(entry.getKey());
            double value = GRADE_MAP.get(entry.getValue());
            totalPoints += value * course.getCredit();
            count += course.getCredit();
            }
        return totalPoints / count;
    }

    /**
     * Returns a formatted string of student information for display.
     *
     * @param key the student ID
     * @return a string containing student information
     */
    public String printInfo(String key){
        return "Student ID: "+key+"\n" +
                "Name: "+name+"\n" +
                "Email: "+email+"\n" +
                "Major: "+department+"\n"+
                "Status: Active\n";
    }
}
