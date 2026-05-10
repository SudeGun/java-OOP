/**
 * {@code Books} extends the {@code Items} class by adding an author field.
 */
public class Books extends Items{
    private String author;
    /**
     * Constructs a {@code Books} object with the specified title, author, category, and type.
     *
     * @param title    the title of the book
     * @param author   the author of the book
     * @param category the category of the book
     * @param type     the type of the book
     */
    public Books( String title, String author, String category, String type) {
        super(title,type,category);
        this.author = author;
    }
    /**
     * @return the book's author
     */
    public String getAuthor() {
        return author;
    }
    /**
     * @return a string in the format "title,author,category,type"
     */
    @Override
    public String toString() {
        return getTitle()+ ","+ author+ ","+ getCategory()+ ","+ getType();

    }

}
