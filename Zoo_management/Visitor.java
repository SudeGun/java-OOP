/**
 * Represents a visitor in the zoo.
 * Inherits from the {@link People} class.
 * Visitors can register for visits and view animals but are not permitted to feed them.
 */
public class Visitor extends People{

    /**
     * Constructs a Visitor object with the specified name and ID.
     *
     * @param name the name of the visitor
     * @param id   the ID of the visitor
     */
    public Visitor(String name, String id) {
        super(name, id);
    }

    /**
     * @return a string representing the type ("Visitor")
     */
    @Override
    public String getType() {
        return "Visitor";
    }

    /**
     * Returns a message indicating an attempt to register for a visit to a specific animal.
     *
     * @param nameOfAnimal the name of the animal the visitor wants to see
     * @return a string indicating the visit registration attempt
     */
    @Override
    public String attemptToVisit(String nameOfAnimal){
        return " tried to register for a visit to "+nameOfAnimal+".";
    }

    /**
     * Returns a message confirming a successful visit to an animal.
     *
     * @param animal the animal that was visited
     * @return a string confirming the visit
     */
    @Override
    public String visit(Animals animal){
        return " successfully visited "+animal.getName()+".";
    }

    /**
     * Throws an exception because visitors are not allowed to feed animals.
     *
     * @param nameOfAnimal the name of the animal the visitor tried to feed
     * @throws AllExceptions.VisitorFeedException always thrown to indicate banned feeding
     */
    @Override
    public String attemptToFeed(String nameOfAnimal) throws AllExceptions.VisitorFeedException{
        throw new AllExceptions.VisitorFeedException(" tried to feed "+nameOfAnimal+"\nError: Visitors do not have the authority to feed animals.");
    }

}
