
import java.util.ArrayList;
import java.util.Scanner;

public class ReceiptSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Create a list to store receipts
        ArrayList<Receipt> receipts = new ArrayList<>();

        // Create a list to store customers
        ArrayList<Customer> customers = new ArrayList<>();

        // Create a list to store stores
        ArrayList<Store> stores = new ArrayList<>();

        // Main loop to interact with the user
        while (true) {
            System.out.println("1. Add Receipt");
            System.out.println("2. View Receipts");
            System.out.println("3. Generate Reports");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    // Add a receipt
                    Receipt newReceipt = new Receipt();
                    System.out.println("Enter store name: ");
                    String storeName = scanner.next();

                    // Check if the store already exists before adding it
                    Store existingStore = findStoreByName(stores, storeName);
                    if (existingStore == null) {
                        existingStore = new Store(storeName);
                        stores.add(existingStore);
                    }
                    newReceipt.setStore(existingStore);

                    System.out.println("Enter customer name: ");
                    String customerName = scanner.next();

                    // Check if the customer already exists before adding it
                    Customer existingCustomer = findCustomerByName(customers, customerName);
                    if (existingCustomer == null) {
                        existingCustomer = new Customer(customerName);
                        customers.add(existingCustomer);
                    }
                    newReceipt.setCustomer(existingCustomer);

                    // Add items to the receipt
                    while (true) {
                        System.out.println("Enter item name (or 'done' to finish): ");
                        String itemName = scanner.next();
                        if (itemName.equals("done")) {
                            break;
                        }
                        System.out.println("Enter item price: ");
                        double price = scanner.nextDouble();
                        System.out.println("Enter item quantity: ");
                        int quantity = scanner.nextInt();
                        Item item = new Item(itemName, price, quantity);
                        newReceipt.addItem(item);
                    }

                    // Prompt for payment method
                    System.out.println("Select payment method:");
                    System.out.println("1. Cash");
                    System.out.println("2. Credit Card");
                    System.out.print("Enter your choice: ");
                    int paymentChoice = scanner.nextInt();
                    Payment payment = null;

                    switch (paymentChoice) {
                        case 1:
                            System.out.println("Enter cash amount: ");
                            double cashAmount = scanner.nextDouble();
                            payment = new CashPayment(cashAmount);
                            break;
                        case 2:
                            System.out.println("Enter card number: ");
                            String cardNumber = scanner.next();
                            System.out.println("Enter card amount: ");
                            double cardAmount = scanner.nextDouble();
                            payment = new CreditCardPayment(cardAmount, cardNumber);
                            break;
                        default:
                            System.out.println("Invalid payment method. Defaulting to cash payment with amount $0.");
                            payment = new CashPayment(0);
                    }

                    newReceipt.setPaymentMethod(payment);

                    // Calculate total and add receipt to lists
                    newReceipt.calculateTotal();
                    receipts.add(newReceipt);
                    existingStore.addReceipt(newReceipt);
                    existingCustomer.addReceipt(newReceipt);

                    break;

                case 2:
                    // View receipts
                    System.out.println("View receipts by:");
                    System.out.println("1. Customer");
                    System.out.println("2. Store");
                    System.out.print("Enter your choice: ");
                    int viewChoice = scanner.nextInt();

                    if (viewChoice == 1) {
                        System.out.print("Enter customer name: ");
                        String customerNameToView = scanner.next();
                        Customer customerToView = findCustomerByName(customers, customerNameToView);
                        if (customerToView != null) {
                            customerToView.viewReceipts();
                        } else {
                            System.out.println("Customer not found.");
                        }
                    } else if (viewChoice == 2) {
                        System.out.print("Enter store name: ");
                        String storeNameToView = scanner.next();
                        Store storeToView = findStoreByName(stores, storeNameToView);
                        if (storeToView != null) {
                            storeToView.viewReceipts();
                        } else {
                            System.out.println("Store not found.");
                        }
                    }

                    break;

                case 3:
                    // Generate reports
                    System.out.println("Generate reports by:");
                    System.out.println("1. Total spending by customer");
                    System.out.println("2. Total receipts by store");
                    System.out.println("3. Overall spending");
                    System.out.print("Enter your choice: ");
                    int reportChoice = scanner.nextInt();

                    switch (reportChoice) {
                        case 1:
                            System.out.println("Spending by customer:");
                            for (Customer customer : customers) {
                                double customerTotal = 0;
                                for (Receipt customerReceipt : customer.getReceipts()) {
                                    customerTotal += customerReceipt.getTotalAmount();
                                }
                                System.out.println(customer.getName() + ": $" + customerTotal);
                            }
                            break;

                        case 2:
                            System.out.println("Receipts by store:");
                            for (Store store : stores) {
                                double storeTotal = 0;
                                for (Receipt storeReceipt : store.getReceipts()) {
                                    storeTotal += storeReceipt.getTotalAmount();
                                }
                                System.out.println(store.getName() + ": $" + storeTotal);
                            }
                            break;

                        case 3:
                            double overallTotal = 0;
                            for (Receipt overallReceipt : receipts) {
                                overallTotal += overallReceipt.getTotalAmount();
                            }
                            System.out.println("Overall total spending: $" + overallTotal);
                            break;

                        default:
                            System.out.println("Invalid choice. Returning to main menu.");
                    }
                    break;

                case 4:
                    // Exit
                    System.out.println("Exiting...");
                    System.exit(0);
            }
        }
    }

    // Helper methods
    private static Customer findCustomerByName(ArrayList<Customer> customers, String name) {
        for (Customer customer : customers) {
            if (customer.getName().equalsIgnoreCase(name)) {
                return customer;
            }
        }
        return null;
    }

    private static Store findStoreByName(ArrayList<Store> stores, String name) {
        for (Store store : stores) {
            if (store.getName().equalsIgnoreCase(name)) {
                return store;
            }
        }
        return null;
    }
}
