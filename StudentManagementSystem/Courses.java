import java.util.*;
/**
 * Represents a course in the academic system.
 * Inherits common academic properties from {@link AcademicEntity}
 * and implements enrolling functionality from {@link I1}.
 */

public class Courses extends AcademicEntity implements I1{
    String semester;
    String programCode;
    private List<String> enrolledStudents = new ArrayList<>();
    private Map<String,Integer> countOfGrades = new HashMap<>();
    private String instructorName;
    double totalPoints = 0.0;
    int countStudents = 0;


    /**
     * Constructs a new course with the specified attributes.
     *
     * @param name         the name of the course
     * @param department   the department offering the course
     * @param credit       the credit value of the course
     * @param semester     the semester the course is offered
     * @param programCode  the program code the course belongs to
     */
    public Courses(String name,String department,int credit, String semester,String programCode ){
        super(name,department,credit);
        this.semester=semester;
        this.programCode=programCode;
    }

    /**
     * Enrolls a student into the course.
     *
     * @param courseCode       the code of the course
     * @param enrolledStudent  the student ID to enroll
     */
    public void enroll(String courseCode, String enrolledStudent){
        enrolledStudents.add(enrolledStudent);
    }

    /**
     * Returns the list of enrolled student IDs.
     *
     * @return a list of student IDs
     */
    public List<String> getEnrolledStudents() {
        return enrolledStudents;
    }

    /**
     * Increments the count of a given letter grade.
     *
     * @param courseCode  the code of the course
     * @param gradeLetter the letter grade
     */
    public void count(String courseCode,String gradeLetter){
        if(!countOfGrades.containsKey(gradeLetter)){
            countOfGrades.put(gradeLetter,1);
        }else{
            int currentCount = countOfGrades.get(gradeLetter);
            countOfGrades.put(gradeLetter, currentCount + 1);
        }
    }

    /**
     * Returns the map of grade letters to their counts.
     *
     * @return the grade distribution map
     */
    public Map<String, Integer> getCountOfGrades() {
        return countOfGrades;
    }

    public String getSemester() {
        return semester;
    }

    /**
     * Sets the name of the instructor for this course.
     *
     * @param name the instructor's name
     */
    public void setInstructor(String name) {
        this.instructorName = name;
    }

    public String getInstructorName() {
        return instructorName;
    }

    /**
     * Calculates the average grade for the course based on all students' grades.
     *
     * @param countOfGrades a map of letter grades and how many students received each
     * @return the average grade point value
     */
    public double calculatingGrade(Map<String,Integer> countOfGrades){
        for (Map.Entry<String, Integer> entry : countOfGrades.entrySet()){
            int count = countOfGrades.get(entry.getKey());
            double value = GRADE_MAP.get(entry.getKey());
            totalPoints += value*count;
            countStudents += count;
        }
        return totalPoints/countStudents;
    }

    @Override
    public String toString() {
        return name+","+department+","+credit+","+semester+","+programCode;
    }

    /**
     * Returns a detailed formatted string containing course information.
     *
     * @param key the course code
     * @return the formatted string
     */
    @Override
    public String printInfo(String key) {
        return "Course Code: "+key+"\nName: "+name+"\nDepartment: "+department+"\nCredits: "+credit+"\nSemester: "+semester+"\n";
    }
}
