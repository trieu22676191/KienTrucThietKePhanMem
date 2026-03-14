package iuh.fit.se.cau1.state;


public class CancelledState implements OrderState {
    @Override
    public void handleRequest(OrderContext context) {
        System.out.println("Đơn hàng đã bị hủy. Không thể thực hiện thao tác khác.");
    }

    @Override
    public String getStatusName() {
        return "Đã hủy";
    }
}