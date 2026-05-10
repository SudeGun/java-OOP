import java.util.Locale;
import java.util.Map;
/**
 * Represents a Penguin in the zoo.
 * Inherits from the {@link Animals} class.
 */
public class Penguin extends Animals{
    /**
     * Constructs a Penguin object with the given name and age.
     *
     * @param name the name of the penguin
     * @param age  the age of the penguin
     */
    public Penguin(String name, int age){
        super(name,age);
    }
    /**
     *
     * @return a string representing the type ("Penguin")
     */
    @Override
    public String getType() {
        return "Penguin";
    }

    /**
     * @param animal the penguin whose habitat is being cleaned
     * @return a formatted string describing the cleaning process
     */
    public String clean(Animals animal){
        return " started cleaning "+animal.getName()+"'s habitat.\nCleaning "+animal.getName()+"'s habitat: Replenishing ice and scrubbing walls.";
    }

    /**
     * Calculates the amount of fish-based food needed based on the penguin's age and number of meals.
     *
     * @param animal      the penguin to be fed
     * @param numOfMeals  the number of meals
     * @return the required amount of food in kilograms
     */
    public double getAmount(Animals animal , int numOfMeals){
        int age = animal.getAge();
        double amount = (((age-4)* 0.04)+3)*numOfMeals;
        return amount;
    }

    /**
     * Checks if enough fish is available and returns a feeding message.
     *
     * @param allFoods the map of all food types and their quantities
     * @param amount   the required amount of food
     * @return a string describing the successful feeding
     * @throws AllExceptions.NotEnoughFoodException if fish is not enough
     */
    public String checkingAmount(Map<String, Double> allFoods , double amount) throws AllExceptions.NotEnoughFoodException{
        if (allFoods.get("Fish")<amount){
            throw new AllExceptions.NotEnoughFoodException("Error: Not enough Fish");
        }else {
            String formattedAmount = String.format(Locale.US,"%.3f", amount);
            return " has been given "+formattedAmount+" kgs of various kinds of fish";
        }
    }

    /**
     *
     * @return a string representing the food type ("fish")
     */
    public String getFoodType(){
        return "fish";
    }



    }
