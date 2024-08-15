import java.io.Serializable;
import java.util.*;

public class Customer implements Serializable {
    private static final long serialVersionUID = 1L;
    private String name;
    private String email;
    private String password;
    private Cart cart = new Cart();
    private List<Order> orderHistory = new ArrayList<>();

    public Customer(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public String getEmail() { return email; }
    public String getPassword() { return password; }
    public String getName() { return name; }
    public Cart getCart() { return cart; }

    public void placeOrder() {
        double total = cart.calculateTotal();
        if (total == 0) {
            System.out.println("Cart is empty!");
            return;
        }
        orderHistory.add(new Order(new ArrayList<>(cart.getItems()), total));
        cart.clearCart();
        System.out.println("✅ Order placed successfully!");
    }

    public void showOrders() {
        if (orderHistory.isEmpty()) {
            System.out.println("No previous orders found.");
        } else {
            System.out.println("\n📦 Order History for " + name + ":");
            for (Order order : orderHistory) order.displayOrder();
        }
    }
}
