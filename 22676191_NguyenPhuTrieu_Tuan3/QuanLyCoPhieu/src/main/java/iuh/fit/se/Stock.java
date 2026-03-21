package iuh.fit.se;

import java.util.ArrayList;
import java.util.List;

class Stock implements Subject {
    private String name;
    private double price;
    private List<Observer> observers = new ArrayList<>();

    public Stock(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public void setPrice(double newPrice) {
        System.out.println("\n📈 [Thị trường] Cổ phiếu " + name + " đổi giá từ " + price + " -> " + newPrice);
        this.price = newPrice;
        notifyObservers();
    }

    @Override
    public void attach(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void detach(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update(name, price);
        }
    }
}
