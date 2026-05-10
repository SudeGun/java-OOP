/**
 * {@code Student} extends the {@code Users} class by adding student-specific fields such as department, faculty, and grade.
 */
public class Student extends Users {
    private String department;
    private String faculty;
    private int grade;

    /**
     * Constructs a {@code Student} with the specified personal and academic information.
     *
     * @param name       the name of the student
     * @param phone      the contact phone number
     * @param department the department the student belongs to
     * @param faculty    the faculty or school the student is part of
     * @param grade      the student's grade level
     * @param borrowNum  the number of borrowed items
     */
    public Student(String name, String phone, String department, String faculty, int grade, int borrowNum) {
        super(name,phone,borrowNum);
        this.department=department;
        this.faculty =faculty;
        this.grade=grade;
    }
    /**
     * Returns a string representation of the student.
     *
     * @return a string in the format "name,phone,department,faculty,grade,borrowNum"
     */
    @Override
    public String toString() {
        return getName()+ ","+ getPhone()+ ","+ department+ ","+ faculty+ ","+ grade+ ","+ getBorrowNum();

    }
    /**
     * @return the department
     */
    public String getDepartment() {
        return department;
    }
    /**
     * @return the faculty
     */
    public String getFaculty() {
        return faculty;
    }
    /**
     * @return the grade
     */
    public int getGrade() {
        return grade;
    }
}
