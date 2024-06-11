package entity.media;

import java.sql.SQLException;
import java.util.Date;

public class AudioBook extends Book {

    String author;
    int numOfPages;
    String language;
    String bookCategory;



    public AudioBook(int id, String title, String category, int price, int quantity, String type, String author,
                     String coverType, String publisher, Date publishDate, int numOfPages, String language,
                     String bookCategory) throws SQLException {
        super(id, title, category, price, quantity, type, author, coverType, publisher, publishDate, numOfPages, language, bookCategory);
    }

}
