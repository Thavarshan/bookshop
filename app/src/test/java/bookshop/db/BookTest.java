package bookshop.db;

import bookshop.db.models.Book;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

public class BookTest {

    /**
     * The book insatnce.
     *
     * @var Book
     */
    Book book = new Book();

    /**
     * Fake book attributes.
     *
     * @var String[]
     */
    String[] attributes = { "Data Smart", "John Foreman", "data_science", "235", "Wiley" }; // title, author, category,
                                                                                            // price, publisher

    @Before
    public void setUp() {
        book.setAttributes(this.attributes);
    }

    @Test
    public void bookModelCanSetAttributes() {
        assertArrayEquals(attributes, book.getAttributes());
    }
}
