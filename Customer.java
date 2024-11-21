import java.util.ArrayList;

public class Customer {
    private String name;
    private ArrayList<Receipt> receipts;

    public Customer(String name) {
        this.name = name;
        this.receipts = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public ArrayList<Receipt> getReceipts() {
        return receipts;
    }

    public void addReceipt(Receipt receipt) {
        this.receipts.add(receipt);
    }

    public void viewReceipts() {
        for (Receipt receipt : receipts) {
            System.out.println(receipt.toString());
        }
    }
}
