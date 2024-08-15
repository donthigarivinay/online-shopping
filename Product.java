import java.io.Serializable;

public abstract class Product implements Serializable {
    private static final long serialVersionUID = 1L;
    private static int idCounter = 1000;
    private int productId;
    private String name;
    private double price;

    public Product(String name, double price) {
        this.productId = ++idCounter;
        this.name = name;
        this.price = price;
    }

    public int getProductId() { return productId; }
    public String getName() { return name; }
    public double getPrice() { return price; }

    public abstract double calculateDiscount();

    public double getFinalPrice() {
        return price - calculateDiscount();
    }

    public void displayInfo() {
        System.out.println("Product ID: " + productId);
        System.out.println("Name: " + name);
        System.out.println("Price: " + price);
        System.out.printf("Discount: %.2f\n", calculateDiscount());
        System.out.println("Final Price: " + getFinalPrice());
        System.out.println("-----------------------------------");
    }
}
