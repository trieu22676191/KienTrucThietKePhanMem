package iuh.fit.se;

public class Main {
    public static void main(String[] args) {
        InventoryManager inventory = InventoryManager.getInstance();
        inventory.checkStock();

        System.out.println("\n--- Đặt đơn lẻ ---");
        PizzaStore store = new VN_PizzaStore();
        store.orderPizza("seafood");

        System.out.println("\n--- Đặt Combo Sang Trọng ---");
        ComboFactory combo = new LuxuryComboFactory();
        Pizza p = combo.createPizza();
        Drink d = combo.createDrink();

        p.prepare();
        d.pour();
    }
}