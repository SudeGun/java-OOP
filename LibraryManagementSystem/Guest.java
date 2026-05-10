/**
 * {@code Guest} extends the {@code Users} class by adding a field for occupation.
 */
public class Guest extends Users{
    private String occupation;

    /**
     * Constructs a {@code Guest} with the specified personal details and occupation.
     *
     * @param name       the name of the guest
     * @param phone      the contact phone number
     * @param occupation the occupation of the guest
     * @param borrowNum  the number of borrowed items
     */
    public Guest(String name, String phone, String occupation, int borrowNum) {
        super(name, phone,borrowNum);
        this.occupation =occupation;
    }
    /**
     * Returns a string representation of the guest.
     *
     * @return a string in the format "name,phone,occupation,borrowNum"
     */

    @Override
    public String toString() {
        return getName()+ ","+ getPhone()+ ","+ occupation+ ","+ getBorrowNum();
    }
    /**
     * @return the occupation
     */
    public String getOccupation(){
        return occupation;
    }

}