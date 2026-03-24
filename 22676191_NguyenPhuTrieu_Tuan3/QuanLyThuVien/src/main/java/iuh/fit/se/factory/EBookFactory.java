package iuh.fit.se.factory;

public class EBookFactory extends BookFactory {
    @Override
    public Book createBook(String id, String title, String author, String category, Object... extras) {
        double fileSize = (double) extras[0];
        String format = (String) extras[1];
        return new EBook(id, title, author, category, fileSize, format);
    }
}
