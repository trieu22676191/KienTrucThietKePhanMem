package iuh.fit.se.factory;

public class AudioBookFactory extends BookFactory {
    @Override
    public Book createBook(String id, String title, String author, String category, Object... extras) {
        int duration = (int) extras[0];
        String narrator = (String) extras[1];
        return new AudioBook(id, title, author, category, duration, narrator);
    }
}
