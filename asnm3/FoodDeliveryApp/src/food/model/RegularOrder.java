package food.model;

public class RegularOrder extends FoodOrder implements Discountable {

    // Constructor
    public RegularOrder(int orderId, String customerName, double amount) {
        super(orderId, customerName, amount);
    }

    // Method overriding
    @Override
    public double calculateDeliveryCharge() {
        return 80;
    }

    // Implementing interface method
    @Override
    public double applyDiscount() {
        return getAmount() * 0.10;
    }
}