package StrategyDesignPattern;


interface PayMethod {
    public void pay();
}

class UPIPay implements PayMethod {
    @Override
    public void pay() {
        System.out.println("Payment Done VIA INDIAN UPI !");
    }
}

class DebitPay implements PayMethod {
    @Override
    public void pay() {
        System.out.println("Payment Done VIA INDIAN DEBIT !");
    }
}

class CreditPay implements PayMethod {
    @Override
    public void pay() {
        System.out.println("Payment Done VIA INDIAN CREDIT !");
    }
}

class CreditDebitPay implements PayMethod {
    @Override
    public void pay() {
        System.out.println("Payment Done VIA INDIAN CREDIT and DEBIT !");
    }
}

class GpPay implements PayMethod {
    @Override
    public void pay() {
        System.out.println("Payment Done VIA INDIAN GPS !");
    }
}


class ClientStrategy {
    PayMethod pay;
    public ClientStrategy(PayMethod pay) {
        this.pay = pay;
    }

    public void pay() {
        pay.pay();
    }
}


public class PaymentStrategies {

    public static void main(String[] args) {
        ClientStrategy cs = new ClientStrategy(new UPIPay());
        cs.pay();
        ClientStrategy cs2 = new ClientStrategy(new DebitPay());
        cs2.pay();
        ClientStrategy cs3 = new ClientStrategy(new CreditPay());
        cs3.pay();
        ClientStrategy cs4 = new ClientStrategy(new CreditDebitPay());
        cs4.pay();

    }
}
