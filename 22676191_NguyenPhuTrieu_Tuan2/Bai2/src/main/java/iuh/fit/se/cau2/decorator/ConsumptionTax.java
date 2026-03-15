package iuh.fit.se.cau2.decorator;

public class ConsumptionTax extends TaxDecorator {
    public ConsumptionTax(Product product) {
        super(product);
    }

    @Override
    public String getDescription() {
        return product.getDescription() + " + ConsumptionTax(5%)";
    }

    @Override
    public double getPrice() {
        return product.getPrice() + (product.getPrice() * 0.05);
    }
}