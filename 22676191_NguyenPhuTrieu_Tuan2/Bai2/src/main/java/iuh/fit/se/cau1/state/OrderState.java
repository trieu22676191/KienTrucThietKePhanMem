package iuh.fit.se.cau1.state;


public interface OrderState {
    void handleRequest(OrderContext context);
    String getStatusName();
}