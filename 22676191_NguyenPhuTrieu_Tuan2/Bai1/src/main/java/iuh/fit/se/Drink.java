package iuh.fit.se;

interface Drink { void pour(); }

class Coke implements Drink { public void pour() { System.out.println("Rót Coca-Cola..."); } }
class Wine implements Drink { public void pour() { System.out.println("Rót Rượu vang đỏ..."); } }

interface ComboFactory {
    Pizza createPizza();
    Drink createDrink();
}

class BudgetComboFactory implements ComboFactory {
    public Pizza createPizza() { return new CheesePizza(); }
    public Drink createDrink() { return new Coke(); }
}

class LuxuryComboFactory implements ComboFactory {
    public Pizza createPizza() { return new SeafoodPizza(); }
    public Drink createDrink() { return new Wine(); }
}