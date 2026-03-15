package iuh.fit.se.cau2.strategy;

public class Product {
    private String name;
    private double basePrice;
    private TaxStrategy taxStrategy;

    public Product(String name, double basePrice, TaxStrategy taxStrategy) {
        this.name = name;
        this.basePrice = basePrice;
        this.taxStrategy = taxStrategy;
    }

    // Cho phép thay đổi chiến lược thuế linh hoạt lúc runtime
    public void setTaxStrategy(TaxStrategy taxStrategy) {
        this.taxStrategy = taxStrategy;
    }

    public double calculateTotal() {
        double tax = taxStrategy.calculateTax(basePrice);
        return basePrice + tax;
    }

    public void displayInfo() {
        System.out.println("Sản phẩm: " + name);
        System.out.println("Giá gốc: " + basePrice);
        System.out.println("Tổng tiền (bao gồm thuế): " + calculateTotal());
        System.out.println("---------------------------");
    }
}
