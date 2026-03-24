package iuh.fit.se.factory;

public class PhysicalBook extends Book {
    private int pageCount;
    private String location;

    public PhysicalBook(String id, String title, String author, String category, int pageCount, String location) {
        super(id, title, author, category);
        this.pageCount = pageCount;
        this.location = location;
    }

    @Override
    public String getDetails() {
        return String.format("Physical Book: %s - %s, %d pages, Location: %s",
                title, author, pageCount, location);
    }
}
