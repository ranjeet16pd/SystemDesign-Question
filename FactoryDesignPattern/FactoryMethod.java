package FactoryDesignPattern;


interface Burger2 {
    void prepare();
}


class BasicBurger2 implements Burger2 {
    @Override
    public void prepare() {
        System.out.println("Prepare Burger");
    }
}

class StandardBurger2 implements Burger2 {
    @Override
    public void prepare() {
        System.out.println("Prepare StandardBurger");
    }
}

class PremiumBurger2 implements Burger2 {
    @Override
    public void prepare() {
        System.out.println("Prepare PremiumBurger");
    }
}


class BasicWheatBurger2 implements Burger2 {
    @Override
    public void prepare() {
        System.out.println("Prepare BasicWheatBurger");
    }
}

class StandardWheatBurger2 implements Burger2 {
    @Override
    public void prepare() {
        System.out.println("Prepare StandardWheatBurger");
    }
}

class PremiumWheatBurger2 implements Burger2 {
    @Override
    public void prepare() {
        System.out.println("Prepare PremiumWheatBurger");
    }
}

interface BurgerFactory2 {
    Burger2 createBurger(String type);
}

class SinghBurgerFactory implements BurgerFactory2 {
    @Override
    public Burger2 createBurger(String type) {
        if (type.equalsIgnoreCase("BasicBurger2")) {
            return new BasicBurger2();
        } else if (type.equalsIgnoreCase("StandardBurger2")) {
            return new StandardBurger2();
        } else if (type.equalsIgnoreCase("PremiumBurger2")) {
            return new PremiumBurger2();
        } else {
            System.out.println("Invalid Burger");
            return null;

        }
    }
}

class KingBurgerFactory implements BurgerFactory2 {
    @Override
    public Burger2 createBurger(String type) {
        if (type.equalsIgnoreCase("BasicBurger2")) {
            return new BasicWheatBurger2();
        } else if (type.equalsIgnoreCase("StandardBurger2")) {
            return new StandardWheatBurger2();
        } else if (type.equalsIgnoreCase("PremiumBurger2")) {
            return new PremiumWheatBurger2();
        } else
            System.out.println("Invalid Burger");
        return null;
    }
}


public class FactoryMethod {

    static void main(String[] args) {
        String type = "PremiumBurger2";
        BurgerFactory2 factory = new SinghBurgerFactory();
        Burger2 burger = factory.createBurger(type);
        burger.prepare();

        BurgerFactory2 factory2 = new KingBurgerFactory();
        Burger2 burger2 = factory2.createBurger(type);
        burger2.prepare();
    }
}
