package SingletonDesingPattern;


class SimpleSingleton {

    private static SimpleSingleton instance = null;

    public SimpleSingleton() {
        System.out.println("SimpleSingleton object created ");
    }

    public static SimpleSingleton getInstance() {
        if (instance == null) {
            instance = new SimpleSingleton();
        }
        return instance;
    }
}


// But this is not thread Safe
public class SimpleSingletonMain {
    public static void main(String[] args) {
        SimpleSingleton singleton = SimpleSingleton.getInstance();
        SimpleSingleton singleton2 = SimpleSingleton.getInstance();
        System.out.println(singleton == singleton2);

    }
}
