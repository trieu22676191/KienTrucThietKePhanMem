package iuh.fit.se.observer;

public class Student implements Observer {
    private String name;
    private String studentId;

    public Student(String name, String studentId) {
        this.name = name;
        this.studentId = studentId;
    }

    @Override
    public void update(String message) {
        System.out.println("[Student " + name + " - ID: " + studentId + "] Notification: " + message);
    }
}
