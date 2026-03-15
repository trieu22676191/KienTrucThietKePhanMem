package iuh.fit.se.cau2.decorator;

public class VATTax extends TaxDecorator {
    public VATTax(Product product) {
        super(product);
    }

    @Override
    public String getDescription() {
        return product.getDescription() + " + VAT(10%)";
    }

    @Override
    public double getPrice() {
        return product.getPrice() * 1.1; // Cộng 10%
    }
}
