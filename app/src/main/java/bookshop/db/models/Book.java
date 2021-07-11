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
    protected String author;

    /**
     * The price of the book in integer format.
     *
     * @param String
     */
    protected Integer price;

    /**
     * The category this book belongs to.
     *
     * @param String
     */
    protected String category;

    /**
     * The published of this book.
     *
     * @param String
     */
    protected String publisher;

    /**
     * Set the user attributes.
     *
     * @param String[] attributes
     *
     * @return void
     */
    public void setAttributes(String[] attributes) {
        title = attributes[0];
        author = attributes[1];
        category = attributes[1];
        price = Integer.parseInt(attributes[3]);
        publisher = attributes[1];
    }
}
