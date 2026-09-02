package food.utility;

import food.model.FoodOrder;
import food.model.Discountable;

public class OrderUtility {

    // Validate amount
    public static boolean validateAmount(double amount) {
        return amount > 0;
    }

    // Validate customer name
    public static boolean validateCustomerName(String customerName) {
        return customerName != null && !customerName.trim().isEmpty();
    }

    // Generate order summary
    public static void generateOrderSummary(FoodOrder order) {

        double discount = 0;

        if (order instanceof Discountable) {
            Discountable discountableOrder = (Discountable) order;
            discount = discountableOrder.applyDiscount();
        }

        double deliveryCharge = order.calculateDeliveryCharge();

        double finalAmount =
                order.getAmount() - discount + deliveryCharge;

        System.out.println("----------------------------------------");
        System.out.println("Order ID          : " + order.getOrderId());
        System.out.println("Customer Name     : " + order.getCustomerName());
        System.out.println("Order Amount      : Rs. " + order.getAmount());
        System.out.println("Discount          : Rs. " + discount);
        System.out.println("Delivery Charge   : Rs. " + deliveryCharge);
        System.out.println("Final Payable     : Rs. " + finalAmount);
        System.out.println("----------------------------------------");
    }
}