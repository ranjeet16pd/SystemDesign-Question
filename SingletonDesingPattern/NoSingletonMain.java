package SingletonDesingPattern;


class Nosingleton {
    public Nosingleton() {
        System.out.println("Nosingleton Object created ");
    }
}

public class NoSingletonMain {

    public static void main(String[] args) {
        Nosingleton nosingleton1 = new Nosingleton();
        Nosingleton nosingleton2 = new Nosingleton();
        if (nosingleton1 != nosingleton2) {
            System.out.println("Nosingleton Object created Twice");
        }
    }
}
