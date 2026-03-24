package iuh.fit.se.factory;

public class AudioBook extends Book {
    private int duration;
    private String narrator;

    public AudioBook(String id, String title, String author, String category, int duration, String narrator) {
        super(id, title, author, category);
        this.duration = duration;
        this.narrator = narrator;
    }

    @Override
    public String getDetails() {
        return String.format("Audio Book: %s - %s, %d minutes, Narrator: %s",
                title, author, duration, narrator);
    }
}
