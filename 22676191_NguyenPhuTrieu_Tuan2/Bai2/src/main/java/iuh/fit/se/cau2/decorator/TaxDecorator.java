package iuh.fit.se.cau2.decorator;

public abstract class TaxDecorator implements Product {
    protected Product product;

    public TaxDecorator(Product product) {
        this.product = product;
    }

    @Override
    public String getDescription() {
        return product.getDescription();
    }

    @Override
    public double getPrice() {
        return product.getPrice();
    }
}