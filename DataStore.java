import java.io.*;
import java.util.*;

public class DataStore {
    private static final String FILE_PATH = "data/users.dat";

    @SuppressWarnings("unchecked")
    public static List<Customer> loadCustomers() {
        File file = new File(FILE_PATH);
        if (!file.exists()) return new ArrayList<>();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            return (List<Customer>) ois.readObject();
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public static void saveCustomers(List<Customer> customers) {
        new File("data").mkdirs(); // ensure directory exists
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_PATH))) {
            oos.writeObject(customers);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
