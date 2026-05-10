/**
 * The {@code Items} class represents Books, Magazines, and DVDs.
 */
public class Items {
    private int id;
    private String title;
    private String type;
    private String category;

    /**
     * Constructs an {@code Items} object with the specified title, type, and category.
     *
     * @param title    the title of the item
     * @param type     the type of the item
     * @param category the category of the item
     */


    public Items(String title,String type,String category){
        this.title=title;
        this.type=type;
        this.category=category;
    }
    /**
     * @return the item's title
     */

    public String getTitle(){
        return title;
    }
    /**
     * @return the item's type
     */
    public String getType(){
        return type;
    }
    /**
     * @return the item's category
     */

    public String getCategory() {
        return category;
    }


}






