package iuh.fit.se.factory;

public class PhysicalBookFactory extends BookFactory {
    @Override
    public Book createBook(String id, String title, String author, String category, Object... extras) {
        int pageCount = (int) extras[0];
        String location = (String) extras[1];
        return new PhysicalBook(id, title, author, category, pageCount, location);
    }
}
