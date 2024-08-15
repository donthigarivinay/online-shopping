import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class Order implements Serializable {
    private static final long serialVersionUID = 1L;
    private List<Product> orderedItems;
    private double totalAmount;
    private String dateTime;

    public Order(List<Product> items, double totalAmount) {
        this.orderedItems = items;
        this.totalAmount = totalAmount;
        this.dateTime = LocalDateTime.now()
                .format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm"));
    }

    public void displayOrder() {
        System.out.println("\n🧾 Order on: " + dateTime);
        for (Product p : orderedItems) {
            System.out.println("- " + p.getName() + " " + p.getFinalPrice());
        }
        System.out.println("Total Paid: %2f" + totalAmount);
    }
}
