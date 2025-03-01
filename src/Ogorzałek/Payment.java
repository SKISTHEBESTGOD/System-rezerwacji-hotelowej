package Ogorzałek;
public class Payment {
    private double price;
    private PaymentType paymentType;

    public Payment(double price, PaymentType paymentType) {
        this.price = price;
        this.paymentType = paymentType;
    }

    public double getPrice() {
        return price;
    }

    public PaymentType getPaymentType() {
        return paymentType;
    }

    @Override
    public String toString() {
        return "Płatność w wysokości " + price + " przy użyciu " + paymentType;
    }
}
