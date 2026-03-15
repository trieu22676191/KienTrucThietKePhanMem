package iuh.fit.se.cau2.state;

public class LuxuryTax implements TaxState {
    @Override
    public double calculateTax(double price) {
        return price * 0.3;
    }
}