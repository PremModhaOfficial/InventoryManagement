package main.java;

class Transaction {
    private static int nextTransactionId = 1000;
    private int transactionId;
    private String productName;
    private double transactionAmount;
    private int productQuantity;
    private String transactionType;

    public Transaction(String productName, double transactionAmount, int productQuantity, String transactionType) {
        this.transactionId = nextTransactionId++;
        this.productName = productName;
        this.transactionAmount = transactionAmount;
        this.productQuantity = productQuantity;
        this.transactionType = transactionType;
    }

    public int getTransactionId() {
        return transactionId;
    }

    public String getProductName() {
        return productName;
    }

    public double getTransactionAmount() {
        return transactionAmount;
    }

    public int getProductQuantity() {
        return productQuantity;
    }

    public String getTransactionType() {
        return transactionType;
    }

    @Override
    public String toString() {
        return "Transaction ID: " + transactionId +
                ", Product Name: " + productName +
                ", Transaction Amount: $" + transactionAmount +
                ", Product Quantity: " + productQuantity +
                ", Transaction Type: " + transactionType;
    }
}

// New PErform Transaction.

/*private static void performTransaction(Scanner scanner) {
    System.out.println("Transaction Types:");
    System.out.println("1. Sale (Decrease quantity)");
    System.out.println("2. Purchase (Increase quantity)");
    System.out.print("Enter transaction type (1 for Sale, 2 for Purchase): ");

    int transactionType = scanner.nextInt();
    scanner.nextLine(); // Consume the newline

    if (transactionType != 1 && transactionType != 2) {
        System.out.println("Invalid transaction type.");
        return;
    }

    System.out.print("Enter item name: ");
    String itemName = scanner.nextLine();

    Product item = inventory.get(itemName);

    if (item == null) {
        System.out.println("Item not found in the inventory.");
        return;
    }

    System.out.print("Enter quantity: ");
    int quantity = scanner.nextInt();

    if (quantity <= 0) {
        System.out.println("Invalid quantity.");
        return;
    }

    if (transactionType == 1) {
        if (quantity > item.getQuantity()) {
            System.out.println("Not enough quantity for sale.");
            return;
        }
        // Perform sale (decrease quantity)
        item.setQuantity(item.getQuantity() - quantity);
        double saleAmount = quantity * item.getPrice();
        Transaction saleTransaction = new Transaction(itemName, saleAmount, quantity, "Sale");
        transactionHistory.add(saleTransaction);
        System.out.println(quantity + " units of " + itemName + " sold.");
    } else if (transactionType == 2) {
        // Perform purchase (increase quantity)
        item.setQuantity(item.getQuantity() + quantity);
        double purchaseAmount = quantity * item.getPrice();
        Transaction purchaseTransaction = new Transaction(itemName, purchaseAmount, quantity, "Purchase");
        transactionHistory.add(purchaseTransaction);
        System.out.println(quantity + " units of " + itemName + " purchased.");
    }
}
 */