package iuh.fit.se.cau2.strategy;

public class ConsumptionTax implements TaxStrategy {
    @Override
    public double calculateTax(double price) {
        return price * 0.05;
    }
}
