package iuh.fit.se;

interface Pizza {
    void prepare();
}

class CheesePizza implements Pizza {
    public void prepare() { System.out.println("Đang chuẩn bị Pizza Phô mai..."); }
}

class SeafoodPizza implements Pizza {
    public void prepare() { System.out.println("Đang chuẩn bị Pizza Hải sản..."); }
}

abstract class PizzaStore {
    public abstract Pizza createPizza(String type);

    public void orderPizza(String type) {
        Pizza pizza = createPizza(type);
        pizza.prepare();
        System.out.println("Pizza đã sẵn sàng phục vụ!");
    }
}

class VN_PizzaStore extends PizzaStore {
    @Override
    public Pizza createPizza(String type) {
        if (type.equals("cheese")) return new CheesePizza();
        if (type.equals("seafood")) return new SeafoodPizza();
        return null;
    }
}