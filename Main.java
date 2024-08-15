import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<Customer> customers = DataStore.loadCustomers();
        Customer currentUser = null;

        while (true) {
            System.out.println("\n=== ONLINE SHOPPING SYSTEM ===");
            System.out.println("1. Login");
            System.out.println("2. Sign Up");
            System.out.println("3. Exit");
            System.out.print("Choose: ");
            int ch = sc.nextInt(); sc.nextLine();

            if (ch == 1) {
                System.out.print("Enter email: ");
                String email = sc.nextLine();
                System.out.print("Enter password: ");
                String pass = sc.nextLine();

                currentUser = customers.stream()
                        .filter(c -> c.getEmail().equals(email) && c.getPassword().equals(pass))
                        .findFirst().orElse(null);

                if (currentUser != null) {
                    System.out.println("Welcome back, " + currentUser.getName() + "!");
                    userMenu(sc, customers, currentUser);
                    DataStore.saveCustomers(customers);
                } else {
                    System.out.println("Invalid credentials!");
                }
            } else if (ch == 2) {
                System.out.print("Enter name: ");
                String name = sc.nextLine();
                System.out.print("Enter email: ");
                String email = sc.nextLine();
                System.out.print("Enter password: ");
                String pass = sc.nextLine();

                Customer newCustomer = new Customer(name, email, pass);
                customers.add(newCustomer);
                DataStore.saveCustomers(customers);
                System.out.println("Signup successful! You can now login.");
            } else {
                System.out.println("Goodbye!");
                break;
            }
        }
        sc.close();
    }

    // Updated userMenu with Remove from Cart + Auto-save
    private static void userMenu(Scanner sc, List<Customer> customers, Customer user) {
        boolean loggedIn = true;

        while (loggedIn) {
            System.out.println("\n----- MENU -----");
            System.out.println("1. Add Electronics");
            System.out.println("2. Add Clothing");
            System.out.println("3. Remove from Cart");
            System.out.println("4. View Cart");
            System.out.println("5. Checkout");
            System.out.println("6. View Orders");
            System.out.println("7. Logout");
            System.out.print("Choose: ");
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1 -> {
                    System.out.print("Product name: ");
                    String name = sc.nextLine();
                    System.out.print("Price: ");
                    double price = sc.nextDouble();
                    System.out.print("Warranty (yrs): ");
                    int w = sc.nextInt();
                    user.getCart().addProduct(new ElectronicsProduct(name, price, w));
                    DataStore.saveCustomers(customers); // save after add
                }
                case 2 -> {
                    System.out.print("Product name: ");
                    String name = sc.nextLine();
                    System.out.print("Price: ");
                    double price = sc.nextDouble();
                    sc.nextLine();
                    System.out.print("Size (S/M/L): ");
                    String size = sc.nextLine();
                    user.getCart().addProduct(new ClothingProduct(name, price, size));
                    DataStore.saveCustomers(customers); // save after add
                }
                case 3 -> {
                    if (user.getCart().isEmpty()) {
                        System.out.println("🛒 Your cart is empty!");
                    } else {
                        user.getCart().showCart();
                        System.out.print("Enter Product ID to remove: ");
                        int pid = sc.nextInt();
                        sc.nextLine();
                        user.getCart().removeProductById(pid);
                        DataStore.saveCustomers(customers); // save after remove
                    }
                }
                case 4 -> user.getCart().showCart();
                case 5 -> {
                    double total = user.getCart().calculateTotal();
                    if (total == 0) {
                        System.out.println("Cart is empty!");
                        break;
                    }
                    System.out.printf("Total: %.2f\n", total);
                    System.out.println("Choose payment method:");
                    System.out.println("1. Credit Card");
                    System.out.println("2. UPI");
                    int pm = sc.nextInt(); sc.nextLine();
                    Payment payment;
                    if (pm == 1) {
                        System.out.print("Enter card number: ");
                        payment = new CreditCardPayment(sc.nextLine());
                    } else {
                        System.out.print("Enter UPI ID: ");
                        payment = new UPIPayment(sc.nextLine());
                    }
                    payment.pay(total);
                    user.placeOrder();
                    DataStore.saveCustomers(customers); // persist order
                }
                case 6 -> user.showOrders();
                case 7 -> {
                    loggedIn = false;
                    System.out.println("Logged out!");
                    DataStore.saveCustomers(customers);
                }
                default -> System.out.println("Invalid choice!");
            }
        }
    }
}
