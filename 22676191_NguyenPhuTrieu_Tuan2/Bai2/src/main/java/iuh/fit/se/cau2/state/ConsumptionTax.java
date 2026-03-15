package iuh.fit.se.cau2.state;

public class ConsumptionTax implements TaxState {
    @Override
    public double calculateTax(double price) {
        return price * 0.05;
    }
}