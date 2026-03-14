package iuh.fit.se.cau1.strategy;

public class CancelledStrategy implements OrderStrategy {
    @Override
    public void handle() {
        System.out.println("Hành vi: Hủy đơn hàng và thực hiện hoàn tiền (Refund).");
    }
}
