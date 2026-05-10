/**
 * Represents an abstract person in the zoo system.
 * A person has a name and an ID, and can attempt to visit or feed animals.
 * Subclasses must define specific behavior for personnel and visitors.
 */
public abstract class People {
    private String type;
    private String name;
    private String id;

    /**
     * Constructs a person with the specified name and ID.
     *
     * @param name the name of the person
     * @param id   the unique identifier for the person
     */
    public People( String name, String id){
        this.name=name;
        this.id=id;
    }

    public abstract String getType();

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }
    @Override
    public String toString() {
        return getType() + "," + getName() + "," + getId();
    }
    public abstract String attemptToVisit(String nameOfAnimal);
    public abstract String visit(Animals animal);
    public abstract String attemptToFeed(String nameOfAnimal) throws AllExceptions.VisitorFeedException;
}
