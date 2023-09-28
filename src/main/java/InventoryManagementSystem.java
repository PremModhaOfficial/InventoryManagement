package main.java;

import java.util.Scanner;


/**
 * comment this whole code before running
 */

public class InventoryManagementSystem {
    private static MyHashMap<String, Product> inventory = new MyHashMap<>();
    private static MyHashMap<MyDate, Product> expiryDateHashMap = new MyHashMap<>();
    protected static MyLinkedList<Transaction> transactionHistory = new MyLinkedList<>();

    static MyDate currentDate;
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
//        currentDate = new MyDate(scanner.nextLine());
        currentDate = new MyDate("2000-01-01");
        addDummyItems();
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

            String choice = scanner.nextLine();

            switch (choice) {
                case "0":
                    nextDay();
                    break;
                case "1":
                    addItem();
                    break;
                case "2":
                    updateItem();
                    break;
                case "3":
                    removeItem();
                    break;
                case "4":
                    listItems();
                    break;
                case "5":
                    performTransaction();
                    break;
                case "6":
                    checkExpiry();
                    break;
                case "7":
                    viewTransactionHistory();
                    break;
                case "8":
                    System.out.println("Exiting...");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void addDummyItems() {
        inventory.put("Eraser",
                new Product("Eraser", 0.79, 250, "Stationary", new MyDate(2023, 07, 10)));
        inventory.put("Ruler",
                new Product("Ruler", 1.19, 350, "Stationary", new MyDate(2023, 9, 20)));
        inventory.put("Highlighter",
                new Product("Highlighter", 0.89, 300, "Stationary", new MyDate(2023, 10, 25)));
        inventory.put("Paper Clips",
                new Product("Paper Clips", 1.29, 200, "Stationary", new MyDate(2023, 11, 30)));
        inventory.put("Binder Clips",
                new Product("Binder Clips", 1.99, 150, "Stationary", new MyDate(2023, 12, 05)));
        inventory.put("Sticky Notes",
                new Product("Sticky Notes", 2.49, 100, "Stationary", new MyDate(2024, 01, 10)));
        inventory.put("Tape Dispenser",
                new Product("Tape Dispenser", 3.99, 75, "Stationary", new MyDate(2024, 02, 15)));
        inventory.put("Staple Remover",
                new Product("Staple Remover", 2.99, 50, "Stationary", new MyDate(2024, 03, 20)));
        inventory.put("Correction Tape",
                new Product("Correction Tape", 2.79, 100, "Stationary", new MyDate(2024 ,04 ,25)));
        inventory.put("T-Shirt",
                new Product("T-Shirt", 14.99, 200, "Clothes", new MyDate(2023, 07, 10)));
        inventory.put("Jeans",
                new Product("Jeans", 39.99, 150, "Clothes", new MyDate(2023, 8, 15)));
        inventory.put("Sweater",
                new Product("Sweater", 29.99, 100, "Clothes", new MyDate(2023, 9, 20)));
        inventory.put("Sneakers",
                new Product("Sneakers", 49.99, 75, "Shoes", new MyDate(2023, 10, 25)));
        inventory.put("Boots",
                new Product("Boots", 59.99, 50, "Shoes", new MyDate(2023, 11, 30)));
        inventory.put("Sandals",
                new Product("Sandals", 19.99, 100, "Shoes", new MyDate(2023, 12, 05)));
        inventory.put("Wrist Watch",
                new Product("Wrist Watch", 199.99, 30, "Accessories", new MyDate(2024, 01, 10)));
        inventory.put("Necklace",
                new Product("Necklace", 149.99, 20, "Accessories", new MyDate(2024, 02, 15)));
        inventory.put("Earrings",
                new Product("Earrings", 79.99, 50, "Accessories", new MyDate(2024 ,03 ,20)));
        inventory.put("Bracelet",
                new Product("Bracelet", 89.99, 40, "Accessories", new MyDate(2024 ,04 ,25)));
        //20 dummy products
    }

    private static void nextDay() {
        currentDate.nextDay();
        inventory.get(String.valueOf((int) (Math.random() * 100))).decreaseStock(((int) (Math.random() * 15)));


    }

    private static void addItem() {
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

        System.out.print("Enter item Category: ");
        String category = scanner.nextLine();

        System.out.print("Enter expiration date (yyyy-MM-dd) or leave blank: ");
        String expirationDate = scanner.nextLine();

        if (expirationDate.equals("") || expirationDate == null) {
            expirationDate = "NEVER_EXPIRES";
        }
        MyDate expirationDateObject = new MyDate(expirationDate);
        Product newItem = new Product(name, price, quantity, category, expirationDateObject);
        inventory.put(name, newItem);
        expiryDateHashMap.put(expirationDateObject, newItem);
        System.out.println("Item added to the inventory.");
    }

    private static void updateItem() {
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

    private static void removeItem() {
        // Implement item removal logic
        System.out.print("Enter item name to remove: ");
        String name = scanner.nextLine();
        if (!inventory.containsKey(name)) {
            System.out.println("Item does not exist in the inventory.");
            return;
        }
        // Remove from the expiry queue if it has an expiration date
        Product removedItem = inventory.get(name);
        inventory.remove(name);
        if (removedItem.getExpirationDate() != null) {
            expiryDateHashMap.removeValue(removedItem);
        }
        System.out.println("Item removed from the inventory.");
    }

    private static void listItems() {
        // Implement item listing logic
        if (inventory.isEmpty()) {
            System.out.println("Inventory is empty.");
        } else {
            System.out.println("Inventory:");
            inventory.display("-----------------------------");
        }
    }

    /**
     * todo DATE-CLASS if (item.getExpirationDate() != null) {
     * SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
     * System.out.println("Expiration Date: " + dateFormat.format(item.getExpirationDate()));
     */

    private static void performTransaction() {
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
        scanner.nextLine();

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

        Transaction transactionDescription = new Transaction(
                itemName, quantity * item.getPrice(), quantity, (transactionType == 1 ? "Sale: " : "Purchase: ")
        );
        transactionHistory.add(transactionDescription);
    }

    private static void checkExpiry() {
        // Implement expiry checking logic
        System.out.println("Items nearing expiration:");
        sortByDateAndDisplay(expiryDateHashMap);
    }

    private static void sortByDateAndDisplay(MyHashMap<MyDate, Product> expiryDateHashMap) {
        for (int i = 0; i < expiryDateHashMap.capacity; i++) {
            MyHashMap.Node<MyDate, Product> n = expiryDateHashMap.getTable()[i];
            MyDate productExpiry;
            while (n != null) {
                productExpiry = n.value.getExpirationDate();
                System.out.println(productExpiry + " " + n.value);
                n = n.next;
            }

        }
    }

    private static void viewTransactionHistory() {
        System.out.println("Transaction History:");
        transactionHistory.display();
    }
}