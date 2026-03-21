package iuh.fit.se;

class Investor implements Observer {
    private String name;

    public Investor(String name) {
        this.name = name;
    }

    @Override
    public void update(String stockName, double price) {
        System.out.println("🔔 [Thông báo đến " + name + "]: Cổ phiếu " + stockName + " hiện có giá mới là: " + price);
    }
}
