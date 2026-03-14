package iuh.fit.se.cau1.state;

public class OrderContext {
    private OrderState state;

    public OrderContext() {
        // Mặc định ban đầu là Mới tạo
        this.state = new NewOrderState();
    }

    public void setState(OrderState state) {
        this.state = state;
    }

    public OrderState getState() {
        return state;
    }

    public void applyState() {
        state.handleRequest(this);
    }
}