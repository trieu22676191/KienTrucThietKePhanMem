package iuh.fit.se.cau1.strategy;

public class OrderContext {
    private OrderStrategy orderStrategy;

    public void setStrategy(OrderStrategy strategy) {
        this.orderStrategy = strategy;
    }

    public void processOrder() {
        if (orderStrategy == null) {
            System.out.println("Vui lòng thiết lập trạng thái/chiến lược trước khi xử lý!");
        } else {
            orderStrategy.handle();
        }
    }
}
