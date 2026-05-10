/**
 * Represents a university department within the academic system.
 * Inherits common academic entity properties from {@link AcademicEntity}.
 */
public class Departments extends AcademicEntity{
    String headID;

    /**
     * Constructs a Departments object with a given name and head ID.
     *
     * @param name   the name of the department
     * @param headID the ID of the department head (an AcademicMember)
     */
    public Departments(String name,String headID){
        super.name=name;
        this.headID=headID;
    }

    /**
     * Returns a formatted string of department information for display.
     *
     * @param key the department code
     * @return a string containing department details
     */
    @Override
    public String printInfo( String key) {
        return "Department Code: "+key+"\nName: "+name+"\nHead: ";
    }

    public String getHeadID() {
        return headID;
    }
}
