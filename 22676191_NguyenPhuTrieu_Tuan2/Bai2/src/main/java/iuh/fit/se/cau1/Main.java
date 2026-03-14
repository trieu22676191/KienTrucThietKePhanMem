package iuh.fit.se.cau1;

//import iuh.fit.se.cau1.state.OrderContext;
import iuh.fit.se.cau1.decorator.*;
import iuh.fit.se.cau1.strategy.OrderContext;
import iuh.fit.se.cau1.strategy.CancelledStrategy;
import iuh.fit.se.cau1.strategy.NewOrderStrategy;
import iuh.fit.se.cau1.strategy.ProcessingStrategy;


public class Main {
    public static void main(String[] args) {
        OrderContext order = new OrderContext();
//2.1.1
//        System.out.println("Trạng thái hiện tại: " + order.getState().getStatusName());
//
//        order.applyState();
//        System.out.println("Trạng thái hiện tại: " + order.getState().getStatusName());
//
//        order.applyState();
//        System.out.println("Trạng thái hiện tại: " + order.getState().getStatusName());
//
//        order.applyState();

//2.1.2
//        order.setStrategy(new NewOrderStrategy());
//        order.processOrder();
//
//        order.setStrategy(new ProcessingStrategy());
//        order.processOrder();
//
//        order.setStrategy(new CancelledStrategy());
//        order.processOrder();

        System.out.println("--- Khởi tạo đơn hàng mới ---");
        Order myOrder = new BasicOrder();

        // "Trang trí" thêm trạng thái Mới tạo
        myOrder = new NewOrderDecorator(myOrder);

        // "Trang trí" thêm trạng thái Đang xử lý
        myOrder = new ProcessingDecorator(myOrder);

        // Thực thi các hành vi đã được "chồng" lên nhau
        myOrder.performAction();

        System.out.println("\n--- Trường hợp Hủy đơn hàng ---");
        Order cancelOrder = new CancelledDecorator(new BasicOrder());
        cancelOrder.performAction();
    }
}