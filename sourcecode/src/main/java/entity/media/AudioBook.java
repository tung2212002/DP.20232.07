package entity.media;

import java.sql.SQLException;
import java.util.Date;

public class AudioBook extends Media {

    String author;
    int numOfPages;
    String language;
    String bookCategory;


    public AudioBook() throws SQLException {
    }

    public AudioBook(int id, String title, int quantity, String category, String imageUrl, int price, String type, String author, String language, int numOfPages, String bookCategory) throws SQLException {
        super(id, title, quantity, category, imageUrl, price, type);
        this.author = author;
        this.numOfPages = numOfPages;
        this.language = language;
        this.bookCategory = bookCategory;
    }

        public int getId() {
            return this.id;
        }

        public String getAuthor() {
            return this.author;
        }

        public AudioBook setAuthor(String author) {
            this.author = author;
            return this;
        }


        public int getNumOfPages() {
            return this.numOfPages;
        }

        public AudioBook setNumOfPages(int numOfPages) {
            this.numOfPages = numOfPages;
            return this;
        }

        public String getLanguage() {
            return this.language;
        }

        public AudioBook setLanguage(String language) {
            this.language = language;
            return this;
        }

        public String getBookCategory() {
            return this.bookCategory;
        }

        public AudioBook setBookCategory(String bookCategory) {
            this.bookCategory = bookCategory;
            return this;
        }

        @Override
        public String toString() {
            String basicInformation = "{" +
                    " id='" + id + "'" +
                    ", title='" + title + "'" +
                    ", category='" + category + "'" +
                    ", price='" + price + "'" +
                    ", quantity='" + quantity + "'" +
                    ", type='" + type + "'" +
                    ", imageURL='" + imageURL + "'" +
                    "}";
            return "{" +
                    basicInformation +
                    " author='" + author + "'" +
                    ", numOfPages='" + numOfPages + "'" +
                    ", language='" + language + "'" +
                    ", bookCategory='" + bookCategory + "'" +
                    "}";
        }
    }
