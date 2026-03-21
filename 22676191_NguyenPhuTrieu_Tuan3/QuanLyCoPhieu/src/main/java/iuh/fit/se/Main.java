package iuh.fit.se;

public class Main {
    public static void main(String[] args) {
        Stock appleStock = new Stock("APPLE (AAPL)", 150.0);

        Investor investor1 = new Investor("Anh Tuấn");
        Investor investor2 = new Investor("Chị Lan");
        Investor investor3 = new Investor("Ông Bình");

        appleStock.attach(investor1);
        appleStock.attach(investor2);
        appleStock.attach(investor3);

        appleStock.setPrice(155.5);

        appleStock.detach(investor3);

        appleStock.setPrice(148.0);
    }
}