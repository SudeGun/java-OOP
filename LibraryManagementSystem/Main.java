import java.io.*;
import java.util.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    private static String[] args;
    /**
     * Stores borrowed item data. Key is itemID, value is list containing userID and date.
     */
    public static Map<Integer,List<String>> allData = new HashMap<>();
    /**
     * @param args Command-line arguments:
     *             args[0] = items file path,
     *             args[1] = users file path,
     *             args[2] = commands file path,
     *             args[3] = output file path
     */
    public static void main(String[] args) {
        Main.args = args;
        String items = args[0];
        String users = args[1];
        String commands = args[2];

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(args[3], false))) {
            writer.write(""); // Overwrite the file with an empty string
        } catch (IOException e) {
            e.printStackTrace();
        }

        Map<Integer, Object> itemsmap = readTxt1(items);
        Map<Integer, Object> usersmap = readTxt2(users);
        read_commands(commands, itemsmap, usersmap);
    }
    /**
     * Reads item data from the provided file and returns a map of itemID to Item object.
     *
     * @param items Path to the items file
     * @return Map of item ID to Item (Book, Magazine, DVD)
     */
    public static Map<Integer, Object> readTxt1(String items) {
        Map<Integer, Object> itemsmap = new HashMap<>(); //creating the itemsmap to store item ID and the item objects
        try (BufferedReader br = new BufferedReader(new FileReader(items))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                // variable parts stores the each value in row, separated by comma

                if (parts[0].equals("B")) { // means it is a book
                    Integer key = Integer.parseInt(parts[1].trim()); // seperating the item ID
                    Items books = new Books(parts[2], parts[3], parts[4], parts[5]);
                    itemsmap.put(key, books); // item ID to item object
                }

                if (parts[0].equals("M")) { // means it is a magazine
                    Integer key = Integer.parseInt(parts[1].trim()); // seperating the item ID
                    Items magazine = new Magazines(parts[2], parts[3], parts[4], parts[5]);
                    itemsmap.put(key, magazine); // item ID to item object
                }

                if (parts[0].equals("D")) { // means it is a DVD
                    Integer key = Integer.parseInt(parts[1].trim()); // seperating the item ID
                    Items dvd = new DVDs(parts[2], parts[3], parts[4], parts[5], parts[6]);
                    itemsmap.put(key, dvd); // item ID to item object
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return itemsmap;

    }
    /**
     * Reads user data from the provided file and returns a map of userID to User object.
     *
     * @param users Path to the users file
     * @return Map of user ID to User (Student, AcademicMember, Guest)
     */

    public static Map<Integer, Object> readTxt2(String users) {
        Map<Integer, Object> usersmap = new HashMap<>();
        try (BufferedReader br = new BufferedReader(new FileReader(users))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                // variable parts stores the each value in row, separated by comma

                if (parts[0].equals("S")) { // means it is a student
                    Integer key = Integer.parseInt(parts[2].trim()); //seperating the user ID
                    Users student = new Student(parts[1], parts[3], parts[4], parts[5], Integer.parseInt(parts[6]), 0);
                    usersmap.put(key, student); // user ID to user object


                }
                if (parts[0].equals("A")) { // means it is a academic member
                    Integer key = Integer.parseInt(parts[2].trim()); //seperating the user ID
                    Users academicMember = new AcademicMember(parts[1], parts[3], parts[4], parts[5], parts[6], 0);
                    usersmap.put(key, academicMember);  // user ID to user object
                }

                if (parts[0].equals("G")) { // means it is a guest
                    Integer key = Integer.parseInt(parts[2].trim()); //seperating the user ID
                    Users guest = new Guest(parts[1], parts[3], parts[4], 0);
                    usersmap.put(key, guest);  // user ID to user object
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return usersmap;

    }
    /**
     * Reads and executes commands from the commands file.
     *
     * @param commands Path to commands file
     * @param itemsmap Map of items
     * @param usersmap Map of users
     */
    public static void read_commands(String commands, Map<Integer, Object> itemsmap, Map<Integer, Object> usersmap) {
        List<Integer> borrowed = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(commands))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                // variable parts stores the each value in row, separated by comma

                if (parts[0].equals("borrow")) {
                    String date = parts[3];
                    borrow(Integer.parseInt(parts[1]), Integer.parseInt(parts[2]), date, itemsmap, usersmap, borrowed);
                } else if (parts[0].equals("return")) {
                    returnItem(Integer.parseInt(parts[1]), Integer.parseInt(parts[2]), itemsmap, usersmap, borrowed);
                } else if (parts[0].equals("pay")) {
                    pay(Integer.parseInt(parts[1]), usersmap);
                } else if (parts[0].equals("displayUsers")) {
                    displayUsers(usersmap);
                }else if (parts[0].equals("displayItems")) {
                    displayItems(itemsmap,usersmap,borrowed,allData);}

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * Processes a borrow command: handles borrowing rules and restrictions.
     *
     * @param id_user   User ID attempting to borrow
     * @param id_item   Item ID to borrow
     * @param date      Date of borrow
     * @param itemsmap  Map of items
     * @param usersmap  Map of users
     * @param borrowed  List of currently borrowed item IDs
     */

    public static void borrow(int id_user, int id_item, String date, Map<Integer, Object> itemsmap, Map<Integer, Object> usersmap, List<Integer> borrowed) {
        String output = Main.args[3];
        Object value1 = itemsmap.get(id_item);
        Object value2 = usersmap.get(id_user);
        Items items = (Items) value1; // casting the object to Items class so ı can use Items methods
        Users users = (Users) value2; // casting the object to Users class
        String title = items.getTitle(); // getting the title of the item
        String user = users.getName(); // getting the name of the user

        StringBuilder result = new StringBuilder();  // Use StringBuilder to accumulate messages
        String itemType = items.getType();  // reference, rare or limited

        if (users instanceof Student) { // Student
            Student student = (Student) value2;
            long overdue = users.checkOverdue(date);  // calculating the number of days between the borrow date and today's date
            int penalty = users.getPenalty();  // getting the penalty of that user
            if (penalty >= 6) {
                result.append(user).append(" cannot borrow ").append(title).append(", you must first pay the penalty amount! ").append(penalty).append("$\n");
            } else if (borrowed.contains(id_item)) { // checking if the item is available
                result.append(user).append(" cannot borrow ").append(title).append(", it is not available!\n");
            } else if (itemType.equals("reference")) {
                result.append(user).append(" cannot borrow reference item!\n");
            } else {
                if (student.getBorrowNum() < 5) {  //checking how many items that student borrowed
                student.setBorrowNum(student.getBorrowNum() + 1); // if borrowed items less than 5 then increase the borrow number by 1
                borrowed.add(id_item);
                result.append(user).append(" successfully borrowed! ").append(title).append("\n");
                storingData(id_item,id_user,date); // storing the user that borrowed the item and the borrow date
                } else {
                    result.append(user).append(" cannot borrow ").append(title).append(", since the borrow limit has been reached!\n");
                }
            }if (overdue > 30) {
                users.setPenalty(id_user); // setting penalty
                borrowed.remove(Integer.valueOf(id_item)); // otomotic return to the library
                student.setBorrowNum(student.getBorrowNum() - 1);
                removingData(id_item); // removing the item from the map that we store borrowed items
            }
        }
            if (users instanceof AcademicMember) { // Academic Member
                AcademicMember academicMember = (AcademicMember) value2;
                long overdue = users.checkOverdue(date); // calculating the number of days between the borrow date and today's date
                int penalty = users.getPenalty(); // getting the penalty of that user
                if (penalty >= 6) {
                    result.append(user).append(" cannot borrow ").append(title).append(", you must first pay the penalty amount! ").append(penalty).append("$\n");
                }else if (borrowed.contains(id_item)) {  // checking if the item is available
                    result.append(user).append(" cannot borrow ").append(title).append(", it is not available!\n");
                } else {
                    if (academicMember.getBorrowNum() < 3) {
                        academicMember.setBorrowNum(academicMember.getBorrowNum() + 1); // if borrowed items less than 3 then increase the borrow number by 1
                        borrowed.add(id_item);
                        result.append(user).append(" successfully borrowed! ").append(title).append("\n");
                        storingData(id_item,id_user,date);  // storing the user that borrowed the item and the borrow date
                    }else{
                        result.append(user).append(" cannot borrow ").append(title).append(", since the borrow limit has been reached!\n");
                    }
                }if (overdue > 15) {
                    users.setPenalty(id_user);
                    borrowed.remove(Integer.valueOf(id_item));  // otomotic return to the library
                    academicMember.setBorrowNum(academicMember.getBorrowNum() - 1);
                    removingData(id_item);  // removing the item from the map that we store borrowed items
                }
            }
            if (users instanceof Guest) { //Guest
                Guest guest = (Guest) value2;
                long overdue = users.checkOverdue(date);  // calculating the number of days between the borrow date and today's date
                int penalty = users.getPenalty();   // getting the penalty of that user
                if (penalty >= 6) {
                    result.append(user).append(" cannot borrow ").append(title).append(", you must first pay the penalty amount! ").append(penalty).append("$\n");
                }else if (borrowed.contains(id_item)) {  // checking if the item is available
                    result.append(user).append(" cannot borrow ").append(title).append(", it is not available!\n");
                }else if (itemType.equals("limited") || itemType.equals("rare")) {
                    result.append(user).append(" cannot borrow "+itemType+" item!\n");
                } else if (guest.getBorrowNum() < 1) {
                    guest.setBorrowNum(guest.getBorrowNum() + 1);    // if borrowed items less than 1 then increase the borrow number by 1
                    borrowed.add(id_item);
                    result.append(user).append(" successfully borrowed! ").append(title).append("\n");
                    storingData(id_item,id_user,date);   // storing the user that borrowed the item and the borrow date
                } else{
                        result.append(user).append(" cannot borrow ").append(title).append(", since the borrow limit has been reached!\n");
                    }
                if (overdue > 7) {
                    users.setPenalty(id_user);
                    borrowed.remove(Integer.valueOf(id_item));  // otomotic return to the library
                    guest.setBorrowNum(guest.getBorrowNum() - 1);
                    removingData(id_item);  // removing the item from the map that we store borrowed items
                }
            }

        // Write all results to the output file at once
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(output, true))) {
            writer.append(result.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * Processes a return command: updates records and borrow counts.
     *
     * @param id_user  User ID returning the item
     * @param id_item  Item ID being returned
     * @param itemsmap Map of items
     * @param usersmap Map of users
     * @param borrowed List of currently borrowed item IDs
     */

    public static void returnItem(int id_user, int id_item, Map<Integer, Object> itemsmap, Map<Integer, Object> usersmap, List<Integer> borrowed) {
        String output = Main.args[3];
        borrowed.remove(Integer.valueOf(id_item)); // removing from borrowed items list
        Object value1 = itemsmap.get(id_item);
        Object value2 = usersmap.get(id_user);
        Items items = (Items) value1;
        Users users = (Users) value2;
        removingData(id_item);
        String title = items.getTitle();
        String user = users.getName();

        if (users instanceof Student){  // for student
            Student student = (Student) value2;
            student.setBorrowNum(student.getBorrowNum() - 1);  // decreasing the number of borrowed items by 1
        }
        if (users instanceof AcademicMember){ // for academic member
            AcademicMember academicMember = (AcademicMember) value2;
            academicMember.setBorrowNum(academicMember.getBorrowNum() - 1);  // decreasing the number of borrowed items by 1
        }
        if (users instanceof Guest){
            Guest guest =(Guest) value2; // for guest
            guest.setBorrowNum(guest.getBorrowNum() - 1);  // decreasing the number of borrowed items by 1
        }
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(output, true))) {
            writer.write(user + " successfully returned " + title +"\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * Processes a pay command: user pays their penalty.
     *
     * @param id_user  User ID paying penalty
     * @param usersmap Map of users
     */
    public static void pay(int id_user, Map<Integer, Object> usersmap){
        String output = Main.args[3];
        Object value = usersmap.get(id_user);
        Users users = (Users) value;
        String user = users.getName();
        users.payPenalty(id_user); // making the penalty 0
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(output, true))) {
            writer.write(user + " has paid penalty\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * Displays information of all users in the system, sorted by ID.
     *
     * @param usersmap Map of user data
     */
    public static void displayUsers( Map<Integer, Object> usersmap){
        String output = Main.args[3];
        Set<Integer> keys = usersmap.keySet(); // getting all the user IDs
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(output, true))) {
            writer.write("\n"); // adding an extra new line
        } catch (IOException e) {
            e.printStackTrace();
        }
        List<Integer> keyList = new ArrayList<>(keys);  // putting all the user IDs to a list
        Collections.sort(keyList); // sorting the IDs
        for (Integer key : keyList) {
            int userID = key;
            Object value = usersmap.get(key);
            String numberStr = Integer.toString(userID); // converting to string
            int firstDigit = Character.getNumericValue(numberStr.charAt(0)); // looking at the first digit of the user ID
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(output, true))) {
                // Check which type of user and write to file
                if (firstDigit == 1) { // student
                    Student student = (Student) value;
                    writer.write("\n------ User Information for " + userID + " ------\n");
                    writer.append("Name: " + student.getName() + " Phone: " + student.getPhone() + "\n");
                    writer.append("Faculty: " + student.getFaculty() + " Department: " + student.getDepartment() + " Grade: " + student.getGrade() + "th\n");
                } else if (firstDigit == 2) {  // academic member
                    AcademicMember academicMember = (AcademicMember) value;
                    writer.write("\n------ User Information for " + userID + " ------\n");
                    writer.append("Name: " + academicMember.getTitle() + " " + academicMember.getName() + " Phone: " + academicMember.getPhone() + "\n");
                    writer.append("Faculty: " + academicMember.getFaculty() + " Department: " + academicMember.getDepartment() + "\n");
                } else if (firstDigit == 3) {  // guest
                    Guest guest = (Guest) value;
                    writer.write("\n------ User Information for " + userID + " ------\n");
                    writer.append("Name: " + guest.getName() + " Phone: " + guest.getPhone() + "\n");
                    writer.append("Occupation: " + guest.getOccupation() + "\n");
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    /**
     * Stores borrowing data for an item.
     *
     * @param itemID ID of the borrowed item
     * @param userID ID of the user borrowing
     * @param date   Date of borrowing
     */
    public static void storingData(int itemID, int userID, String date){
        List<String> data = new ArrayList<>();
        data.add(String.valueOf(userID));   // item ID : [ userID,borrow date]
        data.add(date);
        if (!allData.containsKey(itemID)){
            allData.put(itemID,new ArrayList<>());
        }
        allData.get(itemID).addAll(data);
    }
    /**
     * Removes borrowing record for a returned item.
     *
     * @param itemID ID of the returned item
     */
    public static void removingData(int itemID){
        allData.remove(itemID);
    }
    /**
     * Displays information about all items and their current status (available/borrowed).
     *
     * @param itemsmap Map of items
     * @param usersmap Map of users
     * @param borrowed List of borrowed item IDs
     * @param allData  Borrowing records
     */
    public static void displayItems( Map<Integer, Object> itemsmap , Map<Integer, Object> usersmap, List<Integer> borrowed, Map<Integer,List<String>> allData) {
        String output = Main.args[3];
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(output, true))) {
            writer.write("\n");  // adding an extra new line
        } catch (IOException e) {
            e.printStackTrace();
        }
        Set<Integer> keys = itemsmap.keySet();  // getting all the item IDs
        List<Integer> keyList = new ArrayList<>(keys);  // putting them. into a list
        Collections.sort(keyList); // sorting the item IDs
        for (int i = 0; i < keyList.size(); i++) { // this is for tracking the last item on the keylist
            Integer key = keyList.get(i);
            boolean checkingLast = (i == keyList.size() - 1);  // checking if it is the last one
            int itemID = key;
            Object value1 = itemsmap.get(key);
            if (String.valueOf(itemID).startsWith("1")){ // book
                Books books = (Books) value1;
                try (BufferedWriter writer = new BufferedWriter(new FileWriter(output, true))){
                    writer.write("\n------ Item Information for "+itemID+" ------\n");
                    if (borrowed.contains(itemID)) {  // checking if the item is borrowed
                        List<String> info = allData.get(itemID);
                        String userID = info.get(0); // getting the user that borrowed the item
                        String borrowDate = info.get(1); // getting the borrow date
                        Object value2 = usersmap.get(Integer.valueOf(userID));
                        Users users = (Users) value2;
                        String user = users.getName();
                        writer.write("ID: " + itemID + " Name: " + books.getTitle() + " Status: Borrowed Borrowed Date: " + borrowDate + " Borrowed by: " + user+"\n");
                    }else{
                        writer.write("ID: " + itemID + " Name: " + books.getTitle() + " Status: Available\n");
                    }
                        writer.write("Author: "+books.getAuthor()+" Genre: "+books.getCategory());
                    if (!checkingLast) {
                        writer.write("\n");
                    }
                    }catch (IOException e) {
                        e.printStackTrace();
                    }
            }
            if (String.valueOf(itemID).startsWith("2")){ // magazine
                Magazines magazines = (Magazines) value1;
                try (BufferedWriter writer = new BufferedWriter(new FileWriter(output, true))){
                    writer.write("\n------ Item Information for "+itemID+" ------\n");
                    if (borrowed.contains(itemID)) { // checking if the item is borrowed
                        List<String> info = allData.get(itemID);
                        String userID = info.get(0);  // getting the user that borrowed the item
                        String borrowDate = info.get(1); // getting the borrow date
                        Object value2 = usersmap.get(Integer.valueOf(userID));
                        Users users = (Users) value2;
                        String user = users.getName();
                        writer.write("ID: " + itemID + " Name: " + magazines.getTitle() + " Status: Borrowed Borrowed Date: " + borrowDate + " Borrowed by: " + user+"\n");
                    }else{writer.write("ID: " + itemID + " Name: " + magazines.getTitle() + " Status: Available\n");
                    }
                    writer.write("Publisher: "+magazines.getPublisher()+" Category: "+magazines.getCategory());
                    if (!checkingLast) {
                        writer.write("\n");
                    }
                }catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (String.valueOf(itemID).startsWith("3")) { // DVD
                DVDs dvDs = (DVDs) value1;
                try (BufferedWriter writer = new BufferedWriter(new FileWriter(output, true))) {
                    writer.write("\n------ Item Information for " + itemID + " ------\n");
                    if (borrowed.contains(itemID)) { // checking if the item is borrowed
                        List<String> info = allData.get(itemID);
                        String userID = info.get(0);  // getting the user that borrowed the item
                        String borrowDate = info.get(1);  // getting the borrow date
                        Object value2 = usersmap.get(Integer.valueOf(userID));
                        Users users = (Users) value2;
                        String user = users.getName();
                        writer.write("ID: " + itemID + " Name: " + dvDs.getTitle() + " Status: Borrowed Borrowed Date: " + borrowDate + " Borrowed by: " + user+"\n");
                    } else {
                        writer.write("ID: " + itemID + " Name: " + dvDs.getTitle() + " Status: Available\n");
                    }
                    writer.write("Director: " + dvDs.getDirector() + " Category: " + dvDs.getCategory() + " Runtime: " + dvDs.getRuntime());
                    if (!checkingLast) {
                        writer.write("\n");
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}




