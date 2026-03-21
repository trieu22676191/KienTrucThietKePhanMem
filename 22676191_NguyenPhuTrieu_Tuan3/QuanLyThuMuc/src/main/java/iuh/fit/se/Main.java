package iuh.fit.se;

public class Main {
    public static void main(String[] args) {
        File file1 = new File("baitap.docx", 150);
        File file2 = new File("hinh-anh.jpg", 2000);
        File file3 = new File("ca-nhac.mp3", 5000);

        Directory root = new Directory("C:");
        Directory documentDir = new Directory("Documents");
        Directory mediaDir = new Directory("Media");

        root.add(documentDir);
        root.add(mediaDir);

        documentDir.add(file1);
        mediaDir.add(file2);
        mediaDir.add(file3);

        mediaDir.add(new Directory("Empty Folder"));

        System.out.println("--- Hệ thống quản lý tập tin ---");
        root.display("");
    }
}