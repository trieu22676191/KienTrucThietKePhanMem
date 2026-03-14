package iuh.fit.se;

public class InventoryManager {
    private static InventoryManager instance;
    private int pizzaCount = 100;

    private InventoryManager() {}
    public static synchronized InventoryManager getInstance() {
        if (instance == null) {
            instance = new InventoryManager();
        }
        return instance;
    }

    public void checkStock() {
        System.out.println("Tồn kho hiện tại: " + pizzaCount + " chiếc.");
    }
}