package iuh.fit.se.cau1.strategy;

public class NewOrderStrategy implements OrderStrategy {
    @Override
    public void handle() {
        System.out.println("Hành vi: Kiểm tra thông tin đơn hàng (Check inventory, customer info).");
    }
}

class DeliveredStrategy implements OrderStrategy {
    @Override
    public void handle() {
        System.out.println("Hành vi: Cập nhật trạng thái đơn hàng là Đã giao thành công.");
    }
}

