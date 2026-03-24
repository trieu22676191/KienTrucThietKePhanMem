package iuh.fit.se.factory;

public class EBook extends Book {
    private double fileSize;
    private String format;

    public EBook(String id, String title, String author, String category, double fileSize, String format) {
        super(id, title, author, category);
        this.fileSize = fileSize;
        this.format = format;
    }

    @Override
    public String getDetails() {
        return String.format("E-Book: %s - %s, %.2f MB, Format: %s",
                title, author, fileSize, format);
    }
}
