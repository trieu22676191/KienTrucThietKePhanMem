package iuh.fit.se.cau2.decorator;

public class SpecialTax extends TaxDecorator {
    public SpecialTax(Product product) {
        super(product);
    }

    @Override
    public String getDescription() {
        return product.getDescription() + " + SpecialTax(20%)";
    }

    @Override
    public double getPrice() {
        return product.getPrice() * 1.2; // Cộng 20%
    }
}