import java.io.Serializable;
import java.util.*;

public class Cart implements Serializable {
    private static final long serialVersionUID = 1L;
    private List<Product> items = new ArrayList<>();

    public void addProduct(Product product) {
        items.add(product);
        System.out.println("✅ " + product.getName() + " added to cart.");
    }

    public void removeProductById(int productId) {
        boolean removed = items.removeIf(p -> p.getProductId() == productId);
        if (removed) {
            System.out.println("🗑️ Product removed from cart successfully.");
        } else {
            System.out.println("❌ Product ID not found in cart.");
        }
    }

    public void clearCart() {
        items.clear();
    }

    public double calculateTotal() {
        return items.stream().mapToDouble(Product::getFinalPrice).sum();
    }

    public List<Product> getItems() { return items; }

    public void showCart() {
        if (items.isEmpty()) {
            System.out.println("🛒 Cart is empty!");
        } else {
            System.out.println("\n🛒 Your Cart Items:");
            for (Product p : items) p.displayInfo();
            System.out.printf("Total: ₹%.2f\n", calculateTotal());
        }
    }

    public boolean isEmpty() {
        return items.isEmpty();
    }
}
