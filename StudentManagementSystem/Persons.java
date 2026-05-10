import java.util.Map;
/**
 * Represents a container for student and academic personnel data,
 * or an individual person when used with name, email, and department only.
 *
 */
public class Persons {
    protected String ID;
    protected String name;
    protected String email;
    protected String department;
    public Map<String, Student> students;
    public Map<String, AcademicMember> academics;

    /**
     * Constructs a Persons object as a data container for all parsed individuals.
     *
     * @param students   map of all students
     * @param academics  map of all academic members
     */
    public Persons(Map<String, Student> students, Map<String, AcademicMember> academics){
        this.students = students;
        this.academics = academics;
    }

    /**
     * Constructs a Persons object representing an individual person
     * (used in cases where only name, email, and department are needed).
     *
     * @param name        the person's name
     * @param email       the person's email
     * @param department  the person's department
     */
    public Persons(String name, String email, String department){
        this.name=name;
        this.email=email;
        this.department=department;
    }

    public String getName() {
        return name;
    }

    public String getDepartment() {
        return department;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String toString() {
        return name+","+email+","+department;
    }
}
