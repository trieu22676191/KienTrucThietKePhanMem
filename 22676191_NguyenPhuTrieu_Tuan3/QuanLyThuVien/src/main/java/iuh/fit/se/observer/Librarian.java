package iuh.fit.se.observer;

public class Librarian implements Observer {
    private String name;

    public Librarian(String name) {
        this.name = name;
    }

    @Override
    public void update(String message) {
        System.out.println("[Librarian " + name + "] Notification: " + message);
    }
}
