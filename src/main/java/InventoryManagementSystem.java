package main.java;

import java.util.*;



public class InventoryManagementSystem {
    private static MyHashMap<String, Product> inventory = new MyHashMap<>();
    private static PriorityQueue<Product> expiryQueue = new PriorityQueue<>(Comparator.comparing(Product::getExpirationDate));
    private static MyLinkedList<String> transactionHistory = new MyLinkedList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nInventory Management System");
            System.out.println("1. Add Item");
            System.out.println("2. Update Item");
            System.out.println("3. Remove Item");
            System.out.println("4. List Items");
            System.out.println("5. Perform Transaction");
            System.out.println("6. Check Expiry");
            System.out.println("7. View Transaction History");
            System.out.println("8. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline

            switch (choice) {
                case 1:
                    addItem(scanner);
                    break;
                case 2:
                    updateItem(scanner);
                    break;
                case 3:
                    removeItem(scanner);
                    break;
                case 4:
                    listItems();
                    break;
                case 5:
                    performTransaction(scanner);
                    break;
                case 6:
                    checkExpiry();
                    break;
                case 7:
                    viewTransactionHistory();
                    break;
                case 8:
                    System.out.println("Exiting...");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void addItem(Scanner scanner) {
        // Implement item addition logic
        System.out.print("Enter item name: ");
    String name = scanner.nextLine();
    if (inventory.containsKey(name)) {
        System.out.println("Item already exists in the inventory.");
        return;
    }
    System.out.print("Enter item quantity: ");
    int quantity = scanner.nextInt();
    System.out.print("Enter item price: ");
    double price = scanner.nextDouble();
    scanner.nextLine(); // Consume the newline
        /**
    System.out.print("Enter expiration date (yyyy-MM-dd) or leave blank: ");
    String dateString = scanner.nextLine();
    Date expirationDate = null;
    if (!dateString.isEmpty()) {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            expirationDate = dateFormat.parse(dateString);
        } catch (Exception e) {
            System.out.println("Invalid date format. Item will be added without an expiration date.");
        }
    }
         */
    Product newItem = new Product(name, quantity, price, expirationDate);
    inventory.put(name, newItem);
    if (expirationDate != null) {
        expiryQueue.offer(newItem);
    }
    System.out.println("Item added to the inventory.");
    }

    private static void updateItem(Scanner scanner) {
        // Implement item update logic
        System.out.print("Enter item name to update: ");
    String name = scanner.nextLine();
    if (!inventory.containsKey(name)) {
        System.out.println("Item does not exist in the inventory.");
        return;
    }
    System.out.print("Enter new quantity: ");
    int newQuantity = scanner.nextInt();
    Product item = inventory.get(name);
    item.setQuantity(newQuantity);
    System.out.println("Item updated successfully.");
    }

    private static void removeItem(Scanner scanner) {
        // Implement item removal logic
        System.out.print("Enter item name to remove: ");
        String name = scanner.nextLine();
        if (!inventory.containsKey(name)) {
            System.out.println("Item does not exist in the inventory.");
            return;
        }
        inventory.remove(name);
        // Remove from the expiry queue if it has an expiration date
        Product removedItem = inventory.get(name);
        if (removedItem.getExpirationDate() != null) {
            expiryQueue.remove(removedItem);
        }
        System.out.println("Item removed from the inventory.");
    }

    private static void listItems() {
        // Implement item listing logic
        if (inventory.isEmpty()) {
            System.out.println("Inventory is empty.");
        } else {
            System.out.println("Inventory:");
            for (Product item : inventory.values()) {
                System.out.println("Name: " + item.getName());
                System.out.println("Quantity: " + item.getQuantity());
                System.out.println("Price: $" + item.getPrice());
                if (item.getExpirationDate() != null) {
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    System.out.println("Expiration Date: " + dateFormat.format(item.getExpirationDate()));
                }
                System.out.println("-------------------");
            }
        }
    }

    private static void performTransaction(Scanner scanner) {
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

    if (!inventory.containsKey(itemName)) {
        System.out.println("Item not found in the inventory.");
        return;
    }

    Product item = inventory.get(itemName);

    System.out.print("Enter quantity: ");
    int quantity = scanner.nextInt();

    if (transactionType == 1) {
        if (quantity <= 0 || quantity > item.getQuantity()) {
            System.out.println("Invalid quantity for sale.");
            return;
        }
        // Perform sale (decrease quantity)
        item.setQuantity(item.getQuantity() - quantity);
        System.out.println(quantity + " units of " + itemName + " sold.");
    } else if (transactionType == 2) {
        if (quantity <= 0) {
            System.out.println("Invalid quantity for purchase.");
            return;
        }
        // Perform purchase (increase quantity)
        item.setQuantity(item.getQuantity() + quantity);
        System.out.println(quantity + " units of " + itemName + " purchased.");
    }

    // Record the transaction in history
    String transactionDescription = (transactionType == 1 ? "Sale: " : "Purchase: ") + quantity + " units of " + itemName;
    transactionHistory.add(transactionDescription);
    }

    private static void checkExpiry() {
        // Implement expiry checking logic
        System.out.println("Items nearing expiration:");
        Date currentDate = new Date();
        while (!expiryQueue.isEmpty()) {
            Product item = expiryQueue.peek();
            if (item.getExpirationDate().after(currentDate)) {
                break;
            }
            System.out.println("Name: " + item.getName());
            System.out.println("Expiration Date: " + new SimpleDateFormat("yyyy-MM-dd").format(item.getExpirationDate()));
            System.out.println("-------------------");
            expiryQueue.poll();
        }
    }

    private static void viewTransactionHistory() {
        System.out.println("Transaction History:");
        for (String transaction : transactionHistory) {
            System.out.println(transaction);
        }
    }
}