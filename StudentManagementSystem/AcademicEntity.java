/**
 * An abstract class that represents a general academic entity.
 * It consists courses, programs and departments.
 */
public abstract class AcademicEntity {
    protected String code;
    protected String name;
    protected String department;
    protected int credit;

    /**
     * Default constructor.
     */
    public AcademicEntity(){}

    /**
     * Constructs an academic entity with the given properties.
     *
     * @param name       the name of the entity
     * @param department the associated department
     * @param credit     the number of credits required
     */
    public AcademicEntity(String name,String department,int credit){
        this.name= name;
        this.department=department;
        this.credit=credit;
    }

    /**
     * Returns a formatted string with detailed information about the academic entity.
     *
     * @param key a unique code identifying the entity
     * @return a string of the entity's details
     */
    public abstract String printInfo(String key);

    public String getName() {
        return name;
    }

    public String getDepartment() {
        return department;
    }

    public int getCredit() {
        return credit;
    }

}
