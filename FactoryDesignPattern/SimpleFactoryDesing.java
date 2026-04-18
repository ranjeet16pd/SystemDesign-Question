package FactoryDesignPattern;


interface Burger {
    public void prepare();
}

class BasicBurger implements Burger {
    @Override
    public void prepare() {
        System.out.println("Preparing Basic Burger with Bun, Patty and Ketchup !");
    }
}

class StandardBurger implements Burger {
    @Override
    public void prepare() {
        System.out.println("Preparing StandardBurger with Bun, Patty, Cheese and Ketchup !");
    }
}

class PremiumBurger implements Burger {
    @Override
    public void prepare() {
        System.out.println("Premium Burger with Bun, Patty, lettuce, secret sauce and Cheese !");
    }
}


// -------Burger Factory----------

class BurgerFactory {

    public Burger createBurger(String type) {
        if (type.equalsIgnoreCase("BasicBurger")) {
            return new BasicBurger();
        } else if (type.equalsIgnoreCase("StandardBurger")) {
            return new StandardBurger();
        } else if (type.equalsIgnoreCase("PremiumBurger")) {
            return new PremiumBurger();
        } else {
            System.out.println("Invalid Burger Type");
            return null;
        }
    }
}


public class SimpleFactoryDesing {
    public static void main(String[] args) {
        String type = "StandardBurger";
        BurgerFactory factory = new BurgerFactory();
        Burger burger = factory.createBurger(type);
        burger.prepare();
    }
}
