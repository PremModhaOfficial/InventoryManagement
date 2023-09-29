package main.java;

/**
 * Changed Product class
 */
public class Product {
    public int id;
    public static int ID_END = 1000;
    public String name;
    public double price;
    public int quantity;
    public String category;
    public MyDate expirationDate;

    public Product(String name, double price, int quantity, String category, MyDate expirationDate) {
        this.id = ID_END++;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.category = category;
        this.expirationDate = expirationDate;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setExpirationDate(MyDate expirationDate) {
        this.expirationDate = expirationDate;
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

    @Override
    public String toString() {
        return "Product \n" +
                "id=" + id +
                "\nname='" + name + '\'' +
                "\ncategory='" + category + '\'' +
                "\nprice=" + price +
                "\nquantity=" + quantity +
                "\nexpirationDate='" + expirationDate + '\'';
    }

    public void decreaseStock(int amount) {
        if (quantity >= amount) {
            quantity -= amount;
        } else {
            System.out.println("Insufficient stock for " + name);
        }
    }

    public MyDate getExpirationDate() {
        return expirationDate;
    }

}