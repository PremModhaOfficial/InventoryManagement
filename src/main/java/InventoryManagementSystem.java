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
        inventory.put("eraser",
                new Product("Eraser", 20.00, 250, "Stationary", new MyDate(2023, 7, 10)));
        inventory.put("ruler",
                new Product("Ruler", 50.00, 350, "Stationary", new MyDate(2023, 9, 20)));
        inventory.put("highlighter",
                new Product("Highlighter", 30.00, 300, "Stationary", new MyDate(2023, 10, 25)));
        inventory.put("paper clips",
                new Product("Paper Clips", 40.00, 200, "Stationary", new MyDate(2023, 11, 30)));
        inventory.put("binder clips",
                new Product("Binder Clips", 70.00, 150, "Stationary", new MyDate(2023, 12, 5)));
        inventory.put("sticky notes",
                new Product("Sticky Notes", 20.00, 100, "Stationary", new MyDate(2024, 1, 10)));
        inventory.put("tape dispenser",
                new Product("Tape Dispenser", 150.00, 75, "Stationary", new MyDate(2024, 2, 15)));
        inventory.put("staple remover",
                new Product("Staple Remover", 100.00, 50, "Stationary", new MyDate(2024, 3, 20)));
        inventory.put("correction tape",
                new Product("Correction Tape", 150.00, 100, "Stationary", new MyDate(2024, 4, 25)));
        inventory.put("t-shirt",
                new Product("T-Shirt", 500.00, 200, "Clothes", new MyDate(2023, 7, 10)));
        inventory.put("jeans",
                new Product("Jeans", 1200.00, 150, "Clothes", new MyDate(2023, 8, 15)));
        inventory.put("sweater",
                new Product("Sweater", 800.00, 100, "Clothes", new MyDate(2023, 9, 20)));
        inventory.put("sneakers",
                new Product("Sneakers", 2000.00, 75, "Shoes", new MyDate(2023, 10, 25)));
        inventory.put("boots",
                new Product("Boots", 4000.00, 50, "Shoes", new MyDate(2023, 11, 30)));
        inventory.put("sandals",
                new Product("Sandals", 1500.00, 100, "Shoes", new MyDate(2023, 12, 5)));
        inventory.put("wrist watch",
                new Product("Wrist Watch", 6000.00, 30, "Accessories", new MyDate(2024, 1, 10)));
        inventory.put("necklace",
                new Product("Necklace", 4000.00, 20, "Accessories", new MyDate(2024, 2, 15)));
        inventory.put("earrings",
                new Product("Earrings", 2500.00, 50, "Accessories", new MyDate(2024, 3, 20)));
        inventory.put("bracelet",
                new Product("Bracelet", 1500.00, 40, "Accessories", new MyDate(2024, 4, 25)));
        //20 dummy products
        MyHashMap.Node<String, Product>[] table = inventory.getTable();
        for (int i = 0; i < table.length; i++) {
            MyHashMap.Node<String, Product> current = table[i];
            while (current != null) {
                Product product = current.value;
                if (product.getExpirationDate() != null) {
                    expiryDateHashMap.put(product.getExpirationDate(), product);
                }
                current = current.next;
            }
        }
    }

    private static void nextDay() {
        currentDate = currentDate.nextDay();
        MyHashMap.Node<String, Product> productNode = null;
        while (productNode == null) {
            productNode = inventory.getTable()[(int) (Math.random() * inventory.size)];
        }
        Product randomItem = productNode.value;
        System.out.println(randomItem);
        int quantity = (int) (randomItem.quantity * Math.random());
        performTransaction(1, randomItem, quantity);
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

        if (expirationDate.equals("")){
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
        if (!inventory.containsKey(name.toLowerCase())) {
            System.out.println("Item does not exist in the inventory.");
            return;
        }

        System.out.print("Enter new quantity: ");
        int newQuantity = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Enter new Price");
        double newPrice = scanner.nextDouble();
        scanner.nextLine();
        Product item = inventory.get(name);
        item.setQuantity(newQuantity);
        item.setPrice(newPrice);
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

    private static void performTransaction(int transactionType, Product p, int quantity) {
        if (quantity <= 0 || quantity > p.getQuantity()) {
            System.out.println("""
                    !!!!!!!!!!!!!!!!!!!!!!!!!!
                    Invalid quantity for sale.
                    !!!!!!!!!!!!!!!!!!!!!!!!!!""");
            return;
        }
        p.setQuantity(p.getQuantity() - quantity);
        System.out.println(quantity + " units of " + p.name + " sold.");

        Transaction transactionDescription = new Transaction(
                p.name, quantity * p.getPrice(), quantity, (transactionType == 1 ? "Sale: " : "Purchase: ")
        );
        transactionHistory.add(transactionDescription);
    }

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
                System.out.println("""
                        !!!!!!!!!!!!!!!!!!!!!!!!!!
                        Invalid quantity for sale.
                        !!!!!!!!!!!!!!!!!!!!!!!!!!""");
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
        System.out.println("Items nearing expiration:\n");
        int expiredItemsCount = 0;
        for (int i = 0; i < expiryDateHashMap.capacity; i++) {
            MyHashMap.Node<MyDate, Product> n = expiryDateHashMap.getTable()[i];
            while (n != null) {
                if (currentDate.greaterThan(n.key)) {
                    System.out.println(n.value.name + " has been expired and Will be removed now...");
                    inventory.remove(n.value.getName());
                    expiredItemsCount++;
                }
                n = n.next;
            }
        }
        System.out.println("Total of " +expiredItemsCount + " products has been expired and has been removed.");
    }


    private static void viewTransactionHistory() {
        System.out.println("Transaction History:");
        transactionHistory.display();
    }
}