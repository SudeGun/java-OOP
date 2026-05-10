import java.util.Locale;
import java.util.Map;
/**
        * Represents a Lion in the zoo.
        * Extends the {@link Animals} base class.
        */
public class Lion extends Animals{
    /**
     * Constructs a Lion object with the given name and age.
     *
     * @param name the name of the lion
     * @param age  the age of the lion
     */
    public Lion(String name, int age){
        super(name,age);
    }
    /**
     * @return a string representing the type ("Lion")
     */

    @Override
    public String getType() {
        return "Lion";
    }
    /**
     *
     * @param animal the lion whose habitat is being cleaned
     * @return a formatted string describing the cleaning process
     */
    public String clean(Animals animal){
        return " started cleaning "+animal.getName()+"'s habitat.\nCleaning "+animal.getName()+"'s habitat: Removing bones and refreshing sand.";

    }
    /**
     * Calculates the amount of meat needed to feed the lion based on its age and number of meals.
     *
     * @param animal      the lion to be fed
     * @param numOfMeals  the number of meals to feed
     * @return the total amount of food in kilograms
     */
    public double getAmount(Animals animal , int numOfMeals){
        int age = animal.getAge();
        double amount = (((age-5)* 0.05)+5)*numOfMeals;
        return amount;
    }
    /**
     * Checks if enough meat is available and returns a feeding message.
     *
     * @param allFoods the map of all food types and their amounts
     * @param amount   the required amount of meat
     * @return a string describing the successful feeding
     * @throws AllExceptions.NotEnoughFoodException if meat is not enough
     */
    public String checkingAmount(Map<String, Double> allFoods , double amount) throws AllExceptions.NotEnoughFoodException{
        if (allFoods.get("Meat")<amount){
            throw new AllExceptions.NotEnoughFoodException("Error: Not enough Meat");
        }else {
            String formattedAmount = String.format(Locale.US,"%.3f", amount);
            return " has been given "+formattedAmount+" kgs of meat";
        }
    }
    /**
     *
     * @return a string representing the food type ("meat")
     */
    public String getFoodType(){
        return "meat";
    }


}
