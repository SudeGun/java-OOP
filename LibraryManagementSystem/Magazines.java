/**
 * {@code Magazines} extends the {@code Items} class by adding a publisher field.
 */
public class Magazines extends Items {
    private String publisher;
    /**
     * Constructs a {@code Magazines} object with the specified title, publisher, category, and type.
     *
     * @param title     the title of the magazine
     * @param publisher the publisher of the magazine
     * @param category  the category of the magazine
     * @param type      the type of the magazine
     */
    public Magazines(String title, String publisher, String category, String type) {
        super(title, type,category);
        this.publisher=publisher;
    }
    /**
     * @return the magazine's publisher
     */
    public String getPublisher() {
        return publisher;
    }
    /**
     * @return a string in the format "title,publisher,category,type"
     */
    @Override
    public String toString() {
        return getTitle()+ ","+ publisher+ ","+ getCategory()+ ","+ getType();
    }

}