import java.util.ArrayList;
import java.util.List;
/**
 * Represents an academic program.
 * Inherits common academic properties from {@link AcademicEntity}.
 */
public class Programs extends AcademicEntity{
    String degreeLevel;
    private List<String> courses = new ArrayList<>();

    /**
     * Constructs a new academic program with the given properties.
     *
     * @param name         the name of the program
     * @param department   the department offering the program
     * @param degreeLevel  the degree level
     * @param credit       the total required credit for graduation
     */
    public Programs(String name,String department,String degreeLevel,int credit){
        super(name,department,credit);
        this.degreeLevel=degreeLevel;
    }

    @Override
    public String toString() {
        return name+","+department+","+degreeLevel+","+credit;
    }

    /**
     * Adds a course to the list of courses offered by this program.
     *
     * @param programCode the program code that the courses list belongs to
     * @param courseCode  the course code to add
     */

    public void addCourse(String programCode,String courseCode){
        courses.add(courseCode);
    }

    /**
     * Returns a detailed string containing information about the program,
     * including its name, department, degree level, credit requirement, and courses.
     *
     * @param key the program code
     */
    @Override
    public String printInfo(String key) {
        String courseInfo;
        if(courses.isEmpty()){
             courseInfo = "Courses: -\n";
        }else {
             courseInfo = "Courses: {"+String.join(",", courses) + "}\n";
        }
        return "Program Code: "+key+"\n" +
                "Name: "+name+"\n" +
                "Department: "+department+"\n" +
                "Degree Level: "+degreeLevel+"\n" +
                "Required Credits: "+credit+"\n"+
                courseInfo;
    }
}
