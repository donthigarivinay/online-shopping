public class ClothingProduct extends Product {
    private static final long serialVersionUID = 1L;
    private String size;

    public ClothingProduct(String name, double price, String size) {
        super(name, price);
        this.size = size;
    }

    @Override
    public double calculateDiscount() {
        return getPrice() * 0.20; // 20% off
    }

    public String getSize() { return size; }
}
