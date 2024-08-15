public class CreditCardPayment implements Payment {
    private String cardNumber;
    public CreditCardPayment(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    @Override
    public void pay(double amount) {
        System.out.println("Paid " + amount + " using Credit Card ending with " +
                cardNumber.substring(cardNumber.length() - 4));
    }
}
