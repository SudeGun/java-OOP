/**
 * {@code DVDs} extends the {@code Items} class by adding director and runtime fields.
 */
public class DVDs extends Items{
    private String director;
    private String runtime;
    /**
     * Constructs a {@code DVDs} object with the specified title, director, category, runtime, and type.
     *
     * @param title    the title of the DVD
     * @param director the director of the DVD
     * @param category the category or genre of the DVD
     * @param runtime  the runtime of the DVD
     * @param type     the type of the DVD
     */
    public DVDs(String title, String director, String category, String runtime, String type) {
        super(title, type,category);
        this.director=director;
        this.runtime=runtime;
    }
    /**
     * @return the DVD's director
     */
    public String getDirector() {
        return director;
    }
    /**
     * @return the DVD's runtime
     */
    public String getRuntime() {
        return runtime;
    }
    /**
     * @return a string in the format "title,director,category,runtime,type"
     */
    @Override
    public String toString() {
        return getTitle()+ ","+ director+ ","+ getCategory()+ ","+ runtime+ ","+ getType();

    }
}