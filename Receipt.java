import java.util.ArrayList;


public class Receipt {
    private int id;
    private Store store;
    private Customer customer;
    private ArrayList<Item> items;
    private double totalAmount;
    private Payment paymentMethod;

    public Receipt() {
        this.items = new ArrayList<>();
    }

    public void setStore(Store store) {
        this.store = store;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public void setPaymentMethod(Payment paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public void addItem(Item item) {
        this.items.add(item);
    }

    public void calculateTotal() {
        totalAmount = 0;
        for (Item item : items) {
            totalAmount += item.getPrice() * item.getQuantity();
        }
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    @Override
    public String toString() {
        String storeName = (store != null) ? store.getName() : "No Store Assigned";
        String customerName = (customer != null) ? customer.getName() : "No Customer Assigned";
        return "Store: " + storeName + ", Customer: " + customerName + ", Total: " + totalAmount;
    }
}
