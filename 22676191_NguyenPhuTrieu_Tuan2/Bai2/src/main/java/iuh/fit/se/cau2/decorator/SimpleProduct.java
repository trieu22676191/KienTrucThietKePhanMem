package iuh.fit.se.cau2.decorator;

public class SimpleProduct implements Product {
    private String name;
    private double basePrice;

    public SimpleProduct(String name, double basePrice) {
        this.name = name;
        this.basePrice = basePrice;
    }

    @Override
    public String getDescription() {
        return name;
    }

    @Override
    public double getPrice() {
        return basePrice;
    }
}