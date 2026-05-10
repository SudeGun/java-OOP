import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.time.format.DateTimeFormatter;
/**
 * Represents a user in the library system, which can be an academic member, student, or guest.
 * This class holds the user's basic information and provides functionality to check overdue items,
 * track penalties, and manage borrowing limits.
 */
public class Users {
    private String name;
    private int id;
    private String phone;
    private int penalty;
    private int borrowNum;

    /**
     * Constructs a new user with the given name, phone number, and borrowing limit.
     *
     * @param name the name of the user
     * @param phone the phone number of the user
     * @param borrowNum the number of borrowed items
     */
    public Users(String name,String phone,int borrowNum){
        this.name=name;
        this.phone=phone;
        this.borrowNum=borrowNum;
    }
    /**
     *
     * @return the name of the user
     */
    public String getName() {
        return name;

    }
    /**
     *
     * @return the phone number of the user
     */
    public String getPhone() {
        return phone;
    }
    /**
     * Sets the total number of borrowed items for the user.
     *
     * @param borrowNum the number of borrowed items
     */
    public void setBorrowNum( int borrowNum){
        this.borrowNum= borrowNum;
    }
    /**
     * @return the number of borrowed items
     */
    public int getBorrowNum() {
        return borrowNum;
    }
    /**
     * Calculates the number of days between the borrow date and the current date
     *
     * @param date the due date of the borrowed item in "dd/MM/yyyy" format
     * @return the number of overdue days.
     */
    public long checkOverdue(String date){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate date1 = LocalDate.parse(date, formatter);
        LocalDate date2 = LocalDate.now();
        long overdue = ChronoUnit.DAYS.between(date1,date2);
        return overdue;

    }
    /**
     * Adds a penalty to the user. A fixed penalty amount is added each time this method is called.
     */
    public void setPenalty(int id){
        penalty+=2;
    }
    /**
     * Returns the current penalty amount for the user.
     *
     * @return the current penalty of the user
     */
    public int getPenalty() {
        return penalty;
    }

    /**
     * Resets the user's penalty to 0. This method is called when the penalty is paid.
     *
     * @param userID the ID of the user paying the penalty
     */
    public void payPenalty(int userID){
        penalty=0;
    }
}






