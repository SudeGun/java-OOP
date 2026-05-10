import java.util.Map;

/**
 * Represents an abstract animal in the zoo.
 * Each animal has a name and age, and subclasses must implement
 * methods related to their type, food requirements, and cleaning behavior.
 */
public abstract class Animals {
    private String type;
    private String name;
    private int age;

    /**
     * Constructs an animal with the specified name and age.
     *
     * @param name the name of the animal
     * @param age  the age of the animal
     */
    public Animals(String name, int age){
        this.name= name;
        this.age=age;
    }
    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public abstract String getType();

    @Override
    public String toString() {
        return getName() + "," + getAge();
    }

    public abstract String clean(Animals animal);

    public abstract double getAmount(Animals animal , int numOfMeals);
    public abstract String checkingAmount( Map<String, Double> foodStock , double amount) throws AllExceptions.NotEnoughFoodException;
    public abstract String getFoodType();


}
