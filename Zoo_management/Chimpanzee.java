import java.util.Locale;
import java.util.Map;
/**
 * Represents a Chimpanzee in the zoo.
 * Inherits from the {@link Animals} class and provides specific behaviors
 *
 */
public class Chimpanzee extends Animals{
    /**
     * Constructs a Chimpanzee with a given name and age.
     *
     * @param name the name of the chimpanzee
     * @param age  the age of the chimpanzee
     */
    public Chimpanzee(String name, int age){
        super(name,age);
    }

    /**
     * @return a string representing the type ("Chimpanzee")
     */
    @Override
    public String getType() {
        return "Chimpanzee";
    }

    /**
     * Describes the process of cleaning the Chimpanzee's habitat.
     *
     * @param animal the chimpanzee whose habitat is being cleaned
     * @return a string describing the cleaning process
     */
    public String clean(Animals animal){
        return " started cleaning "+animal.getName()+"'s habitat.\nCleaning "+animal.getName()+"'s habitat: Sweeping the enclosure and replacing branches.";

    }

    /**
     * Calculates the amount of food required for the chimpanzee based on age and number of meals.
     *
     * @param animal      the chimpanzee to be fed
     * @param numOfMeals  the number of meals to be served
     * @return the total amount of food in kilograms
     */
    public double getAmount(Animals animal , int numOfMeals){
        int age = animal.getAge();
        double amount = (((age-10)* 0.0125)+3)*numOfMeals;
        return amount;
    }

    /**
     * Checks if enough meat and plant food are available, and returns a feeding message.
     *
     * @param allFoods a map of available food types and their quantities
     * @param amount   the required amount of each food type
     * @return a string describing the successful feeding
     * @throws AllExceptions.NotEnoughFoodException if either food type is not enough
     */
    public String checkingAmount(Map<String, Double> allFoods , double amount) throws AllExceptions.NotEnoughFoodException{
        if (allFoods.get("Meat")<amount){
            throw new AllExceptions.NotEnoughFoodException("Error: Not enough Meat");
        } else if (allFoods.get("Plant")<amount) {
            throw new AllExceptions.NotEnoughFoodException("Error: Not enough Plant");

        }else {
            String formattedAmount = String.format(Locale.US,"%.3f", amount);
            return " has been given "+formattedAmount+" kgs of meat and "+formattedAmount+" kgs of leaves";
        }
    }

    /**
     * @return a string representing the food type ("meat and plant")
     */
    public String getFoodType(){
        return "meat and plant";
    }
}
