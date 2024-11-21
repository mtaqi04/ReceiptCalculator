import java.util.ArrayList;

public class Store {
    private String name;
    private ArrayList<Receipt> receipts;

    public Store(String name) {
        this.name = name;
        this.receipts = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public ArrayList<Receipt> getReceipts() {
        return receipts; // Ensure this method is correctly defined
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

