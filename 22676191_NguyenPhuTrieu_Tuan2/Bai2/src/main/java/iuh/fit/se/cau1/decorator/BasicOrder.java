package iuh.fit.se.cau1.decorator;

public class BasicOrder implements Order {
    @Override
    public void performAction() {
        System.out.println("BasicOrder: Xử lý thông tin cơ bản");
    }
}