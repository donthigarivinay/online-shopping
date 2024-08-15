public class ElectronicsProduct extends Product {
    private static final long serialVersionUID = 1L;
    private int warrantyYears;

    public ElectronicsProduct(String name, double price, int warrantyYears) {
        super(name, price);
        this.warrantyYears = warrantyYears;
    }

    @Override
    public double calculateDiscount() {
        return getPrice() * 0.10; // 10% off
    }

    public int getWarrantyYears() { return warrantyYears; }
}
