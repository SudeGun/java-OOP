/**
 * The {@code AcademicMember} class represents an academic staff member who uses the library.
 * It extends the {@code Users} class by adding academic-specific fields such as department, faculty, and title.
 */
public class AcademicMember extends Users {
    private String department;
    private String faculty;
    private String title;

    /**
     * Constructs an {@code AcademicMember} with the specified personal and academic information.
     *
     * @param name       the name of the academic member
     * @param phone      the contact phone number
     * @param department the department the member belongs to
     * @param faculty    the faculty or school the member is part of
     * @param title      the academic title (e.g., Professor, Dr.)
     * @param borrowNum  the number of items the member is allowed to borrow
     */
    public AcademicMember(String name, String phone, String department, String faculty, String title, int borrowNum) {
        super(name, phone, borrowNum);
        this.department = department;
        this.faculty = faculty;
        this.title = title;
    }

    /**
     * Returns the department of the academic member.
     *
     * @return the department
     */
    public String getDepartment() {
        return department;
    }

    /**
     * Returns the faculty of the academic member.
     *
     * @return the faculty
     */
    public String getFaculty() {
        return faculty;
    }

    /**
     * Returns the academic title of the member.
     *
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Returns a string representation of the academic member.
     *
     * @return a string in the format "name,phone,department,faculty,title,borrowNum"
     */
    @Override
    public String toString() {
        return getName() + "," + getPhone() + "," + department + "," + faculty + "," + title + "," + getBorrowNum();
    }
}
