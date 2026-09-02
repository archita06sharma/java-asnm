package food.model;

public class PremiumOrder extends FoodOrder implements Discountable {

    // Constructor
    public PremiumOrder(int orderId, String customerName, double amount) {
        super(orderId, customerName, amount);
    }

    // Method overriding
    @Override
    public double calculateDeliveryCharge() {
        return 50;
    }

    // Implementing interface method
    @Override
    public double applyDiscount() {
        return getAmount() * 0.15;
    }
}