package main.java;

 public class Product {
    private int id;
    private String name;
    private double price;
    private int quantity;

    public Product(int id, String name, double price, int quantity) {
       this.id = id;
       this.name = name;
       this.price = price;
       this.quantity = quantity;
    }

    public int getId() {
       return id;
    }

    public String getName() {
       return name;
    }

    public double getPrice() {
       return price;
    }

    public int getQuantity() {
       return quantity;
    }

    public void decreaseStock(int amount) {
       if (quantity >= amount) {
          quantity -= amount;
       } else {
          System.out.println("Insufficient stock for " + name);
       }
    }
}