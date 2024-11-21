
public abstract class Payment {
    private double amount;

    public Payment(double amount) {
        this.amount = amount;
    }

    public double getAmount() {
        return amount;
    }

    public abstract String getType();
}

class CashPayment extends Payment {
    public CashPayment(double amount) {
        super(amount);
    }

    @Override
    public String getType() {
        return "Cash";
    }
}

class CreditCardPayment extends Payment {
    private String cardNumber;

    public CreditCardPayment(double amount, String cardNumber) {
        super(amount);
        this.cardNumber = cardNumber;
    }

    @Override
    public String getType() {
        return "Credit Card";
    }
}

