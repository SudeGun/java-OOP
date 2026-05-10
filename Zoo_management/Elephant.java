import java.util.Locale;
import java.util.Map;
/**
 * Represents an Elephant in the zoo.
 * Extends the {@link Animals} base class.
 */
public class Elephant extends Animals{
    /**
     * Constructs an Elephant object with the given name and age.
     *
     * @param name the name of the elephant
     * @param age  the age of the elephant
     */

    public Elephant(String name, int age){
        super(name,age);
    }
    /**
     * @return a string representing the type ("Elephant")
     */
    @Override
    public String getType() {
        return "Elephant";
    }
    /**
     *
     * @param animal the elephant whose habitat is being cleaned
     * @return a formatted string describing the cleaning process
     */

    public String clean(Animals animal){
        return " started cleaning "+animal.getName()+"'s habitat.\nCleaning "+animal.getName()+"'s habitat: Washing the water area.";

    }
    /**
     * Calculates the amount of plant-based food needed to feed the elephant based on its age and number of meals.
     *
     * @param animal      the elephant to be fed
     * @param numOfMeals  the number of meals to feed
     * @return the total amount of food in kilograms
     */
    public double getAmount(Animals animal , int numOfMeals){
        int age = animal.getAge();
        double amount = (((age-20)* 0.015)+10)*numOfMeals;
        return amount;
    }

    /**
     * Checks if enough plant-based food is available and returns a feeding message.
     *
     * @param allFoods the map of all food types and their quantities
     * @param amount   the required amount of food
     * @return a string describing the successful feeding
     * @throws AllExceptions.NotEnoughFoodException if plant food is not enough
     */
    public String checkingAmount(Map<String, Double> allFoods , double amount) throws AllExceptions.NotEnoughFoodException{
        if (allFoods.get("Plant")<amount){
            throw new AllExceptions.NotEnoughFoodException("Error: Not enough Plant");
        }else {
            String formattedAmount = String.format(Locale.US,"%.3f", amount);
            return " has been given "+formattedAmount+" kgs assorted fruits and hay";
        }

    }
    /**
     * @return a string representing the food type ("plant")
     */
    public String getFoodType(){
        return "plant";
    }


    }
