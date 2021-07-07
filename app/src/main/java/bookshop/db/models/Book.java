package bookshop.db.models;

public class Book extends Model {

    /**
     * The title of the book.
     *
     * @param String
     */
    protected String title;

    /**
     * The ISBN number of the book.
     *
     * @param String
     */
    protected String isbn;

    /**
     * The price of the book in integer format.
     *
     * @param String
     */
    protected Integer price;

    /**
     * The number of units available.
     *
     * @param String
     */
    protected Integer stock = 0;

    /**
     * Set the user attributes.
     *
     * @param String[] attributes
     *
     * @return void
     */
    public void setAttributes(String[] attributes) {
        title = attributes[0];
        isbn = attributes[1];
        price = Integer.parseInt(attributes[2]);
        stock += Integer.parseInt(attributes[3]);
    }
}
