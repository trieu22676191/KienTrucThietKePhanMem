package iuh.fit.se.cau1.strategy;

public class ProcessingStrategy implements OrderStrategy {
    @Override
    public void handle() {
        System.out.println("Hành vi: Đóng gói và vận chuyển (Packing and Shipping).");
    }
}
