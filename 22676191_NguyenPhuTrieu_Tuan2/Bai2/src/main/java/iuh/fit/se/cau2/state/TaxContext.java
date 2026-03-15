package iuh.fit.se.cau2.state;

public class TaxContext {
    private TaxState currentState;

    public void setTaxState(TaxState state) {
        this.currentState = state;
    }

    public double calculate(double price) {
        if (currentState == null) {
            System.out.println("Vui lòng thiết lập loại thuế trước khi tính!");
            return 0;
        }
        return currentState.calculateTax(price);
    }
}
