/**
 * Represents a personnel member in the zoo who is responsible for cleaning animal habitats and feeding.
 * Inherits from the {@link People} class.
 */
public class Personnel extends People {

    /**
     * Constructs a Personnel object with a specified name and ID.
     *
     * @param name the name of the personnel
     * @param id   the ID of the personnel
     */
    public Personnel(String name, String id){
        super(name,id);
    }

    /**
     * @return a string representing the type ("Personnel")
     */
    @Override
    public String getType() {
        return "Personnel";
    }

    /**
     * Allows personnel to visit (clean) the specified animal's habitat.
     *
     * @param animal the animal whose habitat is being cleaned
     * @return a string describing the cleaning process
     */
    @Override
    public String visit(Animals animal){
        return animal.clean(animal);
    }

    /**
     * Returns a message indicating an attempt to clean a specific animal's habitat.
     *
     * @param nameOfAnimal the name of the animal whose habitat is being cleaned
     * @return a string indicating the cleaning attempt
     */
    @Override
    public String attemptToVisit(String nameOfAnimal){
        return " attempts to clean "+nameOfAnimal+"'s habitat.";
    }

    /**
     * Returns a message indicating an attempt to feed a specific animal.
     *
     * @param nameOfAnimal the name of the animal to be fed
     * @return a string indicating the feeding attempt
     */
    @Override
    public String attemptToFeed(String nameOfAnimal){
        return " attempts to feed "+nameOfAnimal+".";
    }

}
