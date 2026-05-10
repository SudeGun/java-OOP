/**
 * Represents an academic member in the academic system.
 * Inherits common personal attributes from the {@link Persons} class.
 */
public class AcademicMember extends Persons {
    /**
     * Constructs an AcademicMember object with personal details.
     *
     * @param name        the academic member's name
     * @param email       the academic member's email
     * @param department  the academic member's department
     */
    public AcademicMember(String name, String email, String department){
        super(name,email,department);
    }

    @Override
    public String getName() {
        return super.getName();
    }

    /**
     * Returns a formatted string of academic member information for display.
     *
     * @param key the faculty ID
     * @return a string containing academic member details
     */
    public String printInfo(String key){
        return "Faculty ID: "+key+"\n" +
                "Name: "+name+"\n" +
                "Email: "+email+"\n" +
                "Department: "+department+"\n";
    }



}
